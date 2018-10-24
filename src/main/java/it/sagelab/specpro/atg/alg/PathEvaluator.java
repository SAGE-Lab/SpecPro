package it.sagelab.specpro.atg.alg;

import it.sagelab.specpro.models.ba.BuchiAutomata;
import it.sagelab.specpro.models.ba.Edge;
import it.sagelab.specpro.models.ba.Vertex;
import it.sagelab.specpro.models.ltl.assign.Assignment;

import java.util.*;

import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.toList;

public class PathEvaluator {

    private class Option {
        Set<Edge> edges = new HashSet<>();
        Assignment assignment = null;

        public Option() { }

        public Option(Edge edge, Assignment assignment) {
            this.edges.add(edge);
            this.assignment = assignment;
        }
    }

    protected ArrayList<BuchiAutomata> graphs;
    protected PathsBuilder pathsBuilder;

    public PathEvaluator(ArrayList<BuchiAutomata> graphs, PathsBuilder pathsBuilder) {
        this.graphs = graphs;
        this.pathsBuilder = pathsBuilder;
    }


    public List<Assignment> evaluate(List<Assignment> path) {

        int minSize = path.stream().mapToInt(a -> a.getAssignments().size()).min().getAsInt();
        if(minSize == 0)
            return null;

        Vertex[] initStates = new Vertex[graphs.size()];
        for(int i = 0; i < graphs.size(); ++i) {
            BuchiAutomata ba = graphs.get(i);
            initStates[i] = ba.outgoingEdgesOf(ba.getInitStates()[0]).stream().map(e -> e.getTarget()).findFirst().get();
        }

        return evaluate(path, initStates, 0);
    }

    private List<Assignment> evaluate(List<Assignment> path, Vertex[] currentState, int index) {
        if(index >= path.size())
            return path;

        Assignment s = path.get(index);
        // System.out.println("Evaluating: " + s);
        List<List<Option>> allOptions = new ArrayList<>();
        for(int i = 0; i < currentState.length; ++i) {
            final Vertex v = currentState[i];
            BuchiAutomata ba = graphs.stream().filter(g -> g.containsVertex(v)).findFirst().get();
            List<Option> options = getCompatibleOptions(ba, v, s);
            if(options.size() == 0) {
                return null;
            }
            allOptions.add(options);
        }

        Option baseOption = new Option();
        baseOption.assignment = s;

        List<Option> goodOptions = findOptions(allOptions, baseOption);

        if(goodOptions == null) {
            return null;
        }

        System.out.println("Good Options: " + goodOptions.size());
        Map<Set<Edge>, List<Option>> mappedOptions = goodOptions.stream().collect(groupingBy(o -> o.edges));
        System.out.println("Mapped Options: " + mappedOptions.size());

        // System.out.println("Assignment " + baseOption.assignment + " has " + goodOptions.size() + " good options");

        for(List<Option> options: mappedOptions.values()) {
            List<Assignment> newPath = evaluate(path, getStates(options.get(0)), index + 1);
            if(newPath != null) {
                newPath.set(index, options.get(0).assignment);
                return newPath;
            }
        }



        return null;
    }

    private Vertex[] getStates(Option option) {
        Vertex [] vertices = new Vertex[option.edges.size()];
        int i = 0;
        for(Edge e: option.edges) {
            vertices[i++] = e.getTarget();
        }
        return vertices;
    }

    private List<Option> findOptions(List<List<Option>> allOptions, Option baseOption) {
        while(allOptions != null && allOptions.size() > 1) {
            allOptions = filterOptions(allOptions, baseOption);
            allOptions = merge(allOptions);
        }
        if(allOptions == null) {
            return null;
        }
        List<Option> options = merge(Arrays.asList(baseOption), allOptions.get(0));
        if (options == null || options.size() == 0) {
            return null;
        } else {
            return options;
        }
    }

    private List<List<Option>> merge(List<List<Option>> allOptions) {
        if(allOptions == null) {
            return null;
        }
        List<List<Option>> mergedOptions = new ArrayList<>();

        for(int i = 0; i < allOptions.size(); i += 2) {
            if(i + 1 < allOptions.size()) {
                List<Option> mergedOpt = merge(allOptions.get(i), allOptions.get(i + 1));
                if (mergedOpt == null || mergedOpt.size() == 0)
                    return null;
                mergedOptions.add(mergedOpt);
            } else {
                mergedOptions.add(allOptions.get(i));
            }
        }

        return mergedOptions;
    }

    private List<Option> merge(List<Option> l1, List<Option> l2) {
        List<Option> mergedOpt = new ArrayList<>();
        for(Option o1: l1) {
            for(Option o2: l2) {
                Assignment a = o1.assignment.combine(o2.assignment);
                if(a != null) {
                    Option opt = new Option();
                    opt.assignment = a;
                    opt.edges.addAll(o1.edges);
                    opt.edges.addAll(o2.edges);
                    mergedOpt.add(opt);
                }
            }
        }
        return mergedOpt;
    }

    private List<List<Option>> filterOptions(List<List<Option>> allOptions, Option baseOption) {
        allOptions.sort(Comparator.comparingInt(List::size));

//        for(List<Option> options: allOptions)
//            System.out.print(options.size() + " ");
//        System.out.println();

        int i = 0;
        while(i < allOptions.size() && allOptions.get(i).size() == 1 && baseOption.assignment != null) {
            baseOption.assignment = baseOption.assignment.combine(allOptions.get(i).get(0).assignment);
            baseOption.edges.addAll(allOptions.get(i).get(0).edges);
            ++i;
        }

        if(baseOption.assignment == null)
            return null;

        if(i > 0) {

            Assignment finalS = baseOption.assignment;

            allOptions = allOptions.subList(i, allOptions.size()).parallelStream()
                    .map(options -> options.stream()
                            .filter(o -> finalS.isCompatible(o.assignment))
                            .collect(toList()))
                    .collect(toList());

            for(List<Option> l: allOptions) {
                if(l.size() == 0)
                    return null;
            }

            return filterOptions(allOptions, baseOption);
        }

        return allOptions;
    }

    private List<Option> getCompatibleOptions(BuchiAutomata ba, Vertex v, Assignment assignment) {
        Set<Edge> edges = ba.outgoingEdgesOf(v);
        List<Option> validAssignments = new ArrayList<>();
        for(Edge e: edges) {
            List<Assignment> edgeAssignments = pathsBuilder.getEdgeAssignments(e);

            edgeAssignments = edgeAssignments.stream().filter(a -> assignment.isCompatible(a)).collect(toList());
            for(Assignment a: edgeAssignments)
                validAssignments.add(new Option(e, a));
        }

        return validAssignments;
    }


}

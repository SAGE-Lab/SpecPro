package it.sagelab.specpro.atg;

import it.sagelab.specpro.models.ba.Edge;
import it.sagelab.specpro.models.ba.Vertex;
import it.sagelab.specpro.models.ltl.Atom;
import it.sagelab.specpro.models.ltl.assign.Assignment;
import org.jgrapht.Graph;
import org.jgrapht.graph.DirectedPseudograph;
import org.jgrapht.io.DOTExporter;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class KISSParser {

    List<Atom> inputs;
    List<Atom> outputs;

    Graph<Vertex, Edge> graph;
    private Map<String, Vertex> vertexMap;
    Vertex resetState;
    private int edgeId = 0;


    public Graph<Vertex, Edge> parse(String filePath) throws FileNotFoundException {

        Scanner scanner = new Scanner(new File(filePath));

        graph = new DirectedPseudograph<Vertex, Edge>(Edge.class);
        vertexMap = new HashMap<>();

        while(scanner.hasNextLine()) {
            String line = scanner.nextLine();
            if(line.startsWith(".")) {
                parseOption(line);
            } else {
                parseEdge(line);
            }
        }

        return graph;
    }

    public void saveToFile(String outputFile) throws IOException {
        DOTExporter<Vertex, Edge> dotExporter = new DOTExporter<>(Vertex::getId, Vertex::getId, this::edgeLabel);
        FileWriter writer = new FileWriter(outputFile);
        dotExporter.exportGraph(graph, writer);
        writer.close();
    }

    private String edgeLabel(Edge e) {
        StringBuilder builder = new StringBuilder();
        Assignment a = e.getAssigments().iterator().next();

        for(Atom i: inputs) {
            if(a.contains(i)) {
                builder.append(a.getValue(i) ? '1' : '0');
            } else {
                builder.append('-');
            }
        }
        builder.append('/');

        for(Atom o: outputs) {
            if(a.contains(o)) {
                builder.append(a.getValue(o) ? '1' : '0');
            } else {
                builder.append('-');
            }
        }

        return builder.toString();
    }

    private void parseEdge(String line) {
        String [] args = line.split(" ");
        Assignment a = new Assignment();
        addAssignmentsValue(a, args[0], inputs);
        addAssignmentsValue(a, args[3], outputs);
        Vertex v1 = getVertex(args[1]);
        Vertex v2 = getVertex(args[2]);
        Edge edge = new Edge(v1, v2, Collections.singleton(a), ++edgeId);
        graph.addEdge(v1, v2, edge);
    }

    private void parseOption(String line) {
        String [] args = line.split(" ");
        if(args[0].equals(".inputs") || args[0].equals(".outputs")) {
            List<Atom> vars = new ArrayList<>();
            for(int i = 1; i < args.length; ++i) {
                vars.add(new Atom(args[i]));
            }
            if(args[0].equals(".inputs")) {
                inputs = vars;
            } else {
                outputs = vars;
            }
        }

        if(args[0].equals(".r")) {
            resetState = getVertex(args[1]);
        }

    }

    private void addAssignmentsValue(Assignment a, String values, List<Atom> atoms) {
        if(values.length() != atoms.size()) {
            throw new IllegalArgumentException("Values number mismatch with the number of variables defined in the header");
        }

        for(int i = 0; i< atoms.size(); ++i) {
            if(values.charAt(i) == '1') {
                a.add(atoms.get(i), true);
            } else if(values.charAt(i) == '0') {
                a.add(atoms.get(i), false);
            }
        }

    }

    private Vertex getVertex(String id) {
        Vertex v = vertexMap.get(id);
        if(v == null) {
            v = new Vertex(id);
            vertexMap.put(id, v);
            graph.addVertex(v);
        }
        return v;
    }



}

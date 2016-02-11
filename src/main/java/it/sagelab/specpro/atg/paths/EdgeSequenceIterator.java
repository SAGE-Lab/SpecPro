package it.sagelab.specpro.atg.paths;

import it.sagelab.specpro.models.ba.Edge;
import it.sagelab.specpro.models.ltl.assign.Assignment;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class EdgeSequenceIterator implements Iterator<List<Assignment>> {

    private final List<Edge> path;

    private final List<Iterator<Assignment>> iterators;
    private final ArrayList<Assignment> assignmentsList;

    public EdgeSequenceIterator(List<Edge> path) {
        this.path = path;
        iterators = new ArrayList<>();
        assignmentsList = new ArrayList<>();
        for(Edge e: path) {
            Iterator<Assignment> itr = e.getAssigments().iterator();
            if(itr.hasNext()) {
                iterators.add(itr);
            } else {
                iterators.clear();
                break;
            }
        }

    }

    @Override
    public boolean hasNext() {
        for(Iterator<Assignment> itr: iterators) {
            if (itr.hasNext())
                return true;
        }
        return false;
    }

    @Override
    public List<Assignment> next() {

        if(assignmentsList.isEmpty()) {
            for(Iterator<Assignment> itr: iterators) {
                assignmentsList.add(itr.next());
            }
        } else {

            int n = iterators.size() - 1;
            while (!iterators.get(n).hasNext()) {
                iterators.set(n, path.get(n).getAssigments().iterator());
                --n;
            }

            for (int i = n; i < iterators.size(); ++i) {
                assignmentsList.set(i, iterators.get(i).next());
            }
        }

        return new ArrayList<>(assignmentsList);
    }
}

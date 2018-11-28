package it.sagelab.specpro.models.ba.ac;

import it.sagelab.specpro.models.ba.Edge;
import it.sagelab.specpro.models.ba.Vertex;

import java.util.ArrayList;
import java.util.List;

public class LassoShapedAcceptanceCondition implements AcceptanceCondition {

    protected final int betaIndex;

    public LassoShapedAcceptanceCondition() {
        this.betaIndex = -1;
    }

    public LassoShapedAcceptanceCondition(int betaIndex) {
        this.betaIndex = betaIndex;
    }

    @Override
    public boolean accept(Edge[] path) {
        if(path == null)
            return false;

        Vertex lastVertex = path[path.length - 1].getTarget();

        int index = 0;
        if(betaIndex >= 0) {
            index = betaIndex;
            if(path[betaIndex].getSource() != lastVertex)
                return false;
        } else {
            while(index < path.length && path[index].getSource() != lastVertex) {
                ++index;
            }
        }

        return isValidIndex(path, index);
    }

    public static List<Integer> getValidBetaIndexes(List<Edge> path) {
        Vertex lastVertex = path.get(path.size() - 1).getTarget();

        ArrayList<Integer> betaIndexes = new ArrayList<>();

        for(int i = 0; i < path.size(); ++i) {
            if(path.get(i).getSource() == lastVertex) {
                if(isValidIndex(path, i))
                    betaIndexes.add(i);
            }
        }

        return betaIndexes;
    }

    private static boolean isValidIndex(Edge[] path, int index) {
        for(int i = index; i < path.length; ++i) {
            if(path[i].getSource().isAcceptingState()) {
                return true;
            }
        }
        return false;
    }

    private static boolean isValidIndex(List<Edge> path, int index) {
        for(int i = index; i < path.size(); ++i) {
            if(path.get(i).getSource().isAcceptingState()) {
                return true;
            }
        }
        return false;
    }

}

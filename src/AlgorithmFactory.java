import java.util.ArrayList;

public class AlgorithmFactory {

    public Algorithm getAlgorithm(String algorithmCode, Node startNode, String goalDigits, ArrayList<String> forbiddenDigits) {

        switch (algorithmCode) {
            case "B": return new BreadthFirstSearch(startNode, goalDigits, forbiddenDigits);
            case "D": return new DepthFirstSearch(startNode, goalDigits, forbiddenDigits);
            case "I": return new IterativeDeepeningSearch(startNode, goalDigits, forbiddenDigits);
            case "G": return new GreedySearch(startNode, goalDigits, forbiddenDigits);
            case "H": return new HillClimbingSearch(startNode, goalDigits, forbiddenDigits);
            case "A": return new AStarSearch(startNode, goalDigits, forbiddenDigits);
            default: return null;
        }

    }

}

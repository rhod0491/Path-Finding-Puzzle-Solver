import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;

public class HillClimbingSearch extends Algorithm {

    public HillClimbingSearch(Node startNode, String goalDigits, ArrayList<String> forbiddenDigits) {
        super(startNode, goalDigits, forbiddenDigits);
    }

    private Comparator<Node> comparator = (x, y) -> {

        int heuristicDifference = x.getHeuristic() - y.getHeuristic();

        if (heuristicDifference != 0) {
            return heuristicDifference;
        } else {
            return -(x.getNodeId() - y.getNodeId());
        }
    };

    @Override
    public void execute() {

        PriorityQueue<Node> fringeNodes = new PriorityQueue<>(comparator);

        int bestHeuristic = Integer.MAX_VALUE;
        startNode.calculateHeuristic(goalDigits);

        fringeNodes.add(startNode);

        while (!fringeNodes.isEmpty() && numExploredNodes <= 1000) {

            Node currentNode = fringeNodes.poll();

            if (forbiddenDigits.contains(currentNode.getDigits()) || currentNode.getHeuristic() >= bestHeuristic) {
                continue;
            } else if (currentNode.getDigits().equals(goalDigits)) {
                goalNode = currentNode;
                validExploredNodes.add(currentNode);
                break;
            }

            currentNode.generateChildren();
            currentNode.calculateChildrenHeuristic(goalDigits);

            if (!validExploredNodes.contains(currentNode)) {
                validExploredNodes.add(currentNode);
                numExploredNodes++;

                fringeNodes.clear();
                fringeNodes.addAll(currentNode.getChildren());

                bestHeuristic = currentNode.getHeuristic();
            }

        }

    }

}
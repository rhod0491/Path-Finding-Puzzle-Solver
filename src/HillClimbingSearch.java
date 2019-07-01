import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;

public class HillClimbingSearch extends Algorithm {

    public HillClimbingSearch(Node startNode, String goalDigits, ArrayList<String> forbiddenDigits) {
        super(startNode, goalDigits, forbiddenDigits);
    }

    private Comparator<Node> comparator = (x, y) -> {

        int manhattanDistanceDifference = x.getManhattanDistance() - y.getManhattanDistance();

        if (manhattanDistanceDifference != 0) {
            return manhattanDistanceDifference;
        } else {
            return -(x.getNodeId() - y.getNodeId());
        }
    };

    @Override
    public void execute() {

        PriorityQueue<Node> fringeNodes = new PriorityQueue<>(comparator);

        int bestManhattanDistance = Integer.MAX_VALUE;
        startNode.calculateHeuristic(goalDigits);

        fringeNodes.add(startNode);

        int numNodesExpanded = 0;
        while (!fringeNodes.isEmpty() && numNodesExpanded <= 1000) {

            Node currentNode = fringeNodes.poll();

            if (forbiddenDigits.contains(currentNode.getDigits()) || currentNode.getManhattanDistance() >= bestManhattanDistance) {
                continue;
            } else if (currentNode.getDigits().equals(goalDigits)) {
                goalNode = currentNode;
                expandedNodes.add(currentNode);
                break;
            }

            currentNode.generateChildren();
            currentNode.calculateChildrenHeuristic(goalDigits);

            if (!expandedNodes.contains(currentNode)) {
                expandedNodes.add(currentNode);
                numNodesExpanded++;

                fringeNodes.clear();
                fringeNodes.addAll(currentNode.getChildren());

                bestManhattanDistance = currentNode.getManhattanDistance();
            }

        }

    }

}
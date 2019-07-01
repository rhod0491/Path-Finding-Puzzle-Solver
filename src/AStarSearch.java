import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;

public class AStarSearch extends Algorithm {

    public AStarSearch(Node startNode, String goalDigits, ArrayList<String> forbiddenDigits) {
        super(startNode, goalDigits, forbiddenDigits);
    }

    private Comparator<Node> comparator = (x, y) -> {

        int xAStarHeuristic = x.getManhattanDistance() + x.getDistanceFromRoot();
        int yAStarHeuristic = y.getManhattanDistance() + y.getDistanceFromRoot();

        int AStarHeuristicDifference = xAStarHeuristic - yAStarHeuristic;

        if (AStarHeuristicDifference != 0) {
            return AStarHeuristicDifference;
        } else {
            return -(x.getNodeId() - y.getNodeId());
        }
    };

    @Override
    public void execute() {

        PriorityQueue<Node> fringeNodes = new PriorityQueue<>(comparator);
        fringeNodes.add(startNode);

        int numNodesExpanded = 0;
        while (!fringeNodes.isEmpty() && numNodesExpanded <= 1000) {

            Node currentNode = fringeNodes.poll();

            if (forbiddenDigits.contains(currentNode.getDigits())) {
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
                fringeNodes.addAll(currentNode.getChildren());
            }

        }

    }

}
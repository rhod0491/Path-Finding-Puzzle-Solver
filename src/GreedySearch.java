import java.util.*;

public class GreedySearch extends Algorithm {

    public GreedySearch(Node startNode, String goalDigits, ArrayList<String> forbiddenDigits) {
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
        fringeNodes.add(startNode);

        while (!fringeNodes.isEmpty() && numExploredNodes <= 1000) {

            Node currentNode = fringeNodes.poll();

            if (forbiddenDigits.contains(currentNode.getDigits())) {
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
                fringeNodes.addAll(currentNode.getChildren());
            }

        }

    }

}

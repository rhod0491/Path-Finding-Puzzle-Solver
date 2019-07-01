import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class BreadthFirstSearch extends Algorithm {

    public BreadthFirstSearch(Node startNode, String goalDigits, ArrayList<String> forbiddenDigits) {
        super(startNode, goalDigits, forbiddenDigits);
    }

    @Override
    public void execute() {

        Queue<Node> fringeNodes = new LinkedList<>();
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

            if (!expandedNodes.contains(currentNode)) {
                expandedNodes.add(currentNode);
                numNodesExpanded++;
                fringeNodes.addAll(currentNode.getChildren());
            }

        }

    }

}

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class DepthFirstSearch extends Algorithm {

    public DepthFirstSearch(Node startNode, String goalDigits, ArrayList<String> forbiddenDigits) {
        super(startNode, goalDigits, forbiddenDigits);
    }

    @Override
    public void execute() {

		Stack<Node> fringeNodes = new Stack<>();
		fringeNodes.push(startNode);

        int numNodesExpanded = 0;
		while (!fringeNodes.isEmpty() && numNodesExpanded <= 1000) {


            Node currentNode = fringeNodes.pop();

            if (forbiddenDigits.contains(currentNode.getDigits())) {
                continue;
            } else if (currentNode.getDigits().equals(goalDigits)) {
                goalNode = currentNode;
                expandedNodes.add(currentNode);
                break;
            }

            currentNode.generateChildren();
            currentNode.reverseChildren();

            if (!expandedNodes.contains(currentNode)) {
                expandedNodes.add(currentNode);
                numNodesExpanded++;
                fringeNodes.addAll(currentNode.getChildren());
            }

        }

    }

}

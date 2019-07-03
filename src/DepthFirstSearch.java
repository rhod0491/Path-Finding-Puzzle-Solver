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

		while (!fringeNodes.isEmpty() && numExploredNodes <= 1000) {

            Node currentNode = fringeNodes.pop();

            if (forbiddenDigits.contains(currentNode.getDigits())) {
                continue;
            } else if (currentNode.getDigits().equals(goalDigits)) {
                goalNode = currentNode;
                validExploredNodes.add(currentNode);
                break;
            }

            currentNode.generateChildren();
            currentNode.reverseChildren();

            if (!validExploredNodes.contains(currentNode)) {
                validExploredNodes.add(currentNode);
                numExploredNodes++;
                fringeNodes.addAll(currentNode.getChildren());
            }

        }

    }

}

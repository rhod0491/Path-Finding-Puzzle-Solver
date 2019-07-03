import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class IterativeDeepeningSearch extends Algorithm {

    public IterativeDeepeningSearch(Node startNode, String goalDigits, ArrayList<String> forbiddenDigits) {
        super(startNode, goalDigits, forbiddenDigits);
    }

    @Override
    public void execute() {

        for (int currentDepth = 0; currentDepth <= 1000; currentDepth++) {

            Stack<Node> fringeNodes = new Stack<>();
            fringeNodes.push(startNode);

            Queue<Node> expandedNodesCurrentDepth = new LinkedList<>();

            while (!fringeNodes.isEmpty() && numExploredNodes <= 1000) {

                Node currentNode = fringeNodes.pop();

                if (forbiddenDigits.contains(currentNode.getDigits()) || currentNode.getDistanceFromRoot() >= currentDepth) {
                    continue;
                } else if (currentNode.getDigits().equals(goalDigits)) {
                    goalNode = currentNode;
                    expandedNodesCurrentDepth.add(currentNode);
                    break;
                }

                currentNode.generateChildren();
                currentNode.reverseChildren();

                if (!expandedNodesCurrentDepth.contains(currentNode)) {
                    expandedNodesCurrentDepth.add(currentNode);
                    numExploredNodes++;
                    fringeNodes.addAll(currentNode.getChildren());
                }
            }

            validExploredNodes.addAll(expandedNodesCurrentDepth);

            if (goalNode != null) {
                break;
            }

        }

    }

}
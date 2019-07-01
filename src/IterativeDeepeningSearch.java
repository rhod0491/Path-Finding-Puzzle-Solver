import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class IterativeDeepeningSearch extends Algorithm {

    private int maxDepth;

    public IterativeDeepeningSearch(Node startNode, String goalDigits, ArrayList<String> forbiddenDigits, int maxDepth) {
        super(startNode, goalDigits, forbiddenDigits);
        this.maxDepth = maxDepth;
    }

    @Override
    public void execute() {

        int numNodesExpanded = 0;
        for (int currentDepth = 0; currentDepth <= maxDepth; currentDepth++) {

            Stack<Node> fringeNodes = new Stack<>();
            fringeNodes.push(startNode);

            Queue<Node> expandedNodesCurrentDepth = new LinkedList<>();

            while (!fringeNodes.isEmpty() && numNodesExpanded <= 1000) {

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
                    numNodesExpanded++;
                    fringeNodes.addAll(currentNode.getChildren());
                }
            }

            expandedNodes.addAll(expandedNodesCurrentDepth);

            if (goalNode != null) {
                break;
            }

        }

    }

}
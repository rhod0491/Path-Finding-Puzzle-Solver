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

            if (!validExploredNodes.contains(currentNode)) {
                validExploredNodes.add(currentNode);
                numExploredNodes++;
                fringeNodes.addAll(currentNode.getChildren());
            }

        }

    }

}

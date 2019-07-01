import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public abstract class Algorithm {

    protected Node startNode;
    protected Node goalNode;
    protected String goalDigits;
    protected ArrayList<String> forbiddenDigits;
    protected Queue<Node> expandedNodes;

    public Algorithm(Node startNode, String goalDigits, ArrayList<String> forbiddenDigits) {
        this.startNode = startNode;
        this.goalDigits = goalDigits;
        this.forbiddenDigits = forbiddenDigits;

        goalNode = null;
        expandedNodes = new LinkedList<>();
    }

    public abstract void execute();

    public void printShortestPath() {

        if (goalNode == null) {
            System.out.println("No solution found.");
            return;
        }

        Stack<String> shortestPath = new Stack<>();

        Node currNode = goalNode;
        while (currNode.getParent() != null) {
            shortestPath.push(currNode.getDigits());
            currNode = currNode.getParent();
        }

        shortestPath.push(currNode.getDigits());

        while (!shortestPath.isEmpty()) {
            String currentNode = shortestPath.pop();

            if (shortestPath.isEmpty()) {
                System.out.println(currentNode);
            } else {
                System.out.print(currentNode + ",");
            }
        }

    }

    public void printExploredNodes() {

        while (!expandedNodes.isEmpty()) {
            String currentNode = expandedNodes.poll().getDigits();

            if (expandedNodes.isEmpty()) {
                System.out.println(currentNode);
            } else {
                System.out.print(currentNode + ",");
            }

        }

    }

}

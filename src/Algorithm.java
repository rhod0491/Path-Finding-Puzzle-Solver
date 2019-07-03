import java.util.*;

public abstract class Algorithm {

    protected Node startNode;
    protected Node goalNode = null;
    protected String goalDigits;
    protected ArrayList<String> forbiddenDigits;
    protected Queue<Node> validExploredNodes = new LinkedList<>();
    protected int numExploredNodes = 1;

    public Algorithm(Node startNode, String goalDigits, ArrayList<String> forbiddenDigits) {
        this.startNode = startNode;
        this.goalDigits = goalDigits;
        this.forbiddenDigits = forbiddenDigits;
    }

    public abstract void execute();

    public void printShortestPath() {

        if (goalNode == null) {
            System.out.println("No solution found.");
            return;
        }

        ArrayList<String> shortestPathDigits = new ArrayList<>(goalNode.getDistanceFromRoot() + 1);

        Node currentNode = goalNode;
        while (currentNode.getParent() != null) {
            shortestPathDigits.add(currentNode.getDigits());
            currentNode = currentNode.getParent();
        }
        shortestPathDigits.add(currentNode.getDigits());

        Collections.reverse(shortestPathDigits);

        String shortestPathString = String.join(",", shortestPathDigits);
        System.out.println("Shortest path:\t" + shortestPathString);

    }

    public void printExploredNodes() {

        ArrayList<String> exploredNodeDigits = new ArrayList<>(validExploredNodes.size());

        for (Node node : validExploredNodes) {
            exploredNodeDigits.add(node.getDigits());
        }

        String exploredNodesString = String.join(",", exploredNodeDigits);
        System.out.println("Explored nodes:\t" + exploredNodesString);

    }

}

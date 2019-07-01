import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.LinkedHashSet;
import java.util.Objects;

public class Node {

	private static int idCounter = 0;
	
	private int nodeId;
	private Node parent;
	private String digits;
	private List<Node> children;
	private int manhattanDistance;
	private int distanceFromRoot;

	public Node(String digits, Node parent) {

		++idCounter;
		
		nodeId = idCounter;
		this.parent = parent;
		this.digits = digits;
		children = new ArrayList<>();
		manhattanDistance = Integer.MAX_VALUE;

        distanceFromRoot = (this.parent == null) ? 0 : this.parent.getDistanceFromRoot() + 1;
	}
	
	public int getNodeId() {
		return nodeId;
	}
	
	public Node getParent() { return parent; }

	public String getDigits() {
		return digits;
	}
	
	public List<Node> getChildren() {
		return children;
	}
	
	public int getManhattanDistance() {
		return manhattanDistance;
	}

	public int getDistanceFromRoot() {
		return distanceFromRoot;
	}
	
	public void calculateHeuristic(String goal) {
		
		int goalHundreds = goal.charAt(0) - '0';
		int goalTens = goal.charAt(1) - '0';
		int goalOnes = goal.charAt(2) - '0';
		
		int currHundreds = digits.charAt(0) - '0';
		int currTens = digits.charAt(1) - '0';
		int currOnes = digits.charAt(2) - '0';
		
		manhattanDistance = Math.abs(goalHundreds - currHundreds) + Math.abs(goalTens - currTens) + Math.abs(goalOnes - currOnes);
	}

	public void calculateChildrenHeuristic(String goal) {
		for(Node node : children) {
			node.calculateHeuristic(goal);
		}
	}
	
	public void reverseChildren() {
		Collections.reverse(children);
	}

	public void generateChildren() {

        children.clear();

        Set<String> childDigits = new LinkedHashSet<>();

		for (int i = 0; i < digits.length(); i++) {

            String decrementedNodeDigit = decrementDigit(i);
            childDigits.add(decrementedNodeDigit);

            String incrementedNodeDigit = incrementDigit(i);
            childDigits.add(incrementedNodeDigit);

        }

        childDigits.remove(digits);

		for (String child : childDigits) {
            children.add(new Node(child, this));
        }

	}

	private String incrementDigit(int index) {

        char digit = digits.charAt(index);

        boolean prevDigitShifted = (parent != null && parent.getDigits().charAt(index) != digit);
        if (digit != '9' && !prevDigitShifted) {
            return digits.substring(0, index) + (digit += 1) + digits.substring(index + 1);
        }

        return digits;

    }

    private String decrementDigit(int index) {

        char digit = digits.charAt(index);

        boolean prevDigitShifted = (parent != null && parent.getDigits().charAt(index) != digit);
        if (digit != '0' && !prevDigitShifted) {
            return digits.substring(0, index) + (digit -= 1) + digits.substring(index + 1);
        }

        return digits;

    }

	@Override
	public String toString() {

        List<String> childrenList = new ArrayList<>(children.size());
        for (Node child : children) {
            childrenList.add(child.getDigits());
        }

        String childrenDigits = String.join(",", childrenList);

		return "Node{" +
				"nodeId=" + nodeId +
				", parent=" + parent +
				", digits='" + digits + '\'' +
                ", children=[" + childrenDigits + "]" +
				", manhattanDistance=" + manhattanDistance +
				", distanceFromRoot=" + distanceFromRoot +
				'}';
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Node node = (Node) o;
		return digits.equals(node.digits) && Objects.equals(children, node.children);
	}

	@Override
	public int hashCode() {
		return Objects.hash(digits, children);
	}

}

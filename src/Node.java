import java.lang.reflect.Array;
import java.util.*;

public class Node {

	private static int idCounter = 0;

	private int nodeId = ++idCounter;
	private Node parent;
	private String digits;
	private int distanceFromRoot;
	private List<Node> children = new ArrayList<>();
	private int heuristic = Integer.MAX_VALUE;

	public Node(String digits, Node parent) {
		this.parent = parent;
		this.digits = digits;

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
	
	public int getHeuristic() {
		return heuristic;
	}

	public int getDistanceFromRoot() {
		return distanceFromRoot;
	}

	public void calculateHeuristic(String goal) {

		if (digits.length() == 0 || digits.length() != goal.length()) {
			return;
		}

		heuristic = 0;

		for (int i = 0; i < digits.length(); i++) {

			int nodeDigit = Character.getNumericValue(digits.charAt(i));
			int goalDigit = Character.getNumericValue(goal.charAt(i));

			heuristic += Math.abs(nodeDigit - goalDigit);

		}

	}

	public void calculateChildrenHeuristic(String goal) {
		for(Node child : children) {
			child.calculateHeuristic(goal);
		}
	}
	
	public void reverseChildren() {
		Collections.reverse(children);
	}

	public void generateChildren() {

        children.clear();

        Set<String> childDigits = new LinkedHashSet<>();

		for (int i = 0; i < digits.length(); i++) {

            String decrementedNodeDigits = decrementDigit(i);
            childDigits.add(decrementedNodeDigits);

            String incrementedNodeDigits = incrementDigit(i);
            childDigits.add(incrementedNodeDigits);

        }

        childDigits.remove(digits);

		for (String child : childDigits) {
            children.add(new Node(child, this));
        }

	}

	private String decrementDigit(int index) {

		char digit = digits.charAt(index);

		boolean prevDigitShifted = (parent != null && parent.getDigits().charAt(index) != digit);
		if (digit != '0' && !prevDigitShifted) {
			return digits.substring(0, index) + (digit -= 1) + digits.substring(index + 1);
		}

		return digits;

	}

	private String incrementDigit(int index) {

        char digit = digits.charAt(index);

        boolean prevDigitShifted = (parent != null && parent.getDigits().charAt(index) != digit);
        if (digit != '9' && !prevDigitShifted) {
            return digits.substring(0, index) + (digit += 1) + digits.substring(index + 1);
        }

        return digits;

    }

    public List<String> getChildDigits() {

		List<String> childDigits = new ArrayList<>(children.size());

		for (Node child : children) {
			childDigits.add(child.getDigits());
		}

		return childDigits;

	}

	@Override
	public String toString() {

		String parentDigits = "null";
		if (parent != null) {
			parentDigits = parent.getDigits();
		}

        List<String> childrenList = new ArrayList<>(children.size());
        for (Node child : children) {
            childrenList.add(child.getDigits());
        }

        String childrenDigits = String.join(",", childrenList);

		return "Node{" +
				"nodeId=" + nodeId +
				", parent=" + parentDigits +
				", digits='" + digits + '\'' +
                ", children=[" + childrenDigits + "]" +
				", heuristic=" + heuristic +
				", distanceFromRoot=" + distanceFromRoot +
				'}';
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Node node = (Node) o;
		return digits.equals(node.digits) && Objects.equals(getChildDigits(), node.getChildDigits());
	}

	@Override
	public int hashCode() {
		return Objects.hash(digits, children);
	}

}

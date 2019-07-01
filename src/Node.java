import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

public class Node {

	private static int idCounter = 0;
	
	private int nodeId;
	private Node parent;
	private String digits;
	private List<Node> children;
	private int manhattanDistance;
	private int distanceFromRoot;

	public Node(String digits, Node parent) {
		idCounter++;
		
		this.nodeId = idCounter;
		this.parent = parent;
		this.digits = digits;
		this.children = new ArrayList<>();
		this.manhattanDistance = Integer.MAX_VALUE;
		
		if(this.parent == null) {
			this.distanceFromRoot = 0;
		} else {
			this.distanceFromRoot = this.getParent().getDistanceFromRoot() + 1;
		}
	}
	
	public int getNodeId() {
		return this.nodeId;
	}
	
	public Node getParent() {
		return this.parent;
	}

	public String getDigits() {
		return this.digits;
	}
	
	public List<Node> getChildren() {
		return this.children;
	}
	
	public int getManhattanDistance() {
		return this.manhattanDistance;
	}

	public int getDistanceFromRoot() {
		return this.distanceFromRoot;
	}
	
	public void calculateHeuristic(String goal) {
		
		int goalHundreds = goal.charAt(0) - '0';
		int goalTens = goal.charAt(1) - '0';
		int goalOnes = goal.charAt(2) - '0';
		
		int currHundreds = this.digits.charAt(0) - '0';
		int currTens = this.digits.charAt(1) - '0';
		int currOnes = this.digits.charAt(2) - '0';
		
		this.manhattanDistance = Math.abs(goalHundreds - currHundreds) + Math.abs(goalTens - currTens) + Math.abs(goalOnes - currOnes);
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
		Set<Node> children = new LinkedHashSet<>();

		String childDigits = "";
		
		childDigits = this.decrementHundreds();
		if (!childDigits.equals(digits)) {
			children.add(new Node(childDigits, this));
		}

		childDigits = this.incrementHundreds();
		if (!childDigits.equals(digits)) {
			children.add(new Node(childDigits, this));
		}

		childDigits = this.decrementTens();
		if (!childDigits.equals(digits)) {
			children.add(new Node(childDigits, this));
		}

		childDigits = this.incrementTens();
		if (!childDigits.equals(digits)) {
			children.add(new Node(childDigits, this));
		}

		childDigits = this.decrementOnes();
		if (!childDigits.equals(digits)) {
			children.add(new Node(childDigits, this));
		}

		childDigits = this.incrementOnes();
		if (!childDigits.equals(digits)) {
			children.add(new Node(childDigits, this));
		}

		this.children.clear();
		this.children.addAll(children);
	}

	public String incrementOnes() {
		char digit = this.digits.charAt(2);

		boolean prevDigitShifted = (parent != null && parent.getDigits().charAt(2) != digit);
		if (digit != '9' && !prevDigitShifted) {
			return digits.substring(0, 2) + (digit += 1);
		}

		return digits;
	}

	public String decrementOnes() {
		char digit = this.digits.charAt(2);

		boolean prevDigitShifted = (parent != null && parent.getDigits().charAt(2) != digit);
		if (digit != '0' && !prevDigitShifted) {
			return digits.substring(0, 2) + (digit -= 1);
		}

		return digits;
	}

	public String incrementTens() {
		char digit = this.digits.charAt(1);

		boolean prevDigitShifted = (parent != null && parent.getDigits().charAt(1) != digit);
		if (digit != '9' && !prevDigitShifted) {
			return digits.substring(0, 1) + (digit += 1) + digits.substring(2, 3);
		}

		return digits;
	}

	public String decrementTens() {
		char digit = this.digits.charAt(1);

		boolean prevDigitShifted = (parent != null && parent.getDigits().charAt(1) != digit);
		if (digit != '0' && !prevDigitShifted) {
			return digits.substring(0, 1) + (digit -= 1) + digits.substring(2, 3);
		}

		return digits;
	}

	public String incrementHundreds() {
		char digit = this.digits.charAt(0);

		boolean prevDigitShifted = (parent != null && parent.getDigits().charAt(0) != digit);
		if (digit != '9' && !prevDigitShifted) {
			return (digit += 1) + digits.substring(1, 3);
		}

		return digits;
	}

	public String decrementHundreds() {
		char digit = this.digits.charAt(0);

		boolean prevDigitShifted = (parent != null && parent.getDigits().charAt(0) != digit);
		if (digit != '0' && !prevDigitShifted) {
			return (digit -= 1) + digits.substring(1, 3);
		}

		return digits;
	}

	@Override
	public String toString() {

		StringBuilder message = new StringBuilder();
		
		message.append("Node ID: " + nodeId + "\n");

		if (parent != null) {
			message.append("Parent: " + parent.getDigits() + "\n");
		} else {
			message.append("Parent: null\n");
		}

		message.append("Digits: " + digits + "\n");

		if (children != null) {
			message.append("Children: ");
			for (Node child : children) {
				message.append(child.getDigits() + " ");
			}
			message.append("\n");
		}

		message.append("Manhattan distance: " + this.manhattanDistance);
		message.append("Distance from root: " + distanceFromRoot + "\n");
		
		return message.toString();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof Node))
			return false;
		Node other = (Node) obj;
		if (children == null) {
			if (other.children != null)
				return false;
		}

		List<String> childNodes1 = new ArrayList<>();
		for (Node child : children) {
			childNodes1.add(child.getDigits());
		}

		List<String> childNodes2 = new ArrayList<>();
		for (Node child : other.children) {
			childNodes2.add(child.getDigits());
		}

		if (!childNodes1.equals(childNodes2))
			return false;
		if (digits == null) {
			if (other.digits != null)
				return false;
		} else if (!digits.equals(other.digits))
			return false;
		return true;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((children == null) ? 0 : children.hashCode());
		result = prime * result + ((digits == null) ? 0 : digits.hashCode());
		return result;
	}

}

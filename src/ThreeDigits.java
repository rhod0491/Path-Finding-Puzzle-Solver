import java.util.ArrayList;
import java.io.FileNotFoundException;
import java.util.NoSuchElementException;
import java.io.FileReader;
import java.util.Scanner;

public class ThreeDigits {

	public static void main(String[] args) {

		if (args.length != 2) {
			System.out.println("Please provide an algorithm code and an input file");
			System.exit(1);
		}

		String algorithmCode = args[0];
		String inputFile = args[1];

		Node startNode = null;
		String goalDigits = "";
		ArrayList<String> forbiddenNumbers = new ArrayList<>();

		try (Scanner scanner = new Scanner(new FileReader(inputFile))) {
			startNode = new Node(scanner.next(), null);
			goalDigits = scanner.next();

			scanner.useDelimiter("\\D+");

			while (scanner.hasNextInt()) {
				forbiddenNumbers.add(scanner.next());
			}
		} catch (FileNotFoundException e) {
			System.out.println("The file " + inputFile + " could not be opened");
			System.exit(1);
		} catch (NoSuchElementException e) {
			System.out.println("The file must contain at least a start node and an end node");
			System.exit(1);
		}

		AlgorithmFactory factory = new AlgorithmFactory();

		Algorithm algorithm = factory.getAlgorithm(algorithmCode, startNode, goalDigits, forbiddenNumbers);

		if (algorithm == null) {
			System.out.println("Invalid algorithm code, valid codes include: B for BFS, D for DFS, I for IDS, " +
					"G for Greedy, A for A*, H for Hill‐climbing.");
			System.exit(1);
		} else {
			algorithm.execute();
			algorithm.printShortestPath();
			algorithm.printExploredNodes();
		}

	}

}
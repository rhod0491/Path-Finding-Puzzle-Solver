# Search Algorithms

This application allows you to see the efficiencies and strategies used
by various searching algorithms by completing a 3-digit puzzle.

## The Puzzle

Given are two 3‐digit numbers called S (start) and  G (goal) and also a set of 3‐digit numbers called 
forbidden. To solve the puzzle, we want to get from S to G in the smallest number of moves. A move
is a transformation of one number into another number by adding or subtracting 1 to one of its digits.
For example, a move can take you from 123 to 124 by adding 1 to the last digit or from 953 to 853 by
subtracting 1 from the first digit. Moves must satisfy the following constraints:

1. You cannot add to the digit 9 or subtract from the digit 0;
2. You cannot make a move that transforms the current number into one of the forbidden
numbers;
3. You cannot change the same digit twice in two successive moves.

## Algorithms

### Uninformed Search

* [Breadth-first search](https://en.wikipedia.org/wiki/Breadth-first_search) 
* [Depth-first search](https://en.wikipedia.org/wiki/Depth-first_search) 
* [Iterative deepening search](https://en.wikipedia.org/wiki/Iterative_deepening_depth-first_search)

### Informed Search

* [Greedy search](https://en.wikipedia.org/wiki/Greedy_algorithm)
* [Hill climbing search](https://en.wikipedia.org/wiki/Hill_climbing)
* [A* search](https://en.wikipedia.org/wiki/A*_search_algorithm)

Note: the [Manhattan distance](http://artis.imag.fr/~Xavier.Decoret/resources/maths/manhattan/html/)
is used as the heuristic for the informed search algorithms. The Manhattan heuristic for 
a move between two numbers A and B is the sum of the absolute differences of the 
corresponding digits of these numbers, e.g. h(123, 492) = |1 - 4| + |2 - 9| + |3 - 2| = 11

## Usage

The application can be run from the command line and with the following arguments:

1. A single letter representing the algorithm to search with, out of B for BFS, D for DFS, I for IDS,
   G for Greedy, H for Hill‐climbing, A for A*.
2. A filename of a file to open for the search details. This file will contain the following format:

    ```
    start‐state
    goal‐state
    forbidden1,forbidden2,forbidden3,…,forbiddenN (optional)
    ```

    For example:
    
    ```
    345
    555
    355,445,554
    ```
    
    means that the search algorithm will start at state 345, and the goal is state 555. The
    search may not pass through any of 355, 445 or 554. Remember that the last line may not be
    present; i.e. there are no forbidden values.

## Example

We will use a file called *sample.txt* which contains the following:

```
320
110
```

An example of each command and the respective output is shown below:

![PuzzleSolver Example](https://i.imgur.com/5t5NsAb.png)

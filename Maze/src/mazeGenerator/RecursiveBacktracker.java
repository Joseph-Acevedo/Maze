package mazeGenerator;

import java.util.ArrayList;
import java.util.Stack;

import utilities.ListUtilities;

public class RecursiveBacktracker extends MazeGenerator {
	
	private ArrayList<MazeCell> unvisited;
	private Stack<MazeCell> stack;
	private MazeCell current;
	
	public void createMaze() {
		unvisited = (ArrayList<MazeCell>) cells.clone();
		stack = new Stack<MazeCell>();
		
		ListUtilities.copyArrayList(cells, unvisited);
		
		current = cells.get(START_POS.y * MAZE_WIDTH + START_POS.x);
		current.setVisited(true);
		
		while ( !unvisited.isEmpty() ) {
			MazeCell[] rawUnvisitedNeighbors = current.getUnvisitedNeighbors( cells.toArray( new MazeCell[cells.size()] ), MAZE_WIDTH, MAZE_HEIGHT );
			int numUnvisited = unvisitedNeighbors(rawUnvisitedNeighbors);
			if ( numUnvisited != 0) {	// if we have unvisited neighbors
				int direction = chooseRandomNeighbor(rawUnvisitedNeighbors, numUnvisited);
				MazeCell nextCell = removeCellWallFromDirection(current, direction);
				stack.push(current);
				current = nextCell;
				current.setVisited(true);
			} else {
				if ( !stack.empty() ) {
					current = stack.pop();
				}
			}
		}
		
	}
	
	/**
	 * Counts the number of unvisited neighbors given from the MazeCell. This is to make the
	 * algorithm less confusing and is a helper function
	 * @param unviNeigh The raw list given from the MazeCell that has the info about the neighbors
	 * @return The number o unvisited neighbors
	 */
	private int unvisitedNeighbors(MazeCell[] unviNeigh) {
		int numUnvisited = 0;
		for (int i = 0; i < unviNeigh.length; i++) {
			if ( unviNeigh[i] != null) {
				numUnvisited++;
			}
		}
		return numUnvisited;
	}

	/**
	 * Chooses a random unvisited neighbor from the given list and returns the direction of the neighbor. 
	 * This assumes that there are unvisited neighbors in the given list
	 * @param neighbors A list of neighbors to randomly choose from
	 * @param numUnvisited The number of unvisited neighbors in the given list. Must be [1,4]
	 * @return An int [0,3] that tells which direction the neighbor is in, [N E S W]
	 */
	private int chooseRandomNeighbor(MazeCell[] neighbors, int numUnvisited) {
		int randNeigh = 0, currNeigh = 0;
		
		if (numUnvisited != 1) 
			randNeigh = (int) (Math.random() * numUnvisited);
		
		for (int i = 0; i < neighbors.length; i++) {
			if (neighbors[i] != null) {
				if (currNeigh == randNeigh) {
					return i;
				} else {
					currNeigh++;
				}
			}
		}
		return -1; // Shouldn't reach this point. Key word: Shouldn't. Hopefully
	}
	
	private MazeCell removeCellWallFromDirection(MazeCell curr, int dir) {
		MazeCell next;
		switch (dir) {
			case 0: // N (x, y-1)
				next = cells.get( (curr.getLocation().y - 1) * MAZE_WIDTH + curr.getLocation().x);
				curr.setNorth(next);
				next.setSouth(curr);
				return  next;
			case 1: // E (x+1, y)
				next = cells.get( (curr.getLocation().y)* MAZE_WIDTH + curr.getLocation().x + 1);;
				curr.setEast(next);
				next.setWest(curr);
				return next;
			case 2: // S (x, y+1)
				next = cells.get( (curr.getLocation().y + 1) * MAZE_WIDTH + curr.getLocation().x);
				curr.setSouth(next);
				next.setNorth(curr);
				return next;
			case 3: // W (x-1, y)
				next = cells.get( (curr.getLocation().y)* MAZE_WIDTH + curr.getLocation().x - 1);
				curr.setWest(next);
				next.setEast(curr);
				return next;
			default: // Undefined
				throw new IllegalArgumentException("Direction must be 0, 1, 2 or 3");
		}
	}
	
	
	public static void main(String[] args) {
		RecursiveBacktracker rb = new RecursiveBacktracker();
		rb.createMaze();
	}
}

package mazeGenerator;

import java.awt.Point;

public class MazeCell {
	
	/*      N
	 *     ---
	 *   W | | E
	 *     ---
	 *      S
	 */
	
	private MazeCell north, south, east, west;
	private Point location;	// Refers to top left corner of component
	private boolean visited = false;
	private long id;
	
	public MazeCell(int x, int y, long id) {
		location = new Point(x, y);
		this.id = id;
		
		north = null;
		south = null;
		east = null;
		west = null;
	}
	
	
	public Point getLocation() {
		return location;
	}
	
	public boolean getVisited() {
		return visited;
	}
	
	public MazeCell getNorth() {
		return north;
	}
	
	public MazeCell getSouth() {
		return south;
	}
	
	public MazeCell getEast() {
		return east;
	}
	
	public MazeCell getWest() {
		return west;
	}
	
	public MazeCell[] getUnvisitedNeighbors(MazeCell[] cells, int cellsWidth, int cellsHeight) {
		MazeCell[] unvisited = new MazeCell[4]; // null if visited, neighbor if unvisited, [N, E, S , W]
		
		
		if ( location.y != 0 && !cells[ (location.y -  1) * cellsWidth + location.x].getVisited() ) {
			unvisited[0] = cells[ (location.y - 1) * cellsWidth + location.x];
		}
		if ( location.x + 1 != cellsWidth && !cells[ location.y * cellsWidth + location.x + 1].getVisited() ) {
			unvisited[1] = cells[location.y * cellsWidth + location.x + 1];
		}
		if ( location.y + 1 != cellsHeight && !cells[ (location.y + 1) * cellsWidth + location.x].getVisited() ) {
			unvisited[2] = cells[ (location.y + 1) * cellsWidth + location.x];
		}
		if ( location.x != 0 && !cells[ location.y * cellsWidth + location.x - 1].getVisited() ) {
			unvisited[3] = cells[ location.y * cellsWidth + location.x - 1];
		}
		
		
		return unvisited;
	}
	
	public long getId() {
		return id;
	}
	
	
	public void setVisited(boolean visit) {
		visited = visit;
	}
	
	public void setNorth(MazeCell n) {
		north = n;
	}
	
    public void setSouth(MazeCell s) {
		south = s;
	}
    
    public void setEast(MazeCell e) {
		east = e;
	}
    
    public void setWest(MazeCell w) {
    	west = w;
	}
}

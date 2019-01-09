/**
 * @author Joseph Acevedo
 * @since 31 December 2018
 */

package mazeGenerator;

/* ===== IMPORTS ===== */
import javax.swing.JPanel;
import javax.swing.JFrame;
import javax.swing.Timer;

import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.util.ArrayList;


public class MazeGenerator extends JPanel {
	
	/*
	 * A super-class for creating randomized mazes. The sub-classes will 
	 * extend this class and implement different maze generating algorithms
	 */
	
	/* ===== GRAPHICS CONSTANTS ===== */
	protected static final long serialVersionUID = 1L;
	
	protected static final int WINDOW_WIDTH  = 1000;
	protected static final int WINDOW_HEIGHT = 1000;
	protected static final int MAZE_WIDTH  = 3;
	protected static final int MAZE_HEIGHT = 3;
	protected static final int WALL_WIDTH = 2;
	
	protected static final Point START_POS = new Point(0, 0);

	
	/* ===== GLOBAL VARIABLES ===== */
	protected JFrame frame;
	protected Timer visualize;
	
	protected ArrayList<MazeCell> cells;
	
	protected int cellWidth, cellHeight;
	
	protected boolean mazeDone = false;
	
	
	public MazeGenerator() {
		cellWidth  = (int) (WINDOW_WIDTH  / MAZE_WIDTH );
		cellHeight = (int) (WINDOW_HEIGHT / MAZE_HEIGHT);
		
		cells = new ArrayList<MazeCell>(MAZE_WIDTH * MAZE_HEIGHT);
		
		//timer = new Timer(cellHeight, null);
		
		createComponentGrid();
		
		startWindow("Maze Generator");
		
	}
	
	protected void startWindow(String title) {
		frame = new JFrame(title);
		
		setPreferredSize( new Dimension( WINDOW_WIDTH, WINDOW_HEIGHT));
		setBackground(Color.WHITE);
		
		frame.setLayout( new BorderLayout());
		frame.add(this, BorderLayout.CENTER);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.pack();
		frame.setResizable(false);
		frame.setVisible(true);
		
	}
	
	/**
	 * Creates a grid of MazeComponents that are initialized with no connections
	 */
	protected void createComponentGrid() {
		long currId = 0L;
		
		for (int y = 0; y < MAZE_WIDTH; y++) {
			for (int x = 0; x < MAZE_HEIGHT; x++) {
				cells.add(new MazeCell(x, y, currId++));
			}
		}
	}
	
	public void paintComponent(Graphics g) {
		
		super.paintComponent(g);
	
		g.setColor(Color.BLACK);
		drawMaze(g);
	}
	
	protected void drawMaze(Graphics g) {
		//System.out.printf("<%d> \t\tdrawMaze\n", System.currentTimeMillis() % 100000);

		Graphics2D g2 = (Graphics2D) g;
		g2.setStroke(new BasicStroke(WALL_WIDTH));
		
		for (MazeCell mc: cells) {
			Point loc = mc.getLocation();
			
			
			if (!mazeDone) {
				if (mc.current) {
					System.out.println("Green");
					g2.setColor(Color.GREEN);
					g2.fillRect(loc.x * cellWidth, loc.y * cellHeight, cellWidth, cellHeight);
					g2.setColor(Color.BLACK);
				}
				else if (mc.getVisited()) {
				
					g2.setColor(Color.MAGENTA);
					g2.fillRect(loc.x * cellWidth, loc.y * cellHeight, cellWidth, cellHeight);
					g2.setColor(Color.BLACK);
					
					
					if (mc.getEast() == null) {
						g2.drawLine( (loc.x * cellWidth) + cellWidth, (loc.y * cellHeight), 
									 (loc.x * cellWidth) + cellWidth, (loc.y * cellHeight) + cellHeight);
					}
					if (mc.getWest() == null) {
						g2.drawLine( (loc.x * cellWidth), (loc.y * cellHeight), 
									 (loc.x * cellWidth), (loc.y * cellHeight) + cellHeight);
					}
					if (mc.getNorth() == null) {
						g2.drawLine( (loc.x * cellWidth), (loc.y * cellHeight), 
									 (loc.x * cellWidth) + cellWidth, (loc.y * cellHeight) );
					}
					if (mc.getSouth() == null) {
						g2.drawLine( (loc.x * cellWidth), (loc.y * cellHeight) + cellHeight, 
									 (loc.x * cellWidth) + cellWidth, (loc.y * cellHeight) + cellHeight);
					}
				}
				/*
				g2.drawString( Long.toString( mc.getId() ) , loc.x * cellWidth + cellWidth / 2, loc.y * cellHeight + cellHeight / 2);
				*/
				
			} else {
				
				g2.setColor(Color.WHITE);
				g2.fillRect(loc.x * cellWidth, loc.y * cellHeight, cellWidth, cellHeight);
				g2.setColor(Color.BLACK);
				
				if (mc.getEast() == null) {
					g2.drawLine( (loc.x * cellWidth) + cellWidth, (loc.y * cellHeight), 
								 (loc.x * cellWidth) + cellWidth, (loc.y * cellHeight) + cellHeight);
				}
				if (mc.getWest() == null) {
					g2.drawLine( (loc.x * cellWidth), (loc.y * cellHeight), 
								 (loc.x * cellWidth), (loc.y * cellHeight) + cellHeight);
				}
				if (mc.getNorth() == null) {
					g2.drawLine( (loc.x * cellWidth), (loc.y * cellHeight), 
								 (loc.x * cellWidth) + cellWidth, (loc.y * cellHeight) );
				}
				if (mc.getSouth() == null) {
					g2.drawLine( (loc.x * cellWidth), (loc.y * cellHeight) + cellHeight, 
								 (loc.x * cellWidth) + cellWidth, (loc.y * cellHeight) + cellHeight);
				}
			}
				
				
			}
		
	}

}

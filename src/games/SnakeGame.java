package games;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import frc.team4015.OI;

public class SnakeGame {
	
	class Coordinate {
		final int X;
		final int Y;
		Coordinate(int x, int y) {
			X = x;
			Y = y;
		}	
	}
	
	class GameBoard {
		class Cell {
			int lifeTime = 0;
			Coordinate location;
			private void decay() {
				if (lifeTime > 0) {
					lifeTime--;
				}
			}
			void activate(int lifeTime) {
				this.lifeTime = lifeTime;
			}
			Cell(int x, int y) {
				location = new Coordinate(x, y);
			}
		}
		Cell[][] board = new Cell[11][11];
		
		boolean activate(int x, int y, int lifetime) throws ArrayIndexOutOfBoundsException {
			boolean out = board[x][y].lifeTime > 0;
			board[x][y].activate(lifetime);
			return out;
		}
		void tick() {
			for (int x = 0; x < 11; x++) {
				for (int y = 0; y < 11; y++) {
					board[x][y].decay();
				}
			}
		}
		List<Coordinate> getOpenSpaces() {
			List<Coordinate> coords = new ArrayList<>();
			for (Cell[] column : board) {
				for (Cell cell : column) {
					if (cell.lifeTime == 0) {
						coords.add(cell.location);
					}
				}
			}
			return coords;
		}
		boolean[][] getActive() {
			boolean[][] coords = new boolean[11][11];
			int y = 0;
			for (Cell[] column : board) {
				int x = 0;
				for (Cell cell : column) {
					if (cell.lifeTime > 0) {
						coords[x][y] = true;
					}
					else {
						coords[x][y] = false;
					}
				}
			}
			return coords;
		}
		
		GameBoard() {
			for (int x = 0; x < 11; x++) {
				for (int y = 0; y < 11; y++) {
					board[x][y] = new Cell(x,y);
				}
			}
		}
	}
	
	private int snake_length = 1;
	private long lastAccept = 0;
	private boolean alive = true;
	
	private Coordinate head;
	private Coordinate food;
	
	private GameBoard board = new GameBoard();
	private Random random = new Random();
	private SnakeDisplay display;
	
	public SnakeGame() {
		display = new SnakeDisplay();
	}
	
	public void update() {
		
		if (System.currentTimeMillis() > lastAccept + 500 && alive) {
			lastAccept = System.currentTimeMillis();
			Coordinate target;
			double deg = 0;//OI.JoyStickRight.getDirectionDegrees();
			if (deg < 45 || deg > 315) {
				target = new Coordinate(head.X + 1, head.Y);
			}
			if (deg > 45 && deg < 135) {
				target = new Coordinate(head.X, head.Y + 1);
			}
			if (deg > 135 && deg < 225) {
				target = new Coordinate(head.X - 1, head.Y);
			}
			else {
				target = new Coordinate(head.X, head.Y - 1);
			}
			boolean foodImmunity = false;
			if (target.X == food.X && target.Y == food.Y) {
				snake_length++;
				foodImmunity = true;
				List<Coordinate> open = board.getOpenSpaces();
				if (open.isEmpty()) {
					alive = false; // You win!
				}
				else {
					food = open.get(random.nextInt(open.size()));
				}
			}
			head = target;
			board.tick();
			try {
				if (board.activate(head.X, head.Y, snake_length)) {
					alive = false || foodImmunity;
				}
			} catch (ArrayIndexOutOfBoundsException e) {
				alive = false;
			}
			
			display.displayStuff(board.getActive(), food.X, food.Y);
		}
		
	}
	
}

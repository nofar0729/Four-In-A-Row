package Game;

public class Board {
	private int height;
	private int width;
	private char[][] board;
	
	public Board(int height, int width) {
		this.height = height;
		this.width = width;
		this.board = new char[height][width];
		initializeBoard();
	}
	
	public int Width() {
		return width;
	}
	
	public int Height() {
		return height;
	}
	
	public void initializeBoard() {
		for(int i = 0; i < height; i++) {
			for(int j = 0; j < width; j++) {
				board[i][j] = ' ';
			}
		}
	}
	
	public void setBoard(int height, int width, char currentChar) {
		board[height][width] =  currentChar;
	}
	
	public char getCell(int height, int width) {
		return board[height][width];
	}
	
	public String showBoard() {
		StringBuilder board = new StringBuilder();
		for(int i = 0; i < height; i++) {
			for(int j = 0; j < width; j++) {
				if(j == 0) {
					board.append("||  ");
					board.append(this.board[i][j]);
					board.append("  |");
				} else if( j == width -1) {
					board.append("|  ");
					board.append(this.board[i][j]);
					board.append("  ||");
				} else {
					board.append("|  ");
					board.append(this.board[i][j]);
					board.append("  |");
				}
			}
			
			board.append(System.lineSeparator());
			for(int k = 0; k < width * 7.3; k++) {
				board.append("-");
			}			
			board.append(System.lineSeparator());
		}
		
		for(int k = 0; k < width; k++) {
			board.append("    ");
			board.append(k+1);
			board.append("  ");		
		}
		
		return board.toString();
	}
}

package Game;
import java.util.Scanner;
import java.util.regex.Pattern;

public class FourInARowGame {
	Scanner player = new Scanner(System.in);
	gameLogic game = new gameLogic();
	
	public void game() {
		
		boolean gameover = false;
		initializeGame();
		while(!gameover) {
			boolean firstPlayerTurn = true;
			boolean isWinning = false;
			boolean playing = false;
			while(!playing) {
				if(firstPlayerTurn) {
					isWinning = turn(game.Player1());
					firstPlayerTurn = false;
				} else {
					isWinning = turn(game.Player2());
					firstPlayerTurn = true;
				}
				displayBoard();
				if(isWinning) {
					if(game.Player1().IsWinning()) {
						System.out.print("Congrats " + game.Player1().getName() + "  you won the game!");
					} else {
						System.out.print("Congrats " + game.Player2().getName() + "  you won the game!");
					}
					if(replay()) {
						playing = true;
					} else {
						playing = true;
						gameover = true;
					}
				}
			}
		}
		
		System.out.print("The End!");
	}
	
	private void initializeGame() {
		System.out.println("Hello welcome to Four in a Row!");
		String firstPlayerName = playerName();
		char firstPlayerSign = playerSign();
		game.SetPlayer1(new Player(firstPlayerName, firstPlayerSign));
		System.out.println("Second player:");
		String secondPlayerName = playerName();
		char secondPlayerSign = 'O';
		if(firstPlayerSign == 'O') {
			secondPlayerSign = 'X';
		}
		
		game.SetPlayer2(new Player (secondPlayerName, secondPlayerSign));
		System.out.println("Great, " + firstPlayerName + " you'll be " + firstPlayerSign);
		System.out.println("And " + secondPlayerName + " you'll be " + secondPlayerSign);
		
		int height = boardSize("rows");
		int width = boardSize("colunms");
		Board b = new Board(height, width);
		game.setBoard(b);
		System.out.println("Let's begin!");
		displayBoard();
	}
	
	private String playerName() {
		System.out.println("Please enter your name: ");
		String playerName = player.nextLine();
		while(!Pattern.matches("[a-zA-Z]+",playerName)){
			System.out.println("Invalid input, please enter your name: ");
			playerName = player.nextLine();
		}
		return playerName;
	}
	
	private char playerSign() {
		System.out.println("Please enter your sign (X or O)");
		String playerSign = player.nextLine();
		while(!Pattern.matches("[x,X,o,O]",playerSign)){
			System.out.println("Invalid input, please enter your sign (X or O)");
			playerSign = player.nextLine();
		}
		
		return playerSign.toUpperCase().charAt(0);
	}
	
	private int boardSize(String option) {
		System.out.println("Please enter the number of " + option + " you wish to have");
		String choosenSize = player.nextLine();
		while(!Pattern.matches("[1-9]",choosenSize)){
			System.out.println("Invalid input, please enter a valid size (1-9) ");
			choosenSize = player.nextLine();
		}
		return Integer.parseInt(choosenSize);
	}
	
	private void displayBoard() {
		System.out.println(game.getBoard().showBoard().toString());
	}
	
	private boolean turn(Player player) {
		int validCol = validColChoice() - 1;
		int validRow = validRow(validCol);
		game.getBoard().setBoard(validRow, validCol, player.getSign());

		return checkForWin(player); 
	}
	
	private int validColChoice() {
		System.out.println("Please enter the column you wish to put your chip in");
		String choosenCol = player.nextLine();
		boolean valid = false;
		while(!valid) {
			if(Pattern.matches("[1-9]",choosenCol)) {
				int choosen = Integer.parseInt(choosenCol);
				if((choosen >= 1 && choosen <= game.getBoard().Width()) && (checkValidCol(choosen))) {
					valid = true;
				} else {
					System.out.println("Invalid input, please enter a valid coulmn, (1 - " + game.getBoard().Width() + ")");
					choosenCol = player.nextLine();
				}
			} else {
				System.out.println("Invalid input, please enter a valid coulmn, (1 - " + game.getBoard().Width() + ")");
				choosenCol = player.nextLine();
			}
		}
		
		return Integer.parseInt(choosenCol);
	}
	
	private boolean checkValidCol(int col) {
		boolean validation = false;
		for(int i = game.getBoard().Height() -1 ; i >= 0; i--) {
			if(game.getBoard().getCell(i, col -1) == ' ') {
				validation = true;
				break;
			}
		}
		
		return validation;
	}
	
	private int validRow(int col) {
		int validRow = 0;
		for(int i = game.getBoard().Height() -1 ; i >= 0; i--) {
			if(game.getBoard().getCell(i, col) == ' ') {
				validRow = i;
				break;
			}
		}
		
		return validRow;
	}
	
	private boolean checkForWin(Player player) {
		boolean isWinning = false; 
		// horizontalCheck 
	    for (int i = 0; i< game.getBoard().Height() ; i++ ){
	        for (int j = 0; j<game.getBoard().Width() - 3; j++){
	            if (game.getBoard().getCell(i, j) == player.getSign() && game.getBoard().getCell(i, j+1) == player.getSign() && game.getBoard().getCell(i, j+2) == player.getSign() && game.getBoard().getCell(i, j+3) == player.getSign()){
	            	isWinning = true;
	            }           
	        }
	    }
	    // verticalCheck
	    for (int i = 0; i<(game.getBoard().Height()-3) ; i++ ){
	        for (int j = 0; j<game.getBoard().Width(); j++){
	            if (game.getBoard().getCell(i, j) == player.getSign() && game.getBoard().getCell(i+1, j) == player.getSign() && game.getBoard().getCell(i+2, j) == player.getSign() && game.getBoard().getCell(i+3, j) == player.getSign()){
	            	isWinning = true;
	            }           
	        }
	    }
	    // ascendingDiagonalCheck 
	    for (int i=3; i< game.getBoard().Width() ; i++){
	        for (int j=0; j< game.getBoard().Height() - 3; j++){
	            if (game.getBoard().getCell(j, i) == player.getSign() && game.getBoard().getCell(j+1, i-1) == player.getSign() && game.getBoard().getCell(j+2, i-2) == player.getSign() && game.getBoard().getCell(j+3, i-3) == player.getSign()){
	            	isWinning = true;
	            }
	        }
	    }
	    // descendingDiagonalCheck
	    for (int i=3; i<game.getBoard().Width(); i++){
	        for (int j=3; j<game.getBoard().Height(); j++){
	            if (game.getBoard().getCell(j, i) == player.getSign() && game.getBoard().getCell(j-1, i-1) == player.getSign() && game.getBoard().getCell(j-2, i-2) == player.getSign() && game.getBoard().getCell(j-3, i-3) == player.getSign()){
	            	isWinning = true;
	            }
	        }
	    }
	    
	    if(isWinning) {
	    	player.setIsWinning(true);
	    }
	    
	    return isWinning;
	}

	private boolean replay() {
		
		System.out.println("Would you like to play again? (Y/N)");
		String playerChoice = player.nextLine();
		while(!Pattern.matches("[y,Y,n,N]",playerChoice)){
			System.out.println("Invalid input, please enter Y or N");
			playerChoice = player.nextLine();
		}
		
		boolean replay = playerChoice.toUpperCase().equals("Y");
		if(replay) {
			game.getBoard().initializeBoard();
			System.out.println("Let's play again");
			displayBoard();
		}
		
		return replay;
	}
}

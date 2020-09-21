package Game;

public class gameLogic {
	private Player player1;
	private Player player2;
	private Board board;
	
	public Board getBoard() {
		return board;
	}
	
	public void setBoard(Board board) {
		this.board = board;
	}
	
	public Player Player1() {
		return player1;
	}
	
	public void SetPlayer1(Player player) {
		player1 = player;
	}
	
	public Player Player2() {
		return player2;
	}
	
	public void SetPlayer2(Player player) {
		player2 = player;
	}
	

}

package Game;

public class Player {
	
	private String name;
	private char sign;
	private boolean isWinning = false;
	
	public Player(String name, char sign) {
		this.name = name;
		this.sign = sign;
	}
	
	public String getName() {
		return name;
	}
	
	public char getSign() {
		return sign;
	}
	
	public boolean IsWinning() {
		return isWinning;
	}
	
	public void setIsWinning( boolean currentIsWinning) {
		isWinning = currentIsWinning;
	}
	

}

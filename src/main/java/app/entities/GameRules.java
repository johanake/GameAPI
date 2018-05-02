package app.entities;

public class GameRules {
	public int getRoundCapacity() {
		return roundCapacity;
	}

	public void setRoundCapacity(int roundCapacity) {
		this.roundCapacity = roundCapacity;
	}

	private int roundCapacity;
	
	public GameRules(int roundCapacity){
		this.roundCapacity = roundCapacity;
		
	}

}

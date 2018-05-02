package app.entities;

/*
 * Stores a users game and login data
 * 
 * @author Johan Åkesson
 *	2018/04/25
 */

public class User {	
	private String email;
	private int highscore;
	private int roundsPlayed;	
	
	public User(){
		
		
	}
	
	public User(String email, int highscore, int roundsPlayed){
			this.email = email;
			this.highscore = highscore;
			this.roundsPlayed = roundsPlayed;

	}

	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}

	public int getHighscore() {
		return highscore;
	}
	public void setHighscore(int highscore) {
		this.highscore = highscore;
	}
	
	public int getNbrOfPlays() {
		return roundsPlayed;
	}
	public void setNbrOfPlays(int roundsPlayed) {
		this.roundsPlayed = roundsPlayed;
	}
	
	public String toString(){
		return this.email + " " + this.highscore + " " + this.roundsPlayed;
	}


}

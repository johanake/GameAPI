package app.user.service;

import java.util.ArrayList;

import org.springframework.stereotype.Service;

import app.entities.UpdateResponse;
import app.entities.User;

/*
 * 
 *  
 * @author Johan Åkesson
 *	2018/04/25
 */

@Service
public class UserService {
	private User testUser = new User("Testing bla", 046, 040);	

	
	/**
	 * Adds a user to the database
	 * @param user user to add
	 */
	public void addUser(User user){
		//db.addUser(user);
	}
	
	/**
	 * Retrives a user from the database
	 * @param email email of user to retrive
	 * @return specified user
	 */
	public ArrayList<User> getUser(String email){
		ArrayList<User> testList = new ArrayList<User>();
		testList.add(testUser);
		return testList;
																													// trådsäkra, reader/writer pattern? 
	}
	
	/**
	 * Updates a specific users highscore
	 * @param email email of user
	 * @param score score to update
	 * @return response object containing confirmation or error data
	 */
	public UpdateResponse updateUserHighscore(String email, int score){
		
		try{
			UpdateResponse response= new UpdateResponse(email, "Updated highscore with: " + score, true);					
			
			return response;			
		}catch(Exception e){
			UpdateResponse response= new UpdateResponse(email, "error " + e.getMessage(), false);					
			
			return response;
		}
	}
									// Kanske kan slå ihop update highscore och roundsplayed?? 
	
	/**
	 * Updates the number of rounds played for a specific user
	 * @param email email of user
	 * @return response object containing confirmation or error data
	 */
	public UpdateResponse updateUserRoundsPlayed(String email){
		//db.incrementUserRoundsPlayed(email)
		
		try{
			UpdateResponse response= new UpdateResponse(email, "Updated rounds played ", true);		
			return response;
			
		}catch(Exception e){
			UpdateResponse response= new UpdateResponse(email, "error " + e.getMessage(), false);		
			return response;
		}
	}
	
	/**
	 * Returns a highscore list of all users in descending format
	 * @return ArrayList of users and their highscores in descending order
	 */
	public  ArrayList<User> getHighscores(){
		
		ArrayList<User> users = new ArrayList<User>();	
		User user = new User("Bla bla", 45, 06);
		users.add(user);
		return users;

	}
	
	
	
	/**
	 * Retrives all users from the database
	 * @return ArrayList of all users
	 */
	public ArrayList<User> getAllUsers(){
		ArrayList<User> users = new ArrayList<User>();	
		User user = new User("johan@akesson.st", 45, 06);
		User user1 = new User("Emma@mailadress.com", 45, 06);
		User user2 = new User("Adam@adamsmail.se", 45, 06);
		users.add(user);
		users.add(user1);
		users.add(user2);
		return users;
	}

}

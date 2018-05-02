package app.user.restController;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import app.entities.DateAndTime;
import app.entities.UpdateResponse;
import app.entities.User;
import app.logger.ServerLogger;
import app.user.service.UserService;
import app.validators.UserValidator;
import app.validators.ValidatorResponse;

/*
 * Controller class that handles all calls regarding the user entity
 * 
 * @author Johan Åkesson
 *	2018/04/25
 */

@RestController
public class UserController {
	private UserValidator userVal;
	private ServerLogger logger = ServerLogger.getInstance();
	private static final String baseURL = "/api/";
	private DateAndTime dateAndTime = new DateAndTime();

	@Autowired
	private UserService userService;		//Singleton instance of userService	
	

	
	@ModelAttribute
	public void setVaryResponseHeader(HttpServletResponse response) {				
	 //   response.setHeader("Vary", "Accept");
		
	}
	
	//FLS10
	/**
	 * Adds a user to the database after the data has been validated
	 * 
	 * @param email	users email
	 * @param highscore	users highscore (default 0) 
	 * @param roundsPlayed users number of rounds played (default 0)
	 * @return returns a JSON-object containing user data or error information and http status codes
	 */
	@RequestMapping(value = baseURL + "/addUser", method = RequestMethod.POST)
	public ResponseEntity<Object> postUser(@RequestParam(value="email", defaultValue="email") String email, @RequestParam(value="highscore", defaultValue="0") int highscore, @RequestParam(value="roundsPlayed", defaultValue="0") int roundsPlayed){
		
		userVal = new UserValidator();
		ResponseEntity<Object> response;		
		ValidatorResponse valid = userVal.validate(email, highscore, roundsPlayed);
		if(valid.isValid()){
			User user = new User(email, highscore, roundsPlayed);
			userService.addUser(user);
			response = new ResponseEntity<Object>(user, HttpStatus.OK);									//Generera och sätta lösenord här? Returnera lösenordet i json som presenteras i FE 
			logger.eventLog("POST /api/users: Added User" + user.toString());
			
			return response;			
		}else{
			response = new ResponseEntity<Object>(valid, HttpStatus.BAD_REQUEST); 
			logger.errorLog("POST /api/users: Could not add User with info: " + email + ", " + highscore + ", " + roundsPlayed);

			
			return response; 
		}

	} 
	
	//FLS2
	/**
	 * Returns all the users from the database
	 * 
	 * @return JSON object containing all users
	 */
	@RequestMapping(value = baseURL +  "/users", method = RequestMethod.GET)
	public ResponseEntity<Object> getAllUsers(){	

		ResponseEntity<Object> response;
		ArrayList<User> users = userService.getAllUsers(); 
		
		if(users.isEmpty()){
			response = new ResponseEntity<Object>("No users found", HttpStatus.NOT_FOUND);
			logger.eventLog("GET /api/users: No users found" );
			
			return response;
		}else{
			logger.eventLog("GET /api/users: Returned user(s)" );
			response = new ResponseEntity<Object>(users, HttpStatus.OK);
			
			return response;
		}

	}
	//FLS3
	
	/**
	 * Retrives a specific user based on email
	 * 
	 * @param email target users email
	 * @return returns a JSON-object containing user data or error information and http status codes
	 */
	@RequestMapping(value = baseURL +  "/users/{email}", method = RequestMethod.GET)
	public ResponseEntity<Object> getUser(@PathVariable("email") String email){
		
		ResponseEntity<Object> response;
		ArrayList<User> users = userService.getUser(email);
		
		if(users.isEmpty()){
			response = new ResponseEntity<Object>("Could not find user with email: " + email, HttpStatus.NOT_FOUND);			//Generalisera?
			logger.eventLog("GET /api/users{email}: Could not find user with email: " + email);
			
			return response;
		}else{			
			response = new ResponseEntity<Object>(users, HttpStatus.OK);		
			logger.eventLog("GET /api/users{email}: Returned user with email: " + email);
			
			return response;
		}

	}
	

	//FLS4
	/**
	 * Updates a specific users highscore
	 * 
	 * @param email user to update
	 * @param score user score from gameplay
	 * @return returns a JSON-object containing confirmation or error information and http status codes
	 */
	@RequestMapping(value = baseURL + "/users/{email}/highscore/{score}", method = RequestMethod.PUT)  //använd patch???
	public ResponseEntity<Object> updateUserHighscore(@PathVariable("email") String email, @PathVariable("score") int score){
		
		UpdateResponse updateResponse = userService.updateUserHighscore(email, score);		
		if(updateResponse.isUpdateSuccessful()){
			ResponseEntity<Object> response = new ResponseEntity<Object>(updateResponse, HttpStatus.OK);
			logger.eventLog("PUT /api/users/{email}/highscore/{score}: /nUpdated user: " + email+ " with: " + score);
			
			return response;			
		}else{
			ResponseEntity<Object> response = new ResponseEntity<Object>(updateResponse, HttpStatus.BAD_REQUEST);
			logger.errorLog("PUT /api/users/{email}/highscore/{score}:"
					+ " /nCould not update user:" + email + " with: " + score + "/n" + response.toString());
			
			
			return response;
		}
		
		
		
		
	}
	//FLS11
	
	/**
	 * Updates the number of rounds a specific player has played
	 * 
	 * @param email user to update
	 * @param nbr nbr of rounds played
	 * @return returns a JSON-object containing confirmation or error information and http status codes
	 */
	@RequestMapping(value = baseURL + "/users/{email}/roundsplayed/inc", method = RequestMethod.PUT)  //använd patch???
	public ResponseEntity<Object>  updateUserRoundsPlayed(@PathVariable("email") String email){
		
		UpdateResponse updateResponse = userService.updateUserRoundsPlayed(email);		
		if(updateResponse.isUpdateSuccessful()){
			ResponseEntity<Object> response = new ResponseEntity<Object>(updateResponse, HttpStatus.OK);
			logger.eventLog("PUT /api/users/{email}/roundsplayed/inc: "
					+ "/nIncremented highscore for user: " + email);
			
			return response;			
		}else{
			ResponseEntity<Object> response = new ResponseEntity<Object>(updateResponse, HttpStatus.BAD_REQUEST);
			logger.errorLog("PUT /api/users/{email}/roundsplayed/inc: "
					+ "/nCould not increment score for user: " + email + 
					"/n" + response.toString());
			
			return response;
		}
	}
	
	//FLS2
	/**
	 * Returns the highscore list in descending order
	 * 
	 * @return returns a JSON-object containing the highscore list or error information and http status codes
	 */
	@RequestMapping(value = baseURL + "/users/highscores", method = RequestMethod.GET)
	public ResponseEntity<Object>  getHighscoreList(){	
		
		ResponseEntity<Object> response;
		ArrayList<User> highscores = userService.getHighscores();
		
		if(highscores.isEmpty()){
			response = new ResponseEntity<Object>("No users found", HttpStatus.NOT_FOUND);
			logger.eventLog("GET /api/users/highscores: "
					+ "/nNo users found");
			
			return response;
		}else{			
			response = new ResponseEntity<Object>(highscores, HttpStatus.OK);
			logger.eventLog("GET /api/users/highscores: "
					+ "/nReturned highscore list");
			
			return response;
		}
		
	}
	
	/**
	 * Returns time difference between the end date and the current time in milliseconds.
	 * @author Lucas Borg 2018-04-30
	 * @return JSON object containing the number or error information and http status codes 
	 */
	@RequestMapping(value = baseURL +  "/end_date", method = RequestMethod.GET)
	public ResponseEntity<Object> getEndDate(){		
		ResponseEntity<Object> response;
		Calendar cal = Calendar.getInstance(TimeZone.getDefault());
		cal.set(2018, 4, 25, 0, 0);
		Date date = cal.getTime();
		Object millisec = dateAndTime.convertEndDateToMillisec(date); // Should get date from database

		if (millisec == null) {
			response = new ResponseEntity<Object>("No end date found", HttpStatus.NOT_FOUND);
			logger.errorLog("No end date found");
			return response;
		} else {
			response = new ResponseEntity<Object>((long) millisec, HttpStatus.OK);
			logger.eventLog("Time until end date received");
			return response;
		}
	}
}
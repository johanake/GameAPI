package app.login.restController;


import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;



import app.logger.ServerLogger;
import app.login.services.LoginService;


@RestController
public class LoginController {
	private ServerLogger logger = ServerLogger.getInstance();
	private static final String baseURL = "/api/";

	@Autowired
	private LoginService loginService;		//Singleton instance of userService	
	

	
	@ModelAttribute
	public void setVaryResponseHeader(HttpServletResponse response) {				
	 //   response.setHeader("Vary", "Accept");
		
	}

	
	@RequestMapping(value = baseURL + "/login", method = RequestMethod.GET)
	public ResponseEntity<Object> login(@RequestParam(value="email", defaultValue="email") String email, @RequestParam(value="password", defaultValue="password") String password){
		boolean response = false;
		
		if(loginService.validateLogin(email, password)){
			response = true;
			ResponseEntity<Object> responseObject = new ResponseEntity<Object>(response, HttpStatus.OK);
			logger.eventLog("User with email " + email + " successfully logged in");
			
			return responseObject;
		}else{
			response = false;
			ResponseEntity<Object> responseObject = new ResponseEntity<Object>(response, HttpStatus.UNAUTHORIZED);
			logger.eventLog("User with email " + email + " could not login");
			
			return responseObject;
		}

	} 
	

}

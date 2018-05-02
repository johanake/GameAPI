package app.admin.restController;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import app.admin.service.AdminService;
import app.logger.ServerLogger;

@RestController
public class AdminController {
	private ServerLogger logger = ServerLogger.getInstance();
	private static final String baseURL = "/api/";

	@Autowired
	private AdminService adminService;		


	
	@RequestMapping(value = baseURL + "/admin/roundcapacity", method = RequestMethod.POST)
	public ResponseEntity<Object> updateAllowedRounds(@RequestParam(value="roundcapacity", defaultValue="3") int nbrOfRounds){
		if(adminService.updateNbrOfRounds(nbrOfRounds)){
			
			ResponseEntity<Object> responseObject = new ResponseEntity<Object>("Round capacity set at: " + nbrOfRounds, HttpStatus.OK);
			logger.eventLog("POST /admin/roundcapacity: Set round capacity at " + nbrOfRounds);
			return responseObject;
			
		}else{
			
			ResponseEntity<Object> responseObject = new ResponseEntity<Object>("Could not update round capacity, please check data: " + nbrOfRounds, HttpStatus.BAD_REQUEST);
			logger.errorLog("POST /admin/roundcapacity: Could not set round capacity at: " + nbrOfRounds);
			return responseObject;
		}		

	} 
	

}
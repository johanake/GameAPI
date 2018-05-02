package app.entities;

/*
 * Used to store information to be presented in JSON-format regarding a update(PUT) request
 * 
 * @author Johan Åkesson
 *	2018/04/25
 */

public class UpdateResponse {
	private String email;
	private String message;
	private boolean updateSuccessful;
	
	public UpdateResponse(String email, String message, boolean status){
		this.email = email;
		this.message = message;
		this.updateSuccessful = status;
		
	}
	

	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	
	public boolean isUpdateSuccessful() {
		return updateSuccessful;
	}

	public void setUpdateSuccessful(boolean updateSuccessful) {
		this.updateSuccessful = updateSuccessful;
	}
	
	public String toString(){
		return this.email + " " + this.message;
	}

}

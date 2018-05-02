package app.validators;

/**
 * Response object contaning confirmation and/or a message with error data
 * 
 * @author Johan
 *
 */
public class ValidatorResponse {

	private boolean valid = false;;
	private String message;
	
	public boolean isValid() {
		return valid;
	}
	public void setValid(boolean valid) {
		this.valid = valid;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
}

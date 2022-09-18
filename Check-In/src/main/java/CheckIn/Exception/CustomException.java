package CheckIn.Exception;

import org.springframework.stereotype.Component;

@Component
public class CustomException extends RuntimeException{

	private String errorCode;
	private String errorMessage;
	public CustomException(String errorCode, String errorMessage) {
		super();
		this.errorCode = errorCode;
		this.errorMessage = errorMessage;
	}
	
	public CustomException()
	{
		
	}

	public String getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
}
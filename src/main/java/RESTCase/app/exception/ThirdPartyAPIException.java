package RESTCase.app.exception;

public class ThirdPartyAPIException extends Exception {

	public ThirdPartyAPIException(Throwable t) {
		super(t.getMessage(), t);
	}
	
	public ThirdPartyAPIException(String message, Throwable t) {
		super(message, t);
	}
}

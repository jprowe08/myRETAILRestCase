package RESTCase.app.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value=HttpStatus.INTERNAL_SERVER_ERROR, reason="A fatal error has occurred.") 
public class ServerErrorException extends Exception {

	public ServerErrorException(Throwable t) {
		super(t);
	}
}

package RESTCase.app.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Exception to throw bad request to the client. The product id they were looking for was not found. 
 * The product id is not in the system.
 * @author James
 *
 */
@ResponseStatus(value=HttpStatus.BAD_REQUEST, reason="Did not find product matching the specified id.") 
public class ProductNotFoundException extends Exception {
	
}

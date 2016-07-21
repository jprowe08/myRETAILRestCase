package RESTCase.app.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value=HttpStatus.BAD_REQUEST, reason="Did not find pricing matching the specified product id.") 
public class PricingNotFoundException extends Exception {

}

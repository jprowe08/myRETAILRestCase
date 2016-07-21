package RESTCase.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import RESTCase.app.domain.Pricing;
import RESTCase.app.domain.Product;
import RESTCase.app.exception.PricingNotFoundException;
import RESTCase.app.exception.ProductNotFoundException;
import RESTCase.app.exception.ServerErrorException;
import RESTCase.app.exception.ThirdPartyAPIException;
import RESTCase.app.external.api.ExternalProductAPI;
import RESTCase.app.repo.PriceRepository;

/**
 * This controller will have two operations - GET by id, and PUT by id. 
 * 
 * GET will retrieve the product from the target API and find the price within our local mongo db. It will then return
 * the object as one.
 * 
 * PUT will update the price value and currency code by product id within our local mongo db.
 * @author James
 *
 */
@RestController
public class ProductController {

	private static final String PRODUCT_CONTROLLER_PATH = "/api/rest/product/{id}";
	
	//By defining these as repositories, the dependencies are injected for the next two class variables
	@Autowired
	private PriceRepository pricingRepo;
	@Autowired
	private ExternalProductAPI externalAPI;
	
	@RequestMapping(value = PRODUCT_CONTROLLER_PATH, method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public Product getProductWithPrice(@PathVariable(value = "id") int id) throws ProductNotFoundException, ServerErrorException {
		try {
			//Retrieve product information from our API
			Product product = externalAPI.getProductInformation(id);

			//If the product was not found throw a 404 not found
			if (product == null) {
				throw new ProductNotFoundException();
			}
			//Retrieve the pricing from the database and set it in the domain object
			Pricing pricing = pricingRepo.getPricingForId(id);
			product.setCurrentPrice(pricing);

			return product;
		} catch (ThirdPartyAPIException e) {
			//This will throw 500 internal server error
			throw new ServerErrorException(e);
		}
	}
	
	@RequestMapping(value = PRODUCT_CONTROLLER_PATH, method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
	public void updateProductPrice(@PathVariable(value = "id") int id, @RequestBody Product product) throws PricingNotFoundException {
		//If we did not update anything throw an exception
		if(pricingRepo.updatePricingForId(id, product.getCurrentPrice()) == false) {
			throw new PricingNotFoundException();
		}
	}

}

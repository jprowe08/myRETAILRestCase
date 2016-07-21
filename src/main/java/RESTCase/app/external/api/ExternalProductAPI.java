package RESTCase.app.external.api;

import RESTCase.app.domain.Product;
import RESTCase.app.exception.ThirdPartyAPIException;

public interface ExternalProductAPI {
	/**
	 * Retrieve the product from the external API by product id.
	 * @param productId
	 * @return Product
	 * @throws ThirdPartyAPIException
	 */
	public Product getProductInformation(int productId) throws ThirdPartyAPIException;
}

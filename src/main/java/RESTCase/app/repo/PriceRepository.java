package RESTCase.app.repo;

import RESTCase.app.domain.Pricing;

public interface PriceRepository {
	/**
	 * Retrieve from the repository product pricing by the product id.
	 * @param productId
	 * @return Pricing
	 */
	public Pricing getPricingForId(int productId);
	/**
	 * Update product pricing by the product id.
	 * @param productId
	 * @param pricing
	 * @return boolean
	 */
	public boolean updatePricingForId(int productId, Pricing pricing);
}

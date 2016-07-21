package RESTCase.app.repo;

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import RESTCase.app.domain.CurrencyCode;
import RESTCase.app.domain.Pricing;

public class TestPriceRepositoryImpl {
	
	private static final int TEST_PRODUCT_ID = 15117729;
	
	private PriceRepositoryImpl repo = new PriceRepositoryImpl();
	
	private Pricing beforePricing;
	
	private final Pricing testPricing = new Pricing(201, CurrencyCode.USD);
	
	@Before
	public void init() {
		beforePricing = repo.getPricingForId(TEST_PRODUCT_ID);
		repo.updatePricingForId(TEST_PRODUCT_ID, testPricing);
	}
	
	@Test
	public void testRead() {
		Pricing pricing = repo.getPricingForId(TEST_PRODUCT_ID);
		
		assertEquals(testPricing, pricing);
	}
	
	@Test
	public void testUpdate() {
		Pricing pricing = new Pricing(502, CurrencyCode.USD);
		repo.updatePricingForId(TEST_PRODUCT_ID, pricing);
		
		Pricing retrievedPricing = repo.getPricingForId(TEST_PRODUCT_ID);
		
		assertEquals(pricing, retrievedPricing);
	}
	
	@After
	public void cleanUp() {
		repo.updatePricingForId(TEST_PRODUCT_ID, beforePricing);
	}
}

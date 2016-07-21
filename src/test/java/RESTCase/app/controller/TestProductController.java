package RESTCase.app.controller;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import RESTCase.app.domain.CurrencyCode;
import RESTCase.app.domain.Pricing;
import RESTCase.app.domain.Product;
import RESTCase.app.exception.PricingNotFoundException;
import RESTCase.app.exception.ProductNotFoundException;
import RESTCase.app.external.api.ExternalProductAPI;
import RESTCase.app.repo.PriceRepository;

@RunWith(MockitoJUnitRunner.class)
public class TestProductController {
	@InjectMocks
	ProductController controller;
	@Mock
	private PriceRepository repoMock;
	@Mock
	private ExternalProductAPI apiMock;
	@Mock
	private Product productMock;
	
	@Test
	public void testHappyPathGet() throws Exception {
		Product product = new Product(1, "iPad");
		product.setCurrentPrice(new Pricing(200.99, CurrencyCode.USD));
		
		when(repoMock.getPricingForId(1)).thenReturn(product.getCurrentPrice());
		when(apiMock.getProductInformation(1)).thenReturn(product);
		
		Product retProduct = controller.getProductWithPrice(1);

		verify(repoMock, times(1)).getPricingForId(1);
		verify(apiMock, times(1)).getProductInformation(1);
		assertEquals(retProduct, product);
	}
	
	@Test
	public void testHappyPathPut() throws Exception {
		
		when(repoMock.updatePricingForId(Mockito.anyInt(), Mockito.anyObject())).thenReturn(true);
		
		controller.updateProductPrice(1, productMock);
		
		verify(repoMock, times(1)).updatePricingForId(Mockito.anyInt(), Mockito.anyObject());
	}
	
	@Test(expected=PricingNotFoundException.class) 
	public void testInvalidIdPut() throws Exception {
		when(repoMock.updatePricingForId(Mockito.anyInt(), Mockito.anyObject())).thenReturn(false);
		
		controller.updateProductPrice(1, productMock);
		
		verify(repoMock, times(1)).updatePricingForId(Mockito.anyInt(), Mockito.anyObject());
	}
	
	@Test(expected=ProductNotFoundException.class)
	public void testInvalidId() throws Exception {
		when(repoMock.getPricingForId(Mockito.anyInt())).thenReturn(null);
		when(apiMock.getProductInformation(Mockito.anyInt())).thenReturn(null);
		
		controller.getProductWithPrice(1);
		
		verify(repoMock, times(1)).getPricingForId(Mockito.anyInt());
		verify(apiMock, times(1)).getProductInformation(Mockito.anyInt());
	}
}

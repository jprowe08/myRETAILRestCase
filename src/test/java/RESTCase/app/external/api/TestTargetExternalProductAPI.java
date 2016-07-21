package RESTCase.app.external.api;

import static org.junit.Assert.assertEquals;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.BDDMockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import RESTCase.app.domain.Product;
import RESTCase.app.util.HttpUtil;

@RunWith(PowerMockRunner.class)
@PrepareForTest(HttpUtil.class)
public class TestTargetExternalProductAPI {

	private TargetExternalProductAPI api = new TargetExternalProductAPI();
	
	@Test
	public void testHappyPathRead() throws Exception {
		Product expectedProduct = new Product(1, "Ipod");
		PowerMockito.mockStatic(HttpUtil.class);
		BDDMockito.given(HttpUtil.peformJSONGetRequest(String.format(TargetExternalProductAPI.TARGET_BASE + TargetExternalProductAPI.GET_PRODUCT_INFO, 1))).willReturn(getJSON());
		
		Product product = api.getProductInformation(1);
		
		assertEquals(expectedProduct, product);
	}
	
	
	@SuppressWarnings("unchecked")
	private JSONObject getJSON() {
		JSONObject retJSON = new JSONObject();
		JSONObject pcrJSON = new JSONObject();
		JSONArray itemArrJSON = new JSONArray();
		JSONObject itemJSON = new JSONObject();
		
		itemJSON.put("general_description", "Ipod");
		itemArrJSON.add(itemJSON);
		pcrJSON.put("items", itemArrJSON);
		retJSON.put("product_composite_response", pcrJSON);
		
		return retJSON;
	}
}

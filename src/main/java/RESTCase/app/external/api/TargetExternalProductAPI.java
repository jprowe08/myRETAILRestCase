package RESTCase.app.external.api;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.stereotype.Repository;

import RESTCase.app.domain.Product;
import RESTCase.app.exception.ThirdPartyAPIException;
import RESTCase.app.util.HttpUtil;

@Repository
public class TargetExternalProductAPI implements ExternalProductAPI {

	protected static final String TARGET_BASE = "https://api.target.com";

	protected static final String GET_PRODUCT_INFO = "/products/v3/%s?fields=descriptions&id_type=TCIN&key=43cJWpLjH8Z8oR18KdrZDBKAgLLQKJjz";
	@Override
	public Product getProductInformation(int productId) throws ThirdPartyAPIException {
		JSONObject productJson = HttpUtil.peformJSONGetRequest(String.format(TARGET_BASE + GET_PRODUCT_INFO, productId));
		
		return fromJson(productId, productJson);
	}
	
	private Product fromJson(int productId, JSONObject productJson) {
		JSONObject productComposite = (JSONObject)productJson.get("product_composite_response");
		JSONArray itemArray = (JSONArray)productComposite.get("items");
		JSONObject item = (JSONObject)itemArray.get(0);
		
		String description = (String)item.get("general_description");
		
		if(description != null) {
			return new Product(productId, description);
		}
		return null;
	}
}

package RESTCase.app.util;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import RESTCase.app.exception.ThirdPartyAPIException;

/**
 * External utility for other classes to eventually use when making outside API requests
 * @author James
 *
 */
public class HttpUtil {
	
	/**
	 * Perform an Http GET request on the URL returning JSON. If no JSON returned throws ThirdPartyAPIException.
	 * @param url
	 * @return JSONObject
	 * @throws ThirdPartyAPIException
	 */
	public static JSONObject peformJSONGetRequest(String url) throws ThirdPartyAPIException {
		CloseableHttpResponse response = null;
	    
		try (CloseableHttpClient httpClient = HttpClients.createDefault()){

			HttpGet httpGet = new HttpGet(url);
			
			response = httpClient.execute(httpGet);
			
			HttpEntity entity = response.getEntity();

			String retSrc = EntityUtils.toString(entity); 
			
			JSONParser parser = new JSONParser();
			
			return (JSONObject)parser.parse(retSrc);
		} catch (Exception e) {
			throw new ThirdPartyAPIException(e.getMessage(), e);
		}
	}
	
}

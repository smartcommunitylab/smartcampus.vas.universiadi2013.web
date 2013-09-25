package eu.trentorise.smartcampus.universiadi.util;

import java.io.IOException;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.params.ConnManagerParams;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.util.EntityUtils;

import eu.trentorise.smartcampus.aac.AACException;
import eu.trentorise.smartcampus.aac.model.TokenData;


public class EasyTokenManger {

	/** address of the code validation endpoint */
	private static final String PATH_TOKEN = "oauth/token";
	
	/** Timeout (in ms) we specify for each http request */
	public static final int HTTP_REQUEST_TIMEOUT_MS = 30 * 1000;

	private String auth_token;

	
	
	private String clientId;
	private String clientSecret;

	private String profileAddress;

	private String juniperToken;

	public EasyTokenManger(String clientId,String clientSecret,String juniperToken,String auth_token,String profileAddress){
		this.clientId=clientId;
		this.clientSecret=clientSecret;
		this.profileAddress=profileAddress;
		this.juniperToken=juniperToken;
		this.auth_token=auth_token;
	}
	

	public String getAuthToken() {
		return auth_token;

	}

	public String getClientSmartCampusToken() throws AACException {

		final HttpResponse resp;
		final HttpEntity entity = null;
		String url = profileAddress + PATH_TOKEN + "?client_id=" + clientId
				+ "&client_secret=" + clientSecret
				+ "&grant_type=client_credentials";
		final HttpPost post = new HttpPost(url);
		post.setEntity(entity);
		post.setHeader("Accept", "application/json");

		try {
			resp = getHttpClient().execute(post);

			final String response = EntityUtils.toString(resp.getEntity());
			if (resp.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
				TokenData data = TokenData.valueOf(response);
				return data.getAccess_token();
			}
			throw new AACException("Error validating " + resp.getStatusLine());
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;

	}

	public String getClientJuniperToken() {
		return juniperToken;

	}

	protected static HttpClient getHttpClient() {
		HttpClient httpClient = new DefaultHttpClient();
		final HttpParams params = httpClient.getParams();
		HttpConnectionParams.setConnectionTimeout(params,
				HTTP_REQUEST_TIMEOUT_MS);
		HttpConnectionParams.setSoTimeout(params, HTTP_REQUEST_TIMEOUT_MS);
		ConnManagerParams.setTimeout(params, HTTP_REQUEST_TIMEOUT_MS);
		return httpClient;
	}

}

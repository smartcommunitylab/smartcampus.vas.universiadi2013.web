package eu.trentorise.smartcampus.universiadi.controller;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.conn.params.ConnManagerParams;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import eu.trentorise.smartcampus.aac.AACException;
import eu.trentorise.smartcampus.universiadi.util.EasyTokenManger;

@Controller("UserObjectController")
public class UserObjectController {

	@Autowired(required = false)
	MongoTemplate db;

	
	@Autowired
	@Value("${juniper.address}")
	String url_juniper="https://smartcampus.eventbuilder.it/";
	
    public static final int HTTP_REQUEST_TIMEOUT_MS = 30 * 1000;
    
    @Autowired
	@Value("${services.server}")
    private String aacURL;
	
    @Autowired
	@Value("${client.id.sc}")
	private String clientId;
    @Autowired
	@Value("${client.secret.sc}")
	private String clientSecret;
    @Autowired
	@Value("${client.token.j}")
    private String client_juniper_token;
    
	private EasyTokenManger tkm;
	
	@PostConstruct
    private void init(){
	tkm= new EasyTokenManger(aacURL,clientId,clientSecret,client_juniper_token);

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
	
	
	@RequestMapping(method = RequestMethod.GET, value = "/volontari/categorie")
	public @ResponseBody
	String getVolontariCategorie(
			HttpServletRequest request,
			HttpServletResponse response, HttpSession session) {
		
		
		  final HttpResponse resp;
	        String url = url_juniper + "getCategorieVolontari";
	        final HttpGet get = new HttpGet(url);
	        get.setHeader("Accept", "application/json");
	        get.setHeader("Authorization", tkm.getClientJuniperToken());
	        try {
	            resp = getHttpClient().execute(get);
	            String s = EntityUtils.toString(resp.getEntity());
	            if (resp.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
	            	return s;
	            }
	            throw new AACException("Error validating resource " + resp.getStatusLine());
	        } catch (final Exception e) {
	            e.printStackTrace();
	        }
		

		return null;
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/utente/{idutente}/funzioni")
	public @ResponseBody
	String getFunzioniUtente(
			HttpServletRequest request, 
			@PathVariable("idutente") String utente,
			HttpServletResponse response, HttpSession session) {
		
	
		
		  final HttpResponse resp;
	        String url = url_juniper +  "getFunzioni?idutente="+utente;
	        final HttpGet get = new HttpGet(url);
	        get.setHeader("Accept", "application/json");
	        get.setHeader("Authorization", tkm.getClientJuniperToken());
	        try {
	            resp = getHttpClient().execute(get);
	            String s = EntityUtils.toString(resp.getEntity());
	            if (resp.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
	            	return s;
	            }
	            throw new AACException("Error validating resource " + resp.getStatusLine());
	        } catch (final Exception e) {
	            e.printStackTrace();
	        }
		

		return null;
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/utente/{idutente}/superiori")
	public @ResponseBody
	String getSuperioriUtente(
			HttpServletRequest request, 
			@PathVariable("idutente") String utente,
			HttpServletResponse response, HttpSession session) {
		
		  final HttpResponse resp;
	        String url = url_juniper + "getSuperiori?idutente="+utente;
	        final HttpGet get = new HttpGet(url);
	        get.setHeader("Accept", "application/json");
	        get.setHeader("Authorization", tkm.getClientJuniperToken());
	        try {
	            resp = getHttpClient().execute(get);
	            String s = EntityUtils.toString(resp.getEntity());
	            if (resp.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
	            	return s;
	            }
	            throw new AACException("Error validating resource " + resp.getStatusLine());
	        } catch (final Exception e) {
	            e.printStackTrace();
	        }
		

		return null;
	}
}
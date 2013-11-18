package eu.trentorise.smartcampus.universiadi.controller;

import java.text.SimpleDateFormat;
import java.util.Date;

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
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import eu.trentorise.smartcampus.aac.AACException;
import eu.trentorise.smartcampus.universiadi.util.EasyTokenManger;

@Controller("CalendarioController")
public class CalendarioController {
	
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
    
	
	@RequestMapping(method = RequestMethod.GET, value = "/turni/{data}/{funzione}/{utente}")
	public @ResponseBody
	String getTurniForDataAndUtenteAndFunzione(
			HttpServletRequest request, @PathVariable("data") long data,
			@PathVariable("funzione") String funzione,
			@PathVariable("utente") String utente,
			HttpServletResponse response, HttpSession session) {
		
		SimpleDateFormat sdf=new SimpleDateFormat("yyyyMMdd");
		Date x=new Date(data);
		
		  final HttpResponse resp;
	        String url = url_juniper + "getTurniForDataAndFunzione?date="+sdf.format(x)+"&idfunzione="+funzione+"&idutente="+utente;
	        System.out.println(url);
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
	
	@RequestMapping(method = RequestMethod.GET, value = "/turni/{data}/{funzione}")
	public @ResponseBody
	String getTurniForDataAndFunzione(
			HttpServletRequest request, @PathVariable("data") long data,
			@PathVariable("funzione") String funzione,
			HttpServletResponse response, HttpSession session) {
		
		SimpleDateFormat sdf=new SimpleDateFormat("yyyyMMdd");
		Date x=new Date(data);
				
				
		  final HttpResponse resp;
	        String url = url_juniper + "getTurniForDataAndFunzione?date="+sdf.format(x)+"&idfunzione="+funzione;
	        System.out.println(url);
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
	
	@RequestMapping(method = RequestMethod.GET, value = "/meeting/{data}/{funzione}/{utente}")
	public @ResponseBody
	String getMeetings(
			HttpServletRequest request, @PathVariable("data") long data,
			@PathVariable("funzione") String funzione,
			@PathVariable("utente") String utente,
			HttpServletResponse response, HttpSession session) {
		
		SimpleDateFormat sdf=new SimpleDateFormat("yyyyMMdd");
		Date x=new Date(data);
		
		  final HttpResponse resp;
	        String url = url_juniper + "getTurniForDataAndFunzione?date="+sdf.format(x)+"&idfunzione="+funzione+"&idutente="+utente;
	        System.out.println(url);
	        final HttpGet get = new HttpGet(url);
	        get.setHeader("Accept", "application/json");
	        get.setHeader("Authorization", tkm.getClientJuniperToken());
	        try {
	        	String resultArray="[ ";
	            resp = getHttpClient().execute(get);
	            String s = EntityUtils.toString(resp.getEntity());
	            if (resp.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
	            	JSONArray array= new JSONArray(s);
	            	for(int i=0;i<array.length();i++){
	            		JSONObject obj=       array.getJSONObject(i);
	            		if(obj.getString("Labels").compareTo("Meeting")==0){
	            			resultArray=resultArray+obj.toString()+",";
	            		}
	            		
	            	}
	            		            	
	            	return resultArray.substring(0, resultArray.length()-1)+"]";
	            }
	            throw new AACException("Error validating resource " + resp.getStatusLine());
	        } catch (final Exception e) {
	            e.printStackTrace();
	        }
		
	        
		return null;
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

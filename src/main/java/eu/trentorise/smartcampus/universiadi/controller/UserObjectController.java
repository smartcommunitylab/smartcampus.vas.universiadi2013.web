package eu.trentorise.smartcampus.universiadi.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.params.ConnManagerParams;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import eu.trentorise.smartcampus.ac.provider.filters.AcProviderFilter;
import eu.trentorise.smartcampus.universiadi.model.TicketObj;
import eu.trentorise.smartcampus.universiadi.model.TurnoObj;
import eu.trentorise.smartcampus.universiadi.model.UserObj;
import eu.trentorise.smartcampus.universiadi.model.UtenteJuniper;
import eu.trentorise.smartcampus.universiadi.model.containerData.ContainerUtenti;
import eu.trentorise.smartcampus.universiadi.util.EasyTokenManger;

@Controller("UserObjectController")
public class UserObjectController {

	

	@Autowired
	@Value("${clientidsc}")
	private String client_sc_Id;

	@Autowired
	@Value("${clientscsecrect}")
	private String client_sc_secret;
	
	@Autowired
	@Value("${fix.juniper.token}")
	private String client_juniper_token;	

	@Autowired
	@Value("${usertoken}")
	private String userToken;


	@Autowired
	@Value("${profile.address}")
	private String profileAddress;
	
	/** Timeout (in ms) we specify for each http request */
	public static final int HTTP_REQUEST_TIMEOUT_MS = 30 * 1000;
	
	
	@Autowired
	@Value("${juniper.address}")
	private String juniperAddress;

	
	
	private EasyTokenManger easyTokenManger;

	
		
	@PostConstruct
	public void init() {
		easyTokenManger=new EasyTokenManger(client_sc_Id,client_sc_secret,client_juniper_token,userToken,profileAddress);
	
	}

	@Autowired(required = false)
	MongoTemplate db;


	@RequestMapping(method = RequestMethod.GET, value = "/login/{user}/{password}")
	public @ResponseBody
	String getAuthToken(HttpServletRequest request,
			@PathVariable("user") String user, HttpServletResponse response,
			@PathVariable("password") String password,
			HttpServletResponse response1, HttpSession session)
			throws JSONException {

		ArrayList<UserObj> mListaUtenti = ContainerUtenti.getUtenti();
		for (UserObj utente : mListaUtenti)
			if (utente.getUser().equalsIgnoreCase(user)
					&& utente.getPassword().equalsIgnoreCase(password)) {
				String token = "aee58a92-d42d-42e8-b55e-12e4289586fc";
				UserObj newUser = new UserObj(utente.getNome(),
						utente.getCognome(), utente.getAmbito(),
						utente.getRuolo(), utente.getFoto(), utente.getUser(),
						utente.getPassword(), utente.getNumeroTelefonico());
				newUser.setToken(token);
				ContainerUtenti.replaceUtente(utente, newUser);
				return token;
			}
		return null;
	}

	@RequestMapping(method = RequestMethod.GET, value = "/read_user_data")
	public @ResponseBody
	UserObj getUserData(HttpServletRequest request,
			HttpServletResponse response, HttpSession session) {
		String token = request.getHeader(AcProviderFilter.TOKEN_HEADER);
		
		ArrayList<UserObj> mListaUtenti = ContainerUtenti.getUtenti();
		for (UserObj utente : mListaUtenti)
			if (utente.getToken() != null
					&& utente.getToken().equalsIgnoreCase(token))
				return utente;
		return null;
	}

	@RequestMapping(method = RequestMethod.GET, value = "/anonymus_login")
	public @ResponseBody
	String getAnonymousToken(HttpServletRequest request,
			HttpServletResponse response, HttpSession session) {

		return "token_anonymous";
	}

	@RequestMapping(method = RequestMethod.GET, value = "/random_turn")
	public @ResponseBody
	TurnoObj getTurnoRandom(HttpServletRequest request,
			HttpServletResponse response, HttpSession session) {
		ArrayList<UserObj> listaUtenti = new ArrayList<UserObj>();
		ArrayList<TurnoObj> listaTurni = new ArrayList<TurnoObj>();
		listaUtenti = ContainerUtenti.getUtenti();

		for (UserObj user : listaUtenti) {

			listaTurni = user.getListaTurni();

		}

		return listaTurni.get(0);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/invalidate")
	public @ResponseBody
	boolean invalidateToken(HttpServletRequest request,
			HttpServletResponse response, HttpSession session) {
		String token = request.getHeader(AcProviderFilter.TOKEN_HEADER);
		
		ArrayList<UserObj> mListaUtenti = ContainerUtenti.getUtenti();
		for (UserObj utente : mListaUtenti)
			if (utente.getToken() != null
					&& utente.getToken().equalsIgnoreCase(token)) {
				UserObj newUser = new UserObj(utente.getNome(),
						utente.getCognome(), utente.getAmbito(),
						utente.getRuolo(), utente.getFoto(), utente.getUser(),
						utente.getPassword(), utente.getNumeroTelefonico());
				newUser.setToken(null);
				ContainerUtenti.replaceUtente(utente, newUser);
				return true;
			}
		return false;
	}

	@RequestMapping(method = RequestMethod.POST, value = "/users_for_turn")
	public @ResponseBody
	ArrayList<UserObj> getAtletiForEvento(HttpServletRequest request,
			HttpServletResponse response, HttpSession session,
			@RequestBody TurnoObj turno) throws IOException
			 {
		ArrayList<UserObj> listaUtenti = new ArrayList<UserObj>();
		ArrayList<UserObj> listaUtentiMatch = new ArrayList<UserObj>();
		listaUtenti = ContainerUtenti.getUtenti();

		for (UserObj utente : listaUtenti) {
			ArrayList<TurnoObj> listaTurniUtente = new ArrayList<TurnoObj>();
			listaTurniUtente = utente.getListaTurni();
			for (TurnoObj turnoSingoloUtente : listaTurniUtente) {
				if (turnoSingoloUtente.getData() == turno.getData()
						&& turnoSingoloUtente.getLuogo().equalsIgnoreCase(
								turno.getLuogo())
						&& turnoSingoloUtente.getCategoria().equalsIgnoreCase(
								turno.getCategoria())
						&& turnoSingoloUtente.getOraInizio() == turno
								.getOraInizio()
						&& turnoSingoloUtente.getOraFine() == turno
								.getOraFine()) {
					listaUtentiMatch.add(utente);
				}
			}
		}

		return listaUtentiMatch;
	}
	
	
	@RequestMapping(method = RequestMethod.POST, value = "/utente/save")
	public @ResponseBody
	boolean saveUtente(HttpServletRequest request, HttpServletResponse response,
			HttpServletResponse response1, HttpSession session,@RequestBody UtenteJuniper utentejuniper)
			throws JSONException {
	
		db.save(utentejuniper);
		
		return false;
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/utente/{idutente}/funzioni")
	public @ResponseBody
	String funzioniUtente(HttpServletRequest request, HttpServletResponse response,
			HttpServletResponse response1, HttpSession session,@PathVariable("idutente") String idutente)
			throws JSONException {
	
		final HttpResponse resp;	
				
		String url =  juniperAddress + "getFunzioni?id=" + idutente;
		final HttpGet post = new HttpGet(url);		
		post.setHeader("Accept", "application/json");
		post.setHeader("Authorization", easyTokenManger.getClientJuniperToken());
		

		try {
			resp = getHttpClient().execute(post);
			
			final String responseString = EntityUtils.toString(resp.getEntity());
			if (resp.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
				return (responseString);
			}
			
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return "";
	}

	
	
	@RequestMapping(method = RequestMethod.GET, value = "/volontari/categorie")
	public @ResponseBody
	String getCategorieVolontari(HttpServletRequest request, HttpServletResponse response,
			HttpServletResponse response1, HttpSession session)
			throws JSONException {
	
		final HttpResponse resp;	
				
		String url =  juniperAddress + "getCategoriVolontari" ;
		final HttpGet post = new HttpGet(url);		
		post.setHeader("Accept", "application/json");
		post.setHeader("Authorization", easyTokenManger.getClientJuniperToken());
		

		try {
			resp = getHttpClient().execute(post);
			
			final String responseString = EntityUtils.toString(resp.getEntity());
			if (resp.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
				return (responseString);
			}
			
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return "";
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
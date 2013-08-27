package eu.trentorise.smartcampus.universiadi.Controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.core.CollectionCallback;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;

import eu.trentorise.smartcampus.ac.provider.filters.AcProviderFilter;
import eu.trentorise.smartcampus.presentation.common.exception.DataException;
import eu.trentorise.smartcampus.presentation.common.exception.NotFoundException;
import eu.trentorise.smartcampus.universiadi.Model.UserObj;
import eu.trentorise.smartcampus.universiadi.Model.ContainerData.ContainerUtenti;

@Controller("userObjectController")
public class UserObjectController {

	@Autowired(required = false)
	MongoTemplate db;

	private static int ID = 0;

	@PostConstruct
	public void init() {

	}

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
				String token = "token" + ID;
				utente.setToken(token);
				ID++;
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
			if (utente.getToken().equalsIgnoreCase(token))
				return utente;
		return null;
	}

	@RequestMapping(method = RequestMethod.GET, value = "/anonymus_login")
	public @ResponseBody
	String getAnonymousToken(HttpServletRequest request,
			HttpServletResponse response, HttpSession session) {

		return "token_anonymous";
	}

	@RequestMapping(method = RequestMethod.GET, value = "/invalidate")
	public @ResponseBody
	UserObj invalidateToken(HttpServletRequest request,
			HttpServletResponse response, HttpSession session) {

		String token = request.getHeader(AcProviderFilter.TOKEN_HEADER);
		ArrayList<UserObj> mListaUtenti = ContainerUtenti.getUtenti();
		for (UserObj utente : mListaUtenti)
			if (utente.getToken().equalsIgnoreCase(token))
				utente.setToken(null);
		return null;
	}

}
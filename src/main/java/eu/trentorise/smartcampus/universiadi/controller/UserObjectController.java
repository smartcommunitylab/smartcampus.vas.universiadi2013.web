package eu.trentorise.smartcampus.universiadi.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import eu.trentorise.smartcampus.universiadi.model.UserObject;



@Controller("userObjectController")
public class UserObjectController {

	
	@Autowired(required = false)
	MongoTemplate db;
	
	@PostConstruct
	public void init(){
		
		
		UserObject x = new UserObject();
		x.setUser("a");
		x.setPassword("a");
		x.setToken("tokendia");
		
		db.save(x);
		
		
	}
	
	
	@RequestMapping(method = RequestMethod.GET, value = "/login/{user}/{password}")
	public @ResponseBody
	String getAuthToken(HttpServletRequest request,
			@PathVariable("user") String user, HttpServletResponse response,
			@PathVariable("password") String password, HttpServletResponse response1,
			HttpSession session) throws JSONException {
		
		Criteria crit = new Criteria();
		UserObject utente;
		String risposta = new String();
		
		crit.and("user").is(user);
		crit.and("password").is(password);
		Query query = Query.query(crit);
		
		utente=db.findOne(query,UserObject.class);
		
		if(utente==null){return "NULL"; }else{risposta = utente.getToken();}
		
		
		return risposta;	
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/login/{token}")
	public @ResponseBody
	UserObject getUserData(HttpServletRequest request,
			@PathVariable("token") String tok,
			HttpServletResponse response, HttpSession session)
			{	
		Criteria crit = new Criteria();
		UserObject utente;
		String risposta = new String();
		
		crit.and("token").is(tok);
		Query query = Query.query(crit);
		
		utente=db.findOne(query,UserObject.class);
		
		
		return utente;	
	}
	
	
}
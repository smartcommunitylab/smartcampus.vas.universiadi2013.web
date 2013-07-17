package eu.trentorise.smartcampus.universiadi.controller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import eu.trentorise.smartcampus.universiadi.model.Evento;
import eu.trentorise.smartcampus.universiadi.model.UserObject;
import eu.trentorise.smartcampus.universiadi.model.ee;




@Controller("eventoController")
public class EventoController {

	@Autowired
	MongoTemplate db;

	
	@PostConstruct
	public void init(){
		/*
		UserObject x = new UserObject();
		x.setUser("mario");
		x.setPassword("rossi");
		x.setToken("tokendimario");
		
		db.save(x);
		*/
	}
	
	
	@RequestMapping(method = RequestMethod.GET, value = "")
	public @ResponseBody
	String gfdfg(HttpServletRequest request,
			@PathVariable("user") String user, HttpServletResponse response,
			@PathVariable("password") String password, HttpServletResponse response1,
			HttpSession session) throws JSONException {
		
		Criteria crit = new Criteria();
		UserObject x;
		String res = new String();
		
		crit.and("user").is(user);
		crit.and("password").is(password);
		Query query = Query.query(crit);
		
		x=db.findOne(query,UserObject.class);
		
		if(x==null){return ""; }else{res = x.getToken();}
		
		
		return res;	
	}
	
	
}
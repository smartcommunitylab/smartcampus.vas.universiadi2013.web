package eu.trentorise.smartcampus.universiadi.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import eu.trentorise.smartcampus.presentation.common.exception.DataException;
import eu.trentorise.smartcampus.presentation.common.exception.NotFoundException;

@Controller("FaqController")
public class FaqController {
	@Autowired(required = false)
	MongoTemplate db;
	
	@RequestMapping(method = RequestMethod.POST, value = "/domanda")
	public @ResponseBody
	List<String> sendUserNotification(HttpServletRequest request,
	HttpServletResponse response, HttpSession session,
	@RequestBody String domanda) throws DataException,
	IOException, NotFoundException {
		
		domanda = domanda.replace("%27", " ");
		domanda = domanda.replace("+", " ");
		domanda = domanda.replace("=", " ");
		domanda = domanda.replace("%3B", " ");
		domanda = domanda.replace("%2C", " ");
		domanda = domanda.replace(".", " ");
		domanda = domanda.replace("%21", " ");
		domanda = domanda.replace("  ", " ");
		
		String[] stringRichiesta = domanda.split(" ");
		
		//String tag = new String();
		List<String> listString = new ArrayList<String>();
		
		//List<String> occorrenze = new ArrayList<String>();
		//Criteria crit = new Criteria();


		for (String stringTemp : stringRichiesta) {
			
			if(stringTemp.length() > 3) {
				listString.add(stringTemp);
			}
		}
		  
		
		/*
		for(i=0;i<g.size();i++){
			tag = g.get(i);
			
			
			crit.and("user").is(user);
			crit.and("password").is(password);
			Query query = Query.query(crit);
			
			utente=db.findOne(query,UserObject.class);
		}*/
		
		return listString;
	}

}

package eu.trentorise.smartcampus.universiadi.controller;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import eu.trentorise.smartcampus.universiadi.model.SportObj;

@Controller("sportController")
public class SportController {

		
	@Autowired
	MongoTemplate db;
	
	@PostConstruct
	public void init() {
		//mListaAllSport = ContainerSport.getSport();
		db.save(new SportObj("Sport 0", 0, "Descrizione Alpine"));
		db.save(new SportObj("Sport 1", 1, "Descrizione Cross"));
		db.save(new SportObj("Sport 2", 2, "Descrizione Curling"));
		db.save(new SportObj("Sport 3", 3, "Descrizione Figure"));
		db.save(new SportObj("Sport 4", 4, "Descrizione Freestyle"));
		db.save(new SportObj("Sport 5", 5, "Descrizione Ice"));
		db.save(new SportObj("Sport 6", 6, "Descrizione Nordic"));
		db.save(new SportObj("Sport 7", 7, "Descrizione Shorttracking"));
		db.save(new SportObj("Sport 8", 8, "Descrizione Ski Jumping"));
		db.save(new SportObj("Sport 9", 9, "Descrizione Snowboarding"));
		db.save(new SportObj("Sport 10", 10, "Descrizione SpeedSkating"));
		db.save(new SportObj("Sport 11", 11, "Descrizione Biathlon"));
		
	}

	@RequestMapping(method = RequestMethod.GET, value = "/sport")
	public @ResponseBody
	List<SportObj> getAllSport(HttpServletRequest request,
			HttpServletResponse response, HttpSession session) {

		return db.findAll(SportObj.class);
	}

}
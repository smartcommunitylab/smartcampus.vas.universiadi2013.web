package eu.trentorise.smartcampus.universiadi.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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

@Controller("eventoController")
public class EventoController {

	@Autowired
	MongoTemplate db;

	@RequestMapping(method = RequestMethod.GET, value = "/evento/{id}")
	public @ResponseBody
	Evento getEventoFromId(HttpServletRequest request,
			@PathVariable("id") String id, HttpServletResponse response,
			HttpSession session) {

		Evento x = new Evento();
		x.setId(id);
		db.save(x);

		Criteria crit = new Criteria();
		crit.and("id").is(id);
		Query query = Query.query(crit);

		x=db.find(query, Evento.class).get(0);
		
		return x;
	}
}

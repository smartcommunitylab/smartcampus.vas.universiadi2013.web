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

import java.awt.Event;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;
import java.util.Random;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;

import eu.trentorise.smartcampus.ac.provider.filters.AcProviderFilter;
import eu.trentorise.smartcampus.journeyplanner.JourneyPlannerConnector;
import eu.trentorise.smartcampus.presentation.common.exception.DataException;
import eu.trentorise.smartcampus.presentation.common.exception.NotFoundException;
import eu.trentorise.smartcampus.universiadi.model.Evento;
import eu.trentorise.smartcampus.universiadi.model.UserObject;

@Controller("eventoController")
public class EventoController {

	@Autowired
	MongoTemplate db;
	private List<Evento> yep = new ArrayList<Evento>();
	private List<UserObject> ulist = new ArrayList<UserObject>();
	private UserObject us1 = new UserObject();
	private UserObject us2 = new UserObject();

	@PostConstruct
	public void init() {

	}

	@RequestMapping(method = RequestMethod.GET, value = "/evento")
	public @ResponseBody
	ArrayList<DBObject> getEventi(HttpServletRequest request,
			HttpServletResponse response, HttpSession session) {

		ArrayList<DBObject> evappl = new ArrayList<DBObject>();
		DBCollection coll = db.getCollection("Eventi");
		ArrayList<BasicDBObject> listParams = new ArrayList<BasicDBObject>();

		BasicDBObject par1 = new BasicDBObject("ruolo", -1);
		BasicDBObject par2 = new BasicDBObject("ambito", "");
		listParams.add(par1);
		listParams.add(par2);
		BasicDBObject query = new BasicDBObject();
		query.put("$and", listParams);

		DBCursor cursorEv = coll.find(query);
		while (cursorEv.hasNext()) {
			DBObject obj = cursorEv.next();
			evappl.add(obj);
		}

		return evappl;
	}

	@RequestMapping(method = RequestMethod.GET, value = "/evento/id/{id}")
	public @ResponseBody
	ArrayList<DBObject> getEventoFromId(HttpServletRequest request,
			@PathVariable("id") Long id, HttpServletResponse response,
			HttpSession session) {
		ArrayList<DBObject> evappl = new ArrayList<DBObject>();
		DBCollection coll = db.getCollection("Eventi");
		BasicDBObject query = new BasicDBObject("ID", id);
		DBCursor cursorEv = coll.find(query);

		while (cursorEv.hasNext()) {
			DBObject obj = cursorEv.next();
			evappl.add(obj);
		}

		return evappl;
	}

	@RequestMapping(method = RequestMethod.GET, value = "/evento/ard/{user_ambito}/{user_ruolo}/{data}")
	public @ResponseBody
	ArrayList<DBObject> getEventi(HttpServletRequest request,
			@PathVariable("data") long data,
			@PathVariable("user_ambito") String user_ambito,
			@PathVariable("user_ruolo") String user_ruolo,
			HttpServletResponse response, HttpSession session) {

		int intUserRuolo = Integer.parseInt(user_ruolo);
		if (user_ambito.equals("null")) {
			user_ambito = "";
		}

		ArrayList<DBObject> evappl = new ArrayList<DBObject>();
		DBCollection coll = db.getCollection("Eventi");
		ArrayList<BasicDBObject> listParams = new ArrayList<BasicDBObject>();

		BasicDBObject par1 = new BasicDBObject("ruolo", intUserRuolo);
		BasicDBObject par2 = new BasicDBObject("ambito", user_ambito);
		BasicDBObject par3 = new BasicDBObject("data", data);

		listParams.add(par1);
		listParams.add(par2);
		listParams.add(par3);
		BasicDBObject query = new BasicDBObject();
		query.put("$and", listParams);

		DBCursor cursorEv = coll.find(query);
		while (cursorEv.hasNext()) {
			DBObject obj = cursorEv.next();
			evappl.add(obj);
		}

		return evappl;
	}

	@RequestMapping(method = RequestMethod.GET, value = "/evento/ar/{user_ambito}/{user_ruolo}")
	public @ResponseBody
	ArrayList<DBObject> getEventi(HttpServletRequest request,
			@PathVariable("user_ambito") String user_ambito,
			@PathVariable("user_ruolo") String user_ruolo,
			HttpServletResponse response, HttpSession session) {

		int intUserRuolo = Integer.parseInt(user_ruolo);
		if (user_ambito.equals("null")) {
			user_ambito = "";
		}

		ArrayList<DBObject> evappl = new ArrayList<DBObject>();
		DBCollection coll = db.getCollection("Eventi");
		ArrayList<BasicDBObject> listParams = new ArrayList<BasicDBObject>();

		BasicDBObject par1 = new BasicDBObject("ruolo", intUserRuolo);
		BasicDBObject par2 = new BasicDBObject("ambito", user_ambito);

		listParams.add(par1);
		listParams.add(par2);
		BasicDBObject query = new BasicDBObject();
		query.put("$and", listParams);

		DBCursor cursorEv = coll.find(query);
		while (cursorEv.hasNext()) {
			DBObject obj = cursorEv.next();
			evappl.add(obj);
		}

		return evappl;
	}

	@RequestMapping(method = RequestMethod.GET, value = "/evento/data/{data}")
	public @ResponseBody
	ArrayList<DBObject> getEventiPerData(HttpServletRequest request,
			@PathVariable("data") long data, HttpServletResponse response,
			HttpSession session) {

		ArrayList<DBObject> evappl = new ArrayList<DBObject>();
		DBCollection coll = db.getCollection("Eventi");
		BasicDBObject query = new BasicDBObject("data", data);
		DBCursor cursorEv = coll.find(query);

		while (cursorEv.hasNext()) {
			DBObject obj = cursorEv.next();
			evappl.add(obj);
		}

		return evappl;
	}

	@RequestMapping(method = RequestMethod.GET, value = "/evento/sport/{sport}")
	public @ResponseBody
	ArrayList<DBObject> getEventiPerSport(HttpServletRequest request,
			@PathVariable("sport") String sport, HttpServletResponse response,
			HttpSession session) {

		ArrayList<DBObject> evappl = new ArrayList<DBObject>();
		DBCollection coll = db.getCollection("Sport");
		BasicDBObject query = new BasicDBObject("nome", sport);
		DBCursor cursorEv = coll.find(query);

		while (cursorEv.hasNext()) {
			DBObject obj = cursorEv.next();
			evappl.add(obj);
		}

		return evappl;
	}

}
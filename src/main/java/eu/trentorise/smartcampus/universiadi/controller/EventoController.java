package eu.trentorise.smartcampus.universiadi.controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.mongodb.core.MongoTemplate;
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

import eu.trentorise.smartcampus.aac.AACException;
import eu.trentorise.smartcampus.territoryservice.TerritoryService;
import eu.trentorise.smartcampus.territoryservice.TerritoryServiceException;
import eu.trentorise.smartcampus.territoryservice.model.EventObject;
import eu.trentorise.smartcampus.territoryservice.model.ObjectFilter;
import eu.trentorise.smartcampus.universiadi.model.AtletObj;
import eu.trentorise.smartcampus.universiadi.model.MeetingObj;
import eu.trentorise.smartcampus.universiadi.model.containerData.ContainerMeeting;
import eu.trentorise.smartcampus.universiadi.util.EasyTokenManger;

@Controller("eventoController")
public class EventoController {
	
	@Autowired
	@Value("${territory.address}")
	private String territoryAddress;
	
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
	@Value("${profile.address}")
	private String profileAddress;
	
	@Autowired
	@Value("${usertoken}")
	private String userToken;

	@Autowired
	MongoTemplate db;
	
	private ArrayList<MeetingObj> mListaMeeting;
	
	private EasyTokenManger easyTokenManger;

	@PostConstruct
	public void init() {
		
		mListaMeeting = ContainerMeeting.getMeeting();
		
		easyTokenManger=new EasyTokenManger(client_sc_Id,client_sc_secret,client_juniper_token,userToken,profileAddress);
		
	}

	@RequestMapping(method = RequestMethod.GET, value = "/evento_data/{data}")
	public @ResponseBody
	List<EventObject> getEventiForData(HttpServletRequest request,
			@PathVariable("data") long data, HttpServletResponse response,
			HttpSession session) throws TerritoryServiceException {
		
		ObjectFilter filter = new ObjectFilter();
		filter.setLimit(10);
		
			
		TerritoryService territoryService = new TerritoryService(territoryAddress);
	
		filter.setTypes(Arrays.asList(new String[]{"universiadi13"}));
		filter.setFromTime(data);
		List<EventObject> events = territoryService.getEvents(filter, easyTokenManger.getUserToken());
	

		return events; 
	}

	@RequestMapping(method = RequestMethod.GET, value = "/evento_sport/{sport}")
	public @ResponseBody
	List<EventObject> getEventiForData(HttpServletRequest request,
			@PathVariable("sport") String sport, HttpServletResponse response,
			HttpSession session) throws TerritoryServiceException {

		ObjectFilter filter = new ObjectFilter();
		filter.setLimit(10);
		
			
		TerritoryService territoryService = new TerritoryService(territoryAddress);
	
		filter.setTypes(Arrays.asList(new String[]{"universiadi13"}));
		filter.setText(sport);
		List<EventObject> events = territoryService.getEvents(filter, easyTokenManger.getUserToken());
	

		return events; 
	}

	@RequestMapping(method = RequestMethod.GET, value = "/meeting/{date}")
	public @ResponseBody
	ArrayList<MeetingObj> getMeetingForData(HttpServletRequest request,
			@PathVariable("date") long date, HttpServletResponse response,
			HttpSession session) {

	
		ArrayList<MeetingObj> mResult = new ArrayList<MeetingObj>();
		for (MeetingObj obj : mListaMeeting)
			if (new SimpleDateFormat("dd.MM.yyyy", Locale.getDefault()).format(
					obj.getData()).equalsIgnoreCase(
					new SimpleDateFormat("dd.MM.yyyy", Locale.getDefault())
							.format(date)))
				mResult.add(obj);

		return mResult;
	}

	@RequestMapping(method = RequestMethod.POST, value = "/atleti_evento")
	public @ResponseBody
	ArrayList<AtletObj> getAtletiForEvento(HttpServletRequest request,
			HttpServletResponse response, HttpSession session,
			@RequestBody EventObject evento) throws  IOException
			 {
//		for (EventObject obj : mListaEventi)
//			if (new SimpleDateFormat("dd.MM.yyyy").format(obj.getData())
//					.equalsIgnoreCase(
//							new SimpleDateFormat("dd.MM.yyyy").format(evento
//									.getData()))
//					&& obj.getDescrizione().equalsIgnoreCase(
//							evento.getDescrizione())
//					&& obj.getGps().compareTo(evento.getGps()) == 0
//					&& obj.getNome().equalsIgnoreCase(evento.getNome())
//					&& obj.getTipoSport().equalsIgnoreCase(
//							evento.getTipoSport()))
//				return obj.getListaAtleti();
		return null;
	}

	// @RequestMapping(method = RequestMethod.GET, value = "/evento")
	// public @ResponseBody
	// ArrayList<DBObject> getEventi(HttpServletRequest request,
	// HttpServletResponse response, HttpSession session) {
	//
	// ArrayList<DBObject> evappl = new ArrayList<DBObject>();
	// DBCollection coll = db.getCollection("Eventi");
	// ArrayList<BasicDBObject> listParams = new ArrayList<BasicDBObject>();
	//
	// BasicDBObject par1 = new BasicDBObject("ruolo", -1);
	// BasicDBObject par2 = new BasicDBObject("ambito", "");
	// listParams.add(par1);
	// listParams.add(par2);
	// BasicDBObject query = new BasicDBObject();
	// query.put("$and", listParams);
	//
	// DBCursor cursorEv = coll.find(query);
	// while (cursorEv.hasNext()) {
	// DBObject obj = cursorEv.next();
	// evappl.add(obj);
	// }
	//
	// return evappl;
	// }

	@RequestMapping(method = RequestMethod.GET, value = "/evento/id/{id}")
	public @ResponseBody
	List<EventObject> getEventoFromId(HttpServletRequest request,
			@PathVariable("id") Long id, HttpServletResponse response,
			HttpSession session) throws TerritoryServiceException {
		ObjectFilter filter = new ObjectFilter();
	
		filter.setLimit(10);
		//fake
			
		TerritoryService territoryService = new TerritoryService(territoryAddress);
	
		filter.setTypes(Arrays.asList(new String[]{"universiadi13"}));
		
		List<EventObject> events = territoryService.getEvents(filter, easyTokenManger.getUserToken());
	

		return events; 
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
	
	@RequestMapping(method = RequestMethod.GET, value = "/evento/all")
	public @ResponseBody
	List<EventObject> getEventiAll(HttpServletRequest request,HttpServletResponse response,
			HttpSession session) throws TerritoryServiceException, AACException {
		ObjectFilter filter = new ObjectFilter();
		filter.setLimit(10);
		
			
		TerritoryService territoryService = new TerritoryService(territoryAddress);
	
		filter.setTypes(Arrays.asList(new String[]{"universiadi13"}));
		filter.setText("party");
		filter.setFromTime(System.currentTimeMillis());
		List<EventObject> events = territoryService.getEvents(filter, easyTokenManger.getUserToken());
	

		return events; 
	}

}
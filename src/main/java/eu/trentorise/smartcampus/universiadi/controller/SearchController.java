package eu.trentorise.smartcampus.universiadi.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.annotation.PostConstruct;
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

import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;

import eu.trentorise.smartcampus.universiadi.model.EventObj;
import eu.trentorise.smartcampus.universiadi.model.POIObj;
import eu.trentorise.smartcampus.universiadi.model.SportObj;
import eu.trentorise.smartcampus.universiadi.model.UserObj;
import eu.trentorise.smartcampus.universiadi.model.containerData.ContainerEventi;
import eu.trentorise.smartcampus.universiadi.model.containerData.ContainerPOI;
import eu.trentorise.smartcampus.universiadi.model.containerData.ContainerSport;
import eu.trentorise.smartcampus.universiadi.model.containerData.ContainerUtenti;

@Controller("searchController")
public class SearchController {

	@Autowired(required = false)
	MongoTemplate db;

	@PostConstruct
	public void init() {
		DBCollection coll = db.getCollection("Eventi");
		if (coll.count() == 0) {
			ArrayList<EventObj> mListaEventi = ContainerEventi.getEventi();
			for (EventObj evento : mListaEventi)
				db.save(evento, "Eventi");
		}

		DBCollection coll2 = db.getCollection("Utenti");
		if (coll2.count() == 0) {
			ArrayList<UserObj> mListaUtenti = ContainerUtenti.getUtenti();
			for (UserObj utente : mListaUtenti)
				db.save(utente, "Utenti");
		}

		DBCollection coll3 = db.getCollection("Sport");
		if (coll3.count() == 0) {
			ArrayList<SportObj> mListaSport = ContainerSport.getSport();
			for (SportObj sport : mListaSport)
				db.save(sport, "Sport");
		}

		DBCollection coll4 = db.getCollection("POI");
		if (coll4.count() == 0) {
			ArrayList<POIObj> mListaPOI = ContainerPOI.getPOI();
			for (POIObj poi : mListaPOI)
				db.save(poi, "POI");
		}

	}

	// public ArrayList<Object> getGlobalMatchFromDb(MongoTemplate db,
	// String pattern) {
	//
	// System.out.println();
	// ArrayList<Object> arrStMached = new ArrayList<Object>();
	// pattern = pattern.toLowerCase();
	//
	// // ////////EVENTI
	// DBCollection coll = db.getCollection("Eventi");
	// // BasicDBObject queryForID = new BasicDBObject();
	// ArrayList<BasicDBObject> listParams = new ArrayList<BasicDBObject>();
	// listParams.add(new BasicDBObject("nome", java.util.regex.Pattern
	// .compile(pattern)));
	// listParams.add(new BasicDBObject("descrizione", java.util.regex.Pattern
	// .compile(pattern)));
	// listParams.add(new BasicDBObject("indirizzo", java.util.regex.Pattern
	// .compile(pattern)));
	// listParams.add(new BasicDBObject("data", java.util.regex.Pattern
	// .compile(pattern)));
	// listParams.add(new BasicDBObject("tipoSport", java.util.regex.Pattern
	// .compile(pattern)));
	//
	// BasicDBObject query = new BasicDBObject();
	// query.put("$or", listParams);
	// DBCursor cursorID = coll.find(query);
	//
	// while (cursorID.hasNext()) {
	// DBObject obj = cursorID.next();
	// arrStMached.add(obj);
	// }
	//
	// // /////POI
	// DBCollection collPOI = db.getCollection("POI");
	// // BasicDBObject queryForID = new BasicDBObject();
	// ArrayList<BasicDBObject> listParamsPOI = new ArrayList<BasicDBObject>();
	// listParamsPOI.add(new BasicDBObject("nome", java.util.regex.Pattern
	// .compile(pattern, java.util.regex.Pattern.CASE_INSENSITIVE)));
	// listParamsPOI.add(new BasicDBObject("poitype", java.util.regex.Pattern
	// .compile(pattern, java.util.regex.Pattern.CASE_INSENSITIVE)));
	//
	// BasicDBObject queryPOI = new BasicDBObject();
	// queryPOI.put("$or", listParamsPOI);
	// DBCursor cursorIDPOI = collPOI.find(queryPOI);
	//
	// while (cursorIDPOI.hasNext()) {
	// DBObject objPOI = cursorIDPOI.next();
	// arrStMached.add(objPOI);
	// }
	//
	// // //SPORT
	//
	// DBCollection collSP = db.getCollection("Sport");
	// // BasicDBObject queryForID = new BasicDBObject();
	// ArrayList<BasicDBObject> listParamsSP = new ArrayList<BasicDBObject>();
	// listParamsSP.add(new BasicDBObject("nome", java.util.regex.Pattern
	// .compile(pattern, java.util.regex.Pattern.CASE_INSENSITIVE)));
	// listParamsSP.add(new BasicDBObject("descrizione",
	// java.util.regex.Pattern.compile(pattern,
	// java.util.regex.Pattern.CASE_INSENSITIVE)));
	//
	// BasicDBObject querySP = new BasicDBObject();
	// querySP.put("$or", listParamsSP);
	// DBCursor cursorIDSP = collSP.find(querySP);
	//
	// while (cursorIDSP.hasNext()) {
	// DBObject objSP = cursorIDSP.next();
	//
	// arrStMached.add(objSP);
	// }
	//
	// return arrStMached;
	//
	// }

	public ArrayList<Object> getEventMatchFromDb(MongoTemplate db,
			String pattern) {

		ArrayList<Object> arrStMached = new ArrayList<Object>();
		pattern = pattern.toLowerCase();

		DBCollection coll = db.getCollection("Eventi");
		// BasicDBObject queryForID = new BasicDBObject();
		ArrayList<BasicDBObject> listParams = new ArrayList<BasicDBObject>();
		listParams.add(new BasicDBObject("nome", java.util.regex.Pattern
				.compile(pattern, java.util.regex.Pattern.CASE_INSENSITIVE)));
		listParams.add(new BasicDBObject("descrizione", java.util.regex.Pattern
				.compile(pattern, java.util.regex.Pattern.CASE_INSENSITIVE)));
		listParams.add(new BasicDBObject("data", java.util.regex.Pattern
				.compile(pattern, java.util.regex.Pattern.CASE_INSENSITIVE)));
		listParams.add(new BasicDBObject("tipoSport", java.util.regex.Pattern
				.compile(pattern, java.util.regex.Pattern.CASE_INSENSITIVE)));

		BasicDBObject query = new BasicDBObject();
		query.put("$or", listParams);
		DBCursor cursorID = coll.find(query);
		while (cursorID.hasNext()) {
			DBObject obj = cursorID.next();

			/*
			 * String nomeSt = (String) obj.get("nome"); String descSt =
			 * (String) obj.get("descrizione"); arrStMached.add(nomeSt);
			 * arrStMached.add(descSt);
			 */
			arrStMached.add(obj);
		}

		return arrStMached;

	}

	public ArrayList<Object> getPOIMatchFromDb(MongoTemplate db, String pattern) {

		ArrayList<Object> arrStMached = new ArrayList<Object>();
		pattern = pattern.toLowerCase();

		DBCollection coll = db.getCollection("POI");
		// BasicDBObject queryForID = new BasicDBObject();
		ArrayList<BasicDBObject> listParams = new ArrayList<BasicDBObject>();
		listParams.add(new BasicDBObject("nome", java.util.regex.Pattern
				.compile(pattern, java.util.regex.Pattern.CASE_INSENSITIVE)));
		listParams.add(new BasicDBObject("categoria", java.util.regex.Pattern
				.compile(pattern, java.util.regex.Pattern.CASE_INSENSITIVE)));

		BasicDBObject query = new BasicDBObject();
		query.put("$or", listParams);
		DBCursor cursorID = coll.find(query);

		while (cursorID.hasNext()) {
			DBObject obj = cursorID.next();
			arrStMached.add(obj);
		}

		return arrStMached;

	}

	public ArrayList<Object> getSportMatchFromDb(MongoTemplate db,
			String pattern) {

		ArrayList<Object> arrStMached = new ArrayList<Object>();
		pattern = pattern.toLowerCase();

		DBCollection coll = db.getCollection("Sport");
		// BasicDBObject queryForID = new BasicDBObject();
		ArrayList<BasicDBObject> listParams = new ArrayList<BasicDBObject>();
		listParams.add(new BasicDBObject("nome", java.util.regex.Pattern
				.compile(pattern, java.util.regex.Pattern.CASE_INSENSITIVE)));
		listParams.add(new BasicDBObject("descrizione", java.util.regex.Pattern
				.compile(pattern, java.util.regex.Pattern.CASE_INSENSITIVE)));

		BasicDBObject query = new BasicDBObject();
		query.put("$or", listParams);
		DBCursor cursorID = coll.find(query);

		while (cursorID.hasNext()) {
			DBObject obj = cursorID.next();

			arrStMached.add(obj);
		}

		return arrStMached;

	}

	// @RequestMapping(method = RequestMethod.POST, value = "/search/global")
	// public @ResponseBody
	// ArrayList<Object> searchGlobal(HttpServletRequest request,
	// HttpServletResponse response, HttpSession session,
	// @RequestBody String domanda) throws DataException, IOException,
	// NotFoundException {
	// domanda = domanda.toLowerCase();
	// domanda = domanda.replace("%27", " ");
	// domanda = domanda.replace("+", " ");
	// domanda = domanda.replace("=", "");
	// domanda = domanda.replace("%3B", " ");
	// domanda = domanda.replace("%2C", " ");
	// domanda = domanda.replace(".", " ");
	// domanda = domanda.replace("%21", " ");
	// domanda = domanda.replace("è", "�");
	// domanda = domanda.replaceAll("\\s+$", "");
	// domanda = domanda.replace("  ", " ");
	//
	// ArrayList<Object> answerList = getGlobalMatchFromDb(db, domanda);
	//
	// return answerList;
	//
	// }

	@RequestMapping(method = RequestMethod.POST, value = "/search/eventi")
	public @ResponseBody
	ArrayList<Object> searchEventi(HttpServletRequest request,
			HttpServletResponse response, HttpSession session,
			@RequestBody String domanda) throws  IOException
			 {

		domanda = domanda.toLowerCase();
		domanda = domanda.replace("%27", " ");
		domanda = domanda.replace("+", " ");
		domanda = domanda.replace("=", "");
		domanda = domanda.replace("%3B", " ");
		domanda = domanda.replace("%2C", " ");
		domanda = domanda.replace(".", " ");
		domanda = domanda.replace("%21", " ");
		domanda = domanda.replace("è", "�");
		domanda = domanda.replaceAll("\\s+$", "");
		domanda = domanda.replace("  ", " ");

		ArrayList<Object> answerList = getEventMatchFromDb(db, domanda);

		return answerList;

	}

	@RequestMapping(method = RequestMethod.POST, value = "/search/poi")
	public @ResponseBody
	ArrayList<Object> searchPoi(HttpServletRequest request,
			HttpServletResponse response, HttpSession session,
			@RequestBody String domanda) throws  IOException
			 {

		domanda = domanda.toLowerCase();
		domanda = domanda.replace("%27", " ");
		domanda = domanda.replace("+", " ");
		domanda = domanda.replace("=", "");
		domanda = domanda.replace("%3B", " ");
		domanda = domanda.replace("%2C", " ");
		domanda = domanda.replace(".", " ");
		domanda = domanda.replace("%21", " ");
		domanda = domanda.replace("è", "�");
		domanda = domanda.replaceAll("\\s+$", "");
		domanda = domanda.replace("  ", " ");

		ArrayList<Object> answerList = getPOIMatchFromDb(db, domanda);

		return answerList;

	}

	@RequestMapping(method = RequestMethod.POST, value = "/search/sport")
	public @ResponseBody
	ArrayList<Object> searchSport(HttpServletRequest request,
			HttpServletResponse response, HttpSession session,
			@RequestBody String domanda) throws  IOException
			 {

		domanda = domanda.toLowerCase();
		domanda = domanda.replace("%27", " ");
		domanda = domanda.replace("+", " ");
		domanda = domanda.replace("=", "");
		domanda = domanda.replace("%3B", " ");
		domanda = domanda.replace("%2C", " ");
		domanda = domanda.replace(".", " ");
		domanda = domanda.replace("%21", " ");
		domanda = domanda.replace("è", "�");
		domanda = domanda.replaceAll("\\s+$", "");
		domanda = domanda.replace("  ", " ");

		ArrayList<Object> answerList = getSportMatchFromDb(db, domanda);

		return answerList;

	}

}

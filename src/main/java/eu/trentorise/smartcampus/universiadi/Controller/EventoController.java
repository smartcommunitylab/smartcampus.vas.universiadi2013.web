package eu.trentorise.smartcampus.universiadi.Controller;

import java.text.SimpleDateFormat;
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

import eu.trentorise.smartcampus.universiadi.Model.AtletObj;
import eu.trentorise.smartcampus.universiadi.Model.ElementDescRoute;
import eu.trentorise.smartcampus.universiadi.Model.EventObj;
import eu.trentorise.smartcampus.universiadi.Model.GeoPoint;
import eu.trentorise.smartcampus.universiadi.Model.MeetingObj;
import eu.trentorise.smartcampus.universiadi.Model.UserObj;
import eu.trentorise.smartcampus.universiadi.Model.ContainerData.ContainerEventi;
import eu.trentorise.smartcampus.universiadi.Model.ContainerData.ContainerMeeting;

import java.awt.Event;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
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

@Controller("eventoController")
public class EventoController {

	@Autowired
	MongoTemplate db;
	// private List<Evento> yep = new ArrayList<Evento>();
	// private List<UserObject> ulist = new ArrayList<UserObject>();
	// private UserObject us1 = new UserObject();
	// private UserObject us2 = new UserObject();
	private ArrayList<EventObj> mListaEventi;
	private ArrayList<MeetingObj> mListaMeeting;

	@PostConstruct
	public void init() {
		mListaEventi = ContainerEventi.getEventi();
		mListaMeeting = ContainerMeeting.getMeeting();
	}

	@RequestMapping(method = RequestMethod.GET, value = "/evento_data/{data}")
	public @ResponseBody
	ArrayList<EventObj> getEventiForData(HttpServletRequest request,
			@PathVariable("data") long data, HttpServletResponse response,
			HttpSession session) {

		String token = request.getHeader(AcProviderFilter.TOKEN_HEADER);
		ArrayList<EventObj> mResult = new ArrayList<EventObj>();
		for (EventObj obj : mListaEventi)
			if (new SimpleDateFormat("dd.MM.yyyy", Locale.getDefault()).format(
					obj.getData()).equalsIgnoreCase(
					new SimpleDateFormat("dd.MM.yyyy", Locale.getDefault())
							.format(data)))
				mResult.add(obj);

		return mResult;
	}

	@RequestMapping(method = RequestMethod.GET, value = "/evento_route")
	public @ResponseBody
	ArrayList<ElementDescRoute> getRouteEvento(HttpServletRequest request,
			HttpServletResponse response, HttpSession session,
			@RequestBody double[] gpsSource, double[] gpsDest)
			throws DataException, IOException, NotFoundException {
		return parseGoogleDescRoute(gpsSource, gpsDest);
	}

	private ArrayList<ElementDescRoute> parseGoogleDescRoute(double[] source,
			double[] dest) {
		URL url;
		try {
			String srcGPS = source[0] + "," + source[1];
			String destGPS = dest[0] + "," + dest[1];
			String path = "http://maps.googleapis.com/maps/api/directions/json?origin="
					+ srcGPS
					+ "&destination="
					+ destGPS
					+ "&sensor=false&language=it";
			url = new URL(path);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");
			conn.setRequestProperty("Accept", "application/json");

			if (conn.getResponseCode() != 200) {
				throw new RuntimeException("Failed : HTTP error code : "
						+ conn.getResponseCode());
			}

			BufferedReader br = new BufferedReader(new InputStreamReader(
					(conn.getInputStream())));

			String line = "";
			String output = "";
			System.out.println("Output from Server .... \n");
			while ((line = br.readLine()) != null) {
				output = output + line;
			}

			conn.disconnect();

			JSONObject object = new JSONObject(output);

			ArrayList<ElementDescRoute> result = new ArrayList<ElementDescRoute>();
			// Get routes
			JSONArray legs;
			legs = object.getJSONArray("routes").getJSONObject(0)
					.getJSONArray("legs");
			JSONObject leg = legs.getJSONObject(0);
			result.add(new ElementDescRoute(leg.getJSONObject("distance")
					.getString("text"), leg.getJSONObject("duration")
					.getString("text"), leg.getString("end_address"), "0"));

			JSONArray steps = leg.getJSONArray("steps");

			for (int j = 0; j < steps.length(); j++) {
				String img = "0";
				JSONObject step = steps.getJSONObject(j);
				try {
					img = step.getString("maneuver");
				} catch (JSONException e) {
				}

				String desc = step.getString("html_instructions").replaceAll(
						"\\<.*?>\n", "");
				result.add(new ElementDescRoute(step.getJSONObject("distance")
						.getString("text"), step.getJSONObject("duration")
						.getString("text"), desc, img));
			}

			return result;
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MalformedURLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (ProtocolException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		return null;
	}

	@RequestMapping(method = RequestMethod.GET, value = "/evento_sport/{sport}")
	public @ResponseBody
	ArrayList<EventObj> getEventiForData(HttpServletRequest request,
			@PathVariable("sport") String sport, HttpServletResponse response,
			HttpSession session) {

		String token = request.getHeader(AcProviderFilter.TOKEN_HEADER);
		ArrayList<EventObj> mResult = new ArrayList<EventObj>();
		for (EventObj obj : mListaEventi)
			if (obj.getTipoSport().equalsIgnoreCase(sport))
				mResult.add(obj);

		return mResult;
	}

	@RequestMapping(method = RequestMethod.GET, value = "/meeting/{date}")
	public @ResponseBody
	ArrayList<MeetingObj> getMeetingForData(HttpServletRequest request,
			@PathVariable("date") long date, HttpServletResponse response,
			HttpSession session) {

		String token = request.getHeader(AcProviderFilter.TOKEN_HEADER);
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
			@RequestBody EventObj evento) throws DataException, IOException,
			NotFoundException {
		int i = -1;
		return ((i = mListaEventi.indexOf(evento)) != -1) ? mListaEventi.get(i)
				.getListaAtleti() : null;
	}

//	@RequestMapping(method = RequestMethod.GET, value = "/evento/id/{id}")
//	public @ResponseBody
//	ArrayList<DBObject> getEventoFromId(HttpServletRequest request,
//			@PathVariable("id") Long id, HttpServletResponse response,
//			HttpSession session) {
//		ArrayList<DBObject> evappl = new ArrayList<DBObject>();
//		DBCollection coll = db.getCollection("Eventi");
//		BasicDBObject query = new BasicDBObject("ID", id);
//		DBCursor cursorEv = coll.find(query);
//
//		while (cursorEv.hasNext()) {
//			DBObject obj = cursorEv.next();
//			evappl.add(obj);
//		}
//
//		return evappl;
//	}

//	@RequestMapping(method = RequestMethod.GET, value = "/evento/ard/{user_ambito}/{user_ruolo}/{data}")
//	public @ResponseBody
//	ArrayList<DBObject> getEventi(HttpServletRequest request,
//			@PathVariable("data") long data,
//			@PathVariable("user_ambito") String user_ambito,
//			@PathVariable("user_ruolo") String user_ruolo,
//			HttpServletResponse response, HttpSession session) {
//
//		int intUserRuolo = Integer.parseInt(user_ruolo);
//		if (user_ambito.equals("null")) {
//			user_ambito = "";
//		}
//
//		ArrayList<DBObject> evappl = new ArrayList<DBObject>();
//		DBCollection coll = db.getCollection("Eventi");
//		ArrayList<BasicDBObject> listParams = new ArrayList<BasicDBObject>();
//
//		BasicDBObject par1 = new BasicDBObject("ruolo", intUserRuolo);
//		BasicDBObject par2 = new BasicDBObject("ambito", user_ambito);
//		BasicDBObject par3 = new BasicDBObject("data", data);
//
//		listParams.add(par1);
//		listParams.add(par2);
//		listParams.add(par3);
//		BasicDBObject query = new BasicDBObject();
//		query.put("$and", listParams);
//
//		DBCursor cursorEv = coll.find(query);
//		while (cursorEv.hasNext()) {
//			DBObject obj = cursorEv.next();
//			evappl.add(obj);
//		}
//
//		return evappl;
//	}
//
//	@RequestMapping(method = RequestMethod.GET, value = "/evento/ar/{user_ambito}/{user_ruolo}")
//	public @ResponseBody
//	ArrayList<DBObject> getEventi(HttpServletRequest request,
//			@PathVariable("user_ambito") String user_ambito,
//			@PathVariable("user_ruolo") String user_ruolo,
//			HttpServletResponse response, HttpSession session) {
//
//		int intUserRuolo = Integer.parseInt(user_ruolo);
//		if (user_ambito.equals("null")) {
//			user_ambito = "";
//		}
//
//		ArrayList<DBObject> evappl = new ArrayList<DBObject>();
//		DBCollection coll = db.getCollection("Eventi");
//		ArrayList<BasicDBObject> listParams = new ArrayList<BasicDBObject>();
//
//		BasicDBObject par1 = new BasicDBObject("ruolo", intUserRuolo);
//		BasicDBObject par2 = new BasicDBObject("ambito", user_ambito);
//
//		listParams.add(par1);
//		listParams.add(par2);
//		BasicDBObject query = new BasicDBObject();
//		query.put("$and", listParams);
//
//		DBCursor cursorEv = coll.find(query);
//		while (cursorEv.hasNext()) {
//			DBObject obj = cursorEv.next();
//			evappl.add(obj);
//		}
//
//		return evappl;
//	}
//
//	@RequestMapping(method = RequestMethod.GET, value = "/evento/data/{data}")
//	public @ResponseBody
//	ArrayList<DBObject> getEventiPerData(HttpServletRequest request,
//			@PathVariable("data") long data, HttpServletResponse response,
//			HttpSession session) {
//
//		ArrayList<DBObject> evappl = new ArrayList<DBObject>();
//		DBCollection coll = db.getCollection("Eventi");
//		BasicDBObject query = new BasicDBObject("data", data);
//		DBCursor cursorEv = coll.find(query);
//
//		while (cursorEv.hasNext()) {
//			DBObject obj = cursorEv.next();
//			evappl.add(obj);
//		}
//
//		return evappl;
//	}
//
//	@RequestMapping(method = RequestMethod.GET, value = "/evento/sport/{sport}")
//	public @ResponseBody
//	ArrayList<DBObject> getEventiPerSport(HttpServletRequest request,
//			@PathVariable("sport") String sport, HttpServletResponse response,
//			HttpSession session) {
//
//		ArrayList<DBObject> evappl = new ArrayList<DBObject>();
//		DBCollection coll = db.getCollection("Sport");
//		BasicDBObject query = new BasicDBObject("nome", sport);
//		DBCursor cursorEv = coll.find(query);
//
//		while (cursorEv.hasNext()) {
//			DBObject obj = cursorEv.next();
//			evappl.add(obj);
//		}
//
//		return evappl;
//	}

}
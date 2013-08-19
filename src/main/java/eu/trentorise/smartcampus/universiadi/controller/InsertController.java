package eu.trentorise.smartcampus.universiadi.controller;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

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

import eu.trentorise.smartcampus.universiadi.model.Evento;
import eu.trentorise.smartcampus.universiadi.model.GeoPoint;
import eu.trentorise.smartcampus.universiadi.model.POIObject;
import eu.trentorise.smartcampus.universiadi.model.SportObject;
import eu.trentorise.smartcampus.universiadi.model.UserObject;

@Controller("insertcontroller")
public class InsertController {

	@Autowired
	MongoTemplate db;
	ArrayList<SportObject> sportList = new ArrayList<SportObject>();
	ArrayList<POIObject> poiList = new ArrayList<POIObject>();
	ArrayList<Evento> eventList = new ArrayList<Evento>();
	ArrayList<UserObject> userList = new ArrayList<UserObject>();

	@PostConstruct
	public void init() {
		/*
		for (int i = 0; i < 200; i++) {
			Evento e1 = new Evento();
			e1.setID(i);
			e1.setNome("evento ".toLowerCase() + Integer.toString(i + 10).toLowerCase());
			e1.setDescrizione("descrizione ".toLowerCase()
					+ Integer.toString(i + 2).toLowerCase());
			e1.setGeo(45.282882, 11.8788787);
			e1.setIndirizzo("indirizzo ".toLowerCase()
					+ Integer.toString(i + 5).toLowerCase());
			if (i < 100 && i % 2 == 0) {
				e1.setTipoSport("skating".toLowerCase());
				e1.setRuolo(0);
				e1.setGeo(46.061991, 11.152775);
				e1.setAmbito("ambito 0".toLowerCase());
				e1.setData(Calendar.getInstance(Locale.getDefault())
						.getTimeInMillis());
			} else if (i >= 100 && i % 2 == 0) {
				e1.setTipoSport("curling".toLowerCase());
				e1.setRuolo(1);
				e1.setAmbito("ambito 1".toLowerCase());
				e1.setData(Calendar.getInstance(Locale.getDefault())
						.getTimeInMillis() + (3600 * 1000 * 24));
			} else {
				if (i < 50) {
					e1.setData(Calendar.getInstance(Locale.getDefault())
							.getTimeInMillis());
					e1.setTipoSport("snowboard".toLowerCase());
				} else if (i >= 50 && i < 150) {
					e1.setData(Calendar.getInstance(Locale.getDefault())
							.getTimeInMillis() + (3600 * 1000 * 24));
					e1.setTipoSport("curling".toLowerCase());
				} else {
					e1.setData(Calendar.getInstance(Locale.getDefault())
							.getTimeInMillis() + 2 * (3600 * 1000 * 24));
					e1.setTipoSport("skating".toLowerCase());
				}

				e1.setRuolo(-1);
				e1.setAmbito("".toLowerCase());

			}

			eventList.add(e1);
		}

		for (int t = 0; t < 20; t++) {
			SportObject s1 = new SportObject();
			if (t < 10) {
				s1.setNome("sport ".toLowerCase()
						+ Integer.toString(t + 10).toLowerCase());
				s1.setDescrizione("descrizione Sport ".toLowerCase()
						+ Integer.toString(t + 10).toLowerCase());
			} else if (t > 10) {
				s1.setNome("sport ".toLowerCase()
						+ Integer.toString(t + 20).toLowerCase());
				s1.setDescrizione("descrizione sport ".toLowerCase()
						+ Integer.toString(t + 20).toLowerCase());
			}

			sportList.add(s1);
		}

		for (int e = 0; e < 20; e++) {
			POIObject p1 = new POIObject();
			if (e < 10) {
				GeoPoint gPS = new GeoPoint(11.0053848 + e, 46.0602349 + e);
				p1.setNome("POI ".toLowerCase()
						+ Integer.toString(e + 10).toLowerCase());
				p1.setPOIType("GamePlaces".toLowerCase());
				p1.setGPS(gPS);
			} else if (e > 10) {
				GeoPoint gPS = new GeoPoint(11.1053848 + e, 46.1602349 + e);
				p1.setNome("POI ".toLowerCase()
						+ Integer.toString(e + 20).toLowerCase());
				p1.setPOIType("GamePlaces".toLowerCase());
				p1.setGPS(gPS);
			}

			poiList.add(p1);
		}
		
		for (int c = 0; c < 20; c++) {
			UserObject u1 = new UserObject();
			if (c < 10) {
				
				u1.setNome("user ".toLowerCase()+ Integer.toString(c + 10).toLowerCase());
				u1.setCognome("cognome ".toLowerCase()+ Integer.toString(c + 10).toLowerCase());
				u1.setAmbito("ambito ".toLowerCase()+ Integer.toString(c + 10).toLowerCase());
				u1.setRuolo(1);
				u1.setNumeroTelefonico("093021392018");
				u1.setUser("user".toLowerCase()+ Integer.toString(c + 10).toLowerCase());
				u1.setPassword("password".toLowerCase()+ Integer.toString(c + 10).toLowerCase());
				u1.setBadgeCode(1223+c);
				u1.setToken("dqqwwq443"+ Integer.toString(c + 10).toLowerCase());
				
				
				
				
			} else if (c > 10) {
				
				u1.setNome("user ".toLowerCase()+ Integer.toString(c + 10).toLowerCase());
				u1.setCognome("cognome ".toLowerCase()+ Integer.toString(c + 10).toLowerCase());
				u1.setAmbito("ambito ".toLowerCase()+ Integer.toString(c + 10).toLowerCase());
				u1.setRuolo(1);
				u1.setNumeroTelefonico("077021392018");
				u1.setUser("user".toLowerCase()+ Integer.toString(c + 10).toLowerCase());
				u1.setPassword("password".toLowerCase()+ Integer.toString(c + 10).toLowerCase());
				u1.setBadgeCode(1442+c);
				u1.setToken("sdd555q443"+ Integer.toString(c + 10).toLowerCase());
				
			}

			userList.add(u1);
		}

		for (int y = 0; y < eventList.size(); y++) {
			db.save(eventList.get(y), "Eventi");
		}

		for (int j = 0; j < sportList.size(); j++) {
			db.save(sportList.get(j), "Sport");
		}

		for (int x = 0; x < poiList.size(); x++) {
			db.save(poiList.get(x), "POI");
		}
		
		for (int f = 0; f < userList.size(); f++) {
			db.save(userList.get(f), "Users");
		}
*/
	}

}







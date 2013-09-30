package eu.trentorise.smartcampus.universiadi.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import eu.trentorise.smartcampus.territoryservice.TerritoryService;
import eu.trentorise.smartcampus.territoryservice.TerritoryServiceException;
import eu.trentorise.smartcampus.territoryservice.model.ObjectFilter;
import eu.trentorise.smartcampus.territoryservice.model.POIObject;
import eu.trentorise.smartcampus.universiadi.model.EventObj;
import eu.trentorise.smartcampus.universiadi.model.GeoPoint;
import eu.trentorise.smartcampus.universiadi.model.POIObj;
import eu.trentorise.smartcampus.universiadi.util.EasyTokenManger;

@Controller("poiController")
public class POIController {

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

	private EasyTokenManger easyTokenManger;

	private ArrayList<POIObj> mListaPOI;

	@PostConstruct
	public void init() {
		
		easyTokenManger = new EasyTokenManger(client_sc_Id, client_sc_secret,
				client_juniper_token, userToken, profileAddress);

	}

	@RequestMapping(method = RequestMethod.GET, value = "/poi/{type}")
	public @ResponseBody
	List<POIObject> getPoiForType(HttpServletRequest request,
			@PathVariable("type") String type, HttpServletResponse response,
			HttpSession session) throws TerritoryServiceException {

		TerritoryService territoryService = new TerritoryService(
				territoryAddress);
		ObjectFilter filter = new ObjectFilter();
		List<String> s = new ArrayList<String>();
		s.add(type);

		filter.setTypes(s);
		filter.setTypes(Arrays
				.asList(new String[] { "universiadi13 - Venues" }));
		List<POIObject> pois = territoryService.getPOIs(filter,
				easyTokenManger.getUserToken());
		return pois;
	}

	@RequestMapping(method = RequestMethod.POST, value = "/poi_evento")
	public @ResponseBody
	List<EventObj> getAtletiForEvento(HttpServletRequest request,
			HttpServletResponse response, HttpSession session,
			@RequestBody GeoPoint poi) throws IOException {

		for (POIObj obj : mListaPOI) {
			if (obj.getGPS().compareTo(poi) == 0)
				return obj.getEvento();
		}
		return null;

	}

	@RequestMapping(method = RequestMethod.POST, value = "/poi/all")
	public @ResponseBody
	List<POIObject> getAllPoi(HttpServletRequest request,
			HttpServletResponse response, HttpSession session,
			@RequestBody GeoPoint poi) throws IOException,
			TerritoryServiceException {

		TerritoryService territoryService = new TerritoryService(
				territoryAddress);
		ObjectFilter filter = new ObjectFilter();
		filter.setTypes(Arrays
				.asList(new String[] { "universiadi13 - Venues" }));
		List<POIObject> pois = territoryService.getPOIs(filter,
				easyTokenManger.getUserToken());
		return pois;
	}

}

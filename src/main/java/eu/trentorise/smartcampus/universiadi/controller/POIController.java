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

import eu.trentorise.smartcampus.presentation.common.exception.DataException;
import eu.trentorise.smartcampus.presentation.common.exception.NotFoundException;
import eu.trentorise.smartcampus.territoryservice.TerritoryService;
import eu.trentorise.smartcampus.territoryservice.TerritoryServiceException;
import eu.trentorise.smartcampus.territoryservice.model.ObjectFilter;
import eu.trentorise.smartcampus.territoryservice.model.POIObject;
import eu.trentorise.smartcampus.universiadi.model.EventObj;
import eu.trentorise.smartcampus.universiadi.model.GeoPoint;
import eu.trentorise.smartcampus.universiadi.model.POIObj;
import eu.trentorise.smartcampus.universiadi.model.containerData.ContainerPOI;
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
	@Value("${auth_token}")
	private String authToken;
	
	private EasyTokenManger easyTokenManger;

	private ArrayList<POIObj> mListaPOI;

	@PostConstruct
	public void init() {
		mListaPOI = ContainerPOI.getPOI();
		easyTokenManger=new EasyTokenManger(client_sc_Id,client_sc_secret,client_juniper_token,authToken,profileAddress);
		
		
	}

	@RequestMapping(method = RequestMethod.GET, value = "/poi/{type}")
	public @ResponseBody
	ArrayList<POIObj> getPoiForType(HttpServletRequest request,
			@PathVariable("type") String type, HttpServletResponse response,
			HttpSession session) {

		

		ArrayList<POIObj> mResult = new ArrayList<POIObj>();
		for (POIObj obj : mListaPOI)
			if (obj.getCategoria().equalsIgnoreCase(type))
				mResult.add(obj);

		return mResult;
	}

	@RequestMapping(method = RequestMethod.POST, value = "/poi_evento")
	public @ResponseBody
	ArrayList<EventObj> getAtletiForEvento(HttpServletRequest request,
			HttpServletResponse response, HttpSession session,
			@RequestBody GeoPoint poi) throws DataException, IOException,
			NotFoundException {

		for (POIObj obj : mListaPOI) {
			if (obj.getGPS().compareTo(poi) == 0)
				return obj.getEvento();
		}
		return null;
	}

	@RequestMapping(method = RequestMethod.POST, value = "/poi_evento/all")
	public @ResponseBody
	List<POIObject> getAllPoi(HttpServletRequest request,
			HttpServletResponse response, HttpSession session,
			@RequestBody GeoPoint poi) throws DataException, IOException,
			NotFoundException, TerritoryServiceException {
		
			TerritoryService territoryService = new TerritoryService(territoryAddress);
		ObjectFilter filter = new ObjectFilter();
		filter.setTypes(Arrays.asList(new String[]{"universiadi13 - Venues"}));
		List<POIObject> pois = territoryService.getPOIs(filter ,easyTokenManger.getAuthToken());
		return pois;
	}

	
	
	
	
}

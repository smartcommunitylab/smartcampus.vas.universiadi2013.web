package eu.trentorise.smartcampus.universiadi.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

import eu.trentorise.smartcampus.aac.AACException;
import eu.trentorise.smartcampus.territoryservice.TerritoryService;
import eu.trentorise.smartcampus.territoryservice.TerritoryServiceException;
import eu.trentorise.smartcampus.territoryservice.model.ObjectFilter;
import eu.trentorise.smartcampus.territoryservice.model.POIObject;
import eu.trentorise.smartcampus.universiadi.model.Domanda;
import eu.trentorise.smartcampus.universiadi.model.GPS;
import eu.trentorise.smartcampus.universiadi.util.EasyTokenManger;

@Controller("poiController")
public class POIController {

	private static final String SOURCE = "Universiadi 2013";

	@Autowired
	@Value("${juniper.address}")
	String url_juniper="https://smartcampus.eventbuilder.it/";
	
    public static final int HTTP_REQUEST_TIMEOUT_MS = 30 * 1000;
    
    @Autowired
	@Value("${services.server}")
    private String aacURL;
    @Autowired
	@Value("${territory.address}")
	private String territoryAddress;
    @Autowired
	@Value("${client.id.sc}")
	private String clientId;
    @Autowired
	@Value("${client.secret.sc}")
	private String clientSecret;
    @Autowired
	@Value("${client.token.j}")
    private String client_juniper_token;
  private EasyTokenManger tkm;
    
    @PostConstruct
    private void init(){
	tkm= new EasyTokenManger(aacURL,clientId,clientSecret,client_juniper_token);

    }

	@RequestMapping(method = RequestMethod.GET, value = "/poi/{type}")
	public @ResponseBody
	List<POIObject> getPoiForType(HttpServletRequest request,
			@PathVariable("type") String type, HttpServletResponse response,
			HttpSession session) throws TerritoryServiceException, AACException {

		TerritoryService territoryService = new TerritoryService(territoryAddress);
		ObjectFilter filter = new ObjectFilter();
		List<String> x=new ArrayList<String>();
		x.add(type);
		//filter.setTypes(x);
		filter.setText(type);
		Map<String,Object> map =new HashMap<String,Object>();
		map.put("source", SOURCE);
		
		filter.setCriteria(map);

		List<POIObject> pois = territoryService.getPOIs(filter ,tkm.getClientSmartCampusToken());
		return pois;
	}

	@RequestMapping(method = RequestMethod.POST, value = "/poi_evento")
	public @ResponseBody
	List<POIObject> getAtletiForEvento(HttpServletRequest request,
			HttpServletResponse response, HttpSession session,
			@RequestBody GPS poi) throws  IOException,
			TerritoryServiceException, AACException {

		TerritoryService territoryService = new TerritoryService(territoryAddress);
		ObjectFilter filter = new ObjectFilter();
		filter.setCenter(poi.getArray());
		Map<String,Object> map =new HashMap<String,Object>();
		map.put("source", SOURCE);
		
		filter.setCriteria(map);

		List<POIObject> pois = territoryService.getPOIs(filter ,tkm.getClientSmartCampusToken());
		return pois;
	}

	@RequestMapping(method = RequestMethod.POST, value = "/poi_evento/all")
	public @ResponseBody
	List<POIObject> getAllPoi(HttpServletRequest request,
			HttpServletResponse response, HttpSession session,
			@RequestBody GPS poi) throws IOException, TerritoryServiceException, AACException {
		
		
		TerritoryService territoryService = new TerritoryService(territoryAddress);
		ObjectFilter filter = new ObjectFilter();
		Map<String,Object> map =new HashMap<String,Object>();
		map.put("source", SOURCE);
		
		filter.setCriteria(map);

		List<POIObject> pois = territoryService.getPOIs(filter ,tkm.getClientSmartCampusToken());
		return pois;
	}
	
	@RequestMapping(method = RequestMethod.POST, value = "/poi/search")
	public @ResponseBody
	List<POIObject> searchPoi(HttpServletRequest request,
			HttpServletResponse response, HttpSession session,
			@RequestBody Domanda domanda) throws  IOException, TerritoryServiceException, AACException
			 {

	
		
		TerritoryService territoryService = new TerritoryService(territoryAddress);
		ObjectFilter filter = new ObjectFilter();
		filter.setText(domanda.getNome());
		Map<String,Object> map =new HashMap<String,Object>();
		map.put("source", SOURCE);
		
		filter.setCriteria(map);

		List<POIObject> pois = territoryService.getPOIs(filter ,tkm.getClientSmartCampusToken());
		return pois;

	}


	
}

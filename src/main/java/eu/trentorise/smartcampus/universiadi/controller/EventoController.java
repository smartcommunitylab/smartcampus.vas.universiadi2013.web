package eu.trentorise.smartcampus.universiadi.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

import eu.trentorise.smartcampus.aac.AACException;
import eu.trentorise.smartcampus.territoryservice.TerritoryService;
import eu.trentorise.smartcampus.territoryservice.TerritoryServiceException;
import eu.trentorise.smartcampus.territoryservice.model.EventObject;
import eu.trentorise.smartcampus.territoryservice.model.ObjectFilter;
import eu.trentorise.smartcampus.universiadi.model.Domanda;
import eu.trentorise.smartcampus.universiadi.util.EasyTokenManger;

@Controller("eventoController")
public class EventoController {
	
	private static final String SOURCE = "Universiadi 2013 - Events";
	private static final String SOURCE_2 = "Universiadi 2013 - Schedule";

	@Autowired
	@Value("${territory.address}")
	private String territoryAddress;

	@Autowired
	MongoTemplate db;
	
	
	@Autowired
	@Value("${juniper.address}")
	String url_juniper="https://smartcampus.eventbuilder.it/";
	
    public static final int HTTP_REQUEST_TIMEOUT_MS = 30 * 1000;
    
    @Autowired
	@Value("${services.server}")
    private String aacURL;
	
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
	

	@RequestMapping(method = RequestMethod.GET, value = "/evento/data/{data}")
	public @ResponseBody
	List<EventObject> getEventiPerData(HttpServletRequest request,
			@PathVariable("data") long data, HttpServletResponse response,
			HttpSession session) throws TerritoryServiceException, AACException {

		ObjectFilter filter = new ObjectFilter();
		filter.setLimit(10);
		
		TerritoryService territoryService = new TerritoryService(territoryAddress);
	

		Map<String,Object> map =new HashMap<String,Object>();
		String[] x=new String[2];
		x[0]=SOURCE;
		x[1]=SOURCE_2;
		map.put("source", x);
		
		Calendar c=Calendar.getInstance();
		c.setTimeInMillis(data);
		c.set(Calendar.HOUR_OF_DAY, 0);
		c.set(Calendar.MINUTE,0);
		c.set(Calendar.SECOND,0);
		c.set(Calendar.MILLISECOND, 0);
		
		
		filter.setCriteria(map);
		filter.setFromTime(c.getTimeInMillis());
		
		
		c.set(Calendar.HOUR_OF_DAY, 23);
		c.set(Calendar.MINUTE,59);
		c.set(Calendar.SECOND,59);

		
		
		filter.setToTime(c.getTimeInMillis());
		List<EventObject> events = territoryService.getEvents(filter, tkm.getClientSmartCampusToken());
	

		return events;
	}

	@RequestMapping(method = RequestMethod.GET, value = "/evento/sport/{sport}")
	public @ResponseBody
	List<EventObject> getEventiPerSport(HttpServletRequest request,
			@PathVariable("sport") String sport, HttpServletResponse response,
			HttpSession session) throws TerritoryServiceException, AACException {

		ObjectFilter filter = new ObjectFilter();
		filter.setLimit(10);
		
		TerritoryService territoryService = new TerritoryService(territoryAddress);
	
		Map<String,Object> map =new HashMap<String,Object>();
		String[] x=new String[2];
		x[0]=SOURCE;
		x[1]=SOURCE_2;
		map.put("source", x);
		
		
		filter.setCriteria(map);
		
		Map<String,String> mapMatch=new HashMap<String, String>();
		mapMatch.put("sci alpino","Alpine Skiing");
		mapMatch.put("sci","Ski Jumping");
		mapMatch.put("pattinaggio","Speed Skating");
		mapMatch.put("pattinaggio","Short Track Speed Skating");
		mapMatch.put("hockey","IceHockey");
		mapMatch.put("curling","Curling");
		mapMatch.put("biathlon","Biathlon");
		mapMatch.put("snowboard","Snowboarding");
		mapMatch.put("combinata nordica","Nordic Combined");
		mapMatch.put("freestyle","Freestyle Skiing");
		
		if(mapMatch.containsKey(sport)){
			List<String> list=new ArrayList();
			list.add(mapMatch.get(sport));
			filter.setTypes(list);
		}else{
			
			filter.setText(sport);
		}


		List<EventObject> events = territoryService.getEvents(filter, tkm.getClientSmartCampusToken());
	

		return events;
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/evento/all")
	public @ResponseBody
	List<EventObject> getEventiAll(HttpServletRequest request,HttpServletResponse response,
			HttpSession session) throws TerritoryServiceException, AACException {
		ObjectFilter filter = new ObjectFilter();
		filter.setLimit(10);
		
		TerritoryService territoryService = new TerritoryService(territoryAddress);
	

		Map<String,Object> map =new HashMap<String,Object>();
		String[] x=new String[2];
		x[0]=SOURCE;
		x[1]=SOURCE_2;
		map.put("source", x);
		

		List<EventObject> events = territoryService.getEvents(filter, tkm.getClientSmartCampusToken());
	

		return events;
	}

	
	@RequestMapping(method = RequestMethod.POST, value = "/evento/search")
	public @ResponseBody
	List<EventObject> searchEventi(HttpServletRequest request,
			HttpServletResponse response, HttpSession session,
			@RequestBody Domanda domanda) throws  IOException, TerritoryServiceException, AACException
			 {

		ObjectFilter filter = new ObjectFilter();
		filter.setLimit(10);
		filter.setText(domanda.getNome());
		
	
		
		TerritoryService territoryService = new TerritoryService(territoryAddress);
	

		Map<String,Object> map =new HashMap<String,Object>();
		String[] x=new String[2];
		x[0]=SOURCE;
		x[1]=SOURCE_2;
		map.put("source", x);
		
		filter.setCriteria(map);

		List<EventObject> events = territoryService.getEvents(filter, tkm.getClientSmartCampusToken());
	

		return events;

	}
}
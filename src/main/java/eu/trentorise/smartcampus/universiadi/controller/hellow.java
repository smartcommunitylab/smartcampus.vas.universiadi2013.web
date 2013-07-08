package eu.trentorise.smartcampus.universiadi.controller;

import it.sayservice.platform.smartplanner.data.message.Itinerary;
import it.sayservice.platform.smartplanner.data.message.Position;
import it.sayservice.platform.smartplanner.data.message.RType;
import it.sayservice.platform.smartplanner.data.message.TType;
import it.sayservice.platform.smartplanner.data.message.journey.SingleJourney;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import eu.trentorise.smartcampus.ac.provider.filters.AcProviderFilter;
import eu.trentorise.smartcampus.journeyplanner.JourneyPlannerConnector;
import eu.trentorise.smartcampus.universiadi.model.Evento;
import eu.trentorise.smartcampus.universiadi.model.UserObject;



@Controller("hellow")
public class hellow {
	
	private List<Evento> yep = new ArrayList<Evento>();
	private List<UserObject> ulist = new ArrayList<UserObject>();
	private UserObject us1 = new UserObject();
	private UserObject us2 = new UserObject();
	private Evento g = new Evento();
	private Evento v = new Evento();
	
	public hellow()
	{
		g.setId("1111");
		g.setNome("notte bianca");
		g.setData(Calendar.getInstance().getTimeInMillis());
		g.setDescrizione("la notte che tutti penzone");
		g.setGeo(45.282882, 11.8788787);
		g.setIndirizzo("Sommarive");
		g.setRuolo(2);
		g.setAmbito("mensa");
		
		v.setId("9999");
		v.setNome("YEP!_Party");
		v.setData(1372862264792L);
		v.setDescrizione("hofdos");
		v.setGeo(45.282772, 11.8785487);
		v.setIndirizzo("Cascata");
		v.setRuolo(1);
		v.setAmbito("cucina");
		
		us1.setNome("Jonny");
		us1.setCognome("Jupiter");
		us1.setNumeroTelefonico("32131213");
		us1.setRuolo(2);
		us1.setBadgeCode(1231L);
		us1.setAmbito("fabbrica di GiuseppeSimone");
		
		us2.setNome("Cordata");
		us2.setCognome("Penzo");
		us2.setNumeroTelefonico("0912081");
		us2.setRuolo(1);
		us2.setBadgeCode(1281L);
		us2.setAmbito("Falegnameria");
		
		
		ulist.add(us1);
		ulist.add(us2);
		yep.add(g);
		yep.add(v);
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/evento")
	public @ResponseBody
	List<Evento> getEventi(HttpServletRequest request,
			HttpServletResponse response, HttpSession session)
			{
		return yep;
	}
		

	@RequestMapping(method = RequestMethod.GET, value = "/evento/id/{id}")
	public @ResponseBody
	List<Evento> getEventoFromId(HttpServletRequest request,
			@PathVariable("id") String id,
			HttpServletResponse response, HttpSession session)
			{
		Integer i;
		Evento apg;
		List<Evento> evappl = new ArrayList<Evento>();
		int g = yep.size();
		for(i=0;i<g;i++){
			
			apg = yep.get(i);
			
			if(apg.getId()==id){
				evappl.add(apg);
			}
			
		}
		
		return evappl;
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/evento/data/{data}")
	public @ResponseBody
	List<Evento> getEventoFromData(HttpServletRequest request,
			@PathVariable("data") long data,
			HttpServletResponse response, HttpSession session)
			{
		
		Integer i;
		Evento apg;
		List<Evento> evappl = new ArrayList<Evento>();
		int g = yep.size();
		for(i=0;i<g;i++){
			
			apg = yep.get(i);
			
			if(apg.getData()==data){
				evappl.add(apg);
			}
			
		}
		
		return evappl;
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/user/{tok}")
	public @ResponseBody
	List<UserObject> getUserData(HttpServletRequest request,
			@PathVariable("tok") Long tok,
			HttpServletResponse response, HttpSession session)
			{
		Integer i;
		UserObject apg;
		List<UserObject> evappl = new ArrayList<UserObject>();
		int g = ulist.size();
		for(i=0;i<g;i++){
			
			apg = ulist.get(i);
			
			if(apg.getBadgeCode()==tok){
				evappl.add(apg);
			}
			
		}
		
		return evappl;
	}
	
	
	
	
	
	
}
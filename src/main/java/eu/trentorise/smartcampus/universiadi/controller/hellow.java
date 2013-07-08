package eu.trentorise.smartcampus.universiadi.controller;

import java.awt.Event;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Random;

import javax.annotation.PostConstruct;
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
	
	
	public hellow()
	{
		for (int i=0; i<200; i++)
		{
			Evento e1 = new Evento();
			e1.setID(i);
			e1.setNome("Evento "+i);
			e1.setData(400);
			e1.setDescrizione("Descrizione "+i);
			e1.setGeo(45.282882, 11.8788787);
			e1.setIndirizzo("Indirizzo "+i);
			if (i < 100 && i % 2 == 0)
			{
				e1.setTipoSport("Skating");
				e1.setRuolo(0);
				e1.setAmbito("Ambito 0");
			}
			else if (i >= 100 && i % 2 == 0)
			{
				e1.setTipoSport("Curling");
				e1.setRuolo(1);
				e1.setAmbito("Ambito 1");
			}
			else
			{
				e1.setTipoSport("Snowboard");
				e1.setRuolo(-1);
				e1.setAmbito("");
			}
			
			yep.add(e1);
		}
	}
	
	
	@RequestMapping(method = RequestMethod.GET, value = "/evento")
	public @ResponseBody
	List<Evento> getEventi(HttpServletRequest request,
			HttpServletResponse response, HttpSession session)
			{
		
		int l;
		Evento evnt = new Evento();
		List<Evento> evappl = new ArrayList<Evento>();
		
		
		  for(l=0;l<yep.size();l++){
			  
			  evnt = yep.get(l);
			  
			if(evnt.getRuolo()==-1 && evnt.getAmbito().equals("")){
				evappl.add(evnt);
			}
			
		  }
		  
		return evappl;
	}
		
	@RequestMapping(method = RequestMethod.GET, value = "/user")
	public @ResponseBody
	List<UserObject> getAllUsers(HttpServletRequest request, HttpServletResponse response, HttpSession session)
	{
	
	return ulist;
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/evento/id/{id}")
	public @ResponseBody
	List<Evento> getEventoFromId(HttpServletRequest request,
			@PathVariable("id") Long id,
			HttpServletResponse response, HttpSession session)
			{
		int i;
		Evento apg;
		List<Evento> evappl = new ArrayList<Evento>();
		int g = yep.size();
		for(i=0;i<g;i++){
			
			apg = yep.get(i);
			
			if(apg.getID()==id){
				evappl.add(apg);
			}
			
		}
		
		return evappl;
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/evento/{user_ambito}/{user_ruolo}/{data}")
	public @ResponseBody
	List<Evento> getEventi(HttpServletRequest request,
			@PathVariable("data") long data,
			@PathVariable("user_ambito") String user_ambito,
			@PathVariable("user_ruolo") int user_ruolo,
			HttpServletResponse response, HttpSession session)
			{
		
		int i;
		Evento evnt = new Evento();
		List<Evento> evappl = new ArrayList<Evento>();
		
		
		  for(i=0;i<yep.size();i++){
			  
			  evnt = yep.get(i);
			  
			if(evnt.getData()==data && evnt.getAmbito().equals(user_ambito) && evnt.getRuolo()<=user_ruolo){
				evappl.add(evnt);
			}
			
		  }
		  
		return evappl;
	}
	

	@RequestMapping(method = RequestMethod.GET, value = "/evento/{user_ambito}/{user_ruolo}")
	public @ResponseBody
	List<Evento> getEventi(HttpServletRequest request,
			@PathVariable("user_ambito") String user_ambito,
			@PathVariable("user_ruolo") int user_ruolo,
			HttpServletResponse response, HttpSession session)
			{
		
		int k;
		Evento evnt = new Evento();
		List<Evento> evappl = new ArrayList<Evento>();
		
		
		  for(k=0;k<yep.size();k++){
			  
			  evnt = yep.get(k);
			  
			if(evnt.getAmbito().equals(user_ambito) && evnt.getRuolo()<=user_ruolo){
				evappl.add(evnt);
			}
			
		  }
		  
		return evappl;
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/evento/{data}")
	public @ResponseBody
	List<Evento> getEventi(HttpServletRequest request,
			@PathVariable("data") long data,
			HttpServletResponse response, HttpSession session)
			{
		
		Integer i;
		Evento evnt = new Evento();
		List<Evento> evappl = new ArrayList<Evento>();
		
		
		  for(i=0;i<yep.size();i++){
			  
			  evnt = yep.get(i);
			  
			if(evnt.getData()==data && evnt.getRuolo()==-1){
				evappl.add(evnt);
			}
			
		  }
		  
		return evappl;
	}
	
	
	
//	@RequestMapping(method = RequestMethod.GET, value = "/user/{tok}")
//	public @ResponseBody
//	List<UserObject> getUserData(HttpServletRequest request,
//			@PathVariable("tok") String tok,
//			HttpServletResponse response, HttpSession session)
//			{	
//		Integer i;
//		UserObject apg;
//		List<UserObject> evappl = new ArrayList<UserObject>();
//		int g = ulist.size();
//		for(i=0;i<g;i++){
//			
//			apg = ulist.get(i);
//			
//			if(apg.getBadgeCode()==tok){
//				evappl.add(apg);
//			}
//			
//		}
//		
//		return evappl;
//	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/evento/sport/{sport}")
	public @ResponseBody
	List<Evento> getEventiPerSport(HttpServletRequest request,
			@PathVariable("sport") String sport,
			HttpServletResponse response, HttpSession session)
			{
			int i;
			List<Evento> evappl = new ArrayList<Evento>();
			Evento evn;
			int siz = yep.size();
			for(i=0;i<siz;i++){
				
				evn = yep.get(i);
				
				if(evn.getTipoSport().equals(sport)){
					evappl.add(evn);
				}
			}
		return	evappl;
	}
	
	
			
	
}
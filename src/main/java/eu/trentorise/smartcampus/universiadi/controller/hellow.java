package eu.trentorise.smartcampus.universiadi.controller;

import it.sayservice.platform.smartplanner.data.message.Itinerary;
import it.sayservice.platform.smartplanner.data.message.Position;
import it.sayservice.platform.smartplanner.data.message.RType;
import it.sayservice.platform.smartplanner.data.message.TType;
import it.sayservice.platform.smartplanner.data.message.journey.SingleJourney;

import java.io.IOException;
import java.util.ArrayList;
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


@Controller("hellow")
public class hellow {
	
	@RequestMapping(method = RequestMethod.GET, value = "/evento")
	public @ResponseBody
	List<Evento> getEventi(HttpServletRequest request,
			HttpServletResponse response, HttpSession session)
			{
		List<Evento> yep = new ArrayList<Evento>();
		Evento g = new Evento();
		Evento v = new Evento();
		g.setId(1111);
		g.setNome("notte bianca");
		g.setData("12/02/2013");
		g.setDescrizione("la notte che tutti penzone");
		
		v.setId(9999);
		v.setNome("YEP!_Party");
		v.setData("29/06/2013");
		v.setDescrizione("hofdos");
		yep.add(g);
		yep.add(v);
		
		
		return yep;
	}
		

	@RequestMapping(method = RequestMethod.GET, value = "/evento/{id}")
	public @ResponseBody
	Evento getEventoFromId(HttpServletRequest request,
			@PathVariable("id") Long id,
			HttpServletResponse response, HttpSession session)
			{
		
		Evento g = new Evento();
		g.setId(id);
		g.setNome("notte biancaaa");
		g.setData("12/02/2013");
		g.setDescrizione("la notte che tutti penzone");
		
		
		return g;
	}
}
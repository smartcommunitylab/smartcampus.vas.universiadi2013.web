package eu.trentorise.smartcampus.universiadi.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import eu.trentorise.smartcampus.presentation.common.exception.DataException;
import eu.trentorise.smartcampus.presentation.common.exception.NotFoundException;
import eu.trentorise.smartcampus.universiadi.model.TicketObj;
import eu.trentorise.smartcampus.universiadi.model.UserObj;
import eu.trentorise.smartcampus.universiadi.model.containerData.ContainerUtenti;

@Controller("risolutoreController")
public class RisolutoreController {

	@PostConstruct
	public void init() {
	}

	@RequestMapping(method = RequestMethod.GET, value = "/categoria_volontari")
	public @ResponseBody
	ArrayList<String> getCategorieVolontari(HttpServletRequest request,
			HttpServletResponse response, HttpSession session) {

		

		ArrayList<String> mCategorie = new ArrayList<String>();
		for (UserObj utente : ContainerUtenti.getUtenti())
			if (!mCategorie.contains(utente.getAmbito()))
				mCategorie.add(utente.getAmbito());

		return mCategorie;
	}

	@RequestMapping(method = RequestMethod.GET, value = "/utenti/{ambito}/{ruolo}")
	public @ResponseBody
	ArrayList<UserObj> getCategorieVolontari(HttpServletRequest request,
			@PathVariable("ambito") String ambito,
			@PathVariable("ruolo") String ruolo, HttpServletResponse response,
			HttpSession session) {

		

		return ContainerUtenti.getUtentiForAmbitoAndRuolo(ambito, ruolo);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/send_sms")
	public @ResponseBody
	boolean sendSMS(HttpServletRequest request, HttpServletResponse response,
			HttpSession session, @RequestBody TicketObj ticket,
			@RequestBody ArrayList<UserObj> receiver) throws DataException,
			IOException, NotFoundException {
		return true;
	}

	@RequestMapping(method = RequestMethod.POST, value = "/send_helpdesk")
	public @ResponseBody
	boolean sendHelpdesk(HttpServletRequest request,
			HttpServletResponse response, HttpSession session,
			@RequestBody TicketObj ticket) throws DataException, IOException,
			NotFoundException {
		return true;
	}

}

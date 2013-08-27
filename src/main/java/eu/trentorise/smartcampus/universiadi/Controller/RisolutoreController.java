package eu.trentorise.smartcampus.universiadi.Controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import eu.trentorise.smartcampus.ac.provider.filters.AcProviderFilter;
import eu.trentorise.smartcampus.presentation.common.exception.DataException;
import eu.trentorise.smartcampus.presentation.common.exception.NotFoundException;
import eu.trentorise.smartcampus.universiadi.Model.AtletObj;
import eu.trentorise.smartcampus.universiadi.Model.EventObj;
import eu.trentorise.smartcampus.universiadi.Model.TicketObj;
import eu.trentorise.smartcampus.universiadi.Model.UserObj;
import eu.trentorise.smartcampus.universiadi.Model.ContainerData.ContainerUtenti;

public class RisolutoreController {

	@PostConstruct
	public void init() {
	}

	@RequestMapping(method = RequestMethod.GET, value = "/categoria_volontari")
	public @ResponseBody
	ArrayList<String> getCategorieVolontari(HttpServletRequest request,
			HttpServletResponse response, HttpSession session) {

		String token = request.getHeader(AcProviderFilter.TOKEN_HEADER);

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

		String token = request.getHeader(AcProviderFilter.TOKEN_HEADER);

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

package eu.trentorise.smartcampus.universiadi.controller;

import java.util.ArrayList;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import eu.trentorise.smartcampus.universiadi.model.SportObj;
import eu.trentorise.smartcampus.universiadi.model.containerData.ContainerSport;

@Controller("sportController")
public class SportController {

	ArrayList<SportObj> mListaAllSport = new ArrayList<SportObj>();

	@PostConstruct
	public void init() {
		mListaAllSport = ContainerSport.getSport();
	}

	@RequestMapping(method = RequestMethod.GET, value = "/sport")
	public @ResponseBody
	ArrayList<SportObj> getAllSport(HttpServletRequest request,
			HttpServletResponse response, HttpSession session) {

		return mListaAllSport;
	}

}
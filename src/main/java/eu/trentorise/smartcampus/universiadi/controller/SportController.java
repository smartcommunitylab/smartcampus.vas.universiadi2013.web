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

import eu.trentorise.smartcampus.ac.provider.filters.AcProviderFilter;
import eu.trentorise.smartcampus.presentation.common.exception.DataException;
import eu.trentorise.smartcampus.presentation.common.exception.NotFoundException;
import eu.trentorise.smartcampus.universiadi.model.EventObj;
import eu.trentorise.smartcampus.universiadi.model.GeoPoint;
import eu.trentorise.smartcampus.universiadi.model.POIObj;
import eu.trentorise.smartcampus.universiadi.model.SportObj;
import eu.trentorise.smartcampus.universiadi.model.ContainerData.ContainerPOI;
import eu.trentorise.smartcampus.universiadi.model.ContainerData.ContainerSport;

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
				HttpServletResponse response,
				HttpSession session) {

			

			return mListaAllSport;
		}

	

	}
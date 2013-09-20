package eu.trentorise.smartcampus.universiadi.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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
import eu.trentorise.smartcampus.territoryservice.TerritoryService;
import eu.trentorise.smartcampus.territoryservice.TerritoryServiceException;
import eu.trentorise.smartcampus.territoryservice.model.ObjectFilter;
import eu.trentorise.smartcampus.territoryservice.model.POIObject;
import eu.trentorise.smartcampus.universiadi.model.EventObj;
import eu.trentorise.smartcampus.universiadi.model.GeoPoint;
import eu.trentorise.smartcampus.universiadi.model.POIObj;
import eu.trentorise.smartcampus.universiadi.model.containerData.ContainerPOI;

@Controller("poiController")
public class POIController {

	
	private TerritoryService territoryService = new TerritoryService(
			"https://vas-dev.smartcampuslab.it/core.territory");
	private ArrayList<POIObj> mListaPOI;

	@PostConstruct
	public void init() {
		mListaPOI = ContainerPOI.getPOI();
	}

	@RequestMapping(method = RequestMethod.GET, value = "/poi/{type}")
	public @ResponseBody
	ArrayList<POIObj> getPoiForType(HttpServletRequest request,
			@PathVariable("type") String type, HttpServletResponse response,
			HttpSession session) {

		String token = request.getHeader(AcProviderFilter.TOKEN_HEADER);

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
		ObjectFilter filter = new ObjectFilter();
		filter.setTypes(Arrays.asList(new String[]{"universiadi13 - Venues"}));
		List<POIObject> pois = territoryService.getPOIs(filter ,"token");
		return pois;
	}

	
	
	
	
}

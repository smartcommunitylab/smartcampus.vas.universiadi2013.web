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
import eu.trentorise.smartcampus.universiadi.Model.EventObj;
import eu.trentorise.smartcampus.universiadi.Model.GeoPoint;
import eu.trentorise.smartcampus.universiadi.Model.POIObj;
import eu.trentorise.smartcampus.universiadi.Model.ContainerData.ContainerPOI;

public class POIController {

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
	ArrayList<EventObj> getPOIForEvento(HttpServletRequest request,
			HttpServletResponse response, HttpSession session,
			@RequestBody GeoPoint poi) throws DataException, IOException,
			NotFoundException {
		for (POIObj obj : mListaPOI) {
			if (obj.getGPS().compareTo(poi) == 0)
				return obj.getEvento();
		}
		return null;
	}

}

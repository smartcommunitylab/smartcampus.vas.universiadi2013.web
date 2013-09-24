package eu.trentorise.smartcampus.universiadi.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import eu.trentorise.smartcampus.universiadi.model.TurnoObj;
import eu.trentorise.smartcampus.universiadi.model.UserObj;
import eu.trentorise.smartcampus.universiadi.model.containerData.ContainerUtenti;

@Controller("CalendarioController")
public class CalendarioController {

	// Data nel formato dd/mm/yyyy
	@RequestMapping(method = RequestMethod.GET, value = "/turni/{data}/{luogo}/{categoria}")
	public @ResponseBody
	ArrayList<TurnoObj> getTurniForDataAndLuogoAndCategoria(
			HttpServletRequest request, @PathVariable("data") long data,
			@PathVariable("luogo") String luogo,
			@PathVariable("categoria") String categoria,
			HttpServletResponse response, HttpSession session) {

		
		ArrayList<UserObj> mListaUtenti = ContainerUtenti
				.getUtentiForAmbitoAndRuolo(
						(categoria.equalsIgnoreCase("\"\"")) ? null : categoria,
						"4");
		ArrayList<TurnoObj> mResult = new ArrayList<TurnoObj>();
		for (UserObj utente : mListaUtenti) {
			ArrayList<TurnoObj> mListaTurniPerUtente = utente.getListaTurni();
			if (!luogo.equalsIgnoreCase("\"\"")
					&& !categoria.equalsIgnoreCase("\"\"")) {
				for (TurnoObj turno : mListaTurniPerUtente) {
					if (turno.getCategoria().equals(categoria)
							&& turno.getLuogo().equals(luogo)
							&& new SimpleDateFormat("dd/MM/yyyy").format(
									turno.getData()).equalsIgnoreCase(
									new SimpleDateFormat("dd/MM/yyyy")
											.format(data)))
						mResult.add(turno);
				}
			} else if (!categoria.equalsIgnoreCase("\"\"")) {
				for (TurnoObj turno : mListaTurniPerUtente) {
					if (turno.getCategoria().equals(categoria)
							&& new SimpleDateFormat("dd/MM/yyyy").format(
									turno.getData()).equalsIgnoreCase(
									new SimpleDateFormat("dd/MM/yyyy")
											.format(data)))
						mResult.add(turno);
				}
			} else if (!luogo.equalsIgnoreCase("\"\"")) {
				for (TurnoObj turno : mListaTurniPerUtente) {
					if (turno.getLuogo().equals(luogo)
							&& new SimpleDateFormat("dd/MM/yyyy").format(
									turno.getData()).equalsIgnoreCase(
									new SimpleDateFormat("dd/MM/yyyy")
											.format(data)))
						mResult.add(turno);
				}
			} else {
				for (TurnoObj turno : mListaTurniPerUtente) {
					if (new SimpleDateFormat("dd/MM/yyyy").format(
							turno.getData()).equalsIgnoreCase(
							new SimpleDateFormat("dd/MM/yyyy").format(data)))
						mResult.add(turno);
				}
			}
		}

		return mResult;
	}
}

package eu.trentorise.smartcampus.universiadi.Controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import eu.trentorise.smartcampus.ac.provider.filters.AcProviderFilter;
import eu.trentorise.smartcampus.universiadi.Model.TurnoObj;
import eu.trentorise.smartcampus.universiadi.Model.UserObj;
import eu.trentorise.smartcampus.universiadi.Model.ContainerData.ContainerUtenti;

public class CalendarioController {

	// Data nel formato dd/mm/yyyy
	@RequestMapping(method = RequestMethod.GET, value = "/turni/{data}/{luogo}/{categoria}")
	public @ResponseBody
	ArrayList<TurnoObj> getTurniForDataAndLuogoAndCategoria(
			HttpServletRequest request, @PathVariable("data") long data,
			@PathVariable("luogo") String luogo,
			@PathVariable("categoria") String ambito,
			HttpServletResponse response, HttpSession session) {

		String token = request.getHeader(AcProviderFilter.TOKEN_HEADER);
		ArrayList<TurnoObj> mResult = new ArrayList<TurnoObj>();

		if (!luogo.equalsIgnoreCase("") && !ambito.equalsIgnoreCase("")) {
			ArrayList<UserObj> mListaUtenti = ContainerUtenti
					.getUtentiForAmbitoAndRuolo(ambito, "4");
			for (UserObj utente : mListaUtenti) {
				ArrayList<TurnoObj> mListaTurniPerUtente = utente
						.getListaTurni();
				for (TurnoObj turno : mListaTurniPerUtente) {
					if (turno.getLuogo().equals(luogo)
							&& new SimpleDateFormat("dd/MM/yyyy").format(
									turno.getData()).equalsIgnoreCase(
									new SimpleDateFormat("dd/MM/yyyy")
											.format(data)))
						mResult.add(turno);
				}
			}
		} else if (!luogo.equalsIgnoreCase("")) {
			ArrayList<UserObj> mListaUtenti = ContainerUtenti
					.getUtentiForAmbitoAndRuolo(ambito, "4");
			for (UserObj utente : mListaUtenti) {
				ArrayList<TurnoObj> mListaTurniPerUtente = utente
						.getListaTurni();
				for (TurnoObj turno : mListaTurniPerUtente) {
					if (new SimpleDateFormat("dd/MM/yyyy").format(
							turno.getData()).equalsIgnoreCase(
							new SimpleDateFormat("dd/MM/yyyy").format(data)))
						mResult.add(turno);
				}
			}
		} else if (!ambito.equalsIgnoreCase("")) {
			ArrayList<UserObj> mListaUtenti = ContainerUtenti
					.getUtentiForAmbitoAndRuolo("", "4");
			for (UserObj utente : mListaUtenti) {
				ArrayList<TurnoObj> mListaTurniPerUtente = utente
						.getListaTurni();
				for (TurnoObj turno : mListaTurniPerUtente) {
					if (new SimpleDateFormat("dd/MM/yyyy").format(
							turno.getData()).equalsIgnoreCase(
							new SimpleDateFormat("dd/MM/yyyy").format(data)))
						mResult.add(turno);
				}
			}
		} else {
			ArrayList<UserObj> mListaUtenti = ContainerUtenti
					.getUtentiForAmbitoAndRuolo("", "");
			for (UserObj utente : mListaUtenti) {
				ArrayList<TurnoObj> mListaTurniPerUtente = utente
						.getListaTurni();
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

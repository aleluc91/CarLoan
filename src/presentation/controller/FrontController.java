package presentation.controller;

import java.util.List;

import business.applicationservice.transfer.Valori;

public class FrontController {

	public Object processaRichiesta(String nomeServizio, List<Valori> valori) {
		ApplicationController ac = new ApplicationController();
		return ac.gestisciRichiesta(nomeServizio, valori);
	}

}

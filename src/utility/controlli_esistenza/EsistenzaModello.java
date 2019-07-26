package utility.controlli_esistenza;

import java.util.ArrayList;
import java.util.List;

import entity.Modello;
import presentation.controller.FrontController;
import javafx.scene.control.Label;
import business.applicationservice.transfer.Valori;

public class EsistenzaModello extends InDatabase<Modello> {

	private static final String RICHIESTA = "leggiModello";
	private static final String METODO = "getModello";
	private String fascia;

	public EsistenzaModello() {
		super(RICHIESTA, METODO);
	}

	@SuppressWarnings("unchecked")
	protected boolean verifica(String ricerca, Label labelErrore) {
		FrontController fc = new FrontController();
		boolean errore = false;
		List<Valori> valori = new ArrayList<Valori>();
		valori.add(new Valori(ricerca));
		List<Modello> lista = (ArrayList<Modello>) fc.processaRichiesta(
				richiesta, null);
		if (lista != null) {
			for (Modello modello : lista) {
				if (modello.getModello().equalsIgnoreCase(ricerca)
						&& modello.getFascia().equalsIgnoreCase(fascia))
					errore = true;
			}
		}
		if (errore == true) {
			labelErrore
					.setText("Valore già presente all'interno del database!");
			return true;
		} else {
			labelErrore.setText("");
			return false;
		}
	}

	@Override
	public boolean esistenza(String modello, Label labelErrore) {
		// TODO Auto-generated method stub
		return verifica(modello, labelErrore);
	}

	public boolean esistenza(String modello, String fascia, Label labelErrore) {
		this.fascia = fascia;
		return this.verifica(modello, labelErrore);
	}

}

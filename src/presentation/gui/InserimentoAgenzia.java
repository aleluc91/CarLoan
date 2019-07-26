package presentation.gui;

import java.util.List;
import java.util.ArrayList;

import entity.Autenticazione;
import presentation.controller.FrontController;
import presentation.gui.control.ControllerInserimentoAgenzia;
import presentation.gui.modelli_tabella.ModelloAgenzia;
import business.applicationservice.transfer.Valori;

class InserimentoAgenzia extends
		SchermataModificabile<ControllerInserimentoAgenzia> {

	private static final String PERCORSO_FXML = "fxml/InserimentoAgenzia.fxml";
	private static final String TITOLO1 = "Inserimento Agenzia";
	private static final String TITOLO2 = "Modifica Agenzia";

	public InserimentoAgenzia() {
		super(PERCORSO_FXML, TITOLO1);
	}

	public InserimentoAgenzia(List<Valori> dati) {
		super(PERCORSO_FXML, TITOLO2);
		inizializza(controller, dati);
		controller.isModifica();
	}

	@SuppressWarnings("unchecked")
	@Override
	protected void inizializza(ControllerInserimentoAgenzia controller,
			List<Valori> valori) {
		// TODO Auto-generated method stub
		ModelloAgenzia dati = (ModelloAgenzia) valori.get(0).getModello();
		FrontController fc = new FrontController();
		List<Autenticazione> lista = (ArrayList<Autenticazione>) fc
				.processaRichiesta("leggiAutenticazione", null);
		if (lista != null) {
			String codiceAgenzia = dati.getCodice();
			for (Autenticazione autenticazione : lista) {
				if (autenticazione.getAgenzia() != null) {
					if (autenticazione.getAgenzia().equalsIgnoreCase(
							codiceAgenzia)) {
						controller.username = autenticazione.getUsername();
						controller.password = autenticazione.getPassword();
					}
				}
			}
			controller.setModello(dati);
		}

	}

}

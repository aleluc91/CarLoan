package presentation.gui;

import java.util.List;

import presentation.gui.control.ControllerInserimentoMacchina;
import presentation.gui.modelli_tabella.ModelloMacchina;
import business.applicationservice.transfer.Valori;

public class InserimentoMacchina extends
		SchermataModificabile<ControllerInserimentoMacchina> {

	private static final String PERCORSO_FXML = "fxml/InserimentoMacchina.fxml";
	private static final String TITOLO1 = "Inserimento Macchina";
	private static final String TITOLO2 = "Modifica Macchina";

	public InserimentoMacchina() {
		super(PERCORSO_FXML, TITOLO1);
	}

	public InserimentoMacchina(List<Valori> valori) {
		super(PERCORSO_FXML, TITOLO2);
		inizializza(controller, valori);
		controller.isModifica();
	}

	@Override
	protected void inizializza(ControllerInserimentoMacchina controller,
			List<Valori> valori) {
		ModelloMacchina dati = (ModelloMacchina) valori.get(0).getModello();
		controller.setTarga(dati.getTarga());
		controller.setFascia(dati.getFascia());
		controller.setModello(dati.getModello());
		controller.setAgenzia(dati.getAgenzia());
		controller.setNoleggiata(dati.getNoleggio());
		controller.setManutenzione(dati.getManutenzione());
		controller.setChilometraggio(dati.getChilometraggio());
	}

}

package presentation.gui;

import java.util.List;

import presentation.gui.control.ControllerInserimentoTariffa;
import presentation.gui.modelli_tabella.ModelloTariffa;
import business.applicationservice.transfer.Valori;

public class InserimentoTariffa extends
		SchermataModificabile<ControllerInserimentoTariffa> {

	private static final String PERCORSO_FXML = "fxml/InserimentoTariffa.fxml";
	private static final String TITOLO1 = "Inserimento Tariffa";
	private static final String TITOLO2 = "Modifica Tariffa";

	public InserimentoTariffa() {
		super(PERCORSO_FXML, TITOLO1);
	}

	public InserimentoTariffa(List<Valori> valori) {
		super(PERCORSO_FXML, TITOLO2);
		inizializza(controller, valori);
		controller.isModifica();
	}

	@Override
	protected void inizializza(ControllerInserimentoTariffa controller,
			List<Valori> valori) {
		ModelloTariffa dati = (ModelloTariffa) valori.get(0).getModello();
		controller.setId(dati.getId());
		controller.setTipo(dati.getTipo());
		controller.setChilometri(dati.getChilometri());
		controller.setGiorni(dati.getGiorni());
		controller.setTariffa(dati.getTariffa());
	}

}

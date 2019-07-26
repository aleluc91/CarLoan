package presentation.gui.control;

import java.util.List;

import javafx.scene.Node;
import javafx.stage.Stage;
import presentation.controller.FrontController;
import utility.Messaggi;
import business.applicationservice.transfer.Valori;

abstract class ControllerModificabile {

	private Node root;

	protected boolean modifica = false;

	protected void setRoot(Node root) {
		this.root = root;
	}

	private Stage getStage(Node root) {
		return (Stage) root.getScene().getWindow();
	}

	protected void chiudi(Node root) {
		getStage(root).close();
	}

	protected boolean getModifica() {
		return modifica;
	}

	protected void aggiungi(String richiesta, List<Valori> valori) {
		FrontController fc = new FrontController();
		if ((boolean) fc.processaRichiesta(richiesta, valori)) {
			if (Messaggi.msgConferma())
				chiudi(root);
		} else {
			if (Messaggi.msgErrore())
				chiudi(root);
		}
	}

	abstract public void isModifica();

}

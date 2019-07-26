package utility.validazione;

import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.TextArea;

/**
 * Classe astratta che
 * 
 * @author Alessio Lucarella , Valentino Arcuri
 *
 * @param <T>
 */
public abstract class Validazione<T> {

	abstract public boolean valida(T campoDiTesto, Label etichettaErrore);

	protected boolean setMessaggio(Label etichettaErrore) {
		if (Controlli.getErrore()) {
			etichettaErrore.setText(Controlli.getMessaggioErrore());
			return true;
		} else {
			etichettaErrore.setText("");
			return false;
		}
	}

	protected String getValore(TextField campoDiTesto) {
		return campoDiTesto.getText();
	}

	protected String getValore(TextArea campoDiTesto) {
		return campoDiTesto.getText();
	}

}

package utility.validazione;

import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class ValidazioneIndirizzo extends Validazione<TextField> {

	private final int MIN_INDIRIZZO = 2;
	private final int MAX_INDIRIZZO = 20;

	@Override
	public boolean valida(TextField campoDiTesto, Label etichettaErrore) {
		// TODO Auto-generated method stub
		String valore = campoDiTesto.getText();
		Controlli.isAlfanumerico_Spazi(valore, MIN_INDIRIZZO, MAX_INDIRIZZO);
		return setMessaggio(etichettaErrore);
	}

}

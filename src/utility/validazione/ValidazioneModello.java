package utility.validazione;

import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class ValidazioneModello extends Validazione<TextField> {

	private final int MIN_MODELLO = 4;
	private final int MAX_MODELLO = 15;

	@Override
	public boolean valida(TextField campoDiTesto, Label etichettaErrore) {
		// TODO Auto-generated method stub
		Controlli.isAlfanumerico_Spazi(getValore(campoDiTesto), MIN_MODELLO,
				MAX_MODELLO);
		return setMessaggio(etichettaErrore);
	}

}

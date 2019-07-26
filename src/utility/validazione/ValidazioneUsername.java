package utility.validazione;

import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class ValidazioneUsername extends Validazione<TextField> {

	private final int MIN_USERNAME = 4;
	private final int MAX_USERNAME = 15;

	@Override
	public boolean valida(TextField campoDiTesto, Label etichettaErrore) {
		// TODO Auto-generated method stub
		Controlli.isAlfanumerico(getValore(campoDiTesto), MIN_USERNAME,
				MAX_USERNAME);
		return setMessaggio(etichettaErrore);
	}

}

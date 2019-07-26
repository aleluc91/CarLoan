package utility.validazione;

import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class ValidazioneIdentificativo extends Validazione<TextField> {

	private final int MIN_IDENTIFICATIVO = 1;

	@Override
	public boolean valida(TextField campoDiTesto, Label etichettaErrore) {
		// TODO Auto-generated method stub
		Controlli.isAlfabetico(getValore(campoDiTesto), MIN_IDENTIFICATIVO,
				MIN_IDENTIFICATIVO);
		return setMessaggio(etichettaErrore);
	}

}

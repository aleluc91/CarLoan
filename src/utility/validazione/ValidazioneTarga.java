package utility.validazione;

import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class ValidazioneTarga extends Validazione<TextField> {

	private final int MIN_TARGA = 7;
	private final int MAX_TARGA = 7;

	@Override
	public boolean valida(TextField campoDiTesto, Label etichettaErrore) {
		// TODO Auto-generated method stub
		Controlli.isTarga(getValore(campoDiTesto), MIN_TARGA, MAX_TARGA);
		return setMessaggio(etichettaErrore);
	}

}

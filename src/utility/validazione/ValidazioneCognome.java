package utility.validazione;

import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class ValidazioneCognome extends Validazione<TextField> {

	private final int MIN_COGNOME = 4;
	private final int MAX_COGNOME = 15;

	@Override
	public boolean valida(TextField campoDiTesto, Label etichettaErrore) {
		// TODO Auto-generated method stub
		Controlli.isAlfabetico(getValore(campoDiTesto), MIN_COGNOME,
				MAX_COGNOME);
		return setMessaggio(etichettaErrore);
	}

}

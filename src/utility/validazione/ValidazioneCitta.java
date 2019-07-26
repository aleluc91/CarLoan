package utility.validazione;

import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class ValidazioneCitta extends Validazione<TextField> {

	private final int MIN_CITTA = 2;
	private final int MAX_CITTA = 15;

	@Override
	public boolean valida(TextField campoDiTesto, Label etichettaErrore) {
		// TODO Auto-generated method stub
		Controlli.isAlfabetico_Spazi(getValore(campoDiTesto), MIN_CITTA,
				MAX_CITTA);
		return setMessaggio(etichettaErrore);
	}

}

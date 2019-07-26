package utility.validazione;

import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class ValidazioneTipo extends Validazione<TextField> {

	private final int MIN_TIPO = 5;
	private final int MAX_TIPO = 30;

	@Override
	public boolean valida(TextField campoDiTesto, Label etichettaErrore) {
		// TODO Auto-generated method stub
		Controlli.isAlfabetico_Spazi(getValore(campoDiTesto), MIN_TIPO,
				MAX_TIPO);
		return setMessaggio(etichettaErrore);
	}

}

package utility.validazione;

import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class ValidazioneTelefono extends Validazione<TextField> {

	private final int MIN_TELEFONO = 10;

	@Override
	public boolean valida(TextField campoDiTesto, Label etichettaErrore) {
		// TODO Auto-generated method stub
		Controlli.isNumeroTelefonico(getValore(campoDiTesto), MIN_TELEFONO);
		return setMessaggio(etichettaErrore);
	}

}

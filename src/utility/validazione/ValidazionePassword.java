package utility.validazione;

import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;

public class ValidazionePassword extends Validazione<PasswordField> {

	private final int MIN_PASSWORD = 4;
	private final int MAX_PASSWORD = 32;

	@Override
	public boolean valida(PasswordField campoDiTesto, Label etichettaErrore) {
		// TODO Auto-generated method stub
		Controlli.isAlfanumerico(getValore(campoDiTesto), MIN_PASSWORD,
				MAX_PASSWORD);
		return setMessaggio(etichettaErrore);
	}

}

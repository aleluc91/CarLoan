package utility.validazione;

import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class ValidazioneFloat extends Validazione<TextField> {

	@Override
	public boolean valida(TextField campoDiTesto, Label etichettaErrore) {
		// TODO Auto-generated method stub
		Controlli.isFloat(getValore(campoDiTesto));
		return setMessaggio(etichettaErrore);
	}

}

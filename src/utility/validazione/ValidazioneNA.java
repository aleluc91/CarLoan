package utility.validazione;

import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class ValidazioneNA extends Validazione<TextField> {

	private final int MIN_NOME = 2;
	private final int MAX_NOME = 15;

	@Override
	public boolean valida(TextField campoDiTesto, Label etichettaErrore) {
		// TODO Auto-generated method stub
		Controlli.isAlfanumerico_Spazi(getValore(campoDiTesto), MIN_NOME,
				MAX_NOME);
		return setMessaggio(etichettaErrore);
	}

}

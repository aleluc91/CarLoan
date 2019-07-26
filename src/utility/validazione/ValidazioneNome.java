package utility.validazione;

import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class ValidazioneNome extends Validazione<TextField> {

	private final int MIN_NOME = 4;
	private final int MAX_NOME = 15;

	@Override
	public boolean valida(TextField campoDiTesto, Label etichettaErrore) {
		// TODO Auto-generated method stub
		Controlli.isAlfabetico_Spazi(getValore(campoDiTesto), MIN_NOME,
				MAX_NOME);
		return setMessaggio(etichettaErrore);
	}

}

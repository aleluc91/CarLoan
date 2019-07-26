package utility.validazione;

import javafx.scene.control.Label;
import javafx.scene.control.TextArea;

public class ValidazioneDescrizione extends Validazione<TextArea> {

	private final int MIN_DESCRIZIONE = 2;
	private final int MAX_DESCRIZIONE = 50;

	@Override
	public boolean valida(TextArea campoDiTesto, Label etichettaErrore) {
		// TODO Auto-generated method stub
		Controlli.isAlfanumerico_Spazi(getValore(campoDiTesto), MIN_DESCRIZIONE,
				MAX_DESCRIZIONE);
		return setMessaggio(etichettaErrore);
	}

}

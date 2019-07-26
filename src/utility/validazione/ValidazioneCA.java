package utility.validazione;

import javafx.scene.control.Label;
import javafx.scene.control.TextField;

/**
 * Classe che si occupa di validare il campo Codice Agenzia.
 * 
 * @author Alessio Lucarella , Valentino Arcuri
 *
 */
public class ValidazioneCA extends Validazione<TextField> {

	private final int MIN_CA = 2;
	private final int MAX_CA = 5;

	@Override
	public boolean valida(TextField campoDiTesto, Label etichettaErrore) {
		// TODO Auto-generated method stub
		Controlli.isAlfanumerico(getValore(campoDiTesto), MIN_CA, MAX_CA);
		return setMessaggio(etichettaErrore);
	}

}

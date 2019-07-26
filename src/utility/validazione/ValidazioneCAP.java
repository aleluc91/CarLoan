package utility.validazione;

import javafx.scene.control.Label;
import javafx.scene.control.TextField;

/**
 * Classe che si occupa di validare il campo CAP.
 * 
 * @author Alessio Lucarella , Valentino Arcuri
 *
 */
public class ValidazioneCAP extends Validazione<TextField> {

	private final int MIN_CAP = 5;

	@Override
	public boolean valida(TextField campoDiTesto, Label etichettaErrore) {
		// TODO Auto-generated method stub
		Controlli.isCAP(getValore(campoDiTesto), MIN_CAP);
		return setMessaggio(etichettaErrore);
	}

}

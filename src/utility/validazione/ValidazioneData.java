package utility.validazione;

import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;

public class ValidazioneData extends Validazione<DatePicker> {

	@Override
	public boolean valida(DatePicker campoDiTesto, Label etichettaErrore) {
		// TODO Auto-generated method stub
		try {
			Controlli.isData(campoDiTesto.getValue().toString());
			return setMessaggio(etichettaErrore);
		} catch (NullPointerException e) {
			etichettaErrore.setText("Il campo non può essere vuoto!");
			return true;
		}
	}

}

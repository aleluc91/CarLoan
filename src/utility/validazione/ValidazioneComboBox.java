package utility.validazione;

import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;

public class ValidazioneComboBox<TModello> extends
		Validazione<ComboBox<TModello>> {

	@Override
	public boolean valida(ComboBox<TModello> combobox, Label etichettaErrore) {
		// TODO Auto-generated method stub
		if (combobox.getSelectionModel().getSelectedIndex() != -1) {
			etichettaErrore.setText("");
			return false;
		} else {
			etichettaErrore.setText("Devi selezionare un elemento!");
			return true;
		}
	}

}

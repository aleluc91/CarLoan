package utility;

import java.util.Optional;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;

public class Messaggi {

	public static void msgErroreSelezione() {
		Alert alert = new Alert(AlertType.WARNING);
		alert.setTitle("Errore di selezione!");
		alert.setContentText("Devi selezionare un elemento!");
		alert.show();
	}

	public static void msgErroreContratto() {
		Alert alert = new Alert(AlertType.WARNING);
		alert.setTitle("Attenzione");
		alert.setContentText("Il contratto selezionato non è ancora attivo!");
		alert.show();
	}

	public static boolean msgConferma() {
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("Conferma Operazione");
		alert.setContentText("L'operazione è andata a buon fine!");
		alert.getButtonTypes().setAll(ButtonType.OK);
		Optional<ButtonType> result = alert.showAndWait();
		if (result.get() == ButtonType.OK)
			return true;
		else
			return false;
	}

	public static boolean msgErrore() {
		Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle("Errore!");
		alert.setContentText("L'operazione non è andata a buon fine. Riprova!");
		alert.getButtonTypes().setAll(ButtonType.OK);
		Optional<ButtonType> result = alert.showAndWait();
		if (result.get() == ButtonType.OK)
			return true;
		else
			return false;
	}

	public static void showMessage(String type, String title, String header,
			String content) {
		Alert alert = new Alert(null);
		alert.setTitle(title);
		alert.setHeaderText(header);
		alert.setContentText(content);
		switch (type) {
		case "errore":
			alert.setAlertType(AlertType.ERROR);
			break;
		case "informazione":
			alert.setAlertType(AlertType.INFORMATION);
			break;
		}
		Optional<ButtonType> risultato = alert.showAndWait();
		if (risultato.get() == ButtonType.OK) {

		}
	}

	public static void msgEsistenza() {
		Alert alert = new Alert(AlertType.WARNING);
		alert.setTitle("Errore di selezione!");
		alert.setContentText("Non è possibile modifica l'elemento perchè è contenuto in un contratto attivo!");
		alert.show();
	}

	public static int controlloElimina() {
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("Conferma eliminazione!");
		alert.setContentText("Sei sicuro di voler eliminare questo elemento?");
		ButtonType bottone1 = new ButtonType("Conferma");
		ButtonType bottone2 = new ButtonType("Annulla");
		alert.getButtonTypes().setAll(bottone1, bottone2);
		Optional<ButtonType> result = alert.showAndWait();
		if (result.get() == bottone1) {
			alert.close();
			return 1;
		} else if (result.get() == bottone2) {
			alert.close();
			return 2;
		} else {
			alert.close();
			return 0;
		}
	}

	public static int esci() {
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("Conferma chiusura!");
		alert.setContentText("Sei sicuro di voler uscire?");
		ButtonType bottone1 = new ButtonType("Torna al Login");
		ButtonType bottone2 = new ButtonType("Esci");
		ButtonType bottone3 = new ButtonType("Annulla");
		alert.getButtonTypes().setAll(bottone1, bottone2, bottone3);
		Optional<ButtonType> result = alert.showAndWait();
		if (result.get() == bottone1) {
			alert.close();
			return 1;
		} else if (result.get() == bottone2) {
			alert.close();
			return 2;
		} else if (result.get() == bottone3) {
			alert.close();
			return 3;
		} else {
			alert.close();
			return 0;
		}
	}

}

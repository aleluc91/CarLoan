package utility.validazione;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import org.apache.commons.lang3.StringUtils;

public class Controlli {

	private static boolean errore;
	private static String messaggioErrore;

	public static boolean getErrore() {
		return errore;
	}

	private static void setMessaggioErrore(String errore) {
		messaggioErrore = errore;
	}

	public static String getMessaggioErrore() {
		return messaggioErrore;
	}

	private static void azzera() {
		errore = false;
		setMessaggioErrore("");
	}

	private static boolean vuoto(String valore) {
		if (StringUtils.isBlank(valore)) {
			setMessaggioErrore("Il campo non può essere vuoto!");
			errore = true;
			return true;
		} else {
			return false;
		}
	}

	private static boolean lunghezza(String valore, int min, int max) {
		if ((valore.length() < min) || (valore.length() > max)) {
			setMessaggioErrore("La lunghezza deve essere compresa tra "
					+ String.valueOf(min) + " e " + String.valueOf(max)
					+ " caratteri!");
			return true;
		} else {
			return false;
		}
	}

	public static void isAlfabetico(String valore, int min, int max) {
		azzera();
		if (!vuoto(valore)) {
			if (!StringUtils.isAlpha(valore)) {
				setMessaggioErrore("Il campo può contenere solo lettere!");
				errore = true;
			} else if (lunghezza(valore, min, max)) {
				errore = true;
			}
		}
	}

	public static void isAlfabetico_Spazi(String valore, int min, int max) {
		azzera();
		if (!vuoto(valore)) {
			if (!StringUtils.isAlphaSpace(valore)) {
				setMessaggioErrore("Il campo può contenere solo lettere!");
				errore = true;
			} else if (lunghezza(valore, min, max)) {
				errore = true;
			}
		}
	}

	public static void isNumeroTelefonico(String valore, int min) {
		azzera();
		String regex = "^\\d?-?(\\d{3})?-?\\d{3}-?\\d{4}$";
		if (!vuoto(valore)) {
			if (!valore.matches(regex)) {
				setMessaggioErrore("Inserire un numero di telefono corretto!");
				errore = true;
			} else if (valore.length() > min || valore.length() < min) {
				setMessaggioErrore("Il numero deve contenere 10 cifre in sequenza!");
				errore = true;
			}
		}
	}

	public static void isAlfanumerico(String valore, int min, int max) {
		azzera();
		if (!vuoto(valore)) {
			if (!StringUtils.isAlphanumeric(valore)) {
				setMessaggioErrore("Sono permessi solo lettere o numeri!");
				errore = true;
			} else if (lunghezza(valore, min, max)) {
				errore = true;
			}
		}
	}

	public static void isAlfanumerico_Spazi(String valore, int min, int max) {
		azzera();
		if (!vuoto(valore)) {
			if (!StringUtils.isAlphanumericSpace(valore)) {
				setMessaggioErrore("Sono permessi solo lettere o numeri!");
				errore = true;
			} else if (lunghezza(valore, min, max)) {
				errore = true;
			}
		}
	}

	public static void isCAP(String valore, int min) {
		azzera();
		String regex = "\\d{5}";
		if (!vuoto(valore)) {
			if (!valore.matches(regex)) {
				setMessaggioErrore("Inserire un CAP corretto!");
				errore = true;
			} else if (valore.length() > min || valore.length() < min) {
				setMessaggioErrore("Il CAP deve essere composto da 5 cifre!");
				errore = true;
			}
		}
	}

	public static void isFloat(String valore) {
		azzera();
		if (!vuoto(valore)) {
			try {
				float f = Float.parseFloat(valore);
			} catch (NumberFormatException e) {
				setMessaggioErrore("Inserisci un valore valido per il costo!");
				errore = true;
			}
		}
	}

	public static void isDouble(String valore) {
		azzera();
		if (!vuoto(valore)) {
			try {
				Double d = Double.parseDouble(valore);
			} catch (NumberFormatException e) {
				setMessaggioErrore("Inserisci un valore valido per il chilometraggio!");
				errore = true;
			}
		}
	}

	public static void isTarga(String valore, int min, int max) {
		azzera();
		String regex = "[a-zA-Z]{2}[0-9]{3,4}[a-zA-Z]{2}";
		if (!vuoto(valore)) {
			if (!valore.matches(regex)) {
				setMessaggioErrore("Inserire una targa valida!");
				errore = true;
			} else if (valore.length() > min || valore.length() < min) {
				setMessaggioErrore("La targa deve essere composta da 5 cifre!");
				errore = true;
			}
		}
	}

	public static void isData(String valore) {
		azzera();
		if (!(vuoto(valore))) {
			try {
				DateTimeFormatter dtf = DateTimeFormatter
						.ofPattern("yyyy-MM-dd");
				LocalDate data = LocalDate.parse(valore, dtf);
			} catch (DateTimeParseException e) {
				errore = true;
				setMessaggioErrore("Data non valida!");
			}

		}
	}

}

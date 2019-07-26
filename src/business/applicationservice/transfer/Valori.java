package business.applicationservice.transfer;

import java.time.LocalDate;

import presentation.gui.modelli_tabella.ModelloTabella;

/**
 * Classe utilizzata per il trasferimento di valori all'interno del sistema.
 * Un'oggetto di questo tipo può essere istanziato scegliendo uno dei vari
 * costruttori , ognuno dei quali accetta un diverso tipo di valore(String ,
 * integer , ecc). Ogni valore immagazzinato all'interno dell'istanza può essere
 * recuperato utilizzando i metodi get appositi.
 * 
 * @author Alessio Lucarella , Valentino Arcuri
 *
 */
public class Valori {

	/**
	 * Variabile che può contenere un valore di tipo stringa.
	 */
	private String s_valore;

	/**
	 * Variabile che può contebere un valore di tipo Intero.
	 */
	private int i_valore;

	/**
	 * Variabile che può contenere un valore di tipo Double.
	 */
	private double d_valore;

	/**
	 * Variabile che può contenere un valore di tipo Long.
	 */
	private long long_valore;

	/**
	 * Variabile che può contenere un valore di tipo Data.
	 */
	private LocalDate l_valore;

	private ModelloTabella modello;

	/**
	 * Meotdo costruttore che riceve un parametro di tipo Stringa che verrà
	 * memorizzato nell'apposità variabile.
	 * 
	 * @param value
	 *            Valore di tipo Stringa.
	 */
	public Valori(String value) {
		this.s_valore = value;
	}

	/**
	 * Meotdo costruttore che riceve un parametro di tipo Intero che verrà
	 * memorizzato nell'apposità variabile.
	 * 
	 * @param value
	 *            Valore di tipo Intero.
	 */
	public Valori(int value) {
		this.i_valore = value;
	}

	/**
	 * Meotdo costruttore che riceve un parametro di tipo Double che verrà
	 * memorizzato nell'apposità variabile.
	 * 
	 * @param value
	 *            Valore di tipo Double.
	 */
	public Valori(double value) {
		this.d_valore = value;
	}

	/**
	 * Meotdo costruttore che riceve un parametro di tipo Long che verrà
	 * memorizzato nell'apposità variabile.
	 * 
	 * @param value
	 *            Valore di tipo Long.
	 */
	public Valori(long value) {
		this.long_valore = value;
	}

	/**
	 * Meotdo costruttore che riceve un parametro di tipo Data che verrà
	 * memorizzato nell'apposità variabile.
	 * 
	 * @param value
	 *            Valore di tipo Data.
	 */
	public Valori(LocalDate data) {
		this.l_valore = data;
	}

	/**
	 * 
	 * @return Restituisce il valore memorizzato nella variabile di tipo
	 *         Stringa.
	 */
	public String getString() {
		return s_valore;
	}

	public Valori(ModelloTabella modello) {
		this.modello = modello;
	}

	public ModelloTabella getModello() {
		return modello;
	}

	public int getInt() {
		return i_valore;
	}

	public double getDouble() {
		return d_valore;
	}

	public long getLong() {
		return long_valore;
	}

	public LocalDate getData() {
		return l_valore;
	}

}

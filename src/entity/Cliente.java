package entity;

/**
 * Classe in cui viene definita l'entità cliente.
 * 
 * @author Alessio Lucarella , Valentino Arcuri
 *
 */

public class Cliente {

	private String nome;
	private String cognome;
	private String numeroTelefono;

	public Cliente(String nome, String cognome, String numeroTelefono) {
		this.nome = nome;
		this.cognome = cognome;
		this.numeroTelefono = numeroTelefono;
	}

	public String getNome() {
		return nome;
	}

	public String getCognome() {
		return cognome;
	}

	public String getNumeroTelefono() {
		return numeroTelefono;
	}

}

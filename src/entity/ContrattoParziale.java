package entity;

import java.util.List;

import business.applicationservice.transfer.Valori;

public class ContrattoParziale {

	private static ContrattoParziale istanza = new ContrattoParziale();

	private static String codice;
	private static int giorni;
	private static double chilometri;
	private static double costo;
	private static double tariffa;
	private static Cliente cliente;
	private static Noleggio noleggio;

	private ContrattoParziale() {

	}

	public static ContrattoParziale getIstanza() {
		return istanza;
	}

	public void setCodiceContratto(String valore) {
		codice = valore;
	}

	public String getCodiceContratto() {
		return codice;
	}

	public int getGiorni() {
		return giorni;
	}

	public void setGiorni(int valore) {
		giorni = valore;
	}

	public void setChiloemtri(double valore) {
		chilometri = valore;
	}

	public double getChilometri() {
		return chilometri;
	}

	public double getCosto() {
		return costo;
	}

	public void setCosto(double valore) {
		costo = valore;
	}

	public double getTariffa() {
		return tariffa;
	}

	public void setTariffa(double valore) {
		tariffa = valore;
	}

	public void setCliente(String nome, String cognome, String telefono) {
		cliente = new Cliente(nome, cognome, telefono);
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setNoleggio(List<Valori> valori) {
		ContrattoParziale.noleggio = new Noleggio(valori.get(0).getString(),
				valori.get(1).getInt(), valori.get(2).getString(), valori
						.get(3).getString(), valori.get(4).getString(), valori
						.get(5).getString());
	}

	public Noleggio getNoleggio() {
		return noleggio;
	}

}

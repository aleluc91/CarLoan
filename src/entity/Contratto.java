package entity;

import java.time.LocalDate;

/**
 * Classe in cui viene definita l'entità Contratto.
 * 
 * @author Alessio Lucarella , Valentino Arcuri
 *
 */

public class Contratto {

	private String codiceContratto;
	private Cliente cliente;
	private LocalDate dataInizio;
	private LocalDate dataFine;
	private double acconto;
	private String agenzia;
	private String agenziaRitorno;
	private String attivo;

	public Contratto(String codiceContratto, Cliente cliente,
			LocalDate dataInizio, LocalDate dataFine, double acconto,
			String agenzia, String agenziaRitorno, String attivo) {
		this.codiceContratto = codiceContratto;
		this.cliente = cliente;
		this.dataInizio = dataInizio;
		this.dataFine = dataFine;
		this.acconto = acconto;
		this.agenzia = agenzia;
		this.agenziaRitorno = agenziaRitorno;
		this.attivo = attivo;
	}

	public void setCodiceContratto(String codice) {
		this.codiceContratto = codice;
	}

	public String getCodiceContratto() {
		return codiceContratto;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public LocalDate getDataInizio() {
		return dataInizio;
	}

	public LocalDate getDataFine() {
		return dataFine;
	}

	public double getAcconto() {
		return acconto;
	}

	public String getAgenzia() {
		return agenzia;
	}

	public String getAgenziaRitorno() {
		return agenziaRitorno;
	}

	public String getAttivo() {
		return attivo;
	}

}

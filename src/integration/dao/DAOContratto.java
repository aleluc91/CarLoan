package integration.dao;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import business.applicationservice.transfer.Valori;
import entity.Cliente;
import entity.Contratto;

public class DAOContratto extends DAO<Contratto> {

	private final String COLONNA1 = "codice";
	private final String COLONNA2 = "nome";
	private final String COLONNA3 = "cognome";
	private final String COLONNA4 = "telefono";
	private final String COLONNA5 = "data_inizio";
	private final String COLONNA6 = "data_fine";
	private final String COLONNA7 = "acconto";
	private final String COLONNA8 = "agenzia";
	private final String COLONNA9 = "agenzia_ritorno";

	private final String INSERIMENTO = "insert into contratto values(?,?,?,?,?,?,?,?,?,?)";
	private final String LETTURA = "select * from contratto";
	private final String ELIMINA = "delete from contratto where codice =?";

	@Override
	public boolean inserimento(Contratto entita) {
		apriConnessione();
		try {
			preparedStatement = connessione.prepareStatement(INSERIMENTO);
			preparedStatement.setString(1, entita.getCodiceContratto());
			preparedStatement.setString(2, entita.getCliente().getNome());
			preparedStatement.setString(3, entita.getCliente().getCognome());
			preparedStatement.setString(4, entita.getCliente()
					.getNumeroTelefono());
			preparedStatement.setDate(5, converti(entita.getDataInizio()));
			preparedStatement.setDate(6, converti(entita.getDataFine()));
			preparedStatement.setDouble(7, entita.getAcconto());
			preparedStatement.setString(8, entita.getAgenzia());
			preparedStatement.setString(9, entita.getAgenziaRitorno());
			preparedStatement.setString(10, "si");
			preparedStatement.executeUpdate();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		} finally {
			chiudiConnessione();
		}
	}

	@Override
	public List<Contratto> leggi() {
		apriConnessione();
		try {
			preparedStatement = connessione.prepareStatement(LETTURA);
			resultSet = preparedStatement.executeQuery();
			if (resultSet != null) {
				List<Contratto> list = new ArrayList<Contratto>();
				while (resultSet.next()) {
					String codice = resultSet.getString(COLONNA1);
					Cliente cliente = new Cliente(
							resultSet.getString(COLONNA2),
							resultSet.getString(COLONNA3),
							resultSet.getString(COLONNA4));
					LocalDate data_inizio = resultSet.getDate(COLONNA5)
							.toLocalDate();
					LocalDate data_fine = resultSet.getDate(COLONNA6)
							.toLocalDate();
					double acconto = new Double(resultSet.getDouble(COLONNA7));
					String agenzia = resultSet.getString(COLONNA8);
					String agenziaRitorno = resultSet.getString(COLONNA9);
					String attivo = resultSet.getString("attivo");
					list.add(new Contratto(codice, cliente, data_inizio,
							data_fine, acconto, agenzia, agenziaRitorno, attivo));
				}
				return list;
			} else {
				return null;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		} finally {
			chiudiConnessione();
		}
	}

	@Override
	public boolean aggiorna(List<Valori> valori) {
		return false;
	}

	@Override
	public boolean elimina(String valore) {
		return false;
	}

	@Override
	public boolean elimina(int valore) {
		return false;
	}

	private java.sql.Date converti(LocalDate data) {
		return java.sql.Date.valueOf(data);
	}

	@Override
	public boolean aggiornaValore(List<Valori> valori) {
		apriConnessione();
		try {
			String colonna = valori.get(0).getString();
			final String MODIFICA_VALORE = "update contratto set " + colonna
					+ " = ? where codice = ?";
			preparedStatement = connessione.prepareStatement(MODIFICA_VALORE);
			preparedStatement.setString(1, valori.get(1).getString());
			preparedStatement.setString(2, valori.get(2).getString());
			preparedStatement.executeUpdate();
			return true;
		} catch (SQLException e) {
			return false;
		}
	}

}

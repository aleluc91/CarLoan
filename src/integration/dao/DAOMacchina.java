package integration.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import entity.Macchina;
import business.applicationservice.transfer.Valori;

public class DAOMacchina extends DAO<Macchina> {

	private final String COLONNA1 = "targa";
	private final String COLONNA2 = "fascia";
	private final String COLONNA3 = "modello";
	private final String COLONNA4 = "agenzia";
	private final String COLONNA5 = "noleggiata";
	private final String COLONNA6 = "manutenzione";
	private final String COLONNA7 = "chilometraggio";

	private final String INSERIMENTO = "insert into macchina values(?,?,?,?,?,?,?)";
	private final String LETTURA = "select * from macchina";
	private final String AGGIORNA = "update macchina set targa=? , fascia=? , modello=? , agenzia=? , noleggiata=? , manutenzione=? , chilometraggio=? where targa=?";
	private final String ELIMINA = "delete from macchina where targa = ?";

	@Override
	public boolean inserimento(Macchina macchina) {
		apriConnessione();
		try {
			preparedStatement = connessione.prepareStatement(INSERIMENTO);
			preparedStatement.setString(1, macchina.getTarga());
			preparedStatement.setString(2, macchina.getFascia());
			preparedStatement.setString(3, macchina.getModello());
			preparedStatement.setString(4, macchina.getAgenzia());
			preparedStatement.setString(5, macchina.getNoleggio());
			preparedStatement.setString(6, macchina.getManutenzione());
			preparedStatement.setLong(7, macchina.getChilometraggio());
			preparedStatement.executeUpdate();
			chiudiConnessione();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public List<Macchina> leggi() {
		apriConnessione();
		try {
			preparedStatement = connessione.prepareStatement(LETTURA);
			resultSet = preparedStatement.executeQuery();
			if (resultSet != null) {
				List<Macchina> listaMacchina = new ArrayList<Macchina>();
				while (resultSet.next()) {
					String targa = resultSet.getString(COLONNA1);
					String fascia = resultSet.getString(COLONNA2);
					String modello = resultSet.getString(COLONNA3);
					String agenzia = resultSet.getString(COLONNA4);
					String noleggio = resultSet.getString(COLONNA5);
					String manutenzione = resultSet.getString(COLONNA6);
					long chilometraggio = new Long(resultSet.getLong(COLONNA7));
					listaMacchina.add(new Macchina(targa, fascia, modello,
							agenzia, noleggio, manutenzione, chilometraggio));
				}
				return listaMacchina;
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
		apriConnessione();
		try {
			preparedStatement = connessione.prepareStatement(AGGIORNA);
			preparedStatement.setString(1, valori.get(0).getString());
			preparedStatement.setString(2, valori.get(1).getString());
			preparedStatement.setString(3, valori.get(2).getString());
			preparedStatement.setString(4, valori.get(3).getString());
			preparedStatement.setString(5, valori.get(4).getString());
			preparedStatement.setString(6, valori.get(5).getString());
			preparedStatement.setDouble(7, valori.get(6).getLong());
			preparedStatement.setString(8, valori.get(7).getString());
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
	public boolean elimina(String valore) {
		apriConnessione();
		try {
			preparedStatement = connessione.prepareStatement(ELIMINA);
			preparedStatement.setString(1, valore);
			preparedStatement.executeUpdate();
			chiudiConnessione();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean elimina(int valore) {
		return false;
	}

	@Override
	public boolean aggiornaValore(List<Valori> valori) {
		apriConnessione();
		try {
			String colonna = valori.get(0).getString();
			final String MODIFICA_VALORE = "update macchina set " + colonna
					+ " = ? where targa = ?";
			preparedStatement = connessione.prepareStatement(MODIFICA_VALORE);
			if (colonna.equalsIgnoreCase(COLONNA5))
				preparedStatement.setString(1, valori.get(1).getString());
			else if (colonna.equalsIgnoreCase(COLONNA6))
				preparedStatement.setString(1, valori.get(1).getString());
			else if (colonna.equalsIgnoreCase(COLONNA7))
				preparedStatement.setDouble(1, valori.get(1).getLong());
			preparedStatement.setString(2, valori.get(2).getString());
			preparedStatement.executeUpdate();
			return true;
		} catch (SQLException e) {
			return false;
		}
	}

}

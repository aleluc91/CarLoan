package integration.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import entity.Noleggio;
import business.applicationservice.transfer.Valori;

public class DAONoleggio extends DAO<Noleggio> {

	private final String COLONNA1 = "codice";
	private final String COLONNA2 = "base";
	private final String COLONNA3 = "chilometraggio";
	private final String COLONNA4 = "fascia";
	private final String COLONNA5 = "modello";
	private final String COLONNA6 = "macchina";

	private final String INSERIMENTO = "insert into noleggio values(?,?,?,?,?,?)";
	private final String LETTURA = "select * from noleggio";

	@Override
	public boolean inserimento(Noleggio entita) {
		apriConnessione();
		try {
			preparedStatement = connessione.prepareStatement(INSERIMENTO);
			preparedStatement.setString(1, entita.getContratto());
			preparedStatement.setInt(2, entita.getBase());
			preparedStatement.setString(3, entita.getChilometraggio());
			preparedStatement.setString(4, entita.getFascia());
			preparedStatement.setString(5, entita.getModello());
			preparedStatement.setString(6, entita.getMacchina());
			preparedStatement.executeUpdate();
			chiudiConnessione();
			return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public List<Noleggio> leggi() {
		// TODO Auto-generated method stub
		apriConnessione();
		try {
			preparedStatement = connessione.prepareStatement(LETTURA);
			resultSet = preparedStatement.executeQuery();
			if (resultSet != null) {
				List<Noleggio> listaNoleggio = new ArrayList<Noleggio>();
				while (resultSet.next()) {
					String codice = resultSet.getString(COLONNA1);
					int base = resultSet.getInt(COLONNA2);
					String chilometraggio = resultSet.getString(COLONNA3);
					String fascia = resultSet.getString(COLONNA4);
					String modello = resultSet.getString(COLONNA5);
					String macchina = resultSet.getString(COLONNA6);
					listaNoleggio.add(new Noleggio(codice, base,
							chilometraggio, fascia, modello, macchina));
				}
				return listaNoleggio;
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
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean elimina(String valore) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean elimina(int valore) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean aggiornaValore(List<Valori> valori) {
		// TODO Auto-generated method stub
		return false;
	}

}

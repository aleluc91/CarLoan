package integration.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import entity.Modello;
import business.applicationservice.transfer.Valori;

public class DAOModello extends DAO<Modello> {

	private final String COLONNA_1 = "modello";
	private final String COLONNA_2 = "fascia";

	private final String INSERIMENTO = "insert into modello(modello,fascia) values(?,?)";
	private final String LETTURA = "select * from modello";
	private final String AGGIORNA = "update modello set modello=? , fascia=? where modello=? and fascia=?";
	private final String ELIMINA = "delete from modello where modello = ?";

	@Override
	public boolean inserimento(Modello entita) {
		apriConnessione();
		try {
			preparedStatement = connessione.prepareStatement(INSERIMENTO);
			preparedStatement.setString(1, entita.getModello());
			preparedStatement.setString(2, entita.getFascia());
			preparedStatement.executeUpdate();
			return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		} finally {
			chiudiConnessione();
		}
	}

	@Override
	public List<Modello> leggi() {
		apriConnessione();
		try {
			preparedStatement = connessione.prepareStatement(LETTURA);
			resultSet = preparedStatement.executeQuery();
			if (resultSet != null) {
				List<Modello> lista = new ArrayList<Modello>();
				while (resultSet.next()) {
					String modello = resultSet.getString(COLONNA_1);
					String fascia = resultSet.getString(COLONNA_2);
					lista.add(new Modello(modello, fascia));
				}
				return lista;
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
	public boolean elimina(String valore) {
		apriConnessione();
		try {
			preparedStatement = connessione.prepareStatement(ELIMINA);
			preparedStatement.setString(1, valore);
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

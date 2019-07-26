package integration.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import entity.Fascia;
import business.applicationservice.transfer.Valori;

public class DAOFascia extends DAO<Fascia> {

	private final String COLONNA_1 = "id";
	private final String COLONNA_2 = "requisiti";
	private final String COLONNA_3 = "costo";

	private final String INSERIMENTO = "insert into fascia values(?,?,?)";
	private final String LETTURA = "select * from fascia";
	private final String AGGIORNA = "update fascia set id=? , requisiti=? , costo=? where id=?";
	private final String ELIMINA = "delete from fascia where id = ?";

	@Override
	public boolean inserimento(Fascia entita) {
		apriConnessione();
		try {
			preparedStatement = connessione.prepareStatement(INSERIMENTO);
			preparedStatement.setString(1, entita.getId());
			preparedStatement.setString(2, entita.getRequisiti());
			preparedStatement.setDouble(3, entita.getCosto());
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
	public List<Fascia> leggi() {
		apriConnessione();
		try {
			preparedStatement = connessione.prepareStatement(LETTURA);
			resultSet = preparedStatement.executeQuery();
			if (resultSet != null) {
				List<Fascia> lista = new ArrayList<Fascia>();
				while (resultSet.next()) {
					String id = resultSet.getString(COLONNA_1);
					String requisiti = resultSet.getString(COLONNA_2);
					double costo = resultSet.getDouble(COLONNA_3);
					lista.add(new Fascia(id, requisiti, costo));
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
			preparedStatement.setDouble(3, valori.get(2).getDouble());
			preparedStatement.setString(4, valori.get(3).getString());
			preparedStatement.executeUpdate();
			chiudiConnessione();
			return true;
		} catch (SQLException e) {
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
		return false;
	}

}

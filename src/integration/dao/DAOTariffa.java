package integration.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import entity.Tariffa;
import business.applicationservice.transfer.Valori;

public class DAOTariffa extends DAO<Tariffa> {

	private final String COLONNA1 = "id";
	private final String COLONNA2 = "tipo";
	private final String COLONNA3 = "chilometri";
	private final String COLONNA4 = "giorni";
	private final String COLONNA5 = "tariffa";

	private final String INSERIMENTO = "insert into tariffa(tipo,chilometri,giorni,tariffa) values(?,?,?,?)";
	private final String LETTURA = "select * from tariffa";
	private final String AGGIORNA = "update tariffa set tipo=? , chilometri=? , giorni=? , tariffa=? where id=?";
	private final String ELIMINA = "delete from tariffa where id=?";

	@Override
	public boolean inserimento(Tariffa entita) {
		apriConnessione();
		try {
			preparedStatement = connessione.prepareStatement(INSERIMENTO);
			preparedStatement.setString(1, entita.getTipo());
			preparedStatement.setLong(2, new Long(entita.getChilometri()));
			preparedStatement.setInt(3, new Integer(entita.getGiorni()));
			preparedStatement.setDouble(4, new Double(entita.getTariffa()));
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
	public List<Tariffa> leggi() {
		apriConnessione();
		try {
			preparedStatement = connessione.prepareStatement(LETTURA);
			resultSet = preparedStatement.executeQuery();
			if (resultSet != null) {
				List<Tariffa> lista = new ArrayList<Tariffa>();
				while (resultSet.next()) {
					int id = resultSet.getInt(COLONNA1);
					String tipo = resultSet.getString(COLONNA2);
					long chilometri = new Long(resultSet.getLong(COLONNA3));
					int giorni = new Integer(resultSet.getInt(COLONNA4));
					double tariffa = new Double(resultSet.getDouble(COLONNA5));
					lista.add(new Tariffa(id, tipo, chilometri, giorni, tariffa));
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
			preparedStatement.setLong(2, valori.get(1).getLong());
			preparedStatement.setInt(3, valori.get(2).getInt());
			preparedStatement.setDouble(4, valori.get(3).getDouble());
			preparedStatement.setInt(5, valori.get(4).getInt());
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
			preparedStatement.setInt(1, Integer.valueOf(valore));
			System.out.println("ok");
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
		apriConnessione();
		try {
			preparedStatement = connessione.prepareStatement(ELIMINA);
			preparedStatement.setInt(1, valore);
			System.out.println("ok");
			preparedStatement.executeUpdate();
			chiudiConnessione();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean aggiornaValore(List<Valori> valori) {
		// TODO Auto-generated method stub
		return false;
	}

}

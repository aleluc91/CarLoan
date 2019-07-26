package integration.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import entity.Autenticazione;
import business.applicationservice.transfer.Valori;

class DAOAutenticazione extends DAO<Autenticazione> {

	private final String COLONNA1 = "username";
	private final String COLONNA2 = "password";
	private final String COLONNA3 = "agenzia";
	private final String COLONNA4 = "privilegi";

	private final String INSERIMENTO = "insert into autenticazione(username,password,agenzia,privilegi) values(?,?,?,?);";
	private final String LETTURA = "select * from autenticazione";
	private final String AGGIORNA = "update autenticazione set username=? , password=?  where agenzia=?";

	public DAOAutenticazione() {

	}

	@Override
	public boolean inserimento(Autenticazione entita) {
		// TODO Auto-generated method stub
		apriConnessione();
		try {
			preparedStatement = connessione.prepareStatement(INSERIMENTO);
			preparedStatement.setString(1, entita.getUsername());
			preparedStatement.setString(2, entita.getPassword());
			preparedStatement.setString(3, entita.getAgenzia());
			preparedStatement.setString(4, entita.getPrivilegi());
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
	public List<Autenticazione> leggi() {
		// TODO Auto-generated method stub
		apriConnessione();
		try {
			preparedStatement = connessione.prepareStatement(LETTURA);
			resultSet = preparedStatement.executeQuery();
			if (resultSet != null) {
				List<Autenticazione> list = new ArrayList<Autenticazione>();
				while (resultSet.next()) {
					String username = resultSet.getString(COLONNA1);
					String password = resultSet.getString(COLONNA2);
					String agenzia = resultSet.getString(COLONNA3);
					String privilegi = resultSet.getString(COLONNA4);
					list.add(new Autenticazione(username, password, agenzia,
							privilegi));
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
		// TODO Auto-generated method stub
		apriConnessione();
		try {
			preparedStatement = connessione.prepareStatement(AGGIORNA);
			preparedStatement.setString(1, valori.get(0).getString());
			preparedStatement.setString(2, valori.get(1).getString());
			preparedStatement.setString(3, valori.get(2).getString());
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

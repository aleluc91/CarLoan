package integration.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import entity.Agenzia;
import business.applicationservice.transfer.Valori;

class DAOAgenzia extends DAO<Agenzia> {

	private final String COLONNA_1 = "codice";
	private final String COLONNA_2 = "nome";
	private final String COLONNA_3 = "indirizzo";
	private final String COLONNA_4 = "citta";
	private final String COLONNA_5 = "CAP";

	private final String INSERIMENTO = "insert into agenzia(codice,nome,indirizzo,citta,CAP) values(?,?,?,?,?)";
	private final String LETTURA = "select * from agenzia";
	private final String AGGIORNA = "update agenzia set codice=? , nome=? , indirizzo=? , citta=? , CAP=?  where codice=?";
	private final String ELIMINA = "delete from agenzia where codice = ?";

	@Override
	public boolean inserimento(Agenzia agenzia) {
		apriConnessione();
		try {
			preparedStatement = connessione.prepareStatement(INSERIMENTO);
			preparedStatement.setString(1, agenzia.getCodice());
			preparedStatement.setString(2, agenzia.getCitta());
			preparedStatement.setString(3, agenzia.getIndirizzo());
			preparedStatement.setString(4, agenzia.getCitta());
			preparedStatement.setString(5, agenzia.getCap());
			preparedStatement.executeUpdate();
			chiudiConnessione();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public List<Agenzia> leggi() {
		apriConnessione();
		try {
			preparedStatement = connessione.prepareStatement(LETTURA);
			resultSet = preparedStatement.executeQuery();
			if (resultSet != null) {
				List<Agenzia> listaAgenzia = new ArrayList<Agenzia>();
				while (resultSet.next()) {
					String codice = resultSet.getString(COLONNA_1);
					String nome = resultSet.getString(COLONNA_2);
					String indirizzo = resultSet.getString(COLONNA_3);
					String citta = resultSet.getString(COLONNA_4);
					String cap = resultSet.getString(COLONNA_5);
					listaAgenzia.add(new Agenzia(codice, nome, indirizzo,
							citta, cap));
				}
				return listaAgenzia;
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

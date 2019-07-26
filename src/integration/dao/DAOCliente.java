package integration.dao;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import entity.Cliente;
import business.applicationservice.transfer.Valori;

class DAOCliente extends DAO<Cliente> {

	private static final String INSERIMENTO = "insert into cliente(nome,cognome,telefono) values(?,?,?)";

	public DAOCliente() {

	}

	@Override
	public boolean inserimento(Cliente entita) {
		// TODO Auto-generated method stub
		apriConnessione();
		try {
			preparedStatement = connessione.prepareStatement(INSERIMENTO,
					Statement.RETURN_GENERATED_KEYS);
			preparedStatement.setString(1, entita.getNome());
			preparedStatement.setString(2, entita.getCognome());
			preparedStatement.setString(3, entita.getNumeroTelefono());
			preparedStatement.executeUpdate();
			resultSet = preparedStatement.getGeneratedKeys();
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
	public boolean aggiorna(List<Valori> valori) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<Cliente> leggi() {
		// TODO Auto-generated method stub
		return null;
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

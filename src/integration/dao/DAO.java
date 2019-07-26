package integration.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;

import org.apache.ibatis.jdbc.ScriptRunner;

import utility.LeggiConfig;
import business.applicationservice.transfer.Valori;

public abstract class DAO<T> {

	/**
	 * Driver che viene utilizzato per la connessione al database. In questo
	 * caso utilizziamo il driver per la connessione a un database mysql.
	 */
	private final String DRIVER = "com.mysql.jdbc.Driver";

	/**
	 * URL di base per la connessione al database.
	 */
	private final String URL_BASE = "jdbc:mysql://";

	private String url;
	private String nomeDatabase;

	/**
	 * Username per l'accesso al databse.
	 */
	private String username;

	/**
	 * Password per l'accesso al database
	 */
	private String password;

	private final String QUERY_ESISTENZA = "SELECT SCHEMA_NAME FROM INFORMATION_SCHEMA.SCHEMATA WHERE SCHEMA_NAME = ?";

	Connection connessione = null;
	PreparedStatement preparedStatement = null;
	ResultSet resultSet = null;

	/**
	 * Metodo che si occupa di aprire una connessione con il database
	 * 
	 */
	void apriConnessione() {
		try {
			try {
				url = LeggiConfig.getValoreProp(
						getClass().getResource("risorse/config.properties")
								.toExternalForm(), "url");
				nomeDatabase = LeggiConfig.getValoreProp(getClass()
						.getResource("risorse/config.properties").getPath(),
						"dbname");
				username = LeggiConfig.getValoreProp(
						getClass().getResource("risorse/config.properties")
								.getPath(), "user");
				password = LeggiConfig.getValoreProp(
						getClass().getResource("risorse/config.properties")
								.getPath(), "password");
				if (!esistenzaDB())
					creaDB();
			} catch (IOException e) {
				e.printStackTrace();
			}
			Class.forName(DRIVER);
			String urlCompleto = URL_BASE + url + "/" + nomeDatabase;
			connessione = DriverManager.getConnection(urlCompleto, username,
					password);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			System.out.println("Errore connessione al database");
			e.printStackTrace();
		}
	}

	void chiudiConnessione() {
		if (connessione != null) {
			try {
				connessione.close();
				if (preparedStatement != null) {
					preparedStatement.close();
				}
				if (resultSet != null) {
					resultSet.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	private boolean esistenzaDB() {
		try {
			Connection conn = DriverManager.getConnection(URL_BASE + url,
					username, password);
			preparedStatement = conn.prepareStatement(QUERY_ESISTENZA);
			preparedStatement.setString(1, nomeDatabase);
			resultSet = preparedStatement.executeQuery();
			if (resultSet != null) {
				while (resultSet.next()) {
					return true;
				}
				return false;
			} else
				return false;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}

	}

	private void creaDB() {
		try {
			String scriptSqlPath = getClass()
					.getResource("risorse/carloan.sql").getPath();
			Connection conn = DriverManager.getConnection(URL_BASE + url,
					username, password);
			ScriptRunner sr = new ScriptRunner(conn);
			Reader reader = new BufferedReader(new FileReader(scriptSqlPath));
			sr.runScript(reader);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}



	abstract public boolean inserimento(T entita);

	abstract public List<T> leggi();

	abstract public boolean aggiorna(List<Valori> valori);

	abstract public boolean aggiornaValore(List<Valori> valori);

	abstract public boolean elimina(String valore);

	abstract public boolean elimina(int valore);

}

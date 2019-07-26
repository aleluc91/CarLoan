package utility;

import integration.dao.DAO;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class LeggiConfig {

	private static String valore = "";
	private static InputStream inputStream;

	public static String getValoreProp(String path, String nome)
			throws IOException {
		try {
			Properties prop = new Properties();

			inputStream = DAO.class.getResourceAsStream("risorse/config.properties");

			if (inputStream != null) {
				prop.load(inputStream);
			} else {
				throw new FileNotFoundException("File property  '" + path
						+ "' non trovato!");
			}

			valore = prop.getProperty(nome);

		} catch (Exception e) {
			System.out.println("Exception: " + e);
		} finally {
			inputStream.close();
		}
		return valore;
	}

}

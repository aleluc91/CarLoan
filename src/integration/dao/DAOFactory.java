package integration.dao;

public class DAOFactory {

	private static final String DAOString = "DAO";
	private static final String PACKAGE_PATH = "integration.dao.";

	public static <T> DAO<T> getDAO(String DAOName) {
		return getDAOIstance(getDAOClass(DAOName));
	}

	private static Class<?> getDAOClass(String DAOName) {
		String className = getDAOPath(DAOName);
		Class<?> applicationClass = null;
		try {
			applicationClass = Class.forName(className);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return applicationClass;
	}

	private static String getDAOPath(String DAOName) {
		return PACKAGE_PATH + DAOString + DAOName;
	}

	@SuppressWarnings("unchecked")
	private static <T> DAO<T> getDAOIstance(Class<?> applicationClass) {
		DAO<T> applicationIstance = null;
		try {
			applicationIstance = (DAO<T>) applicationClass.newInstance();
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		}
		return applicationIstance;
	}

}

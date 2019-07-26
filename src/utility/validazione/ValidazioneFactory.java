package utility.validazione;

public class ValidazioneFactory {

	private static final String NOME_CLASSE = "Validazione";
	private static final String PERCORSO = "utility.validazione.";

	public static <T> Validazione<T> getValidazione(String nome) {
		return getIstanza(getClasseValidazione(nome));
	}

	private static Class<?> getClasseValidazione(String nome) {
		String nomeClasse = getPercorso(nome);
		Class<?> classe = null;
		try {
			classe = Class.forName(nomeClasse);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return classe;
	}

	private static String getPercorso(String nome) {
		return PERCORSO + NOME_CLASSE + nome;
	}

	@SuppressWarnings("unchecked")
	private static <T> Validazione<T> getIstanza(Class<?> classe) {
		Validazione<T> istanza = null;
		try {
			istanza = (Validazione<T>) classe.newInstance();
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return istanza;
	}
}

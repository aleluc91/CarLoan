package utility.controlli_esistenza;

import java.util.ArrayList;
import java.util.List;

import entity.Contratto;
import entity.Noleggio;
import presentation.controller.FrontController;
import business.applicationservice.transfer.Valori;

public class EsistenzaContratto {

	@SuppressWarnings("unchecked")
	public static boolean verificaAgenzia(String agenzia) {
		FrontController fc = new FrontController();
		List<Contratto> lista = (ArrayList<Contratto>) fc.processaRichiesta(
				"leggiContratto", null);
		if (lista != null) {
			for (Contratto contratto : lista) {
				if (contratto.getAgenzia().equalsIgnoreCase(agenzia)
						&& contratto.getAttivo().equalsIgnoreCase("si")) {
					return true;
				}
			}
			return false;
		}
		return false;
	}

	@SuppressWarnings("unchecked")
	public static boolean verificaFascia(String fascia) {
		FrontController fc = new FrontController();
		List<Noleggio> lista = (ArrayList<Noleggio>) fc.processaRichiesta(
				"leggiNoleggio", null);
		if (lista != null) {
			for (Noleggio noleggio : lista) {
				if (noleggio.getFascia().equalsIgnoreCase(fascia)) {
					fc = new FrontController();
					List<Valori> valori = new ArrayList<Valori>();
					valori.add(new Valori(noleggio.getContratto()));
					Contratto contratto = (Contratto) fc.processaRichiesta(
							"cercaContratto", valori);
					if (contratto.getAttivo().equalsIgnoreCase("si"))
						return true;
				}
			}
			return false;
		}
		return false;
	}

	@SuppressWarnings("unchecked")
	public static boolean verificaModello(String modello) {
		FrontController fc = new FrontController();
		List<Noleggio> lista = (ArrayList<Noleggio>) fc.processaRichiesta(
				"leggiNoleggio", null);
		if (lista != null) {
			for (Noleggio noleggio : lista) {
				if (noleggio.getModello().equalsIgnoreCase(modello)) {
					fc = new FrontController();
					List<Valori> valori = new ArrayList<Valori>();
					valori.add(new Valori(noleggio.getContratto()));
					Contratto contratto = (Contratto) fc.processaRichiesta(
							"cercaContratto", valori);
					if (contratto.getAttivo().equalsIgnoreCase("si"))
						return true;
				}
			}
			return false;
		}
		return false;
	}

	@SuppressWarnings("unchecked")
	public static boolean verificaMacchina(String targa) {
		FrontController fc = new FrontController();
		List<Noleggio> lista = (ArrayList<Noleggio>) fc.processaRichiesta(
				"leggiNoleggio", null);
		if (lista != null) {
			for (Noleggio noleggio : lista) {
				if (noleggio.getMacchina().equalsIgnoreCase(targa)) {
					fc = new FrontController();
					List<Valori> valori = new ArrayList<Valori>();
					valori.add(new Valori(noleggio.getContratto()));
					Contratto contratto = (Contratto) fc.processaRichiesta(
							"cercaContratto", valori);
					if (contratto.getAttivo().equalsIgnoreCase("si"))
						return true;
				}
			}
			return false;
		}
		return false;
	}

	@SuppressWarnings("unchecked")
	public static boolean verificaTariffa(int tariffa) {
		FrontController fc = new FrontController();
		List<Noleggio> lista = (ArrayList<Noleggio>) fc.processaRichiesta(
				"leggiNoleggio", null);
		if (lista != null) {
			for (Noleggio noleggio : lista) {
				if (noleggio.getBase() == tariffa) {
					fc = new FrontController();
					List<Valori> valori = new ArrayList<Valori>();
					valori.add(new Valori(noleggio.getContratto()));
					Contratto contratto = (Contratto) fc.processaRichiesta(
							"cercaContratto", valori);
					if (contratto.getAttivo().equalsIgnoreCase("si"))
						return true;
				}
			}
			return false;
		}
		return false;
	}

}

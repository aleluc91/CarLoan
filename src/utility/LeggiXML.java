package utility;

import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class LeggiXML {

	public static String leggiFileXML(String percorso, String tagGenitore,
			String nomeAttributo, String valoreAttributo, String tagFiglio) {
		Document documento = esaminaFileXML(percorso);
		NodeList listaNodi = esaminaDocumento(documento, tagGenitore);
		return getElemento(listaNodi, nomeAttributo, valoreAttributo, tagFiglio);
	}

	private static Document esaminaFileXML(String percorso) {
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		try {
			DocumentBuilder db = dbf.newDocumentBuilder();
			Document dom = db.parse(percorso);
			return dom;
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	private static NodeList esaminaDocumento(Document dom, String tagGenitore) {
		Element elementiDocumento = dom.getDocumentElement();
		NodeList listaNodi = elementiDocumento
				.getElementsByTagName(tagGenitore);
		return listaNodi;
	}

	private static String getElemento(NodeList listaNodi, String nomeAttributo,
			String valoreAttributo, String tagFiglio) {
		String valoreElemento = null;
		for (int i = 0; i < listaNodi.getLength(); i++) {
			Element elemento = (Element) listaNodi.item(i);
			if (elemento.getAttribute(nomeAttributo).equalsIgnoreCase(
					valoreAttributo)) {
				valoreElemento = getValoreElemento(elemento, tagFiglio);
			}
		}
		return valoreElemento;
	}

	private static String getValoreElemento(Element elemento,
			String tagRicercato) {
		String valoreElemento = null;
		NodeList listaNodi = elemento.getElementsByTagName(tagRicercato);
		if (checkListaNodi(listaNodi) == true) {
			Element sottoElemento = (Element) listaNodi.item(0);
			valoreElemento = sottoElemento.getFirstChild().getNodeValue();
		}
		return valoreElemento;
	}

	private static boolean checkListaNodi(NodeList listaNodi) {
		if ((listaNodi != null) && listaNodi.getLength() > 0) {
			return true;
		} else {
			return false;
		}
	}

}

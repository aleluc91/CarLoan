package business.applicationservice.factory;

import utility.LeggiXML;

public class ASFactory extends ApplicationServiceFactory {

	private static final String PATH = "business.applicationservice.";
	private static final String CHILD_TAG = "class";

	public static Class<?> getApplicationService(String serviceName) {
		Class<?> cls = null;
		try {
			cls = Class.forName(getClassName(serviceName));
			return cls;
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return cls;
	}

	private static String getClassName(String attributeValue) {
		return PATH + researchClassName(attributeValue);
	}

	private static String researchClassName(String attributeValue) {
		return LeggiXML.leggiFileXML(XML_PATH, ROOT_TAG, ATTRIBUTE_TAG,
				attributeValue, CHILD_TAG);
	}

}

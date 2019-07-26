package business.applicationservice.factory;

import java.lang.reflect.Method;
import java.util.List;

import utility.LeggiXML;

public class ASMethodFactory extends ApplicationServiceFactory {

	private static final String CHILD_TAG = "method";

	public static Method getASMethod(Class<?> cls, String serviceName,
			boolean parametri) {
		Method method = null;
		if (parametri) {
			Class<?> params[] = { List.class };
			try {
				method = cls.getDeclaredMethod(getMethodName(serviceName),
						params);
				return method;
			} catch (NoSuchMethodException e) {
				e.printStackTrace();
			} catch (SecurityException e) {
				e.printStackTrace();
			}
			return method;
		} else {
			Class<?> params[] = {};
			try {
				method = cls.getDeclaredMethod(getMethodName(serviceName),
						params);
				return method;
			} catch (NoSuchMethodException e) {
				e.printStackTrace();
			} catch (SecurityException e) {
				e.printStackTrace();
			}
			return method;
		}

	}

	private static String getMethodName(String attributeValue) {
		return researchMethodName(attributeValue);
	}

	private static String researchMethodName(String attributeValue) {
		return LeggiXML.leggiFileXML(XML_PATH, ROOT_TAG, ATTRIBUTE_TAG,
				attributeValue, CHILD_TAG);
	}

}

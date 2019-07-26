package business.applicationservice.factory;

abstract class ApplicationServiceFactory {

	protected static final String XML_PATH = ApplicationServiceFactory.class
			.getResource("ApplicationServiceMap.xml").toExternalForm();
	protected static final String ROOT_TAG = "Service";
	protected static final String ATTRIBUTE_TAG = "id";

}

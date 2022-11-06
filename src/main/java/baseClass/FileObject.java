package baseClass;

import java.util.Properties;

public class FileObject {

	private static final ThreadLocal<Properties> property = new ThreadLocal<>();

	public static Properties getPropertyInstance() {
		return property.get();
	}

	public static void setPropertyInstance(Properties prop) {
		property.set(prop);
	}

	//For assigning null value to webDriver so that it can be eligible to Garbage collection
	public static void remove() {
		property.remove();
	}
}



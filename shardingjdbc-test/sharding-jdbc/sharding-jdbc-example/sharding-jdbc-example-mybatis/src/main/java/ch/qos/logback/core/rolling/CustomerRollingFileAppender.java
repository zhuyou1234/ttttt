package ch.qos.logback.core.rolling;

@SuppressWarnings("rawtypes")
public class CustomerRollingFileAppender extends ch.qos.logback.core.rolling.RollingFileAppender{
	
	@Override
	public void setFile(String file) {
		int lastIndexOf = file.lastIndexOf(".");
		String fileName = file.substring(0, lastIndexOf) +  System.getProperty("customer_property_file_prefix", "") + ".log";
		super.setFile(fileName);
	}

}

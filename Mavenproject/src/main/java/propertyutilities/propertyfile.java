package propertyutilities;

import java.io.FileInputStream;
import java.util.Properties;

public class propertyfile {
	
	public static String getvalueforkey(String key) throws Throwable{
	Properties configproperties = new Properties();
	FileInputStream fi=new FileInputStream("C:\\Users\\deepthi.m\\workspace\\Mavenproject\\propertyfile\\environment.properties");
    configproperties.load(fi);
    return configproperties.getProperty(key);
	
	}

}
 

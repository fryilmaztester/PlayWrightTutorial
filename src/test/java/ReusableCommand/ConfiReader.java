package ReusableCommand;

import java.io.FileInputStream;
import java.util.Properties;

public class ConfiReader {

    private static Properties properties;

    static {
        String path = "configuration.properties";
        try{
            FileInputStream file = new FileInputStream(path);
            properties = new Properties();
            properties.load(file);
        }catch (Exception e){
            System.out.println("Could not read config file");
        }
    }

    public static String getProperty(String key){
        return properties.getProperty(key);
    }
}

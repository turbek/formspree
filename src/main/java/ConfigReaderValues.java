import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * ConfigReaderValues class reads the email configurations
 * from the app.properties files
 */
public class ConfigReaderValues {
    private String result = "";
    private String username;
    private String password;
    private InputStream inputStream;

    /**
     * @return username
     */
    public String getUsername() {
        return username;
    }

    /**
     * @return password
     */
    public String getPassword() {
        return password;
    }

    /**
     * GetPropValues method reads the app.properties file
     * and assigns the values for the attributes
     *
     * @return String of properties
     * @throws IOException
     */
    public String getPropValues() throws IOException {

        try {
            Properties prop = new Properties();
            String propFileName = "config/app.properties";

            inputStream = getClass().getClassLoader().getResourceAsStream(propFileName);

            if (inputStream != null) {
                prop.load(inputStream);
            } else {
                throw new FileNotFoundException("property file '" + propFileName + "' not found in the classpath");
            }

            // get the property values
            username = prop.getProperty("username");
            password = prop.getProperty("password");

            result = "result:" + username + " " + password;

        } catch (Exception e) {
            System.out.println("Exception: " + e);
        } finally {
            inputStream.close();
        }
        return result;
    }

    @Override
    public String toString() {
        return "ConfigReaderValues{" +
                "result='" + result + '\'' +
                '}';
    }
}

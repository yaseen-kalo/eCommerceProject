package Common;


import java.io.FileInputStream;
import java.util.Properties;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class BaseClass {
    Properties prop;
    public static Logger logger;
    public BaseClass() {
        String filePath = System.getProperty("user.dir") + "/Configuration/config.properties";
        try {
            prop = new Properties();
            FileInputStream fis = new FileInputStream(filePath);
            prop.load(fis);

            logger = LogManager.getLogger("eCommerceProject");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public String getUserEmail() {
        String userEmail = prop.getProperty("userEmail");
        return  userEmail;
    }

    public String getUserPassword() {
        String userPassword = prop.getProperty("userPassword");
        return  userPassword;
    }
}

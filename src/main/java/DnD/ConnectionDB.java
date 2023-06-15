package DnD;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

public class ConnectionDB {
    public Connection getConnectionDB(){
        Properties properties = new Properties();
        Connection connection = null;
        try(FileInputStream fileInputStream = new FileInputStream("resources/security/config.properties")) {
            properties.load(fileInputStream);

            final String databaseURL = properties.getProperty("db.url");
            final String databaseUser = properties.getProperty("db.username");
            final String databasePassword = properties.getProperty("db.password");

            connection = DriverManager.getConnection(databaseURL, databaseUser, databasePassword);
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return connection;
    }
}

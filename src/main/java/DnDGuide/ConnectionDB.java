package DnDGuide;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionDB {
    public Connection getConnectionDB(){
        final String databaseURL = "jdbc:mysql://localhost:3306/dndEnemies";
        final String databaseUser = "Elexlex";
        final String databasePassword = "Phoenix$$2004";
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(databaseURL, databaseUser, databasePassword);
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return connection;
    }
}

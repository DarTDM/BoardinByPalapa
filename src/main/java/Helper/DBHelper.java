package Helper;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBHelper {

    public Connection getConnection() throws ClassNotFoundException, SQLException {
        Config config = new Config();
        String stringConnection = "jdbc:mysql://" + config.getDbHost() + ":"
                + config.getDbPort() + "/" + config.getDbName() + "?autoReconnect=true&useSSL=false";
        Class.forName("com.mysql.cj.jdbc.Driver");
        return DriverManager.getConnection(stringConnection, config.getDbUser(), config.getDbPass());
    }
}

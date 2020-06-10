package model;

import java.sql.Connection;
import java.sql.SQLException;
import org.apache.commons.dbcp2.BasicDataSource;

/**
 *
 * @author JorgeLPR
 */
public class ConnectionPoolSqlServer {
    
    private final String DB="paises";
    private final String SERVER = "DESKTOP-733KL2A\\SQLEXPRESS";     
    private final String URL="jdbc:sqlserver://"+SERVER+":1433;database="+DB;
    private final String USER="sa";
    private final String PASS="123";
    
    private static ConnectionPoolSqlServer dataSource;
    private BasicDataSource basicDataSource=null;
    
    private ConnectionPoolSqlServer(){
     
        basicDataSource = new BasicDataSource();
        basicDataSource.setDriverClassName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        basicDataSource.setUsername(USER);
        basicDataSource.setPassword(PASS);
        basicDataSource.setUrl(URL);
        
        basicDataSource.setMinIdle(5);
        basicDataSource.setMaxIdle(20);
        basicDataSource.setMaxTotal(50);
        basicDataSource.setMaxWaitMillis(-1);
        
    }
    
    public static ConnectionPoolSqlServer getInstance() {
        if (dataSource == null) {
            dataSource = new ConnectionPoolSqlServer();
            return dataSource;
        } else {
            return dataSource;
        }
    }

    public Connection getConnection() throws SQLException{
      return this.basicDataSource.getConnection();
    }
    
    public void closeConnection(Connection connection) throws SQLException {
        connection.close();
    }    
    
    
    
}



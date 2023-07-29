package kg.kstu.sweetshop;

import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.DriverManager;

@Component
public class Connector {

    public Connection connect() {
        try{
            String connString = "jdbc:sqlserver://localhost:1433;databaseName=SecondModule;username=rashid;password=12345";
            DriverManager.registerDriver(new com.microsoft.sqlserver.jdbc.SQLServerDriver());
            Connection conn = DriverManager.getConnection(connString);
            return conn;
        } catch(Exception e){
            e.printStackTrace();
            return null;
        }
    }
}

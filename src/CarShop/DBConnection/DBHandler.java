package CarShop.DBConnection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBHandler extends configs{
    Connection connection;

    public Connection getConnection() {
        String connectionString= "jdbc:mysql://"+this.dbhost+":"+this.dbport+"/"+this.dbname;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        try {
            connection = DriverManager.getConnection(connectionString,this.dbuser,this.dbpass);
        } catch (SQLException throwables) {
            System.out.println("connection fail");
        }
        return connection;
    }
}

package testsmartapp;
import java.sql.*;

public class ConnectionDB {
    Connection c;
    Statement s;
    static final String DB_URL = "jdbc:postgresql://127.0.0.1:5432/test_db";
    static final String USER = "postgres";
    static final String PASS = "0672089596";
    public ConnectionDB(){
        System.out.println("Testing connection to PostgreSQL JDBC");
        try {
            Class.forName("org.postgresql.Driver");
            c = DriverManager.getConnection(DB_URL, USER, PASS);
            s = c.createStatement();
        }
        catch (Exception e){
            System.out.println("PostgreSQL JDBC Driver is not found. Include it in your library path ");
            e.printStackTrace();
        }
    }
}

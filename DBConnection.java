import java.sql.*;

public class DBConnection {

    public static Connection getConnection(){

        Connection con = null;

        try {
            Class.forName("org.sqlite.JDBC");
            con = DriverManager.getConnection("jdbc:sqlite:a38.db");
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }

        return con;
    }

}
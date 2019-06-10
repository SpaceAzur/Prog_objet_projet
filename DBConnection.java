import java.sql.*;

public class DBConnection {

    public static void main(String[] args) throws Exception {

        Connection con = null;

        try {
            Class.forName("org.sqlite.JDBC");
            con = DriverManager.getConnection("jdbc:sqlite:a38.db");
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
        System.out.println("Opened database successfully");

        String requete = "SELECT * FROM utilisateurs";

        Statement statement = con.createStatement();
        ResultSet rs = statement.executeQuery(requete);
        ResultSetMetaData rsmd = rs.getMetaData();
        System.out.println(requete);
        int columnsNumber = rsmd.getColumnCount();
        while (rs.next()) {
            for (int i = 1; i <= columnsNumber; i++) {
                if (i > 1)
                    System.out.print(",  ");
                String columnValue = rs.getString(i);
                System.out.print(columnValue + " " + rsmd.getColumnName(i));
            }
        }
        System.out.println("");
    }

}
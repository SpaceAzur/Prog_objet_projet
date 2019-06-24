package a38;

import java.sql.*;

public class A38 {

    public static void main(String[] args) {

        try {

            Connection con = null;
            Class.forName("org.sqlite.JDBC");
            con = DriverManager.getConnection("jdbc:sqlite:a38.db");
            Modele mod = new Modele(con);
            Interface gui = new Interface(mod);

        } catch (Exception e) {

            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);

        }

    }

}
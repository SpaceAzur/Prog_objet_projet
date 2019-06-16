import java.sql.Connection;

public class A38 {

    public static void main(String[] args) {

        Connection conn = DBConnection.getConnection();
        Modele mod = new Modele(conn);

        Interface gui = new Interface(mod);

    }

}
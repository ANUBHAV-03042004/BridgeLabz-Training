package connection;

import java.sql.Connection;
import connection.DBConnection;

public class TestConnection {
    public static void main(String[] args) {

        Connection con = DBConnection.getConnection();

        if (con != null) {
            System.out.println("Connected Successfully!");
        } else {
            System.out.println("Connection Failed!");
        }
    }
}

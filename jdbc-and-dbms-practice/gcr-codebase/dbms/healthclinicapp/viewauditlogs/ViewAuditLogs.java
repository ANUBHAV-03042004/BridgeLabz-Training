package healthclinicapp.viewauditlogs;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.Scanner;

import connection.DBConnection;

public class ViewAuditLogs {

    public static void main(String[] args) throws Exception {

        Scanner sc = new Scanner(System.in);

        System.out.print("Filter by User (or press Enter): ");
        String user = sc.nextLine();

        System.out.print("Filter by Table Name (or press Enter): ");
        String table = sc.nextLine();

        System.out.print("Start Date (YYYY-MM-DD): ");
        String start = sc.nextLine();

        System.out.print("End Date (YYYY-MM-DD): ");
        String end = sc.nextLine();

        String sql = """
            SELECT *
            FROM audit_log
            WHERE (? = '' OR user_name = ?)
              AND (? = '' OR table_name = ?)
              AND action_time BETWEEN ? AND ?
            ORDER BY action_time DESC
        """;

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, user);
            ps.setString(2, user);
            ps.setString(3, table);
            ps.setString(4, table);
            ps.setTimestamp(5, Timestamp.valueOf(start + " 00:00:00"));
            ps.setTimestamp(6, Timestamp.valueOf(end + " 23:59:59"));

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                System.out.println(
                        rs.getInt("log_id") + " | " +
                        rs.getString("user_name") + " | " +
                        rs.getString("table_name") + " | " +
                        rs.getString("operation_type") + " | " +
                        rs.getTimestamp("action_time")
                );
            }
        }

        sc.close();
    }
}
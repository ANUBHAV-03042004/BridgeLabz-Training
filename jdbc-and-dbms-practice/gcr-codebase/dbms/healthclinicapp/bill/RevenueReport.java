package healthclinicapp.bill;

import java.sql.*;
import java.util.Scanner;
import connection.DBConnection;

public class RevenueReport {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.print("Start Date (YYYY-MM-DD): ");
        String start = sc.nextLine();

        System.out.print("End Date (YYYY-MM-DD): ");
        String end = sc.nextLine();

        String sql = """
            SELECT d.doctor_id,
                   CONCAT(d.first_name,' ',d.last_name) AS doctor_name,
                   SUM(b.total_amount) AS revenue
            FROM bills b
            JOIN visits v ON b.visit_id = v.visit_id
            JOIN doctors d ON v.doctor_id = d.doctor_id
            WHERE b.payment_status='PAID'
              AND b.payment_date BETWEEN ? AND ?
            GROUP BY d.doctor_id, doctor_name
            HAVING revenue > 0
        """;

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setDate(1, Date.valueOf(start));
            ps.setDate(2, Date.valueOf(end));

            ResultSet rs = ps.executeQuery();

            System.out.println("\nRevenue Report:");
            boolean hasRevenue=false;
            while (rs.next()) {
            	hasRevenue=true;
                System.out.println(
                        "Doctor: " + rs.getString("doctor_name") +
                        " | Revenue: ₹" + rs.getDouble("revenue")
                );
            }
            if(!hasRevenue)System.out.println("No Revenue Report Got");

        } catch (Exception e) {
            e.printStackTrace();
        }

        sc.close();
    }
}
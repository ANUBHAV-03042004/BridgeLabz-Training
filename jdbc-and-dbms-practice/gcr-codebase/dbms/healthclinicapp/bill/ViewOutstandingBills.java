package healthclinicapp.bill;

import java.sql.*;
import connection.DBConnection;

public class ViewOutstandingBills {

    public static void main(String[] args) {

        String sql = """
            SELECT p.patient_id,
                   CONCAT(p.first_name,' ',p.last_name) AS name,
                   COUNT(b.bill_id) AS total_bills,
                   SUM(b.total_amount) AS total_due
            FROM bills b
            JOIN visits v ON b.visit_id = v.visit_id
            JOIN patients p ON v.patient_id = p.patient_id
            WHERE b.payment_status='UNPAID'
            GROUP BY p.patient_id, name
        """;

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            System.out.println("Outstanding Bills Summary:");
            boolean hasOutstandingBills=false;
            while (rs.next()) {
            	hasOutstandingBills=true;
                System.out.println(
                        "Patient: " + rs.getString("name") +
                        " | Bills: " + rs.getInt("total_bills") +
                        " | Total Due: ₹" + rs.getDouble("total_due")
                );
            }
            if(!hasOutstandingBills) System.out.println("No Outstanding Bills found");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
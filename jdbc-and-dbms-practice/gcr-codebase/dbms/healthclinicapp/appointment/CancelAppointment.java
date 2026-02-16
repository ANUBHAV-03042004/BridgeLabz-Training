package healthclinicapp.appointment;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

import connection.DBConnection;
import healthclinicapp.audit.AddAuditLog;

public class CancelAppointment {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.print("Appointment ID to cancel: ");
        int appointmentId = sc.nextInt();
        sc.nextLine();

        System.out.print("Reason: ");
        String remarks = sc.nextLine();

        Connection con = null;

        try {
            con = DBConnection.getConnection();
            con.setAutoCommit(false);   

          
            String updateSql = """
                UPDATE appointments 
                SET status = 'CANCELLED'
                WHERE appointment_id = ?
            """;

            PreparedStatement updatePs = con.prepareStatement(updateSql);
            updatePs.setInt(1, appointmentId);

            int rows = updatePs.executeUpdate();

            if (rows == 0) {
                System.out.println("Appointment not found.");
                con.rollback();
                return;
            }

          
            AddAuditLog.addAuditLog(
                    con,
                    appointmentId,
                    "CANCELLED",
                    remarks
            );

        
            con.commit();

            System.out.println("Appointment cancelled successfully!");

        } catch (Exception e) {
            try {
                if (con != null) {
                    con.rollback();
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            e.printStackTrace();
        } finally {
            try {
                if (con != null) {
                    con.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            sc.close();
        }
    }
}
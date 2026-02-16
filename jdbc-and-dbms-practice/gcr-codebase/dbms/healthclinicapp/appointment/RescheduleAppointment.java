package healthclinicapp.appointment;

import java.sql.*;
import java.util.Scanner;

import connection.DBConnection;
import healthclinicapp.audit.AddAuditLog;

public class RescheduleAppointment {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.print("Appointment ID: ");
        int appointmentId = sc.nextInt();

        System.out.print("New Doctor ID: ");
        int doctorId = sc.nextInt();
        sc.nextLine();

        System.out.print("New Date (YYYY-MM-DD): ");
        String date = sc.nextLine();

        System.out.print("New Time (HH:MM:SS): ");
        String time = sc.nextLine();

        Connection con = null;

        try {
            con = DBConnection.getConnection();
            con.setAutoCommit(false);   
         
            String checkSql = """
                SELECT COUNT(*) FROM appointments
                WHERE doctor_id = ?
                AND appointment_date = ?
                AND appointment_time = ?
                AND status = 'SCHEDULED'
                AND appointment_id != ?
            """;

            PreparedStatement checkPs = con.prepareStatement(checkSql);
            checkPs.setInt(1, doctorId);
            checkPs.setDate(2, Date.valueOf(date));
            checkPs.setTime(3, Time.valueOf(time));
            checkPs.setInt(4, appointmentId);

            ResultSet rs = checkPs.executeQuery();
            rs.next();

            if (rs.getInt(1) > 0) {
                System.out.println("New slot not available!");
                con.rollback();
                return;
            }

         
            String updateSql = """
                UPDATE appointments
                SET doctor_id = ?,
                    appointment_date = ?,
                    appointment_time = ?,
                    status = 'SCHEDULED'
                WHERE appointment_id = ?
            """;

            PreparedStatement updatePs = con.prepareStatement(updateSql);
            updatePs.setInt(1, doctorId);
            updatePs.setDate(2, Date.valueOf(date));
            updatePs.setTime(3, Time.valueOf(time));
            updatePs.setInt(4, appointmentId);

            int rows = updatePs.executeUpdate();

            if (rows == 0) {
                System.out.println("Appointment not found!");
                con.rollback();
                return;
            }

         
            AddAuditLog.addAuditLog(
                    con,
                    appointmentId,
                    "UPDATED",
                    "Appointment rescheduled to " + date + " " + time
            );

          
            con.commit();

            System.out.println("Appointment rescheduled successfully!");

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
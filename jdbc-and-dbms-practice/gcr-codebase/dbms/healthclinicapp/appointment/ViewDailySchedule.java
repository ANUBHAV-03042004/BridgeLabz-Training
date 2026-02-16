package healthclinicapp.appointment;

import java.sql.*;
import java.util.Scanner;

import connection.DBConnection;

public class ViewDailySchedule {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.print("Enter Date (YYYY-MM-DD): ");
        String date = sc.nextLine();

        String sql = """
            SELECT a.appointment_time,
                  CONCAT(p.first_name , ' ',p.last_name)AS patient_name,
                   CONCAT(d.first_name,' ',d.last_name) AS doctor_name,
                   a.status
            FROM appointments a
            JOIN patients p ON a.patient_id = p.patient_id
            JOIN doctors d ON a.doctor_id = d.doctor_id
            WHERE a.appointment_date = ?
            ORDER BY a.appointment_time
        """;

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setDate(1, Date.valueOf(date));
            ResultSet rs = ps.executeQuery();

            System.out.println("\nDaily Schedule for " + date + ":");
          

            boolean hasRecords = false;

            while (rs.next()) {
                hasRecords = true;

                System.out.printf("%-10s | %-15s | %-20s | %-10s%n",
                        rs.getTime("appointment_time"),
                        rs.getString("patient_name"),
                        rs.getString("doctor_name"),
                        rs.getString("status")
                );
            }

            if (!hasRecords) {
                System.out.println("No appointments scheduled on this date.");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        sc.close();
    }
}
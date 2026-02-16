package healthclinicapp.appointment;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

import connection.DBConnection;

public class CheckDoctorAvailability {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.print("Doctor ID: ");
        int doctorId = sc.nextInt();
        sc.nextLine();

        System.out.print("Date (YYYY-MM-DD): ");
        String date = sc.nextLine();

        String sql = """
            SELECT appointment_time,
                   COUNT(*) AS total_bookings
            FROM appointments
            WHERE doctor_id = ?
            AND appointment_date = ?
            AND status = 'SCHEDULED'
            GROUP BY appointment_time
            ORDER BY appointment_time
        """;

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, doctorId);
            ps.setDate(2, Date.valueOf(date));

            ResultSet rs = ps.executeQuery();

            System.out.println("\nDoctor Schedule on " + date + ":");

            boolean hasRecords = false;

            while (rs.next()) {
                hasRecords = true;

                System.out.println(
                        rs.getTime("appointment_time") +
                        " → Total Bookings: " +
                        rs.getInt("total_bookings")
                );
            }

            if (!hasRecords) {
                System.out.println("No appointments scheduled for this date.");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        sc.close();
    }
}
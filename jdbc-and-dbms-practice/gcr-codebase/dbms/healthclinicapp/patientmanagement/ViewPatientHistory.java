package healthclinicapp.patientmanagement;

import java.sql.*;
import java.util.Scanner;
import connection.DBConnection;
public class ViewPatientHistory {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        System.out.print("Enter Patient ID: ");
        int patientId = sc.nextInt();

        String sql = """
            SELECT v.visit_date,
                   d.first_name,
                   d.last_name,
                   v.diagnosis
            FROM visits v
            JOIN appointments a ON v.appointment_id = a.appointment_id
            JOIN doctors d ON a.doctor_id = d.doctor_id
            WHERE a.patient_id = ?
            ORDER BY v.visit_date ASC
        """;

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, patientId);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                System.out.println("\nVisit Date: " + rs.getDate("visit_date"));
                System.out.println("Doctor: " +
                        rs.getString("first_name") + " " +
                        rs.getString("last_name"));
                System.out.println("Diagnosis: " + rs.getString("diagnosis"));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        sc.close();
    }
}

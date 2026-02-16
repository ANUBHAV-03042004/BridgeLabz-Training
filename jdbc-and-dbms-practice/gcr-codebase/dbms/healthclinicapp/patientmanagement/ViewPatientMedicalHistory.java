package healthclinicapp.patientmanagement;

import java.sql.*;
import java.util.Scanner;
import connection.DBConnection;

public class ViewPatientMedicalHistory {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.print("Enter Patient ID: ");
        int patientId = sc.nextInt();

        String sql = """
            SELECT v.visit_id,
                   v.visit_date,
                   v.diagnosis,
                   p.medicine_name,
                   p.dosage,
                   p.duration
            FROM visits v
            LEFT JOIN prescriptions p 
                   ON v.visit_id = p.visit_id
            WHERE v.patient_id = ?
            ORDER BY v.visit_date DESC
        """;

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, patientId);
            ResultSet rs = ps.executeQuery();

            System.out.println("\nMedical History:");
         

            while (rs.next()) {
                System.out.println(
                        "Visit ID: " + rs.getInt("visit_id") +
                        " | Date: " + rs.getDate("visit_date") +
                        " | Diagnosis: " + rs.getString("diagnosis") +
                        " | Medicine: " + rs.getString("medicine_name") +
                        " | Dosage: " + rs.getString("dosage") +
                        " | Duration: " + rs.getString("duration")
                );
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        sc.close();
    }
}
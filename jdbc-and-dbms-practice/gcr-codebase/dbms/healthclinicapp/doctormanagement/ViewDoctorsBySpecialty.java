package healthclinicapp.doctormanagement;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

import connection.DBConnection;

public class ViewDoctorsBySpecialty {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.print("Enter Specialty Name: ");
        String specialty = sc.nextLine();

        String sql = """
            SELECT d.doctor_id,
                   CONCAT(d.first_name,' ',d.last_name) AS doctor_name,
                   s.specialty_name,
                   d.consultation_fee,
                   a.appointment_date,
                   a.appointment_time
            FROM doctors d
            JOIN specialities s ON d.specialty_id = s.specialty_id
            LEFT JOIN appointments a 
                ON d.doctor_id = a.doctor_id 
                AND a.status='SCHEDULED'
            WHERE s.specialty_name = ?
            AND d.is_active = TRUE
            ORDER BY a.appointment_date
        """;

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, specialty);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

              
                System.out.println("Doctor: " + rs.getString("doctor_name"));
                System.out.println("Fee: " + rs.getDouble("consultation_fee"));
                System.out.println("Specialty: " + rs.getString("specialty_name"));
                System.out.println("Next Appointment Date: " 
                        + rs.getDate("appointment_date"));
                System.out.println("Time: " 
                        + rs.getTime("appointment_time"));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        sc.close();
    }
}
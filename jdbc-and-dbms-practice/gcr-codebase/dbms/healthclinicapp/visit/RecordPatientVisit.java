package healthclinicapp.visit;

import java.sql.*;
import java.util.Scanner;
import connection.DBConnection;

public class RecordPatientVisit {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.print("Appointment ID: ");
        int appointmentId = sc.nextInt();

        System.out.print("Patient ID: ");
        int patientId = sc.nextInt();

        System.out.print("Doctor ID: ");
        int doctorId = sc.nextInt();
        sc.nextLine();

        System.out.print("Diagnosis: ");
        String diagnosis = sc.nextLine();

        System.out.print("Notes: ");
        String notes = sc.nextLine();

        try (Connection con = DBConnection.getConnection()) {

            con.setAutoCommit(false);  

            String visitSql = """
                INSERT INTO visits 
                (appointment_id, patient_id, doctor_id, visit_date, diagnosis, notes)
                VALUES (?, ?, ?, CURDATE(), ?, ?)
            """;

            PreparedStatement visitPs =
                    con.prepareStatement(visitSql, Statement.RETURN_GENERATED_KEYS);

            visitPs.setInt(1, appointmentId);
            visitPs.setInt(2, patientId);
            visitPs.setInt(3, doctorId);
            visitPs.setString(4, diagnosis);
            visitPs.setString(5, notes);

            visitPs.executeUpdate();

         
            ResultSet rs = visitPs.getGeneratedKeys();
            rs.next();
            int visitId = rs.getInt(1);

        
            String updateSql = """
                UPDATE appointments
                SET status = 'COMPLETED'
                WHERE appointment_id = ?
            """;

            PreparedStatement updatePs = con.prepareStatement(updateSql);
            updatePs.setInt(1, appointmentId);
            updatePs.executeUpdate();

            con.commit();  

            System.out.println("Visit recorded successfully! Visit ID: " + visitId);

        } catch (Exception e) {
            e.printStackTrace();
        }

        sc.close();
    }
}
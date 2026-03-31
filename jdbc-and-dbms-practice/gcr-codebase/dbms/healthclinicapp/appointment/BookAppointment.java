package healthclinicapp.appointment;

import java.sql.*;
import java.util.Scanner;
import connection.DBConnection;
import healthclinicapp.audit.AddAuditLog;

public class BookAppointment {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        try (Connection con = DBConnection.getConnection()) {

            String doctorSql = """
                SELECT doctor_id, CONCAT(first_name,' ',last_name) AS name
                FROM doctors
                WHERE is_active = TRUE
            """;

            Statement stmt = con.createStatement();
            ResultSet doctorRs = stmt.executeQuery(doctorSql);

            System.out.println("\nAvailable Doctors:");
            while (doctorRs.next()) {
                System.out.println(
                        doctorRs.getInt("doctor_id") + " - " +
                        doctorRs.getString("name"));
            }

        
            System.out.print("\nEnter Patient ID: ");
            int patientId = sc.nextInt();

            System.out.print("Enter Doctor ID: ");
            int doctorId = sc.nextInt();
            sc.nextLine();

            System.out.print("Enter Appointment Date (YYYY-MM-DD): ");
            String date = sc.nextLine();

            System.out.print("Enter Appointment Time (HH:MM:SS): ");
            String time = sc.nextLine();

         
            String checkSql = """
                SELECT COUNT(*) FROM appointments
                WHERE doctor_id = ?
                AND appointment_date = ?
                AND appointment_time = ?
                AND status = 'SCHEDULED'
            """;

            PreparedStatement checkPs = con.prepareStatement(checkSql);
            checkPs.setInt(1, doctorId);
            checkPs.setDate(2, Date.valueOf(date));
            checkPs.setTime(3, Time.valueOf(time));

            ResultSet rs = checkPs.executeQuery();
            rs.next();

            if (rs.getInt(1) > 0) {
                System.out.println("Doctor not available at this time!");
                return;
            }

          
            con.setAutoCommit(false);

           
            String insertSql = """
                INSERT INTO appointments
                (patient_id, doctor_id, appointment_date, appointment_time, status)
                VALUES (?, ?, ?, ?, 'SCHEDULED')
            """;

            PreparedStatement insertPs =
                    con.prepareStatement(insertSql, Statement.RETURN_GENERATED_KEYS);

            insertPs.setInt(1, patientId);
            insertPs.setInt(2, doctorId);
            insertPs.setDate(3, Date.valueOf(date));
            insertPs.setTime(4, Time.valueOf(time));

            int rowsInserted = insertPs.executeUpdate();

            if (rowsInserted == 0) {
                throw new SQLException("Failed to insert appointment.");
            }

          
        
            ResultSet generatedKeys = insertPs.getGeneratedKeys();
            int appointmentId = 0;

            if (generatedKeys.next()) {
                appointmentId = generatedKeys.getInt(1);
            } else {
                throw new SQLException("Failed to retrieve appointment ID.");
            }

           
            AddAuditLog.addAuditLog(
                    con,
                    appointmentId,
                    "CREATED",
                    "Appointment booked successfully"
            );

          
            con.commit();

            System.out.println("Appointment booked successfully!");

        } catch (Exception e) {
            e.printStackTrace();
            try {
                Connection con = DBConnection.getConnection();
                if (con != null) {
                    con.rollback();
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }

        sc.close();
    }
}
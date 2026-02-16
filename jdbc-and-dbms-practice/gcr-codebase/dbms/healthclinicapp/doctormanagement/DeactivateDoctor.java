package healthclinicapp.doctormanagement;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

import connection.DBConnection;

public class DeactivateDoctor {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        System.out.print("Doctor ID to deactivate: ");
        int doctorId = sc.nextInt();

        try (Connection con = DBConnection.getConnection()) {

            con.setAutoCommit(false);

            String checkSql = """
                SELECT COUNT(*) FROM appointments
                WHERE doctor_id=?
                AND appointment_date>=CURDATE()
                AND status='SCHEDULED'
            """;

            PreparedStatement checkPs = con.prepareStatement(checkSql);
            checkPs.setInt(1, doctorId);

            ResultSet rs = checkPs.executeQuery();
            rs.next();

            if (rs.getInt(1) > 0) {
                System.out.println("Cannot deactivate. Future appointments exist.");
                con.rollback();
                return;
            }

            String updateSql = "UPDATE doctors SET is_active=false WHERE doctor_id=?";
            PreparedStatement updatePs = con.prepareStatement(updateSql);
            updatePs.setInt(1, doctorId);
            updatePs.executeUpdate();

            con.commit();
            System.out.println("Doctor deactivated successfully.");

        } catch (Exception e) {
            e.printStackTrace();
        }

        sc.close();
    }
}
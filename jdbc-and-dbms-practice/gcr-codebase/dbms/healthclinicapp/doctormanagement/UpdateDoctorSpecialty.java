package healthclinicapp.doctormanagement;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

import connection.DBConnection;

public class UpdateDoctorSpecialty {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        try (Connection con = DBConnection.getConnection()) {

            con.setAutoCommit(false);

            // Show Specialties
            ResultSet rs = con.createStatement()
                    .executeQuery("SELECT * FROM specialities");

            System.out.println("Available Specialties:");
            while (rs.next()) {
                System.out.println(rs.getInt("specialty_id") + " - " 
                        + rs.getString("specialty_name"));
            }

            System.out.print("Doctor ID: ");
            int doctorId = sc.nextInt();

            System.out.print("New Specialty ID: ");
            int specialtyId = sc.nextInt();

            String sql = "UPDATE doctors SET specialty_id=? WHERE doctor_id=?";

            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, specialtyId);
            ps.setInt(2, doctorId);

            int rows = ps.executeUpdate();

            if (rows > 0) {
                con.commit();
                System.out.println("Specialty updated successfully!");
            } else {
                con.rollback();
                System.out.println("Doctor not found!");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        sc.close();
    }
}
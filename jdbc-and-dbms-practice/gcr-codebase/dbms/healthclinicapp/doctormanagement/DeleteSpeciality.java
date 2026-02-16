package healthclinicapp.doctormanagement;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

import connection.DBConnection;

public class DeleteSpeciality {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.print("Enter Specialty ID to delete: ");
        int id = sc.nextInt();

        String checkExistsSql = "SELECT COUNT(*) FROM specialities WHERE specialty_id=?";
        String checkDoctorSql = "SELECT COUNT(*) FROM doctors WHERE specialty_id=?";
        String deleteSql = "DELETE FROM specialities WHERE specialty_id=?";

        try (Connection con = DBConnection.getConnection()) {


            try (PreparedStatement psExists = con.prepareStatement(checkExistsSql)) {
                psExists.setInt(1, id);
                ResultSet rsExists = psExists.executeQuery();
                rsExists.next();

                if (rsExists.getInt(1) == 0) {
                    System.out.println("Specialty ID not found.");
                    return;
                }
            }

       
            try (PreparedStatement psCheck = con.prepareStatement(checkDoctorSql)) {
                psCheck.setInt(1, id);
                ResultSet rs = psCheck.executeQuery();
                rs.next();

                if (rs.getInt(1) > 0) {
                    System.out.println("Cannot delete. Doctors are assigned to this specialty.");
                    return;
                }
            }


            try (PreparedStatement psDelete = con.prepareStatement(deleteSql)) {
                psDelete.setInt(1, id);
                int rows = psDelete.executeUpdate();

                if (rows > 0) {
                    System.out.println("Specialty deleted successfully.");
                } else {
                    System.out.println("Deletion failed.");
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        sc.close();
    }
}
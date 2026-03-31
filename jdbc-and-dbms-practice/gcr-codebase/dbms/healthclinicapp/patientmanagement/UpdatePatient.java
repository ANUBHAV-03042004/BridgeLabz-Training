package healthclinicapp.patientmanagement;

import java.sql.*;
import java.util.Scanner;
import connection.DBConnection;

public class UpdatePatient {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        try (Connection con = DBConnection.getConnection()) {

            System.out.print("Enter Patient ID to update: ");
            int patientId = sc.nextInt();
            sc.nextLine();

            String selectSQL = "SELECT * FROM patients WHERE patient_id=?";
            PreparedStatement selectPs = con.prepareStatement(selectSQL);
            selectPs.setInt(1, patientId);

            ResultSet rs = selectPs.executeQuery();

            if (!rs.next()) {
                System.out.println("Patient not found!");
                return;
            }

            System.out.println("\nCurrent Details:");
            System.out.println("Name: " + rs.getString("first_name") + " " + rs.getString("last_name"));
            System.out.println("Phone: " + rs.getString("phone"));

            System.out.println("\nEnter new values (Press Enter to keep old)");
            
            System.out.print("FirstName: ");
            String newFirstName = sc.nextLine();
            if(newFirstName.isEmpty()) newFirstName = rs.getString("first_name");
            
            System.out.print("LastName: ");
            String newLastName = sc.nextLine();
            if(newLastName.isEmpty()) newLastName = rs.getString("last_name");
            
            System.out.print("Date Of Birth(yyyy-mm-dd): ");
            String new_dob = sc.nextLine();
            if(new_dob.isEmpty())  new_dob= rs.getDate(new_dob).toString();
            Date newDate = Date.valueOf(new_dob);
            
            
            System.out.print("Gender(MALE/FEMALE/OTHER): ");
            String newGender = sc.nextLine().toUpperCase();
            if(newGender.isEmpty()) newGender = rs.getString("gender");
            
            System.out.print("New Phone: ");
            String phone = sc.nextLine();
            if (phone.isEmpty()) phone = rs.getString("phone");

            System.out.print("Email: ");
            String newEmail = sc.nextLine();
            if(newEmail.isEmpty()) newEmail = rs.getString("email");
           
            
            System.out.print("New Address: ");
            String address = sc.nextLine();
            if (address.isEmpty()) address = rs.getString("address");

            System.out.print("BloodGroup: ");
            String newBloodGroup = sc.nextLine();
            if(newBloodGroup.isEmpty()) newBloodGroup = rs.getString("blood_group");
            
            String updateSQL = "UPDATE patients SET first_name=?,last_name=?,dob=?,gender=?,phone=?,email=?,address=?,blood_group=? WHERE patient_id=?";

            PreparedStatement updatePs = con.prepareStatement(updateSQL);
            updatePs.setString(1, newFirstName);
            updatePs.setString(2, newLastName);
            updatePs.setDate(3, newDate);
            updatePs.setString(4, newGender);
            updatePs.setString(5, phone);
            updatePs.setString(6, newEmail);
            updatePs.setString(7, address);
            updatePs.setString(8, newBloodGroup);
            updatePs.setInt(9, patientId);

            updatePs.executeUpdate();

            System.out.println("Patient Updated Successfully!");

        } catch (Exception e) {
            e.printStackTrace();
        }

        sc.close();
    }
}

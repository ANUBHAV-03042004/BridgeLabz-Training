package healthclinicapp.patientmanagement;

import java.sql.*;
import java.util.Scanner;
import connection.DBConnection;

public class SearchPatient {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        try (Connection con = DBConnection.getConnection()) {

            System.out.println("\nSearch By:");
            System.out.println("1. ID");
            System.out.println("2. Phone");
            System.out.println("3. Name");
            int choice = sc.nextInt();
            sc.nextLine();

            PreparedStatement ps = null;

            switch (choice) {

                case 1:
                    System.out.print("Enter ID: ");
                    int id = sc.nextInt();
                    ps = con.prepareStatement("SELECT * FROM patients WHERE patient_id=?");
                    ps.setInt(1, id);
                    break;

                case 2:
                    System.out.print("Enter Phone: ");
                    String phone = sc.nextLine();
                    ps = con.prepareStatement("SELECT * FROM patients WHERE phone=?");
                    ps.setString(1, phone);
                    break;

                case 3:
                    System.out.print("Enter Name: ");
                    String name = sc.nextLine();
                    ps = con.prepareStatement("""
                        SELECT * FROM patients
                        WHERE first_name LIKE ? OR last_name LIKE ?
                    """);
                    ps.setString(1, "%" + name + "%");
                    ps.setString(2, "%" + name + "%");
                    break;
            }

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                System.out.println("\nPatient");
                System.out.println("ID: " + rs.getInt("patient_id"));
                System.out.println("Name: " + rs.getString("first_name") + " " + rs.getString("last_name"));
                System.out.println("Phone: " + rs.getString("phone"));
                System.out.println("Blood Group: " + rs.getString("blood_group"));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        sc.close();
    }
}

package healthclinicapp.patientmanagement;

import java.sql.*;
import java.util.Scanner;
import connection.DBConnection;

public class AddPatient {

    public static void main(String[] args) {

        String sql = """
            INSERT INTO patients 
            (first_name, last_name, dob, gender, phone, email, address, blood_group)
            VALUES (?, ?, ?, ?, ?, ?, ?, ?)
        """;

        Scanner sc = new Scanner(System.in);

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            System.out.print("Enter number of patients to add: ");
            int count = sc.nextInt();
            sc.nextLine();

            for (int i = 1; i <= count; i++) {

                System.out.println("\nEnter Details for Patient " + i );

                System.out.print("First Name: ");
                String firstName = sc.nextLine();

                System.out.print("Last Name: ");
                String lastName = sc.nextLine();

                System.out.print("DOB (yyyy-mm-dd): ");
                Date dob = Date.valueOf(sc.nextLine());

                System.out.print("Gender (MALE/FEMALE/OTHER): ");
                String gender = sc.nextLine().toUpperCase();

                System.out.print("Phone: ");
                String phone = sc.nextLine();

                System.out.print("Email: ");
                String email = sc.nextLine();

                System.out.print("Address: ");
                String address = sc.nextLine();

                System.out.print("Blood Group: ");
                String bloodGroup = sc.nextLine();

                ps.setString(1, firstName);
                ps.setString(2, lastName);
                ps.setDate(3, dob);
                ps.setString(4, gender);
                ps.setString(5, phone);
                ps.setString(6, email);
                ps.setString(7, address);
                ps.setString(8, bloodGroup);

                ps.addBatch();
            }

            ps.executeBatch();

            System.out.println("\n All patients inserted successfully!");

        } catch (Exception e) {
            e.printStackTrace();
        }

        sc.close();
    }
}

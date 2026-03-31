package healthclinicapp.doctormanagement;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.util.Scanner;
import connection.DBConnection;

public class InsertSpecialities {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        String sql = "INSERT INTO specialities (specialty_name, description) VALUES (?, ?)";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            System.out.print("How many specialties to insert? ");
            int count = sc.nextInt();
            sc.nextLine();

            for (int i = 1; i <= count; i++) {

                System.out.println("\nEnter details for Specialty " + i);

                System.out.print("Specialty Name: ");
                String name = sc.nextLine();

                System.out.print("Description: ");
                String desc = sc.nextLine();

                ps.setString(1, name);
                ps.setString(2, desc);

                ps.executeUpdate();
            }

            System.out.println("All specialties inserted successfully!");

        } catch (Exception e) {
            e.printStackTrace();
        }

        sc.close();
    }
}
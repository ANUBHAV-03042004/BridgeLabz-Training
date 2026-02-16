package healthclinicapp.prescription;

import java.sql.*;
import java.util.Scanner;
import connection.DBConnection;

public class AddPrescriptionDetails {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.print("Visit ID: ");
        int visitId = sc.nextInt();
        sc.nextLine();

        try (Connection con = DBConnection.getConnection()) {

            con.setAutoCommit(false);

            String sql = """
                INSERT INTO prescriptions
                (visit_id, medicine_name, dosage, duration, instructions)
                VALUES (?, ?, ?, ?, ?)
            """;

            PreparedStatement ps = con.prepareStatement(sql);

            while (true) {

                System.out.print("Medicine Name (or 'done'): ");
                String medicine = sc.nextLine();

                if (medicine.equalsIgnoreCase("done"))
                    break;

                System.out.print("Dosage: ");
                String dosage = sc.nextLine();

                System.out.print("Duration: ");
                String duration = sc.nextLine();

                System.out.print("Instructions: ");
                String instructions = sc.nextLine();

                ps.setInt(1, visitId);
                ps.setString(2, medicine);
                ps.setString(3, dosage);
                ps.setString(4, duration);
                ps.setString(5, instructions);

                ps.addBatch();  
            }

            ps.executeBatch();
            con.commit();

            System.out.println("Prescriptions added successfully!");

        } catch (Exception e) {
            e.printStackTrace();
        }

        sc.close();
    }
}
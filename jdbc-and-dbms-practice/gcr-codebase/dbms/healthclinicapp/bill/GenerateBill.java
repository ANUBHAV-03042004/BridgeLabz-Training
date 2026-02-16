package healthclinicapp.bill;

import java.sql.*;
import java.util.Scanner;
import connection.DBConnection;

public class GenerateBill {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.print("Visit ID: ");
        int visitId = sc.nextInt();

        System.out.print("Additional Charges: ");
        double additionalCharges = sc.nextDouble();

        try (Connection con = DBConnection.getConnection()) {

            String feeSql = """
                SELECT d.consultation_fee
                FROM visits v
                JOIN doctors d ON v.doctor_id = d.doctor_id
                WHERE v.visit_id = ?
            """;

            PreparedStatement feePs = con.prepareStatement(feeSql);
            feePs.setInt(1, visitId);
            ResultSet rs = feePs.executeQuery();

            if (!rs.next()) {
                System.out.println("Visit not found!");
                return;
            }

            double consultationFee = rs.getDouble("consultation_fee");
            double totalAmount = consultationFee + additionalCharges;

          
            String billSql = """
                INSERT INTO bills (visit_id, total_amount, payment_status)
                VALUES (?, ?, 'UNPAID')
            """;

            PreparedStatement billPs = con.prepareStatement(billSql);
            billPs.setInt(1, visitId);
            billPs.setDouble(2, totalAmount);
            billPs.executeUpdate();

            System.out.println("Bill Generated Successfully!");
            System.out.println("Total Amount: ₹" + totalAmount);

        } catch (Exception e) {
            e.printStackTrace();
        }

        sc.close();
    }
}
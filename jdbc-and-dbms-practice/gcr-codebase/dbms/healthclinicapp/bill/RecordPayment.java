package healthclinicapp.bill;

import java.sql.*;
import java.util.Scanner;
import connection.DBConnection;

public class RecordPayment {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.print("Bill ID: ");
        int billId = sc.nextInt();
        sc.nextLine();

        System.out.print("Payment Mode (CASH/UPI/CARD/ONLINE): ");
        String mode = sc.nextLine().toUpperCase();

        Connection con = null;

        try {
            con = DBConnection.getConnection();
            con.setAutoCommit(false); 

            String checkBill = """
                SELECT total_amount, payment_status 
                FROM bills 
                WHERE bill_id = ?
            """;

            PreparedStatement psCheck = con.prepareStatement(checkBill);
            psCheck.setInt(1, billId);
            ResultSet rs = psCheck.executeQuery();

            if (!rs.next()) {
                System.out.println("Bill not found!");
                return;
            }

            String status = rs.getString("payment_status");
            double amount = rs.getDouble("total_amount");

            if ("PAID".equalsIgnoreCase(status)) {
                System.out.println(" Bill is already paid!");
                return;
            }

            String updateBill = """
                UPDATE bills
                SET payment_status='PAID',
                    payment_date=CURDATE(),
                    payment_mode=?
                WHERE bill_id=?
            """;

            PreparedStatement psUpdate = con.prepareStatement(updateBill);
            psUpdate.setString(1, mode);
            psUpdate.setInt(2, billId);
            psUpdate.executeUpdate();

         
            String insertPayment = """
                INSERT INTO payment_transactions
                (bill_id, amount_paid, payment_mode)
                VALUES (?, ?, ?)
            """;

            PreparedStatement psInsert = con.prepareStatement(insertPayment);
            psInsert.setInt(1, billId);
            psInsert.setDouble(2, amount);
            psInsert.setString(3, mode);
            psInsert.executeUpdate();

            con.commit();

            System.out.println("Payment recorded successfully!");

        } catch (Exception e) {
            try {
                if (con != null) {
                    con.rollback(); 
                    System.out.println("Transaction rolled back.");
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }

            e.printStackTrace();

        } finally {
            try {
                if (con != null)
                    con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            sc.close();
        }
    }
}

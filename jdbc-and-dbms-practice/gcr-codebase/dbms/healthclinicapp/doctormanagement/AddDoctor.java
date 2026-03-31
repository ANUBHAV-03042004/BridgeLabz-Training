package healthclinicapp.doctormanagement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.*;
import connection.DBConnection;
public class AddDoctor {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        try (Connection con = DBConnection.getConnection()) {

    
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM specialities");

            System.out.println("Available Specialties:");
            while (rs.next()) {
                System.out.println(rs.getInt("specialty_id") + " - " 
                        + rs.getString("specialty_name"));
            }


            System.out.print("First Name: ");
            String firstName = sc.nextLine();

            System.out.print("Last Name: ");
            String lastName = sc.nextLine();

            System.out.print("Phone: ");
            String phone = sc.nextLine();

            System.out.print("Email: ");
            String email = sc.nextLine();

            System.out.print("Consultation Fee: ");
            double fee = sc.nextDouble();

            System.out.print("Specialty ID: ");
            int specialtyId = sc.nextInt();

       
            String sql = """
                INSERT INTO doctors 
                (first_name, last_name, phone, email, consultation_fee, specialty_id)
                VALUES (?, ?, ?, ?, ?, ?)
            """;

            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, firstName);
            ps.setString(2, lastName);
            ps.setString(3, phone);
            ps.setString(4, email);
            ps.setDouble(5, fee);
            ps.setInt(6, specialtyId);

            ps.executeUpdate();
            System.out.println("Doctor added successfully!");

        } catch (Exception e) {
            e.printStackTrace();
        }

        sc.close();
    }
}
package healthclinicapp.databasebackuptrigger;

import java.io.FileWriter;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;

import connection.DBConnection;

public class BackupUtility {

    public static void backupDatabase() throws Exception {

        try (Connection con = DBConnection.getConnection()) {

            DatabaseMetaData meta = con.getMetaData();

            ResultSet tables = meta.getTables(null, null, "%", new String[]{"TABLE"});

            while (tables.next()) {

                String tableName = tables.getString("TABLE_NAME");

                System.out.println("Backing up table: " + tableName);

                Statement st = con.createStatement();
                ResultSet rs = st.executeQuery("SELECT * FROM " + tableName);

                ResultSetMetaData rsMeta = rs.getMetaData();
                int columnCount = rsMeta.getColumnCount();
String filepath = "C:\\Users\\HP\\eclipse-workspace\\BridgeLabz-Training\\jdbc-and-dbms-practice\\gcr-codebase\\dbms\\backup\\backup.txt";
                while (rs.next()) {

                    for (int i = 1; i <= columnCount; i++) {
//                        System.out.print(rs.getString(i) + " | ");
                    	FileWriter writer = new FileWriter(filepath, true);
                    	writer.write(rs.getString(i) + " | ");
                    	writer.close();
                    }
                    System.out.println();
                }

            }
        }
    }
}
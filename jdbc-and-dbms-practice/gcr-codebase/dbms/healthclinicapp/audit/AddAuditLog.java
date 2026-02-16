package healthclinicapp.audit;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class AddAuditLog {
	public static void addAuditLog(Connection con,
	                               int appointmentId,
	                               String actionType,
	                               String remarks) throws SQLException {
	
	    String auditSql = "INSERT INTO appointment_audit " +
	                      "(appointment_id, action_type, remarks) " +
	                      "VALUES (?, ?, ?)";
	
	    PreparedStatement ps = con.prepareStatement(auditSql);
	    ps.setInt(1, appointmentId);
	    ps.setString(2, actionType); // CREATED, UPDATED, CANCELLED
	    ps.setString(3, remarks);
	
	    ps.executeUpdate();
	}
}


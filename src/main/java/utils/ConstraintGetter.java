package utils;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import descriptor.Constraint;


public interface ConstraintGetter {
	
	public List<Constraint> getConstraints(Connection conn, String schema, String tableName) throws SQLException;

}

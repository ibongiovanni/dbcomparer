package utils;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import descriptor.Constraint;

public class CGPostgres implements ConstraintGetter {

	public CGPostgres(){}
	
	public List<Constraint> getConstraints(Connection conn, String schema) throws SQLException{
		try{
			List<Constraint> listConst = new ArrayList<Constraint>();
			Statement stmt = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);     
			String query = "select check_clause from information_schema.check_constraints where constraint_schema ="+schema+"';";
			ResultSet rs = stmt.executeQuery(query);
        	rs.beforeFirst();
        	while(rs.next()){
        		String clause   = rs.getString("check_clause").toLowerCase();		  
        		Constraint cons = new Constraint(clause);
        		listConst.add(cons);
        	}
        	stmt.close();
        	return listConst; 
		}catch(Exception cnfe) {System.err.println("Error");}
	 	return null;   	 		 	
	}
}

package utils;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import descriptor.Constraint;
import descriptor.Trigger;

public class CGPostgres implements ConstraintGetter {

	public CGPostgres(){}
	
	public List<Constraint> getConstraints(Connection conn, String schema, String tableName) throws SQLException{
		try{
			List<Constraint> listCons = new ArrayList<Constraint>();
            String check ="CHECK";
            Statement stmt = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            String query = "SELECT tc.constraint_schema, tc.constraint_name, tc.table_name, cc.check_clause FROM information_schema.table_constraints as tc JOIN information_schema.check_constraints as cc ON tc.constraint_name = cc.constraint_name WHERE tc.table_name ='"+tableName+"' and tc.constraint_schema ='"+schema+"' and tc.constraint_type ='"+check+"';";
            ResultSet rs = stmt.executeQuery(query);
            rs.beforeFirst();
            while(rs.next()){
            	//rs.getString("constraint_schema");
            	//rs.getString("table_name");
                String name   = rs.getString("constraint_name");
                String clause = rs.getString("check_clause");
                
                Constraint cons = new Constraint(name,clause);
                listCons.add(cons);
            }
            stmt.close();
            return listCons;
		}catch(Exception cnfe) {System.err.println("Error");}
	 	return null;   	 		 	
	}
}

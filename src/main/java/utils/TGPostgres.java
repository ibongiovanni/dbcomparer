package utils;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import descriptor.Trigger;

public class TGPostgres implements TriggerGetter{
	
	public TGPostgres(){}
	
	public List<Trigger> getTriggers(Connection conn, String schema, String tableName) throws SQLException{
		try{
			List<Trigger> listTrig = new ArrayList<Trigger>();
			Statement stmt = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);     
			String query = "select * from information_schema.triggers where trigger_schema = '"+schema+"' and event_object_table ='"+tableName+"';";
			ResultSet rs = stmt.executeQuery(query);
        	rs.beforeFirst();
        	while(rs.next()){
        		String name   = rs.getString("trigger_name").toLowerCase();		  //trigger name
        		String event  = rs.getString("event_manipulation").toLowerCase(); //insert-update-delete
        		String timing = rs.getString("action_timing").toLowerCase();	  //before-after
        		boolean tim;
    		
        		if(timing.equals("before")){ tim = true; }  //before = true 
        		else { tim = false; }						//after = false
    		
        		Trigger trigger = new Trigger(name, tim, event);
        		listTrig.add(trigger);
        	}
        	stmt.close();
        	return listTrig; 
		}catch(Exception cnfe) {System.err.println("Error");}
	 	return null;   
	}

}

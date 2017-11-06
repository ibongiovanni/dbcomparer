package utils;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import descriptor.*;

public class DBMaker {
	private DataBase db;
	private Connection conn;
	
	public DBMaker(Connection c, String dbName){
		conn = c;
		db = new DataBase(dbName);
	}
	
	public DataBase getDB(){
		return db;
	}
	
	public void buildDB(){
		try{
			
            DatabaseMetaData metaData = conn.getMetaData();
            String catalogo = conn.getCatalog();
            String schema = db.getName();
            //String schema   = conn.getSchema();
        
            List<Tuple> listFK = new ArrayList<Tuple>(); //used to temporarily save information from FK's
            
            //I get the tables from the database.
            String[] tipo = {"TABLE"};
            ResultSet rsTables = metaData.getTables(catalogo,schema, null, tipo);
            while(rsTables.next()){ 
                String tableName = rsTables.getString(3);  //(3)TABLE_NAME 
            	Table table = new Table(tableName);
                
            	
            	//I get the columns from the current table. 
                ResultSet rsColumns = metaData.getColumns(null, null, tableName, null); 
                while(rsColumns.next() ){
                	Column col = new Column(rsColumns.getString(4),rsColumns.getString(6));
                	col.setTable(table);
                	table.addColumn(col);
                }    
	              
                
                //I get the primary keys from the current table.
                ResultSet primaryKeys = metaData.getPrimaryKeys(catalogo, schema, tableName); 
                while(primaryKeys.next()) {
                	Column pk = table.findColumn(primaryKeys.getString(4));
                	if (pk != null){
                		table.addPK(pk);
                	} 
                }
                
                
                //I get the foreign keys(part1).
                ResultSet foreignKeys = metaData.getImportedKeys(catalogo, schema, tableName);
                while(foreignKeys.next()){
                	String ref_table  =	foreignKeys.getString(3); //table referenced by the FK
                	String ref_column =	foreignKeys.getString(4); //column referenced by the FK          
                	String fk_column = foreignKeys.getString(8);
                	
                	Column col = table.findColumn(fk_column); //look for the column of the FK in the current table
                	ForeignKey fk = new ForeignKey();
                	fk.setFK(col);
                	
                	Tuple tuple  = new Tuple(ref_table, ref_column, fk);
                	listFK.add(tuple); //add information from this FK to my auxiliary list
                }
                
                //Interfaz en utils..TriggerFindconection, tablename, schema
                //Pasar todo a minusculas
                
                //I get the triggers from the current table.
                Statement stmt = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);     
                String query = "select * from information_schema.triggers where trigger_schema = '"+schema+"' and event_object_table ='"+tableName+"';";
                ResultSet rs = stmt.executeQuery(query);
                rs.beforeFirst();
            	while(rs.next()){
            		String name   = rs.getString("trigger_name");
            		String event  = rs.getString("event_manipulation");
            		String timing = rs.getString("action_timing");
            		boolean tim;
            		
            		if(timing.equals("BEFORE")){ tim = true; }  //before = true 
            		else { tim = false; }						//after = false
            		
            		Trigger trigger = new Trigger(name, tim, event);
            		trigger.setTable(table);
            		table.addTrigger(trigger);
            	}
            	//stmt.close();
                db.addTable(table);
            }
             
            
            //I get the foreign keys(part2).
            for( int i = 0 ; i < listFK.size() ; i++ ){
            	ForeignKey fk = listFK.get(i).getThird();
            	Table t = fk.getFK().getTable(); //table corresponding to the FK
            	
            	String table_fk  = listFK.get(i).getFirst();
            	String column_fk = listFK.get(i).getSecond();
            	
            	//I get the column that the FK references to (it's from another table)
            	Column col_ref = db.findTable(table_fk).findColumn(column_fk); 
            	fk.setRef(col_ref);
            	t.addFK(fk);
            	
 			}
         

            //I get the procedures.
            ResultSet rsProc = metaData.getProcedures(catalogo, schema, "%");
            while(rsProc.next()) { 	
            	String procedureName = rsProc.getString(3);    
                Procedure procedure = new Procedure(procedureName);
                
                //I get the parameters from the current procedure.
                ResultSet rsPColum = metaData.getProcedureColumns(catalogo, schema, procedureName, null);
                while(rsPColum.next()) {
                	String name  = rsPColum.getString(4);  //parameter name
                    short  inout = rsPColum.getShort(5);   //parameter in-out-inOut
                    String type  = rsPColum.getString(7);  //parameter type
                    if(!type.equals("trigger")){
                    	if(name.equals("returnValue")){
                    		procedure.setResultType(type);
                        }else{
                            Param p = new Param(name, type, inout); //FALTA TRATAR!!!!!
                            procedure.addParam(p);
                        } 
                    }   
                }
                if(procedure.getResultType()==null){
                	procedure.setResultType("void");
                }
                db.addProcedure(procedure);
            } 
            
          conn.close(); 
        }catch(Exception cnfe) {System.err.println("Error");}		
	}
}
/*
getShort(5); // COLUMN_TYPE Short => kind of column/parameter:
(?)procedureColumnUnknown - nobody knows
(1)procedureColumnIn - IN parameter
(2)procedureColumnInOut - INOUT parameter
(4)procedureColumnOut - OUT parameter
(5)procedureColumnReturn - procedure return value
(?)procedureColumnResult - result column in ResultSet
*/


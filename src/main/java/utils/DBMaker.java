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
            String schema   = conn.getSchema();
        
            List<Tuple> listFK = new ArrayList<Tuple>(); //usado para guardar temporalmente informacion de FK's
            
            //Obtengo las tablas de la Base de Datos.
            String[] tipo = {"TABLE"};
            ResultSet rsTables = metaData.getTables(catalogo,schema, null, tipo);
            while(rsTables.next()){ 
                String tableName = rsTables.getString(3);  //(3)TABLE_NAME 
            	Table table = new Table(tableName);
                
            	//Obtengo las columnas de la tabla. 
                ResultSet rsColumns = metaData.getColumns(null, null, tableName, null); 
                while(rsColumns.next() ){
                	Column col = new Column(rsColumns.getString(4),rsColumns.getString(6));
                	col.setTable(table);
                	table.addColumn(col);
                }    
	              
                //Obtengo las claves primarias de la tabla.
                ResultSet primaryKeys = metaData.getPrimaryKeys(catalogo, schema, tableName); 
                while(primaryKeys.next()) {
                	Column pk = table.findColumn(primaryKeys.getString(4));
                	if (pk != null){
                		table.addPK(pk);
                	} 
                }
                
                //Obtengo las claves foraneas(parte1)
                ResultSet foreignKeys = metaData.getImportedKeys(catalogo, schema, tableName);
                while(foreignKeys.next()){
                	String ref_table  =	foreignKeys.getString(3); //tabla a la que referencia la FK
                	String ref_column =	foreignKeys.getString(4); //columna a la que referencia la FK           
                	String fk_column = foreignKeys.getString(8);
                	
                	Column col = table.findColumn(fk_column); //busco la columna de la fk en la tabla actual
                	ForeignKey fk = new ForeignKey();
                	fk.setFK(col);
                	

                	Tuple tuple  = new Tuple(ref_table, ref_column, fk);
                	listFK.add(tuple); //Agrego informacion de esta FK a mi lista auxiliar
                }
                
                //Obtengo los triggers ??
            
                db.addTable(table);
            }
              
            //Obtengo las claves foraneas(parte2)
            for( int i = 0 ; i < listFK.size() ; i++ ){
            	ForeignKey fk = listFK.get(i).getThird();
            	Table t = fk.getFK().getTable(); //tabla que correspondiente a la FK
            	
            	String table_fk  = listFK.get(i).getFirst();
            	String column_fk = listFK.get(i).getSecond();
            	
            	//Obtengo la columna a la que referencia la FK (es de otra tabla)
            	Column col_ref = db.findTable(table_fk).findColumn(column_fk); 
            	
            	fk.setRef(col_ref);
            	t.addFK(fk);
 			}
         

            //Obtengo los Procedimiento
            ResultSet rsProc = metaData.getProcedures(catalogo, schema, "%");
            while(rsProc.next()) { 	
            	String procedureName = rsProc.getString(3);    
                Procedure procedure = new Procedure(procedureName);
                
                //Obtengo los parametros
                ResultSet rsPColum = metaData.getProcedureColumns(catalogo, schema, procedureName, null);
                while(rsPColum.next()) {
                	String name  = rsPColum.getString(4);
                    short  inout = rsPColum.getShort(5);
                    String type  = rsPColum.getString(7);
                    if(!type.equals("trigger")){
                    	if(name.equals("returnValue")){
                    		procedure.setResultType(type);
                        }else{
                            Param p = new Param(name, type, inout);
                            procedure.addParam(p);
                        } 
                    }
                    if(procedure.getResultType()==null){procedure.setResultType("void");} 
                 }         
             }
            
         
        }catch(Exception cnfe) {System.err.println("Error");}		
	}
	



public static void main(String[] args) { 
    try{
        String driver = "org.postgresql.Driver";
        String url = "jdbc:postgresql://localhost:5432/postgres";
        String username = "postgres";
        String password = "root";

        Class.forName(driver); 
        Connection connection = DriverManager.getConnection(url, username, password);
        DBMaker test = new DBMaker(connection, "databaseTest");
        //test.getDB().show();
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

//https://docs.oracle.com/javase/7/docs/api/java/sql/DatabaseMetaData.html#getTableTypes()
//https://mahichir.wordpress.com/2012/09/05/reading-java-derby-metadata/ 
//http://www.javamadesoeasy.com/2015/11/jdbc-databasemetadata-in-java-retrieve.html

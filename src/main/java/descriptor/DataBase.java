package descriptor;

import java.util.ArrayList;
import java.util.List;

public class DataBase {

	private String name;
	private List<Table> tables;
	private List<Procedure> procedures;
	
	
	public DataBase(){}
	
	public DataBase(String name){
		this.name = name;
		tables = new ArrayList<Table>();
		procedures = new ArrayList<Procedure>();
	}
	
	
	public void addTable(Table t){
		tables.add(t);
	}
	
	
	public void addProcedure(Procedure p){
		procedures.add(p);
	}
	
	
	public String getName(){
		return name;
	}
	
	
	public List<Table> getTables(){
		return tables;
	}
	
	
	public List<Procedure> getProcedures(){
		return procedures;
	}
	
	@Override
	public boolean equals(Object o){
		DataBase db = (DataBase)o;
		return(name.equals(db.getName()) && tables.equals(db.getTables()) && procedures.equals(db.getProcedures()));
	}
	
	
	public String compare(DataBase db){
		if (this.equals(db)) {
		    return "DataBase "+name+" es igual a DataBase "+db.getName();
		}
		else {
			return "DataBase "+name+" es distinto a DataBase "+db.getName();
		}
	}
	
	@Override
	public String toString(){
		String s = "\nData Base: " + name + "\n";
		for( int i = 0 ; i < tables.size() ; i++ ){
			   s = s + tables.get( i ).toString() + "\n\n";
		}
		return s;
	}
	
	public Table findTable(String name){
		for( int i = 0 ; i < tables.size() ; i++ ){
			   if (tables.get(i).getName().equals(name)){
				   return tables.get(i);
			   }
		}
		return null;
	}	
}

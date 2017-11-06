package descriptor;

import java.util.ArrayList;
import java.util.List;

public class Table {

	private String name;
	private List<Column> columns;
	private List<Trigger> triggers;
	private List<Column> primaryKey;
	private List<ForeignKey> foreignKey;
	
	
	public Table(){}
	
	public Table(String name){
		this.name = name;
		columns = new ArrayList<Column>();
		triggers = new ArrayList<Trigger>();
		primaryKey = new ArrayList<Column>();
		foreignKey = new ArrayList<ForeignKey>();
	}
	
	
	public void addColumn(Column col){
		columns.add(col);	
	}
	
	
	public void addTrigger(Trigger t){
		triggers.add(t);	
	}
	
	
	public void addPK(Column col){
		primaryKey.add(col);	
	}
	
	
	public void addFK(ForeignKey fk){
		foreignKey.add(fk);	
	}
	
	
	public String getName(){
		return name;
	}
	
	
	public List<Column> getColumns(){
		return columns;
	}
	
	
	public List<Trigger> getTriggers(){
		return triggers;
	}
	
	
	public List<Column> getPK(){
		return primaryKey;
	}
	
	
	public List<ForeignKey> getFK(){
		return foreignKey;
		
	}

	@Override
	public boolean equals(Object o){
		Table t = (Table)o;
		return ( name.equals(t.getName())) &&  (columns.equals(t.getColumns())) 
		&& (triggers.equals(t.getTriggers())) && primaryKey.equals(t.getPK()) && (foreignKey.equals(t.getFK()));
	}
	
	
	public String compare(Table t){
		if (this.equals(t)) {
			return "Table "+name+" es igual a Table "+t.getName();
		}
		else {
		    return "Table "+name+" es distinto a Table "+t.getName();
		}
	}
	
	public void showTable(){	
		System.out.println("Table: "+name);
		for( int i = 0 ; i < columns.size() ; i++ ){
			   System.out.println("  -");
			   columns.get( i ).showColumn();
		}
		for( int j = 0 ; j < primaryKey.size() ; j++ ){
			   System.out.println("Primary Key: ");
			   primaryKey.get( j ).showColumn();
		}
		for( int k = 0 ; k < foreignKey.size() ; k++ ){ 
			   foreignKey.get( k ).showFK();
		}	
	}
	
	@Override
	public String toString(){
		String s = "Table: " + name + "\n";
		for( int i = 0 ; i < columns.size() ; i++ ){
			   s = s +"-"+ columns.get( i ).toString() + "\n";
		}
		return s;
	}
	
	
	public Column findColumn(String name){
		for( int i = 0 ; i < columns.size() ; i++ ){
			   if (columns.get(i).getName().equals(name)){
				   return columns.get(i);
			   }
		}
		return null;
	}
	
	
	
	
}

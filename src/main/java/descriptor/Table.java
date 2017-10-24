package descriptor;

import java.util.ArrayList;
import java.util.List;

public class Table {

	private String name;
	private List<Column> columns;
	private List<Trigger> triggers;
	private List<Column> primaryKey;
	private List<Column> foreignKey;
	
	
	public Table(){}
	
	public Table(String name){
		this.name = name;
		columns = new ArrayList<Column>();
		triggers = new ArrayList<Trigger>();
		primaryKey = new ArrayList<Column>();
		foreignKey = new ArrayList<Column>();
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
	
	
	public void addFK(Column col){
		foreignKey.add(col);	
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
	
	
	public List<Column> getFK(){
		return foreignKey;
		
	}

	@Override
	public boolean equals(Object o){
		Table t = (Table)o;
		if( name.equals(t.getName()) ){	
			if( columns.size() == t.getColumns().size() ) {
				List<Column> tCol = t.getColumns();
				for( int i = 0 ; i < columns.size(); i++ ){
					if ( !columns.get( i ).equals(tCol.get(i)) ){ return false; }	
				}	
			}else{ return false; }
			
		}else{ return false; }	
		// falta
		return true;
	}
	
	
	public String compare(Table t){
		// completar...
		return "algo";
	}
	
	@Override
	public String toString(){
		String s = "Table: " + name + "\n";
		for( int i = 0 ; i < columns.size() ; i++ ){
			   s = s +"-"+ columns.get( i ).toString() + "\n";
			}
		return s;
	}
	
}

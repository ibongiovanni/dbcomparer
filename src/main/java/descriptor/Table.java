package descriptor;

import java.util.ArrayList;
import java.util.List;

public class Table {

	private String name;
	private List<Column> columns;
	private List<Trigger> triggers;
	private List<Column> primaryKey;
	private List<ForeignKey> foreignKey;
	private List<Constraint> constraint;

	private final String sep = "-------------------------------------------------------------\n";
	
	
	public Table(){}
	
	public Table(String name){
		this.name = name.toLowerCase();
		columns = new ArrayList<Column>();
		triggers = new ArrayList<Trigger>();
		primaryKey = new ArrayList<Column>();
		foreignKey = new ArrayList<ForeignKey>();
		constraint = new ArrayList<Constraint>();
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
	
	public void addConstraint(Constraint c){
		constraint.add(c);	
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

	public List<Constraint> getConstraint(){
		return constraint;	
	}
	
	@Override
	public boolean equals(Object o){
		Table t = (Table)o;
		return ( name.equals(t.getName())) &&  (columns.equals(t.getColumns())) 
		&& (triggers.equals(t.getTriggers())) && primaryKey.equals(t.getPK()) && (foreignKey.equals(t.getFK())  
		&& constraint.equals(t.getConstraint()));
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
		String s = sep+"Table: " + name + "\n";
		s+=sep+"Columns:\n";
		for( int i = 0 ; i < columns.size() ; i++ ){
			   s = s +"-"+ columns.get( i ).toString() + "\n";
		}
		s+=sep+"Primary Key: ";
		for ( Column c : primaryKey ) {
			s+=c.getName()+" ";
		}
		s+="\n";
		if (foreignKey.size()>0) {
			s+=sep;
			s+="Foreign Keys: \n";
			for ( ForeignKey fk : foreignKey ) {
				s+="-"+fk+"\n";
			}
		}
		if (constraint.size()>0) {
			s+=sep;
			s+="Constraints: \n";
			for ( Constraint c : constraint ) {
				s+="-"+c+"\n";
			}
		}
		if (triggers.size()>0) {
			s+=sep;
			s+="Triggers: \n";
			for ( Trigger t : triggers ) {
				s+="-"+t+"\n";
			}
		}
		return s+sep;
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

package descriptor;

import java.util.ArrayList;
import java.util.List;

public class Table {

	private String name;
	private DataBase database;
	private List<Column> columns;
	private List<Trigger> triggers;
	private List<Column> primaryKey;
	private List<ForeignKey> foreignKey;
	private List<Constraint> constraint;

	
	
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

	public void setDB(DataBase db){
		database= db;
	}

	public DataBase getDB(){
		return database;
	}
	
	@Override
	public boolean equals(Object o){
		Table t = (Table)o;
		return ( name.equals(t.getName())) &&  (columns.equals(t.getColumns())) 
		&& (triggers.equals(t.getTriggers())) && primaryKey.equals(t.getPK()) && (foreignKey.equals(t.getFK())  
		&& constraint.equals(t.getConstraint()));
	}
	
	private final String sep = "-------------------------------------------------------------\n";
	
	public String compare(Table t){
		String ret="";
		String db1= database.getName();
		String db2=t.getDB().getName();
		ret+= sep+"Table '"+name+"' from "+db1+" and "+db2+"\n"+sep;
		//COLUMNS COMPARISON
		ret+= "Columns in "+db1+"."+name+" ("+columns.size()+"):\n "+listColumns(columns)+"\n";
		ret+= "\nColumns in "+db2+"."+name+" ("+t.getColumns().size()+"):\n "+listColumns(t.getColumns())+"\n";
		ret+="\n";
		if (columns.equals(t.getColumns())) {
			ret+= " \u2713 Both Tables have the same columns.\n";
		}
		else{
			List<Column> commons = getCommonCoulumns(t);
			if (commons.size()==0) {
				ret+=" \u292B There's no columns in common.\n";
			}
			else{
				ret+= "Number of columns with same name: "+commons.size()+".\n";
				ret+= "Comparing columns with same name:\n";
				for ( Column c : commons ) {
					ret+= " "+findColumn(c.getName()).compare(c)+"\n";
				}
			}
		}
		//PRIMARY KEYS COMPARISON

		//FOREIGN KEYS COMPARISON

		//CONSTRAINTS COMPARISON

		//TRIGGERS COMPARISON
		
		return ret+sep;
	}

	private List<Column> getCommonCoulumns(Table ot){
		List<Column> others = ot.getColumns();
    List<Column> commons = new ArrayList<>();
    for ( Column c : columns ) {
      for ( Column oc : others ) {
        //Both tables has the same name
        if (c.getName().equals(oc.getName())) {
          commons.add(oc);
        }
      }
    }
    return commons;
	}

	public String listColumns(List<Column> list){
		String ret = "";
    for ( Column c : list ) {
      ret+= c.getName()+" | ";
    }
    ret = ret.substring(0,ret.length()-2);
    return "[ "+ret+"]";
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

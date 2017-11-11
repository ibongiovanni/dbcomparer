package descriptor;

public class Column {

	private String name;
	private String type;
	private Table table;
	
	
	public Column(){}
	
	public Column(String name, String type){
		this.name = name;
		this.type = type;		
	}
	
	
	public String getName(){
		return name;
	}
	
	
	public String getType(){
		return type;
	}
	
	
	public Table getTable(){
		return table;
	}
	
	
	public void setTable(Table t){
		table = t;
	}
	
	@Override
	public boolean equals(Object o){
		Column col = (Column)o;
		return (name.equals(col.getName()) && type.equals(col.getType()));
	}
	
	
	public String compare(Column col){
		if (this.equals(col)) {
		  return "Column '"+name+"': Both Columns have the same type: '"+type+"'";
		}
		else {
			return "Column '"+name+"': in "+table.getDB().getName()+" is of type '"+type+"' and in "+col.getTable().getDB().getName()+" is of type '"+col.getType()+"'";
		}
	}
	
	public void showColumn(){
		System.out.print("column: "+name+", type: "+type);
	}
	
	@Override
	public String toString(){
		return ( "Name: " + name +"\t Type: " + type );
	}
	

}

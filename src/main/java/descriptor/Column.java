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
		// completar...
		return "algo";
	}
	
	@Override
	public String toString(){
		String s = "Name: " + name +" Type: " + type;
		return s;
	}
	

}

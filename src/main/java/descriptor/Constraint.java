package descriptor;

public class Constraint{
	private String name;
	private String clause;
	
	public Constraint(String name, String clause){
		this.clause = clause.toLowerCase();
		this.name = name.toLowerCase();
	}
	
	public String getConstraint(){
		return clause;
	}
	
	public void setConstraint(String clause){
		this.clause = clause.toLowerCase();	
	}
	
	public String getName(){
		return name;
	}
	
	public void setName(String name){
		this.name = name.toLowerCase();	
	}
	@Override
	public boolean equals(Object o){
		Constraint c = (Constraint)o;
		return clause.equals(c.getConstraint()) && name.equals(c.getName());
	}
	

}

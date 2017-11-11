package descriptor;

public class Constraint{
	private String name;
	private String clause;
	
	public Constraint(String name, String clause){
		this.clause = clause;
		this.name = name;
	}
	
	public String getConstraint(){
		return clause;
	}
	
	public void setConstraint(String clause){
		this.clause = clause;	
	}
	
	public String getName(){
		return name;
	}
	
	public void setName(String name){
		this.name = name;	
	}
	@Override
	public boolean equals(Object o){
		Constraint c = (Constraint)o;
		return clause.equals(c.getConstraint()) && name.equals(c.getName());
	}
	

}

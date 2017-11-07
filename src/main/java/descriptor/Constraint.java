package descriptor;

public class Constraint{
	private String clause;
	
	public Constraint(String clause){
		this.clause = clause;
	}
	
	public String getConstraint(){
		return clause;
	}
	
	public void setConstraint(String clause){
		this.clause = clause;	
	}
	
	@Override
	public boolean equals(Object o){
		Constraint c = (Constraint)o;
		return clause.equals(c.getConstraint());
	}
	

}

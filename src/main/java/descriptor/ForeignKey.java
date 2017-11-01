package descriptor;

public class ForeignKey {
	private Column fk;
	private Column referens;
	
	public ForeignKey(){}
	public ForeignKey(Column fk, Column ref){
		this.fk = fk;
		this.referens = ref;
	}
	
	public void setFK(Column fk){
		this.fk = fk;	
	}
	
	public void setRef(Column ref){
		this.referens = ref;	
	}
	
	public Column getFK(){
		return fk;	
	}
	
	public Column getRef(){
		return referens;	
	}
}

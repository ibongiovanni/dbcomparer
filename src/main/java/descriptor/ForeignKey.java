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
	
	public void showFK(){
		System.out.println("Foreign-Key: ");
		fk.showColumn();
		System.out.println("References: ");
		referens.showColumn();
	}
	
  @Override
  public boolean equals(Object o){
		ForeignKey f = (ForeignKey)o;
    return (fk.equals(f.getFK()) && referens.equals(f.getRef()));
  }

  @Override
  public String toString(){
  	String ret="";
  	ret+=fk.getName()+" References "+referens.getTable().getName()+"("+referens.getName()+")";
  	return ret;
  }
	
}

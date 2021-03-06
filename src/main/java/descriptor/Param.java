package descriptor;

public class Param {
  private String name;
  private String type;
  private int inout;
  private Procedure procedure;

  public Param(String n, String t, int io){
    name=n;
    type=t;
    inout=io;
  }

  public String getName(){
    return name;
  }

  public String getType(){
    return type;
  }

  public int getInOut(){
    return inout;
  }
 
  public Procedure getProcedure(){
	  return procedure;
  }  
  
  public void setProcedure(Procedure p){
	  this.procedure = p;
  }
  

  @Override
  public boolean equals(Object o){
    Param p = (Param)o;
    return (name.equals(p.getName()) 
      && type.equals(p.getType()) 
      && inout==p.getInOut());
  }

  public String compare(Param p){
	  String ret ="";
    if (this.equals(p)) {
       ret+="  \u2713 Both are equals\n";
    }
    else {
    	 ret+="";	
      if(name.equals(p.getName())) {
    	  ret+= "  \u2713 Both have the equal name: '"+name+"'\n";}
      else{
    	  ret+="  \u292B In "+getProcedure().getDB().getName()+" is named '"+name+ "' and in "+p.getProcedure().getDB().getName()+" is named '"+p.getName()+"'\n";
      }
      if(type.equals(p.getType())) {
    	  ret+= "  \u2713 Both have the equal type: '"+type+"'\n";}
      else{
    	  ret+= "  \u292B In "+getProcedure().getDB().getName()+" is of type '"+type+ "' and in "+p.getProcedure().getDB().getName()+" is of type '"+p.getType()+"'\n";
      }      
  	  String[] types = {"","IN","INOUT","RETURN","OUT"};
      if(inout==p.getInOut()) {
        ret+= "  \u2713 Both have the equal type of IN-OUT: '"+types[inout]+"'\n";}
      else{
    	  ret+= "  \u292B In "+getProcedure().getDB().getName()+" is of in-out type '"+types[inout]+"' and in "+p.getProcedure().getDB().getName()+" is of in-out type '"+types[p.getInOut()]+"'\n";
      }    
    }
    return ret;
  }

  @Override
  public String toString(){
    String[] types = {"","IN","INOUT","RETURN","OUT"};
    String ret =types[inout];
    ret+= " "+name;
    ret+= " "+type;
    return ret;
  }
}

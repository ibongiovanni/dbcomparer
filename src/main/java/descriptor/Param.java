package descriptor;

public class Param {
  private String name;
  private String type;
  private int inout;

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

  @Override
  public boolean equals(Object o){
    Param p = (Param)o;
    return (name.equals(p.getName()) 
      && type.equals(p.getType()) 
      && inout==p.getInOut());
  }

  public String compare(Param p){
    if (this.equals(p)) {
      return "Param "+name+" es igual a Param "+p.getName();
    }
    else {
      return "Param "+name+" es distinto a Param "+p.getName(); 
    }
  }

  @Override
  public String toString(){
    String[] types = {"","IN","INOUT","RETURN","OUT"};
    String ret = types[inout];
    ret+= " "+name;
    ret+= " "+type;
    return ret;
  }
}

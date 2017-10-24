package descriptor;

import java.util.List;
import java.util.ArrayList;

public class Procedure {
  private String name;
  private List<Param> params;
  private String resultType;

  public Procedure(String n){
    name=n;
    params=new ArrayList();
  }

  public void addParam(Param p){
    params.add(p);
  }

  public void setResultType(String t){
    resultType=t;
  }

  public String getName(){
    return name;
  }

  public String getResultType(){
    return resultType;
  }

  public List<Param> getParams(){
    return params;
  }

  @Override
  public boolean equals(Object o){
    Procedure p= (Procedure)o;
    return (name==p.getName() && resultType==p.getResultType() && params.equals(p.getParams()));
  }

  public String compare(Procedure p){
    if (this.equals(p)) {
      return "Procedure "+name+" es igual a Procedure "+p.getName();
    }
    else {
      return "Procedure "+name+" es distinto a Procedure "+p.getName();
    }
  }

}

package descriptor;



public class Trigger {
  private String name;
  private boolean timing;
  private String event;
  private Table table;


  public Trigger(String n,boolean t, String e){
    name =n;
    timing=t;
    event=e;
  }

  public String getName(){
    return name;
  }

  public boolean getTiming(){
    return timing;
  }

  public String getEvent(){
    return event;
  }

  public Table getTable(){
    return table;
  }

  public void setTable(Table t){
    table=t;
  }

  @Override
  public boolean equals(Object o){
    Trigger t = (Trigger)o; 
    return (name.equals(t.getName()) && timing==t.getTiming() && event.equals(t.getEvent()));
  }

  public String compare(Trigger t){
    String ret="";
    String db1=table.getDB().getName();
    String db2=t.getTable().getDB().getName();
    if (this.equals(t)) {
      ret+= " \u2713 Trigger "+name+" in both databases is fired ";
      ret+= (timing)? "BEFORE":"AFTER";
      ret+= " "+event.toUpperCase();
    }
    else {
      ret+= " \u292B Trigger "+name+" in "+db1+" is fired ";
      ret+= (timing)? "BEFORE":"AFTER";
      ret+= " "+event.toUpperCase();
      ret+= ", and in "+db2+" ";
      ret+= (t.getTiming())? "BEFORE":"AFTER";
      ret+= " "+t.getEvent().toUpperCase();
    }
    return ret;
  }

  @Override
  public String toString(){
    String ret = "Trigger ";
    ret+= name+" on ";
    ret+= table.getName()+" ";
    ret+= (timing)? "BEFORE":"AFTER";
    ret+= " "+event.toUpperCase();
    return ret;
  }

}
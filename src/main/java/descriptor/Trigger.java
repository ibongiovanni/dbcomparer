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
    return (name==t.getName() && timing==t.getTiming() && event==t.getEvent());
  }

  public String compare(Trigger t){
    if (this.equals(t)) {
      return "Trigger "+name+" es igual a Trigger "+t.getName();
    }
    else {
      return "Trigger "+name+" es distinto a Trigger "+t.getName();
    }
  }

}
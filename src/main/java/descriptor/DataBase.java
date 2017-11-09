package descriptor;

import java.util.ArrayList;
import java.util.List;

public class DataBase {

  private String name;
  private List<Table> tables;
  private List<Procedure> procedures;
  
  
  public DataBase(){}
  
  public DataBase(String name){
    this.name = name;
    tables = new ArrayList<Table>();
    procedures = new ArrayList<Procedure>();
  }
  
  
  public void addTable(Table t){
    tables.add(t);
  }
  
  
  public void addProcedure(Procedure p){
    procedures.add(p);
  }
  
  
  public String getName(){
    return name;
  }
  
  
  public List<Table> getTables(){
    return tables;
  }
  
  
  public List<Procedure> getProcedures(){
    return procedures;
  }
  
  @Override
  public boolean equals(Object o){
    DataBase db = (DataBase)o;
    return(name.equals(db.getName()) && tables.equals(db.getTables()) && procedures.equals(db.getProcedures()));
  }
  
  
  public String compare(DataBase db){
    if (this.equals(db)) {
        return "DataBase "+name+" es igual a DataBase "+db.getName();
    }
    else {
      return "DataBase "+name+" es distinto a DataBase "+db.getName();
    }
  }
  
  public void showDB(){ 
    for( int i = 0 ; i < tables.size() ; i++ ){
      System.out.println("-------------------------------");
      tables.get( i ).showTable();
      System.out.println("-------------------------------");
    }
  }
  
  @Override
  public String toString(){
    String sep = "#############################################################\n";
    String s = sep+"Data Base: " + name + "\n";
    for( int i = 0 ; i < tables.size() ; i++ ){
      String t;
      t = tables.get( i ).toString() + "\n";
      s += t;//tabulate(t);
    }
    s+="Procedures:\n";
    for ( Procedure p : procedures ) {
      s+= "> "+p+"\n";
    }
    return s+sep;
  }
  
  public Table findTable(String name){
    for( int i = 0 ; i < tables.size() ; i++ ){
         if (tables.get(i).getName().equals(name)){
           return tables.get(i);
         }
    }
    return null;
  } 

  private String tabulate(String in){
    String[] lines = in.split("\n");
    StringBuilder builder = new StringBuilder();
    for (String line : lines) {
        builder.insert(0,line);
        // I suspect you want this, otherwise you're losing line breaks.
        builder.append("\n");
    }
    return builder.toString();
  }
}


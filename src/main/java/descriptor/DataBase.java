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
  
  private List<Table> getCommonTables(DataBase db){
    List<Table> others = db.getTables();
    List<Table> commons = new ArrayList<>();
    for ( Table t : tables ) {
      for ( Table ot : others ) {
        //Both tables has the same name
        if (t.getName().equals(ot.getName())) {
          commons.add(ot);
        }
      }
    }
    return commons;
  }
  
  public String compare(DataBase db){
    String ret="";
    //TABLES COMPARISON
    ret+= sep+"Table Level Comparison:\n"+sep;
    ret+= "\nTables in "+name+":\n"+listTables()+"\n";
    ret+= "\nTables in "+db.getName()+": \n"+db.listTables()+"\n\n";
    if (tables.equals(db.getTables())) {
      ret+= "DataBase "+name+" and DataBase "+db.getName()+" has the same tables.\n";
    }
    else {
      List<Table> commonTables = getCommonTables(db); //tables from db with same name
      if (commonTables.size()==0) {
        ret+= "DataBase "+name+" and DataBase "+db.getName()+" has no tables with same name.\n";
      }
      else {
        ret+= "Number of tables with same name: "+commonTables.size()+".\n";
        ret+= "Comparing tables with same name:\n";
        for ( Table ot : commonTables ) {
          ret+= tabulate(findTable(ot.getName()).compare(ot))+"\n";
        }
      }
    }
    //PROCEDURES COMPARISON

    return ret+sep;
  }

  public  String listTables(){
    String ret = "";
    for ( Table t : tables ) {
      ret+= t.getName()+" | ";
    }
    return ret;
  }
  
  public void showDB(){ 
    for( int i = 0 ; i < tables.size() ; i++ ){
      System.out.println("-------------------------------");
      tables.get( i ).showTable();
      System.out.println("-------------------------------");
    }
  }
  
  private String sep = "#################################################################################\n";
    
  @Override
  public String toString(){
    String s = sep+"Data Base: " + name + "\n";
    for( int i = 0 ; i < tables.size() ; i++ ){
      String t;
      t = tables.get( i ).toString() + "\n";
      s += tabulate(t)+"\n";
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
        builder.append("\t|"+line);
        // I suspect you want this, otherwise you're losing line breaks.
        builder.append("\n");
    }
    return builder.toString();
  }
}


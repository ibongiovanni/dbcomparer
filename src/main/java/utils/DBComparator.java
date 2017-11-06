package utils;

import descriptor.*;

public class DBComparator {
  private DataBase db1;
  private DataBase db2;

  public DBComparator(DataBase d1, DataBase d2){
    db1=d1;
    db2=d2;
  }

  public String compare(){
    String ret= "";
    ret = db1.compare(db2);
    return ret;
  }
}
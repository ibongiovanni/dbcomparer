
import utils.*;
import descriptor.*;
import java.sql.*;
import java.util.*;

public class App {
  // JDBC driver name and database URL
  static final String JDBC_DRIVER = "org.postgresql.Driver";  
  static final String DB_URL = "jdbc:postgresql://localhost:5432/postgres";

  //  Database credentials
  static final String USER = "postgres";
  static final String PASS = "root"; 

  public static void main(String[] args) {
    if (args.length < 2) {
      System.out.println("ERROR: Wrong number of params");
      System.out.println("Use: program config_file1 config_file2");
      return;
    }
    Connection conn = null;
    try{
      /*
        Load DataBase 1
      */
      ConfigLoader cdb1 = new ConfigLoader(args[0]);
      if (!cdb1.loadData()) {
        System.out.println("\uE532 Error loading data");
        return;
      }
      else {
        System.out.println("\uE531 Data of "+args[0]+" loaded");
      }

      //STEP 2: Register JDBC driver
      Class.forName(cdb1.driver());

      //STEP 3: Open a connection
      System.out.println("\uE39D Connecting to database "+cdb1.schema()+"...");
      conn = DriverManager.getConnection(cdb1.url(),cdb1.user(),cdb1.pass());

      DBMaker test = new DBMaker(conn, cdb1.schema());

      test.buildDB();
      DataBase db1 = test.getDB();

      
      conn.close();

      /*
        Load DataBase 2
      */
      ConfigLoader cdb2 = new ConfigLoader(args[1]);
      if (!cdb2.loadData()) {
        System.out.println("\uE532  Error loading data");
        return;
      }
      else {
        System.out.println("\uE531 Data of "+args[1]+" loaded");
      }
      if (!cdb1.driver().equals(cdb2.driver())) {
        System.out.println("Error: Both DataBases must be of the same type.");
        return;
      }
      System.out.println("\uE39D Connecting to database "+cdb2.schema()+"...");
      conn = DriverManager.getConnection(cdb2.url(),cdb2.user(),cdb2.pass());

      test = new DBMaker(conn, cdb2.schema());

      test.buildDB();
      DataBase db2 = test.getDB();

      /*
        Show Both Databases info
      */
      System.out.println(db1);
      System.out.println(db2);

      /*
        Compare Both Databases
      */
      DBComparator dbc = new DBComparator(db1,db2);
      System.out.println(dbc.compare());

    }catch(ClassNotFoundException cnfe) {
      System.err.println("Error loading driver: " + cnfe);
    }catch(SQLException se){
        //Handle errors for JDBC
        se.printStackTrace();
    }catch(Exception e){
        //Handle errors for Class.forName
        e.printStackTrace();
    }finally{
      //finally block used to close resources
      try{
         if(conn!=null)
            conn.close();
      }catch(SQLException se){
         se.printStackTrace();
      }//end finally try
    }//end try
    System.out.println("GoodBye!");
  }//end main
}
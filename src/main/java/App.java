
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
    Connection conn = null;
    try{
      //STEP 2: Register JDBC driver
      Class.forName(JDBC_DRIVER);

      //STEP 3: Open a connection
      System.out.println("Connecting to database...");
      conn = DriverManager.getConnection(DB_URL,USER,PASS);

      DBMaker test = new DBMaker(conn, "database1");

      test.buildDB();
      DataBase db = test.getDB();

      System.out.println(db);
      
      conn.close();

      System.out.println("Connecting to database...");
      conn = DriverManager.getConnection(DB_URL,USER,PASS);

      test = new DBMaker(conn, "database4");

      test.buildDB();
      DataBase db2 = test.getDB();

      System.out.println(db2);

      System.out.println(db.compare(db2));

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
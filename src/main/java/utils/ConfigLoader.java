/**
* Configuration File Loader.
*   Reads a file from path in JSON format containing
*   the information to load a database schema.
*
*/


package utils;

import com.google.gson.Gson;

public class ConfigLoader {
  
  private String fpath;
  private String fcontent;
  //data
  private String url; //Database url
  private String driver; //Database driver
  private String schema; 
  private String user;
  private String pass;

  public ConfigLoader(String path){
    fpath=path;
    fcontent= readFile(fpath);
  }

  public boolean loadData(){
    Properties data = new Gson().fromJson(fcontent, Properties.class);
    //Set data
    url = data.getProperty("DB_URL");
    driver = data.getProperty("JDBC_DRIVER");
    schema = data.getProperty("SCHEMA");
    user = data.getProperty("USER");
    pass = data.getProperty("PASS");
    if ((url==null) || (driver==null) || (schema==null) || (user==null) || (pass==null)) {
      return false;
    }
    else {
      return true;
    } 
  }

  public String getURL(){
    return url;
  }

  public String getDriver(){
    return driver;
  }

  public String getUser(){
    return user;
  }

  public String getPass(){
    return pass;
  }


  private String readFile(String pathname) throws IOException {

    File file = new File(pathname);
    StringBuilder fileContents = new StringBuilder((int)file.length());
    Scanner scanner = new Scanner(file);
    String lineSeparator = System.getProperty("line.separator");

    try {
        while(scanner.hasNextLine()) {
            fileContents.append(scanner.nextLine() + lineSeparator);
        }
        return fileContents.toString();
    } finally {
        scanner.close();
    }
  }

}
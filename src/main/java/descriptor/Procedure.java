package descriptor;

import java.util.List;
import java.util.ArrayList;

public class Procedure {
  private String name;
  private List<Param> params;
  private String resultType;
  private DataBase database;

  public Procedure(String n){
    name=n;
    params=new ArrayList<>();
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
  
  public void setDB(DataBase db){
	database= db;
  }

  public DataBase getDB(){
	return database;
  }  

  @Override
  public boolean equals(Object o){
    Procedure p= (Procedure)o;
    return (name.equals(p.getName()) && resultType.equals(p.getResultType()) && params.equals(p.getParams()));
  }


  public String compare(Procedure p){
	  String ret="";
	  if (this.equals(p)) {
		  ret+=sep2;
		  ret+="Procedure '"+name+"': Both Procedures have the same profile\n";
	  }
	  else {
		    ret+=sep2;
		    ret+="Procedure '"+name+"'\n\n"; 
		    List<Param> otherparams = p.getParams();
		    
		    int long_other = otherparams.size();
		    int long_current = params.size();
		    
			if(long_other == long_current){ //Equal long of parameters
				for( int i = 0 ; i < params.size() ; i++ ){
					Param param = params.get(i);
				    Param op = otherparams.get(i);
				    ret+="*Parameter "+(i+1)+"\n";
				    ret+= param.compare(op);
				    ret+="\n";
				}
			}else{	
				int extra;
				if(	long_other > long_current){
					extra = long_other - long_current;
					for( int i = 0 ; i < long_current ; i++ ){
						Param param = params.get(i);
						Param op = otherparams.get(i);
						ret+="*Parameter "+(i+1)+"\n";
						ret+= param.compare(op);						
					}
					ret+= "\nThe procedure in "+p.getDB().getName()+" have "+extra+" parameters aditional:\n";
					for(int j = 0 ; j < extra ; j++){
						ret+="*Parameter "+(long_current+j+1)+": "+otherparams.get(long_current+j);
						ret+="\n";
					}
					ret+="\n";
				}else{ 
				    extra = long_current -long_other;
					for( int i = 0 ; i < long_other ; i++ ){
						Param param = params.get(i);
						Param op = otherparams.get(i);
						ret+="*Parameter "+(i+1)+"\n";
						ret+= param.compare(op);							
					}
					ret+= "\nThe procedure in "+getDB().getName()+" have "+extra+" parameters aditional:\n";
					for(int j = 0 ; j < extra ; j++){
						ret+="*Parameter "+(long_other+j+1)+": "+params.get(long_other+j);	
						ret+="\n";
					}
					ret+="\n";
				 }

			}
			if(resultType.equals(p.getResultType())){
				ret+="Both have the equal type of return value\n";			
			}else{
				ret+= "The procedure in "+getDB().getName()+ " have the type of return value: '"+resultType+"'\n";
				ret+= "The procedure in "+p.getDB().getName()+ " have the type of return value: '"+p.getResultType()+"'\n";
				
			}
	  }
		    
	  return ret;
  }
  
  private final String sep2 = "-------------------------------------------------------------\n";
	
  @Override
  public String toString(){
    String ret = "Procedure ";
    ret+=name+" ( ";
    for ( Param p : params ) {
      ret+= p + ", ";
    }
    if(!params.isEmpty()) ret = ret.substring(0,ret.length()-2)+") ";
    else ret += ") ";
    ret+= "returns "+resultType;
    return ret;
  }
}

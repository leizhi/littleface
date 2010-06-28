package org.pig.error;

public abstract class DbError implements Error{

public static String message = null;
public String type = null;
public String dataBases = null;
public String table = null;
public String Record = null;

public String getMessage(){
 	return message;
}
public void setMessage(String message){
	this.message = message;

}
public void checkCustomer(String ShortName){

}
public void checkConsignee(String ShortName){

}
}

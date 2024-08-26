package CMS.dbinfo;
import java.sql.*;

public class DBConnection 
{
 private static Connection con;
 
 public static Connection createConnection()
 {
	 try {
		Class.forName("com.mysql.cj.jdbc.Driver");  //factory methods,it is used to make the object of the class.-------->    step1 
		con=DriverManager.getConnection("jdbc:mysql://localhost:3306/cms_db","root","anas");  //creates connection with a particular database-------->     step2
	
	 
	 }
	 catch(ClassNotFoundException|SQLException cse)
	 {
		cse.printStackTrace() ;
	 }
	 
	 return con;
	 
	 
	 
 }
// public static void main(String[] args) {
//	Connection c=createConnection();
//	System.out.println(c);
}
	
	


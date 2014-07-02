
package edu.towson.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import edu.towson.controllers.ConnectionManager;

/**
 *
 * @author korea_fern
 */
public class LoginDao {
    
    static Connection con = null;
      static ResultSet rs = null;  
	
	
	
      public static LoginBean login(LoginBean bean) {
	
         //preparing some objects for connection 
         Statement stmt = null;    
	
         String username = bean.getuserName();    
         String password = bean.getPasswordHash();   
	    
         String searchQuery =
               "select * from users where username='"
                        + username
                        + "' AND password='"
                        + password
                        + "'";
	    
      // "System.out.println" prints in the console; Normally used to trace the process
      System.out.println("Your user name is " + username);          
      System.out.println("Your password is " + password);
      System.out.println("Query: "+searchQuery);
	    
      try 
      {
         //connect to DB 
         con = ConnectionManager.getConnection();
         stmt=con.createStatement();
         rs = stmt.executeQuery(searchQuery);	        
         boolean more = rs.next();
	       
         // if user does not exist set the isValid variable to false
         if (!more) 
         {
            System.out.println("Sorry, you are not a registered user! Please sign up first");
            bean.setValid(false);
         } 
	        
         //if user exists set the isValid variable to true
         else if (more) 
         {
            String firstName = rs.getString("FirstName");
            String lastName = rs.getString("LastName");
	     	
            System.out.println("Welcome " + firstName);
            bean.setfirstName(firstName);
            bean.setlastName(lastName);
            bean.setValid(true);
         }
      } 

      catch (Exception ex) 
      {
         System.out.println("Log In failed: An Exception has occurred! " + ex);
      } 
	    
      //some exception handling
      finally 
      {
         if (rs != null)	{
            try {
               rs.close();
            } catch (Exception e) {}
               rs = null;
            }
	
         if (stmt != null) {
            try {
               stmt.close();
            } catch (Exception e) {}
               stmt = null;
            }
	
         if (con != null) {
            try {
               con.close();
            } catch (Exception e) {
            }

            con = null;
         }
      }

return bean;
	
      }	
    
}

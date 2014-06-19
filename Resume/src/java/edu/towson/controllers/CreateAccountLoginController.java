package edu.towson.controllers;

import edu.towson.beans.Info;
import edu.towson.dao.DaoFactory;
import edu.towson.dao.DaoPattern;
import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class LoginServlet
 */


@WebServlet(urlPatterns = {"/CreateAccountLoginController"})
public class CreateAccountLoginController extends HttpServlet {

   

    private DaoFactory factory;
    private DaoPattern<Info> daoDb;


@Override
public void doGet(HttpServletRequest request, HttpServletResponse response) 
throws ServletException, java.io.IOException {

try
{	    

     LoginBean user = new LoginBean();
     user.setuserName(request.getParameter("username"));
     user.setPasswordHash(request.getParameter("password"));

     user = LoginBean.login(user);
	   		    
     if (user.isValid())
     {
	        
          HttpSession session = request.getSession(true);	    
          session.setAttribute("currentSessionUser",user); 
          response.sendRedirect("EnterInfo.jsp"); //logged-in page      		
     }
	        
     else 
          response.sendRedirect("CreateaccountOrlogin.jsp"); //error page 
} 
		
		
catch (Throwable theException) 	    
{
     System.out.println(theException); 
}
     }

@Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        

String target = "/CreateaccountOrlogin.jsp";
        try {
            
            HttpSession session = request.getSession();
            String submit = request.getParameter("submit");
            if ("Create Account".equals(submit)) {
               
                String[] requiredFormParams = {"ifirst_name", "ilast_name", "iaddress1", "iaddress2", "icity", "istate", "izipcode", "iemail", "iuser_name", "ipassword"};
                // run through the list and thrown an exception if a required field is missing
                for (String param : requiredFormParams) {
                    if (request.getParameter(param) == null || request.getParameter(param).isEmpty()) {
                        Exception ex = new Exception("Error! All fields are required. param:" + param);
                        throw ex;
                    }
                }
                // if we get here then all the required fields were found
                Info info = new Info();

                int resume_ID = this.daoDb.findLast().getresume_ID() + 1; // this should make all entries have a unique incrementing id
                info.setresume_ID(resume_ID);
                info.setfirst_name(request.getParameter("ifirst_name"));
                info.setlast_name(request.getParameter("ilast_name"));
                info.setaddress1(request.getParameter("iaddress1"));
                info.setaddress2(request.getParameter("iaddress2"));
                info.setcity(request.getParameter("icity"));
                info.setstate(request.getParameter("istate"));
                info.setzipcode(request.getParameter("izipcode"));
                info.setemail(request.getParameter("iemail"));
                info.setuser_name(request.getParameter("iuser_name"));
                info.setpassword(request.getParameter("ipassword"));
                
            

                int res = this.daoDb.store(info);
                if (res != 0) // check that the item was successfully added.
                {
                    // add the successfully added item to the session so that the view item page will load it.
                    session.setAttribute("info", info);
                }
                else {
                    target = "/CreateaccountOrlogin.jsp"; // database error try again
                    request.setAttribute("error", "Error! Info couldn't be saved.");// errors are specific to the request so use the request object
                }

            }
        }
        catch (Exception ex) {
            System.err.println("Exception caught." + ex.getMessage());
            target = "/CreateaccountOrlogin.jsp"; // try again
            request.setAttribute("error", "Error! All fields are required.");
        }
        finally {
            ServletContext context = this.getServletContext();
            RequestDispatcher dispatcher = context.getRequestDispatcher(target);
            dispatcher.forward(request, response);
}
 
}
}

/*

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;


    
     
  

@WebServlet(urlPatterns = {"/CreateAccountLoginController"})
public class CreateAccountLoginController extends HttpServlet {

   

    private DaoFactory factory;
    private DaoPattern<Info> daoDb;
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        //check if the user is logged in
        //if logged in, go to EnterInfo page
        //if not logged in, go to create account or login page
        
        
        
        
        
        
    }

  
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        

String target = "/CreateaccountOrlogin.jsp";
        try {
            
            HttpSession session = request.getSession();
            String submit = request.getParameter("submit");
            if ("Create Account".equals(submit)) {
               
                String[] requiredFormParams = {"ifirst_name", "ilast_name", "iaddress1", "iaddress2", "icity", "istate", "izipcode", "iemail", "iuser_name", "ipassword"};
                // run through the list and thrown an exception if a required field is missing
                for (String param : requiredFormParams) {
                    if (request.getParameter(param) == null || request.getParameter(param).isEmpty()) {
                        Exception ex = new Exception("Error! All fields are required. param:" + param);
                        throw ex;
                    }
                }
                // if we get here then all the required fields were found
                Info info = new Info();

                int resume_ID = this.daoDb.findLast().getresume_ID() + 1; // this should make all entries have a unique incrementing id
                info.setresume_ID(resume_ID);
                info.setfirst_name(request.getParameter("ifirst_name"));
                info.setlast_name(request.getParameter("ilast_name"));
                info.setaddress1(request.getParameter("iaddress1"));
                info.setaddress2(request.getParameter("iaddress2"));
                info.setcity(request.getParameter("icity"));
                info.setstate(request.getParameter("istate"));
                info.setzipcode(request.getParameter("izipcode"));
                info.setemail(request.getParameter("iemail"));
                info.setuser_name(request.getParameter("iuser_name"));
                info.setpassword(request.getParameter("ipassword"));
                
            

                int res = this.daoDb.store(info);
                if (res != 0) // check that the item was successfully added.
                {
                    // add the successfully added item to the session so that the view item page will load it.
                    session.setAttribute("info", info);
                }
                else {
                    target = "/CreateaccountOrlogin.jsp"; // database error try again
                    request.setAttribute("error", "Error! Info couldn't be saved.");// errors are specific to the request so use the request object
                }

            }
        }
        catch (Exception ex) {
            System.err.println("Exception caught." + ex.getMessage());
            target = "/CreateaccountOrlogin.jsp"; // try again
            request.setAttribute("error", "Error! All fields are required.");
        }
        finally {
            ServletContext context = this.getServletContext();
            RequestDispatcher dispatcher = context.getRequestDispatcher(target);
            dispatcher.forward(request, response);
}
 
}
}
     
*/
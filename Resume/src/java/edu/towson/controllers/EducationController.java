package edu.towson.controllers;

/*
 * Fern Sawitree Euamethiyangkool
 */

import edu.towson.beans.Info;
import edu.towson.dao.DaoFactory;
import edu.towson.dao.DaoPattern;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author korea_fern
 */
@WebServlet(urlPatterns = {"/AddEducation.jsp"})
public class EducationController extends HttpServlet {

    private DaoFactory factory;
    private DaoPattern<Info> daoDb;
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        
        
        
        
    }

  
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
    

 

String target = "/AddEducation.jsp";
        try {
            
            HttpSession session = request.getSession();
            String submit = request.getParameter("submit");
            if ("Add".equals(submit)) {
               
                String[] requiredFormParams = {"iinstitute_name", "idegree_name", "istartdate", "ienddate", "ied_description"};
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
                info.setinstitute_name(request.getParameter("iinstitute_name"));
                info.setdegree_name(request.getParameter("idegree_name"));
                info.setstartdate(request.getParameter("istartdate"));
                info.setenddate(request.getParameter("ienddate"));
                info.seted_description(request.getParameter("ied_description"));
               
                
            

                int res = this.daoDb.store(info);
                if (res != 0) // check that the item was successfully added.
                {
                    // add the successfully added item to the session so that the view item page will load it.
                    session.setAttribute("info", info);
                }
                else {
                    target = "/AddEducation.jsp"; // database error try again
                    request.setAttribute("error", "Error! Info couldn't be saved.");// errors are specific to the request so use the request object
                }

            }
        }
        catch (Exception ex) {
            System.err.println("Exception caught." + ex.getMessage());
            target = "/AddEducation.jsp"; // try again
            request.setAttribute("error", "Error! All fields are required.");
        }
        finally {
            ServletContext context = this.getServletContext();
            RequestDispatcher dispatcher = context.getRequestDispatcher(target);
            dispatcher.forward(request, response);
}
 
}
}
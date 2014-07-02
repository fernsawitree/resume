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

@WebServlet(urlPatterns = {"/AddSkills.jsp"})
public class SkillsController extends HttpServlet {

    private DaoFactory factory;
    private DaoPattern<Info> daoDb;
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        
        
        
        
    }

  
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
    

 //once on EnterInfo page, user can enter the information and add new section to each category 

String target = "/AddSkills.jsp";
        try {
            
            HttpSession session = request.getSession();
            String submit = request.getParameter("submit");
            if ("Add".equals(submit)) {
               
                String[] requiredFormParams = { "iskills_info"};
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
                info.setskills_info(request.getParameter("iskills_info"));
                
            

                int res = this.daoDb.store(info);
                if (res != 0) // check that the item was successfully added.
                {
                    // add the successfully added item to the session so that the view item page will load it.
                    session.setAttribute("info", info);
                }
                else {
                    target = "/AddSkills.jsp"; // database error try again
                    request.setAttribute("error", "Error! Info couldn't be saved.");// errors are specific to the request so use the request object
                }

            }
        }
        catch (Exception ex) {
            System.err.println("Exception caught." + ex.getMessage());
            target = "/AddSkills.jsp"; // try again
            request.setAttribute("error", "Error! All fields are required.");
        }
        finally {
            ServletContext context = this.getServletContext();
            RequestDispatcher dispatcher = context.getRequestDispatcher(target);
            dispatcher.forward(request, response);
}
 
}
}
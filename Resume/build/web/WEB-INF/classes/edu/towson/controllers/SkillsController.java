package edu.towson.controllers;

/*
 * Fern Sawitree Euamethiyangkool
 */
import edu.towson.beans.SkillBean;
import edu.towson.dao.SkillBeanDao;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.commons.dbutils.BeanProcessor;

/**
 *
 * @author korea_fern
 */
@WebServlet(urlPatterns = {"/SkillsController"})
public class SkillsController extends HttpServlet {
private static final Logger log = Logger.getLogger(SkillsController.class.getName());
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //if Add Experience button is clicked, pop-up window will appear
        String addskill = request.getParameter("add");
        response.sendRedirect("AddSkills.jsp");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();
       // String submit = request.getParameter("submit");
        //start database connection
        String Driver = "com.mysql.jdbc.Driver";
        String URL = "jdbc:mysql://localhost:3306/resume1";
        ResultSet RS = null;
        try {
            Class.forName(Driver);
            Connection Conn = DriverManager.getConnection(URL, "root", "");
            Statement S = Conn.createStatement();
          
            String[] requiredFormParams = {"title", "description"};
            // run through the list and thrown an exception if a required field is missing
            String message = "Error! Please fill out the required field. param:";
            boolean haserror = false;
            for (String parameter : requiredFormParams) {
                if (request.getParameter(parameter) == null || request.getParameter(parameter).isEmpty()) {
                    message += parameter + ',';
                    haserror = true; //Note that I have to change null value in the database
                }

            }
            if (haserror) {
                request.setAttribute("message", message);
                request.getRequestDispatcher("/AddSkills.jsp").forward(request, response);
                log.log(Level.INFO, "errors");
                return;
            }
            List<SkillBean> skills = new ArrayList<SkillBean>();
            
            // if we get here then all the required fields were found
            SkillBean skill = new SkillBean();
            int userid = (Integer)(session.getAttribute("id")); //was user_id
            String userquery = "SELECT * FROM Skills where user_id = '" + userid + "'";
            BeanProcessor bp = new BeanProcessor();
             RS = S.executeQuery(                   
                    userquery);
            while(RS.next()){
                SkillBean bean = (SkillBean) bp.toBean(RS, SkillBean.class);
                skills.add(bean);
                //convert each row to experience bean object
                //add experience object to the list
              
            }
            log.log(Level.INFO, "user_id{0}", userid);
            SkillBeanDao skilldao = new SkillBeanDao(Conn);
          //  SkillBean findlast = skilldao.findLast();
          //  int skillId = 0;
           // if (findlast != null){
           // skillId = skilldao.findLast().getSkillId() + 1; // this should make all entries have a unique incrementing id
           // }
            skill.setUser_id(userid);
         //   skill.setSkillId(skillId);
            skill.setTitle(request.getParameter("title"));
            skill.setDescription(request.getParameter("description"));
            skill.setYears(request.getParameter("years"));
            skill.setLevel(request.getParameter("level"));
           
          

            int res = skilldao.store(skill);
            if (res != 0) // check that the item was successfully added.
            {
                skills.add(skill);
                // add the successfully added item to the session so that the view item page will load it.
                session.setAttribute("skills", skills);
                //redirect the page to the next step in order to fil our all information 
                request.getRequestDispatcher("/SkillsList.jsp").forward(request, response);
            } else {
                request.setAttribute("message", "Data cannot be saved into the database.");
                request.getRequestDispatcher("/AddSkills.jsp").forward(request, response);
                log.log(Level.INFO, "statement store data");
                return;

            }
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(CreateAccount.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(CreateAccount.class.getName()).log(Level.SEVERE, null, ex);

        
        }

    }
}
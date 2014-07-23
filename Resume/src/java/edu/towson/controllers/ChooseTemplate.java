/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.towson.controllers;

import edu.towson.beans.TemplateBean;
import edu.towson.dao.TemplateBeanDao;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
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
@WebServlet(urlPatterns = {"/ChooseTemplate"})
public class ChooseTemplate extends HttpServlet {

    private static final Logger log = Logger.getLogger(ChooseTemplate.class.getName());

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, java.io.IOException {
//display jsp file
        response.sendRedirect("ChooseTemplate.jsp");

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();
        String templatevalue = request.getParameter("Template");
        if (templatevalue != null) {
            String Driver = "com.mysql.jdbc.Driver";
            String URL = "jdbc:mysql://localhost:3306/resume1";
            ResultSet RS = null;
            try {
                Class.forName(Driver);
                Connection Conn = DriverManager.getConnection(URL, "root", "");
                Statement S = Conn.createStatement();
                int userid = (Integer) (session.getAttribute("user_id"));
                String userquery = "SELECT * FROM User where id = '" + userid + "'";
                RS = S.executeQuery(
                        userquery);
                log.log(Level.INFO, userquery);
                while (RS.next()) {
                }


                int templateID = 0;

                if (templatevalue.equals("template1")) {
                    templateID = 1;
                    //store to database
                    TemplateBean template = new TemplateBean();
                    TemplateBeanDao templatedao = new TemplateBeanDao(Conn);
                    template.setUser_id(userid);
                    template.setTemplate_id(templateID);
                    response.sendRedirect("template1.jsp");

                } else {
                    templateID = 2;
                    response.sendRedirect("template2.jsp");
                }

            } catch (ClassNotFoundException ex) {
                Logger.getLogger(ChooseTemplate.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SQLException ex) {
                Logger.getLogger(ChooseTemplate.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            response.sendRedirect("ChooseTemplate.jsp");
        }

    }
}

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.towson.test;

import edu.towson.dao.EducationBeanDao;
import edu.towson.beans.EducationBean;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author korea_fern
 */
@WebServlet(name = "TestEducationBean", urlPatterns = {"/TestEducationBean"})
public class TestEducationBean extends HttpServlet {
    private Connection connection;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Set the response message's MIME type
        response.setContentType("text/html;charset=UTF-8");
        // Allocate a output writer to write the response message into the network socket
        PrintWriter out = response.getWriter();
        // open a connection to the database
        Connection conn = null;
        Statement stmt = null;
        try {
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head><title>Qurey Servlet</title></head>");
            out.println("<body>");
            //get connection 
            conn = getConnection();
            stmt = conn.createStatement();
            ResultSet rset = stmt.executeQuery("SELECT EDUATION_ID, INSTITUTE_NAME, DEGREE_NAME, STARTDATE, ENDDATE,ED_DESCRIPTION  FROM EDUCATION");
            out.println("<p>" + rset.getString("EDUCATION_ID") + "," + rset.getString("INSTITUTE_NAME") + "," + rset.getString("DEGREE_NAME") + ","
                    + rset.getString("DEGREE_NAME") + "," + rset.getString("STARTDATE") + "," + rset.getString("ENDDATE") + "," + rset.getString("ED_DESCRIPTION") + "</p>");
            out.println("</body></html>");
            // get an instance of the EducationBeanDao
            EducationBeanDao dao = new EducationBeanDao(conn);
            // use the Dao to get an instance of the bean
            List<EducationBean> beanList = dao.findAll();
            EducationBean bean = new EducationBean();
            //create a new bean from scratch
            bean.setEducationId(2);
            bean.setInstituteName("UMD");
            bean.setDegreeName("M.S. in Engineering");
            bean.setStartdate("09/09/2012");
            bean.setEnddate("05/15/2014");
            bean.setDescription("This is a two year program in engineering.");
            beanList.add(bean);
        } catch (SQLException ex) {
            Logger.getLogger(TestEducationBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        //add the new bean to the database using store on the dao
    public int store(T t) {
        int res = 0;// default to false
        String insertStr = getPSString();
        log.Education(insertStr);
        Connection conn;
        PreparedStatement ps = null;
        try {
            conn = getConnection();
            ps = conn.prepareStatement(insertStr);
            BeanToPreparedStatement(t, ps);
            res = ps.executeUpdate();

            ps.clearParameters();

        } catch (SQLException sqe) {
            log.warning(sqe.getMessage());
            System.out.println("there was an error with store for query:" + insertStr);
        } finally {
            safeClose(ps);
        }
        return res;
    }
    // retrieve the bean just added.

    // print the bean to a page
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }

    protected Connection getConnection()
            throws SQLException {
        if (this.connection != null) {
            return this.connection;
        }
// use this code if you have a datasource
// Context initCtx = new javax.naming.InitialContext();
// Context envCtx = initCtx.lookup("java:comp/env")
// DataSource ds = (DataSource)envCtx.lookup("jdbc/quotes");
// return = ds.getConnection();
        String url = "jdbc:mysql://localhost:3306/Resume";
        String driver = "com.mysql.jdbc.Driver";
        String uri = url + basePath + "/db/" + database + ";shutdown=true";
        log.log(Level.INFO, "Database URI: {0}", uri);
        try {
            Class.forName(driver).newInstance();
        } catch (Exception e) {
            log.warning(e.getMessage());
        }
        this.connection = DriverManager.getConnection(uri, "sa", "");
        log.info("*** Connection Opened ***");
        return this.connection;
    }

    private String getPSString() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private void BeanToPreparedStatement(T t, PreparedStatement ps) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private static class T {

        public T() {
        }
    }
}

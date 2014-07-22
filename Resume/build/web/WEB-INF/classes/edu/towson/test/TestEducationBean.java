package edu.towson.test;

import edu.towson.dao.EducationBeanDao;
import edu.towson.beans.EducationBean;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
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
import org.apache.commons.dbutils.BeanProcessor;

/**
 *
 * @author korea_fern
 */
@WebServlet(name = "TestEducationBean", urlPatterns = {"/TestEducationBean"})
public class TestEducationBean extends HttpServlet {

    private Connection connection;
    private static final Logger log = Logger.getLogger(TestEducationBean.class.getName());
    private static final String QUERY_BY_ID = "SELECT TOP 1 * FROM ${table} WHERE EDUCATION_ID = ${id}";

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
            out.println("<head><title>Test EducationBean</title></head>");
            out.println("<body>");
            out.println("<h1>This is database testing.</h1>");
            //get connection 
            conn = getConnection();
            stmt = conn.createStatement();
            log.log(Level.INFO, "statement {0}" , stmt.toString());
          //  ResultSet rset = stmt
            
           
            ResultSet rset = stmt.executeQuery("SELECT EDUCATION_ID, INSTITUTE_NAME, DEGREE_NAME, STARTDATE, ENDDATE,DESCRIPTION  FROM EDUCATION");
            ResultSetMetaData metadata = rset.getMetaData();
            int columnsNumber = metadata.getColumnCount();
            while (rset.next()) {
        for (int i = 1; i <= columnsNumber; i++) {
            if (i > 1) out.print(",  ");
            String columnValue = rset.getString(i);
            out.print(columnValue + " " + metadata.getColumnName(i));
        }
            out.println("");
    }
            /*
            out.println("<p>" + rset.getString("EDUCATION_ID") + "," + rset.getString("INSTITUTE_NAME") + "," + rset.getString("DEGREE_NAME") + ","
                    + rset.getString("DEGREE_NAME") + "," + rset.getString("STARTDATE") + "," + rset.getString("ENDDATE") + "," + rset.getString("DESCRIPTION") + "</p>");
            */
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
            store(bean);
            out.println("</body></html>");
        } catch (SQLException ex) {
            Logger.getLogger(TestEducationBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    //add the new bean to the database using store on the dao

    public int store(EducationBean t) {
        int res = 0;// default to false
        String insertStr = getPSString();
        log.info(insertStr);
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

    protected void safeClose(Statement st) {
        if (st != null) {
            try {
                st.close();
            } catch (Exception e) {
                log.warning(e.getMessage());
            }
        }
    }
    // retrieve the bean just added.

    public List<EducationBean> runQuery(String queryStr) {
        queryStr = queryStr.replace("${table}", "EDUCATION");
        List<EducationBean> beans = new ArrayList<EducationBean>(10);
        Connection conn;
        Statement st = null;
        ResultSet rs = null;
        log.info(queryStr);
        try {
            conn = getConnection();
            st = conn.createStatement();
            rs = st.executeQuery(queryStr);
            BeanProcessor bp = new BeanProcessor();
            while (rs.next()) {
                EducationBean bean = (EducationBean) bp.toBean(rs, EducationBean.class);
                beans.add(bean);
            }
        } catch (SQLException e) {
            log.warning(e.getMessage());
        } finally {
            this.safeClose(rs);
            this.safeClose(st);
        }
        return beans;
    }

    public EducationBean findById(int id) {
        String queryStr = QUERY_BY_ID.replace("${table}", Integer.toString(id));
        List<EducationBean> qbArray = runQuery(queryStr);
        if (qbArray != null && qbArray.size() >= 1) {
            return qbArray.get(0);
        }
        return null;
    }
    // print the bean to a page

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
        String url = "jdbc:mysql://localhost:3306/resume1";
        String driver = "com.mysql.jdbc.Driver";
        log.log(Level.INFO, "Database URL: {0}", url);
        try {
            Class.forName(driver).newInstance();
        } catch (Exception e) {
            log.warning(e.getMessage());
        }
        this.connection = DriverManager.getConnection(url, "root", "");
        log.info("*** Connection Opened ***");
        log.log(Level.INFO, "Check connection {0}" , connection.toString());
       
        return this.connection;
    }

    private String getPSString() {
        String insertTableSQL = "INSERT INTO EDUCATION"
                + "(EDUCATION_ID, INSTITUTE_NAME, DEGREE_NAME, STARTDATE, ENDDATE, DESCRIPTION) VALUES"
                + "(?,?,?,?,?,?)";
        return insertTableSQL;
    }

    private void BeanToPreparedStatement(EducationBean name, PreparedStatement ps) throws SQLException {
        ps.setInt(1, name.getEducationId());
        ps.setString(2, name.getInstituteName());
        ps.setString(3, name.getDegreeName());
        ps.setString(4, name.getStartdate());
        ps.setString(5, name.getEnddate());
        ps.setString(6, name.getDescription());
    }

    protected void safeClose(ResultSet rs) {
        if (rs != null) {
            try {
                rs.close();
            } catch (Exception e) {
            }
        }
    }
}

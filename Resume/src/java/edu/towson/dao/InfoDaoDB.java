
package edu.towson.dao;

/*
 * author: Fern Sawitree Euamethiyangkool
 * Adapted from AIT618 - Professor John Bell
 */

import edu.towson.dao.DaoPattern;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import edu.towson.db.DBSupport;
import edu.towson.beans.Info;

public class InfoDaoDB extends DBSupport<Info> implements DaoPattern<Info> {

    CREATE TABLE RESUME_INFO (RESUME_ID INTEGER PRIMARY KEY, FIRST_NAME VARCHAR(32),LAST_NAME VARCHAR(32) , ADDRESS1 VARCHAR(128),ADDRESS2 VARCHAR(128),CITY VARCHAR(32),STATE VARCHAR(32),ZIPCODE INTEGER(10),EMAIL VARCHAR(32),PHONE_NUMBER INTEGER(10));
    
    CREATE TABLE USERS_LOGIN (USER_ID INTEGER PRIMARY KEY,USER_NAME VARCHAR(32),PASSWORD VARCHAR(32));
            
    CREATE TABLE EXPERIENCE(EXPERIENCE_ID INTEGER PRIMARY KEY,COMPANY_NAME VARCHAR(32),DESIGNATION VARCHAR(32) ,START_DATE VARCHAR(10),END_DATE VARCHAR(10),EX_DESCRIPTION VARCHAR(256),RESUME_ID VARCHAR(32) FOREIGN KEY);
            
    CREATE TABLE EDUCATION(EDUCATION_ID INTEGER PRIMARY KEY,INSTITUTE_NAME VARCHAR(32),DEGREE_NAME VARCHAR(32) ,STARTDATE VARCHAR(10),ENDDATE VARCHAR(10),ED_DESCRIPTION VARCHAR(256),RESUME_ID VARCHAR(32) FOREIGN KEY);
            
    CREATE TABLE SKILLS (SKILLS_ID INTEGER PRIMARY KEY,SKILLS_INFO VARCHAR(256),RESUME_ID VARCHAR(32) FOREIGN KEY);
    
    private static boolean initialized = false;
    private static final Logger log = Logger.getLogger(InfoDaoDB.class.getName());
    protected static final String columnNames = "RESUME_ID,FIRST_NAME,LAST_NAME,ADDRESS1,ADDRESS2,CITY,STATE,ZIPCODE,EMAIL,PHONE_NUMBER,USER_ID,USER_NAME,PASSWORD,EXPERIENCE_ID,COMPANY_NAME,\n" +
"            DESIGNATION,START_DATE,END_DATE,EX_DESCRIPTION,EDUCATION_ID,INSTITUTE_NAME,\n" +
"            DEGREE_NAME,STARTNAME,ENDDATE, ED_DESCRIPTION,SKILLS_ID,SKILLS_INFO";

    public InfoDaoDB(String base)
    {
        super(base);
        database = "RESUME";
     
    }

    public void init(boolean willForce)
    {
        if (willForce) {
            initialized = false;
        }
        if (initialized) {
            return;
        }
        log.log(Level.INFO, "basePath: {0}", basePath);
        Info bean = null;
        List<Info> beans;
        try {
            if (!willForce) {
                bean = findFirst();
            }
        }
        finally {
            close();
        }
        initialized = true;
    }

    public int store(Info qb)
    {
        int res = 0;// default to false
        String insertStr = getPSString();
        log.info(insertStr);
        Connection conn;
        PreparedStatement ps = null;
        try {
            conn = getConnection();
            ps = conn.prepareStatement(insertStr);
            BeanToPreparedStatement(qb, ps);
            res = ps.executeUpdate();

            ps.clearParameters();

        }
        catch (SQLException sqe) {
            log.warning(sqe.getMessage());
            System.out.println("there was an error with store for query:" + insertStr);
        }
        finally {
            safeClose(ps);
        }
        return res;
    }

    

    protected void setresume_ID(Info bean, int resume_ID)
    {
        bean.setresume_ID(resume_ID);
    }

    protected int getresume_ID(Info bean)
    {
        return bean.getresume_ID();
    }


    protected Info RsToBean(ResultSet rs) throws SQLException
    {
        Info info = new Info();
        info.setresume_ID(rs.getInt("RESUME_ID"));
        info.setfirst_name(rs.getString("FIRST_NAME"));
        info.setlast_name(rs.getString("LAST_NAME"));
        info.setaddress1(rs.getString("ADDRESS1"));
        info.setaddress2(rs.getString("ADDRESS2"));
        info.setcity(rs.getString("CITY"));
        info.setstate(rs.getString("STATE"));
        info.setzipcode(rs.getInt("ZIPCODE"));
        info.setemail(rs.getString("EMAIL"));
        info.setphone_number(rs.getInt("PHONE_NUMBER"));
        info.setuser_ID(rs.getInt("USER_ID"));
        info.setuser_name(rs.getString("USER_NAME"));
        info.setpassword(rs.getString("PASSWORD"));
        info.setexperience_ID(rs.getInt("EXPERIENCE_ID"));
        info.setcompany_name(rs.getString("COMPANY_NAME"));
        info.setdesignation(rs.getString("DESIGNATION"));
        info.setstart_date(rs.getString("START_DATE"));
        info.setend_date(rs.getString("END_DATE"));
        info.setex_description(rs.getString("EX_DESCRIPTION"));
        info.seteducation_ID(rs.getInt("EDUCATION_ID"));
        info.setinstitute_name(rs.getString("INSTITUTE_NAME"));
        info.setdegree_name(rs.getString("DEGREE_NAME"));
        info.setstartdate(rs.getString("STARTDATE"));
        info.setenddate(rs.getString("ENDDATE"));
        info.seted_description(rs.getString("ED_DESCRIPTION"));
        info.setskills_ID(rs.getInt("SKILLS_ID"));
        info.setskills_info(rs.getString("SKILLS_INFO"));
        
        return info;
    }

    protected String BuildInsertString(Info info)
    {

        StringBuilder columns = new StringBuilder(128);
        StringBuilder values = new StringBuilder(128);
        columns.append("RESUME_ID");
        values.append(info.getresume_ID());
        columns.append(", FIRST_NAME");
        values.append(", '").append(info.getfirst_name()).append("'");
        columns.append(", LAST_NAME");
        values.append(", '").append(info.getlast_name()).append("'");
        columns.append(", ADDRESS1");
        values.append(", '").append(info.getaddress1()).append("'");
        columns.append(", ADDRESS2");
        columns.append(", CITY");
        values.append(", '").append(info.getcity()).append("'");
        values.append(", '").append(info.getaddress2()).append("'");
        columns.append(", STATE");
        values.append(", ").append(info.getstate()).append("");
        columns.append(", ZIPCODE");
        values.append(", '").append(info.getzipcode()).append("'");
        columns.append(", EMAIL");
        values.append(", ").append(info.getemail()).append("'");
        columns.append(", PHONE_NUMBER");
        values.append(", '").append(info.getphone_number()).append("");
        columns.append(", USER_ID");
        values.append(", '").append(info.getuser_ID()).append("");
        columns.append(", USER_NAME");
        values.append(", '").append(info.getuser_name()).append("");
        columns.append(", PASSWORD");
        values.append(", '").append(info.getpassword()).append("");
        columns.append(", EXPERIENCE_ID");
        values.append(", '").append(info.getexperience_ID()).append("");
        columns.append(", COMPANY_NAME");
        values.append(", '").append(info.getcompany_name()).append("");
        columns.append(", DESIGNATION");
        values.append(", '").append(info.getdesignation()).append("");
        columns.append(", START_DATE");
        values.append(", '").append(info.getstart_date()).append("");
        columns.append(", END_DATE");
        values.append(", '").append(info.getend_date()).append("");
        columns.append(", EX_DESCRIPTION");
        values.append(", '").append(info.getex_description()).append("");
        columns.append(", EDUCATION_ID");
        values.append(", '").append(info.geteducation_ID()).append("");
        columns.append(", INSTITUTE_NAME");
        values.append(", '").append(info.getinstitute_name()).append("");
        columns.append(", DEGREE_NAME");
        values.append(", '").append(info.getdegree_name()).append("");
        columns.append(", STARTDATE");
        values.append(", '").append(info.getstartdate()).append("");
        columns.append(", ENDDATE");
        values.append(", '").append(info.getenddate()).append("");
        columns.append(", ED_DESCRIPTION");
        values.append(", '").append(info.geted_description()).append("");
        columns.append(", SKILLS_ID");
        values.append(", '").append(info.getskills_ID()).append("");
        columns.append(", SKILLS_INFO");
        values.append(", '").append(info.getskills_info()).append("");
        return UPDATE_INSERT.replace("${columns}", columns).replace("${values}", values);
    }

    protected String getPSString()
    {
        String rval = UPDATE_INSERT.replace("${columns}", columnNames);
        rval = rval.replace("${resume}", table);
        rval = rval.replace("${values}", "?, ?, ?, ?, ?, ?, ?, ?, ?, ?");
        return rval;
    }

    protected void BeanToPreparedStatement(Info info, PreparedStatement ps) throws SQLException
    {
        ps.setInt(1, info.getresume_ID());
        ps.setString(2, info.getfirst_name());
        ps.setString(3, info.getlast_name());
        ps.setString(4, info.getaddress1());
        ps.setString(5, info.getaddress2());
        ps.setString(6, info.getcity());
        ps.setString(7, info.getstate());
        ps.setInt(8, info.getzipcode());
        ps.setString(9, info.getemail());
        ps.setInt(10, info.getphone_number());
        ps.setInt(11, info.getuser_ID());
        ps.setString(12, info.getuser_name());
        ps.setString(13, info.getpassword());
        ps.setInt(14, info.getexperience_ID());
        ps.setString(15, info.getcompany_name());
        ps.setString(16, info.getdesignation());
        ps.setString(17, info.getstart_date());
        ps.setString(18, info.getend_date());
        ps.setString(19, info.getex_description());
        ps.setInt(20, info.geteducation_ID());
        ps.setString(21, info.getinstitute_name());
        ps.setString(22, info.getdegree_name());
        ps.setString(23, info.getstartdate());
        ps.setString(24, info.getenddate());
        ps.setString(25, info.geted_description());
        ps.setInt(26, info.getskills_ID());
        ps.setString(27, info.getskills_info());
    }
}

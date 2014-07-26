/*
 *Fern
 */
package edu.towson.dao;

import edu.towson.beans.EducationBean;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import edu.towson.db.DBSupport;
import java.util.logging.Logger;

public class EducationBeanDao extends DBSupport<EducationBean> implements DaoPattern<EducationBean> {

    private static boolean initialized = false;
    private static final Logger log = Logger.getLogger(EducationBean.class.getName());
    private static final String INSTITUTE_NAME = "institute_name";
    private static final String DEGREE_NAME = "degree_name";
    private static final String STARTDATE = "startdate";
    private static final String ENDDATE = "enddate";
    private static final String DESCRIPTION = "description";
    private static final String USER_ID = "user_id";
    //protected static final String columnNames = "EDUCATION_ID, INSTITUTE_NAME, DEGREE_NAME, STARTDATE, ENDDATE, DESCRIPTION";
    protected static final String columnNames = USER_ID +"," + INSTITUTE_NAME + ","+ DEGREE_NAME +"," + STARTDATE + ","+ ENDDATE+","+ DESCRIPTION;
    private Connection con = null;
    private ResultSet rs = null;
    private PreparedStatement stmt = null;

    public EducationBeanDao(Connection conn) {
        super(conn);
        database = "resume1";
        table = "Education";
    }

    public void init(boolean willForce) {
    }

    @Override
    protected EducationBean RsToBean(ResultSet rs) throws SQLException {
        EducationBean education = new EducationBean();
        //education.setEducationId(rs.getInt("EDUCATION_ID"));
        education.setUser_id(rs.getInt(USER_ID));
        education.setInstituteName(rs.getString(INSTITUTE_NAME));
        education.setDegreeName(rs.getString(DEGREE_NAME));
        education.setStartdate(rs.getString(STARTDATE));
        education.setEnddate(rs.getString(ENDDATE));
        education.setDescription(rs.getString(DESCRIPTION));
        return education;
    }

    @Override
    protected String BuildInsertString(EducationBean education) {
        StringBuilder columns = new StringBuilder(128);
        StringBuilder values = new StringBuilder(128);
        columns.append(USER_ID);
        values.append(education.getUser_id());
        columns.append("," + INSTITUTE_NAME);
        values.append(", '").append(education.getInstituteName()).append("'");
        columns.append("," + DEGREE_NAME);
        values.append(", '").append(education.getDegreeName()).append("'");
        columns.append(","+ STARTDATE);
        values.append(", '").append(education.getStartdate()).append("'");
        columns.append("," + ENDDATE);
        values.append(", '").append(education.getEnddate()).append("'");
        columns.append("," + DESCRIPTION);
        values.append(", '").append(education.getDescription()).append("'");
        return UPDATE_INSERT.replace("${columns}", columns).replace("${values}", values);
    }

    @Override
    protected void BeanToPreparedStatement(EducationBean education, PreparedStatement ps) throws SQLException {
        ps.setInt(1, education.getUser_id());
        ps.setString(2, education.getInstituteName());
        ps.setString(3, education.getDegreeName());
        ps.setString(4, education.getStartdate());
        ps.setString(5, education.getEnddate());
        ps.setString(6, education.getDescription());
    }

    @Override
    protected String getPSString() {
        String rval = UPDATE_INSERT.replace("${columns}", columnNames);
        rval = rval.replace("${table}", table);// need to set the tabel for the prepared statement, this was missing from the lab source
        rval = rval.replace("${values}", "?, ?, ?, ?, ?, ?");
        return rval;
    }

    @Override
    protected String getId(EducationBean t) {
        String id = Integer.toString(t.getEducationId());
        return id;
    }
}

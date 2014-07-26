/*
 *Fern
 */
package edu.towson.dao;

import edu.towson.beans.TemplateBean;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import edu.towson.db.DBSupport;
import java.util.logging.Logger;

public class TemplateBeanDao extends DBSupport<TemplateBean> implements DaoPattern<TemplateBean> {

    private static boolean initialized = false;
    private static final Logger log = Logger.getLogger(TemplateBean.class.getName());
    private static final String TEMPLATE_ID = "template_id";
    private static final String USER_ID = "user_id";
    //protected static final String columnNames = "EDUCATION_ID, INSTITUTE_NAME, DEGREE_NAME, STARTDATE, ENDDATE, DESCRIPTION";
    protected static final String columnNames = USER_ID +"," + TEMPLATE_ID;
    private Connection con = null;
    private ResultSet rs = null;
    private PreparedStatement stmt = null;

    public TemplateBeanDao(Connection conn) {
        super(conn);
        database = "resume1";
        table = "UserTemplate";
    }

    public void init(boolean willForce) {
    }

    @Override
    protected TemplateBean RsToBean(ResultSet rs) throws SQLException {
        TemplateBean template = new TemplateBean();
        //education.setEducationId(rs.getInt("EDUCATION_ID"));
        template.setUser_id(rs.getInt(USER_ID));
        template.setTemplate_id(rs.getInt(TEMPLATE_ID));
        return template;
    }

    @Override
    protected String BuildInsertString(TemplateBean template) {
        StringBuilder columns = new StringBuilder(128);
        StringBuilder values = new StringBuilder(128);
        columns.append(USER_ID);
        values.append(template.getUser_id());
        columns.append("," + TEMPLATE_ID);
        values.append(", '").append(template.getTemplate_id()).append("'");
        return UPDATE_INSERT.replace("${columns}", columns).replace("${values}", values);
    }

    @Override
    protected void BeanToPreparedStatement(TemplateBean template, PreparedStatement ps) throws SQLException {
        ps.setInt(1, template.getUser_id());
        ps.setInt(2, template.getTemplate_id());
    }

    @Override
    protected String getPSString() {
        String rval = UPDATE_INSERT.replace("${columns}", columnNames);
        rval = rval.replace("${table}", table);// need to set the tabel for the prepared statement, this was missing from the lab source
        rval = rval.replace("${values}", "?, ?");
        return rval;
    }

    @Override
    protected String getId(TemplateBean t) {
        String id = Integer.toString(t.getTemplate_id());
        return id;
    }
}

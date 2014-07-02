
package  edu.towson.dao;

import edu.towson.beans.ExperienceBean;
import static edu.towson.dao.ExperienceBeanDao.columnNames;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import edu.towson.db.DBSupport;
import java.util.logging.Logger;

public class ExperienceBeanDao extends DBSupport<ExperienceBean> implements DaoPattern<ExperienceBean> {

    private static boolean initialized = false;
    private static final Logger log = Logger.getLogger(ExperienceBean.class.getName());
    private static final String COMPANYNAME = "companyname";
    private static final String DESIGNATION = "designation";
    private static final String STARTDATE = "startdate";
    private static final String ENDDATE = "enddate";
    private static final String DESCRIPTION = "description";
    protected static final String columnNames = COMPANYNAME +","+ DESIGNATION +","+ STARTDATE + ","+ ENDDATE + "," + DESCRIPTION;
    private Connection con = null;
    private ResultSet rs = null;
    private PreparedStatement stmt = null;

    public ExperienceBeanDao(Connection conn) {
        super(conn);
        database = "Resume";
        table = "Experience";
    }

    public void init(boolean willForce) {
    }

    @Override
    protected ExperienceBean RsToBean(ResultSet rs) throws SQLException {
        ExperienceBean experience = new ExperienceBean();
        experience.setCompanyName(rs.getString(COMPANYNAME));
        experience.setDesignation(rs.getString(DESIGNATION));
        experience.setStartDate(rs.getString(STARTDATE));
        experience.setEndDate(rs.getString(ENDDATE));
        experience.setDescription(rs.getString(DESCRIPTION));
        
        return experience;
    }

    @Override
    protected String BuildInsertString(ExperienceBean experience) {
        StringBuilder columns = new StringBuilder(128);
        StringBuilder values = new StringBuilder(128);
        columns.append(COMPANYNAME);
        values.append(experience.getCompanyName());
        columns.append("," + DESIGNATION);
        values.append(", '").append(experience.getDesignation()).append("'");
        columns.append("," + STARTDATE);
        values.append(", '").append(experience.getStartDate()).append("'");
        columns.append("," + ENDDATE);
        values.append(", '").append(experience.getEndDate()).append("'");
        columns.append("," + DESCRIPTION);
        values.append(", '").append(experience.getDescription()).append("'");
        return UPDATE_INSERT.replace("${columns}", columns).replace("${values}", values);
    }

    @Override
    protected void BeanToPreparedStatement(ExperienceBean experience, PreparedStatement ps) throws SQLException {
        ps.setString(1, experience.getCompanyName());
        ps.setString(2, experience.getDesignation());
        ps.setString(3, experience.getStartDate());
        ps.setString(4, experience.getEndDate());
        ps.setString(4, experience.getDescription());
    }

    @Override
    protected String getPSString() {
        String rval = UPDATE_INSERT.replace("${columns}", columnNames);
        rval = rval.replace("${EXPERIENCE}", table);// need to set the tabel for the prepared statement, this was missing from the lab source
        rval = rval.replace("${values}", "?, ?, ?, ?, ?");
        return rval;
    }

    @Override
    protected String getId(ExperienceBean t) {
        String id = Integer.toString(t.getExperienceId());
        return id;
    }


}


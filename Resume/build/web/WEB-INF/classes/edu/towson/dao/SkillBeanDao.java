
package  edu.towson.dao;

import edu.towson.beans.SkillBean;
import static edu.towson.dao.SkillBeanDao.columnNames;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import edu.towson.db.DBSupport;
import java.util.logging.Logger;

public class SkillBeanDao extends DBSupport<SkillBean> implements DaoPattern<SkillBean> {

    private static boolean initialized = false;
    private static final Logger log = Logger.getLogger(SkillBean.class.getName());
    private static final String TITLE = "title";
    private static final String DESCRIPTION = "description";
    private static final String YEARS = "years";
    private static final String LEVEL = "level";
    protected static final String columnNames = TITLE +","+ DESCRIPTION +","+ YEARS + ","+ LEVEL;
    private Connection con = null;
    private ResultSet rs = null;
    private PreparedStatement stmt = null;

    public SkillBeanDao(Connection conn) {
        super(conn);
        database = "Resume";
        table = "Skill";
    }

    public void init(boolean willForce) {
    }

    @Override
    protected SkillBean RsToBean(ResultSet rs) throws SQLException {
        SkillBean skills = new SkillBean();
        skills.setTitle(rs.getString(TITLE));
        skills.setDescription(rs.getString(DESCRIPTION));
        skills.setYears(rs.getInt(YEARS));
        skills.setLevel(rs.getString(LEVEL));
        
        
        return skills;
    }

    @Override
    protected String BuildInsertString(SkillBean skills) {
        StringBuilder columns = new StringBuilder(128);
        StringBuilder values = new StringBuilder(128);
        columns.append(TITLE);
        values.append(skills.getTitle());
        columns.append("," + DESCRIPTION);
        values.append(", '").append(skills.getDescription()).append("'");
        columns.append("," + YEARS);
        values.append(", '").append(skills.getYears()).append("'");
        columns.append("," + LEVEL);
        values.append(", '").append(skills.getLevel()).append("'");
        return UPDATE_INSERT.replace("${columns}", columns).replace("${values}", values);
    }

    @Override
    protected void BeanToPreparedStatement(SkillBean skills, PreparedStatement ps) throws SQLException {
        ps.setString(1, skills.getTitle());
        ps.setString(2, skills.getDescription());
        ps.setInt(3, skills.getYears());
        ps.setString(4, skills.getLevel());
        
    }

    @Override
    protected String getPSString() {
        String rval = UPDATE_INSERT.replace("${columns}", columnNames);
        rval = rval.replace("${SKILLS}", table);// need to set the tabel for the prepared statement, this was missing from the lab source
        rval = rval.replace("${values}", "?, ?, ?, ?");
        return rval;
    }

    @Override
    protected String getId(SkillBean t) {
        String id = Integer.toString(t.getSkillId());
        return id;
    }


}


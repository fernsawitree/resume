package edu.towson.dao;

import static edu.towson.dao.UserDao.columnNames;



import edu.towson.beans.UserBean;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import edu.towson.db.DBSupport;
import java.util.logging.Logger;

public class UserDao extends DBSupport<UserBean> implements DaoPattern<UserBean> {

    private static boolean initialized = false;
    private static final Logger log = Logger.getLogger(UserBean.class.getName());
    private static final String USER_ID = "id";
    private static final String USERNAME = "username";
    private static final String PASSWORD = "password";
    private static final String FIRSTNAME = "firstname";
    private static final String LASTNAME = "lastname";
    private static final String ADDRESS = "address";
    private static final String CITY = "city";
    private static final String STATE = "state";
    private static final String ZIPCODE = "zipcode";
    private static final String EMAIL = "email";
    private static final String PHONENUMBER = "phonenumber";
    //protected static final String columnNames = USER_ID + ","+USERNAME +","+ PASSWORD +","+ FIRSTNAME + ","+ LASTNAME + "," + PHONENUMBER + "," + EMAIL;
    protected static final String columnNames = USERNAME + "," + PASSWORD + "," + FIRSTNAME + "," + LASTNAME + "," + ADDRESS + "," + CITY + "," + STATE + "," + ZIPCODE + "," + PHONENUMBER + "," + EMAIL;
    private Connection con = null;
    private ResultSet rs = null;
    private PreparedStatement stmt = null;

    public UserDao(Connection conn) {
        super(conn);
        database = "resume1";
        table = "User";
    }

    public void init(boolean willForce) {
    }

    @Override
    protected UserBean RsToBean(ResultSet rs) throws SQLException {
        UserBean user = new UserBean();
        user.setUsername(rs.getString(USERNAME));
        user.setPassword(rs.getString(PASSWORD));
        user.setFirstname(rs.getString(FIRSTNAME));
        user.setLastname(rs.getString(LASTNAME));
        user.setAddress(rs.getString(ADDRESS));
        user.setCity(rs.getString(CITY));
        user.setState(rs.getString(STATE));
        user.setZipcode(rs.getString(ZIPCODE));
        user.setEmail(rs.getString(EMAIL));
        user.setPhoneNumber(rs.getString(PHONENUMBER));
        //user.setUser_id(rs.getInt(USER_ID));





        return user;
    }

    @Override
    protected String BuildInsertString(UserBean user) {
        StringBuilder columns = new StringBuilder(128);
        StringBuilder values = new StringBuilder(128);
        //   columns.append("," + USER_ID);
        // values.append(", '").append(user.getUser_id()).append("'");
        columns.append(USERNAME);
        values.append(user.getUsername());
        columns.append("," + PASSWORD);
        values.append(", '").append(user.getPassword()).append("'");
        columns.append("," + FIRSTNAME);
        values.append(", '").append(user.getFirstname()).append("'");
        columns.append("," + LASTNAME);
        values.append(", '").append(user.getLastname()).append("'");
        columns.append("," + ADDRESS);
        values.append(", '").append(user.getAddress()).append("'");
        columns.append("," + CITY);
        values.append(", '").append(user.getCity()).append("'");
        columns.append("," + STATE);
        values.append(", '").append(user.getState()).append("'");
        columns.append("," + ZIPCODE);
        values.append(", '").append(user.getZipcode()).append("'");
        columns.append("," + PHONENUMBER);
        values.append(", '").append(user.getPhoneNumber()).append("'");
        columns.append("," + EMAIL);
        values.append(", '").append(user.getEmail()).append("'");

        return UPDATE_INSERT.replace("${columns}", columns).replace("${values}", values);
    }

    @Override
    protected void BeanToPreparedStatement(UserBean user, PreparedStatement ps) throws SQLException {
        //   ps.setInt(1,user.getUser_id());
        ps.setString(1, user.getUsername());
        ps.setString(2, user.getPassword());
        ps.setString(3, user.getFirstname());
        ps.setString(4, user.getLastname());
        ps.setString(5, user.getAddress());
        ps.setString(6, user.getCity());
        ps.setString(7, user.getState());
        ps.setString(8, user.getZipcode());
        ps.setString(9, user.getPhoneNumber());
        ps.setString(10, user.getEmail());
    }

    @Override
    protected String getPSString() {
        String rval = UPDATE_INSERT.replace("${columns}", columnNames);
        rval = rval.replace("${table}", "User");// need to set the tabel for the prepared statement, this was missing from the lab source
        rval = rval.replace("${values}", "?, ?, ?, ?, ?, ?, ? ,? , ? , ?");
        return rval;
    }

    @Override
    protected String getId(UserBean t) {
        String id = Integer.toString(t.getUser_id());
        return id;
    }
}

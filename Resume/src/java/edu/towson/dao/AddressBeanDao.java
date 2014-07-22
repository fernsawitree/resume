
package  edu.towson.dao;

import edu.towson.beans.AddressBean;
import static edu.towson.dao.AddressBeanDao.columnNames;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import edu.towson.db.DBSupport;
import java.util.logging.Logger;

public class AddressBeanDao extends DBSupport<AddressBean> implements DaoPattern<AddressBean> {

    private static boolean initialized = false;
    private static final Logger log = Logger.getLogger(AddressBean.class.getName());
    private static final String ADDRESS1 = "addressname";
    private static final String CITY = "city";
    private static final String STATE = "state";
    private static final String ZIPCODE = "zipcode";
    private static final String USER_ID = "user_id";
    protected static final String columnNames = ADDRESS1 +","+ CITY +","+ STATE + ","+ ZIPCODE;
    private Connection con = null;
    private ResultSet rs = null;
    private PreparedStatement stmt = null;

    public AddressBeanDao(Connection conn) {
        super(conn);
        database = "resume1";
        table = "Address";
    }

    public void init(boolean willForce) {
    }

    @Override
    protected AddressBean RsToBean(ResultSet rs) throws SQLException {
        AddressBean address = new AddressBean();
        address.setUser_id(rs.getInt(USER_ID));
        address.setAddress1(rs.getString(ADDRESS1));
        address.setCity(rs.getString(CITY));
        address.setState(rs.getString(STATE));
        address.setZipcode(rs.getString(ZIPCODE));
        
        
        return address;
    }

    @Override
    protected String BuildInsertString(AddressBean address) {
        StringBuilder columns = new StringBuilder(128);
        StringBuilder values = new StringBuilder(128);
        columns.append(ADDRESS1);
        values.append(address.getAddress1());
        columns.append("," + CITY);
        values.append(", '").append(address.getCity()).append("'");
        columns.append("," + STATE);
        values.append(", '").append(address.getState()).append("'");
        columns.append("," + ZIPCODE);
        values.append(", '").append(address.getZipcode()).append("'");
        return UPDATE_INSERT.replace("${columns}", columns).replace("${values}", values);
    }

    @Override
    protected void BeanToPreparedStatement(AddressBean address, PreparedStatement ps) throws SQLException {
        ps.setString(1, address.getAddress1());
        ps.setString(2, address.getCity());
        ps.setString(3, address.getState());
        ps.setInt(4, address.getZipcode());
        
    }

    @Override
    protected String getPSString() {
        String rval = UPDATE_INSERT.replace("${columns}", columnNames);
        rval = rval.replace("${ADDRESS}", table);// need to set the tabel for the prepared statement, this was missing from the lab source
        rval = rval.replace("${values}", "?, ?, ?, ?");
        return rval;
    }

    @Override
    protected String getId(AddressBean t) {
        String id = Integer.toString(t.getAddress_id());
        return id;
    }


}


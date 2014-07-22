
package edu.towson.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;


abstract public class DBSupport<T> {
//These queries are generic for any table

    protected static final String QUERY_FIRST = "SELECT TOP 1 * FROM ${table}";
    protected static final String QUERY_LAST = "SELECT * FROM ${table} ORDER BY user_id DESC LIMIT 1";//it was by id
    protected static final String QUERY_NEXT = "SELECT TOP 1 * FROM ${table} WHERE ID > ${id}";
    protected static final String QUERY_PREV = "SELECT TOP 1 * FROM ${table} WHERE ID < ${id} ORDER BY id DESC";
    protected static final String QUERY_BY_ID = "SELECT TOP 1 * FROM ${table} WHERE ID = ${id}";
    protected static final String QUERY_ALL = "SELECT * FROM ${table}";
    protected static final String UPDATE_DROP = "DROP TABLE ${table} IF EXISTS";
    protected static final String UPDATE_INSERT = "INSERT INTO ${table} (${columns}) values (${values});";

    /*-- concrete class should override these values */
    protected String table = "RESUME";
    protected String database = "resume1";
    protected String basePath = null;
    private Connection connection = null;
    private static final Logger log = Logger.getLogger(DBSupport.class.getName());

  
    abstract protected String getId(T t);
    abstract protected T RsToBean(ResultSet rs) throws SQLException;

    /**
     * Builds a SQL Insert statement from a bean
     * <p/>
     * @param t
     * <p/>
     * @return
     */
    abstract protected String BuildInsertString(T t);

    /**
     * Sets the parameters of PreparedStatement using attributes of the bean
     * <p/>
     * @param t
     * @param ps
     * <p/>
     * @throws SQLException
     */
    abstract protected void BeanToPreparedStatement(T t, PreparedStatement ps) throws SQLException;

    /**
     * returns a string that can be used for a PreparedStatement to write the
     * bean to a table row
     * <p/>
     * @return
     */
    abstract protected String getPSString();

    public DBSupport(Connection conn)
    {
        this.connection = conn;
    }

    public void setTable(String table)
    {
        this.table = table;
    }

    public String getTable()
    {
        return this.table;
    }

    public void close()
    {
        closeConn();
    }

    protected void closeConn()
    {
        if (this.connection != null) {
            try {
                this.connection.close();
            }
            catch (Exception e) {
            }
            finally {
                log.info("*** Connection Closed ***");
                this.connection = null;
            }
        }
    }

    protected void safeClose(Statement st)
    {
        if (st != null) {
            try {
                st.close();
            }
            catch (Exception e) {
                log.warning(e.getMessage());
            }
        }
    }

    protected void safeClose(ResultSet rs)
    {
        if (rs != null) {
            try {
                rs.close();
            }
            catch (Exception e) {
            }
        }
    }

    public T findFirst()
    {
        log.info("findFirst");
        List<T> qbArray = runQuery(DBSupport.QUERY_FIRST);
        if (qbArray != null && qbArray.size() >= 1) {
            return qbArray.get(0);
        }
        return null;
    }

    public T findLast()
    {
        log.info("findLast");
        List<T> qbArray = runQuery(DBSupport.QUERY_LAST);
        if (qbArray != null && qbArray.size() >= 1) {
            return qbArray.get(0);
        }
        return null;
    }

    public T findNext(T t)
    {
        log.log(Level.INFO, "findNext after: {0}", getId(t));
        String queryStr = DBSupport.QUERY_NEXT.replace("${id}", getId(t));
        List<T> qbArray = runQuery(queryStr);
        if (qbArray != null && qbArray.size() >= 1) {
            return qbArray.get(0);
        }
        return findFirst();
    }

    public T findPrevious(T t)
    {
        log.log(Level.INFO, "findNext after: {0}", getId(t));
        String queryStr = DBSupport.QUERY_PREV.replace("${id}", getId(t));
        List<T> qbArray = runQuery(queryStr);
        if (qbArray != null && qbArray.size() >= 1) {
            return qbArray.get(0);
        }
        return findLast();
    }

    public T findById(int id)
    {
        String queryStr = DBSupport.QUERY_BY_ID.replace("${id}", Integer.toString(id));
        List<T> qbArray = runQuery(queryStr);
        if (qbArray != null && qbArray.size() >= 1) {
            return qbArray.get(0);
        }
        return null;
    }

    public List<T> findAll()
    {
        List<T> qbArray = runQuery(DBSupport.QUERY_ALL);
        return qbArray;
    }

    protected List<T> runQuery(String queryStr)
    {
        queryStr = queryStr.replace("${table}", table);
        List<T> beans = new ArrayList<T>(10);
        Connection conn;
        Statement st = null;
        ResultSet rs = null;
        log.info(queryStr);
        try {
            conn = getConnection();
            st = conn.createStatement();
            rs = st.executeQuery(queryStr);
            while (rs.next()) {
                T bean = RsToBean(rs);
                beans.add(bean);
            }
        }
        catch (SQLException e) {
            log.warning(e.getMessage());
        }
        finally {
            this.safeClose(rs);
            this.safeClose(st);
        }
        return beans;
    }

    protected int updateTable(String updateStr)
    {
        String updateQuery = updateStr.replace("${table}", table);
        log.info(updateQuery);
        Connection conn;
        Statement st = null;
        int rv = 0;
        try {
            conn = getConnection();
            st = conn.createStatement();
            rv = st.executeUpdate(updateQuery);
        }
        catch (SQLException sqe) {
            log.warning(sqe.getMessage());
            System.out.println("exception message:" + sqe.getMessage());
            System.out.println("there was an error with updateTable for query:" + updateQuery);
        }
        finally {
            this.safeClose(st);
        }
        return rv;
    }
    
    public int store(T t) {
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

    private Connection getConnection(){
        return this.connection;
    }

}

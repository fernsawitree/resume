
package edu.towson.dao;

import edu.towson.dao.InfoDaoDB;
import edu.towson.dao.DaoPattern;
//import edu.towson.beans.Info;

/**
 *
 * Fern Sawitree Euamethiyangkool
 */

public class DaoFactory {

    public static final int DB_DAO = 1;
    private final String base;

    public DaoFactory(String basepath)
    {
        this.base = basepath;
    }

    public DaoPattern<Info> getDao(String type)
    {
        if ("DB".equalsIgnoreCase(type)) {
            return getDbDAO();
        }
        return null;
    }

    public DaoPattern<Info> getDbDAO()
    {
        InfoDaoDB dao = new InfoDaoDB(base);
        dao.init(false);
        return dao;
    }

    public void init(DaoPattern<Info> dao, boolean willForce)
    {
        if (dao instanceof InfoDaoDB) {
            initDb(willForce);
        }
    }


    private void initDb(boolean willForceInit)
    {
        InfoDaoDB dao = new InfoDaoDB(base);
        dao.init(willForceInit);
    }
}

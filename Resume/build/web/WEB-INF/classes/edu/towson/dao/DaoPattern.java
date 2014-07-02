
package edu.towson.dao;

import java.util.List;

/**
 *
 * @author korea_fern
 */


public interface DaoPattern<T> {

  public T findById(int id);
  public List<T> findAll();
  public int store(T t);
  public void close();
}
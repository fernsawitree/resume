/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.towson.beans;

import java.util.Date;

/**
 * 
 * @author korea_fern
 */
public class LoginBean {
    private String id;
    private String userName;
    private String passwordHash;
    private Date creationDate;
    private Date lastLogin;
    private Date lastErrorTime;
    private int lastError;
}

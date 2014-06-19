/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.towson.beans;

/**
 *
 * @author korea_fern
 */
public class ExperienceBean {
    
    private int experience_ID;
    private String company_name;
    private String designation;
    private String start_date;
    private String end_date;
    private String ex_description;

    /**
     * @return the experience_ID
     */
    public int getExperience_ID() {
        return experience_ID;
    }

    /**
     * @param experience_ID the experience_ID to set
     */
    public void setExperience_ID(int experience_ID) {
        this.experience_ID = experience_ID;
    }

    /**
     * @return the company_name
     */
    public String getCompany_name() {
        return company_name;
    }

    /**
     * @param company_name the company_name to set
     */
    public void setCompany_name(String company_name) {
        this.company_name = company_name;
    }

    /**
     * @return the designation
     */
    public String getDesignation() {
        return designation;
    }

    /**
     * @param designation the designation to set
     */
    public void setDesignation(String designation) {
        this.designation = designation;
    }

    /**
     * @return the start_date
     */
    public String getStart_date() {
        return start_date;
    }

    /**
     * @param start_date the start_date to set
     */
    public void setStart_date(String start_date) {
        this.start_date = start_date;
    }

    /**
     * @return the end_date
     */
    public String getEnd_date() {
        return end_date;
    }

    /**
     * @param end_date the end_date to set
     */
    public void setEnd_date(String end_date) {
        this.end_date = end_date;
    }

    /**
     * @return the ex_description
     */
    public String getEx_description() {
        return ex_description;
    }

    /**
     * @param ex_description the ex_description to set
     */
    public void setEx_description(String ex_description) {
        this.ex_description = ex_description;
    }
}

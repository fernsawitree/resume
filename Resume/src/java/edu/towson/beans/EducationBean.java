/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.towson.beans;

/**
 *
 * @author korea_fern
 */
public class EducationBean {
    
    private int educationId;
    private String instituteName;
    private String degreeName;
    private String startdate;
    private String enddate;
    private String description;

    /**
     * @return the education_ID
     */
    public int getEducationId() {
        return educationId;
    }

    /**
     * @param education_ID the education_ID to set
     */
    public void setEducationId(int educationId) {
        this.educationId = educationId;
    }

    /**
     * @return the institute_name
     */
    public String getInstituteName() {
        return instituteName;
    }

    /**
     * @param institute_name the institute_name to set
     */
    public void setInstituteName(String instituteName) {
        this.instituteName = instituteName;
    }

    /**
     * @return the degree_name
     */
    public String getDegreeName() {
        return degreeName;
    }

    /**
     * @param degree_name the degree_name to set
     */
    public void setDegreeName(String degreeName) {
        this.degreeName = degreeName;
    }

    /**
     * @return the startdate
     */
    public String getStartdate() {
        return startdate;
    }

    /**
     * @param startdate the startdate to set
     */
    public void setStartdate(String startdate) {
        this.startdate = startdate;
    }

    /**
     * @return the enddate
     */
    public String getEnddate() {
        return enddate;
    }

    /**
     * @param enddate the enddate to set
     */
    public void setEnddate(String enddate) {
        this.enddate = enddate;
    }

    /**
     * @return the ed_description
     */
    public String getDescription() {
        return description;
    }

    /**
     * @param ed_description the ed_description to set
     */
    public void setDescription(String ed_description) {
        this.description = ed_description;
    }
}

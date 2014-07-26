
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
    private int user_id;

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
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * @param description the description to set
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * @return the User_id
     */
    public int getUser_id() {
        return user_id;
    }

    /**
     * @param User_id the User_id to set
     */
    public void setUser_id(int User_id) {
        this.user_id = User_id;
    }
}

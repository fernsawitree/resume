/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.towson.beans;

/**
 *
 * @author korea_fern
 */
public class SkillBean {
    private int SkillId;
    private String title;
    private String description;
    private String years;
    private String level;
    private int user_id;

    /**
     * @return the SkillId
     */
    public int getSkillId() {
        return SkillId;
    }

    /**
     * @param SkillId the SkillId to set
     */
    public void setSkillId(int SkillId) {
        this.SkillId = SkillId;
    }

    /**
     * @return the title
     */
    public String getTitle() {
        return title;
    }

    /**
     * @param title the title to set
     */
    public void setTitle(String title) {
        this.title = title;
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
     * @return the years
     */
    public String getYears() {
        return years;
    }

    /**
     * @param years the years to set
     */
    public void setYears(String years) {
        this.years = years;
    }
    
    
    

    /**
     * @return the level
     */
    public String getLevel() {
        return level;
    }

    /**
     * @param level the level to set
     */
    public void setLevel(String level) {
        this.level = level;
    }

    /**
     * @return the user_id
     */
    public int getUser_id() {
        return user_id;
    }

    /**
     * @param user_id the user_id to set
     */
    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }
    
    
}

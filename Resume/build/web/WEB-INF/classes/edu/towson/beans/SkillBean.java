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
    private int years;
    private String level;

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
    public int getYears() {
        return years;
    }

    /**
     * @param years the years to set
     */
    public void setYears(int years) {
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
    
    
}

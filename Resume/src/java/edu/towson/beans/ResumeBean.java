/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.towson.beans;

import java.util.ArrayList;
import java.util.List;
import edu.towson.beans.EducationBean;
import edu.towson.beans.ExperienceBean;
import edu.towson.beans.InfoBean;
import edu.towson.beans.SkillBean;

/**
 *
 * @author korea_fern
 */
public class ResumeBean {
    String id;
    
    private InfoBean info;
    //EXPERIENCE table
    private List<ExperienceBean> experienceList = new ArrayList<ExperienceBean>();
    //EDUCATION table
    private List<EducationBean> educationList = new ArrayList<EducationBean>();
    //SKILLS table
    private List<SkillBean> skills= new ArrayList<SkillBean>();
    public ResumeBean(){};
}

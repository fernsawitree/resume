package edu.towson.beans;

/**
 *
 * @author Fern Sawitree Euamethiyangkool
 */

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class InfoBean implements Serializable {
    //RESUME_INFO table
    private int resumeId;
    private String firstName;
    private String lastName;
    private String address1;
    private String address2;
    private String city;
    private String state;
    private int zipcode;
    private String email;
    private int phoneNumber;
    private String userName;
    
    //EXPERIENCE table
    private List<ExperienceBean> experienceList = new ArrayList<ExperienceBean>();
    //EDUCATION table
    private List<EducationBean> educationList = new ArrayList<EducationBean>();
    //SKILLS table
    private List<SkillBean> skills= new ArrayList<SkillBean>();

    public InfoBean()
    {
        // empty constructor
    }
}

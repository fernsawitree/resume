
package edu.towson.beans;


/**
 *
 * @author Fern Sawitree Euamethiyangkool
 */

import java.io.Serializable;

public class Info implements Serializable {
    //RESUME_INFO table
    private int resume_ID;
    private String first_name;
    private String last_name;
    private String address1;
    private String address2;
    private String city;
    private String state;
    private int zipcode;
    private String email;
    private int phone_number;
    //USERS_LOGIN table
    private int user_ID;
    private String user_name;
    private String password;
    //EXPERIENCE table
    private int experience_ID;
    private String company_name;
    private String designation;
    private String start_date;
    private String end_date;
    private String ex_description;
    //EDUCATION table
    private int education_ID;
    private String institute_name;
    private String degree_name;
    private String startdate;
    private String enddate;
    private String ed_description;
    //SKILLS table
    private int skills_ID;
    private String skills_info;
    

    public Info()
    {
        // empty constructor
    }


    public Info(int resume_ID, String first_name, String last_name, String address1, String address2, 
            String city, String state, int zipcode, String email, int phone_number, int user_ID,String user_name, String password, int experience_ID, String company_name,
            String designation, String start_date, String end_date, String ex_description, int education_ID, String institute_name,
            String degree_name, String startdate, String enddate, String ed_description, int skills_ID, String skills_info)
    {
        this.resume_ID = user_ID;
        this.first_name = first_name;
        this.last_name = last_name;
        this.address1 = address1;
        this.address2 = address2;
        this.city = city;
        this.state = state;
        this.zipcode = zipcode;
        this.email = email;
        this.phone_number = phone_number;
        this.user_ID = user_ID;
        this.user_name = user_name;
        this.password = password;
        this.experience_ID = experience_ID;
        this.company_name = company_name;
        this.designation = designation;
        this.start_date = start_date;
        this.end_date = end_date;
        this.ex_description = ex_description;
        this.education_ID = education_ID;
        this.institute_name = institute_name;
        this.degree_name = degree_name;
        this.startdate = startdate;
        this.enddate = enddate;
        this.ed_description = ed_description;
        this.skills_ID = skills_ID;
        this.skills_info = skills_info;
        
        
        
        
        
    }

 /*
    public Info(int resume_ID,String first_name,String last_name,String address1,String address2, 
            String city, String state, String zipcode, String email, String phone_number,int user_ID,String username, String password, 
            int experience_ID, String company_name,
            String designation, String start_date, String end_date, String ex_description, int education_ID, String institute_name,
            String degree_name, String startdate, String enddate, String ed_description, int skills_ID, String skills_info)
    {
        this(resume_ID,first_name,last_name,address1,address2, 
            city,state,zipcode,email,phone_number,user_ID,username,password,experience_ID,company_name,
            designation,start_date,end_date,ex_description,education_ID,institute_name,
            degree_name,startdate,enddate, ed_description,skills_ID,skills_info); // call the main constructor with the changed image name
    }
 */

    /**
     * this provides a way to make an exact state copy of the item, including
     * it's id.
     *
     * @return a new Item with the same state as the current object
     */
    public Info makeCopy()
    {
        return new Info(this.resume_ID,
        this.first_name,
        this.last_name,
        this.address1,
        this.address2,
        this.city,
        this.state,
        this.zipcode,
        this.email,
        this.phone_number,
        this.user_ID,
        this.user_name,
        this.password,
        this.experience_ID,
        this.company_name,
        this.designation,
        this.start_date,
        this.end_date,
        this.ex_description,
        this.education_ID,
        this.institute_name,
        this.degree_name,
        this.startdate,
        this.enddate,
        this.ed_description,
        this.skills_ID,
        this.skills_info);
    }

    /**
     * @return resume id
     */
    public int getresume_ID()
    {
        return resume_ID;
    }

    /**
     * @param resume_ID the resume_ID to set
     */
    public void setresume_ID(int resume_ID)
    {
        this.resume_ID = resume_ID;
    }

    /**
     * parse integer to String
     */
    public void setresume_ID(String resume_ID)
    {
        this.resume_ID = Integer.parseInt(resume_ID);
    }

    /**
     * @return the first_name
     */
    public String getfirst_name()
    {
        return first_name;
    }

    /**
     * @param first_name the first_name to set
     */
    public void setfirst_name(String first_name)
    {
        this.first_name = first_name;
    }

    /**
     * @return the last_name
     */
    public String getlast_name()
    {
        return last_name;
    }

    /**
     * @param last_name the last_name to set
     */
    public void setlast_name(String last_name)
    {
        this.last_name = last_name;
    }

    /**
     * @return the address1
     */
    public String getaddress1()
    {
        return address1;
    }

    /**
     * @param address1 the address1 to set
     */
    public void setaddress1(String address1)
    {
        this.address1 = address1;
    }

    /**
     * @return the address2
     */
    public String getaddress2()
    {
        return address2;
    }

    /**
     * @param address2 the address2 to set
     */
    public void setaddress2(String address2)
    {
        this.address2 = address2;
    }

    /**
     * @return the city
     */
    public String getcity()
    {
        return city;
    }

    /**
     * @param city the city to set
     */
    public void setcity(String city)
    {
        this.city = city;
    }

    /**
     * @return the state
     */
    public String getstate()
    {
        return state;
    }

    /**
     * @param state the state to set
     */
    public void setstate(String state)
    {
        this.state = state;
    }

    /**
     * @return the zip code
     */
    public int getzipcode()
    {
        return zipcode;
    }

    /**
     * @param zipcode the zip code to set
     */
    public void setzipcode(int zipcode)
    {
        this.zipcode = zipcode;
    }
  
        /**
     * parse integer to String
     */
    public void setzipcode(String zipcode)
    {
        this.zipcode = Integer.parseInt(zipcode);
    }

    /**
     * @return the email
     */
    public String getemail()
    {
        return email;
    }

    /**
     * @param email the email to set
     */
    public void setemail(String email)
    {
        this.email = email;
    }

    /**
     * @return the phone_number
     */
    public int getphone_number()
    {
        return phone_number;
    }

    /**
     * @param phone_number the phone_number to set
     */
    public void setphone_number(int phone_number)
    {
        this.phone_number = phone_number;
    }
    
        /**
     * parse integer to String
     */
    public void setphone_number(String phone_number)
    {
        this.phone_number = Integer.parseInt(phone_number);
    }
    /**
     * @return the user_ID
     */
     public int getuser_ID()
    {
        return user_ID;
    }

    /**
     * @param user_ID the user_ID to set
     */
    public void setuser_ID(int user_ID)
    {
        this.user_ID = user_ID;
    }
    /**
     *convert integer to String
     */
     public void setuser_ID(String user_ID)
    {
        this.user_ID = Integer.parseInt(user_ID);
    }
    /**
     * @return the username
     */
     public String getuser_name()
    {
        return user_name;
    }

    /**
     * @param user_name the username to set
     */
    public void setuser_name(String user_name)
    {
        this.user_name = user_name;
    }
    /**
     * @return the password
     */
        public String getpassword()
    {
        return password;
    }

    /**
     * @param password the password to set
     */
    public void setpassword(String password)
    {
        this.password = password;
    }
    /**
     * @return the experience_ID
     */
     public int getexperience_ID()
    {
        return experience_ID;
    }

    /**
     * @param experience_ID the experience_ID to set
     */
    public void setexperience_ID(int experience_ID)
    {
        this.experience_ID = experience_ID;
    }
    
    /**
     * convert integer to String
     */
     public void setexperience_ID(String experience_ID)
    {
        this.experience_ID = Integer.parseInt(experience_ID);
    }
    
    /**
     * @return the company_name
     */
    public String getcompany_name()
    {
        return company_name;
    }

    /**
     * @param company_name the company_name to set
     */
    public void setcompany_name(String company_name)
    {
        this.company_name = company_name;
    }
     
    /**
     * @return the designation
     */
    public String getdesignation()
    {
        return designation;
    }

    /**
     * @param designation the designation to set
     */
    public void setdesignation(String designation)
    {
        this.designation = designation;
    }
    
    /**
     * @return the start_date
     */
    public String getstart_date()
    {
        return start_date;
    }

    /**
     * @param start_date the start_date to set
     */
    public void setstart_date(String start_date)
    {
        this.start_date = start_date;
    }
    
    /**
     * @return the end_date
     */
    public String getend_date()
    {
        return end_date;
    }

    /**
     * @param end_date the end_date to set
     */
    public void setend_date(String end_date)
    {
        this.end_date = end_date;
    }
    
    /**
     * @return the ex_description
     */
    public String getex_description()
    {
        return ex_description;
    }

    /**
     * @param ex_description the ex_description to set
     */
    public void setex_description(String ex_description)
    {
        this.ex_description = ex_description;
    }
    /**
     * @return the education_ID
     */
    public int geteducation_ID()
    {
        return education_ID;
    }

    /**
     * @param education_ID the education_ID to set
     */
    public void seteducation_ID(int education_ID)
    {
        this.education_ID = education_ID;
    }
    /**
     * convert integer to String
     */
     public void seteducation_ID(String education_ID)
    {
        this.education_ID = Integer.parseInt(education_ID);
    }
     
       /**
     * @return the institute_name
     */
    public String getinstitute_name()
    {
        return institute_name;
    }

    /**
     * @param institute_name the institute_name to set
     */
    public void setinstitute_name(String institute_name)
    {
        this.institute_name = institute_name;
    } 
     
       /**
     * @return the degree_name
     */
    public String getdegree_name()
    {
        return degree_name;
    }

    /**
     * @param degree_name the degree_name to set
     */
    public void setdegree_name(String degree_name)
    {
        this.degree_name = degree_name;
    }
    
       /**
     * @return the start date
     */
    public String getstartdate()
    {
        return startdate;
    }

    /**
     * @param startdate the start date to set
     */
    public void setstartdate(String startdate)
    {
        this.startdate = startdate;
    }
     
       /**
     * @return the end date
     */
    public String getenddate()
    {
        return enddate;
    }

    /**
     * @param enddate the end date to set
     */
    public void setenddate(String enddate)
    {
        this.enddate = enddate;
    }
    
       /**
     * @return the ed_description
     */
    public String geted_description()
    {
        return ed_description;
    }

    /**
     * @param ed_description the ed_description to set
     */
    public void seted_description(String ed_description)
    {
        this.ed_description = ed_description;
    }
    
    /**
     * @return skills_ID
     */ 
    public int getskills_ID()
    {
        return skills_ID;
    }

    /**
     * @param skills_ID the skills_ID to set
     */
    public void setskills_ID(int skills_ID)
    {
        this.skills_ID = skills_ID;
    }
    /**
     * convert integer to String
     */
     public void setskills_ID(String skills_ID)
    {
        this.skills_ID = Integer.parseInt(skills_ID);
    }
    
      
     /**
     * @return the skills_info
     */
    public String getskills_info()
    {
        return skills_info;
    }

    /**
     * @param skills_info the skills_info to set
     */
    public void setskills_info(String skills_info)
    {
        this.skills_info = skills_info;
    }
    
}

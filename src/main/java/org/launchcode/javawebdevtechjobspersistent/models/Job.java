package org.launchcode.javawebdevtechjobspersistent.models;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotEmpty;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Job extends AbstractEntity{                    // updated per Part3.UJ.1

//    @Id                                                   // removed per Part3.UJ.1
//    @GeneratedValue                                       // removed per Part3.UJ.1
//    private int id;                                       // removed per Part3.UJ.1

//    private String name;                                  // removed per Part3.UJ.1

//    private String employer;                              // changed per Part3.UJ.2
    @ManyToOne                                              // added per Part3.UJ.3
    private Employer employer;                              // changed per Part3.UJ.2

//    private String skills;                                // changed per Part 4.RJ.1
    @ManyToMany                                             // added per Part 4.RJ.1
    @NotEmpty(message = "A skill must be selected")       // error w/ @NotBlank, @NotNull, @NotEmpty;
                                                            // added back and it corrected error below
//org.springframework.web.bind.MissingServletRequestParameterException: Required List parameter 'skills' is not present
    private List<Skill> skills = new ArrayList<>();         // changed per Part 4.RJ.1; see 18.5.4

//    public Job(String anEmployer, String someSkills) {    // changed per Part3.UJ.2
//    public Job(Employer anEmployer, String someSkills) {  // changed per Part3.UJ.2, Part 4.RJ.1
    public Job(Employer anEmployer, List<Skill> someSkills) {    // changed per Part 4.RJ.1
        super();
        this.employer = anEmployer;
        this.skills = someSkills;
    }

    public Job() {
    }
    // Getters and setters.

//    public String getName() {                             // removed per Part3.UJ.1
//        return name;
//    }

//    public void setName(String name) {                    // removed per Part3.UJ.1
//        this.name = name;
//    }

//    public String getEmployer() {                         // changed per Part3.UJ.2
    public Employer getEmployer() {                         // changed per Part3.UJ.2
        return employer;
    }

//    public void setEmployer(String employer) {            // changed per Part3.UJ.2
    public void setEmployer(Employer employer) {            // changed per Part3.UJ.2
        this.employer = employer;
    }

//    public String getSkills() {                           // changed per Part4.RJ.1
    public List<Skill> getSkills() {                              // changed per Part4.RJ.1
        return skills;
    }

//    public void setSkills(String skills) {                // changed per Part4.RJ.1
    public void setSkills(List<Skill> skills) {                   // changed per Part4.RJ.1
        this.skills = skills;
    }

}

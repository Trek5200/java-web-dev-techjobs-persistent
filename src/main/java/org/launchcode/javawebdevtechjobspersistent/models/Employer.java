package org.launchcode.javawebdevtechjobspersistent.models;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.List;

@Entity                                                 // added Part2.M.2
public class Employer extends AbstractEntity {

    @NotBlank(message = "City is required")             // added Part2.M.1
    private String location;                            // added Part2.M.1

//    Tried (mappedBy = "employer") tried employerId, too - neither worked; Also can't use mappedBy with JoinColumn
//    @JoinColumn(name = "employerId") //Did not work; gave error below
//    Caused by: org.hibernate.DuplicateMappingException:
//    Table [job] contains physical column name [employer_id] referred to by multiple logical column names:
//    [employerId], [employer_id]
    @OneToMany                                          // added Part3.A.2
    @JoinColumn(name = "employer_id")                   // added Part3.A.2
    // Why employer_id works is not entirely clear to me!, but hibernate message gave indication to try this
    private final List<Job> jobs = new ArrayList<>();   // added Part3.A.1

    public Employer(String location) {                                        // added Part2.M.2
        this.location = location;                                             // added Part2.M.2
    }

    public Employer() {}                                                      // added Part2.M.2

    public String getLocation() {
        return location;
    }                          // added Part2.M.1

    public void setLocation(String location) {
        this.location = location;
    }    // added Part2.M.1
}

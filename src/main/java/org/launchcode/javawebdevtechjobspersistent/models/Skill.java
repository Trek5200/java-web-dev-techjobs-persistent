package org.launchcode.javawebdevtechjobspersistent.models;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.List;

@Entity                                                                         // added Part2.M.2
public class Skill extends AbstractEntity {

    @ManyToMany(mappedBy = "skills")                                            // added Part4.S.2
    private final List<Job> jobs = new ArrayList<>();                           // added Part4.S.1

    @NotBlank(message = "Skill description is required")
    private String description;                                                 // added Part2.M.3

    public Skill(String description) {                                          // added Part2.M.2
        this.description = description;                                         // added Part2.M.2
    }

    public Skill() {}                                                           // added Part2.M.2

    public String getDescription() {                                            // added Part2.M.2
        return description;                                                     // added Part2.M.2
    }

    public void setDescription(String description) {                            // added Part2.M.2
        this.description = description;                                         // added Part2.M.2
    }

    public List<Job> getJobs() {                                                // added in debug of skills/view
        return jobs;                                                            // missed adding in Part4
    }                                                                           // either in Skill or Refactor section
}
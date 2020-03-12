package org.launchcode.javawebdevtechjobspersistent.controllers;

import org.launchcode.javawebdevtechjobspersistent.models.Employer;
import org.launchcode.javawebdevtechjobspersistent.models.Job;
import org.launchcode.javawebdevtechjobspersistent.models.Skill;
import org.launchcode.javawebdevtechjobspersistent.models.data.EmployerRepository;
import org.launchcode.javawebdevtechjobspersistent.models.data.JobRepository;
import org.launchcode.javawebdevtechjobspersistent.models.data.SkillRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

/**
 * Created by LaunchCode
 */
@Controller
public class HomeController {

    @Autowired
    private JobRepository jobRepository;

    @Autowired                                                              // added per Part3.UH.1
    private EmployerRepository employerRepository;                          // added per Part3.UH.1

    @Autowired                                                      // added then removed while working thru Part3
    private SkillRepository skillRepository;                        // due to skill compile errors; added in Part4.UH

    @RequestMapping("")
    public String index(Model model) {

        model.addAttribute("title", "My Jobs");
        return "index";
    }

    @GetMapping("add")
    public String displayAddJobForm(Model model) {
        model.addAttribute("title", "Add Job");
        model.addAttribute("employers", employerRepository.findAll()); // added Part3.UH.2. Pops drop down
        model.addAttribute("skills", skillRepository.findAll()); // added Part4. Populates check boxes
        model.addAttribute(new Job());

        return "add";
    }

    @PostMapping("add")
    public String processAddJobForm(@ModelAttribute @Valid Job newJob,
                                    Errors errors, Model model,
                                    @RequestParam int employerId,         // Part3.UH.4, but already in source code
                                    @RequestParam (required = false)List<Integer> skills) { // removed while working thru Part3;
                                                                          // added back for Part4.UH.2
        if (errors.hasErrors()) {
            model.addAttribute("title", "Add Job");
            model.addAttribute("employers", employerRepository.findAll());//added in debug;repops dropdown
            model.addAttribute("skills", skillRepository.findAll()); //added in debug; repops check boxes
            return "add";
        }
                                    // Part3.UH.5: Need to add "if form data is valid condition"
                                    // Had to find employer by employerId, verify not empty, then add to newJob
                                    // then save newJob to SQL via repository
        Optional<Employer> employerResult = employerRepository.findById(employerId);    // See 18.3.1,2
        Employer employer = employerResult.get();                                       // See 18.3.1,2
        newJob.setEmployer(employer);                                                   // See 18.3.1,2


        List<Skill> skillObs = (List<Skill>) skillRepository.findAllById(skills);       // added per Part4.UH.3
        if (skillObs.isEmpty()){                                    // added in debug; errors when no skill selected
            return "add";                                           // worked in conjunction with @NotEmpty for skills
        } else {                                                    // in Job.java
            newJob.setSkills(skillObs);                                                     // added per Part4.UH.3

            jobRepository.save(newJob);                              // added in Part3 to make newJob save to the job table
        }
//        model.addAttribute("job", newJob);                                      // added, but does not do anything
//        model.addAttribute("employer", employerId);                             // added Part3.UH.5, 1st thought does not seem to work
//        model.addAttribute("employerId", employerId);                           // added Part3.UH.5, does not work
//        model.addAttribute("employer", employerRepository.findById(employerId));  // added Part3.UH.5, does not work
//        model.addAttribute("employer", employerRepository.findAll());           // added Part3.UH.5, messed up Employer drop down
//        model.addAttribute("skill", skillRepository.findAll());                 // added and removed while working thru Part3
        return "redirect:";

    }

    @GetMapping("view/{jobId}")
    public String displayViewJob(Model model, @PathVariable int jobId) {

        Optional<Job> optJob = jobRepository.findById(jobId);   // MISSING, but needed last instruction before Part4!!!
        if (optJob.isPresent()) {                                   // Copied and modified from Employer view/{}
            Job job = optJob.get();                                 // Now working!!!
            model.addAttribute("job", job);            // MISSING, added
            return "view";                                          // MISSING, added
        } else {                                                    // MISSING, added
            return "redirect:../";                                  // MISSING, added
        }
    }


}

package vn.techmaster.jobhunt.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import vn.techmaster.jobhunt.model.Employer;
import vn.techmaster.jobhunt.model.Job;
import vn.techmaster.jobhunt.request.JobRequest;
import vn.techmaster.jobhunt.service.EmployerService;
import vn.techmaster.jobhunt.service.JobService;

@Controller
@RequestMapping("/job")
public class JobController {
    @Autowired
    private JobService jobService;
    @Autowired
    private EmployerService employerService;

    private static final String REDIRECT_JOB_LIST = "redirect:/job/list";

    @GetMapping("/list")
    public String jobList(Model model) {
        model.addAttribute("jobs", jobService.findAll());
        model.addAttribute("employerService", employerService);
        return "job_list";
    }

    @GetMapping("/detail/{id}")
    public String jobDetail(Model model, @PathVariable String id) {
        Job job = jobService.findById(id);
        Employer employer = job.getEmployer();
        model.addAttribute("job", job);
        model.addAttribute("employer", employer);
        return "job_detail";
    }

    @GetMapping("/add")
    public String addJob(Model model) {
        model.addAttribute("jobRequest", new JobRequest());
        model.addAttribute("employers", employerService.findAll());
        return "job_add";
    }

    @PostMapping("/add")
    public String submitAddJob(Model model, @Valid @ModelAttribute JobRequest jobRequest,
            BindingResult result) {

        if (result.hasErrors()) {
            model.addAttribute("employers", employerService.findAll());
            return "job_add";
        }

        Employer employer = employerService.findById(jobRequest.getEmployerId());
        Job job = new Job(employer, jobRequest.getTitle(), jobRequest.getDescription(), jobRequest.getCity());
        jobService.add(job);
        return REDIRECT_JOB_LIST;
    }

    @GetMapping("/update/{id}")
    public String updateJob(Model model, @PathVariable String id) {
        Job job = jobService.findById(id);
        JobRequest jobRequest = new JobRequest(
                job.getId(),
                job.getEmployer().getId(),
                job.getTitle(),
                job.getDescription(),
                job.getCity());
        model.addAttribute("jobRequest", jobRequest);
        model.addAttribute("employers", employerService.findAll());
        return "job_update";
    }

    @PostMapping("/update/{id}")
    public String submitUpdateJob(Model model, @PathVariable String id,
            @Valid @ModelAttribute JobRequest jobRequest,
            BindingResult result) {

        if (result.hasErrors()) {
            model.addAttribute("employers", employerService.findAll());
            return "job_update";
        }

        Employer employer = employerService.findById(jobRequest.getEmployerId());
        jobService.update(jobRequest, employer);
        return REDIRECT_JOB_LIST;
    }

    @GetMapping("/delete/{id}")
    public String deleteJob(@PathVariable String id) {
        jobService.delete(id);
        return REDIRECT_JOB_LIST;
    }
}

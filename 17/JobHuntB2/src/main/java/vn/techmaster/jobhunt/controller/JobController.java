package vn.techmaster.jobhunt.controller;

import java.time.LocalDateTime;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import vn.techmaster.jobhunt.model.Employer;
import vn.techmaster.jobhunt.model.Job;
import vn.techmaster.jobhunt.repository.JobRepositoryImpl;
import vn.techmaster.jobhunt.request.JobRequest;
import vn.techmaster.jobhunt.service.EmployerService;
import vn.techmaster.jobhunt.service.JobService;

@Controller
@RequestMapping("/job")
public class JobController {
    @Autowired
    private JobRepositoryImpl jobRepository;
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
        Job job = jobRepository.getJobById(id);
        Employer employer = job.getEmployer();
        model.addAttribute("job", job);
        model.addAttribute("employer", employer);
        return "job_detail";
    }

    @GetMapping("/add")
    public String addJob(Model model) {
        model.addAttribute("job", new Job());
        model.addAttribute("employers", employerService.findAll());
        return "job_add";
    }

    @PostMapping("/add")
    public String submitAddJob(@ModelAttribute JobRequest jobRequest) {
        String id = UUID.randomUUID().toString();
        Job job = new Job(id, jobRequest.employer(), jobRequest.title(), jobRequest.description(), jobRequest.city(),
                LocalDateTime.now(), LocalDateTime.now());
        jobRepository.createJob(job);
        return REDIRECT_JOB_LIST;
    }

    @GetMapping("/update/{id}")
    public String updateJob(Model model, @PathVariable String id) {
        Job job = jobRepository.getJobById(id);
        model.addAttribute("job", job);
        model.addAttribute("employers", employerService.findAll());
        return "job_update";
    }

    @PostMapping("/update/{id}")
    public String submitUpdateJob(@PathVariable String id, @ModelAttribute JobRequest jobRequest) {
        Job currentJob = jobRepository.getJobById(id);
        LocalDateTime created_at = currentJob.getCreated_at();
        Job job = new Job(id, jobRequest.employer(), jobRequest.title(), jobRequest.description(), jobRequest.city(),
                LocalDateTime.now(), created_at);
        jobRepository.updateJob(job);
        return REDIRECT_JOB_LIST;
    }

    @GetMapping("/delete/{id}")
    public String deleteJob(@PathVariable String id) {
        jobRepository.deleteJobById(id);
        return REDIRECT_JOB_LIST;
    }
}

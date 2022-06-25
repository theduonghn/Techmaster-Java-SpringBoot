package vn.techmaster.jobhunt.controller;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import vn.techmaster.jobhunt.model.Applicant;
import vn.techmaster.jobhunt.repository.ApplicantRepositoryImpl;
import vn.techmaster.jobhunt.repository.EmployerRepositoryImpl;
import vn.techmaster.jobhunt.repository.JobRepositoryImpl;
import vn.techmaster.jobhunt.request.ApplicantRequest;
import vn.techmaster.jobhunt.service.JobService;

@Controller
@RequestMapping("/applicant")
public class ApplicantController {
    @Autowired
    private ApplicantRepositoryImpl applicantRepository;
    @Autowired
    private JobRepositoryImpl jobRepository;
    @Autowired
    private JobService jobService;
    @Autowired
    private EmployerRepositoryImpl employerRepository;

    @GetMapping("/list")
    public String jobList(Model model) {
        model.addAttribute("applicants", applicantRepository.getApplicants());
        model.addAttribute("jobRepository", jobRepository);
        model.addAttribute("employerRepository", employerRepository);
        return "applicant_list";
    }

    @GetMapping("/add")
    public String addApplicant(Model model) {
        model.addAttribute("applicant", new Applicant());
        model.addAttribute("jobs", jobService.findAll());
        model.addAttribute("employerRepository", employerRepository);
        return "applicant_add";
    }

    @PostMapping("/add")
    public String submitAddApplicant(@ModelAttribute ApplicantRequest applicantRequest) {
        String id = UUID.randomUUID().toString();
        Applicant applicant = new Applicant(id, applicantRequest.job_id(), applicantRequest.name(),
                applicantRequest.email(), applicantRequest.phone(), applicantRequest.skills(),
                applicantRequest.applyContent());
        applicantRepository.createApplicant(applicant);
        return "applicant_add_success";
    }

    @GetMapping("/update/{id}")
    public String updateApplicant(Model model, @PathVariable String id) {
        Applicant applicant = applicantRepository.getApplicantById(id);
        model.addAttribute("applicant", applicant);
        model.addAttribute("jobs", jobService.findAll());
        model.addAttribute("employerRepository", employerRepository);
        return "applicant_update";
    }

    @PostMapping("/update/{id}")
    public String submitUpdateApplicant(@PathVariable String id, @ModelAttribute ApplicantRequest applicantRequest) {
        Applicant applicant = new Applicant(id, applicantRequest.job_id(), applicantRequest.name(),
                applicantRequest.email(), applicantRequest.phone(), applicantRequest.skills(),
                applicantRequest.applyContent());
        applicantRepository.updateApplicant(applicant);
        return "applicant_update_success";
    }

    @GetMapping("/delete/{id}")
    public String deleteApplicant(@PathVariable String id) {
        applicantRepository.deleteApplicantById(id);
        return "redirect:/applicant/list";
    }
}

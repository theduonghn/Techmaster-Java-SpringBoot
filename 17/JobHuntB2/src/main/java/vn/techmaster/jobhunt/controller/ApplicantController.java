package vn.techmaster.jobhunt.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import vn.techmaster.jobhunt.model.Applicant;
import vn.techmaster.jobhunt.model.Job;
import vn.techmaster.jobhunt.model.Skill;
import vn.techmaster.jobhunt.request.ApplicantRequest;
import vn.techmaster.jobhunt.service.ApplicantService;
import vn.techmaster.jobhunt.service.EmployerService;
import vn.techmaster.jobhunt.service.JobService;
import vn.techmaster.jobhunt.service.SkillService;

@Controller
@RequestMapping("/applicant")
public class ApplicantController {
    @Autowired
    private ApplicantService applicantService;
    @Autowired
    private JobService jobService;
    @Autowired
    private EmployerService employerService;
    @Autowired
    private SkillService skillService;

    @GetMapping("/list")
    public String jobList(Model model) {
        model.addAttribute("applicants", applicantService.findAll());
        model.addAttribute("applicantService", applicantService);
        model.addAttribute("employerService", employerService);
        return "applicant_list";
    }

    @GetMapping("/detail/{id}")
    public String applicantDetail(Model model, @PathVariable String id) {
        Applicant applicant = applicantService.findById(id);
        model.addAttribute("applicant", applicant);
        model.addAttribute("skills", applicantService.showSkills(applicant));
        return "applicant_detail";
    }

    @GetMapping("/add")
    public String addApplicant(Model model) {
        model.addAttribute("applicantRequest", new ApplicantRequest());
        model.addAttribute("jobs", jobService.findAll());
        model.addAttribute("skills", skillService.findAll());
        model.addAttribute("employerService", employerService);
        return "applicant_add";
    }

    @PostMapping("/add")
    public String submitAddApplicant(@ModelAttribute ApplicantRequest applicantRequest) {
        Job job = jobService.findById(applicantRequest.getJobId());
        List<Skill> skills = applicantRequest.getSkillIds().stream()
                .map(id -> skillService.findById(id)).toList();
        Applicant applicant = new Applicant(job, applicantRequest.getName(),
                applicantRequest.getEmail(), applicantRequest.getPhone(), skills,
                applicantRequest.getApplyContent());
        applicantService.add(applicant);
        return "applicant_add_success";
    }

    @GetMapping("/update/{id}")
    public String updateApplicant(Model model, @PathVariable String id) {
        Applicant applicant = applicantService.findById(id);
        List<String> skillIds = applicant.getSkills().stream().map(Skill::getId).toList();
        model.addAttribute("applicantRequest", new ApplicantRequest(id, applicant.getJob().getId(), applicant.getName(),
                applicant.getEmail(), applicant.getPhone(), skillIds, applicant.getApplyContent()));
        model.addAttribute("jobs", jobService.findAll());
        model.addAttribute("skills", skillService.findAll());
        return "applicant_update";
    }

    @PostMapping("/update/{id}")
    public String submitUpdateApplicant(@PathVariable String id, @ModelAttribute ApplicantRequest applicantRequest) {
        Job job = jobService.findById(applicantRequest.getJobId());
        List<Skill> skills = applicantRequest.getSkillIds().stream()
                .map(skillId -> skillService.findById(skillId)).toList();
        Applicant applicant = new Applicant(id, job, applicantRequest.getName(),
                applicantRequest.getEmail(), applicantRequest.getPhone(), skills,
                applicantRequest.getApplyContent());
        applicantService.update(applicant);
        return "applicant_update_success";
    }

    @GetMapping("/delete/{id}")
    public String deleteApplicant(@PathVariable String id) {
        applicantService.delete(id);
        return "redirect:/applicant/list";
    }
}

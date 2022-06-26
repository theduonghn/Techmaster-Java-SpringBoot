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

import vn.techmaster.jobhunt.model.Employer;
import vn.techmaster.jobhunt.request.EmployerRequest;
import vn.techmaster.jobhunt.service.EmployerService;
import vn.techmaster.jobhunt.service.FileService;

@Controller
@RequestMapping("/employer")
public class EmployerController {
    @Autowired
    private FileService fileService;
    @Autowired
    private EmployerService employerService;

    private static final String REDIRECT_EMPLOYER_LIST = "redirect:/employer/list";

    @GetMapping("/list")
    public String employerList(Model model) {
        model.addAttribute("employers", employerService.findAll());
        return "employer_list";
    }

    @GetMapping("/detail/{id}")
    public String employerDetail(Model model, @PathVariable String id) {
        Employer employer = employerService.findById(id);
        model.addAttribute("employer", employer);
        return "employer_detail";
    }

    @GetMapping("/add")
    public String addEmployer(Model model) {
        model.addAttribute("employerRequest", new EmployerRequest());
        return "employer_add";
    }

    @PostMapping("/add")
    public String submitAddEmployer(@ModelAttribute EmployerRequest employerRequest) {
        String id = UUID.randomUUID().toString();
        Employer employer = Employer.builder()
                .id(id)
                .name(employerRequest.getName())
                .website(employerRequest.getWebsite())
                .email(employerRequest.getEmail())
                .build();
        fileService.uploadEmployerLogo(employer.getId(), employerRequest.getLogo());
        employer.setLogo_path(employerService.logoPathFromLogo(employer.getId(), employerRequest.getLogo()));
        employerService.add(employer);
        return REDIRECT_EMPLOYER_LIST;
    }

    @GetMapping("/update/{id}")
    public String updateEmployer(Model model, @PathVariable String id) {
        Employer employer = employerService.findById(id);
        EmployerRequest employerRequest = new EmployerRequest(employer.getName(), employer.getWebsite(),
                employer.getEmail(), null);
        model.addAttribute("logo_path", employer.getLogo_path());
        model.addAttribute("employerRequest", employerRequest);
        return "employer_update";
    }

    @PostMapping("/update/{id}")
    public String submitUpdateEmployer(@PathVariable String id, @ModelAttribute EmployerRequest employerRequest) {
        employerService.update(id, employerRequest);
        return REDIRECT_EMPLOYER_LIST;
    }

    @GetMapping("/delete/{id}")
    public String deleteEmployer(@PathVariable String id) {
        employerService.delete(id);
        return REDIRECT_EMPLOYER_LIST;
    }
}

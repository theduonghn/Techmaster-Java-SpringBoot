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
import vn.techmaster.jobhunt.repository.EmployerRepositoryImpl;
import vn.techmaster.jobhunt.request.EmployerRequest;
import vn.techmaster.jobhunt.service.EmployerService;
import vn.techmaster.jobhunt.service.FileService;

@Controller
@RequestMapping("/employer")
public class EmployerController {
    @Autowired
    private FileService fileService;
    @Autowired
    private EmployerRepositoryImpl employerRepo;
    @Autowired
    private EmployerService employerService;

    private static final String REDIRECT_EMPLOYER_LIST = "redirect:/employer/list";

    @GetMapping("/list")
    public String employerList(Model model) {
        model.addAttribute("employers", employerService.findAll());
        return "employer_list";
    }

    @GetMapping("/add")
    public String addEmployer(Model model) {
        model.addAttribute("employerRequest", new EmployerRequest(null, null, null, null)); // TODO: better way to do
                                                                                            // this?
        return "employer_add";
    }

    @PostMapping("/add")
    public String submitAddEmployer(@ModelAttribute EmployerRequest employerRequest) {
        String id = UUID.randomUUID().toString();
        fileService.uploadEmployerLogo(id, employerRequest.logo());
        Employer employer = new Employer(id, employerRequest.name(),
                employerRepo.logoPathFromLogo(id, employerRequest.logo()),
                employerRequest.website(), employerRequest.email());
        employerRepo.createEmployer(employer);
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
        Employer currentEmployer = employerService.findById(id);
        String logo_path;
        if (!employerRequest.logo().isEmpty()) {
            fileService.uploadEmployerLogo(id, employerRequest.logo());
            logo_path = employerRepo.logoPathFromLogo(id, employerRequest.logo());
        } else {
            logo_path = currentEmployer.getLogo_path();
        }
        Employer employer = new Employer(id, employerRequest.name(),
                logo_path, employerRequest.website(), employerRequest.email());
        employerRepo.updateEmployer(employer);
        return REDIRECT_EMPLOYER_LIST;
    }

    @GetMapping("/delete/{id}")
    public String deleteEmployer(@PathVariable String id) {
        employerRepo.deleteEmployerById(id);
        return REDIRECT_EMPLOYER_LIST;
    }
}

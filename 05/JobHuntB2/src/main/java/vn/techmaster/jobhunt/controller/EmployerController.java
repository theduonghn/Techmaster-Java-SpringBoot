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
import vn.techmaster.jobhunt.repository.EmployerRepository;
import vn.techmaster.jobhunt.request.EmployerRequest;
import vn.techmaster.jobhunt.service.StorageService;

@Controller
@RequestMapping("/employer")
public class EmployerController {
    @Autowired
    private StorageService storageService;
    @Autowired
    private EmployerRepository employerRepository;

    @GetMapping("/list")
    public String employerList(Model model) {
        model.addAttribute("employers", employerRepository.getEmployers());
        return "employer_list";
    }

    @GetMapping("/add")
    public String addEmployer(Model model) {
        model.addAttribute("employerRequest", new EmployerRequest(null, null, null, null)); // TODO: better way to do
                                                                                            // this?
        return "employer_add";
    }

    @PostMapping(value = "/add")
    public String submitAddEmployer(@ModelAttribute EmployerRequest employerRequest) {
        String id = UUID.randomUUID().toString();
        storageService.uploadFile(employerRequest.logo());
        Employer employer = new Employer(id, employerRequest.name(),
                employerRepository.logoPathFromLogo(employerRequest.logo()),
                employerRequest.website(), employerRequest.email());
        employerRepository.updateEmployer(employer);
        employerRepository.createEmployer(employer);
        // TODO: able to redirect to context relative link?
        return "redirect:/employer/list";
    }

    @GetMapping("/update/{id}")
    public String updateEmployer(Model model, @PathVariable String id) {
        Employer employer = employerRepository.getEmployerById(id);
        EmployerRequest employerRequest = new EmployerRequest(employer.getName(), employer.getWebsite(),
                employer.getEmail(), employerRepository.logoFromLogoPath(employer.getLogo_path()));
        model.addAttribute("employerRequest", employerRequest);
        return "employer_update";
    }

    // TODO: khi submit ảnh và web redirect về trang employer/list, ảnh không hiện
    // ngay mà phải reload lại trang
    @PostMapping(value = "/update/{id}")
    public String submitUpdateEmployer(@PathVariable String id, @ModelAttribute EmployerRequest employerRequest) {
        storageService.uploadFile(employerRequest.logo());
        Employer employer = new Employer(id, employerRequest.name(),
                employerRepository.logoPathFromLogo(employerRequest.logo()),
                employerRequest.website(), employerRequest.email());
        employerRepository.updateEmployer(employer);
        return "redirect:/employer/list";
    }

    @GetMapping("/delete/{id}")
    public String deleteEmployer(@PathVariable String id) {
        employerRepository.deleteEmployerById(id);
        return "redirect:/employer/list";
    }
}
package vn.techmaster.jobhunt.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import vn.techmaster.jobhunt.exception.BadRequestException;
import vn.techmaster.jobhunt.model.Employer;
import vn.techmaster.jobhunt.model.Job;
import vn.techmaster.jobhunt.repository.EmployerRepository;
import vn.techmaster.jobhunt.request.EmployerRequest;

@Service
public class EmployerService {
    private String logoPath = "upload/employer_logo";

    @Autowired
    private EmployerRepository employerRepository;
    @Autowired
    private JobService jobService;
    @Autowired
    private FileService fileService;

    public List<Employer> findAll() {
        return employerRepository.findAll();
    }

    public Employer findById(String id) {
        Optional<Employer> oEmployer = employerRepository.findById(id);
        if (oEmployer.isEmpty()) {
            throw new BadRequestException("Employer with id = " + id + " is not exist");
        }

        return oEmployer.get();
    }

    public Employer add(Employer employer) {
        return employerRepository.save(employer);
    }

    public Employer update(String id, EmployerRequest employerRequest) {
        Employer employer = this.findById(id);
        String logo_path;
        if (!employerRequest.getLogo().isEmpty()) {
            fileService.uploadEmployerLogo(id, employerRequest.getLogo());
            logo_path = this.logoPathFromLogo(id, employerRequest.getLogo());
        } else {
            logo_path = employer.getLogo_path();
        }
        employer.setName(employerRequest.getName());
        employer.setWebsite(employerRequest.getWebsite());
        employer.setEmail(employerRequest.getEmail());
        employer.setLogo_path(logo_path);
        employerRepository.save(employer);
        return employer;
    }

    public void delete(String id) {
        Employer employer = this.findById(id);

        // Delete jobs of this employer
        List<Job> jobs = jobService.findByEmployer(employer);
        jobs.forEach(job -> jobService.delete(job));

        // Delete employer
        employerRepository.delete(employer);

    }

    public String logoPathFromLogo(String id, MultipartFile logo) {
        return logoPath + "/" + id + "/" + logo.getOriginalFilename();
    }
}

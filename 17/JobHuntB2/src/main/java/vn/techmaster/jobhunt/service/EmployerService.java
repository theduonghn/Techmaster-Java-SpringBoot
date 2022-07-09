package vn.techmaster.jobhunt.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import vn.techmaster.jobhunt.exception.BadRequestException;
import vn.techmaster.jobhunt.model.Employer;
import vn.techmaster.jobhunt.model.Job;
import vn.techmaster.jobhunt.repository.EmployerRepository;
import vn.techmaster.jobhunt.request.EmployerRequest;

@Service
public class EmployerService {
    private String employerLogoPath = "upload/employer_logo";

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
            throw new BadRequestException("Employer with id = " + id + " does not exist");
        }

        return oEmployer.get();
    }

    // Add instance
    public Employer add(Employer employer) {
        return employerRepository.save(employer);
    }

    // Update by employerRequest
    public Employer update(EmployerRequest employerRequest) {
        String id = employerRequest.getId();
        Employer employer = this.findById(id);
        String logoPath;
        if (!employerRequest.getLogo().isEmpty()) {
            fileService.uploadEmployerLogo(id, employerRequest.getLogo());
            logoPath = this.createLogoPath(id);
        } else {
            logoPath = employer.getLogoPath();
        }
        employer.setName(employerRequest.getName());
        employer.setWebsite(employerRequest.getWebsite());
        employer.setEmail(employerRequest.getEmail());
        employer.setLogoPath(logoPath);
        employerRepository.save(employer);
        return employer;
    }

    // Update instance
    public Employer update(Employer employer) {
        return employerRepository.save(employer);
    }

    // Delete by id
    public Employer delete(String id) {
        Employer employer = this.findById(id);

        // Delete jobs of this employer
        List<Job> jobs = jobService.findByEmployer(employer);
        jobs.forEach(job -> jobService.delete(job));

        // Delete employer
        employerRepository.delete(employer);

        return employer;
    }

    public String createLogoPath(String id) {
        return employerLogoPath + "/" + id;
    }
}

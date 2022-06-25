package vn.techmaster.jobhunt.repository;

import java.util.Collection;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;

import vn.techmaster.jobhunt.model.Employer;

@Repository
public class EmployerRepositoryImpl {
    String logoPath = "upload/employer_logo";

    @Autowired
    @Lazy
    private EmployerRepository employerRepository;

    @Autowired
    private JobRepositoryImpl jobRepo;

    ConcurrentHashMap<String, Employer> employers;

    public void createEmployer(Employer employer) {
        employers.put(employer.getId(), employer);
    }

    public void updateEmployer(Employer employer) {
        employers.put(employer.getId(), employer);
    }

    public void deleteEmployerById(String id) {
        // Remove employer
        employers.remove(id);
        // Remove job by this employer
        jobRepo.deleteJobsByEmployerId(id);
    }

    public String logoPathFromLogo(String id, MultipartFile logo) {
        return logoPath + "/" + id + "/" + logo.getOriginalFilename();
    }
}

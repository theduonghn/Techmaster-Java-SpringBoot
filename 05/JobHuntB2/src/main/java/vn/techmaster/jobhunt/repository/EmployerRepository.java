package vn.techmaster.jobhunt.repository;

import java.util.Collection;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;

import vn.techmaster.jobhunt.model.Employer;

@Repository
public class EmployerRepository {
    String logoPath = "upload/employer_logo";

    @Autowired
    private JobRepository jobRepository;

    ConcurrentHashMap<String, Employer> employers;

    public EmployerRepository() {
        employers = new ConcurrentHashMap<>();
        employers.put("e1",
                new Employer("e1", "FPT", "upload/employer_logo/e1/FPT-transparent.png", "https://fpt.com.vn/",
                        "fpt@mail.com"));
        employers.put("e2",
                new Employer("e2", "CMC", "upload/employer_logo/e2/cmc.png", "https://cmc.com.vn/",
                        "cmc@mail.com"));
        employers.put("e3",
                new Employer("e3", "Amazon", "upload/employer_logo/e3/amazon.png", "https://amazon.com/",
                        "amazon@mail.com"));
        employers.put("e4",
                new Employer("e4", "Google", "upload/employer_logo/e4/google.png", "https://google.com/",
                        "google@mail.com"));
    }

    public Collection<Employer> getEmployers() {
        return employers.values();
    }

    public Employer getEmployerById(String id) {
        return employers.get(id);
    }

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
        jobRepository.deleteJobsByEmployerId(id);
    }

    public String logoPathFromLogo(String id, MultipartFile logo) {
        return logoPath + "/" + id + "/" + logo.getOriginalFilename();
    }
}

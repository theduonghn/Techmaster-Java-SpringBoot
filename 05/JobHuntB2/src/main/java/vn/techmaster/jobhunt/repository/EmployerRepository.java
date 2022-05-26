package vn.techmaster.jobhunt.repository;

import java.util.Collection;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;

import vn.techmaster.jobhunt.model.Employer;

@Repository
public class EmployerRepository {
    String logoPath = "img/employer_logo/"; // TODO: refactor this path

    @Autowired
    private JobRepository jobRepository;

    ConcurrentHashMap<String, Employer> employers;

    public EmployerRepository() {
        employers = new ConcurrentHashMap<>();
        employers.put("e1",
                new Employer("e1", "FPT", "img/employer_logo/FPT-transparent.png", "https://fpt.com.vn/",
                        "fpt@mail.com"));
        employers.put("e2",
                new Employer("e2", "CMC", "img/employer_logo/cmc.svg", "https://cmc.com.vn/",
                        "cmc@mail.com"));
        employers.put("e3",
                new Employer("e3", "Amazon", "img/employer_logo/amazon.png", "https://amazon.com/",
                        "amazon@mail.com"));
        employers.put("e4",
                new Employer("e4", "Google", "img/employer_logo/google.png", "https://google.com/",
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

    public String logoPathFromLogo(MultipartFile logo) {
        return logoPath + logo.getOriginalFilename();
    }

    public MultipartFile logoFromLogoPath(String logo_path) {
        // TODO: display current logo
        return null;
    }
}

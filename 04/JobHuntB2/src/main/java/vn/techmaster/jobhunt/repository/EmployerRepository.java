package vn.techmaster.jobhunt.repository;

import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.stereotype.Repository;

import vn.techmaster.jobhunt.model.Employer;

@Repository
public class EmployerRepository {
    ConcurrentHashMap<String, Employer> employers;

    public EmployerRepository() {
        employers = new ConcurrentHashMap<>();
        employers.put("e1",
                new Employer("e1", "FPT Software", "img/employer_logo/FPT-transparent.png", "http://fpt-software.com/",
                        "fpt-software@gmail.com"));
        employers.put("e2",
                new Employer("e2", "VNG CORPORATION", "img/employer_logo/VNG_logo.png", "www.vng.com.vn",
                        "vng@gmail.com"));
        employers.put("e3",
                new Employer("e3", "SHIFT ASIA", "img/employer_logo/ShiftAsia-Logo.png", "http://shiftasia.com/",
                        "shift-asia@gmail.com"));
        employers.put("e4",
                new Employer("e4", "Hybrid Technologies", "img/employer_logo/hybird.webp",
                        "http://jobs.hybrid-technologies.vn",
                        "hybrid-technologies@gmail.com"));
        employers.put("e5",
                new Employer("e5", "PANASONIC VIETNAM CO., LTD", "img/employer_logo/Panasonic_logo_bl_posi_PNG.png",
                        "https://www.panasonic.com/vn/en/",
                        "panasonic@gmail.com"));
    }

    public List<Employer> getEmployers() {
        return employers.values().stream().toList();
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
        employers.remove(id);
    }
}

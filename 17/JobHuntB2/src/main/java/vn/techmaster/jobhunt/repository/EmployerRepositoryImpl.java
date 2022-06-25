package vn.techmaster.jobhunt.repository;

import java.util.concurrent.ConcurrentHashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import vn.techmaster.jobhunt.model.Employer;

@Repository
public class EmployerRepositoryImpl {
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
}

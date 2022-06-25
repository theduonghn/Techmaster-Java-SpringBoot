package vn.techmaster.jobhunt.repository;

import java.util.concurrent.ConcurrentHashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import vn.techmaster.jobhunt.model.Job;

@Repository
public class JobRepositoryImpl {
    @Autowired
    private ApplicantRepositoryImpl applicantRepository;

    ConcurrentHashMap<String, Job> jobs;

    public Job getJobById(String id) {
        return jobs.get(id);
    }

    public void createJob(Job job) {
        jobs.put(job.getId(), job);
    }

    public void updateJob(Job job) {
        jobs.put(job.getId(), job);
    }

    public void deleteJobById(String id) {
        // Remove job
        jobs.remove(id);
        // Remove applicants of this job
        applicantRepository.deleteApplicantsByJobId(id);
    }

    public void deleteJobsByEmployerId(String emp_id) {
        for (String id : jobs.keySet()) {
            Job job = getJobById(id);
            if (job.getEmployer().getId().equals(emp_id)) {
                deleteJobById(id);
            }
        }
    }
}

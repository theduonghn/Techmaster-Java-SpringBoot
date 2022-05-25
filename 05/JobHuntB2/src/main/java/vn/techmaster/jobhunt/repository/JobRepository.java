package vn.techmaster.jobhunt.repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import vn.techmaster.jobhunt.model.City;
import vn.techmaster.jobhunt.model.Job;

@Repository
public class JobRepository {
    @Autowired
    private ApplicantRepository applicantRepository;

    ConcurrentHashMap<String, Job> jobs;

    public JobRepository() {
        jobs = new ConcurrentHashMap<>();
        jobs.put("j1", new Job("j1", "e1", "Java developer", "Lập trình viên Java", City.HaNoi, LocalDateTime.now(),
                LocalDateTime.now()));
        jobs.put("j2",
                new Job("j2", "e2", "Python developer", "Lập trình viên Python", City.DaNang, LocalDateTime.now(),
                        LocalDateTime.now()));
        jobs.put("j3",
                new Job("j3", "e3", "JavaScript developer", "Lập trình viên JavaScript", City.HaiPhong,
                        LocalDateTime.now(),
                        LocalDateTime.now()));
        jobs.put("j4",
                new Job("j4", "e4", "Fullstack developer", "Lập trình viên fullstack", City.HoChiMinh,
                        LocalDateTime.now(),
                        LocalDateTime.now()));
    }

    public List<Job> getJobs() {
        return jobs.values().stream().toList();
    }

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
            if (job.getEmp_id().equals(emp_id)) {
                deleteJobById(id);
            }
        }
    }
}

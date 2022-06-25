package vn.techmaster.jobhunt.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.techmaster.jobhunt.exception.BadRequestException;
import vn.techmaster.jobhunt.model.Applicant;
import vn.techmaster.jobhunt.model.Employer;
import vn.techmaster.jobhunt.model.Job;
import vn.techmaster.jobhunt.repository.JobRepository;

@Service
public class JobService {
    @Autowired
    private JobRepository jobRepository;
    @Autowired
    private ApplicantService applicantService;

    public List<Job> findAll() {
        return jobRepository.findAll();
    }

    public Job findById(String id) {
        Optional<Job> oJob = jobRepository.findById(id);
        if (oJob.isEmpty()) {
            throw new BadRequestException("Job with id = " + id + " is not exist");
        }

        return oJob.get();
    }

    public List<Job> findByEmployer(Employer employer) {
        return jobRepository.findByEmployer(employer);
    }

    // Delete by id
    public void delete(String id) {
        Job job = this.findById(id);

        // Delete applicants of this job
        this.deleteApplicantsOf(job);

        // Delete job
        jobRepository.delete(job);
    }

    // Delete by instance
    public void delete(Job job) {
        // Delete applicants of this job
        this.deleteApplicantsOf(job);

        // Delete job
        jobRepository.delete(job);
    }

    public void deleteApplicantsOf(Job job) {
        // Delete applicants of this job
        List<Applicant> applicants = applicantService.findByJob(job);
        applicants.forEach(applicant -> applicantService.delete(applicant));
    }
}

package vn.techmaster.jobhunt.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import vn.techmaster.jobhunt.model.Job;
import vn.techmaster.jobhunt.repository.JobRepository;

@Service
public class JobService {
    @Autowired
    private JobRepository jobRepository;

    public List<Job> findAll() {
        return jobRepository.findAll();
    }

    public Job findById(String id) {
        Optional<Job> oJob = jobRepository.findById(id);
        if (oJob.isEmpty()) {
            throw new RuntimeException("Job with id = " + id + " is not exist");
        }

        return oJob.get();
    }
}

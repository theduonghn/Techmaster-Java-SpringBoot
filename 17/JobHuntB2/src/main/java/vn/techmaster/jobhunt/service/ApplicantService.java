package vn.techmaster.jobhunt.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import vn.techmaster.jobhunt.exception.BadRequestException;
import vn.techmaster.jobhunt.model.Applicant;
import vn.techmaster.jobhunt.model.Job;
import vn.techmaster.jobhunt.model.Skill;
import vn.techmaster.jobhunt.repository.ApplicantRepository;

@Service
public class ApplicantService {
    @Autowired
    private ApplicantRepository applicantRepository;

    public List<Applicant> findAll() {
        return applicantRepository.findAll();
    }

    public Applicant findById(String id) {
        Optional<Applicant> oApplicant = applicantRepository.findById(id);
        if (oApplicant.isEmpty()) {
            throw new BadRequestException("Applicant with id = " + id + " does not exist");
        }

        return oApplicant.get();
    }

    public List<Applicant> findByJob(Job job) {
        return applicantRepository.findByJob(job);
    }

    public String showSkills(Applicant applicant) {
        return String.join(", ", applicant.getSkills().stream().map(Skill::getName).toList());
    }

    public Applicant add(Applicant applicant) {
        applicantRepository.save(applicant);
        return applicant;
    }

    public Applicant update(Applicant applicant) {
        applicantRepository.save(applicant);
        return applicant;
    }

    // Delete by instance
    public void delete(Applicant applicant) {
        applicantRepository.delete(applicant);
    }

    // Delete by id
    public void delete(String id) {
        Applicant applicant = this.findById(id);
        this.delete(applicant);
    }
}

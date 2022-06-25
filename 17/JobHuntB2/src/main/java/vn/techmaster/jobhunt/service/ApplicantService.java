package vn.techmaster.jobhunt.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import vn.techmaster.jobhunt.model.Applicant;
import vn.techmaster.jobhunt.model.Skill;
import vn.techmaster.jobhunt.repository.ApplicantRepository;

@Service
public class ApplicantService {
    @Autowired
    private ApplicantRepository applicantRepository;

    public List<Applicant> findAll() {
        return applicantRepository.findAll();
    }

    public String showSkills(Applicant applicant) {
        return String.join(", ", applicant.getSkills().stream().map(Skill::getName).toList());
    }
}

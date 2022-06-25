package vn.techmaster.jobhunt.repository;

import java.util.concurrent.ConcurrentHashMap;

import org.springframework.stereotype.Repository;

import vn.techmaster.jobhunt.model.Applicant;

@Repository
public class ApplicantRepositoryImpl {
    ConcurrentHashMap<String, Applicant> applicants;

    public Applicant getApplicantById(String id) {
        return applicants.get(id);
    }

    public void createApplicant(Applicant applicant) {
        applicants.put(applicant.getId(), applicant);
    }

    public void updateApplicant(Applicant applicant) {
        applicants.put(applicant.getId(), applicant);
    }

    public void deleteApplicantById(String id) {
        applicants.remove(id);
    }

    public void deleteApplicantsByJobId(String job_id) {
        for (String id : applicants.keySet()) {
            Applicant applicant = getApplicantById(id);
            if (applicant.getJob().getId().equals(job_id)) {
                deleteApplicantById(id);
            }
        }
    }
}

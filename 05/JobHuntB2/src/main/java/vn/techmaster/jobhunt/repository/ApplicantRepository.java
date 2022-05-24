package vn.techmaster.jobhunt.repository;

import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.stereotype.Repository;

import vn.techmaster.jobhunt.model.Applicant;
import vn.techmaster.jobhunt.model.Skill;

@Repository
public class ApplicantRepository {
    ConcurrentHashMap<String, Applicant> applicants;

    public ApplicantRepository() {
        applicants = new ConcurrentHashMap<>();
        applicants.put("a1", new Applicant("a1", "j1", "Nguyễn Văn A", "nva@mail.com", "123456789",
                List.of(Skill.AWS, Skill.SQL)));
        applicants.put("a2", new Applicant("a2", "j2", "Nguyễn Văn B", "nvb@mail.com", "223456789",
                List.of(Skill.Java, Skill.CSharp)));
        applicants.put("a3", new Applicant("a3", "j3", "Nguyễn Văn C", "nvc@mail.com", "323456789",
                List.of(Skill.CSharp, Skill.SQL)));
        applicants.put("a4", new Applicant("a4", "j4", "Nguyễn Văn D", "nvd@mail.com", "423456789",
                List.of(Skill.AWS, Skill.CSharp, Skill.Java, Skill.SQL)));
    }

    public List<Applicant> getApplicants() {
        return applicants.values().stream().toList();
    }

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
}

package vn.techmaster.jobhunt.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import vn.techmaster.jobhunt.model.Applicant;
import vn.techmaster.jobhunt.model.Job;

@Repository
public interface ApplicantRepository extends JpaRepository<Applicant, String> {
    public List<Applicant> findByJob(Job job);
}

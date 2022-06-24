package vn.techmaster.jobhunt.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import vn.techmaster.jobhunt.model.Employer;

public interface EmployerRepository extends JpaRepository<Employer, String> {

}

package vn.techmaster.jobhunt;

import static org.assertj.core.api.Assertions.assertThat;

import javax.transaction.Transactional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import vn.techmaster.jobhunt.model.Employer;
import vn.techmaster.jobhunt.repository.EmployerRepository;

@SpringBootTest
@Transactional
class EmployerRepositoryTest {
    @Autowired
    private EmployerRepository employerRepository;

    @Test
    void findAll() {
        assertThat(employerRepository.findAll()).size().isEqualTo(4);
    }

    @Test
    void saveAndFindById() {
        Employer employer = new Employer(
                "Samsung",
                "upload/employer_log/samsung",
                "https://samsung.com",
                "samsung@gmail.com");
        Employer savedEmployer = employerRepository.save(employer);
        assertThat(employerRepository.findById(savedEmployer.getId())).isNotNull();
        Employer foundedEmployer = employerRepository.findById(savedEmployer.getId()).get();
        assertThat(foundedEmployer.getId()).isEqualTo(savedEmployer.getId());
        assertThat(foundedEmployer.getName()).isEqualTo(savedEmployer.getName());
        assertThat(foundedEmployer.getWebsite()).isEqualTo(savedEmployer.getWebsite());
        assertThat(foundedEmployer.getEmail()).isEqualTo(savedEmployer.getEmail());
    }

    // @Test
    // void delete() {
    // List<Employer> employers = employerRepository.findAll();
    // employers.forEach(employer -> employerRepository.delete(employer));
    // assertThat(employerRepository.findAll()).isEmpty();
    // }

}

package vn.techmaster.jobhunt;

import static org.assertj.core.api.Assertions.assertThat;

import javax.transaction.Transactional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import vn.techmaster.jobhunt.model.Employer;
import vn.techmaster.jobhunt.service.EmployerService;

@SpringBootTest
@Transactional
class EmployerServiceTest {
    @Autowired
    private EmployerService employerService;

    @Test
    void findAll() {
        assertThat(employerService.findAll()).size().isEqualTo(4);
    }

    @Test
    void findById() {
        Employer employer = new Employer(
                "Samsung",
                "upload/employer_log/samsung",
                "https://samsung.com",
                "samsung@gmail.com");
        Employer savedEmployer = employerService.add(employer);
        assertThat(employerService.findById(savedEmployer.getId())).isNotNull();
        Employer foundedEmployer = employerService.findById(savedEmployer.getId());
        assertThat(foundedEmployer.getId()).isEqualTo(savedEmployer.getId());
        assertThat(foundedEmployer.getName()).isEqualTo(savedEmployer.getName());
        assertThat(foundedEmployer.getWebsite()).isEqualTo(savedEmployer.getWebsite());
        assertThat(foundedEmployer.getEmail()).isEqualTo(savedEmployer.getEmail());
    }

    @Test
    void add() {
        Employer employer = new Employer(
                "Samsung",
                "upload/employer_log/samsung",
                "https://samsung.com",
                "samsung@gmail.com");
        employerService.add(employer);
        assertThat(employerService.findAll()).size().isEqualTo(5);
    }
}

package vn.techmaster.jobhunt;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

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
    void addAndFindById() {
        Employer employer = new Employer(
                "Samsung",
                "upload/employer_logo/samsung",
                "https://samsung.com",
                "samsung@gmail.com");
        Employer savedEmployer = employerService.add(employer);
        assertThat(employerService.findById(savedEmployer.getId())).isNotNull();
        Employer foundedEmployer = employerService.findById(savedEmployer.getId());
        assertThat(foundedEmployer.getId()).isEqualTo(savedEmployer.getId());
        assertThat(foundedEmployer.getName()).isEqualTo(savedEmployer.getName());
        assertThat(foundedEmployer.getLogoPath()).isEqualTo(savedEmployer.getLogoPath());
        assertThat(foundedEmployer.getWebsite()).isEqualTo(savedEmployer.getWebsite());
        assertThat(foundedEmployer.getEmail()).isEqualTo(savedEmployer.getEmail());
    }

    @Test
    void updateInstance() {
        Employer employer = new Employer(
                "Samsung",
                "upload/employer_logo/samsung",
                "https://samsung.com",
                "samsung@gmail.com");
        Employer savedEmployer = employerService.add(employer);
        savedEmployer.setLogoPath("upload/employer_logo/new");
        employerService.update(savedEmployer);
        Employer foundedEmployer = employerService.findById(savedEmployer.getId());
        assertThat(foundedEmployer.getLogoPath()).isEqualTo("upload/employer_logo/new");
    }

    @Test
    void deleteById() {
        List<Employer> employers = employerService.findAll();
        employers.forEach(employer -> employerService.delete(employer.getId()));
        assertThat(employerService.findAll()).isEmpty();
    }

    @Test
    void createLogoPath() {
        String id = "testId";
        String logoPath = employerService.createLogoPath(id);
        assertThat(logoPath).isEqualTo("upload/employer_logo/testId");
    }
}

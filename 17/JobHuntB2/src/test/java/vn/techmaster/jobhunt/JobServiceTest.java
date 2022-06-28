package vn.techmaster.jobhunt;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.time.LocalDateTime;

import javax.transaction.Transactional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import vn.techmaster.jobhunt.exception.BadRequestException;
import vn.techmaster.jobhunt.model.City;
import vn.techmaster.jobhunt.model.Employer;
import vn.techmaster.jobhunt.model.Job;
import vn.techmaster.jobhunt.request.JobRequest;
import vn.techmaster.jobhunt.service.EmployerService;
import vn.techmaster.jobhunt.service.JobService;

@SpringBootTest
@Transactional
class JobServiceTest {
    @Autowired
    private JobService jobService;
    @Autowired
    private EmployerService employerService;

    @Test
    void findAll() {
        assertThat(jobService.findAll()).size().isEqualTo(4);
    }

    @Test
    void findById() {
        Employer employer = new Employer(
                "Samsung",
                "upload/employer_log/samsung",
                "https://samsung.com",
                "samsung@gmail.com");
        employerService.add(employer);
        Job job = new Job(employer, "Tester", "Automation tester at least 3 years of experience", City.HaNoi,
                LocalDateTime.now(), LocalDateTime.now());
        Job addedJob = jobService.add(job);
        Job foundedJob = jobService.findById(addedJob.getId());
        assertThat(foundedJob).isNotNull();
    }

    @Test
    void add() {
        Employer employer = new Employer(
                "Samsung",
                "upload/employer_log/samsung",
                "https://samsung.com",
                "samsung@gmail.com");
        employerService.add(employer);
        Job job = new Job(employer, "Tester", "Automation tester at least 3 years of experience", City.HaNoi,
                LocalDateTime.now(), LocalDateTime.now());
        Job addedJob = jobService.add(job);
        Job foundedJob = jobService.findById(addedJob.getId());
        assertThat(foundedJob).isNotNull();
        assertThat(foundedJob.getId()).isEqualTo(addedJob.getId());
        assertThat(foundedJob.getEmployer()).isEqualTo(employer);
        assertThat(foundedJob.getTitle()).isEqualTo(job.getTitle());
        assertThat(foundedJob.getDescription()).isEqualTo(job.getDescription());
        assertThat(foundedJob.getCity()).isEqualTo(job.getCity());
        assertThat(foundedJob.getUpdated_at()).isEqualTo(job.getUpdated_at());
        assertThat(foundedJob.getCreated_at()).isEqualTo(job.getCreated_at());
    }

    @Test
    void update() {
        Employer employer = new Employer(
                "Samsung",
                "upload/employer_log/samsung",
                "https://samsung.com",
                "samsung@gmail.com");
        Employer employer1 = new Employer(
                "Samsung1",
                "upload/employer_log/samsung1",
                "https://samsung.com1",
                "samsung@gmail.com1");
        employerService.add(employer);
        employerService.add(employer1);
        Job job = new Job(employer, "Tester", "Automation tester at least 3 years of experience", City.HaNoi,
                LocalDateTime.now(), LocalDateTime.now());
        Job addedJob = jobService.add(job);
        JobRequest jobRequest = new JobRequest();

        jobRequest.setId(addedJob.getId());
        jobRequest.setEmployerId(employer1.getId());
        jobRequest.setTitle("Tester1");
        jobRequest.setDescription("Updated description");
        jobRequest.setCity(City.AllCity);

        Job updatedJob = jobService.update(jobRequest, employer1);

        assertThat(updatedJob).isNotNull();
        assertThat(updatedJob.getId()).isEqualTo(addedJob.getId());
        assertThat(updatedJob.getEmployer()).isEqualTo(employer1);
        assertThat(updatedJob.getTitle()).isEqualTo("Tester1");
        assertThat(updatedJob.getDescription()).isEqualTo("Updated description");
        assertThat(updatedJob.getCity()).isEqualTo(City.AllCity);
        assertThat(updatedJob.getCreated_at()).isEqualTo(job.getCreated_at());
    }

    @Test
    void deleteById() {
        Employer employer = new Employer(
                "Samsung",
                "upload/employer_log/samsung",
                "https://samsung.com",
                "samsung@gmail.com");
        employerService.add(employer);
        Job job = new Job(employer, "Tester", "Automation tester at least 3 years of experience", City.HaNoi,
                LocalDateTime.now(), LocalDateTime.now());
        Job addedJob = jobService.add(job);
        jobService.delete(addedJob.getId());

        assertThatThrownBy(() -> jobService.findById(addedJob.getId()))
                .isInstanceOf(BadRequestException.class)
                .hasMessage("Job with id = " + addedJob.getId() + " does not exist");
    }

    @Test
    void deleteInstance() {
        Employer employer = new Employer(
                "Samsung",
                "upload/employer_log/samsung",
                "https://samsung.com",
                "samsung@gmail.com");
        employerService.add(employer);
        Job job = new Job(employer, "Tester", "Automation tester at least 3 years of experience", City.HaNoi,
                LocalDateTime.now(), LocalDateTime.now());
        Job addedJob = jobService.add(job);
        jobService.delete(addedJob);

        assertThatThrownBy(() -> jobService.findById(addedJob.getId()))
                .isInstanceOf(BadRequestException.class)
                .hasMessage("Job with id = " + addedJob.getId() + " does not exist");
    }
}

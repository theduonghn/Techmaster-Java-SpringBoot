package vn.techmaster.jobhunt;

import java.time.LocalDateTime;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import vn.techmaster.jobhunt.model.City;
import vn.techmaster.jobhunt.model.Employer;
import vn.techmaster.jobhunt.model.Job;

@SpringBootApplication
public class JobhuntApplication implements ApplicationRunner {
	@Autowired
	private EntityManager entityManager;

	public static void main(String[] args) {
		SpringApplication.run(JobhuntApplication.class, args);
	}

	@Override
	@Transactional
	public void run(ApplicationArguments args) throws Exception {
		// Create employers
		Employer e1 = new Employer("FPT", "upload/employer_logo/e1/FPT-transparent.png", "https://fpt.com.vn/",
				"fpt@mail.com");
		entityManager.persist(e1);
		Employer e2 = new Employer("CMC", "upload/employer_logo/e2/cmc.png", "https://cmc.com.vn/",
				"cmc@mail.com");
		entityManager.persist(e2);
		Employer e3 = new Employer("Amazon", "upload/employer_logo/e3/amazon.png", "https://amazon.com/",
				"amazon@mail.com");
		entityManager.persist(e3);
		Employer e4 = new Employer("Google", "upload/employer_logo/e4/google.png", "https://google.com/",
				"google@mail.com");
		entityManager.persist(e4);

		// Create jobs
		Job j1 = new Job("j1", e1.getId(), "Java developer", "Lập trình viên Java", City.HaNoi, LocalDateTime.now(),
				LocalDateTime.now());
		entityManager.persist(j1);
		Job j2 = new Job("j2", e2.getId(), "Python developer", "Lập trình viên Python", City.DaNang,
				LocalDateTime.now(),
				LocalDateTime.now());
		entityManager.persist(j2);
		Job j3 = new Job("j3", e3.getId(), "JavaScript developer", "Lập trình viên JavaScript", City.HaiPhong,
				LocalDateTime.now(),
				LocalDateTime.now());
		entityManager.persist(j3);
		Job j4 = new Job("j4", e4.getId(), "Fullstack developer", "Lập trình viên fullstack", City.HoChiMinh,
				LocalDateTime.now(),
				LocalDateTime.now());
		entityManager.persist(j4);

		entityManager.flush();

	}

}

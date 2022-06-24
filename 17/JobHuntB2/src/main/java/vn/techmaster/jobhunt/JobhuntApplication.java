package vn.techmaster.jobhunt;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import vn.techmaster.jobhunt.model.Employer;

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
		entityManager
				.persist(new Employer("FPT", "upload/employer_logo/e1/FPT-transparent.png", "https://fpt.com.vn/",
						"fpt@mail.com"));
		entityManager.persist(new Employer("CMC", "upload/employer_logo/e2/cmc.png", "https://cmc.com.vn/",
				"cmc@mail.com"));
		entityManager.persist(new Employer("Amazon", "upload/employer_logo/e3/amazon.png", "https://amazon.com/",
				"amazon@mail.com"));
		entityManager.persist(new Employer("Google", "upload/employer_logo/e4/google.png", "https://google.com/",
				"google@mail.com"));

		entityManager.flush();

	}

}

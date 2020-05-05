package com.byeonghun.app;

import com.byeonghun.app.entity.Interests;
import com.byeonghun.app.entity.Person;
import com.byeonghun.app.entity.SocialMedia;
import com.byeonghun.app.repository.InterestsRepository;
import com.byeonghun.app.repository.PersonRepository;
import com.byeonghun.app.repository.SocialMediaRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class AppApplication implements CommandLineRunner {

	private final PersonRepository personRepository;
	private final SocialMediaRepository socialMediaRepository;
	private final InterestsRepository interestsRepository;

	public AppApplication(PersonRepository personRepository, SocialMediaRepository socialMediaRepository, InterestsRepository interestsRepository) {
		this.personRepository = personRepository;
		this.socialMediaRepository = socialMediaRepository;
		this.interestsRepository = interestsRepository;
	}

	public static void main(String[] args) {
		SpringApplication.run(AppApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		personRepository.save(new Person("김병훈","백엔드 개발자","hello@test.com","010-1234-5678"));
		socialMediaRepository.save(new SocialMedia("anonymous","","","anonymous"));
		interestsRepository.save(new Interests("곤충사육"));
		interestsRepository.save(new Interests("낚시"));
	}
}

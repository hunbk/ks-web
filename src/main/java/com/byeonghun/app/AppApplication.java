package com.byeonghun.app;

import com.byeonghun.app.entity.*;
import com.byeonghun.app.repository.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class AppApplication implements CommandLineRunner {

	private final PersonRepository personRepository;
	private final SocialMediaRepository socialMediaRepository;
	private final InterestsRepository interestsRepository;
	private final SkillRepository skillRepository;
	private final WorkRepository workRepository;


	public AppApplication(PersonRepository personRepository, SocialMediaRepository socialMediaRepository, InterestsRepository interestsRepository, SkillRepository skillRepository, WorkRepository workRepository) {
		this.personRepository = personRepository;
		this.socialMediaRepository = socialMediaRepository;
		this.interestsRepository = interestsRepository;
		this.skillRepository = skillRepository;
		this.workRepository = workRepository;
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
		skillRepository.save(new Skill("JAVA", 50));
		skillRepository.save(new Skill("HTML", 30));
		skillRepository.save(new Skill("JS", 40));
		skillRepository.save(new Skill("CSS", 20));
		workRepository.save(new Work("대학생", "경남정보대학교", "~2020.02", "개발자 공부"));
		workRepository.save(new Work("대학생", "경성대학교", "~2022.02", "백엔드 개발자 공부"));
	}
}

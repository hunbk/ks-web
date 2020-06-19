package com.byeonghun.app;

import com.byeonghun.app.domain.Lesson;
import com.byeonghun.app.domain.Student;
import com.byeonghun.app.repository.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class AppApplication {

	public static void main(String[] args) {
		SpringApplication.run(AppApplication.class, args);
	}

	@Bean
	public CommandLineRunner runner(StudentRepository studentRepository, LessonRepository lessonRepository) {
		return (args) -> {
			studentRepository.save(Student.builder().name("홍길동").email("길@동.com").build());
			lessonRepository.save(Lesson.builder().name("웹").quota(25).build());
			lessonRepository.save(Lesson.builder().name("디비").quota(40).build());
			lessonRepository.save(Lesson.builder().name("네트워크").quota(50).build());
		};
	}
}

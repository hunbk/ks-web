package com.byeonghun.app.controller;

import com.byeonghun.app.domain.Course;
import com.byeonghun.app.domain.Lesson;
import com.byeonghun.app.domain.Student;
import com.byeonghun.app.repository.CourseRepository;
import com.byeonghun.app.repository.LessonRepository;
import com.byeonghun.app.repository.StudentRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class CourseController {
    private final StudentRepository studentRepository;
    private final CourseRepository courseRepository;
    private final LessonRepository lessonRepository;

    public CourseController(StudentRepository studentRepository, CourseRepository courseRepository, LessonRepository lessonRepository) {
        this.studentRepository = studentRepository;
        this.courseRepository = courseRepository;
        this.lessonRepository = lessonRepository;
    }

    @GetMapping("/course")
    public String showCourseForm(Model model) {
        List<Student> students = studentRepository.findAll();
        List<Lesson> lessons = lessonRepository.findAll();
        model.addAttribute("students", students);
        model.addAttribute("lessons", lessons);
        return "courses/courseForm";
    }

    @PostMapping("/course")
    public String createCourse(@RequestParam("studentId") Long studentId, @RequestParam("lessonId") Long lessonId) {
        Student student = studentRepository.findById(studentId).get();
        Lesson lesson = lessonRepository.findById(lessonId).get();
        Course course = Course.createCourse(student,lesson);
        courseRepository.save(course);
        return "redirect:/courses";
    }

    // 수정 GetMapping
    @GetMapping("/courses/update/{id}")
    public String showCourseUpdateForm(@PathVariable("id") Long id, Model model) {
        Course course = courseRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid course Id:" + id));
        Lesson savedLesson = course.getLessons().get(0);
        model.addAttribute("savedLesson", savedLesson);

        List<Lesson> lessons = lessonRepository.findAll();
        course.setLessons(lessons);
        model.addAttribute("course", course);
        return "courses/courseUpdateForm";
    }

    // 수정 PostMapping
    @PostMapping("/courses/update/{id}")
    public String updateCourse(@PathVariable("id") Long id, @RequestParam("lessonId") Long lessonId) {
        Course course = courseRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid course Id:" + id));

        // 기존 참조 해제
        Lesson savedLesson = course.getLessons().get(0);
        savedLesson.setCourse(null);

        // 새로운 참조 설정
        Lesson lesson = lessonRepository.findById(lessonId).orElseThrow(() -> new IllegalArgumentException("Invalid lesson Id:" + id));
        course.addLesson(lesson);

        courseRepository.save(course);
        return "redirect:/courses";
    }

    // 삭제 GetMapping
    @GetMapping("/courses/delete/{id}")
    public String deleteCourse(@PathVariable("id") Long id) {
        Course course = courseRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid course Id:" + id));
        Lesson lesson = course.getLessons().get(0);
        lesson.setCourse(null);
        courseRepository.delete(course);
        return "redirect:/courses";
    }

    @GetMapping("/courses")
    public String courseList(Model model) {
        List<Course> courses = courseRepository.findAll();
        model.addAttribute("courses", courses);
        return "courses/courseList";
    }

}

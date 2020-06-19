package com.byeonghun.app.controller;

import com.byeonghun.app.domain.Course;
import com.byeonghun.app.domain.Lesson;
import com.byeonghun.app.domain.Student;
import com.byeonghun.app.repository.StudentRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.util.List;

@Controller
public class StudentController {

    private final StudentRepository studentRepository;

    public StudentController(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @GetMapping("/students/new")
    public String showStudentForm(Model model) {
        model.addAttribute("studentForm", new StudentForm());
        return "students/studentForm";
    }

    @PostMapping("/students/new")
    public String createStudent(@Valid StudentForm studentForm, BindingResult result) {
        if (result.hasErrors()) {
            return "students/studentForm";
        }

        Student student = new Student();
        student.setName(studentForm.getName());
        student.setEmail(studentForm.getEmail());
        studentRepository.save(student);
        return "redirect:/students";
    }

    // 수정 GetMapping
    @GetMapping("/students/update/{id}")
    public String showStudentUpdateForm(@PathVariable("id") Long id, Model model) {
        Student student = studentRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid student Id:" + id));
        model.addAttribute("student", student);
        return "students/studentUpdateForm";
    }

    // 수정 PostMapping
    @PostMapping("/students/update/{id}")
    public String updateStudent(@Valid Student student, BindingResult result) {
        if (result.hasErrors()) {
            return "students/studentUpdateForm";
        }

        studentRepository.save(student);
        return "redirect:/students";
    }

    // 삭제 GetMapping
    @GetMapping("/students/delete/{id}")
    public String deleteStudent(@PathVariable("id") Long id) {
        Student student = studentRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid student Id:" + id));
        if(student.getCourses().size() >= 1) {
            List<Course> courses = student.getCourses();
            for(Course course : courses) {
                Lesson lesson = course.getLessons().get(0);
                lesson.setCourse(null);
                course.setStudent(null);
            }
        }
        studentRepository.delete(student);
        return "redirect:/students";
    }


    @GetMapping("/students")
    public String list(Model model) {
        List<Student> students = studentRepository.findAll();
        model.addAttribute("students", students);
        return "students/studentList";
    }
}

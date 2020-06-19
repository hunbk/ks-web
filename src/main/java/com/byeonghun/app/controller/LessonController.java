package com.byeonghun.app.controller;

import com.byeonghun.app.domain.Lesson;
import com.byeonghun.app.repository.LessonRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.util.List;

@Controller
public class LessonController {

    private final LessonRepository lessonRepository;

    public LessonController(LessonRepository lessonRepository) {
        this.lessonRepository = lessonRepository;
    }

    @GetMapping(value = "/lessons/new")
    public String createForm(Model model) {
        model.addAttribute("lessonForm", new LessonForm());
        return "lessons/lessonForm";
    }

    @PostMapping(value = "/lessons/new")
    public String create(LessonForm form) {
        Lesson lesson = new Lesson();
        lesson.setName(form.getName());
        lesson.setQuota(form.getQuota());
        lessonRepository.save(lesson);
        return "redirect:/lessons";
    }

    // 수정 GetMapping
    @GetMapping(value = "/lessons/update/{id}")
    public String updateForm(@PathVariable("id") Long id, Model model) {
        Lesson lesson = lessonRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid lesson Id:" + id));
        model.addAttribute("lesson", lesson);
        return "lessons/lessonUpdateForm";
    }

    // 수정 PostMapping
    @PostMapping(value = "/lessons/update/{id}")
    public String update(@Valid Lesson lesson) {
        lessonRepository.save(lesson);
        return "redirect:/lessons";
    }

    // 삭제 GetMapping
    @GetMapping(value = "/lessons/delete/{id}")
    public String delete(@PathVariable("id") Long id) {
        Lesson lesson = lessonRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid lesson Id:" + id));
        lessonRepository.delete(lesson);
        return "redirect:/lessons";
    }

    @GetMapping(value = "/lessons")
    public String list(Model model) {
        List<Lesson> lessons = lessonRepository.findAll();
        model.addAttribute("lessons", lessons);
        return "lessons/lessonList";
    }
}

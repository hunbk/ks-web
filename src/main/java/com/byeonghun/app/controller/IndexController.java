package com.byeonghun.app.controller;

import com.byeonghun.app.entity.Interests;
import com.byeonghun.app.entity.Person;
import com.byeonghun.app.entity.Skill;
import com.byeonghun.app.entity.SocialMedia;
import com.byeonghun.app.repository.InterestsRepository;
import com.byeonghun.app.repository.PersonRepository;
import com.byeonghun.app.repository.SkillRepository;
import com.byeonghun.app.repository.SocialMediaRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class IndexController {

    private final PersonRepository personRepository;
    private final SocialMediaRepository socialMediaRepository;
    private final InterestsRepository interestsRepository;
    private final SkillRepository skillRepository;

    public IndexController(PersonRepository personRepository, SocialMediaRepository socialMediaRepository, InterestsRepository interestsRepository, SkillRepository skillRepository) {
        this.personRepository = personRepository;
        this.socialMediaRepository = socialMediaRepository;
        this.interestsRepository = interestsRepository;
        this.skillRepository = skillRepository;
    }

    @GetMapping("/")
    public String index(Model model) {
        Person person = personRepository.findAll().stream().findFirst().orElse(null); model.addAttribute("person", person);
        SocialMedia socialMedia = socialMediaRepository.findAll().stream().findFirst().orElse(null); model.addAttribute("socialMedia",socialMedia);
        List<Interests> interests = interestsRepository.findAll(); model.addAttribute("interests", interests);
        List<Skill> skills = skillRepository.findAll(); model.addAttribute("skills", skills);
        return "resume";
    }
}

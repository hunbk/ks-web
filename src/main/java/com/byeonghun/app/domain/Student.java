package com.byeonghun.app.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
public class Student {
    @Id
    @Column(name = "student_id")
    @GeneratedValue
    private Long id;

    @NotEmpty(message = "이름은 필수입니다.")
    private String name;

    @NotEmpty(message = "이메일은 필수입니다.")
    @Email(message = "이메일 형식을 확인해주세요")
    private String email;

    public Student() {
    }

    @Builder
    public Student(String name, String email) {
        this.name = name;
        this.email = email;
    }

    @OneToMany(mappedBy = "student", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Course> courses = new ArrayList<>();
}

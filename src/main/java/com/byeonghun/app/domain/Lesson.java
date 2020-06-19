package com.byeonghun.app.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
public class Lesson {
    @Id
    @Column(name = "lesson_id")
    @GeneratedValue
    private Long id;

    private String name;
    private int quota;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "course_id")
    private Course course;

    public void setCourse(Course course) {
        this.course = course;
        if(course != null) {
            this.course.getLessons().add(this);
        }
    }

    public Lesson() {
    }

    @Builder
    public Lesson(String name, int quota) {
        this.name = name;
        this.quota = quota;
    }
}

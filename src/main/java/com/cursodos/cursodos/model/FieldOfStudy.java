package com.cursodos.cursodos.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
public class FieldOfStudy {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long fieldOfStudyId;

    private String fieldOfStudyName;

    @OneToMany(mappedBy = "fieldOfStudy")
    private List<Subject> subjects;

    public FieldOfStudy(Long fieldOfStudyId, String fieldOfStudyName, List<Subject> subjects) {
        this.fieldOfStudyId = fieldOfStudyId;
        this.fieldOfStudyName = fieldOfStudyName;
        this.subjects = subjects;
    }

    public FieldOfStudy() {
    }
}

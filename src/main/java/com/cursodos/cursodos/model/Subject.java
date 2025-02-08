package com.cursodos.cursodos.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

@Getter
@Setter
@Entity
public class Subject {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long subjectId;

    private String subjectName;

    private String subjectModelity;

    private LocalDate subjectEndDate;

    @ManyToOne
    @JoinColumn(name = "field_of_study_id", nullable = false)
    private FieldOfStudy fieldOfStudy;

    @ManyToMany
    @JoinTable(
            name = "subject_issue",
            joinColumns = @JoinColumn(name = "fk_subject", nullable = false),
            inverseJoinColumns = @JoinColumn(name = "fk_issue", nullable = true)
    )
    private List<Issue> listIssus;

    public Subject() {
    }

    public Subject(Long subjectId, String subjectName, String subjectModelity, LocalDate subjectEndDate, FieldOfStudy fieldOfStudy, List<Issue> listIssus) {
        this.subjectId = subjectId;
        this.subjectName = subjectName;
        this.subjectModelity = subjectModelity;
        this.subjectEndDate = subjectEndDate;
        this.fieldOfStudy = fieldOfStudy;
        this.listIssus = listIssus;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Subject subject = (Subject) o;
        return Objects.equals(subjectName, subject.subjectName);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(subjectName);
    }
}

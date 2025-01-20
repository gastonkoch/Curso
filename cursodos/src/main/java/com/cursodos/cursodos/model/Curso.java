package com.cursodos.cursodos.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Getter @Setter
@Entity
public class Curso {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long cursoId;

    private String cursoName;

    private String cursoModelity;

    private LocalDate cursoEndDate;

    @ManyToMany
    @JoinTable(
            name = "curso_issue",
            joinColumns = @JoinColumn(name = "fk_curso", nullable = false),
            inverseJoinColumns = @JoinColumn(name = "fk_issue", nullable = true)
    )
    private List<Issue> listIssus;

    public Curso() {
    }

    public Curso(Long cursoId, String cursoName, String cursoModelity, LocalDate cursoEndDate, List<Issue> listIssus) {
        this.cursoId = cursoId;
        this.cursoName = cursoName;
        this.cursoModelity = cursoModelity;
        this.cursoEndDate = cursoEndDate;
        this.listIssus = listIssus;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Curso curso = (Curso) o;
        return Objects.equals(cursoName, curso.cursoName);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(cursoName);
    }
}

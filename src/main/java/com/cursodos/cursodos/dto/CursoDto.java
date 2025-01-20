package com.cursodos.cursodos.dto;

import com.cursodos.cursodos.model.Issue;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter @Setter
public class CursoDto {
    private Long cursoId;
    private String cursoName;
    private String cursoModelity;
    private LocalDate cursoEndDate;
    private List<Long> listIssus;
    private List<IssueDto> listIssusEntity;


    public CursoDto(Long cursoId, String cursoName, String cursoModelity, LocalDate cursoEndDate, List<Long> listIssus) {
        this.cursoId = cursoId;
        this.cursoName = cursoName;
        this.cursoModelity = cursoModelity;
        this.cursoEndDate = cursoEndDate;
        this.listIssus = listIssus;
    }

    public CursoDto() {
    }
}

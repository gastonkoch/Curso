package com.cursodos.cursodos.dto;

import com.cursodos.cursodos.model.FieldOfStudy;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter @Setter
public class SubjectDto {
    private Long subjectId;
    private String subjectName;
    private String subjectModelity;
    private LocalDate subjectEndDate;
    private Long fieldOfStudy;
    private List<Long> listIssus;
    private List<IssueDto> listIssusEntity;



    public SubjectDto(Long subjectId, String subjectName, String subjectModelity, LocalDate subjectEndDate, List<Long> listIssus, List<IssueDto> listIssusEntity, Long fieldOfStudy) {
        this.subjectId = subjectId;
        this.subjectName = subjectName;
        this.subjectModelity = subjectModelity;
        this.subjectEndDate = subjectEndDate;
        this.listIssus = listIssus;
        this.listIssusEntity = listIssusEntity;
        this.fieldOfStudy = fieldOfStudy;
    }

    public SubjectDto() {
    }
}

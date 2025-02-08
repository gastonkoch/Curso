package com.cursodos.cursodos.dto;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
public class GetSubjectByFieldOfStudyDto {
    private Long subjectId;
    private String subjectName;
    private String subjectModelity;
    private LocalDate subjectEndDate;
    private List<IssueDto> listIssusEntity;


    public GetSubjectByFieldOfStudyDto(Long subjectId, String subjectName, String subjectModelity, LocalDate subjectEndDate, List<IssueDto> listIssusEntity) {
        this.subjectId = subjectId;
        this.subjectName = subjectName;
        this.subjectModelity = subjectModelity;
        this.subjectEndDate = subjectEndDate;
        this.listIssusEntity = listIssusEntity;
    }

    public GetSubjectByFieldOfStudyDto() {
    }
}

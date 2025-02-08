package com.cursodos.cursodos.dto;
import com.cursodos.cursodos.model.Subject;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class FieldOfStudyDto {

    private Long fieldOfStudyId;

    private String fieldOfStudyName;

    private List<GetSubjectByFieldOfStudyDto> subjects;

    public FieldOfStudyDto(Long fieldOfStudyId, String fieldOfStudyName, List<GetSubjectByFieldOfStudyDto> subjects) {
        this.fieldOfStudyId = fieldOfStudyId;
        this.fieldOfStudyName = fieldOfStudyName;
        this.subjects = subjects;
    }

    public FieldOfStudyDto() {
    }
}

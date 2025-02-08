package com.cursodos.cursodos.service;

import com.cursodos.cursodos.dto.FieldOfStudyDto;
import com.cursodos.cursodos.dto.IssueDto;
import com.cursodos.cursodos.repository.IFieldOfStudyRepository;
import org.springframework.stereotype.Service;

import java.util.List;


public interface IFieldOfStudyService {
    public List<FieldOfStudyDto> getFieldOfStudys();
    public FieldOfStudyDto getById(Long fieldOfStudyId);
    public void createFieldOfStudy(FieldOfStudyDto fieldOfStudyDto);
    public void updateFieldOfStudy(FieldOfStudyDto fieldOfStudyDto);
    public void deleteFieldOfStudy(Long fieldOfStudyId);
}

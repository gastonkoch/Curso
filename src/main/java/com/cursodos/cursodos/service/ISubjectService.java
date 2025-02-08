package com.cursodos.cursodos.service;

import com.cursodos.cursodos.dto.SubjectDto;

import java.util.List;

public interface ISubjectService {
    public List<SubjectDto> getSubjects();
    public SubjectDto getById(Long subjectd);
    public void createSubject(SubjectDto subjectDto);
    public void updateSubject(SubjectDto subjectDto);
    public void deleteSubject(Long subjectId);
    public List<SubjectDto> getSubjectByName(String subjectName);
}

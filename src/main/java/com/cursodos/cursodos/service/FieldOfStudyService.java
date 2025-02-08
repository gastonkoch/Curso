package com.cursodos.cursodos.service;

import com.cursodos.cursodos.dto.FieldOfStudyDto;
import com.cursodos.cursodos.dto.GetSubjectByFieldOfStudyDto;
import com.cursodos.cursodos.dto.IssueDto;
import com.cursodos.cursodos.dto.SubjectDto;
import com.cursodos.cursodos.model.FieldOfStudy;
import com.cursodos.cursodos.model.Issue;
import com.cursodos.cursodos.model.Subject;
import com.cursodos.cursodos.repository.IFieldOfStudyRepository;
import com.cursodos.cursodos.repository.IIssueRepository;
import com.cursodos.cursodos.repository.ISubjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class FieldOfStudyService implements IFieldOfStudyService{

    @Autowired
    private IFieldOfStudyRepository fieldOfStudyRepository;

    @Autowired
    private ISubjectRepository subjectRepository;

    @Autowired
    private IIssueRepository issueRepository;

    public List<FieldOfStudyDto> getFieldOfStudys() {
        List<FieldOfStudy> listFieldOfStudys = fieldOfStudyRepository.findAll();
        List<FieldOfStudyDto> listFieldOfStudysDto = new ArrayList<>();

        for(FieldOfStudy fieldOfStudy:listFieldOfStudys) {
            FieldOfStudyDto fieldOfStudysDto = new FieldOfStudyDto();
            fieldOfStudysDto.setFieldOfStudyId(fieldOfStudy.getFieldOfStudyId());
            fieldOfStudysDto.setFieldOfStudyName(fieldOfStudy.getFieldOfStudyName());

            List<Subject> listSubject = subjectRepository.findSubjectsByFieldOfStudyId(fieldOfStudy.getFieldOfStudyId());

            List<GetSubjectByFieldOfStudyDto> listSubjectDto = new ArrayList<>();

            for(Subject itemSubject : listSubject){
                GetSubjectByFieldOfStudyDto subjectDto = new GetSubjectByFieldOfStudyDto();
                subjectDto.setSubjectId(itemSubject.getSubjectId());
                subjectDto.setSubjectName(itemSubject.getSubjectName());
                subjectDto.setSubjectEndDate(itemSubject.getSubjectEndDate());
                subjectDto.setSubjectModelity(itemSubject.getSubjectModelity());

                List<IssueDto> listIssueDto = new ArrayList<>();

                for(Issue issue : itemSubject.getListIssus()){
                    IssueDto issueDto = new IssueDto();
                    issueDto.setIssueId(issue.getIssueId());
                    issueDto.setIssueName(issue.getIssueName());
                    issueDto.setIssueDescription(issue.getIssueDescription());
                    listIssueDto.add(issueDto);
                }

                subjectDto.setListIssusEntity(listIssueDto);


                listSubjectDto.add(subjectDto);
            }

            fieldOfStudysDto.setSubjects(listSubjectDto);

            listFieldOfStudysDto.add(fieldOfStudysDto);
        }

        return listFieldOfStudysDto;
    }

    public FieldOfStudyDto getById(Long fieldOfStudyId) {
        FieldOfStudy fieldOfStudy = fieldOfStudyRepository.findById(fieldOfStudyId)
                .orElseThrow(() -> new RuntimeException("La carrera no existe"));

        FieldOfStudyDto fieldOfStudyDto = new FieldOfStudyDto();
        fieldOfStudyDto.setFieldOfStudyId(fieldOfStudy.getFieldOfStudyId());
        fieldOfStudyDto.setFieldOfStudyName(fieldOfStudy.getFieldOfStudyName());

        return fieldOfStudyDto;
    }


    public void createFieldOfStudy(FieldOfStudyDto fieldOfStudyDto) {
        FieldOfStudy fieldOfStudy = new FieldOfStudy();
        fieldOfStudy.setFieldOfStudyName(fieldOfStudyDto.getFieldOfStudyName());
//        fieldOfStudy.setSubjects(fieldOfStudyDto.getSubjects());

        fieldOfStudyRepository.save(fieldOfStudy);
    }

    public void updateFieldOfStudy(FieldOfStudyDto fieldOfStudyDto) {

    }

    public void deleteFieldOfStudy(Long fieldOfStudyId) {

    }


}

package com.cursodos.cursodos.service;

import com.cursodos.cursodos.dto.SubjectDto;
import com.cursodos.cursodos.dto.IssueDto;
import com.cursodos.cursodos.model.FieldOfStudy;
import com.cursodos.cursodos.model.Subject;
import com.cursodos.cursodos.model.Issue;
import com.cursodos.cursodos.repository.IFieldOfStudyRepository;
import com.cursodos.cursodos.repository.ISubjectRepository;
import com.cursodos.cursodos.repository.IIssueRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SubjectService implements ISubjectService {

    @Autowired
    private ISubjectRepository subjectRepository;

    @Autowired
    private IIssueRepository issueRepository;

    @Autowired
    private IFieldOfStudyRepository fieldOfStudyRepository;

//    public List<IssueDto> getIssueBysubjectId(Long subjectId) {
//        return null;
//    }

    public List<SubjectDto> getSubjectByName(String subjectName) {
//        List<Subject> listSubjectsEntity = subjectRepository.findBySubjectNameContaining(subjectName);
        List<Subject> listSubjectsEntity = new ArrayList<>();
        List<SubjectDto> listSubjectsDto = new ArrayList<>();

        for(Subject subject:listSubjectsEntity){
            SubjectDto subjectDto = new SubjectDto();
            subjectDto.setSubjectId(subject.getSubjectId());
            subjectDto.setSubjectName(subject.getSubjectName());
            subjectDto.setSubjectModelity(subject.getSubjectModelity());
            subjectDto.setSubjectEndDate(subject.getSubjectEndDate());

            List<IssueDto> listIssueDto = new ArrayList<>();

            for(Issue issue: subject.getListIssus()){
                IssueDto issueDto = new IssueDto();
                issueDto.setIssueId(issue.getIssueId());
                issueDto.setIssueName(issue.getIssueName());
                issueDto.setIssueDescription(issue.getIssueDescription());
                listIssueDto.add(issueDto);
            }
            subjectDto.setListIssusEntity(listIssueDto);

            listSubjectsDto.add(subjectDto);
        }

        return listSubjectsDto;
    }

    public List<SubjectDto> getSubjects() {
        List<Subject> listSubject = subjectRepository.findAll();
        List<SubjectDto> listSubjectDto = new ArrayList<>();

        for (Subject subject: listSubject) {
            SubjectDto subjectDto = new SubjectDto();
            subjectDto.setSubjectId(subject.getSubjectId());
            subjectDto.setSubjectModelity(subject.getSubjectModelity());
            subjectDto.setSubjectEndDate(subject.getSubjectEndDate());
            subjectDto.setSubjectName(subject.getSubjectName());
            subjectDto.setListIssus(new ArrayList<>());
            subjectDto.setFieldOfStudy(subject.getFieldOfStudy().getFieldOfStudyId());

            List<IssueDto> listIssueDto = new ArrayList<>();

            for(Issue issue: subject.getListIssus()){
                IssueDto issueDto = new IssueDto();
                issueDto.setIssueId(issue.getIssueId());
                issueDto.setIssueDescription(issue.getIssueDescription());
                issueDto.setIssueName(issue.getIssueName());
                listIssueDto.add(issueDto);
            }

            subjectDto.setListIssusEntity(listIssueDto);

            listSubjectDto.add(subjectDto);
        }


        return listSubjectDto;
    }

    public SubjectDto getById(Long subjectId) {
        Subject subject = subjectRepository.findById(subjectId).orElseThrow(() -> new RuntimeException("Issue not found with id: " + subjectId));
        SubjectDto subjectDto = new SubjectDto();
        subjectDto.setSubjectId(subject.getSubjectId());
        subjectDto.setSubjectModelity(subject.getSubjectModelity());
        subjectDto.setSubjectEndDate(subject.getSubjectEndDate());
        subjectDto.setSubjectName(subject.getSubjectName());
        subjectDto.setListIssus(new ArrayList<>());

        List<IssueDto> listIssueDto = new ArrayList<>();

        for(Issue issue: subject.getListIssus()){
            IssueDto issueDto = new IssueDto();
            issueDto.setIssueId(issue.getIssueId());
            issueDto.setIssueDescription(issue.getIssueDescription());
            issueDto.setIssueName(issue.getIssueName());
            listIssueDto.add(issueDto);
        }

        subjectDto.setListIssusEntity(listIssueDto);

        return subjectDto;
    }


    public void createSubject(SubjectDto subjectDto) {
        // Validar que los valores requeridos no sean nulos
        if (subjectDto.getSubjectName() == null || subjectDto.getSubjectName().isBlank()) {
            throw new IllegalArgumentException("El nombre del materia no puede ser nulo o vacío.");
        }
        if (subjectDto.getSubjectEndDate() == null) {
            throw new IllegalArgumentException("La fecha de finalización del subject no puede ser nula.");
        }
        if (subjectDto.getSubjectModelity() == null || subjectDto.getSubjectModelity().isBlank()) {
            throw new IllegalArgumentException("La modalidad del materia no puede ser nula o vacía.");
        }

        if (subjectDto.getFieldOfStudy() == null ) {
            throw new IllegalArgumentException("La modalidad de la materia no puede ser nula o vacía.");
        }

        // Crear y guardar el subject
        Subject subject = new Subject();
        subject.setSubjectName(subjectDto.getSubjectName());
        subject.setSubjectEndDate(subjectDto.getSubjectEndDate());
        subject.setSubjectModelity(subjectDto.getSubjectModelity());

        FieldOfStudy fieldOfStudy = fieldOfStudyRepository.findById(subjectDto.getFieldOfStudy())
                .orElseThrow(() -> new RuntimeException("La carrera no existe"));

        subject.setFieldOfStudy(fieldOfStudy);
        subject = subjectRepository.save(subject);

        // Recuperar las issues existentes
        List<Issue> listIssues = new ArrayList<>();

        if(subjectDto.getListIssus() != null){
            for (Long issueId : subjectDto.getListIssus()) {
                Issue issue = issueRepository.findById(issueId)
                        .orElseThrow(() -> new IllegalArgumentException("Issue no encontrada con ID: " + issueId));
                listIssues.add(issue);
            }
            subject.setListIssus(listIssues);
        }else{
            subject.setListIssus(null);
        }
        subjectRepository.save(subject);
    }

    public void updateSubject(SubjectDto subjectDto) {
        // Validar que los valores requeridos no sean nulos o vacíos
        if (subjectDto.getSubjectId() == null || subjectDto.getSubjectId() <= 0L) {
            throw new IllegalArgumentException("El ID de la materia no puede ser nulo ni vacío y tampoco puede ser 0 o negativo.");
        }


        if (subjectDto.getSubjectName() == null || subjectDto.getSubjectName().isBlank()) {
            throw new IllegalArgumentException("El nombre de la materia no puede ser nulo o vacío.");
        }
        if (subjectDto.getSubjectModelity() == null || subjectDto.getSubjectModelity().isBlank()) {
            throw new IllegalArgumentException("La modalidad de la materia no puede ser nula o vacía.");
        }
        if (subjectDto.getSubjectEndDate() == null) {
            throw new IllegalArgumentException("La fecha de finalización del subject no puede ser nula.");
        }

        // Recuperar el subject existente
        Subject subject = subjectRepository.findById(subjectDto.getSubjectId())
                .orElseThrow(() -> new RuntimeException("subject no encontrado con ID: " + subjectDto.getSubjectId()));

        // Actualizar los valores del subject
        subject.setSubjectName(subjectDto.getSubjectName());
        subject.setSubjectModelity(subjectDto.getSubjectModelity());
        subject.setSubjectEndDate(subjectDto.getSubjectEndDate());

        FieldOfStudy fieldOfStudy = fieldOfStudyRepository.findById(subjectDto.getFieldOfStudy())
                .orElseThrow(() -> new RuntimeException("La carrera no existe"));

        subject.setFieldOfStudy(fieldOfStudy);

        // Actualizar la lista de issues si se proporciona
        List<Issue> listIssues = new ArrayList<>();
        if (subjectDto.getListIssus() != null) {
            for (Long issueId : subjectDto.getListIssus()) {
                Issue issue = issueRepository.findById(issueId)
                        .orElseThrow(() -> new IllegalArgumentException("Issue no encontrada con ID: " + issueId));
                listIssues.add(issue);
            }
        }
        subject.setListIssus(listIssues);

        // Guardar los cambios
        subjectRepository.save(subject);
    }


    public void deleteSubject(Long subjectId) {
        subjectRepository.deleteById(subjectId);
    }
}

package com.cursodos.cursodos.service;

import com.cursodos.cursodos.dto.CursoDto;
import com.cursodos.cursodos.dto.IssueDto;
import com.cursodos.cursodos.model.Curso;
import com.cursodos.cursodos.model.Issue;
import com.cursodos.cursodos.repository.ICursoRepository;
import com.cursodos.cursodos.repository.IIssueRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CursoService implements ICursoService {

    @Autowired
    private ICursoRepository cursoRepository;

    @Autowired
    private IIssueRepository issueRepository;

    public List<CursoDto> getCursos() {
        List<Curso> listCurso = cursoRepository.findAll();
        List<CursoDto> listCursoDto = new ArrayList<>();

        for (Curso curso: listCurso) {
            CursoDto cursoDto = new CursoDto();
            cursoDto.setCursoId(curso.getCursoId());
            cursoDto.setCursoModelity(curso.getCursoModelity());
            cursoDto.setCursoEndDate(curso.getCursoEndDate());
            cursoDto.setCursoName(curso.getCursoName());
            cursoDto.setListIssus(new ArrayList<>());

            List<IssueDto> listIssueDto = new ArrayList<>();

            for(Issue issue: curso.getListIssus()){
                IssueDto issueDto = new IssueDto();
                issueDto.setIssueId(issue.getIssueId());
                issueDto.setIssueDescription(issue.getIssueDescription());
                issueDto.setIssueName(issue.getIssueName());
                listIssueDto.add(issueDto);
            }

            cursoDto.setListIssusEntity(listIssueDto);

            listCursoDto.add(cursoDto);
        }


        return listCursoDto;
    }

    public CursoDto getById(Long cursoId) {
        Curso curso = cursoRepository.findById(cursoId).orElseThrow(() -> new RuntimeException("Issue not found with id: " + cursoId));
        CursoDto cursoDto = new CursoDto();
        cursoDto.setCursoId(curso.getCursoId());
        cursoDto.setCursoModelity(curso.getCursoModelity());
        cursoDto.setCursoEndDate(curso.getCursoEndDate());
        cursoDto.setCursoName(curso.getCursoName());
        cursoDto.setListIssus(new ArrayList<>());

        List<IssueDto> listIssueDto = new ArrayList<>();

        for(Issue issue: curso.getListIssus()){
            IssueDto issueDto = new IssueDto();
            issueDto.setIssueId(issue.getIssueId());
            issueDto.setIssueDescription(issue.getIssueDescription());
            issueDto.setIssueName(issue.getIssueName());
            listIssueDto.add(issueDto);
        }

        cursoDto.setListIssusEntity(listIssueDto);

        return cursoDto;
    }


    public void createCurso(CursoDto cursoDto) {
        // Validar que los valores requeridos no sean nulos
        if (cursoDto.getCursoName() == null || cursoDto.getCursoName().isBlank()) {
            throw new IllegalArgumentException("El nombre del curso no puede ser nulo o vacío.");
        }
        if (cursoDto.getCursoEndDate() == null) {
            throw new IllegalArgumentException("La fecha de finalización del curso no puede ser nula.");
        }
        if (cursoDto.getCursoModelity() == null || cursoDto.getCursoModelity().isBlank()) {
            throw new IllegalArgumentException("La modalidad del curso no puede ser nula o vacía.");
        }

        // Crear y guardar el curso
        Curso curso = new Curso();
        curso.setCursoName(cursoDto.getCursoName());
        curso.setCursoEndDate(cursoDto.getCursoEndDate());
        curso.setCursoModelity(cursoDto.getCursoModelity());
        curso = cursoRepository.save(curso);

        // Recuperar las issues existentes
        List<Issue> listIssues = new ArrayList<>();

        if(cursoDto.getListIssus() != null){
            for (Long issueId : cursoDto.getListIssus()) {
                Issue issue = issueRepository.findById(issueId)
                        .orElseThrow(() -> new IllegalArgumentException("Issue no encontrada con ID: " + issueId));
                listIssues.add(issue);
            }
            curso.setListIssus(listIssues);
        }else{
            curso.setListIssus(null);
        }
        cursoRepository.save(curso);
    }




    public void updateCurso(CursoDto cursoDto) {
        Curso curso = cursoRepository.findById(cursoDto.getCursoId()).orElseThrow(() -> new RuntimeException("Issue not found with id: " + cursoDto.getCursoId()));

        curso.setCursoName(cursoDto.getCursoName());
        curso.setCursoModelity(cursoDto.getCursoModelity());
        curso.setCursoEndDate(cursoDto.getCursoEndDate());

        List<Issue> listIssues = new ArrayList<>();

        for(Long issueId: cursoDto.getListIssus()){
            Issue issue = issueRepository.findById(issueId)
                    .orElseThrow(() -> new IllegalArgumentException("Issue no encontrada con ID: " + issueId));
            listIssues.add(issue);
        }

        curso.setListIssus(listIssues);

        cursoRepository.save(curso);
    }

    public void deleteCurso(Long cursoId) {
        cursoRepository.deleteById(cursoId);
    }
}

package com.cursodos.cursodos.service;

import com.cursodos.cursodos.dto.CursoDto;
import com.cursodos.cursodos.dto.IssueDto;

import java.util.List;

public interface ICursoService {
    public List<CursoDto> getCursos();
    public CursoDto getById(Long cursoId);
//    public List<IssueDto> getIssueByCursoId(Long cursoId);
    public void createCurso(CursoDto cursoDto);
    public void updateCurso(CursoDto cursoDto);
    public void deleteCurso(Long cursoId);
    public List<CursoDto> getCursoByName(String cursoName);
}

package com.cursodos.cursodos.service;

import com.cursodos.cursodos.dto.CursoDto;

import java.util.List;

public interface ICursoService {
    public List<CursoDto> getCursos();
    public CursoDto getById(Long cursoId);
    public void createCurso(CursoDto cursoDto);
    public void updateCurso(CursoDto cursoDto);
    public void deleteCurso(Long cursoId);
}

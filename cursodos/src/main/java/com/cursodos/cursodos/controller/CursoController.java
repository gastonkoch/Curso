package com.cursodos.cursodos.controller;

import com.cursodos.cursodos.dto.CursoDto;
import com.cursodos.cursodos.service.ICursoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CursoController{

    @Autowired
    private ICursoService cursoService;

    @GetMapping("/curso/getall")
    public ResponseEntity<List<CursoDto>> getCursos() {
        try {
            List<CursoDto> cursos = cursoService.getCursos();
            if (cursos.isEmpty()) {
                return ResponseEntity.noContent().build();
            }
            return ResponseEntity.ok(cursos);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(null);
        }
    }

    @GetMapping("/curso/getbyid/{cursoId}")
    public ResponseEntity<CursoDto> getById(@PathVariable Long cursoId) {
        try {
            CursoDto curso = cursoService.getById(cursoId);
            if (curso == null) {
                return ResponseEntity.notFound().build();
            }
            return ResponseEntity.ok(curso);
        } catch (Exception e) {
            // Manejo de excepciones genéricas
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(null);
        }
    }



    @PostMapping("/curso/create")
    public ResponseEntity<String> createCurso(@RequestBody CursoDto cursoDto) {
        try {
            cursoService.createCurso(cursoDto);
            return ResponseEntity.status(HttpStatus.CREATED).body("Curso creado con éxito");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al crear el curso: " + e.getMessage());
        }
    }

    @PutMapping("/curso/update")
    public ResponseEntity<String> updateCurso(@RequestBody CursoDto cursoDto) {
        try {
            cursoService.updateCurso(cursoDto);
            return ResponseEntity.status(HttpStatus.OK).body("Se modifico el curso con exito");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al modificar el curso " + e.getMessage());
        }
    }

    @DeleteMapping("/curso/delte")
    public ResponseEntity<String> deleteCurso(@PathVariable Long cursoId) {
        try{
            cursoService.deleteCurso(cursoId);
            return ResponseEntity.status(HttpStatus.OK).body("Se elimino el curso con exito");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al eliminar el curso " + e.getMessage());
        }
    }
}

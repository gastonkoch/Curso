package com.cursodos.cursodos.controller;

import com.cursodos.cursodos.dto.SubjectDto;
import com.cursodos.cursodos.service.ISubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class SubjectController {

    @Autowired
    private ISubjectService subjectService;

    @GetMapping("/subject/getsubjectbyname/{subjectName}")
    public ResponseEntity<List<SubjectDto>> getSubjectByName(@PathVariable String subjectName) {
        List<SubjectDto> listSubjectDto = subjectService.getSubjectByName(subjectName);

        return ResponseEntity.ok(listSubjectDto);
    }

    @GetMapping("/subject/getall")
    public ResponseEntity<List<SubjectDto>> getSubjects() {
        try {
            List<SubjectDto> subjects = subjectService.getSubjects();
            if (subjects.isEmpty()) {
                return ResponseEntity.noContent().build();
            }
            return ResponseEntity.ok(subjects);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(null);
        }
    }

    @GetMapping("/subject/getbyid/{subjectId}")
    public ResponseEntity<SubjectDto> getById(@PathVariable Long subjectId) {
        try {
            SubjectDto subject = subjectService.getById(subjectId);
            if (subject == null) {
                return ResponseEntity.notFound().build();
            }
            return ResponseEntity.ok(subject);
        } catch (Exception e) {
            // Manejo de excepciones genéricas
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(null);
        }
    }

    @PostMapping("/subject/create")
    public ResponseEntity<String> createSubject(@RequestBody SubjectDto subjectDto) {
        try {
            subjectService.createSubject(subjectDto);
            return ResponseEntity.status(HttpStatus.CREATED).body("La materia fue creado con éxito");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al crear la materia: " + e.getMessage());
        }
    }

    @PutMapping("/subject/update")
    public ResponseEntity<String> updateSubject(@RequestBody SubjectDto subjectDto) {
        try {
            subjectService.updateSubject(subjectDto);
            return ResponseEntity.status(HttpStatus.OK).body("Se modifico la materia con exito");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al modificar la materia " + e.getMessage());
        }
    }

    @DeleteMapping("/subject/delete")
    public ResponseEntity<String> deleteSubject(@PathVariable Long subjectId) {
        try{
            subjectService.deleteSubject(subjectId);
            return ResponseEntity.status(HttpStatus.OK).body("Se elimino la materia con exito");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al eliminar la materia " + e.getMessage());
        }
    }
}

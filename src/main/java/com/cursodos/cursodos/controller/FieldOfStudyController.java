package com.cursodos.cursodos.controller;

import com.cursodos.cursodos.dto.FieldOfStudyDto;
import com.cursodos.cursodos.dto.SubjectDto;
import com.cursodos.cursodos.service.IFieldOfStudyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class FieldOfStudyController {

    @Autowired
    private IFieldOfStudyService fieldOfStudyService;

    @GetMapping("/fieldofstudy/getall")
    public ResponseEntity<List<FieldOfStudyDto>> getSubjects() {
        try {
            List<FieldOfStudyDto> fieldOfStudyServices = fieldOfStudyService.getFieldOfStudys();
            if (fieldOfStudyServices.isEmpty()) {
                return ResponseEntity.noContent().build();
            }
            return ResponseEntity.ok(fieldOfStudyServices);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(null);
        }
    }

    @GetMapping("/fieldofstudy/getbyid/{fieldofstudyid}")
    public ResponseEntity<FieldOfStudyDto> getById(@PathVariable Long fieldofstudyid) {
        try {
            FieldOfStudyDto fieldOfStudy = fieldOfStudyService.getById(fieldofstudyid);
            if (fieldOfStudy == null) {
                return ResponseEntity.notFound().build();
            }
            return ResponseEntity.ok(fieldOfStudy);
        } catch (Exception e) {
            // Manejo de excepciones genéricas
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(null);
        }
    }

    @PostMapping("/fieldofstudy/create")
    public ResponseEntity<String> createSubject(@RequestBody FieldOfStudyDto fieldOfStudyDto) {
        try {
            fieldOfStudyService.createFieldOfStudy(fieldOfStudyDto);
            return ResponseEntity.status(HttpStatus.CREATED).body("La materia fue creado con éxito");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al crear la materia: " + e.getMessage());
        }
    }
}

package com.cursodos.cursodos.controller;

import com.cursodos.cursodos.dto.IssueDto;
import com.cursodos.cursodos.service.IIssueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class IssueController{

    @Autowired
    private IIssueService issueService;

    @GetMapping("/issue/getissues")
    @ResponseBody
    public ResponseEntity<List<IssueDto>> getIssues() {
        try {
            List<IssueDto> issues = issueService.getIssues();
            if (issues.isEmpty()) {
                return ResponseEntity.noContent().build();
            }
            return ResponseEntity.ok(issues);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(null);
        }
    }

    @GetMapping("/issue/getbyid/{issueId}")
    @ResponseBody
    public ResponseEntity<IssueDto> getById(@PathVariable Long issueId) {
        try{
            IssueDto issue = issueService.getById(issueId);
            if (issue==null) {
                return ResponseEntity.noContent().build();
            }
            return ResponseEntity.ok(issue);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(null);
        }
    }

    @PostMapping("/issue/createissue")
    public ResponseEntity<String> createIssue(@RequestBody IssueDto issueDto) {
        try {
            issueService.createIssue(issueDto);
            return ResponseEntity.status(HttpStatus.CREATED).body("Issue creado con exito");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al crear el tema" + e.getMessage());
        }

    }

    @PutMapping("/issue/updateissue")
    public void updateIssue(@RequestBody IssueDto issueDto) {
        issueService.updateIssue(issueDto);
    }

    @DeleteMapping("/issue/deleteissue/{issueId}")
    public void deleteIssue(@PathVariable Long issueId) {
        issueService.deleteIssue(issueId);
    }
}

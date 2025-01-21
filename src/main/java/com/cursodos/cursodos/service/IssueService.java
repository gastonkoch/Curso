package com.cursodos.cursodos.service;

import com.cursodos.cursodos.dto.IssueDto;
import com.cursodos.cursodos.model.Issue;
import com.cursodos.cursodos.repository.IIssueRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class IssueService implements IIssueService{

    @Autowired
    private IIssueRepository issueRepository;

    public List<IssueDto> getIssues() {
        List<Issue> listIssue = issueRepository.findAll();
        List<IssueDto> listIssueDto = new ArrayList<>();

        for(Issue issue:listIssue){
            IssueDto issueDto = new IssueDto();
            issueDto.setIssueId(issue.getIssueId());
            issueDto.setIssueName(issue.getIssueName());
            issueDto.setIssueDescription(issue.getIssueDescription());

            listIssueDto.add(issueDto);
        }


        return listIssueDto;
    }

    public IssueDto getById(Long issueId) {
        Issue issue = issueRepository.findById(issueId)
                .orElseThrow(() -> new RuntimeException("Issue not found with id: " + issueId));
        IssueDto issueDto = new IssueDto();
        issueDto.setIssueId(issue.getIssueId());
        issueDto.setIssueName(issue.getIssueName());
        issueDto.setIssueDescription(issue.getIssueDescription());

        return issueDto;
    }

    public void createIssue(IssueDto issueDto) {
        Issue issue = new Issue();

        if (issueDto.getIssueName() == null) {
            throw new IllegalArgumentException("El nombre del tema no puede ser nulo.");
        }

        if (issueDto.getIssueDescription() == null) {
            throw new IllegalArgumentException("La descripcion del tema no puede ser nulo.");
        }

        issue.setIssueDescription(issueDto.getIssueDescription());
        issue.setIssueName(issueDto.getIssueName());
        issueRepository.save(issue);
    }

    public void updateIssue(IssueDto issueDto) {
        if (issueDto.getIssueId() == null || issueDto.getIssueId() <= 0L) {
            throw new IllegalArgumentException("El Id del tema no puede ser nulo y tampoco puede ser 0 o negativo.");
        }

        if (issueDto.getIssueName() == null) {
            throw new IllegalArgumentException("El nombre del tema no puede ser nulo.");
        }

        if (issueDto.getIssueDescription() == null) {
            throw new IllegalArgumentException("La descripcion del tema no puede ser nulo.");
        }

        Issue issue = issueRepository.findById(issueDto.getIssueId())
                .orElseThrow(() -> new RuntimeException("Issue not found with id: " + issueDto.getIssueId()));

        issue.setIssueName(issueDto.getIssueName());
        issue.setIssueDescription(issueDto.getIssueDescription());

        issueRepository.save(issue);
    }

    public void deleteIssue(Long issueId) {
        issueRepository.deleteById(issueId);
    }
}

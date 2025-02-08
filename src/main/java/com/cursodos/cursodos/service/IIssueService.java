package com.cursodos.cursodos.service;

import com.cursodos.cursodos.dto.IssueDto;

import java.util.List;

public interface IIssueService {
    public List<IssueDto> getIssues();
    public IssueDto getById(Long issueId);
    public void createIssue(IssueDto issueDto);
    public void updateIssue(IssueDto issueDto);
    public void deleteIssue(Long issueId);
}

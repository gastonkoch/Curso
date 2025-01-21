package com.cursodos.cursodos.dto;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class IssueDto {
    private Long issueId;
    private String issueName;
    private String issueDescription;

    public IssueDto() {
    }

    public IssueDto(Long issueId, String issueName, String issueDescription) {
        this.issueId = issueId;
        this.issueName = issueName;
        this.issueDescription = issueDescription;
    }



}

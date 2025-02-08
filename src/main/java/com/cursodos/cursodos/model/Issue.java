package com.cursodos.cursodos.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@Entity
public class Issue {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long issueId;
    private String issueName;
    private String issueDescription;

    public Issue() {
    }

    public Issue(Long issueId, String issueName, String issueDescription) {
        this.issueId = issueId;
        this.issueName = issueName;
        this.issueDescription = issueDescription;
    }


}

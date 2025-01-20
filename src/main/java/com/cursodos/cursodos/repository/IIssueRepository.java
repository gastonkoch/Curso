package com.cursodos.cursodos.repository;

import com.cursodos.cursodos.model.Issue;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IIssueRepository extends JpaRepository<Issue,Long> {
}

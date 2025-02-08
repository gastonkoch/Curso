package com.cursodos.cursodos.repository;

import com.cursodos.cursodos.model.FieldOfStudy;
import com.cursodos.cursodos.model.Subject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface IFieldOfStudyRepository extends JpaRepository<FieldOfStudy,Long> {
}

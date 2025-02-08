package com.cursodos.cursodos.repository;

import com.cursodos.cursodos.model.Subject;
import com.cursodos.cursodos.model.Subject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ISubjectRepository extends JpaRepository<Subject, Long> {

//    @Query("Subject s FROM Subject s WHERE LOWER(s.subjectName) LIKE LOWER(CONCAT('%', :name, '%'))")
//    List<Subject> findBySubjectNameContaining(@Param("name") String name);

    @Query("SELECT s FROM Subject s WHERE s.fieldOfStudy.fieldOfStudyId = :fieldOfStudyId")
    List<Subject> findSubjectsByFieldOfStudyId(@Param("fieldOfStudyId") Long fieldOfStudyId);
}

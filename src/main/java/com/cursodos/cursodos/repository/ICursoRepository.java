package com.cursodos.cursodos.repository;

import com.cursodos.cursodos.model.Curso;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ICursoRepository extends JpaRepository<Curso, Long> {

    @Query("SELECT c FROM Curso c WHERE LOWER(c.cursoName) LIKE LOWER(CONCAT('%', :name, '%'))")
    List<Curso> findByCursoNameContaining(@Param("name") String name);
}

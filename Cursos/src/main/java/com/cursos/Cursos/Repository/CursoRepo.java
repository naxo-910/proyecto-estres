package com.cursos.Cursos.Repository;

import com.cursos.Cursos.Model.Curso;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CursoRepo extends JpaRepository<Curso, Long> {
}
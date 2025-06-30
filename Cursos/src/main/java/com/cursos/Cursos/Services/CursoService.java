package com.cursos.Cursos.Services;

import com.cursos.Cursos.Model.Curso;
import java.util.List;

public interface CursoService {
    List<Curso> getAllCursos();
    Curso getCursoById(Long id);
    Curso saveCurso(Curso curso);
    Curso updateCurso(Long id, Curso curso);
    void deleteCurso(Long id);
}
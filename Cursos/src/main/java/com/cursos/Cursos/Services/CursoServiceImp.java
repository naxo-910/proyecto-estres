package com.cursos.Cursos.Services;

import com.cursos.Cursos.Model.Curso;
import com.cursos.Cursos.Repository.CursoRepo;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CursoServiceImp implements CursoService {

    private final CursoRepo cursoRepo;

    public CursoServiceImp(CursoRepo cursoRepo) {
        this.cursoRepo = cursoRepo;
    }

    @Override
    public List<Curso> getAllCursos() {
        return cursoRepo.findAll();
    }

    @Override
    public Curso getCursoById(Long id) {
        return cursoRepo.findById(id).orElse(null);
    }

    @Override
    public Curso saveCurso(Curso curso) {
        return cursoRepo.save(curso);
    }

    @Override
    public Curso updateCurso(Long id, Curso curso) {
        Optional<Curso> optionalCurso = cursoRepo.findById(id);
        if (optionalCurso.isPresent()) {
            Curso existing = optionalCurso.get();
            existing.setNombre(curso.getNombre());
            existing.setDescripcion(curso.getDescripcion());
            return cursoRepo.save(existing);
        }
        return null;
    }

    @Override
    public void deleteCurso(Long id) {
        cursoRepo.deleteById(id);
    }
}

package com.cursos.Cursos;

import com.cursos.Cursos.Model.Curso;
import com.cursos.Cursos.Repository.CursoRepo;
import net.datafaker.Faker;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements CommandLineRunner {

    private final CursoRepo cursoRepo;

    public DataLoader(CursoRepo cursoRepo) {
        this.cursoRepo = cursoRepo;
    }

    @Override
    public void run(String... args) {
        Faker faker = new Faker();
        for (int i = 0; i < 10; i++) {
            Curso curso = new Curso();
            curso.setNombre(faker.educator().course());
            curso.setDescripcion(faker.lorem().sentence());
            cursoRepo.save(curso);
        }
    }
}

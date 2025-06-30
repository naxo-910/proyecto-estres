package com.cursos.Cursos.Services;

import com.cursos.Cursos.Model.Curso;
import com.cursos.Cursos.Repository.CursoRepo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CursoServiceImpTest {

    @Mock
    private CursoRepo cursoRepo;

    @InjectMocks
    private CursoServiceImp cursoServiceImp;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testFindAll() {
        Curso curso1 = new Curso();
        Curso curso2 = new Curso();
        when(cursoRepo.findAll()).thenReturn(Arrays.asList(curso1, curso2));

        List<Curso> cursos = cursoServiceImp.getAllCursos();

        assertNotNull(cursos);
        assertEquals(2, cursos.size());
        verify(cursoRepo, times(1)).findAll();
    }
}
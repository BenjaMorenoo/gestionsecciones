package com.gestionsecciones.gestionsecciones.repository;

import com.gestionsecciones.gestionsecciones.model.Nota;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NotaRepository extends JpaRepository<Nota, Integer> {
    List<Nota> findByIdAlumno(int idAlumno);
    List<Nota> findByEvaluacion_IdEvaluacion(int idEvaluacion);
}

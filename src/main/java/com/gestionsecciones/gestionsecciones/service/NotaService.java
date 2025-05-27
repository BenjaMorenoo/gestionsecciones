package com.gestionsecciones.gestionsecciones.service;

import com.gestionsecciones.gestionsecciones.model.Nota;
import com.gestionsecciones.gestionsecciones.repository.NotaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class NotaService {

    @Autowired
    private NotaRepository notaRepository;

    public Nota guardarNota(Nota nota) {
        return notaRepository.save(nota);
    }

    public List<Nota> obtenerNotasPorAlumno(int idAlumno) {
        return notaRepository.findByIdAlumno(idAlumno);
    }

    public List<Nota> obtenerNotasPorEvaluacion(int idEvaluacion) {
        return notaRepository.findByEvaluacion_IdEvaluacion(idEvaluacion);
    }

    public Optional<Nota> actualizarNota(int idNota, Nota nuevaNota) {
        return notaRepository.findById(idNota)
                .map(notaExistente -> {
                    notaExistente.setNota(nuevaNota.getNota());
                    return notaRepository.save(notaExistente);
                });
                
    }

     public boolean eliminarNota(int idNota) {
        if (notaRepository.existsById(idNota)) {
            notaRepository.deleteById(idNota);
            return true;
        }
        return false;
    }
}

package co.edu.uniandes.dse.parcialejemplo.services;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import co.edu.uniandes.dse.parcialejemplo.repositories.*;
import co.edu.uniandes.dse.parcialejemplo.entities.*;
import javax.persistence.EntityNotFoundException;

@Service
public class EspecialidadService {

    @Autowired
    private EspecialidadRepository especialidadRepository;
    @Transactional
    public EspecialidadEntity createEspecialidad(EspecialidadEntity especialidad) throws EntityNotFoundException {
        if (especialidad.getDescripcion().length() < 10) {
            throw new EntityNotFoundException("La descripción debe tener al menos 10 caracteres.");
        }
        return especialidadRepository.save(especialidad);
    }
}
package co.edu.uniandes.dse.parcialejemplo.services;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import co.edu.uniandes.dse.parcialejemplo.repositories.*;
import co.edu.uniandes.dse.parcialejemplo.entities.*;
import javax.persistence.EntityNotFoundException;

@Service
public class MedicoEspecialidadService {

    @Autowired
    private MedicoRepository medicoRepository;

    @Autowired
    private EspecialidadRepository especialidadRepository;
    @Transactional
    public void addEspecialidadToMedico(Long medicoId, Long especialidadId) throws EntityNotFoundException {
        MedicoEntity medico = medicoRepository.findById(medicoId)
                .orElseThrow(() -> new EntityNotFoundException("Médico no encontrado"));
        EspecialidadEntity especialidad = especialidadRepository.findById(especialidadId)
                .orElseThrow(() -> new EntityNotFoundException("Especialidad no encontrada"));

        medico.getEspecialidades().add(especialidad);
        medicoRepository.save(medico);
    }
}
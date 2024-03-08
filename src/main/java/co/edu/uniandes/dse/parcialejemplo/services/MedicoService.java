package co.edu.uniandes.dse.parcialejemplo.services;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import co.edu.uniandes.dse.parcialejemplo.repositories.*;
import co.edu.uniandes.dse.parcialejemplo.entities.*;
import co.edu.uniandes.dse.parcialejemplo.exceptions.EntityNotFoundException;

@Service
public class MedicoService {

    @Autowired
    private MedicoRepository medicoRepository;
    @Transactional
    public MedicoEntity createMedico(MedicoEntity medico) throws EntityNotFoundException {
        
        if (!medico.getRegistroMedico().startsWith("RM")) {
            throw new EntityNotFoundException("El registro médico debe iniciar con 'RM'.");
        }
        
        return medicoRepository.save(medico);
    }
}
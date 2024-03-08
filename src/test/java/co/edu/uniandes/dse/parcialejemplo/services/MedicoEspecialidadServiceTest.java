package co.edu.uniandes.dse.parcialejemplo.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;


import javax.transaction.Transactional;
import javax.persistence.EntityNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import co.edu.uniandes.dse.parcialejemplo.entities.*;


@ExtendWith(SpringExtension.class)
@DataJpaTest
@Transactional
@Import(MedicoEspecialidadService.class)
class MedicoEspecialidadServiceTest {
    
    @Autowired
    private MedicoEspecialidadService medicoEspecialidadService;
    
    @Autowired
    private TestEntityManager entityManager;

    private MedicoEntity medicoEntity;
    private EspecialidadEntity especialidadEntity;

    @BeforeEach
    void setUp() {
        entityManager.getEntityManager().createQuery("delete from MedicoEntity").executeUpdate();
        entityManager.getEntityManager().createQuery("delete from EspecialidadEntity").executeUpdate();
        
        medicoEntity = new MedicoEntity();
        medicoEntity.setNombre("Laura");
        medicoEntity.setApellido("Gomez");
        medicoEntity.setRegistroMedico("RM6789");
        entityManager.persist(medicoEntity);

        especialidadEntity = new EspecialidadEntity();
        especialidadEntity.setNombre("Pediatría");
        especialidadEntity.setDescripcion("Especialidad médica enfocada en niños y adolescentes");
        entityManager.persist(especialidadEntity);
    }

    @Test
    void testAddEspecialidadToMedico() throws EntityNotFoundException {
        medicoEspecialidadService.addEspecialidadToMedico(medicoEntity.getId(), especialidadEntity.getId());

        MedicoEntity foundMedico = entityManager.find(MedicoEntity.class, medicoEntity.getId());
        assertNotNull(foundMedico.getEspecialidades());
        assertEquals(1, foundMedico.getEspecialidades().size());
        assertEquals(especialidadEntity.getId(), foundMedico.getEspecialidades().iterator().next().getId());
    }

    @Test
    void testAddEspecialidadToNonExistingMedico() {
        assertThrows(EntityNotFoundException.class, () -> {
            medicoEspecialidadService.addEspecialidadToMedico(0L, especialidadEntity.getId());
        });
    }

    @Test
    void testAddNonExistingEspecialidadToMedico() {
        assertThrows(EntityNotFoundException.class, () -> {
            medicoEspecialidadService.addEspecialidadToMedico(medicoEntity.getId(), 0L);
        });
    }
}
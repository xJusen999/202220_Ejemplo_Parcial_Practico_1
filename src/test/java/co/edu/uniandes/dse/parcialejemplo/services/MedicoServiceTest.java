package co.edu.uniandes.dse.parcialejemplo.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;


import javax.transaction.Transactional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import co.edu.uniandes.dse.parcialejemplo.entities.*;

import co.edu.uniandes.dse.parcialejemplo.exceptions.EntityNotFoundException;


@ExtendWith(SpringExtension.class)
@DataJpaTest
@Transactional
@Import(MedicoService.class)
class MedicoServiceTest {
    
    @Autowired
    private MedicoService medicoService;
    
    @Autowired
    private TestEntityManager entityManager;

    @BeforeEach
    void setUp() {
        entityManager.getEntityManager().createQuery("delete from MedicoEntity").executeUpdate();
    }

    @Test
    void testCreateMedicoCorrectly() throws EntityNotFoundException {
        MedicoEntity newMedico = new MedicoEntity();
        newMedico.setNombre("Carlos");
        newMedico.setApellido("Lopez");
        newMedico.setRegistroMedico("RM1234");

        MedicoEntity result = medicoService.createMedico(newMedico);

        assertNotNull(result);

        MedicoEntity entity = entityManager.find(MedicoEntity.class, result.getId());
        assertNotNull(entity);
        assertEquals("Carlos", entity.getNombre());
    }

    @Test
    void testCreateMedicoWithInvalidRegistroMedico() {
        MedicoEntity newMedico = new MedicoEntity();
        newMedico.setNombre("Ana");
        newMedico.setApellido("Martinez");
        newMedico.setRegistroMedico("1234RM"); 

        assertThrows(EntityNotFoundException.class, () -> {
            medicoService.createMedico(newMedico); 
        });
    }
}
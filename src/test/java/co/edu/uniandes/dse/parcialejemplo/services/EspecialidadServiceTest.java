package co.edu.uniandes.dse.parcialejemplo.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import javax.persistence.EntityNotFoundException;
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

@ExtendWith(SpringExtension.class)
@DataJpaTest
@Transactional
@Import(EspecialidadService.class)
class EspecialidadServiceTest {
    
    @Autowired
    private EspecialidadService especialidadService;
    
    @Autowired
    private TestEntityManager entityManager;

    @BeforeEach
    void setUp() {
        entityManager.getEntityManager().createQuery("delete from EspecialidadEntity").executeUpdate();
    }

    @Test
    void testCreateEspecialidadCorrectly() throws EntityNotFoundException {
        EspecialidadEntity newEspecialidad = new EspecialidadEntity();
        newEspecialidad.setNombre("Cardiología");
        newEspecialidad.setDescripcion("Especialidad médica dedicada a las afecciones del corazón");

        EspecialidadEntity result = especialidadService.createEspecialidad(newEspecialidad);

        assertNotNull(result);

        EspecialidadEntity entity = entityManager.find(EspecialidadEntity.class, result.getId());
        assertNotNull(entity);
        assertEquals("Cardiología", entity.getNombre());
    }

    @Test
    void testCreateEspecialidadWithShortDescription() {
        EspecialidadEntity newEspecialidad = new EspecialidadEntity();
        newEspecialidad.setNombre("Neurología");
        newEspecialidad.setDescripcion("Corta");

        assertThrows(EntityNotFoundException.class, () -> {
            especialidadService.createEspecialidad(newEspecialidad);
        });
    }
}
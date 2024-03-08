package co.edu.uniandes.dse.parcialejemplo.repositories;
import org.springframework.data.jpa.repository.JpaRepository;
import co.edu.uniandes.dse.parcialejemplo.entities.*;

public interface MedicoRepository extends JpaRepository<MedicoEntity, Long> {
}
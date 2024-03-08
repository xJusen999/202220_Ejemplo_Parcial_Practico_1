package co.edu.uniandes.dse.parcialejemplo.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import java.util.HashSet;
import java.util.Set;

import lombok.Data;

@Entity
@Data
public class EspecialidadEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;
    private String descripcion;

    @ManyToMany(mappedBy = "especialidades")
    private Set<MedicoEntity> medicos = new HashSet<>();

   
}
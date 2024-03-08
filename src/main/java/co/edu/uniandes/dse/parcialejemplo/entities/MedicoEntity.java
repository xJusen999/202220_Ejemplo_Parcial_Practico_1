package co.edu.uniandes.dse.parcialejemplo.entities;

import java.util.HashSet;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import java.util.Set;

import lombok.Data;

@Entity
@Data
public class MedicoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;
    private String apellido;
    private String registroMedico;

    @ManyToMany
    @JoinTable(
      name = "medico_especialidad",
      joinColumns = @JoinColumn(name = "medico_id"),
      inverseJoinColumns = @JoinColumn(name = "especialidad_id")
    )
    private Set<EspecialidadEntity> especialidades = new HashSet<>();
   
}
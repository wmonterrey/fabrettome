package ni.org.fabretto.me.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.hibernate.annotations.ForeignKey;

import ni.org.fabretto.me.domain.audit.Auditable;

/**
 * Familia es la clase que representa a una relacion familiar del Estudiante en el sistema.<br><br>
 * Nombre de la tabla<br>
 * Table(name = "tblFamilia", catalog = "fabrettome")<br><br>
 * 
 * EstudianteFamilia se relaciona con:
 * 
 * <ul>
 * <li>Persona
 * <li>Estudiante
 * </ul>
 * 
 *  
 * @author      William Avilés
 * @version     1.0
 * @since       1.0
 */
@Entity
@Table(name = "tblFamilia", catalog = "fabrettome", uniqueConstraints={@UniqueConstraint(columnNames = {"idEstudiante","idPersonaRelacionada"})})
public class Familia extends BaseMetaData implements Auditable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String idUnico;
	private Estudiante estudiante;
	private Persona personaRelacionada;
	private String relacionFamiliar;
	
	
	public Familia() {
		super();
	}
	
	/** Columna = "idUnico",  = false, length = 50.
	 * @return idUnico Identificador único del registro en el sistema, generado automáticamente.*/
	@Id
	@Column(name = "idUnico", nullable = false, length = 50)
	public String getIdUnico() {
		return idUnico;
	}
	public void setIdUnico(String idUnico) {
		this.idUnico = idUnico;
	}

	/** Columna = "idPersonaRelacionada", nullable = false, length = 50.
	 * @return personaRelacionada - Persona que tiene relacion familiar con el estudiante*/
	@ManyToOne(optional=false)
	@JoinColumn(name="idPersonaRelacionada")
	@ForeignKey(name = "fkPersonaRelacionFamiliar")
	public Persona getPersonaRelacionada() {
		return personaRelacionada;
	}
	public void setPersonaRelacionada(Persona personaRelacionada) {
		this.personaRelacionada = personaRelacionada;
	}

	/** Columna = "idEstudiante", nullable = false, length = 50.
	 * @return estudiante - Estudiante en la relacion familiar*/
	@ManyToOne(optional=false)
	@JoinColumn(name="idEstudiante")
	@ForeignKey(name = "fkEstudianteRelacionFamiliar")
	public Estudiante getEstudiante() {
		return estudiante;
	}

	public void setEstudiante(Estudiante estudiante) {
		this.estudiante = estudiante;
	}

	/** Columna = "relacionFamiliar",  = false, length = 2.
	 * @return relacionFamiliar Relacion familiar*/
	@Column(name = "relacionFamiliar", nullable = false, length = 2)
	public String getRelacionFamiliar() {
		return relacionFamiliar;
	}

	public void setRelacionFamiliar(String relacionFamiliar) {
		this.relacionFamiliar = relacionFamiliar;
	}

	@Override
	public boolean isFieldAuditable(String fieldname) {
		return true;
	}

	@Override
	public String toString(){
		return this.idUnico;
	}
	
	@Override
	public boolean equals(Object other) {
		
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof Familia))
			return false;
		
		Familia castOther = (Familia) other;

		return (this.getIdUnico().equals(castOther.getIdUnico()));
	}

}

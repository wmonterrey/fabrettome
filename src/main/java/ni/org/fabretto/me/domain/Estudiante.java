package ni.org.fabretto.me.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.ForeignKey;

import ni.org.fabretto.me.domain.audit.Auditable;
import ni.org.fabretto.me.domain.catalogs.Centro;
import ni.org.fabretto.me.domain.catalogs.Escuela;

/**
 * Estudiante es la clase que representa a un Estudiante en el sistema.<br><br>
 * Nombre de la tabla<br>
 * Table(name = "tblEstudiante", catalog = "fabrettome")<br><br>
 * 
 * Estudiante se relaciona con:
 * 
 * <ul>
 * <li>Persona
 * <li>Centro
 * <li>Escuela
 * </ul>
 * 
 *  
 * @author      William Avilés
 * @version     1.0
 * @since       1.0
 */
@Entity
@Table(name = "tblEstudiante", catalog = "fabrettome")
public class Estudiante extends BaseMetaData implements Auditable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String idUnico;
	private Persona persona;
	private Centro centro;
	private Escuela escuela;

	
	public Estudiante() {
		super();
	}

	
	@Id
	@Column(name = "idUnico", nullable = false, length = 50)
	/** Columna = "idUnico", nullable = false, length = 50.
	 * @return idUnico Identificador único del registro en el sistema, generado automáticamente.*/
	public String getIdUnico() {
		return idUnico;
	}
	public void setIdUnico(String idUnico) {
		this.idUnico = idUnico;
	}

	@OneToOne(optional=false)
	@JoinColumn(name="idPersona")
	@ForeignKey(name = "fkPersonaEstudiante")
	/** Columna = "idPersona", nullable = false, length = 50.
	 * @return persona - Persona que es el estudiante*/
	public Persona getPersona() {
		return persona;
	}
	public void setPersona(Persona persona) {
		this.persona = persona;
	}

	@OneToOne(optional=false)
	@JoinColumn(name="idCentro")
	@ForeignKey(name = "fkCentroEstudiante")
	/** Columna = "idCentro", nullable = false, length = 50.
	 * @return centro - Codigo del centro u Oratorio donde pertece el estudiante*/
	public Centro getCentro() {
		return centro;
	}
	public void setCentro(Centro centro) {
		this.centro = centro;
	}

	@OneToOne(optional=false)
	@JoinColumn(name="idEscuela")
	@ForeignKey(name = "fkEscuelaEstudiante")
	/** Columna = "idEscuela", nullable = false, length = 50.
	 * @return escuela - Codigo de la escuela donde pertece el estudiante*/
	public Escuela getEscuela() {
		return escuela;
	}
	public void setEscuela(Escuela escuela) {
		this.escuela = escuela;
	}


	@Override
	public boolean isFieldAuditable(String fieldname) {
		return true;
	}

	@Override
	public String toString(){
		return persona.getTipoIdent() +": "+persona.getIdent();
	}
	
	@Override
	public boolean equals(Object other) {
		
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof Estudiante))
			return false;
		
		Estudiante castOther = (Estudiante) other;

		return (this.getIdUnico().equals(castOther.getIdUnico()));
	}

}

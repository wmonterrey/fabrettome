package ni.org.fabretto.me.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.hibernate.annotations.ForeignKey;

import ni.org.fabretto.me.domain.audit.Auditable;

/**
 * Estudiante es la clase que representa a un Estudiante en el sistema.<br><br>
 * Nombre de la tabla<br>
 * Table(name = "tblEstudiante", catalog = "fabrettome", uniqueConstraints={UniqueConstraint(columnNames = {"idEstudiante"})})<br><br>
 * 
 * Estudiante se relaciona con:
 * 
 * <ul>
 * <li>Persona
 * </ul>
 * 
 *  
 * @author      William Avilés
 * @version     1.0
 * @since       1.0
 */
@Entity
@Table(name = "tblEstudiante", catalog = "fabrettome", uniqueConstraints={@UniqueConstraint(columnNames = {"idEstudiante"})})
public class Estudiante extends BaseMetaData implements Auditable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String idUnico;
	private String idEstudiante;
	private Persona persona;
	private Date fechaIngreso;
	private Date fechaEgreso;
	private String razonEgreso;
	private String estado;
	
	
	public Estudiante() {
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
	
	/** Columna = "idEstudiante",  = false, length = 50.
	 * @return idEstudiante Identificador único del estudiante en el sistema.*/
	@Column(name = "idEstudiante", nullable = false, length = 50)
	public String getIdEstudiante() {
		return idEstudiante;
	}

	public void setIdEstudiante(String idEstudiante) {
		this.idEstudiante = idEstudiante;
	}

	/** Columna = "idPersona", nullable = false, length = 50.
	 * @return persona - Persona que es el estudiante*/
	@OneToOne(optional=false)
	@JoinColumn(name="idPersona")
	@ForeignKey(name = "fkPersonaEstudiante")
	public Persona getPersona() {
		return persona;
	}
	public void setPersona(Persona persona) {
		this.persona = persona;
	}

	/** Column(name = "fechaIngreso", nullable = false).
	 * @return fechaIngreso Fecha de inicio en los programas*/
	@Column(name = "fechaIngreso", nullable = false)
	public Date getFechaIngreso() {
		return fechaIngreso;
	}

	public void setFechaIngreso(Date fechaIngreso) {
		this.fechaIngreso = fechaIngreso;
	}

	/** Column(name = "fechaEgreso", nullable = true).
	 * @return fechaEgreso Fecha de fin en los programas*/
	@Column(name = "fechaEgreso", nullable = true)
	public Date getFechaEgreso() {
		return fechaEgreso;
	}

	public void setFechaEgreso(Date fechaEgreso) {
		this.fechaEgreso = fechaEgreso;
	}

	/** Column(name = "razonEgreso", nullable = true, length = 250)
	 * @return razonEgreso Razon por la que sale de los programas en Fabretto.*/
	@Column(name = "razonEgreso", nullable = true, length = 250)
	public String getRazonEgreso() {
		return razonEgreso;
	}

	public void setRazonEgreso(String razonEgreso) {
		this.razonEgreso = razonEgreso;
	}

	/** Column(name = "estado", nullable = false, length = 1)
	 * @return Estado actual del estudiante.*/
	@Column(name = "estado", nullable = false, length = 1)
	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
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

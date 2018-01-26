package ni.org.fabretto.me.domain.capacitacion;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.hibernate.annotations.ForeignKey;

import ni.org.fabretto.me.domain.BaseMetaData;
import ni.org.fabretto.me.domain.Persona;
import ni.org.fabretto.me.domain.audit.Auditable;

/**
 * CapacitacionAsistencia es la clase que representa la asistencia de una capacitación<br><br>
 * Nombre de la tabla<br>
 * Table(name = "tblCapacitacionAsistencia", catalog = "fabrettome", uniqueConstraints={UniqueConstraint(columnNames = {"idCapacitacion","idPersona"})})<br><br>
 *
 * <ul>
 * <li>Capacitacion
 * <li>Persona
 * </ul>
 *  
 * @author      William Avilés
 * @version     1.0
 * @since       1.0
 */
@Entity
@Table(name = "tblCapacitacionAsistencia", catalog = "fabrettome", uniqueConstraints={@UniqueConstraint(columnNames = {"idCapacitacion","idPersona"})})
public class CapacitacionAsistencia extends BaseMetaData implements Auditable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String idUnico;
	private Capacitacion capacitacion;
	private String tipoParticipante;
	private Persona participante;
	private Integer horasRecibidas;
	
	
	
	
	
	public CapacitacionAsistencia() {
		super();
	}

	/** Columna = "idUnico", nullable = false, length = 50.
	 * @return idUnico Identificador único del registro en el sistema, generado automáticamente.*/
	@Id
	@Column(name = "idUnico", nullable = false, length = 50)
	public String getIdUnico() {
		return idUnico;
	}
	public void setIdUnico(String idUnico) {
		this.idUnico = idUnico;
	}
	
	/** Columna = "idCapacitacion", nullable = false, length = 50.
	 * @return capacitacion Capacitacion*/
	@ManyToOne(optional=false)
	@JoinColumn(name="idCapacitacion")
	@ForeignKey(name = "fkCapacitacionCapacitacionAsistencia")
	public Capacitacion getCapacitacion() {
		return capacitacion;
	}

	public void setCapacitacion(Capacitacion capacitacion) {
		this.capacitacion = capacitacion;
	}
	
	/** Columna = "tipoParticipante", nullable = false, length = 5.
	 * @return tipoParticipante Tipo de Participante en la capacitacion*/
	@Column(name = "tipoParticipante", nullable = false, length = 50)
	public String getTipoParticipante() {
		return tipoParticipante;
	}

	public void setTipoParticipante(String tipoParticipante) {
		this.tipoParticipante = tipoParticipante;
	}

	/** Columna = "idPersona", nullable = false, length = 50.
	 * @return participante Participante en la Capacitacion*/
	@ManyToOne(optional=false)
	@JoinColumn(name="idPersona")
	@ForeignKey(name = "fkPersonaCapacitacionAsistencia")
	public Persona getParticipante() {
		return participante;
	}

	public void setParticipante(Persona participante) {
		this.participante = participante;
	}

	/** Columna = "horasRecibidas", nullable = false
	 * @return horasRecibidas Horas recibidas en la capacitacion*/
	@Column(name = "horasRecibidas", nullable = false)
	public Integer getHorasRecibidas() {
		return horasRecibidas;
	}

	public void setHorasRecibidas(Integer horasRecibidas) {
		this.horasRecibidas = horasRecibidas;
	}

	@Override
	public boolean equals(Object other) {
		
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof CapacitacionAsistencia))
			return false;
		
		CapacitacionAsistencia castOther = (CapacitacionAsistencia) other;

		return (this.getIdUnico().equals(castOther.getIdUnico()));
	}


	@Override
	public boolean isFieldAuditable(String fieldname) {
		return true;
	}

}

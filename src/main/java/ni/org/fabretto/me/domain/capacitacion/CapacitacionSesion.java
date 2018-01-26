package ni.org.fabretto.me.domain.capacitacion;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.hibernate.annotations.ForeignKey;

import ni.org.fabretto.me.domain.BaseMetaData;
import ni.org.fabretto.me.domain.audit.Auditable;

/**
 * CapacitacionSesion es la clase que representa a las sesiones de una capacitación<br><br>
 * Nombre de la tabla<br>
 * Table(name = "tblCapacitacionSesion", catalog = "fabrettome", uniqueConstraints={UniqueConstraint(columnNames = {"idCapacitacion","idSesion"})})<br><br>
 *
 * <ul>
 * <li>Curso
 * <li>Centro
 * <li>Comunidad
 * </ul>
 *  
 * @author      William Avilés
 * @version     1.0
 * @since       1.0
 */
@Entity
@Table(name = "tblCapacitacionSesion", catalog = "fabrettome", uniqueConstraints={@UniqueConstraint(columnNames = {"idCapacitacion","idSesion"})})
public class CapacitacionSesion extends BaseMetaData implements Auditable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String idUnico;
	private Capacitacion capacitacion;
	private Sesion sesion;
	private Date fechaInicio;
	private Date fechaFin;
	
	
	
	
	
	public CapacitacionSesion() {
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
	@ForeignKey(name = "fkCapacitacionCapacitacionSesion")
	public Capacitacion getCapacitacion() {
		return capacitacion;
	}

	public void setCapacitacion(Capacitacion capacitacion) {
		this.capacitacion = capacitacion;
	}

	/** Columna = "idSesion", nullable = false, length = 50.
	 * @return sesion Sesion en esta capacitacion .*/
	@ManyToOne(optional=false)
	@JoinColumn(name="idSesion")
	@ForeignKey(name = "fkSesionCapacitacionSesion")
	public Sesion getSesion() {
		return sesion;
	}

	public void setSesion(Sesion sesion) {
		this.sesion = sesion;
	}

	/** Columna = "fechaInicio", nullable = false
	 * @return fechaInicio Fecha de Inicio de la capacitacion*/
	@Column(name = "fechaInicio", nullable = false)
	public Date getFechaInicio() {
		return fechaInicio;
	}

	public void setFechaInicio(Date fechaInicio) {
		this.fechaInicio = fechaInicio;
	}

	/** Columna = "fechaFin", nullable = false
	 * @return fechaFin Fecha de Fin de la capacitacion*/
	@Column(name = "fechaFin", nullable = false)
	public Date getFechaFin() {
		return fechaFin;
	}

	public void setFechaFin(Date fechaFin) {
		this.fechaFin = fechaFin;
	}
	
	@Override
	public boolean equals(Object other) {
		
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof CapacitacionSesion))
			return false;
		
		CapacitacionSesion castOther = (CapacitacionSesion) other;

		return (this.getIdUnico().equals(castOther.getIdUnico()));
	}


	@Override
	public boolean isFieldAuditable(String fieldname) {
		return true;
	}

}

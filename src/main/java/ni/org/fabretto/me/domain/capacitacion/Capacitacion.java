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
import ni.org.fabretto.me.domain.catalogs.Centro;
import ni.org.fabretto.me.domain.catalogs.Comunidad;

/**
 * Capacitacion es la clase que representa a una capacitación<br><br>
 * Nombre de la tabla<br>
 * Table(name = "tblCapacitacion", catalog = "fabrettome", uniqueConstraints={UniqueConstraint(columnNames = {"idCurso","idComunidad","fechaInicio"})})<br><br>
 *
 * Capacitacion se relaciona con:
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
@Table(name = "tblCapacitacion", catalog = "fabrettome", uniqueConstraints={@UniqueConstraint(columnNames = {"idCurso","idComunidad","fechaInicio"})})
public class Capacitacion extends BaseMetaData implements Auditable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String idUnico;
	private Curso curso;
	private Centro centro;
	private Comunidad comunidad;
	private Date fechaInicio;
	private Date fechaFin;
	private String objetivos;
	private String logros;
	private String nombreCapacitador;
	private String donante;
	
	
	
	
	public Capacitacion() {
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
	
	/** Columna = "idCurso", nullable = false, length = 50.
	 * @return curso Codigo del curso impartido en esta capacitacion .*/
	@ManyToOne(optional=false)
	@JoinColumn(name="idCurso")
	@ForeignKey(name = "fkCursoCapacitacion")
	public Curso getCurso() {
		return curso;
	}

	public void setCurso(Curso curso) {
		this.curso = curso;
	}

	/** Columna = "idCentro", nullable = false, length = 50.
	 * @return centro Codigo del centro u Oratorio donde esta la capacitacion .*/
	@ManyToOne(optional=false)
	@JoinColumn(name="idCentro")
	@ForeignKey(name = "fkCentroCapacitacion")
	public Centro getCentro() {
		return centro;
	}

	public void setCentro(Centro centro) {
		this.centro = centro;
	}

	/** Columna = "idComunidad", nullable = false, length = 50.
	 * @return comunidad Comunidad donde se impartio la capacitacion.*/
	@ManyToOne(optional=false)
	@JoinColumn(name="idComunidad")
	@ForeignKey(name = "fkComunidadCapacitacion")
	public Comunidad getComunidad() {
		return comunidad;
	}

	public void setComunidad(Comunidad comunidad) {
		this.comunidad = comunidad;
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

	/** Columna = "objetivos", nullable = false, length = 500.
	 * @return objetivos Objetivos de la capacitacion*/
	@Column(name = "objetivos", nullable = false, length = 500)
	public String getObjetivos() {
		return objetivos;
	}

	public void setObjetivos(String objetivos) {
		this.objetivos = objetivos;
	}

	/** Columna = "logros", nullable = false, length = 500.
	 * @return logros Logros de la capacitacion*/
	@Column(name = "logros", nullable = false, length = 500)
	public String getLogros() {
		return logros;
	}

	public void setLogros(String logros) {
		this.logros = logros;
	}

	/** Columna = "nombreCapacitador", nullable = false, length = 250.
	 * @return nombreCapacitador Nombre del Capacitador que brinda la capacitacion*/
	@Column(name = "nombreCapacitador", nullable = false, length = 250)
	public String getNombreCapacitador() {
		return nombreCapacitador;
	}

	public void setNombreCapacitador(String nombreCapacitador) {
		this.nombreCapacitador = nombreCapacitador;
	}

	/** Columna = "donante", nullable = false, length = 250.
	 * @return donante Donante que patrocina la capacitacion*/
	@Column(name = "donante", nullable = false, length = 250)
	public String getDonante() {
		return donante;
	}

	public void setDonante(String donante) {
		this.donante = donante;
	}

	@Override
	public boolean equals(Object other) {
		
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof Capacitacion))
			return false;
		
		Capacitacion castOther = (Capacitacion) other;

		return (this.getIdUnico().equals(castOther.getIdUnico()));
	}


	@Override
	public boolean isFieldAuditable(String fieldname) {
		return true;
	}

}

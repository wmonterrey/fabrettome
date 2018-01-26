package ni.org.fabretto.me.domain.actividad;

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
 * Actividad es la clase que representa a una Actividad del centro<br><br>
 * Nombre de la tabla<br>
 * Table(name = "tblActividad", catalog = "fabrettome", uniqueConstraints={UniqueConstraint(columnNames = {"idCentro","actividad","fechaActividad"})})<br><br>
 *
 * Capacitacion se relaciona con:
 * 
 * <ul>
 * <li>Centro
 * <li>Comunidad
 * </ul>
 *  
 * @author      William Avilés
 * @version     1.0
 * @since       1.0
 */
@Entity
@Table(name = "tblActividad", catalog = "fabrettome", uniqueConstraints={@UniqueConstraint(columnNames = {"idCentro","actividad","fechaActividad"})})
public class Actividad extends BaseMetaData implements Auditable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String idUnico;
	private Centro centro;
	private Comunidad comunidad;
	private Date fechaActividad;
	private String actividad;
	private String desActividad;
	private Integer numParticipantesAS;
	private Integer numParticipantesF;
	private String tiposParticipantes;
	private String donante;
	
	
	
	
	public Actividad() {
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

	/** Columna = "idCentro", nullable = false, length = 50.
	 * @return centro Codigo del centro u Oratorio donde esta la actividad .*/
	@ManyToOne(optional=false)
	@JoinColumn(name="idCentro")
	@ForeignKey(name = "fkCentroActividad")
	public Centro getCentro() {
		return centro;
	}

	public void setCentro(Centro centro) {
		this.centro = centro;
	}

	/** Columna = "idComunidad", nullable = false, length = 50.
	 * @return comunidad Comunidad donde se realizó la actividad.*/
	@ManyToOne(optional=false)
	@JoinColumn(name="idComunidad")
	@ForeignKey(name = "fkComunidadActividad")
	public Comunidad getComunidad() {
		return comunidad;
	}

	public void setComunidad(Comunidad comunidad) {
		this.comunidad = comunidad;
	}

	/** Columna = "fechaActividad", nullable = false
	 * @return fechaActividad Fecha de la actividad*/
	@Column(name = "fechaActividad", nullable = false)
	public Date getFechaActividad() {
		return fechaActividad;
	}

	public void setFechaActividad(Date fechaActividad) {
		this.fechaActividad = fechaActividad;
	}

	/** Columna = "actividad", nullable = false, length = 50.
	 * @return actividad Actividad realizada*/
	@Column(name = "actividad", nullable = false, length = 50)
	public String getActividad() {
		return actividad;
	}

	public void setActividad(String actividad) {
		this.actividad = actividad;
	}

	/** Columna = "desActividad", nullable = false, length = 500.
	 * @return desActividad Descripcion de la Actividad realizada*/
	@Column(name = "desActividad", nullable = false, length = 500)
	public String getDesActividad() {
		return desActividad;
	}

	public void setDesActividad(String desActividad) {
		this.desActividad = desActividad;
	}

	/** Columna = "numParticipantesAS", nullable = false
	 * @return numParticipantesAS Numero de participantes en la actividad*/
	@Column(name = "numParticipantesAS", nullable = false)
	public Integer getNumParticipantesAS() {
		return numParticipantesAS;
	}

	public void setNumParticipantesAS(Integer numParticipantesAS) {
		this.numParticipantesAS = numParticipantesAS;
	}

	/** Columna = "numParticipantesF", nullable = false
	 * @return numParticipantesF Numero de participantes femeninos en la actividad*/
	@Column(name = "numParticipantesF", nullable = false)
	public Integer getNumParticipantesF() {
		return numParticipantesF;
	}

	public void setNumParticipantesF(Integer numParticipantesF) {
		this.numParticipantesF = numParticipantesF;
	}

	/** Columna = "tiposParticipantes", nullable = false, length = 50.
	 * @return tiposParticipantes Lista tipo de participantes en la actividad*/
	@Column(name = "tiposParticipantes", nullable = false, length = 50)
	public String getTiposParticipantes() {
		return tiposParticipantes;
	}

	public void setTiposParticipantes(String tiposParticipantes) {
		this.tiposParticipantes = tiposParticipantes;
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
		if (!(other instanceof Actividad))
			return false;
		
		Actividad castOther = (Actividad) other;

		return (this.getIdUnico().equals(castOther.getIdUnico()));
	}


	@Override
	public boolean isFieldAuditable(String fieldname) {
		return true;
	}

}

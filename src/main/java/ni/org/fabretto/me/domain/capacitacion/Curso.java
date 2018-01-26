package ni.org.fabretto.me.domain.capacitacion;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import ni.org.fabretto.me.domain.BaseMetaData;
import ni.org.fabretto.me.domain.audit.Auditable;

/**
 * Curso es la clase que representa a un curso disponible para capacitación<br><br>
 * Nombre de la tabla<br>
 * Table(name = "catCurso", catalog = "fabrettome", uniqueConstraints={UniqueConstraint(columnNames = {"nombreCurso"})})<br><br>
 *
 *  
 * @author      William Avilés
 * @version     1.0
 * @since       1.0
 */
@Entity
@Table(name = "catCurso", catalog = "fabrettome", uniqueConstraints={@UniqueConstraint(columnNames = {"nombreCurso"})})
public class Curso extends BaseMetaData implements Auditable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String idUnico;
	private String nombreCurso;
	private String descCurso;
	private String tipoCurso;
	private String estado;
	
	public Curso() {
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
	
	/** Columna = "nombreCurso", nullable = false, length = 50.
	 * @return nombreCurso Nombre del Curso*/
	@Column(name = "nombreCurso", nullable = false, length = 50)
	public String getNombreCurso() {
		return nombreCurso;
	}

	public void setNombreCurso(String nombreCurso) {
		this.nombreCurso = nombreCurso;
	}


	/** Columna = "descCurso", nullable = false, length = 250.
	 * @return descCurso Descripcion del Curso*/
	@Column(name = "descCurso", nullable = false, length = 250)
	public String getDescCurso() {
		return descCurso;
	}


	public void setDescCurso(String descCurso) {
		this.descCurso = descCurso;
	}

	/** Columna = "tipoCurso", nullable = false, length = 5.
	 * @return tipoCurso Descripcion del Curso*/
	@Column(name = "tipoCurso", nullable = false, length = 5)
	public String getTipoCurso() {
		return tipoCurso;
	}


	public void setTipoCurso(String tipoCurso) {
		this.tipoCurso = tipoCurso;
	}

	/** Columna = "estado", nullable = false, length = 2
	 * @return estado Estado del Curso*/
	@Column(name = "estado", nullable = false, length = 2)
	public String getEstado() {
		return estado;
	}


	public void setEstado(String estado) {
		this.estado = estado;
	}


	@Override
	public boolean equals(Object other) {
		
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof Curso))
			return false;
		
		Curso castOther = (Curso) other;

		return (this.getIdUnico().equals(castOther.getIdUnico()));
	}


	@Override
	public boolean isFieldAuditable(String fieldname) {
		return true;
	}

}

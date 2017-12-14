package ni.org.fabretto.me.domain.catalogs;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.ForeignKey;

import ni.org.fabretto.me.domain.BaseMetaData;
import ni.org.fabretto.me.domain.audit.Auditable;

/**
 * Comunidad es la clase que representa a una Comunidad en el sistema.<br><br>
 * Nombre de la tabla<br>
 * Table(name = "catComunidad", catalog = "fabrettome")<br><br>
 * 
 *  
 * @author      William Avilés
 * @version     1.0
 * @since       1.0
 */
@Entity
@Table(name = "catComunidad", catalog = "fabrettome")
public class Comunidad extends BaseMetaData implements Auditable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String idUnico;
	private String nombreComunidad;
	private Departamento departamento;
	
	public Comunidad() {
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
	
	@Column(name = "nombreComunidad", nullable = false, length = 300)
	/** Columna = "nombreComunidad", nullable = false, length = 300.
	 * @return nombreComunidad Nombre de la comunidad.*/
	public String getNombreComunidad() {
		return nombreComunidad;
	}
	public void setNombreComunidad(String nombreComunidad) {
		this.nombreComunidad = nombreComunidad;
	}


	@ManyToOne(optional=false)
	@JoinColumn(name="idDepartamento")
	@ForeignKey(name = "fkDepartamentoComunidad")
	public Departamento getDepartamento() {
		return departamento;
	}
	public void setDepartamento(Departamento departamento) {
		this.departamento = departamento;
	}


	@Override
	public boolean equals(Object other) {
		
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof Comunidad))
			return false;
		
		Comunidad castOther = (Comunidad) other;

		return (this.getIdUnico().equals(castOther.getIdUnico()));
	}


	@Override
	public boolean isFieldAuditable(String fieldname) {
		return true;
	}

}

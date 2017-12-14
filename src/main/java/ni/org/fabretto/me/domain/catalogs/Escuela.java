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
 * Escuela es la clase que representa a una Escuela en el sistema.<br><br>
 * Nombre de la tabla<br>
 * Table(name = "catEscuela", catalog = "fabrettome")<br><br>
 * 
 *  
 * @author      William Avilés
 * @version     1.0
 * @since       1.0
 */
@Entity
@Table(name = "catEscuela", catalog = "fabrettome")
public class Escuela extends BaseMetaData implements Auditable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String idUnico;
	private String nombreEscuela;
	private Comunidad comunidad;
	
	public Escuela() {
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
	
	@Column(name = "nombreEscuela", nullable = false, length = 100)
	/** Columna = "nombreEscuela", nullable = false, length = 100.
	 * @return nombreEscuela Nombre de la escuela.*/
	public String getNombreEscuela() {
		return nombreEscuela;
	}
	public void setNombreEscuela(String nombreEscuela) {
		this.nombreEscuela = nombreEscuela;
	}


	@ManyToOne(optional=false)
	@JoinColumn(name="idComunidad")
	@ForeignKey(name = "fkComunidadEscuela")
	/** Columna = "idComunidad", nullable = false, length = 50.
	 * @return comunidad Comunidad donde esta ubicada la escuela.*/
	public Comunidad getComunidad() {
		return comunidad;
	}
	public void setComunidad(Comunidad comunidad) {
		this.comunidad = comunidad;
	}


	@Override
	public boolean equals(Object other) {
		
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof Escuela))
			return false;
		
		Escuela castOther = (Escuela) other;

		return (this.getIdUnico().equals(castOther.getIdUnico()));
	}


	@Override
	public boolean isFieldAuditable(String fieldname) {
		return true;
	}

}

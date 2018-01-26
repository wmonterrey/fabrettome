package ni.org.fabretto.me.domain.capacitacion;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import ni.org.fabretto.me.domain.BaseMetaData;
import ni.org.fabretto.me.domain.audit.Auditable;

/**
 * Sesion es la clase que representa a una sesion disponible para un curso en una capacitacion<br><br>
 * Nombre de la tabla<br>
 * Table(name = "catSesion", catalog = "fabrettome", uniqueConstraints={UniqueConstraint(columnNames = {"nombreSesion"})})<br><br>
 *
 *  
 * @author      William Avilés
 * @version     1.0
 * @since       1.0
 */
@Entity
@Table(name = "catSesion", catalog = "fabrettome", uniqueConstraints={@UniqueConstraint(columnNames = {"nombreSesion"})})
public class Sesion extends BaseMetaData implements Auditable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String idUnico;
	private String nombreSesion;
	private String objetivosSesion;
	private String duracionHoras;
	private String estado;
	
	public Sesion() {
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
	
	/** Columna = "nombreSesion", nullable = false, length = 50.
	 * @return nombreSesion Nombre de la Sesion*/
	@Column(name = "nombreSesion", nullable = false, length = 50)
	public String getNombreSesion() {
		return nombreSesion;
	}

	public void setNombreSesion(String nombreSesion) {
		this.nombreSesion = nombreSesion;
	}
	
	/** Columna = "objetivosSesion", nullable = false, length = 100.
	 * @return objetivosSesion Objetivos de la Sesion*/
	@Column(name = "objetivosSesion", nullable = false, length = 100)
	public String getObjetivosSesion() {
		return objetivosSesion;
	}

	public void setObjetivosSesion(String objetivosSesion) {
		this.objetivosSesion = objetivosSesion;
	}

	/** Columna = "duracionHoras", nullable = false
	 * @return duracionHoras Nombre de la Sesion*/
	@Column(name = "duracionHoras", nullable = false)
	public String getDuracionHoras() {
		return duracionHoras;
	}

	public void setDuracionHoras(String duracionHoras) {
		this.duracionHoras = duracionHoras;
	}

	/** Columna = "estado", nullable = false, length = 2
	 * @return estado Estado de la sesion*/
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
		if (!(other instanceof Sesion))
			return false;
		
		Sesion castOther = (Sesion) other;

		return (this.getIdUnico().equals(castOther.getIdUnico()));
	}


	@Override
	public boolean isFieldAuditable(String fieldname) {
		return true;
	}

}

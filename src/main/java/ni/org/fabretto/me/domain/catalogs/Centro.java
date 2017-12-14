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
 * Centro es la clase que representa a una centro u oratorio en el sistema.<br><br>
 * Nombre de la tabla<br>
 * Table(name = "catCentro", catalog = "fabrettome")<br><br>
 * 
 *  
 * @author      William Avil�s
 * @version     1.0
 * @since       1.0
 */
@Entity
@Table(name = "catCentro", catalog = "fabrettome")
public class Centro extends BaseMetaData implements Auditable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String idUnico;
	private String nombreCentro;
	private String direccion;
	private String telefono;
	private String director;
	private String codigo;
	private Comunidad comunidad;
	
	public Centro() {
		super();
	}

	
	@Id
	@Column(name = "idUnico", nullable = false, length = 50)
	/** Columna = "idUnico", nullable = false, length = 50.
	 * @return idUnico - Identificador �nico del registro en el sistema, generado autom�ticamente.*/
	public String getIdUnico() {
		return idUnico;
	}
	public void setIdUnico(String idUnico) {
		this.idUnico = idUnico;
	}

	@Column(name = "nombreCentro", nullable = false, length = 100)
	/** Columna = "nombreCentro", nullable = false, length = 100.
	 * @return nombreCentro - Nombre del Centro Oratorio.*/
	public String getNombreCentro() {
		return nombreCentro;
	}
	public void setNombreCentro(String nombreCentro) {
		this.nombreCentro = nombreCentro;
	}

	@Column(name = "direccion", nullable = false, length = 500)
	/** Columna = "direccion", nullable = false, length = 500.
	 * @return direccion - Direccion Exacta donde esta ubicada el centro oratorio.*/
	public String getDireccion() {
		return direccion;
	}
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	@Column(name = "telefono", nullable = true, length = 15)
	/** Columna = "telefono", nullable = true, length = 15.
	 * @return telefono - Numero de telefono del Oratorio.*/
	public String getTelefono() {
		return telefono;
	}
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	@Column(name = "director", nullable = true, length = 100)
	/** Columna = "director", nullable = true, length = 100.
	 * @return director - Director a cargo del Centro Oratorio.*/
	public String getDirector() {
		return director;
	}
	public void setDirector(String director) {
		this.director = director;
	}

	@Column(name = "codigo", nullable = false, length = 100)
	/** Columna = "codigo", nullable = false, length = 100.
	 * @return codigo - Codigo de fabretto .*/
	public String getCodigo() {
		return codigo;
	}
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	
	@ManyToOne(optional=false)
	@JoinColumn(name="idComunidad")
	@ForeignKey(name = "fkComunidadCentro")
	/** Columna = "idComunidad", nullable = false, length = 50.
	 * @return comunidad Comunidad donde se encuentra el centro*/
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
		if (!(other instanceof Centro))
			return false;
		
		Centro castOther = (Centro) other;

		return (this.getIdUnico().equals(castOther.getIdUnico()));
	}


	@Override
	public boolean isFieldAuditable(String fieldname) {
		return true;
	}

}

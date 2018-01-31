package ni.org.fabretto.me.domain.catalogs;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.hibernate.annotations.ForeignKey;

import ni.org.fabretto.me.domain.BaseMetaData;
import ni.org.fabretto.me.domain.Colaborador;
import ni.org.fabretto.me.domain.audit.Auditable;

/**
 * Centro es la clase que representa a una centro u oratorio en el sistema.<br><br>
 * Nombre de la tabla<br>
 * Table(name = "catCentro", catalog = "fabrettome")<br><br>
 *
 * Centro se relaciona con:
 * 
 * <ul>
 * <li>Catálogo comunidades
 * <li>Catálogo Colaboradores
 * </ul>
 *  
 * @author      William Avilés
 * @version     1.0
 * @since       1.0
 */
@Entity
@Table(name = "catCentro", catalog = "fabrettome", uniqueConstraints={@UniqueConstraint(columnNames = {"codigo","pasivo"})})
public class Centro extends BaseMetaData implements Auditable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String idUnico;
	private String nombreCentro;
	private String direccion;
	private String telefono;
	private Colaborador director;
	private String codigo;
	private Comunidad comunidad;
	
	public Centro() {
		super();
	}

	/** Columna = "idUnico", nullable = false, length = 50.
	 * @return idUnico - Identificador único del registro en el sistema, generado automáticamente.*/
	@Id
	@Column(name = "idUnico", nullable = false, length = 50)
	public String getIdUnico() {
		return idUnico;
	}
	public void setIdUnico(String idUnico) {
		this.idUnico = idUnico;
	}

	/** Columna = "nombreCentro", nullable = false, length = 100.
	 * @return nombreCentro - Nombre del Centro Oratorio.*/
	@Column(name = "nombreCentro", nullable = false, length = 100)
	public String getNombreCentro() {
		return nombreCentro;
	}
	public void setNombreCentro(String nombreCentro) {
		this.nombreCentro = nombreCentro;
	}

	/** Columna = "direccion", nullable = false, length = 500.
	 * @return direccion - Direccion Exacta donde esta ubicada el centro oratorio.*/
	@Column(name = "direccion", nullable = false, length = 500)
	public String getDireccion() {
		return direccion;
	}
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	/** Columna = "telefono", nullable = true, length = 15.
	 * @return telefono - Numero de telefono del Oratorio.*/
	@Column(name = "telefono", nullable = true, length = 15)
	public String getTelefono() {
		return telefono;
	}
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	/** Columna = "director", nullable = true, length = 50.
	 * @return director - Director a cargo del Centro Oratorio.*/
	@ManyToOne(optional=true)
	@JoinColumn(name="director")
	@ForeignKey(name = "fkColaboradorDirector")
	public Colaborador getDirector() {
		return director;
	}
	public void setDirector(Colaborador director) {
		this.director = director;
	}

	/** Columna = "codigo", nullable = false, length = 100.
	 * @return codigo - Codigo de fabretto .*/
	@Column(name = "codigo", nullable = false, length = 100)
	public String getCodigo() {
		return codigo;
	}
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	
	/** Columna = "idComunidad", nullable = false, length = 50.
	 * @return comunidad Comunidad donde se encuentra el centro*/
	@ManyToOne(optional=false)
	@JoinColumn(name="idComunidad")
	@ForeignKey(name = "fkComunidadCentro")
	public Comunidad getComunidad() {
		return comunidad;
	}
	public void setComunidad(Comunidad comunidad) {
		this.comunidad = comunidad;
	}
	
	
	@Override
	public String toString(){
		return this.nombreCentro;
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

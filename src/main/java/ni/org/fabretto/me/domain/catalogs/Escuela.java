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
 * Escuela se relaciona con:
 * 
 * <ul>
 * <li>Catálogo comunidades
 * <li>Catálogo centros
 * </ul>
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
	private String tipoEscuela;
	private String catEscuela;
	private String telefono;
	private Centro centro;
	private String codigo;
	private Comunidad comunidad;
	
	public Escuela() {
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
	
	/** Columna = "nombreEscuela", nullable = false, length = 100.
	 * @return nombreEscuela Nombre de la escuela.*/
	@Column(name = "nombreEscuela", nullable = false, length = 100)
	public String getNombreEscuela() {
		return nombreEscuela;
	}
	public void setNombreEscuela(String nombreEscuela) {
		this.nombreEscuela = nombreEscuela;
	}
	
	/** Columna = "tipoEscuela", nullable = false, length = 2.
	 * @return tipoEscuela Tipo de escuela. Publica o Privada*/
	@Column(name = "tipoEscuela", nullable = false, length = 2)
	public String getTipoEscuela() {
		return tipoEscuela;
	}
	public void setTipoEscuela(String tipoEscuela) {
		this.tipoEscuela = tipoEscuela;
	}
	
	/** Columna = "catEscuela", nullable = false, length = 2.
	 * @return catEscuela Categoria de escuela. A B o C*/
	@Column(name = "catEscuela", nullable = false, length = 2)
	public String getCatEscuela() {
		return catEscuela;
	}
	public void setCatEscuela(String catEscuela) {
		this.catEscuela = catEscuela;
	}


	/** Columna = "telefono", nullable = true, length = 15.
	 * @return telefono - Numero de telefono de la escuela.*/
	@Column(name = "telefono", nullable = true, length = 15)
	public String getTelefono() {
		return telefono;
	}
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
	
	/** Columna = "codigo", nullable = false, length = 100.
	 * @return codigo - Codigo de escuela .*/
	@Column(name = "codigo", nullable = false, length = 100)
	public String getCodigo() {
		return codigo;
	}
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	
	/** Columna = "idComunidad", nullable = false, length = 50.
	 * @return comunidad Comunidad donde esta ubicada la escuela.*/
	@ManyToOne(optional=false)
	@JoinColumn(name="idComunidad")
	@ForeignKey(name = "fkComunidadEscuela")
	public Comunidad getComunidad() {
		return comunidad;
	}
	public void setComunidad(Comunidad comunidad) {
		this.comunidad = comunidad;
	}
	
	/** Columna = "idCentro", nullable = false, length = 50.
	 * @return centro Codigo del centro u Oratorio donde esta la escuela .*/
	@ManyToOne(optional=false)
	@JoinColumn(name="idCentro")
	@ForeignKey(name = "fkCentroEscuela")
	public Centro getCentro() {
		return centro;
	}
	public void setCentro(Centro centro) {
		this.centro = centro;
	}

	@Override
	public String toString(){
		return this.nombreEscuela;
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

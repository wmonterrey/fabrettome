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
 * Comunidad se relaciona con:
 * 
 * <ul>
 * <li>Cat�logo municipios
 * </ul>
 *  
 * @author      William Avil�s
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
	private String descComunidad;
	private Municipio municipio;
	private Centro centro;
	
	public Comunidad() {
		super();
	}


	/** Columna = "idUnico", nullable = false, length = 50.
	 * @return idUnico Identificador �nico del registro en el sistema, generado autom�ticamente.*/
	@Id
	@Column(name = "idUnico", nullable = false, length = 50)
	public String getIdUnico() {
		return idUnico;
	}
	public void setIdUnico(String idUnico) {
		this.idUnico = idUnico;
	}

	/** Columna = "nombreComunidad", nullable = false, length = 300.
	 * @return nombreComunidad Nombre de la comunidad.*/
	@Column(name = "nombreComunidad", nullable = false, length = 300)
	public String getNombreComunidad() {
		return nombreComunidad;
	}
	public void setNombreComunidad(String nombreComunidad) {
		this.nombreComunidad = nombreComunidad;
	}

	/** Columna = "descComunidad", nullable = true, length = 500.
	 * @return descComunidad Descripci�n de la comunidad.*/
	@Column(name = "descComunidad", nullable = true, length = 500)
	public String getDescComunidad() {
		return descComunidad;
	}


	public void setDescComunidad(String descComunidad) {
		this.descComunidad = descComunidad;
	}

	/** Columna = "idMunicipio", nullable = true, length = 50.
	 * @return municipio Municipio donde se encuentra la comunidad.*/
	@ManyToOne(optional=false)
	@JoinColumn(name="idMunicipio")
	@ForeignKey(name = "fkMunicipioComunidad")
	public Municipio getMunicipio() {
		return municipio;
	}
	public void setMunicipio(Municipio municipio) {
		this.municipio = municipio;
	}
	
	/** Columna = "idCentro", nullable = false, length = 50.
	 * @return centro Codigo del centro u Oratorio que atiende esta comunidad.*/
	@ManyToOne(optional=false)
	@JoinColumn(name="idCentro")
	@ForeignKey(name = "fkCentroComunidad")
	public Centro getCentro() {
		return centro;
	}
	public void setCentro(Centro centro) {
		this.centro = centro;
	}

	@Override
	public String toString(){
		return this.nombreComunidad;
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

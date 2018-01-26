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
 * Municipio es la clase que representa a un Municipio en el sistema.<br><br>
 * Nombre de la tabla<br>
 * Table(name = "catMunicipio", catalog = "fabrettome")<br><br>
 * 
 * Municipio se relaciona con:
 * 
 * <ul>
 * <li>Catálogo departamentos
 * </ul>
 *  
 * @author      William Avilés
 * @version     1.0
 * @since       1.0
 */
@Entity
@Table(name = "catMunicipio", catalog = "fabrettome")
public class Municipio extends BaseMetaData implements Auditable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String idUnico;
	private String nombreMunicipio;
	private Departamento departamento;
	
	public Municipio() {
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
	
	@Column(name = "nombreMunicipio", nullable = false, length = 300)
	/** Columna = "nombreMunicipio", nullable = false, length = 300.
	 * @return nombreMunicipio Nombre del municipio.*/
	public String getNombreMunicipio() {
		return nombreMunicipio;
	}
	public void setNombreMunicipio(String nombreMunicipio) {
		this.nombreMunicipio = nombreMunicipio;
	}

	@ManyToOne(optional=false)
	@JoinColumn(name="idDepartamento")
	@ForeignKey(name = "fkDepartamentoMunicipio")
	public Departamento getDepartamento() {
		return departamento;
	}
	public void setDepartamento(Departamento departamento) {
		this.departamento = departamento;
	}

	@Override
	public String toString(){
		return this.nombreMunicipio;
	}

	@Override
	public boolean equals(Object other) {
		
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof Municipio))
			return false;
		
		Municipio castOther = (Municipio) other;

		return (this.getIdUnico().equals(castOther.getIdUnico()));
	}


	@Override
	public boolean isFieldAuditable(String fieldname) {
		return true;
	}

}

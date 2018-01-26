package ni.org.fabretto.me.domain.catalogs;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import ni.org.fabretto.me.domain.BaseMetaData;
import ni.org.fabretto.me.domain.audit.Auditable;

/**
 * Departamento es la clase que representa a una Departamento en el sistema.<br><br>
 * Nombre de la tabla<br>
 * Table(name = "catDepartamento", catalog = "fabrettome")<br><br>
 * 
 *  
 * @author      William Avilés
 * @version     1.0
 * @since       1.0
 */
@Entity
@Table(name = "catDepartamento", catalog = "fabrettome", uniqueConstraints={@UniqueConstraint(columnNames = {"nombreDepartamento"})})
public class Departamento extends BaseMetaData implements Auditable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String idUnico;
	private String nombreDepartamento;
	
	public Departamento() {
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

	/** Columna = "nombreDepartamento", nullable = false, length = 100.
	 *@return nombreDepartamento Nombre del departamento del país.*/
	@Column(name = "nombreDepartamento", nullable = false, length = 100)
	public String getNombreDepartamento() {
		return nombreDepartamento;
	}
	public void setNombreDepartamento(String nombreDepartamento) {
		this.nombreDepartamento = nombreDepartamento;
	}
	
	@Override
	public String toString(){
		return this.nombreDepartamento;
	}


	@Override
	public boolean equals(Object other) {
		
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof Departamento))
			return false;
		
		Departamento castOther = (Departamento) other;

		return (this.getIdUnico().equals(castOther.getIdUnico()));
	}


	@Override
	public boolean isFieldAuditable(String fieldname) {
		return true;
	}

}

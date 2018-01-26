package ni.org.fabretto.me.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.ForeignKey;

import ni.org.fabretto.me.domain.audit.Auditable;

/**
 * Colaborador es la clase que representa a un Colaborador en el sistema.<br><br>
 * Nombre de la tabla<br>
 * Table(name = "tblColaborador", catalog = "fabrettome")<br><br>
 * 
 * Colaborador se relaciona con:
 * 
 * <ul>
 * <li>Persona
 * </ul>
 * 
 *  
 * @author      William Avilés
 * @version     1.0
 * @since       1.0
 */
@Entity
@Table(name = "tblColaborador", catalog = "fabrettome")
public class Colaborador extends BaseMetaData implements Auditable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String idUnico;
	private Persona persona;
	private String cargo;
	private String estadoCivil;
	private String numSeguro;
	private Date fechaIngreso;
	private Date fechaEgreso;
	private String razonEgreso;
	private String estado;
	
	
	public Colaborador() {
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

	/** Columna = "idPersona", nullable = false, length = 50.
	 * @return persona - Persona que es el colaborador*/
	@OneToOne(optional=false)
	@JoinColumn(name="idPersona")
	@ForeignKey(name = "fkPersonaColaborador")
	public Persona getPersona() {
		return persona;
	}
	public void setPersona(Persona persona) {
		this.persona = persona;
	}
	
	/** Column(name = "cargo", nullable = false, length = 2)
	 * @return cargo Cargo del colaborador*/
	@Column(name = "cargo", nullable = false, length = 2)
	public String getCargo() {
		return cargo;
	}

	public void setCargo(String cargo) {
		this.cargo = cargo;
	}
	
	/** Column(name = "estadoCivil", nullable = false, length = 2)
	 * @return estadoCivil Estado Civil del Colaborador */
	@Column(name = "estadoCivil", nullable = false, length = 2)
	public String getEstadoCivil() {
		return estadoCivil;
	}

	public void setEstadoCivil(String estadoCivil) {
		this.estadoCivil = estadoCivil;
	}

	/** Column(name = "numSeguro", nullable = false, length = 25)
	 * @return numSeguro Numero de Seguro del Colaborador */
	@Column(name = "numSeguro", nullable = true, length = 25)
	public String getNumSeguro() {
		return numSeguro;
	}

	public void setNumSeguro(String numSeguro) {
		this.numSeguro = numSeguro;
	}

	/** Column(name = "fechaIngreso", nullable = false).
	 * @return fechaIngreso Fecha de ingreso*/
	@Column(name = "fechaIngreso", nullable = false)
	public Date getFechaIngreso() {
		return fechaIngreso;
	}

	public void setFechaIngreso(Date fechaIngreso) {
		this.fechaIngreso = fechaIngreso;
	}

	/** Column(name = "fechaEgreso", nullable = true).
	 * @return fechaEgreso Fecha de egreso*/
	@Column(name = "fechaEgreso", nullable = true)
	public Date getFechaEgreso() {
		return fechaEgreso;
	}

	public void setFechaEgreso(Date fechaEgreso) {
		this.fechaEgreso = fechaEgreso;
	}

	/** Column(name = "razonEgreso", nullable = true, length = 250)
	 * @return razonEgreso Razon por la que se retira*/
	@Column(name = "razonEgreso", nullable = true, length = 250)
	public String getRazonEgreso() {
		return razonEgreso;
	}
	
	public void setRazonEgreso(String razonEgreso) {
		this.razonEgreso = razonEgreso;
	}

	/** Column(name = "estado", nullable = false, length = 1)
	 * @return Estado actual del colaborador.*/
	@Column(name = "estado", nullable = false, length = 1)
	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	@Override
	public boolean isFieldAuditable(String fieldname) {
		return true;
	}

	@Override
	public String toString(){
		return persona.getTipoIdent() +": "+persona.getIdent();
	}
	
	@Override
	public boolean equals(Object other) {
		
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof Colaborador))
			return false;
		
		Colaborador castOther = (Colaborador) other;

		return (this.getIdUnico().equals(castOther.getIdUnico()));
	}

}

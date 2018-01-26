package ni.org.fabretto.me.domain.academico;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.hibernate.annotations.ForeignKey;

import ni.org.fabretto.me.domain.BaseMetaData;
import ni.org.fabretto.me.domain.audit.Auditable;

/**
 * Apadrinamiento es la clase que representa el financiamiento de un estudiante en un curso escolar en el sistema.<br><br>
 * Nombre de la tabla<br>
 * Table(name = "tblApadrinamiento", catalog = "fabrettome", uniqueConstraints={UniqueConstraint(columnNames = {"idMatricula"})})<br><br>
 * 
 * Apadrinamiento se relaciona con:
 * 
 * <ul>
 * <li>Matricula
 * </ul>
 * 
 *  
 * @author      William Avilés
 * @version     1.0
 * @since       1.0
 */
@Entity
@Table(name = "tblApadrinamiento", catalog = "fabrettome", uniqueConstraints={@UniqueConstraint(columnNames = {"idMatricula"})})
public class Apadrinamiento extends BaseMetaData implements Auditable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String idUnico;
	private Matricula matricula;
	private String tipo;
	private String fuente;
	private Date fechaIngreso;
	private Date fechaEgreso;
	private String estado;
	
	
	
	
	
	public Apadrinamiento() {
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

	/** Columna = "idMatricula", nullable = false, length = 50.
	 * @return idMatricula Estudiante y curso escolar de esta medición*/
	@ManyToOne(optional=false)
	@JoinColumn(name="idMatricula")
	@ForeignKey(name = "fkMatriculaApadrinamiento")
	public Matricula getMatricula() {
		return matricula;
	}
	public void setMatricula(Matricula cursoEscolar) {
		this.matricula = cursoEscolar;
	}
	
	
	/** Columna = "tipo", nullable = false, length = 2.
	 * @return tipo Tipo de apadrinamiento, apadrinado o becado*/
	@Column(name = "tipo", nullable = false, length = 2)
	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	/** Columna = "fuente", nullable = false, length = 50.
	 * @return fuente Fuente de apadrinamiento*/
	@Column(name = "fuente", nullable = false, length = 50)
	public String getFuente() {
		return fuente;
	}

	public void setFuente(String fuente) {
		this.fuente = fuente;
	}

	/** Columna = "fechaIngreso", nullable = false
	 * @return fechaIngreso Fecha de inicio del apadrinamiento*/
	@Column(name = "fechaIngreso", nullable = false)
	public Date getFechaIngreso() {
		return fechaIngreso;
	}

	public void setFechaIngreso(Date fechaIngreso) {
		this.fechaIngreso = fechaIngreso;
	}

	/** Columna = "fechaEgreso", nullable = false
	 * @return fechaEgreso Fecha de fin del apadrinamiento*/
	@Column(name = "fechaEgreso", nullable = true)
	public Date getFechaEgreso() {
		return fechaEgreso;
	}

	public void setFechaEgreso(Date fechaEgreso) {
		this.fechaEgreso = fechaEgreso;
	}
	
	/** Columna = "estado", nullable = false, length = 1.
	 * @return estado estado actual del apadrinamiento*/
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
		return this.idUnico;
	}
	
	@Override
	public boolean equals(Object other) {
		
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof Apadrinamiento))
			return false;
		
		Apadrinamiento castOther = (Apadrinamiento) other;

		return (this.getIdUnico().equals(castOther.getIdUnico()));
	}

}

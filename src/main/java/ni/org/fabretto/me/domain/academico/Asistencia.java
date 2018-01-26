package ni.org.fabretto.me.domain.academico;

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
 * Asistencia es la clase que representa el registro de la asistencia de los estudiantes a reforzamiento en un curso escolar en el sistema por semestre.<br><br>
 * Nombre de la tabla<br>
 * Table(name = "tblRegistroNota", catalog = "fabrettome", uniqueConstraints={@UniqueConstraint(columnNames = {"numEvaluacion","idMatricula"})})<br><br>
 * 
 * Asistencia se relaciona con:
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
@Table(name = "tblAsistencia", catalog = "fabrettome", uniqueConstraints={@UniqueConstraint(columnNames = {"numEvaluacion","idMatricula"})})
public class Asistencia extends BaseMetaData implements Auditable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String idUnico;
	private Matricula matricula;
	private String mes;
	private Integer diasTotalesClase;
	private Integer diasAsisteClase;
	private Integer diasTotalesAlim;
	private Integer diasAsisteAlim;
	
	
	public Asistencia() {
		super();
	}
	
	/** Columna = "idUnico",  = false, length = 50.
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
	 * @return idMatricula Estudiante y curso escolar de esta nota*/
	@ManyToOne(optional=false)
	@JoinColumn(name="idMatricula")
	@ForeignKey(name = "fkMatriculaRegistroNota")
	public Matricula getMatricula() {
		return matricula;
	}
	public void setMatricula(Matricula cursoEscolar) {
		this.matricula = cursoEscolar;
	}
	
	/** Columna = "mes",  = false, length = 2.
	 * @return mes Mes que se toma la asistencia*/
	@Column(name = "mes", nullable = false, length = 2)
	public String getMes() {
		return mes;
	}

	public void setMes(String mes) {
		this.mes = mes;
	}

	/** Columna = "diasTotalesClase",  = false
	 * @return diasTotalesClase Total de dias en el Mes que hay clases*/
	@Column(name = "diasTotalesClase", nullable = false)
	public Integer getDiasTotalesClase() {
		return diasTotalesClase;
	}

	public void setDiasTotalesClase(Integer diasTotalesClase) {
		this.diasTotalesClase = diasTotalesClase;
	}

	/** Columna = "diasAsisteClase",  = false
	 * @return diasAsisteClase Total de dias en el Mes que asiste el estudiante*/
	@Column(name = "diasAsisteClase", nullable = false)
	public Integer getDiasAsisteClase() {
		return diasAsisteClase;
	}

	public void setDiasAsisteClase(Integer diasAsisteClase) {
		this.diasAsisteClase = diasAsisteClase;
	}

	/** Columna = "diasTotalesAlim",  = false
	 * @return diasTotalesAlim Total de dias en el Mes que hay alimentacion*/
	@Column(name = "diasTotalesAlim", nullable = false)
	public Integer getDiasTotalesAlim() {
		return diasTotalesAlim;
	}

	public void setDiasTotalesAlim(Integer diasTotalesAlim) {
		this.diasTotalesAlim = diasTotalesAlim;
	}

	/** Columna = "diasAsisteAlim",  = false
	 * @return diasAsisteAlim Total de dias en el Mes que asiste el estudiante a la alimentacion*/
	@Column(name = "diasAsisteAlim", nullable = false)
	public Integer getDiasAsisteAlim() {
		return diasAsisteAlim;
	}

	public void setDiasAsisteAlim(Integer diasAsisteAlim) {
		this.diasAsisteAlim = diasAsisteAlim;
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
		if (!(other instanceof Asistencia))
			return false;
		
		Asistencia castOther = (Asistencia) other;

		return (this.getIdUnico().equals(castOther.getIdUnico()));
	}

}

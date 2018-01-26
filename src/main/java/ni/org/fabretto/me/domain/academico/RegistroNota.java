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
 * RegistroNota es la clase que representa el registro de la nota de los estudiantes en un curso escolar en el sistema por semestre.<br><br>
 * Nombre de la tabla<br>
 * Table(name = "tblRegistroNota", catalog = "fabrettome", uniqueConstraints={UniqueConstraint(columnNames = {"numEvaluacion","idMatricula"})})<br><br>
 * 
 * RegistroNota se relaciona con:
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
@Table(name = "tblRegistroNota", catalog = "fabrettome", uniqueConstraints={@UniqueConstraint(columnNames = {"numEvaluacion","idMatricula"})})
public class RegistroNota extends BaseMetaData implements Auditable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String idUnico;
	private Matricula matricula;
	private String numEvaluacion;
	private Float notaEspanol;
	private Float notaMatematicas;
	private Float promAsignaturas;
	
	
	
	public RegistroNota() {
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
	
	/** Columna = "numEvaluacion", nullable = false, length = 2.
	 * @return numEvaluacion Numero de evaluación son dos en el año.*/
	@Column(name = "numEvaluacion", nullable = false, length = 2)
	public String getNumEvaluacion() {
		return numEvaluacion;
	}

	public void setNumEvaluacion(String numEvaluacion) {
		this.numEvaluacion = numEvaluacion;
	}

	/** Columna = "notaEspanol", nullable = false
	 * @return notaEspanol Nota de la asignatura español*/
	@Column(name = "notaEspanol", nullable = false)
	public Float getNotaEspanol() {
		return notaEspanol;
	}

	public void setNotaEspanol(Float notaEspanol) {
		this.notaEspanol = notaEspanol;
	}

	/** Columna = "notaMatematicas", nullable = false
	 * @return notaMatematicas Nota de la asignatura matematicas*/
	@Column(name = "notaMatematicas", nullable = false)
	public Float getNotaMatematicas() {
		return notaMatematicas;
	}

	public void setNotaMatematicas(Float notaMatematicas) {
		this.notaMatematicas = notaMatematicas;
	}

	/** Columna = "promAsignaturas", nullable = false
	 * @return promAsignaturas Promedio del resto de asignaturas*/
	@Column(name = "promAsignaturas", nullable = false)
	public Float getPromAsignaturas() {
		return promAsignaturas;
	}

	public void setPromAsignaturas(Float promAsignaturas) {
		this.promAsignaturas = promAsignaturas;
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
		if (!(other instanceof RegistroNota))
			return false;
		
		RegistroNota castOther = (RegistroNota) other;

		return (this.getIdUnico().equals(castOther.getIdUnico()));
	}

}

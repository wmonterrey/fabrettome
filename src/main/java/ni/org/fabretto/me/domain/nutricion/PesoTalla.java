package ni.org.fabretto.me.domain.nutricion;

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
import ni.org.fabretto.me.domain.academico.Matricula;
import ni.org.fabretto.me.domain.audit.Auditable;

/**
 * PesoTalla es la clase que representa el registro de la medicion de peso y talla de los estudiantes en un curso escolar en el sistema.<br><br>
 * Nombre de la tabla<br>
 * Table(name = "tblPesoTalla", catalog = "fabrettome", uniqueConstraints={UniqueConstraint(columnNames = {"numEvaluacion","idCursoEscolar","idEstudiante"})})<br><br>
 * 
 * PesoTalla se relaciona con:
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
@Table(name = "tblPesoTalla", catalog = "fabrettome", uniqueConstraints={@UniqueConstraint(columnNames = {"numEvaluacion","idMatricula"})})
public class PesoTalla extends BaseMetaData implements Auditable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String idUnico;
	private Matricula matricula;
	private String numEvaluacion;
	private Date fechaEvaluacion;
	private Float peso;
	private Float talla;
	private String tomaLongitud;
	private Float longitud;
	private String indCrecimiento1;
	private String indCrecimiento2;
	private String indCrecimiento3;
	private String indCrecimiento4;
	private String indCrecimiento5;
	
	
	
	
	
	public PesoTalla() {
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
	 * @return idMatricula Estudiante y curso escolar de esta medición*/
	@ManyToOne(optional=false)
	@JoinColumn(name="idMatricula")
	@ForeignKey(name = "fkMatriculaPesoTalla")
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

	/** Columna = "fechaEvaluacion", nullable = false.
	 * @return fechaEvaluacion Fecha de evaluación.*/
	@Column(name = "fechaEvaluacion", nullable = false)
	public Date getFechaEvaluacion() {
		return fechaEvaluacion;
	}

	public void setFechaEvaluacion(Date fechaEvaluacion) {
		this.fechaEvaluacion = fechaEvaluacion;
	}

	/** Columna = "peso", nullable = false.
	 * @return peso Peso del estudiante.*/
	@Column(name = "peso", nullable = false)
	public Float getPeso() {
		return peso;
	}

	public void setPeso(Float peso) {
		this.peso = peso;
	}

	/** Columna = "talla", nullable = false.
	 * @return talla Talla del estudiante*/
	@Column(name = "talla", nullable = false)
	public Float getTalla() {
		return talla;
	}

	public void setTalla(Float talla) {
		this.talla = talla;
	}
	
	
	/** Columna = "tomaLongitud", nullable = false, length = 2.
	 * @return tomaLongitud Indica si se toma longitud en menores de dos años. En caso No, se toma talla*/
	@Column(name = "tomaLongitud", nullable = false, length = 2)
	public String getTomaLongitud() {
		return tomaLongitud;
	}

	public void setTomaLongitud(String tomaLongitud) {
		this.tomaLongitud = tomaLongitud;
	}

	/** Columna = "longitud", nullable = false.
	 * @return longitud Longitud del estudiante para menores de 2 años*/
	@Column(name = "longitud", nullable = false)
	public Float getLongitud() {
		return longitud;
	}

	public void setLongitud(Float longitud) {
		this.longitud = longitud;
	}

	/** Columna = "indCrecimiento1", nullable = false, length = 50.
	 * @return indCrecimiento1 Indicador de crecimiento longitud/talla para la edad*/
	@Column(name = "indCrecimiento1", nullable = false, length = 50)
	public String getIndCrecimiento1() {
		return indCrecimiento1;
	}

	public void setIndCrecimiento1(String indCrecimiento1) {
		this.indCrecimiento1 = indCrecimiento1;
	}

	/** Columna = "indCrecimiento2", nullable = false, length = 50.
	 * @return indCrecimiento2 Indicador de crecimiento peso para la edad*/
	@Column(name = "indCrecimiento2", nullable = false, length = 50)
	public String getIndCrecimiento2() {
		return indCrecimiento2;
	}

	public void setIndCrecimiento2(String indCrecimiento2) {
		this.indCrecimiento2 = indCrecimiento2;
	}

	/** Columna = "indCrecimiento3", nullable = false, length = 50.
	 * @return indCrecimiento3 Indicador de crecimiento IMC para la edad*/
	@Column(name = "indCrecimiento3", nullable = false, length = 50)
	public String getIndCrecimiento3() {
		return indCrecimiento3;
	}

	public void setIndCrecimiento3(String indCrecimiento3) {
		this.indCrecimiento3 = indCrecimiento3;
	}

	/** Columna = "indCrecimiento4", nullable = false, length = 50.
	 * @return indCrecimiento4 Indicador de nutrición Peso para la edad personalizado*/
	@Column(name = "indCrecimiento4", nullable = false, length = 50)
	public String getIndCrecimiento4() {
		return indCrecimiento4;
	}

	public void setIndCrecimiento4(String indCrecimiento4) {
		this.indCrecimiento4 = indCrecimiento4;
	}

	/** Columna = "indCrecimiento5", nullable = false, length = 50.
	 * @return indCrecimiento5 Indicador de nutrición Talla para la edad personalizado*/
	@Column(name = "indCrecimiento5", nullable = false, length = 50)
	public String getIndCrecimiento5() {
		return indCrecimiento5;
	}

	public void setIndCrecimiento5(String indCrecimiento5) {
		this.indCrecimiento5 = indCrecimiento5;
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
		if (!(other instanceof PesoTalla))
			return false;
		
		PesoTalla castOther = (PesoTalla) other;

		return (this.getIdUnico().equals(castOther.getIdUnico()));
	}

}

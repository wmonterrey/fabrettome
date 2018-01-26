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
 * PruebaDiagnostica es la clase que representa el registro de la prueba diagnostica en los estudiantes en un curso escolar.<br><br>
 * Nombre de la tabla<br>
 * Table(name = "tblPruebaDiagnostica", catalog = "fabrettome", uniqueConstraints={UniqueConstraint(columnNames = {"tipoPrueba","fechaEvaluacion","idMatricula"})})<br><br>
 * 
 * PruebaDiagnostica se relaciona con:
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
@Table(name = "tblPruebaDiagnostica", catalog = "fabrettome", uniqueConstraints={@UniqueConstraint(columnNames = {"tipoPrueba","fechaEvaluacion","idMatricula"})})
public class PruebaDiagnostica extends BaseMetaData implements Auditable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String idUnico;
	private Matricula matricula;
	private Date fechaEvaluacion;
	private String nivel;
	private String tipoPrueba;
	private String categoria;
	private String pregunta;
	private String resultado;

	
	
	
	public PruebaDiagnostica() {
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
	@ForeignKey(name = "fkMatriculaPruebaDiagnostica")
	public Matricula getMatricula() {
		return matricula;
	}
	public void setMatricula(Matricula cursoEscolar) {
		this.matricula = cursoEscolar;
	}
	
	/** Columna = "fechaEvaluacion", nullable = false
	 * @return fechaEvaluacion Fecha de Evaluacion*/
	@Column(name = "fechaEvaluacion", nullable = false)
	public Date getFechaEvaluacion() {
		return fechaEvaluacion;
	}

	public void setFechaEvaluacion(Date fechaEvaluacion) {
		this.fechaEvaluacion = fechaEvaluacion;
	}
	
	
	/** Columna = "nivel", nullable = false, length = 5.
	 * @return nivel Nivel de la prueba diagnostica*/
	@Column(name = "nivel", nullable = false, length = 5)
	public String getNivel() {
		return nivel;
	}

	public void setNivel(String nivel) {
		this.nivel = nivel;
	}

	/** Columna = "tipoPrueba", nullable = false, length = 5.
	 * @return tipoPrueba Tipo de Prueba, lenguaje, matematicas*/
	@Column(name = "tipoPrueba", nullable = false, length = 5)
	public String getTipoPrueba() {
		return tipoPrueba;
	}

	public void setTipoPrueba(String tipoPrueba) {
		this.tipoPrueba = tipoPrueba;
	}

	/** Columna = "categoria", nullable = false, length = 5.
	 * @return categoria Categoría de la pregunta*/
	@Column(name = "categoria", nullable = false, length = 5)
	public String getCategoria() {
		return categoria;
	}

	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}

	/** Columna = "pregunta", nullable = false, length = 5.
	 * @return pregunta Categoría de la pregunta*/
	@Column(name = "pregunta", nullable = false, length = 5)
	public String getPregunta() {
		return pregunta;
	}

	public void setPregunta(String pregunta) {
		this.pregunta = pregunta;
	}

	/** Columna = "resultado", nullable = false, length = 5.
	 * @return resultado Resultado de la respuesta a la pregunta*/
	@Column(name = "resultado", nullable = false, length = 5)
	public String getResultado() {
		return resultado;
	}

	public void setResultado(String resultado) {
		this.resultado = resultado;
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
		if (!(other instanceof PruebaDiagnostica))
			return false;
		
		PruebaDiagnostica castOther = (PruebaDiagnostica) other;

		return (this.getIdUnico().equals(castOther.getIdUnico()));
	}

}

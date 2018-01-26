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
import ni.org.fabretto.me.domain.Docente;
import ni.org.fabretto.me.domain.Estudiante;
import ni.org.fabretto.me.domain.audit.Auditable;
import ni.org.fabretto.me.domain.catalogs.Centro;

/**
 * Matricula es la clase que representa la matricula de un estudiante en un curso escolar en el sistema.<br><br>
 * Nombre de la tabla<br>
 * Table(name = "tblMatricula", catalog = "fabrettome", uniqueConstraints={UniqueConstraint(columnNames = {"idEstudiante","idCursoEscolar"})})<br><br>
 * 
 * Matricula se relaciona con:
 * 
 * <ul>
 * <li>CursoEscolar
 * <li>Estudiante
 * </ul>
 * 
 *  
 * @author      William Avilés
 * @version     1.0
 * @since       1.0
 */
@Entity
@Table(name = "tblMatricula", catalog = "fabrettome", uniqueConstraints={@UniqueConstraint(columnNames = {"idEstudiante","idCursoEscolar"})})
public class Matricula extends BaseMetaData implements Auditable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String idUnico;
	private Date fechaMatricula;
	private CursoEscolar cursoEscolar;
	private Estudiante estudiante;
	private String estado;
	private String observaciones;
	private String motivoRetiro;
	private Date fechaRetiro;
	private String enriquecimientoEducativo;
	private Centro centroEnrEduc;
	private Docente docenteEnrEduc;
	
	
	
	
	public Matricula() {
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

	/** Column(name = "fechaMatricula", nullable = false)
	 * @return Fecha en que se realiza la matrícula*/
	@Column(name = "fechaMatricula", nullable = false)
	public Date getFechaMatricula() {
		return fechaMatricula;
	}

	public void setFechaMatricula(Date fechaMatricula) {
		this.fechaMatricula = fechaMatricula;
	}

	/** Columna = "idCursoEscolar", nullable = false, length = 50.
	 * @return cursoEscolar CursoEscolar al que pertenece esta matrícula*/
	@ManyToOne(optional=false)
	@JoinColumn(name="idCursoEscolar")
	@ForeignKey(name = "fkCursoEscolarMatricula")
	public CursoEscolar getCursoEscolar() {
		return cursoEscolar;
	}
	public void setCursoEscolar(CursoEscolar cursoEscolar) {
		this.cursoEscolar = cursoEscolar;
	}
	
	/** Columna = "idEstudiante", nullable = false, length = 50.
	 * @return estudiante Estudiante al que se le hace esta matrícula*/
	@ManyToOne(optional=false)
	@JoinColumn(name="idEstudiante")
	@ForeignKey(name = "fkEstudianteMatricula")
	public Estudiante getEstudiante() {
		return estudiante;
	}

	public void setEstudiante(Estudiante estudiante) {
		this.estudiante = estudiante;
	}
	
	
	/** Column(name = "estado", nullable = false, length = 1)
	 * @return estado Estado actual de esta matricula.*/
	@Column(name = "estado", nullable = false, length = 1)
	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}
	
	/** Column(name = "observaciones", nullable = true, length = 500)
	 * @return observaciones Observaciones sobre esta matricula.*/
	@Column(name = "observaciones", nullable = true, length = 500)
	public String getObservaciones() {
		return observaciones;
	}

	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}
	
	/** Column(name = "motivoRetiro", nullable = true, length = 2)
	 * @return motivoRetiro Motivo de retiro de esta matricula.*/
	@Column(name = "motivoRetiro", nullable = true, length = 2)
	public String getMotivoRetiro() {
		return motivoRetiro;
	}

	public void setMotivoRetiro(String motivoRetiro) {
		this.motivoRetiro = motivoRetiro;
	}

	
	/** Column(name = "fechaRetiro", nullable = true)
	 * @return fechaRetiro Fecha de retiro de esta matricula.*/
	@Column(name = "fechaRetiro", nullable = true)
	public Date getFechaRetiro() {
		return fechaRetiro;
	}

	public void setFechaRetiro(Date fechaRetiro) {
		this.fechaRetiro = fechaRetiro;
	}

	/** Column(name = "enriquecimientoEducativo", nullable = false, length = 2)
	 * @return enriquecimientoEducativo Si recibe este estudiante Enriquecimiento Educativo*/
	@Column(name = "enriquecimientoEducativo", nullable = true, length = 2)
	public String getEnriquecimientoEducativo() {
		return enriquecimientoEducativo;
	}

	public void setEnriquecimientoEducativo(String enriquecimientoEducativo) {
		this.enriquecimientoEducativo = enriquecimientoEducativo;
	}

	/** Columna = "idCentroEnrEduc", nullable = true, length = 50.
	 * @return centroEnrEduc Centro en el que el estudiante hace enriquecimiento educativo*/
	@ManyToOne(optional=true)
	@JoinColumn(name="idCentroEnrEduc")
	@ForeignKey(name = "fkCentroEnrEduMatricula")
	public Centro getCentroEnrEduc() {
		return centroEnrEduc;
	}

	public void setCentroEnrEduc(Centro centroEnrEduc) {
		this.centroEnrEduc = centroEnrEduc;
	}

	/** Columna = "idDocenteEnrEduc", nullable = true, length = 50.
	 * @return docenteEnrEduc Docente con el que el estudiante hace enriquecimiento educativo*/
	@ManyToOne(optional=true)
	@JoinColumn(name="idDocenteEnrEduc")
	@ForeignKey(name = "fkDocenteEnrEducMatricula")
	public Docente getDocenteEnrEduc() {
		return docenteEnrEduc;
	}

	public void setDocenteEnrEduc(Docente docenteEnrEduc) {
		this.docenteEnrEduc = docenteEnrEduc;
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
		if (!(other instanceof Matricula))
			return false;
		
		Matricula castOther = (Matricula) other;

		return (this.getIdUnico().equals(castOther.getIdUnico()));
	}

}

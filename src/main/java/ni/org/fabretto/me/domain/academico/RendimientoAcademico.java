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
 * RendimientoAcademico es la clase que representa el registro del rendimiento academico en un curso escolar en el sistema.<br><br>
 * Nombre de la tabla<br>
 * Table(name = "tblRendimientoAcademico", catalog = "fabrettome", uniqueConstraints={UniqueConstraint(columnNames = {"numEvaluacion","idCursoEscolar"})})<br><br>
 * 
 * RendimientoAcademico se relaciona con:
 * 
 * <ul>
 * <li>CursoEscolar
 * </ul>
 * 
 *  
 * @author      William Avilés
 * @version     1.0
 * @since       1.0
 */
@Entity
@Table(name = "tblRendimientoAcademico", catalog = "fabrettome", uniqueConstraints={@UniqueConstraint(columnNames = {"numEvaluacion","idCursoEscolar"})})
public class RendimientoAcademico extends BaseMetaData implements Auditable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String idUnico;
	private CursoEscolar cursoEscolar;
	private String numEvaluacion;
	private Integer matriculaInicialAS;
	private Integer matriculaInicialF;
	private Integer matriculaFinalAS;
	private Integer matriculaFinalF;
	private Integer aprobadosTodasAS;
	private Integer aprobadosTodasF;
	private Integer noAprobadosAS;
	private Integer noAprobadosF;	
	
	
	public RendimientoAcademico() {
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

	/** Columna = "idCursoEscolar", nullable = false, length = 50.
	 * @return cursoEscolar CursoEscolar al que pertenece este reporte*/
	@ManyToOne(optional=false)
	@JoinColumn(name="idCursoEscolar")
	@ForeignKey(name = "fkCursoEscolarRendimientoAcademico")
	public CursoEscolar getCursoEscolar() {
		return cursoEscolar;
	}
	public void setCursoEscolar(CursoEscolar cursoEscolar) {
		this.cursoEscolar = cursoEscolar;
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

	/** Columna = "matriculaInicialAS", nullable = false
	 * @return matriculaInicialAS matricula Inicial ambos sexos*/
	@Column(name = "matriculaInicialAS", nullable = false)
	public Integer getMatriculaInicialAS() {
		return matriculaInicialAS;
	}

	public void setMatriculaInicialAS(Integer matriculaInicialAS) {
		this.matriculaInicialAS = matriculaInicialAS;
	}
	
	/** Columna = "matriculaInicialF", nullable = false
	 * @return matriculaInicialF matricula Inicial femenino*/
	@Column(name = "matriculaInicialF", nullable = false)
	public Integer getMatriculaInicialF() {
		return matriculaInicialF;
	}

	public void setMatriculaInicialF(Integer matriculaInicialF) {
		this.matriculaInicialF = matriculaInicialF;
	}

	/** Columna = "matriculaFinalAS", nullable = false
	 * @return matriculaFinalAS matricula final ambos sexos*/
	@Column(name = "matriculaFinalAS", nullable = false)
	public Integer getMatriculaFinalAS() {
		return matriculaFinalAS;
	}

	public void setMatriculaFinalAS(Integer matriculaFinalAS) {
		this.matriculaFinalAS = matriculaFinalAS;
	}

	/** Columna = "matriculaFinalF", nullable = false
	 * @return matriculaFinalF matricula final femenino*/
	@Column(name = "matriculaFinalF", nullable = false)
	public Integer getMatriculaFinalF() {
		return matriculaFinalF;
	}

	public void setMatriculaFinalF(Integer matriculaFinalF) {
		this.matriculaFinalF = matriculaFinalF;
	}

	/** Columna = "aprobadosTodasAS", nullable = false
	 * @return aprobadosTodasAS Numero de aprobados ambos sexos */
	@Column(name = "aprobadosTodasAS", nullable = false)
	public Integer getAprobadosTodasAS() {
		return aprobadosTodasAS;
	}

	public void setAprobadosTodasAS(Integer aprobadosTodasAS) {
		this.aprobadosTodasAS = aprobadosTodasAS;
	}

	/** Columna = "aprobadosTodasF", nullable = false
	 * @return aprobadosTodasF Numero de aprobados femeninos*/
	@Column(name = "aprobadosTodasF", nullable = false)
	public Integer getAprobadosTodasF() {
		return aprobadosTodasF;
	}

	public void setAprobadosTodasF(Integer aprobadosTodasF) {
		this.aprobadosTodasF = aprobadosTodasF;
	}

	/** Columna = "noAprobadosAS", nullable = false
	 * @return noAprobadosAS Numero de reprobados ambos sexos*/
	@Column(name = "noAprobadosAS", nullable = false)
	public Integer getNoAprobadosAS() {
		return noAprobadosAS;
	}

	public void setNoAprobadosAS(Integer noAprobadosAS) {
		this.noAprobadosAS = noAprobadosAS;
	}

	/** Columna = "noAprobadosF", nullable = false
	 * @return noAprobadosF Numero de reprobados femeninos*/
	@Column(name = "noAprobadosF", nullable = false)
	public Integer getNoAprobadosF() {
		return noAprobadosF;
	}

	public void setNoAprobadosF(Integer noAprobadosF) {
		this.noAprobadosF = noAprobadosF;
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
		if (!(other instanceof RendimientoAcademico))
			return false;
		
		RendimientoAcademico castOther = (RendimientoAcademico) other;

		return (this.getIdUnico().equals(castOther.getIdUnico()));
	}

}

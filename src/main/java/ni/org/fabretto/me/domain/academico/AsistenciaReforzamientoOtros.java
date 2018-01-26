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
import ni.org.fabretto.me.domain.catalogs.Escuela;

/**
 * AsistenciaReforzamientoOtros es la clase que representa el registro de la asistencia de otras personas no estudiantes a reforzamiento en una escuela por mes.<br><br>
 * Nombre de la tabla<br>
 * Table(name = "tblAsistRefOtros", catalog = "fabrettome", uniqueConstraints={UniqueConstraint(columnNames = {"mes","idCursoEscolar"})})<br><br>
 * 
 * Asistencia se relaciona con:
 * 
 * <ul>
 * <li>Curso Escolar
 * </ul>
 * 
 *  
 * @author      William Avilés
 * @version     1.0
 * @since       1.0
 */
@Entity
@Table(name = "tblAsistRefOtros", catalog = "fabrettome", uniqueConstraints={@UniqueConstraint(columnNames = {"mes","periodo","idEscuela"})})
public class AsistenciaReforzamientoOtros extends BaseMetaData implements Auditable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String idUnico;
	private Escuela escuela;
	private String periodo;
	private String mes;
	private Integer diasHabilesMes;
	private Integer asistenciaPadres;
	private Integer asistenciaEduMined;
	private Integer asistenciaColaboradores;
	private Integer asistenciaVisitas;

	public AsistenciaReforzamientoOtros() {
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

	/** Columna = "idEscuela", nullable = false, length = 50.
	 * @return idEscuela Escuela donde se registra esta asistencia*/
	@ManyToOne(optional=false)
	@JoinColumn(name="idEscuela")
	@ForeignKey(name = "fkEscuelaAsistencia")
	public Escuela getEscuela() {
		return escuela;
	}
	public void setEscuela(Escuela escuela) {
		this.escuela = escuela;
	}
	
	/** Columna = "periodo",  = false, length = 4.
	 * @return periodo Año escolar al que se toma la asistencia*/
	@Column(name = "periodo", nullable = false, length = 4)
	public String getPeriodo() {
		return periodo;
	}

	public void setPeriodo(String periodo) {
		this.periodo = periodo;
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

	/** Columna = "diasHabilesMes",  = false
	 * @return diasHabilesMes Total de dias en el Mes que hay clases*/
	@Column(name = "diasHabilesMes", nullable = false)
	public Integer getDiasHabilesMes() {
		return diasHabilesMes;
	}

	public void setDiasHabilesMes(Integer diasTotalesClase) {
		this.diasHabilesMes = diasTotalesClase;
	}

	/** Columna = "asistenciaPadres",  = false
	 * @return asistenciaPadres Total de padres que asistieron en el mes*/
	@Column(name = "asistenciaPadres", nullable = false)
	public Integer getAsistenciaPadres() {
		return asistenciaPadres;
	}

	public void setAsistenciaPadres(Integer asistenciaPadres) {
		this.asistenciaPadres = asistenciaPadres;
	}

	/** Columna = "asistenciaEduMined",  = false
	 * @return asistenciaEduMined Total de docentes MINED que asistieron en el mes*/
	@Column(name = "asistenciaEduMined", nullable = false)
	public Integer getAsistenciaEduMined() {
		return asistenciaEduMined;
	}

	public void setAsistenciaEduMined(Integer asistenciaEduMined) {
		this.asistenciaEduMined = asistenciaEduMined;
	}

	/** Columna = "asistenciaColaboradores",  = false
	 * @return asistenciaColaboradores Total de colaboradores Fabretto que asistieron en el mes*/
	@Column(name = "asistenciaColaboradores", nullable = false)
	public Integer getAsistenciaColaboradores() {
		return asistenciaColaboradores;
	}

	public void setAsistenciaColaboradores(Integer asistenciaColaboradores) {
		this.asistenciaColaboradores = asistenciaColaboradores;
	}

	/** Columna = "asistenciaVisitas",  = false
	 * @return asistenciaVisitas Total de otras personas que asistieron en el mes*/
	@Column(name = "asistenciaVisitas", nullable = false)
	public Integer getAsistenciaVisitas() {
		return asistenciaVisitas;
	}

	public void setAsistenciaVisitas(Integer asistenciaVisitas) {
		this.asistenciaVisitas = asistenciaVisitas;
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
		if (!(other instanceof AsistenciaReforzamientoOtros))
			return false;
		
		AsistenciaReforzamientoOtros castOther = (AsistenciaReforzamientoOtros) other;

		return (this.getIdUnico().equals(castOther.getIdUnico()));
	}

}

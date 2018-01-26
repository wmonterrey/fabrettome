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
 * CursoEscolar es la clase que representa un curso escolar para una escuela, una modalidad, un turno, nivel y docente en un año en el sistema.<br><br>
 * Nombre de la tabla<br>
 * Table(name = "tblCursoEscolar", catalog = "fabrettome", uniqueConstraints={UniqueConstraint(columnNames = {"periodo","idEscuela","modalidad","turno","nivel"})})<br><br>
 * 
 * CursoEscolar se relaciona con:
 * 
 * <ul>
 * <li>Escuela
 * <li>Docente
 * </ul>
 * 
 *  
 * @author      William Avilés
 * @version     1.0
 * @since       1.0
 */
@Entity
@Table(name = "tblCursoEscolar", catalog = "fabrettome", uniqueConstraints={@UniqueConstraint(columnNames = {"periodo","idEscuela","modalidad","turno","nivel"})})
public class CursoEscolar extends BaseMetaData implements Auditable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String idUnico;
	private String periodo;
	private Escuela escuela;
	private String modalidad;
	private String turno;
	private String nivel;
	private String estado;
	
	
	public CursoEscolar() {
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

	/** Column(name = "periodo", nullable = false, length = 4)
	 * @return Año lectivo escolar*/
	@Column(name = "periodo", nullable = false, length = 4)
	public String getPeriodo() {
		return periodo;
	}

	public void setPeriodo(String periodo) {
		this.periodo = periodo;
	}
	
	/** Columna = "idEscuela", nullable = false, length = 50.
	 * @return escuela Escuela de este curso Escolar*/
	@ManyToOne(optional=false)
	@JoinColumn(name="idEscuela")
	@ForeignKey(name = "fkEscuelaCursoEscolar")
	public Escuela getEscuela() {
		return escuela;
	}
	public void setEscuela(Escuela escuela) {
		this.escuela = escuela;
	}
	
	/** Column(name = "modalidad", nullable = false, length = 2)
	 * @return Modalidad de Estudio*/
	@Column(name = "modalidad", nullable = false, length = 2)
	public String getModalidad() {
		return modalidad;
	}

	public void setModalidad(String modalidad) {
		this.modalidad = modalidad;
	}

	/** Column(name = "turno", nullable = false, length = 2)
	 * @return Turno que tendra la modalidad de estudio*/
	@Column(name = "turno", nullable = false, length = 2)
	public String getTurno() {
		return turno;
	}

	public void setTurno(String turno) {
		this.turno = turno;
	}
	
	/** Column(name = "nivel", nullable = false, length = 2)
	 * @return Nivel del curso escolar.*/
	@Column(name = "nivel", nullable = false, length = 2)
	public String getNivel() {
		return nivel;
	}

	public void setNivel(String nivel) {
		this.nivel = nivel;
	}

	/** Column(name = "estado", nullable = false, length = 1)
	 * @return Estado actual del curso escolar.*/
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
		if (!(other instanceof CursoEscolar))
			return false;
		
		CursoEscolar castOther = (CursoEscolar) other;

		return (this.getIdUnico().equals(castOther.getIdUnico()));
	}

}

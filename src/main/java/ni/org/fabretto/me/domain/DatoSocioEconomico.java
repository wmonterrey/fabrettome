package ni.org.fabretto.me.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.hibernate.annotations.ForeignKey;

import ni.org.fabretto.me.domain.audit.Auditable;

/**
 * DatosSocioEconomicos es la clase que representa el información acerca de la situación socioeconómica y familiar del niño<br><br>
 * Nombre de la tabla<br>
 * Table(name = "tblDatosSocioEconomicos", catalog = "fabrettome", uniqueConstraints={UniqueConstraint(columnNames = {"idMatricula"})})<br><br>
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
@Table(name = "tblDatosSocioEconomicos", catalog = "fabrettome", uniqueConstraints={@UniqueConstraint(columnNames = {"idEstudiante"})})
public class DatoSocioEconomico extends BaseMetaData implements Auditable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String idUnico;
	private Estudiante estudiante;
	private String situacionFamiliar;
	private String tipoIngreso;
	private String materialCasa;
	private String piso;
	private String techo;
	private Integer numPersonas;
	private Integer numHermanos;
	private String famCultiva;
	private Float areaCultivo;
	private String rubroCultivo;
	private String otroRubroCultivo;
	private String serviciosBasicos;
	private String fuentesAgua;
	
	
	
	
	
	public DatoSocioEconomico() {
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

	/** Columna = "idEstudiante", nullable = false, length = 50.
	 * @return estudiante - Estudiante al que pertenece la informacion*/
	@OneToOne(optional=false)
	@JoinColumn(name="idEstudiante")
	@ForeignKey(name = "fkEstudianteDatosSocioEconomicos")
	public Estudiante getEstudiante() {
		return estudiante;
	}

	public void setEstudiante(Estudiante estudiante) {
		this.estudiante = estudiante;
	}
	
	
	/** Columna = "situacionFamiliar", nullable = false, length = 2.
	 * @return situacionFamiliar Situacion Familiar del estudiante*/
	@Column(name = "situacionFamiliar", nullable = false, length = 2)
	public String getSituacionFamiliar() {
		return situacionFamiliar;
	}

	public void setSituacionFamiliar(String situacionFamiliar) {
		this.situacionFamiliar = situacionFamiliar;
	}

	/** Columna = "tipoIngreso", nullable = false, length = 2.
	 * @return tipoIngreso Tipo Ingreso de la familia del estudiante*/
	@Column(name = "tipoIngreso", nullable = false, length = 2)
	public String getTipoIngreso() {
		return tipoIngreso;
	}

	public void setTipoIngreso(String tipoIngreso) {
		this.tipoIngreso = tipoIngreso;
	}

	/** Columna = "materialCasa", nullable = false, length = 50.
	 * @return materialCasa Material de la Casa del estudiante*/
	@Column(name = "materialCasa", nullable = false, length = 50)
	public String getMaterialCasa() {
		return materialCasa;
	}

	public void setMaterialCasa(String materialCasa) {
		this.materialCasa = materialCasa;
	}

	/** Columna = "piso", nullable = false, length = 50.
	 * @return piso Piso de la Casa del estudiante*/
	@Column(name = "piso", nullable = false, length = 50)
	public String getPiso() {
		return piso;
	}

	public void setPiso(String piso) {
		this.piso = piso;
	}

	/** Columna = "techo", nullable = false, length = 50.
	 * @return techo Techo de la Casa del estudiante*/
	@Column(name = "techo", nullable = false, length = 50)
	public String getTecho() {
		return techo;
	}

	public void setTecho(String techo) {
		this.techo = techo;
	}

	/** Columna = "numPersonas", nullable = false
	 * @return numPersonas Numero de Personas en la Casa del estudiante*/
	@Column(name = "numPersonas", nullable = false)
	public Integer getNumPersonas() {
		return numPersonas;
	}

	public void setNumPersonas(Integer numPersonas) {
		this.numPersonas = numPersonas;
	}

	/** Columna = "numHermanos", nullable = false
	 * @return numHermanos numero de Hermanos del estudiante*/
	@Column(name = "numHermanos", nullable = false)
	public Integer getNumHermanos() {
		return numHermanos;
	}

	public void setNumHermanos(Integer numHermanos) {
		this.numHermanos = numHermanos;
	}

	/** Columna = "famCultiva", nullable = false, length = 1.
	 * @return famCultiva Cultivan la familia del estudiante*/
	@Column(name = "famCultiva", nullable = false, length = 1)
	public String getFamCultiva() {
		return famCultiva;
	}

	public void setFamCultiva(String famCultiva) {
		this.famCultiva = famCultiva;
	}

	/** Columna = "areaCultivo", nullable = true
	 * @return areaCultivo area del Cultivo en manzanas*/
	@Column(name = "areaCultivo", nullable = true)
	public Float getAreaCultivo() {
		return areaCultivo;
	}

	public void setAreaCultivo(Float areaCultivo) {
		this.areaCultivo = areaCultivo;
	}

	/** Columna = "rubroCultivo", nullable = false, length = 50.
	 * @return rubroCultivo Rubros de Cultivo de la familia del estudiante*/
	@Column(name = "rubroCultivo", nullable = false, length = 50)
	public String getRubroCultivo() {
		return rubroCultivo;
	}

	public void setRubroCultivo(String rubroCultivo) {
		this.rubroCultivo = rubroCultivo;
	}

	/** Columna = "otroRubroCultivo", nullable = false, length = 150.
	 * @return otroRubroCultivo Otro Rubro de Cultivo de la familia del estudiante*/
	@Column(name = "otroRubroCultivo", nullable = false, length = 150)
	public String getOtroRubroCultivo() {
		return otroRubroCultivo;
	}

	public void setOtroRubroCultivo(String otroRubroCultivo) {
		this.otroRubroCultivo = otroRubroCultivo;
	}

	/** Columna = "serviciosBasicos", nullable = false, length = 50.
	 * @return serviciosBasicos Servicios Basicos de la casa de la familia del estudiante*/
	@Column(name = "serviciosBasicos", nullable = false, length = 50)
	public String getServiciosBasicos() {
		return serviciosBasicos;
	}

	public void setServiciosBasicos(String serviciosBasicos) {
		this.serviciosBasicos = serviciosBasicos;
	}

	/** Columna = "fuentesAgua", nullable = false, length = 50.
	 * @return fuentesAgua Fuentes de Agua de la casa de la familia del estudiante*/
	@Column(name = "fuentesAgua", nullable = false, length = 50)
	public String getFuentesAgua() {
		return fuentesAgua;
	}

	public void setFuentesAgua(String fuentesAgua) {
		this.fuentesAgua = fuentesAgua;
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
		if (!(other instanceof DatoSocioEconomico))
			return false;
		
		DatoSocioEconomico castOther = (DatoSocioEconomico) other;

		return (this.getIdUnico().equals(castOther.getIdUnico()));
	}

}

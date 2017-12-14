package ni.org.fabretto.me.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.hibernate.annotations.ForeignKey;

import ni.org.fabretto.me.domain.audit.Auditable;
import ni.org.fabretto.me.domain.catalogs.Comunidad;

/**
 * Persona es la clase que representa a una persona en el sistema.<br><br>
 * Nombre de la tabla<br>
 * Table(name = "tblPersona", catalog = "fabrettome", uniqueConstraints={UniqueConstraint(columnNames = {"identificador","tipoIdentificador"})})<br><br>
 * 
 * Persona se relaciona con:
 * 
 * <ul>
 * <li>Catálogo tipo de identificador
 * <li>Catálogo sexo de la persona
 * <li>Catálogo comunidades
 * </ul>
 * 
 *  
 * @author      William Avilés
 * @version     1.0
 * @since       1.0
 */
@Entity
@Table(name = "tblPersona", catalog = "fabrettome", uniqueConstraints={@UniqueConstraint(columnNames = {"identificador","tipoIdentificador"})})
public class Persona extends BaseMetaData implements Auditable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String idUnico;
	private String ident;
	private String tipoIdent;
	private String primerNombre;
	private String segundoNombre;
	private String primerApellido;
	private String segundoApellido;
	private Date fechaNacimiento;
	private String sexo;
	private String direccion;
	private Comunidad comunidad;
	
	public Persona() {
		super();
	}

	
	@Id
	@Column(name = "idUnico", nullable = false, length = 50)
	/** Columna = "idUnico", nullable = false, length = 50.
	 * @return idUnico Identificador único del registro en el sistema, generado automáticamente.*/
	public String getIdUnico() {
		return idUnico;
	}
	public void setIdUnico(String idUnico) {
		this.idUnico = idUnico;
	}



	/** Columna = "identificador", nullable = false, length = 50.
	 * @return ident - Identificador único para la persona, definido en conjunto con el tipo de identificador.*/
    @Column(name = "identificador", nullable = false, length = 50)
	public String getIdent() {
		return ident;
	}
	public void setIdent(String ident) {
		this.ident = ident;
	}
	
	/** Columna = "tipoIdent", nullable = false, length = 15.
	 * @return tipoIdent - Tipo de identificador de la persona.*/
    @Column(name = "tipoIdentificador", nullable = false, length = 15)	
	public String getTipoIdent() {
		return tipoIdent;
	}
	public void setTipoIdent(String tipoIdent) {
		this.tipoIdent = tipoIdent;
	}
	
	/** Columna = "primerNombre", nullable = false, length = 50.
	 * @return primerNombre - Primer Nombre de la persona.*/
    @Column(name = "primerNombre", nullable = false, length = 50)	
	public String getPrimerNombre() {
		return primerNombre;
	}
	public void setPrimerNombre(String primerNombre) {
		this.primerNombre = primerNombre;
	}

	/** Columna = "segundoNombre", nullable = true, length = 50.
	 * @return segundoNombre - Segundo Nombre de la persona.*/
    @Column(name = "segundoNombre", nullable = true, length = 50)
	public String getSegundoNombre() {
		return segundoNombre;
	}
	public void setSegundoNombre(String segundoNombre) {
		this.segundoNombre = segundoNombre;
	}

	/** Columna = "primerApellido", nullable = false, length = 50.
	 * @return primerApellido - Primer Apellido de la persona.*/
    @Column(name = "primerApellido", nullable = false, length = 50)	
	public String getPrimerApellido() {
		return primerApellido;
	}
	public void setPrimerApellido(String primerApellido) {
		this.primerApellido = primerApellido;
	}

	/** Columna = "segundoApellido", nullable = true, length = 50.
	 * @return segundoApellido - Segundo Apellido de la persona.*/
    @Column(name = "segundoApellido", nullable = true, length = 50)
	public String getSegundoApellido() {
		return segundoApellido;
	}
	public void setSegundoApellido(String segundoApellido) {
		this.segundoApellido = segundoApellido;
	}

	/** Columna = "fechaNacimiento", nullable = false.
	 * @return fechaNacimiento - Fecha de Nacimiento de la persona.*/
    @Column(name = "fechaNacimiento", nullable = false)
	public Date getFechaNacimiento() {
		return fechaNacimiento;
	}
	public void setFechaNacimiento(Date fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}

	/** Columna = "sexo", nullable = false, length = 1.
	 * @return sexo - Sexo de la persona.*/
    @Column(name = "sexo", nullable = false, length = 1)	
	public String getSexo() {
		return sexo;
	}
	public void setSexo(String sexo) {
		this.sexo = sexo;
	}

	/** Columna = "direccion", nullable = false, length = 500.
	 * @return direccion - Direccion de la persona.*/
    @Column(name = "direccion", nullable = false, length = 500)	
	public String getDireccion() {
		return direccion;
	}
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	@ManyToOne(optional=false)
	@JoinColumn(name="idComunidad")
	@ForeignKey(name = "fkComunidadPersona")
	/** Columna = "comunidad", nullable = false, length = 50.
	 * @return comunidad - Comunidad de residencia de la persona.*/
	public Comunidad getComunidad() {
		return comunidad;
	}
	public void setComunidad(Comunidad comunidad) {
		this.comunidad = comunidad;
	}


	@Override
	public boolean isFieldAuditable(String fieldname) {
		return true;
	}

	@Override
	public String toString(){
		return tipoIdent +": "+ident;
	}
	
	@Override
	public boolean equals(Object other) {
		
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof Persona))
			return false;
		
		Persona castOther = (Persona) other;

		return (this.getIdUnico().equals(castOther.getIdUnico()));
	}

}

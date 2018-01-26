package ni.org.fabretto.me.users.model;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotBlank;

import ni.org.fabretto.me.domain.audit.Auditable;




/**
 * Simple objeto de dominio que representa un usuario
 * 
 * @author William Aviles
 **/

@Entity
@Table(name = "catUsuario", catalog = "fabrettome")
public class Usuario implements Auditable {
	private String nombreUsuario;
	private Date fechaCreacion;
	private Date fechaUltimaModificacion;
	private Date fechaUltimoAcceso;
	private String contrasena;
	private String nombreCompleto;
	private String correoElectronico;
	private Boolean habilitado=true;
	private Boolean cuentaSinExpirar=true;
	private Boolean credencialSinExpirar=true;
	private Date ultimoCambioCredencial;
	private Boolean cuentaSinBloquear=true;
	private String usuarioRegistro;
	private String usuarioModifica;
	
	@Id
	@Column(name = "nombreUsuario", nullable = false, length =50)
	@Size(min = 5, max = 50)
	@Pattern(regexp = "^[a-zA-Z0-9]+$")
	@NotBlank
	public String getNombreUsuario() {
		return nombreUsuario;
	}
	public void setnombreUsuario(String nombreUsuario) {
		this.nombreUsuario = nombreUsuario;
	}
	@Temporal( TemporalType.TIMESTAMP)
	@Column(name = "fechaCreacion", nullable = false)
	public Date getFechaCreacion() {
		return fechaCreacion;
	}
	public void setFechaCreacion(Date fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}
	@Temporal( TemporalType.TIMESTAMP)
	@Column(name = "fechaUltimaModificacion", nullable = true)
	public Date getModified() {
		return fechaUltimaModificacion;
	}
	public void setModified(Date modified) {
		this.fechaUltimaModificacion = modified;
	}
	@Temporal( TemporalType.TIMESTAMP)
	@Column(name = "fechaUltimoAcceso", nullable = true)
	public Date getFechaUltimoAcceso() {
		return fechaUltimoAcceso;
	}
	public void setFechaUltimoAcceso(Date fechaUltimoAcceso) {
		this.fechaUltimoAcceso = fechaUltimoAcceso;
	}
	@Temporal( TemporalType.TIMESTAMP)
	@Column(name = "ultimoCambioCredencial", nullable = true)
	public Date getUltimoCambioCredencial() {
		return ultimoCambioCredencial;
	}
	public void setUltimoCambioCredencial(Date ultimoCambioCredencial) {
		this.ultimoCambioCredencial = ultimoCambioCredencial;
	}
	@Column(name = "contrasena", nullable = false, length =150)
	@Size(min = 8, max = 150)
	@Pattern(regexp = "^[a-zA-Z0-9!@#$%^&*()?/]+$")
	@NotBlank
	public String getContrasena() {
		return contrasena;
	}
	public void setContrasena(String contrasena) {
		this.contrasena = contrasena;
	}
	@Column(name = "nombreCompleto", nullable = true, length =250)
	@Size(max = 250)
	public String getNombreCompleto() {
		return nombreCompleto;
	}
	public void setNombreCompleto(String nombreCompleto) {
		this.nombreCompleto = nombreCompleto;
	}
	@Column(name = "correoElectronico", nullable = true, length =100)
	@Size(max = 100)
	public String getCorreoElectronico() {
		return correoElectronico;
	}
	public void setCorreoElectronico(String correoElectronico) {
		this.correoElectronico = correoElectronico;
	}
	@Column(name = "habilitado", nullable = false)
	public Boolean getHabilitado() {
		return habilitado;
	}
	public void setHabilitado(Boolean habilitado) {
		this.habilitado = habilitado;
	}	
	@Column(name = "cuentaSinExpirar", nullable = false)
	public Boolean getCuentaSinExpirar() {
		return cuentaSinExpirar;
	}
	public void setCuentaSinExpirar(Boolean cuentaSinExpirar) {
		this.cuentaSinExpirar = cuentaSinExpirar;
	}
	@Column(name = "credencialSinExpirar", nullable = false)
	public Boolean getCredencialSinExpirar() {
		return credencialSinExpirar;
	}
	public void setCredencialSinExpirar(Boolean credencialSinExpirar) {
		this.credencialSinExpirar = credencialSinExpirar;
	}
	@Column(name = "cuentaSinBloquear", nullable = false)
	public Boolean getCuentaSinBloquear() {
		return cuentaSinBloquear;
	}
	public void setCuentaSinBloquear(Boolean cuentaSinBloquear) {
		this.cuentaSinBloquear = cuentaSinBloquear;
	}
	@Column(name = "usuarioRegistro", nullable = false, length =50)
	public String getUsuarioRegistro() {
		return usuarioRegistro;
	}
	public void setUsuarioRegistro(String usuarioRegistro) {
		this.usuarioRegistro = usuarioRegistro;
	}
	@Column(name = "usuarioModifica", nullable = true, length =50)
	public String getUsuarioModifica() {
		return usuarioModifica;
	}
	public void setUsuarioModifica(String usuarioModifica) {
		this.usuarioModifica = usuarioModifica;
	}
	
	@Override
	public String toString(){
		return nombreUsuario;
	}
	@Override
	public boolean isFieldAuditable(String fieldname) {
		if(fieldname.matches("created")||fieldname.matches("createdBy")||fieldname.matches("modified")||fieldname.matches("modifiedBy")||fieldname.matches("password")){
			return false;
		}
		return true;
	}
	
	@Override
	public boolean equals(Object other) {
		
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof Usuario))
			return false;
		
		Usuario castOther = (Usuario) other;

		return (this.getNombreUsuario().equals(castOther.getNombreUsuario()));
	}
	
	@Override
	public int hashCode(){
		int result = 0;
		result = 31*result + (nombreUsuario !=null ? nombreUsuario.hashCode() : 0);
		return result;
	}
}

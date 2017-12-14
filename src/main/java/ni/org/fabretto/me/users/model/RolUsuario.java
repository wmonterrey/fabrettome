package ni.org.fabretto.me.users.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.ForeignKey;

import ni.org.fabretto.me.domain.BaseMetaData;
import ni.org.fabretto.me.domain.audit.Auditable;

/**
 * Simple objeto de dominio que representa un rol para un usuario
 * 
 * @author William Aviles
 **/

@Entity
@Table(name = "tblUsuarioRol", catalog = "fabrettome")
public class RolUsuario extends BaseMetaData implements Auditable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private RolUsuarioId rolUsuarioId;
	private Usuario usuario;
	private Rol rol;
	
	
	@Id
	public RolUsuarioId getRolUsuarioId() {
		return rolUsuarioId;
	}
	public void setRolUsuarioId(RolUsuarioId rolUsuarioId) {
		this.rolUsuarioId = rolUsuarioId;
	}
	
	public RolUsuario() {
	}
	
	
	public RolUsuario(RolUsuarioId rolUsuarioId, Usuario usuario, Rol rol) {
		super();
		this.rolUsuarioId = rolUsuarioId;
		this.usuario = usuario;
		this.rol = rol;
	}
	
	public RolUsuario(RolUsuarioId rolUsuarioId,
			Date fechaRegistro, String usuarioRegistro) {
		super(fechaRegistro, usuarioRegistro);
		this.rolUsuarioId = rolUsuarioId;
	}
	
	public RolUsuario(RolUsuarioId rolUsuarioId,
			Usuario usuario, Rol rol,Date fechaRegistro, String usuarioRegistro) {
		super(fechaRegistro, usuarioRegistro);
		this.rolUsuarioId = rolUsuarioId;
		this.usuario = usuario;
		this.rol = rol;
	}
	@ManyToOne
	@JoinColumn(name="nombreUsuario", insertable = false, updatable = false)
	@ForeignKey(name = "fkUsuario")
	public Usuario getUser() {
		return usuario;
	}
	
	public void setUser(Usuario user) {
		this.usuario = user;
	}
	@ManyToOne
	@JoinColumn(name="nombreRol", insertable = false, updatable = false)
	@ForeignKey(name = "fkRol")
	public Rol getRol() {
		return rol;
	}
	public void setRol(Rol rol) {
		this.rol = rol;
	}
	
	@Override
	public String toString(){
		return rolUsuarioId.getNombreRol();
	}
	@Override
	public boolean isFieldAuditable(String fieldname) {	
		return true;
	}

}

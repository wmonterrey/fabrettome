package ni.org.fabretto.me.domain.relationships;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.ForeignKey;

import ni.org.fabretto.me.domain.BaseMetaData;
import ni.org.fabretto.me.domain.audit.Auditable;
import ni.org.fabretto.me.domain.catalogs.Centro;
import ni.org.fabretto.me.users.model.Usuario;

/**
 * Simple objeto de dominio que representa la relación de los centros para un usuario
 * 
 * @author William Aviles
 **/

@Entity
@Table(name = "tblCentroUsuario", catalog = "fabrettome")
public class UsuarioCentro extends BaseMetaData implements Auditable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private UsuarioCentroId usuarioCentroId;
	private Usuario usuario;
	private Centro centro;
	
	public UsuarioCentro() {
	}
	
	public UsuarioCentro(UsuarioCentroId usuarioCentroId, Date recordDate, String recordUser) {
		super(recordDate, recordUser);
		this.usuarioCentroId = usuarioCentroId;
	}
	
	public UsuarioCentro(UsuarioCentroId usuarioCentroId, Usuario usuario, Centro centro
			, String recordUser, Date recordDate) {
		super(recordDate, recordUser);
		this.usuarioCentroId = usuarioCentroId;
		this.usuario = usuario;
		this.centro = centro;
	}
	
	@Id
	public UsuarioCentroId getUsuarioCentroId() {
		return usuarioCentroId;
	}
	public void setUsuarioCentroId(UsuarioCentroId usuarioCentroId) {
		this.usuarioCentroId = usuarioCentroId;
	}
	
	@ManyToOne
	@JoinColumn(name="usuario", insertable = false, updatable = false)
	@ForeignKey(name = "UC_USUARIOS_FK")
	public Usuario getUsuario() {
		return usuario;
	}
	
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	@ManyToOne
	@JoinColumn(name="centro", insertable = false, updatable = false)
	@ForeignKey(name = "UC_CENTROS_FK")
	public Centro getCentro() {
		return centro;
	}
	public void setCentro(Centro centro) {
		this.centro = centro;
	}
	
	@Override
	public String toString(){
		return centro.getCodigo();
	}
	@Override
	public boolean isFieldAuditable(String fieldname) {
		//Campos no auditables en la tabla
		if(fieldname.matches("recordDate")||fieldname.matches("recordUser")){
			return false;
		}		
		return true;
	}

}

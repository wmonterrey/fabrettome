package ni.org.fabretto.me.users.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;


@Embeddable
public class RolUsuarioId implements Serializable {
	/**
	 * Objeto que representa la clave unica de un rol
	 * 
	 * @author William Aviles
	 **/
	
	private static final long serialVersionUID = 1L;
	private String nombreUsuario;
	private String nombreRol;
	
	public RolUsuarioId(){
		
	}
	
	

	public RolUsuarioId(String nombreUsuario, String nombreRol) {
		super();
		this.nombreUsuario = nombreUsuario;
		this.nombreRol = nombreRol;
	}



	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof RolUsuarioId))
			return false;
		RolUsuarioId castOther = (RolUsuarioId) other;

		return (this.getNombreUsuario() == castOther.getNombreUsuario())
				&& (this.getNombreRol() == castOther.getNombreRol());
	}

	public int hashCode() {
		int result = 17;
		result = 37 * 3;
		return result;	
	}
	
	@Column(name = "nombreUsuario", nullable = false, length =50)
	public String getNombreUsuario() {
		return nombreUsuario;
	}


	public void setNombreUsuario(String nombreUsuario) {
		this.nombreUsuario = nombreUsuario;
	}

	@Column(name = "nombreRol", nullable = false, length =50)
	public String getNombreRol() {
		return nombreRol;
	}


	public void setNombreRol(String nombreRol) {
		this.nombreRol = nombreRol;
	}

	@Override
	public String toString(){
		return nombreUsuario;
	}

}
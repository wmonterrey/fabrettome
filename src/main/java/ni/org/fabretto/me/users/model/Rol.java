package ni.org.fabretto.me.users.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Simple objeto de dominio que representa un rol
 * 
 * 
 * @author William Aviles
 **/

@Entity
@Table(name = "catRol", catalog = "fabrettome")
public class Rol {
	private String nombreRol;	
	
	@Id
	@Column(name = "nombreRol", nullable = false, length =50)
	public String getNombreRol() {
		return nombreRol;
	}
	public void setNombreRol(String nombreRol) {
		this.nombreRol = nombreRol;
	}
	@Override
	public String toString(){
		return nombreRol;
	}
	@Override
	public boolean equals(Object other) {
		
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof Rol))
			return false;
		
		Rol castOther = (Rol) other;

		return (this.getNombreRol().equals(castOther.getNombreRol()));
	}
}

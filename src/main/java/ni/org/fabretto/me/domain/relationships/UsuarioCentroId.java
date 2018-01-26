package ni.org.fabretto.me.domain.relationships;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * Objeto que representa la clave unica de relacion usuario/centro
 * 
 * @author William Aviles
 **/
@Embeddable
public class UsuarioCentroId implements Serializable {

	
	private static final long serialVersionUID = 1L;
	private String usuario;
	private String centro;
	
	public UsuarioCentroId(){
		
	}
	
	

	public UsuarioCentroId(String usuario, String centro) {
		super();
		this.usuario = usuario;
		this.centro = centro;
	}



	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof UsuarioCentroId))
			return false;
		UsuarioCentroId castOther = (UsuarioCentroId) other;

		return this.getUsuario().equals(castOther.getUsuario())
				&& this.getCentro().equals(castOther.getCentro());
	}

	public int hashCode() {
		int result = 17;
		result = 37 * 3 * this.usuario.hashCode() * this.centro.hashCode();
		return result;	
	}
	
	@Column(name = "usuario", nullable = false, length =50)
	public String getUsuario() {
		return usuario;
	}


	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	@Column(name = "centro", nullable = false, length =50)
	public String getCentro() {
		return centro;
	}


	public void setCentro(String centro) {
		this.centro = centro;
	}

	@Override
	public String toString(){
		return usuario;
	}

}
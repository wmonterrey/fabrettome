package ni.org.fabretto.me.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * BaseMetaData es la clase que almacena la información de referencia acerca de los objetos.
 * 
 * BaseMetaData incluye información como:
 * 
 * <ul>
 * <li>Fecha del registro
 * <li>Usuario que registra
 * <li>Identificador del dispositivo
 * <li>Si el registro fué borrado
 * </ul>
 * 
 * 
 *  
 * @author      William Avilés
 * @version     1.0
 * @since       1.0
 */
@MappedSuperclass 
public class BaseMetaData implements Serializable 
{  


	private static final long serialVersionUID = 1L;
	private Date fechaRegistro;
	private String usuarioRegistro;
	private char pasivo = '0';
	private char estado='0';
	private String idEquipo;
	
	public BaseMetaData() {

	}

	public BaseMetaData(Date fechaRegistro, String usuarioRegistro) {
		super();
		this.fechaRegistro = fechaRegistro;
		this.usuarioRegistro = usuarioRegistro;
	}
	
	@Temporal( TemporalType.TIMESTAMP)
	@Column(name="fechaRegistro")
	public Date getFechaRegistro() {
		return fechaRegistro;
	}

	public void setFechaRegistro(Date fechaRegistro) {
		this.fechaRegistro = fechaRegistro;
	}

	@Column(name="usuarioRegistro", length = 50)
	public String getUsuarioRegistro() {
		return usuarioRegistro;
	}

	public void setUsuarioRegistro(String usuarioRegistro) {
		this.usuarioRegistro = usuarioRegistro;
	}
	
	@Column(name="pasivo", nullable = false, length = 1)
	public char getPasivo() {
		return pasivo;
	}

	public void setPasivo(char pasivo) {
		this.pasivo = pasivo;
	}

	@Column(name="estado", nullable = false, length = 1)
	public char getEstado() {
		return estado;
	}

	public void setEstado(char estado) {
		this.estado = estado;
	}

	@Column(name="idEquipo", length = 100)
	public String getIdEquipo() {
		return idEquipo;
	}

	public void setIdEquipo(String idEquipo) {
		this.idEquipo = idEquipo;
	}
	
	

}  

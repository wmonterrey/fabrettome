package ni.org.fabretto.me.users.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.ForeignKey;
import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "tblUsuarioAcceso", catalog = "fabrettome")
public class AccesoUsuario {

	private int id;
	private Usuario usuario;
	private Date fechaIngreso;
	private String idSesion;
	private String direccionRemota;
	private Date fechaSalida;
	private String urlSalida;
	
	
	public AccesoUsuario() {
		super();
	}
	
	
	public AccesoUsuario(Usuario usuario, Date fechaIngreso, String idSesion,
			String direccionRemota) {
		super();
		this.usuario = usuario;
		this.fechaIngreso = fechaIngreso;
		this.idSesion = idSesion;
		this.direccionRemota = direccionRemota;
	}

	@Id
	@GenericGenerator(name="idautoinc2" , strategy="increment")
	@GeneratedValue(generator="idautoinc2")
	@Column(name="id")
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}

	@ManyToOne(optional=false)
	@JoinColumn(name="nombreUsuario")
	@ForeignKey(name = "fkAccesoUsuario")
	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	@Column(name = "fechaIngreso")
	public Date getFechaIngreso() {
		return fechaIngreso;
	}
	public void setFechaIngreso(Date fechaIngreso) {
		this.fechaIngreso = fechaIngreso;
	}
	@Column(name = "idSesion", nullable = true, length =100)
	public String getIdSesion() {
		return idSesion;
	}
	public void setIdSesion(String idSesion) {
		this.idSesion = idSesion;
	}
	@Column(name = "direccionRemota", nullable = true, length =50)
	public String getDireccionRemota() {
		return direccionRemota;
	}
	public void setDireccionRemota(String direccionRemota) {
		this.direccionRemota = direccionRemota;
	}
	@Column(name = "fechaSalida")
	public Date getFechaSalida() {
		return fechaSalida;
	}
	public void setFechaSalida(Date fechaSalida) {
		this.fechaSalida = fechaSalida;
	}
	@Column(name = "urlSalida", nullable = true, length =100)
	public String getUrlSalida() {
		return urlSalida;
	}
	public void setUrlSalida(String urlSalida) {
		this.urlSalida = urlSalida;
	}
	@Override
	public String toString(){
		return usuario.toString();
	}
}
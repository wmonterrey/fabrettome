package ni.org.fabretto.me.users.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "tblUsuarioIntento", catalog = "fabrettome")
public class IntentoUsuario {

	private int id;
	private String nombreUsuario;
	private int numIntentos;
	private Date fechaUltimoIntento;
	
	public IntentoUsuario() {
		super();
	}
	public IntentoUsuario(String nombreUsuario, int numIntentos, Date fechaUltimoIntento) {
		super();
		this.nombreUsuario = nombreUsuario;
		this.numIntentos = numIntentos;
		this.fechaUltimoIntento = fechaUltimoIntento;
	}
	@Id
	@GenericGenerator(name="idautoinc" , strategy="increment")
	@GeneratedValue(generator="idautoinc")
	@Column(name="id")
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}

	@Column(name = "nombreUsuario", nullable = false, length =50)
	public String getNombreUsuario() {
		return nombreUsuario;
	}
	public void setNombreUsuario(String nombreUsuario) {
		this.nombreUsuario = nombreUsuario;
	}

	@Column(name = "numIntentos")
	public int getNumIntentos() {
		return numIntentos;
	}

	public void setNumIntentos(int numIntentos) {
		this.numIntentos = numIntentos;
	}

	@Column(name = "fechaUltimoIntento")
	public Date getFechaUltimoIntento() {
		return fechaUltimoIntento;
	}

	public void setFechaUltimoIntento(Date fechaUltimoIntento) {
		this.fechaUltimoIntento = fechaUltimoIntento;
	}
	@Override
	public String toString(){
		return nombreUsuario;
	}

}
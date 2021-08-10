package butiqueklf.model.core.entities;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the empleado database table.
 * 
 */
@Entity
@Table(name="empleado")
@NamedQuery(name="Empleado.findAll", query="SELECT e FROM Empleado e")
public class Empleado implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="cedula_emp", unique=true, nullable=false, length=10)
	private String cedulaEmp;

	@Column(name="apellido_emp", nullable=false, length=50)
	private String apellidoEmp;

	@Column(name="correo_emp", nullable=false, length=50)
	private String correoEmp;

	@Column(name="direccion_emp", nullable=false, length=50)
	private String direccionEmp;

	@Column(name="nombre_emp", nullable=false, length=50)
	private String nombreEmp;

	@Column(name="telefono_emp", nullable=false, length=50)
	private String telefonoEmp;

	//bi-directional many-to-one association to Cargo
	@ManyToOne
	@JoinColumn(name="id_cargo")
	private Cargo cargo;

	//bi-directional many-to-one association to Usuario
	@ManyToOne
	@JoinColumn(name="id_usu")
	private Usuario usuario;

	public Empleado() {
	}

	public String getCedulaEmp() {
		return this.cedulaEmp;
	}

	public void setCedulaEmp(String cedulaEmp) {
		this.cedulaEmp = cedulaEmp;
	}

	public String getApellidoEmp() {
		return this.apellidoEmp;
	}

	public void setApellidoEmp(String apellidoEmp) {
		this.apellidoEmp = apellidoEmp;
	}

	public String getCorreoEmp() {
		return this.correoEmp;
	}

	public void setCorreoEmp(String correoEmp) {
		this.correoEmp = correoEmp;
	}

	public String getDireccionEmp() {
		return this.direccionEmp;
	}

	public void setDireccionEmp(String direccionEmp) {
		this.direccionEmp = direccionEmp;
	}

	public String getNombreEmp() {
		return this.nombreEmp;
	}

	public void setNombreEmp(String nombreEmp) {
		this.nombreEmp = nombreEmp;
	}

	public String getTelefonoEmp() {
		return this.telefonoEmp;
	}

	public void setTelefonoEmp(String telefonoEmp) {
		this.telefonoEmp = telefonoEmp;
	}

	public Cargo getCargo() {
		return this.cargo;
	}

	public void setCargo(Cargo cargo) {
		this.cargo = cargo;
	}

	public Usuario getUsuario() {
		return this.usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

}
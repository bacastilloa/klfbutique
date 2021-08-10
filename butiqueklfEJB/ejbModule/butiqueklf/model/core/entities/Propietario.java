package butiqueklf.model.core.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the propietario database table.
 * 
 */
@Entity
@Table(name="propietario")
@NamedQuery(name="Propietario.findAll", query="SELECT p FROM Propietario p")
public class Propietario implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="cedula_pro", unique=true, nullable=false, length=10)
	private String cedulaPro;

	@Column(name="apellido_pro", nullable=false, length=50)
	private String apellidoPro;

	@Column(name="correo_pro", nullable=false, length=50)
	private String correoPro;

	@Column(name="direccion_pro", nullable=false, length=50)
	private String direccionPro;

	@Column(name="nombre_pro", nullable=false, length=50)
	private String nombrePro;

	@Column(name="telefono_pro", nullable=false, length=50)
	private String telefonoPro;

	//bi-directional many-to-one association to Inventario
	@OneToMany(mappedBy="propietario")
	private List<Inventario> inventarios;

	//bi-directional many-to-one association to Cargo
	@ManyToOne
	@JoinColumn(name="id_cargo")
	private Cargo cargo;

	//bi-directional many-to-one association to Usuario
	@ManyToOne
	@JoinColumn(name="id_usu")
	private Usuario usuario;

	public Propietario() {
	}

	public String getCedulaPro() {
		return this.cedulaPro;
	}

	public void setCedulaPro(String cedulaPro) {
		this.cedulaPro = cedulaPro;
	}

	public String getApellidoPro() {
		return this.apellidoPro;
	}

	public void setApellidoPro(String apellidoPro) {
		this.apellidoPro = apellidoPro;
	}

	public String getCorreoPro() {
		return this.correoPro;
	}

	public void setCorreoPro(String correoPro) {
		this.correoPro = correoPro;
	}

	public String getDireccionPro() {
		return this.direccionPro;
	}

	public void setDireccionPro(String direccionPro) {
		this.direccionPro = direccionPro;
	}

	public String getNombrePro() {
		return this.nombrePro;
	}

	public void setNombrePro(String nombrePro) {
		this.nombrePro = nombrePro;
	}

	public String getTelefonoPro() {
		return this.telefonoPro;
	}

	public void setTelefonoPro(String telefonoPro) {
		this.telefonoPro = telefonoPro;
	}

	public List<Inventario> getInventarios() {
		return this.inventarios;
	}

	public void setInventarios(List<Inventario> inventarios) {
		this.inventarios = inventarios;
	}

	public Inventario addInventario(Inventario inventario) {
		getInventarios().add(inventario);
		inventario.setPropietario(this);

		return inventario;
	}

	public Inventario removeInventario(Inventario inventario) {
		getInventarios().remove(inventario);
		inventario.setPropietario(null);

		return inventario;
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
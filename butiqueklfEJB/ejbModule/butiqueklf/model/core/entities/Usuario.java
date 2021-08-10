package butiqueklf.model.core.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the usuario database table.
 * 
 */
@Entity
@Table(name="usuario")
@NamedQuery(name="Usuario.findAll", query="SELECT u FROM Usuario u")
public class Usuario implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="id_usu", unique=true, nullable=false, length=50)
	private String idUsu;

	@Column(name="contrasenia_usu", nullable=false, length=50)
	private String contraseniaUsu;

	//bi-directional many-to-one association to Empleado
	@OneToMany(mappedBy="usuario")
	private List<Empleado> empleados;

	//bi-directional many-to-one association to Propietario
	@OneToMany(mappedBy="usuario")
	private List<Propietario> propietarios;

	public Usuario() {
	}

	public String getIdUsu() {
		return this.idUsu;
	}

	public void setIdUsu(String idUsu) {
		this.idUsu = idUsu;
	}

	public String getContraseniaUsu() {
		return this.contraseniaUsu;
	}

	public void setContraseniaUsu(String contraseniaUsu) {
		this.contraseniaUsu = contraseniaUsu;
	}

	public List<Empleado> getEmpleados() {
		return this.empleados;
	}

	public void setEmpleados(List<Empleado> empleados) {
		this.empleados = empleados;
	}

	public Empleado addEmpleado(Empleado empleado) {
		getEmpleados().add(empleado);
		empleado.setUsuario(this);

		return empleado;
	}

	public Empleado removeEmpleado(Empleado empleado) {
		getEmpleados().remove(empleado);
		empleado.setUsuario(null);

		return empleado;
	}

	public List<Propietario> getPropietarios() {
		return this.propietarios;
	}

	public void setPropietarios(List<Propietario> propietarios) {
		this.propietarios = propietarios;
	}

	public Propietario addPropietario(Propietario propietario) {
		getPropietarios().add(propietario);
		propietario.setUsuario(this);

		return propietario;
	}

	public Propietario removePropietario(Propietario propietario) {
		getPropietarios().remove(propietario);
		propietario.setUsuario(null);

		return propietario;
	}

}
package butiqueklf.model.core.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;


/**
 * The persistent class for the cargo database table.
 * 
 */
@Entity
@Table(name="cargo")
@NamedQuery(name="Cargo.findAll", query="SELECT c FROM Cargo c")
public class Cargo implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_cargo", unique=true, nullable=false)
	private Integer idCargo;

	@Column(name="nombre_cargo", nullable=false, length=50)
	private String nombreCargo;

	@Column(nullable=false, precision=7, scale=2)
	private BigDecimal sueldo;

	//bi-directional many-to-one association to Empleado
	@OneToMany(mappedBy="cargo")
	private List<Empleado> empleados;

	//bi-directional many-to-one association to Propietario
	@OneToMany(mappedBy="cargo")
	private List<Propietario> propietarios;

	public Cargo() {
	}

	public Integer getIdCargo() {
		return this.idCargo;
	}

	public void setIdCargo(Integer idCargo) {
		this.idCargo = idCargo;
	}

	public String getNombreCargo() {
		return this.nombreCargo;
	}

	public void setNombreCargo(String nombreCargo) {
		this.nombreCargo = nombreCargo;
	}

	public BigDecimal getSueldo() {
		return this.sueldo;
	}

	public void setSueldo(BigDecimal sueldo) {
		this.sueldo = sueldo;
	}

	public List<Empleado> getEmpleados() {
		return this.empleados;
	}

	public void setEmpleados(List<Empleado> empleados) {
		this.empleados = empleados;
	}

	public Empleado addEmpleado(Empleado empleado) {
		getEmpleados().add(empleado);
		empleado.setCargo(this);

		return empleado;
	}

	public Empleado removeEmpleado(Empleado empleado) {
		getEmpleados().remove(empleado);
		empleado.setCargo(null);

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
		propietario.setCargo(this);

		return propietario;
	}

	public Propietario removePropietario(Propietario propietario) {
		getPropietarios().remove(propietario);
		propietario.setCargo(null);

		return propietario;
	}

}
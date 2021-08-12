package butiqueklf.model.core.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;


/**
 * The persistent class for the thm_cargo database table.
 * 
 */
@Entity
@Table(name="thm_cargo")
@NamedQuery(name="ThmCargo.findAll", query="SELECT t FROM ThmCargo t")
public class ThmCargo implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="id_thm_cargo", unique=true, nullable=false)
	private Integer idThmCargo;

	@Column(name="nombre_cargo", nullable=false, length=50)
	private String nombreCargo;

	@Column(nullable=false, precision=7, scale=2)
	private BigDecimal sueldo;

	//bi-directional many-to-one association to ThmEmpleado
	@OneToMany(mappedBy="thmCargo")
	private List<ThmEmpleado> thmEmpleados;

	public ThmCargo() {
	}

	public Integer getIdThmCargo() {
		return this.idThmCargo;
	}

	public void setIdThmCargo(Integer idThmCargo) {
		this.idThmCargo = idThmCargo;
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

	public List<ThmEmpleado> getThmEmpleados() {
		return this.thmEmpleados;
	}

	public void setThmEmpleados(List<ThmEmpleado> thmEmpleados) {
		this.thmEmpleados = thmEmpleados;
	}

	public ThmEmpleado addThmEmpleado(ThmEmpleado thmEmpleado) {
		getThmEmpleados().add(thmEmpleado);
		thmEmpleado.setThmCargo(this);

		return thmEmpleado;
	}

	public ThmEmpleado removeThmEmpleado(ThmEmpleado thmEmpleado) {
		getThmEmpleados().remove(thmEmpleado);
		thmEmpleado.setThmCargo(null);

		return thmEmpleado;
	}

}
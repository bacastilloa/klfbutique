package butiqueklf.model.core.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;


/**
 * The persistent class for the thm_empleado database table.
 * 
 */
@Entity
@Table(name="thm_empleado")
@NamedQuery(name="ThmEmpleado.findAll", query="SELECT t FROM ThmEmpleado t")
public class ThmEmpleado implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="id_thm_empleado", unique=true, nullable=false)
	private Integer idThmEmpleado;

	@Column(name="couta_prestamo", nullable=false, precision=7, scale=2)
	private BigDecimal coutaPrestamo;

	@Column(name="horas_extra", nullable=false)
	private Integer horasExtra;

	@Column(name="horas_trabajadas", nullable=false)
	private Integer horasTrabajadas;

	//bi-directional many-to-one association to SegUsuario
	@ManyToOne
	@JoinColumn(name="id_seg_usuario")
	private SegUsuario segUsuario;

	//bi-directional many-to-one association to ThmCargo
	@ManyToOne
	@JoinColumn(name="id_thm_cargo")
	private ThmCargo thmCargo;

	//bi-directional many-to-one association to VenFacturaCabezera
	@OneToMany(mappedBy="thmEmpleado")
	private List<VenFacturaCabezera> venFacturaCabezeras;

	public ThmEmpleado() {
	}

	public Integer getIdThmEmpleado() {
		return this.idThmEmpleado;
	}

	public void setIdThmEmpleado(Integer idThmEmpleado) {
		this.idThmEmpleado = idThmEmpleado;
	}

	public BigDecimal getCoutaPrestamo() {
		return this.coutaPrestamo;
	}

	public void setCoutaPrestamo(BigDecimal coutaPrestamo) {
		this.coutaPrestamo = coutaPrestamo;
	}

	public Integer getHorasExtra() {
		return this.horasExtra;
	}

	public void setHorasExtra(Integer horasExtra) {
		this.horasExtra = horasExtra;
	}

	public Integer getHorasTrabajadas() {
		return this.horasTrabajadas;
	}

	public void setHorasTrabajadas(Integer horasTrabajadas) {
		this.horasTrabajadas = horasTrabajadas;
	}

	public SegUsuario getSegUsuario() {
		return this.segUsuario;
	}

	public void setSegUsuario(SegUsuario segUsuario) {
		this.segUsuario = segUsuario;
	}

	public ThmCargo getThmCargo() {
		return this.thmCargo;
	}

	public void setThmCargo(ThmCargo thmCargo) {
		this.thmCargo = thmCargo;
	}

	public List<VenFacturaCabezera> getVenFacturaCabezeras() {
		return this.venFacturaCabezeras;
	}

	public void setVenFacturaCabezeras(List<VenFacturaCabezera> venFacturaCabezeras) {
		this.venFacturaCabezeras = venFacturaCabezeras;
	}

	public VenFacturaCabezera addVenFacturaCabezera(VenFacturaCabezera venFacturaCabezera) {
		getVenFacturaCabezeras().add(venFacturaCabezera);
		venFacturaCabezera.setThmEmpleado(this);

		return venFacturaCabezera;
	}

	public VenFacturaCabezera removeVenFacturaCabezera(VenFacturaCabezera venFacturaCabezera) {
		getVenFacturaCabezeras().remove(venFacturaCabezera);
		venFacturaCabezera.setThmEmpleado(null);

		return venFacturaCabezera;
	}

}
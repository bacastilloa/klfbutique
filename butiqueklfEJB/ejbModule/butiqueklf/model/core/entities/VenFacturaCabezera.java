package butiqueklf.model.core.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the ven_factura_cabezera database table.
 * 
 */
@Entity
@Table(name="ven_factura_cabezera")
@NamedQuery(name="VenFacturaCabezera.findAll", query="SELECT v FROM VenFacturaCabezera v")
public class VenFacturaCabezera implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="id_ven_factura_cabezera", unique=true, nullable=false)
	private Integer idVenFacturaCabezera;

	@Temporal(TemporalType.DATE)
	@Column(nullable=false)
	private Date fecha;

	//bi-directional many-to-one association to VenFactura
	@OneToMany(mappedBy="venFacturaCabezera")
	private List<VenFactura> venFacturas;

	//bi-directional many-to-one association to CliCliente
	@ManyToOne
	@JoinColumn(name="cedula_cliente")
	private CliCliente cliCliente;

	//bi-directional many-to-one association to ThmEmpleado
	@ManyToOne
	@JoinColumn(name="id_thm_empleado")
	private ThmEmpleado thmEmpleado;

	public VenFacturaCabezera() {
	}

	public Integer getIdVenFacturaCabezera() {
		return this.idVenFacturaCabezera;
	}

	public void setIdVenFacturaCabezera(Integer idVenFacturaCabezera) {
		this.idVenFacturaCabezera = idVenFacturaCabezera;
	}

	public Date getFecha() {
		return this.fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public List<VenFactura> getVenFacturas() {
		return this.venFacturas;
	}

	public void setVenFacturas(List<VenFactura> venFacturas) {
		this.venFacturas = venFacturas;
	}

	public VenFactura addVenFactura(VenFactura venFactura) {
		getVenFacturas().add(venFactura);
		venFactura.setVenFacturaCabezera(this);

		return venFactura;
	}

	public VenFactura removeVenFactura(VenFactura venFactura) {
		getVenFacturas().remove(venFactura);
		venFactura.setVenFacturaCabezera(null);

		return venFactura;
	}

	public CliCliente getCliCliente() {
		return this.cliCliente;
	}

	public void setCliCliente(CliCliente cliCliente) {
		this.cliCliente = cliCliente;
	}

	public ThmEmpleado getThmEmpleado() {
		return this.thmEmpleado;
	}

	public void setThmEmpleado(ThmEmpleado thmEmpleado) {
		this.thmEmpleado = thmEmpleado;
	}

}
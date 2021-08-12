package butiqueklf.model.core.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;


/**
 * The persistent class for the ven_factura_detalle database table.
 * 
 */
@Entity
@Table(name="ven_factura_detalle")
@NamedQuery(name="VenFacturaDetalle.findAll", query="SELECT v FROM VenFacturaDetalle v")
public class VenFacturaDetalle implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="id_ven_factura_detalle", unique=true, nullable=false)
	private Integer idVenFacturaDetalle;

	@Column(nullable=false, precision=131089)
	private BigDecimal cantidad;

	@Column(nullable=false, precision=8, scale=2)
	private BigDecimal total;

	//bi-directional many-to-one association to InvInventario
	@OneToMany(mappedBy="venFacturaDetalle")
	private List<InvInventario> invInventarios;

	//bi-directional many-to-one association to VenFactura
	@OneToMany(mappedBy="venFacturaDetalle")
	private List<VenFactura> venFacturas;

	public VenFacturaDetalle() {
	}

	public Integer getIdVenFacturaDetalle() {
		return this.idVenFacturaDetalle;
	}

	public void setIdVenFacturaDetalle(Integer idVenFacturaDetalle) {
		this.idVenFacturaDetalle = idVenFacturaDetalle;
	}

	public BigDecimal getCantidad() {
		return this.cantidad;
	}

	public void setCantidad(BigDecimal cantidad) {
		this.cantidad = cantidad;
	}

	public BigDecimal getTotal() {
		return this.total;
	}

	public void setTotal(BigDecimal total) {
		this.total = total;
	}

	public List<InvInventario> getInvInventarios() {
		return this.invInventarios;
	}

	public void setInvInventarios(List<InvInventario> invInventarios) {
		this.invInventarios = invInventarios;
	}

	public InvInventario addInvInventario(InvInventario invInventario) {
		getInvInventarios().add(invInventario);
		invInventario.setVenFacturaDetalle(this);

		return invInventario;
	}

	public InvInventario removeInvInventario(InvInventario invInventario) {
		getInvInventarios().remove(invInventario);
		invInventario.setVenFacturaDetalle(null);

		return invInventario;
	}

	public List<VenFactura> getVenFacturas() {
		return this.venFacturas;
	}

	public void setVenFacturas(List<VenFactura> venFacturas) {
		this.venFacturas = venFacturas;
	}

	public VenFactura addVenFactura(VenFactura venFactura) {
		getVenFacturas().add(venFactura);
		venFactura.setVenFacturaDetalle(this);

		return venFactura;
	}

	public VenFactura removeVenFactura(VenFactura venFactura) {
		getVenFacturas().remove(venFactura);
		venFactura.setVenFacturaDetalle(null);

		return venFactura;
	}

}
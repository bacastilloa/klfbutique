package butiqueklf.model.core.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;


/**
 * The persistent class for the inv_inventario database table.
 * 
 */
@Entity
@Table(name="inv_inventario")
@NamedQuery(name="InvInventario.findAll", query="SELECT i FROM InvInventario i")
public class InvInventario implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="id_inv_inventario", unique=true, nullable=false)
	private Integer idInvInventario;

	@Column(nullable=false, precision=8, scale=2)
	private BigDecimal cantidadtotal;

	//bi-directional many-to-one association to CompProveedorDetalle
	@ManyToOne
	@JoinColumn(name="id_comp_proveedor_detalle")
	private CompProveedorDetalle compProveedorDetalle;

	//bi-directional many-to-one association to InvProducto
	@ManyToOne
	@JoinColumn(name="id_prod")
	private InvProducto invProducto;

	//bi-directional many-to-one association to VenFacturaDetalle
	@ManyToOne
	@JoinColumn(name="id_ven_factura_detalle")
	private VenFacturaDetalle venFacturaDetalle;

	public InvInventario() {
	}

	public Integer getIdInvInventario() {
		return this.idInvInventario;
	}

	public void setIdInvInventario(Integer idInvInventario) {
		this.idInvInventario = idInvInventario;
	}

	public BigDecimal getCantidadtotal() {
		return this.cantidadtotal;
	}

	public void setCantidadtotal(BigDecimal cantidadtotal) {
		this.cantidadtotal = cantidadtotal;
	}

	public CompProveedorDetalle getCompProveedorDetalle() {
		return this.compProveedorDetalle;
	}

	public void setCompProveedorDetalle(CompProveedorDetalle compProveedorDetalle) {
		this.compProveedorDetalle = compProveedorDetalle;
	}

	public InvProducto getInvProducto() {
		return this.invProducto;
	}

	public void setInvProducto(InvProducto invProducto) {
		this.invProducto = invProducto;
	}

	public VenFacturaDetalle getVenFacturaDetalle() {
		return this.venFacturaDetalle;
	}

	public void setVenFacturaDetalle(VenFacturaDetalle venFacturaDetalle) {
		this.venFacturaDetalle = venFacturaDetalle;
	}

}
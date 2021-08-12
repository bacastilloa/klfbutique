package butiqueklf.model.core.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;


/**
 * The persistent class for the ven_factura database table.
 * 
 */
@Entity
@Table(name="ven_factura")
@NamedQuery(name="VenFactura.findAll", query="SELECT v FROM VenFactura v")
public class VenFactura implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="id_ven_factura", unique=true, nullable=false)
	private Integer idVenFactura;

	@Column(nullable=false, precision=8, scale=2)
	private BigDecimal descuento;

	@Column(nullable=false, precision=8, scale=2)
	private BigDecimal iva;

	@Column(nullable=false, precision=8, scale=2)
	private BigDecimal subtotal;

	@Column(nullable=false, precision=8, scale=2)
	private BigDecimal total;

	//bi-directional many-to-one association to VenFacturaCabezera
	@ManyToOne
	@JoinColumn(name="id_ven_factura_cabezera")
	private VenFacturaCabezera venFacturaCabezera;

	//bi-directional many-to-one association to VenFacturaDetalle
	@ManyToOne
	@JoinColumn(name="id_ven_factura_detalle")
	private VenFacturaDetalle venFacturaDetalle;

	public VenFactura() {
	}

	public Integer getIdVenFactura() {
		return this.idVenFactura;
	}

	public void setIdVenFactura(Integer idVenFactura) {
		this.idVenFactura = idVenFactura;
	}

	public BigDecimal getDescuento() {
		return this.descuento;
	}

	public void setDescuento(BigDecimal descuento) {
		this.descuento = descuento;
	}

	public BigDecimal getIva() {
		return this.iva;
	}

	public void setIva(BigDecimal iva) {
		this.iva = iva;
	}

	public BigDecimal getSubtotal() {
		return this.subtotal;
	}

	public void setSubtotal(BigDecimal subtotal) {
		this.subtotal = subtotal;
	}

	public BigDecimal getTotal() {
		return this.total;
	}

	public void setTotal(BigDecimal total) {
		this.total = total;
	}

	public VenFacturaCabezera getVenFacturaCabezera() {
		return this.venFacturaCabezera;
	}

	public void setVenFacturaCabezera(VenFacturaCabezera venFacturaCabezera) {
		this.venFacturaCabezera = venFacturaCabezera;
	}

	public VenFacturaDetalle getVenFacturaDetalle() {
		return this.venFacturaDetalle;
	}

	public void setVenFacturaDetalle(VenFacturaDetalle venFacturaDetalle) {
		this.venFacturaDetalle = venFacturaDetalle;
	}

}
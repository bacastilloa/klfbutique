package butiqueklf.model.core.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;


/**
 * The persistent class for the factura database table.
 * 
 */
@Entity
@Table(name="factura")
@NamedQuery(name="Factura.findAll", query="SELECT f FROM Factura f")
public class Factura implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_fac", unique=true, nullable=false)
	private Integer idFac;

	@Column(nullable=false, precision=8, scale=2)
	private BigDecimal descuento;

	@Column(nullable=false, precision=8, scale=2)
	private BigDecimal iva;

	@Column(nullable=false, precision=8, scale=2)
	private BigDecimal subtotal;

	@Column(nullable=false, precision=8, scale=2)
	private BigDecimal total;

	//bi-directional many-to-one association to DetalleFactura
	@ManyToOne
	@JoinColumn(name="id_det_fac")
	private DetalleFactura detalleFactura;

	//bi-directional many-to-one association to FacturaCabezera
	@ManyToOne
	@JoinColumn(name="id_fac_cabe")
	private FacturaCabezera facturaCabezera;

	public Factura() {
	}

	public Integer getIdFac() {
		return this.idFac;
	}

	public void setIdFac(Integer idFac) {
		this.idFac = idFac;
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

	public DetalleFactura getDetalleFactura() {
		return this.detalleFactura;
	}

	public void setDetalleFactura(DetalleFactura detalleFactura) {
		this.detalleFactura = detalleFactura;
	}

	public FacturaCabezera getFacturaCabezera() {
		return this.facturaCabezera;
	}

	public void setFacturaCabezera(FacturaCabezera facturaCabezera) {
		this.facturaCabezera = facturaCabezera;
	}

}
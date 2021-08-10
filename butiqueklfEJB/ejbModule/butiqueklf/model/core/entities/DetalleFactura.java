package butiqueklf.model.core.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;


/**
 * The persistent class for the detalle_factura database table.
 * 
 */
@Entity
@Table(name="detalle_factura")
@NamedQuery(name="DetalleFactura.findAll", query="SELECT d FROM DetalleFactura d")
public class DetalleFactura implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_det_fac", unique=true, nullable=false)
	private Integer idDetFac;

	@Column(nullable=false, precision=131089)
	private BigDecimal cantidad;

	@Column(nullable=false, precision=8, scale=2)
	private BigDecimal total;

	//bi-directional many-to-one association to DetalleProducto
	@ManyToOne
	@JoinColumn(name="id_det_pro")
	private DetalleProducto detalleProducto;

	//bi-directional many-to-one association to DetalleProducto
	@OneToMany(mappedBy="detalleFactura")
	private List<DetalleProducto> detalleProductos;

	//bi-directional many-to-one association to Factura
	@OneToMany(mappedBy="detalleFactura")
	private List<Factura> facturas;

	//bi-directional many-to-one association to Inventario
	@OneToMany(mappedBy="detalleFactura")
	private List<Inventario> inventarios;

	public DetalleFactura() {
	}

	public Integer getIdDetFac() {
		return this.idDetFac;
	}

	public void setIdDetFac(Integer idDetFac) {
		this.idDetFac = idDetFac;
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

	public DetalleProducto getDetalleProducto() {
		return this.detalleProducto;
	}

	public void setDetalleProducto(DetalleProducto detalleProducto) {
		this.detalleProducto = detalleProducto;
	}

	public List<DetalleProducto> getDetalleProductos() {
		return this.detalleProductos;
	}

	public void setDetalleProductos(List<DetalleProducto> detalleProductos) {
		this.detalleProductos = detalleProductos;
	}

	public DetalleProducto addDetalleProducto(DetalleProducto detalleProducto) {
		getDetalleProductos().add(detalleProducto);
		detalleProducto.setDetalleFactura(this);

		return detalleProducto;
	}

	public DetalleProducto removeDetalleProducto(DetalleProducto detalleProducto) {
		getDetalleProductos().remove(detalleProducto);
		detalleProducto.setDetalleFactura(null);

		return detalleProducto;
	}

	public List<Factura> getFacturas() {
		return this.facturas;
	}

	public void setFacturas(List<Factura> facturas) {
		this.facturas = facturas;
	}

	public Factura addFactura(Factura factura) {
		getFacturas().add(factura);
		factura.setDetalleFactura(this);

		return factura;
	}

	public Factura removeFactura(Factura factura) {
		getFacturas().remove(factura);
		factura.setDetalleFactura(null);

		return factura;
	}

	public List<Inventario> getInventarios() {
		return this.inventarios;
	}

	public void setInventarios(List<Inventario> inventarios) {
		this.inventarios = inventarios;
	}

	public Inventario addInventario(Inventario inventario) {
		getInventarios().add(inventario);
		inventario.setDetalleFactura(this);

		return inventario;
	}

	public Inventario removeInventario(Inventario inventario) {
		getInventarios().remove(inventario);
		inventario.setDetalleFactura(null);

		return inventario;
	}

}
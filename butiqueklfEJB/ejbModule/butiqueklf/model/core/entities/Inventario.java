package butiqueklf.model.core.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;


/**
 * The persistent class for the inventario database table.
 * 
 */
@Entity
@Table(name="inventario")
@NamedQuery(name="Inventario.findAll", query="SELECT i FROM Inventario i")
public class Inventario implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_inve", unique=true, nullable=false)
	private Integer idInve;

	@Column(nullable=false, precision=8, scale=2)
	private BigDecimal cantidadtotal;

	//bi-directional many-to-one association to DetalleCompra
	@ManyToOne
	@JoinColumn(name="id_det_comp")
	private DetalleCompra detalleCompra;

	//bi-directional many-to-one association to DetalleFactura
	@ManyToOne
	@JoinColumn(name="id_det_fac")
	private DetalleFactura detalleFactura;

	//bi-directional many-to-one association to Producto
	@ManyToOne
	@JoinColumn(name="id_prod")
	private Producto producto;

	//bi-directional many-to-one association to Propietario
	@ManyToOne
	@JoinColumn(name="cedula_pro")
	private Propietario propietario;

	public Inventario() {
	}

	public Integer getIdInve() {
		return this.idInve;
	}

	public void setIdInve(Integer idInve) {
		this.idInve = idInve;
	}

	public BigDecimal getCantidadtotal() {
		return this.cantidadtotal;
	}

	public void setCantidadtotal(BigDecimal cantidadtotal) {
		this.cantidadtotal = cantidadtotal;
	}

	public DetalleCompra getDetalleCompra() {
		return this.detalleCompra;
	}

	public void setDetalleCompra(DetalleCompra detalleCompra) {
		this.detalleCompra = detalleCompra;
	}

	public DetalleFactura getDetalleFactura() {
		return this.detalleFactura;
	}

	public void setDetalleFactura(DetalleFactura detalleFactura) {
		this.detalleFactura = detalleFactura;
	}

	public Producto getProducto() {
		return this.producto;
	}

	public void setProducto(Producto producto) {
		this.producto = producto;
	}

	public Propietario getPropietario() {
		return this.propietario;
	}

	public void setPropietario(Propietario propietario) {
		this.propietario = propietario;
	}

}
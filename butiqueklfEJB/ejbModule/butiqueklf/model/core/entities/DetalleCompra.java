package butiqueklf.model.core.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;


/**
 * The persistent class for the detalle_compra database table.
 * 
 */
@Entity
@Table(name="detalle_compra")
@NamedQuery(name="DetalleCompra.findAll", query="SELECT d FROM DetalleCompra d")
public class DetalleCompra implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_det_comp", unique=true, nullable=false)
	private Integer idDetComp;

	@Column(name="cantidad_comp", nullable=false, precision=131089)
	private BigDecimal cantidadComp;

	@Column(name="precio_comp", nullable=false, precision=8, scale=2)
	private BigDecimal precioComp;

	@Column(name="total_comp", nullable=false, precision=8, scale=2)
	private BigDecimal totalComp;

	//bi-directional many-to-one association to Producto
	@ManyToOne
	@JoinColumn(name="id_prod")
	private Producto producto;

	//bi-directional many-to-one association to Proveedore
	@ManyToOne
	@JoinColumn(name="ruc_prove")
	private Proveedore proveedore;

	//bi-directional many-to-one association to Inventario
	@OneToMany(mappedBy="detalleCompra")
	private List<Inventario> inventarios;

	public DetalleCompra() {
	}

	public Integer getIdDetComp() {
		return this.idDetComp;
	}

	public void setIdDetComp(Integer idDetComp) {
		this.idDetComp = idDetComp;
	}

	public BigDecimal getCantidadComp() {
		return this.cantidadComp;
	}

	public void setCantidadComp(BigDecimal cantidadComp) {
		this.cantidadComp = cantidadComp;
	}

	public BigDecimal getPrecioComp() {
		return this.precioComp;
	}

	public void setPrecioComp(BigDecimal precioComp) {
		this.precioComp = precioComp;
	}

	public BigDecimal getTotalComp() {
		return this.totalComp;
	}

	public void setTotalComp(BigDecimal totalComp) {
		this.totalComp = totalComp;
	}

	public Producto getProducto() {
		return this.producto;
	}

	public void setProducto(Producto producto) {
		this.producto = producto;
	}

	public Proveedore getProveedore() {
		return this.proveedore;
	}

	public void setProveedore(Proveedore proveedore) {
		this.proveedore = proveedore;
	}

	public List<Inventario> getInventarios() {
		return this.inventarios;
	}

	public void setInventarios(List<Inventario> inventarios) {
		this.inventarios = inventarios;
	}

	public Inventario addInventario(Inventario inventario) {
		getInventarios().add(inventario);
		inventario.setDetalleCompra(this);

		return inventario;
	}

	public Inventario removeInventario(Inventario inventario) {
		getInventarios().remove(inventario);
		inventario.setDetalleCompra(null);

		return inventario;
	}

}
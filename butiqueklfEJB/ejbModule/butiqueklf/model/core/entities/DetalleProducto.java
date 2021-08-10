package butiqueklf.model.core.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the detalle_producto database table.
 * 
 */
@Entity
@Table(name="detalle_producto")
@NamedQuery(name="DetalleProducto.findAll", query="SELECT d FROM DetalleProducto d")
public class DetalleProducto implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_det_pro", unique=true, nullable=false)
	private Integer idDetPro;

	@Column(nullable=false, length=50)
	private String descripcion;

	@Column(nullable=false, length=30)
	private String talla;

	//bi-directional many-to-one association to DetalleFactura
	@OneToMany(mappedBy="detalleProducto")
	private List<DetalleFactura> detalleFacturas;

	//bi-directional many-to-one association to DetalleFactura
	@ManyToOne
	@JoinColumn(name="id_det_fac", nullable=false)
	private DetalleFactura detalleFactura;

	//bi-directional many-to-one association to Producto
	@ManyToOne
	@JoinColumn(name="id_prod")
	private Producto producto;

	public DetalleProducto() {
	}

	public Integer getIdDetPro() {
		return this.idDetPro;
	}

	public void setIdDetPro(Integer idDetPro) {
		this.idDetPro = idDetPro;
	}

	public String getDescripcion() {
		return this.descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getTalla() {
		return this.talla;
	}

	public void setTalla(String talla) {
		this.talla = talla;
	}

	public List<DetalleFactura> getDetalleFacturas() {
		return this.detalleFacturas;
	}

	public void setDetalleFacturas(List<DetalleFactura> detalleFacturas) {
		this.detalleFacturas = detalleFacturas;
	}

	public DetalleFactura addDetalleFactura(DetalleFactura detalleFactura) {
		getDetalleFacturas().add(detalleFactura);
		detalleFactura.setDetalleProducto(this);

		return detalleFactura;
	}

	public DetalleFactura removeDetalleFactura(DetalleFactura detalleFactura) {
		getDetalleFacturas().remove(detalleFactura);
		detalleFactura.setDetalleProducto(null);

		return detalleFactura;
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

}
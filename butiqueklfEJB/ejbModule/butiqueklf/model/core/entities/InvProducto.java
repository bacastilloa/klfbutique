package butiqueklf.model.core.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;


/**
 * The persistent class for the inv_producto database table.
 * 
 */
@Entity
@Table(name="inv_producto")
@NamedQuery(name="InvProducto.findAll", query="SELECT i FROM InvProducto i")
public class InvProducto implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="id_prod", unique=true, nullable=false)
	private Integer idProd;

	@Column(nullable=false, length=50)
	private String descripcion;

	@Column(name="nombre_producto", nullable=false, length=50)
	private String nombreProducto;

	@Column(nullable=false, precision=7, scale=2)
	private BigDecimal precio;

	//bi-directional many-to-one association to CompProveedorDetalle
	@OneToMany(mappedBy="invProducto")
	private List<CompProveedorDetalle> compProveedorDetalles;

	//bi-directional many-to-one association to InvInventario
	@OneToMany(mappedBy="invProducto")
	private List<InvInventario> invInventarios;

	//bi-directional many-to-one association to InvTalla
	@ManyToOne
	@JoinColumn(name="id_inv_talla")
	private InvTalla invTalla;

	public InvProducto() {
	}

	public Integer getIdProd() {
		return this.idProd;
	}

	public void setIdProd(Integer idProd) {
		this.idProd = idProd;
	}

	public String getDescripcion() {
		return this.descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getNombreProducto() {
		return this.nombreProducto;
	}

	public void setNombreProducto(String nombreProducto) {
		this.nombreProducto = nombreProducto;
	}

	public BigDecimal getPrecio() {
		return this.precio;
	}

	public void setPrecio(BigDecimal precio) {
		this.precio = precio;
	}

	public List<CompProveedorDetalle> getCompProveedorDetalles() {
		return this.compProveedorDetalles;
	}

	public void setCompProveedorDetalles(List<CompProveedorDetalle> compProveedorDetalles) {
		this.compProveedorDetalles = compProveedorDetalles;
	}

	public CompProveedorDetalle addCompProveedorDetalle(CompProveedorDetalle compProveedorDetalle) {
		getCompProveedorDetalles().add(compProveedorDetalle);
		compProveedorDetalle.setInvProducto(this);

		return compProveedorDetalle;
	}

	public CompProveedorDetalle removeCompProveedorDetalle(CompProveedorDetalle compProveedorDetalle) {
		getCompProveedorDetalles().remove(compProveedorDetalle);
		compProveedorDetalle.setInvProducto(null);

		return compProveedorDetalle;
	}

	public List<InvInventario> getInvInventarios() {
		return this.invInventarios;
	}

	public void setInvInventarios(List<InvInventario> invInventarios) {
		this.invInventarios = invInventarios;
	}

	public InvInventario addInvInventario(InvInventario invInventario) {
		getInvInventarios().add(invInventario);
		invInventario.setInvProducto(this);

		return invInventario;
	}

	public InvInventario removeInvInventario(InvInventario invInventario) {
		getInvInventarios().remove(invInventario);
		invInventario.setInvProducto(null);

		return invInventario;
	}

	public InvTalla getInvTalla() {
		return this.invTalla;
	}

	public void setInvTalla(InvTalla invTalla) {
		this.invTalla = invTalla;
	}

}
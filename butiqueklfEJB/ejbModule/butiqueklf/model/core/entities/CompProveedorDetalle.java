package butiqueklf.model.core.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;


/**
 * The persistent class for the comp_proveedor_detalle database table.
 * 
 */
@Entity
@Table(name="comp_proveedor_detalle")
@NamedQuery(name="CompProveedorDetalle.findAll", query="SELECT c FROM CompProveedorDetalle c")
public class CompProveedorDetalle implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="id_comp_proveedor_detalle", unique=true, nullable=false)
	private Integer idCompProveedorDetalle;

	@Column(name="cantidad_comp", nullable=false, precision=131089)
	private BigDecimal cantidadComp;

	@Column(name="precio_comp", nullable=false, precision=8, scale=2)
	private BigDecimal precioComp;

	@Column(name="total_comp", nullable=false, precision=8, scale=2)
	private BigDecimal totalComp;

	//bi-directional many-to-one association to CompProveedor
	@ManyToOne
	@JoinColumn(name="ruc_comp_proveedor")
	private CompProveedor compProveedor;

	//bi-directional many-to-one association to InvProducto
	@ManyToOne
	@JoinColumn(name="id_prod")
	private InvProducto invProducto;

	//bi-directional many-to-one association to InvInventario
	@OneToMany(mappedBy="compProveedorDetalle")
	private List<InvInventario> invInventarios;

	public CompProveedorDetalle() {
	}

	public Integer getIdCompProveedorDetalle() {
		return this.idCompProveedorDetalle;
	}

	public void setIdCompProveedorDetalle(Integer idCompProveedorDetalle) {
		this.idCompProveedorDetalle = idCompProveedorDetalle;
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

	public CompProveedor getCompProveedor() {
		return this.compProveedor;
	}

	public void setCompProveedor(CompProveedor compProveedor) {
		this.compProveedor = compProveedor;
	}

	public InvProducto getInvProducto() {
		return this.invProducto;
	}

	public void setInvProducto(InvProducto invProducto) {
		this.invProducto = invProducto;
	}

	public List<InvInventario> getInvInventarios() {
		return this.invInventarios;
	}

	public void setInvInventarios(List<InvInventario> invInventarios) {
		this.invInventarios = invInventarios;
	}

	public InvInventario addInvInventario(InvInventario invInventario) {
		getInvInventarios().add(invInventario);
		invInventario.setCompProveedorDetalle(this);

		return invInventario;
	}

	public InvInventario removeInvInventario(InvInventario invInventario) {
		getInvInventarios().remove(invInventario);
		invInventario.setCompProveedorDetalle(null);

		return invInventario;
	}

}
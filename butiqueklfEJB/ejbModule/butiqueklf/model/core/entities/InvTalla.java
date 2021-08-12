package butiqueklf.model.core.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the inv_talla database table.
 * 
 */
@Entity
@Table(name="inv_talla")
@NamedQuery(name="InvTalla.findAll", query="SELECT i FROM InvTalla i")
public class InvTalla implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="id_inv_talla", unique=true, nullable=false)
	private Integer idInvTalla;

	@Column(name="nombre_talla", nullable=false, length=30)
	private String nombreTalla;

	//bi-directional many-to-one association to InvProducto
	@OneToMany(mappedBy="invTalla")
	private List<InvProducto> invProductos;

	public InvTalla() {
	}

	public Integer getIdInvTalla() {
		return this.idInvTalla;
	}

	public void setIdInvTalla(Integer idInvTalla) {
		this.idInvTalla = idInvTalla;
	}

	public String getNombreTalla() {
		return this.nombreTalla;
	}

	public void setNombreTalla(String nombreTalla) {
		this.nombreTalla = nombreTalla;
	}

	public List<InvProducto> getInvProductos() {
		return this.invProductos;
	}

	public void setInvProductos(List<InvProducto> invProductos) {
		this.invProductos = invProductos;
	}

	public InvProducto addInvProducto(InvProducto invProducto) {
		getInvProductos().add(invProducto);
		invProducto.setInvTalla(this);

		return invProducto;
	}

	public InvProducto removeInvProducto(InvProducto invProducto) {
		getInvProductos().remove(invProducto);
		invProducto.setInvTalla(null);

		return invProducto;
	}

}
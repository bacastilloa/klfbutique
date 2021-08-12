package butiqueklf.model.core.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the comp_proveedor database table.
 * 
 */
@Entity
@Table(name="comp_proveedor")
@NamedQuery(name="CompProveedor.findAll", query="SELECT c FROM CompProveedor c")
public class CompProveedor implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="ruc_comp_proveedor", unique=true, nullable=false, length=13)
	private String rucCompProveedor;

	@Column(name="apellido_prove", nullable=false, length=50)
	private String apellidoProve;

	@Column(name="correo_prive", nullable=false, length=50)
	private String correoPrive;

	@Column(name="direccion_prove", nullable=false, length=50)
	private String direccionProve;

	@Column(name="nombre_prove", nullable=false, length=50)
	private String nombreProve;

	@Column(name="telefono_prove", nullable=false, length=50)
	private String telefonoProve;

	//bi-directional many-to-one association to CompProveedorDetalle
	@OneToMany(mappedBy="compProveedor")
	private List<CompProveedorDetalle> compProveedorDetalles;

	public CompProveedor() {
	}

	public String getRucCompProveedor() {
		return this.rucCompProveedor;
	}

	public void setRucCompProveedor(String rucCompProveedor) {
		this.rucCompProveedor = rucCompProveedor;
	}

	public String getApellidoProve() {
		return this.apellidoProve;
	}

	public void setApellidoProve(String apellidoProve) {
		this.apellidoProve = apellidoProve;
	}

	public String getCorreoPrive() {
		return this.correoPrive;
	}

	public void setCorreoPrive(String correoPrive) {
		this.correoPrive = correoPrive;
	}

	public String getDireccionProve() {
		return this.direccionProve;
	}

	public void setDireccionProve(String direccionProve) {
		this.direccionProve = direccionProve;
	}

	public String getNombreProve() {
		return this.nombreProve;
	}

	public void setNombreProve(String nombreProve) {
		this.nombreProve = nombreProve;
	}

	public String getTelefonoProve() {
		return this.telefonoProve;
	}

	public void setTelefonoProve(String telefonoProve) {
		this.telefonoProve = telefonoProve;
	}

	public List<CompProveedorDetalle> getCompProveedorDetalles() {
		return this.compProveedorDetalles;
	}

	public void setCompProveedorDetalles(List<CompProveedorDetalle> compProveedorDetalles) {
		this.compProveedorDetalles = compProveedorDetalles;
	}

	public CompProveedorDetalle addCompProveedorDetalle(CompProveedorDetalle compProveedorDetalle) {
		getCompProveedorDetalles().add(compProveedorDetalle);
		compProveedorDetalle.setCompProveedor(this);

		return compProveedorDetalle;
	}

	public CompProveedorDetalle removeCompProveedorDetalle(CompProveedorDetalle compProveedorDetalle) {
		getCompProveedorDetalles().remove(compProveedorDetalle);
		compProveedorDetalle.setCompProveedor(null);

		return compProveedorDetalle;
	}

}
package butiqueklf.model.core.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the proveedores database table.
 * 
 */
@Entity
@Table(name="proveedores")
@NamedQuery(name="Proveedore.findAll", query="SELECT p FROM Proveedore p")
public class Proveedore implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="ruc_prove", unique=true, nullable=false, precision=13)
	private long rucProve;

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

	//bi-directional many-to-one association to DetalleCompra
	@OneToMany(mappedBy="proveedore")
	private List<DetalleCompra> detalleCompras;

	public Proveedore() {
	}

	public long getRucProve() {
		return this.rucProve;
	}

	public void setRucProve(long rucProve) {
		this.rucProve = rucProve;
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

	public List<DetalleCompra> getDetalleCompras() {
		return this.detalleCompras;
	}

	public void setDetalleCompras(List<DetalleCompra> detalleCompras) {
		this.detalleCompras = detalleCompras;
	}

	public DetalleCompra addDetalleCompra(DetalleCompra detalleCompra) {
		getDetalleCompras().add(detalleCompra);
		detalleCompra.setProveedore(this);

		return detalleCompra;
	}

	public DetalleCompra removeDetalleCompra(DetalleCompra detalleCompra) {
		getDetalleCompras().remove(detalleCompra);
		detalleCompra.setProveedore(null);

		return detalleCompra;
	}

}
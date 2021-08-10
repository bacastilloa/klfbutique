package butiqueklf.model.core.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the cliente database table.
 * 
 */
@Entity
@Table(name="cliente")
@NamedQuery(name="Cliente.findAll", query="SELECT c FROM Cliente c")
public class Cliente implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="cedula_cli", unique=true, nullable=false, length=10)
	private String cedulaCli;

	@Column(name="apellido_cli", nullable=false, length=50)
	private String apellidoCli;

	@Column(name="correo_cli", nullable=false, length=50)
	private String correoCli;

	@Column(name="direccion_cli", nullable=false, length=50)
	private String direccionCli;

	@Column(name="nombre_cli", nullable=false, length=50)
	private String nombreCli;

	@Column(name="telefono_cli", nullable=false, length=50)
	private String telefonoCli;

	//bi-directional many-to-one association to FacturaCabezera
	@OneToMany(mappedBy="cliente")
	private List<FacturaCabezera> facturaCabezeras;

	public Cliente() {
	}

	public String getCedulaCli() {
		return this.cedulaCli;
	}

	public void setCedulaCli(String cedulaCli) {
		this.cedulaCli = cedulaCli;
	}

	public String getApellidoCli() {
		return this.apellidoCli;
	}

	public void setApellidoCli(String apellidoCli) {
		this.apellidoCli = apellidoCli;
	}

	public String getCorreoCli() {
		return this.correoCli;
	}

	public void setCorreoCli(String correoCli) {
		this.correoCli = correoCli;
	}

	public String getDireccionCli() {
		return this.direccionCli;
	}

	public void setDireccionCli(String direccionCli) {
		this.direccionCli = direccionCli;
	}

	public String getNombreCli() {
		return this.nombreCli;
	}

	public void setNombreCli(String nombreCli) {
		this.nombreCli = nombreCli;
	}

	public String getTelefonoCli() {
		return this.telefonoCli;
	}

	public void setTelefonoCli(String telefonoCli) {
		this.telefonoCli = telefonoCli;
	}

	public List<FacturaCabezera> getFacturaCabezeras() {
		return this.facturaCabezeras;
	}

	public void setFacturaCabezeras(List<FacturaCabezera> facturaCabezeras) {
		this.facturaCabezeras = facturaCabezeras;
	}

	public FacturaCabezera addFacturaCabezera(FacturaCabezera facturaCabezera) {
		getFacturaCabezeras().add(facturaCabezera);
		facturaCabezera.setCliente(this);

		return facturaCabezera;
	}

	public FacturaCabezera removeFacturaCabezera(FacturaCabezera facturaCabezera) {
		getFacturaCabezeras().remove(facturaCabezera);
		facturaCabezera.setCliente(null);

		return facturaCabezera;
	}

}
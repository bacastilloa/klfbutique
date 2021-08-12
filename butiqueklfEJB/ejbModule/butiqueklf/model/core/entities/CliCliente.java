package butiqueklf.model.core.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the cli_cliente database table.
 * 
 */
@Entity
@Table(name="cli_cliente")
@NamedQuery(name="CliCliente.findAll", query="SELECT c FROM CliCliente c")
public class CliCliente implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="cedula_cliente", unique=true, nullable=false, length=10)
	private String cedulaCliente;

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

	//bi-directional many-to-one association to VenFacturaCabezera
	@OneToMany(mappedBy="cliCliente")
	private List<VenFacturaCabezera> venFacturaCabezeras;

	public CliCliente() {
	}

	public String getCedulaCliente() {
		return this.cedulaCliente;
	}

	public void setCedulaCliente(String cedulaCliente) {
		this.cedulaCliente = cedulaCliente;
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

	public List<VenFacturaCabezera> getVenFacturaCabezeras() {
		return this.venFacturaCabezeras;
	}

	public void setVenFacturaCabezeras(List<VenFacturaCabezera> venFacturaCabezeras) {
		this.venFacturaCabezeras = venFacturaCabezeras;
	}

	public VenFacturaCabezera addVenFacturaCabezera(VenFacturaCabezera venFacturaCabezera) {
		getVenFacturaCabezeras().add(venFacturaCabezera);
		venFacturaCabezera.setCliCliente(this);

		return venFacturaCabezera;
	}

	public VenFacturaCabezera removeVenFacturaCabezera(VenFacturaCabezera venFacturaCabezera) {
		getVenFacturaCabezeras().remove(venFacturaCabezera);
		venFacturaCabezera.setCliCliente(null);

		return venFacturaCabezera;
	}

}
package butiqueklf.model.core.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the factura_cabezera database table.
 * 
 */
@Entity
@Table(name="factura_cabezera")
@NamedQuery(name="FacturaCabezera.findAll", query="SELECT f FROM FacturaCabezera f")
public class FacturaCabezera implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_fac_cabe", unique=true, nullable=false)
	private Integer idFacCabe;

	@Temporal(TemporalType.DATE)
	@Column(nullable=false)
	private Date fecha;

	//bi-directional many-to-one association to Factura
	@OneToMany(mappedBy="facturaCabezera")
	private List<Factura> facturas;

	//bi-directional many-to-one association to Cliente
	@ManyToOne
	@JoinColumn(name="cedula_cli")
	private Cliente cliente;

	public FacturaCabezera() {
	}

	public Integer getIdFacCabe() {
		return this.idFacCabe;
	}

	public void setIdFacCabe(Integer idFacCabe) {
		this.idFacCabe = idFacCabe;
	}

	public Date getFecha() {
		return this.fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public List<Factura> getFacturas() {
		return this.facturas;
	}

	public void setFacturas(List<Factura> facturas) {
		this.facturas = facturas;
	}

	public Factura addFactura(Factura factura) {
		getFacturas().add(factura);
		factura.setFacturaCabezera(this);

		return factura;
	}

	public Factura removeFactura(Factura factura) {
		getFacturas().remove(factura);
		factura.setFacturaCabezera(null);

		return factura;
	}

	public Cliente getCliente() {
		return this.cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

}
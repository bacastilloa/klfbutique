package butiqueklf.controller.usuarios;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import butiqueklf.controller.JSFUtil;
import butiqueklf.model.core.entities.SegModulo;
import butiqueklf.model.usuario.manager.ManagerUsuario;

@Named
@SessionScoped
public class BeanUsuModulos implements Serializable {
	
	private static final long serialVersionUID = 1L;
	@EJB
	private ManagerUsuario mUsuario;
	private List<SegModulo> listaModulos;
	private SegModulo nuevoModulo;
	private SegModulo edicionModulo;

	public BeanUsuModulos() {
		// TODO Auto-generated constructor stub
	}

	@PostConstruct
	public void inicializacion() {
		System.out.println("BeanSegModulos inicializado...");
		nuevoModulo=new SegModulo();
	}
	
	public String actionCargarMenuModulos() {
		listaModulos=mUsuario.findAllModulos();
		return "modulos?faces-redirect=true";
	}
	
	public void actionListenerInsertarModulo() {
		try {
			mUsuario.insertarModulo(nuevoModulo);
			listaModulos=mUsuario.findAllModulos();
			JSFUtil.crearMensajeINFO("Módulo creado.");
			nuevoModulo=new SegModulo();
		} catch (Exception e) {
			JSFUtil.crearMensajeERROR(e.getMessage());
			e.printStackTrace();
		}
	}
	
	public void actionListenerCargarModulo(SegModulo modulo) {
		edicionModulo=modulo;
	}
	
	public void actionListenerGuardarEdicionModulo() {
		try {
			mUsuario.actualizarModulo(edicionModulo);
			JSFUtil.crearMensajeINFO("Módulo editado.");
		} catch (Exception e) {
			JSFUtil.crearMensajeERROR(e.getMessage());
			e.printStackTrace();
		}
		
	}
	
	public void actionListenerEliminarModulo(int idSegModulo) {
		try {
			mUsuario.eliminarModulo(idSegModulo);
			listaModulos=mUsuario.findAllModulos();
			JSFUtil.crearMensajeINFO("Módulo eliminado");
		} catch (Exception e) {
			JSFUtil.crearMensajeERROR(e.getMessage());
			e.printStackTrace();
		}
	}

	public List<SegModulo> getListaModulos() {
		return listaModulos;
	}

	public void setListaModulos(List<SegModulo> listaModulos) {
		this.listaModulos = listaModulos;
	}
	
	public SegModulo getNuevoModulo() {
		return nuevoModulo;
	}
	public void setNuevoModulo(SegModulo nuevoModulo) {
		this.nuevoModulo = nuevoModulo;
	}
	public SegModulo getEdicionModulo() {
		return edicionModulo;
	}
	public void setEdicionModulo(SegModulo edicionModulo) {
		this.edicionModulo = edicionModulo;
	}
}

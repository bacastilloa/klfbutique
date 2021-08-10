package butiqueklf.controller.usuarios;

import java.io.Serializable;
import java.util.List;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

import butiqueklf.controller.JSFUtil;
import butiqueklf.controller.usuarios.BeanUsuLogin;
import butiqueklf.model.core.entities.SegUsuario;
import butiqueklf.model.usuario.manager.ManagerUsuario;

@Named
@SessionScoped
public class BeanUsuUsuario implements Serializable {

	private static final long serialVersionUID = 1L;

	@EJB
	private ManagerUsuario managerUsuario;
	
	private List<SegUsuario> listaUsuarios;
	private SegUsuario nuevoUsuario;
	private SegUsuario edicionUsuario;
	
	@Inject
	private BeanUsuLogin beanUsuLogin;
	
	public BeanUsuUsuario() {
		// TODO Auto-generated constructor stub
	}

	public String actionMenuUsuarios() {
		listaUsuarios=managerUsuario.findAllUsuarios();
		return "usuarios";
	}
	
	public void actionListenerActivarDesactivarUsuario(int idSegUsuario) {
		try {
			managerUsuario.activarDesactivarUsuario(idSegUsuario);
			listaUsuarios=managerUsuario.findAllUsuarios();
			JSFUtil.crearMensajeINFO("Usuario activado/desactivado");
		} catch (Exception e) {
			JSFUtil.crearMensajeERROR(e.getMessage());
			e.printStackTrace();
		}
	}
	
	public String actionMenuNuevoUsuario() {
		nuevoUsuario=new SegUsuario();
		nuevoUsuario.setActivo(true);
		return "usuarios_nuevo";
	}
	
	public void actionListenerInsertarNuevoUsuario() {
		try {
			managerUsuario.insertarUsuario(nuevoUsuario);
			listaUsuarios=managerUsuario.findAllUsuarios();
			nuevoUsuario=new SegUsuario();
			nuevoUsuario.setActivo(true);
			JSFUtil.crearMensajeINFO("Usuario insertado.");
		} catch (Exception e) {
			JSFUtil.crearMensajeERROR(e.getMessage());
			e.printStackTrace();
		}
	}
	
	public String actionSeleccionarEdicionUsuario(SegUsuario usuario) {
		edicionUsuario=usuario;
		return "usuarios_edicion";
	}
	
	public void actionListenerActualizarEdicionUsuario() {
		try {
			managerUsuario.actualizarUsuario(beanUsuLogin.getLoginDTO(),edicionUsuario);
			listaUsuarios=managerUsuario.findAllUsuarios();
			JSFUtil.crearMensajeINFO("Usuario actualizado.");
		} catch (Exception e) {
			JSFUtil.crearMensajeERROR(e.getMessage());
			e.printStackTrace();
		}
	}
	
	public void actionListenerEliminarUsuario(int idSegUsuario) {
		try {
			managerUsuario.eliminarUsuario(idSegUsuario);
			listaUsuarios=managerUsuario.findAllUsuarios();
			JSFUtil.crearMensajeINFO("Usuario eliminado.");
		} catch (Exception e) {
			JSFUtil.crearMensajeERROR(e.getMessage());
			e.printStackTrace();
		}
	}

	public List<SegUsuario> getListaUsuarios() {
		return listaUsuarios;
	}


	public void setListaUsuarios(List<SegUsuario> listaUsuarios) {
		this.listaUsuarios = listaUsuarios;
	}

	public SegUsuario getNuevoUsuario() {
		return nuevoUsuario;
	}

	public void setNuevoUsuario(SegUsuario nuevoUsuario) {
		this.nuevoUsuario = nuevoUsuario;
	}

	public SegUsuario getEdicionUsuario() {
		return edicionUsuario;
	}

	public void setEdicionUsuario(SegUsuario edicionUsuario) {
		this.edicionUsuario = edicionUsuario;
	}

	public BeanUsuLogin getBeanSegLogin() {
		return beanUsuLogin;
	}

	public void setBeanUsuLogin(BeanUsuLogin beanSegLogin) {
		this.beanUsuLogin = beanSegLogin;
	}
}

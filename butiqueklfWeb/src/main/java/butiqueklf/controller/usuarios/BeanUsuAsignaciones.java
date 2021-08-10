package butiqueklf.controller.usuarios;

import java.io.Serializable;
import java.util.List;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import butiqueklf.controller.JSFUtil;
import butiqueklf.model.core.entities.SegAsignacion;
import butiqueklf.model.core.entities.SegModulo;
import butiqueklf.model.core.entities.SegUsuario;
import butiqueklf.model.usuario.manager.ManagerUsuario;

@Named
@SessionScoped
public class BeanUsuAsignaciones implements Serializable {
	private static final long serialVersionUID = 1L;

	@EJB
	private ManagerUsuario managerUsuario;
	
	private List<SegUsuario> listaUsuarios;
	private List<SegModulo> listaModulos;
	private int idSegUsuarioSeleccionado;
	private List<SegAsignacion> listaAsignaciones;
	
	public BeanUsuAsignaciones() {
		// TODO Auto-generated constructor stub
	}

	public String actionMenuAsignaciones() {
		listaUsuarios=managerUsuario.findAllUsuarios();
		listaModulos=managerUsuario.findAllModulos();
		return "asignaciones";
	}
	
	public void actionListenerSeleccionarUsuario() {
		listaAsignaciones=managerUsuario.findAsignacionByUsuario(idSegUsuarioSeleccionado);
	}
	
	public void actionListenerAsignarModulo(int idSegModulo) {
		try {
			managerUsuario.asignarModulo(idSegUsuarioSeleccionado, idSegModulo);
			listaAsignaciones=managerUsuario.findAsignacionByUsuario(idSegUsuarioSeleccionado);
			JSFUtil.crearMensajeINFO("Módulo asignado.");
		} catch (Exception e) {
			JSFUtil.crearMensajeERROR(e.getMessage());
			e.printStackTrace();
		}
	}
	
	public void actionListenerEliminarAsignacionModulo(int idSegAsignacion) {
		try {
			managerUsuario.eliminarAsignacion(idSegAsignacion);
			listaAsignaciones=managerUsuario.findAsignacionByUsuario(idSegUsuarioSeleccionado);
			JSFUtil.crearMensajeINFO("Asignación eliminada.");
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

	public List<SegModulo> getListaModulos() {
		return listaModulos;
	}

	public void setListaModulos(List<SegModulo> listaModulos) {
		this.listaModulos = listaModulos;
	}

	public int getIdSegUsuarioSeleccionado() {
		return idSegUsuarioSeleccionado;
	}

	public void setIdSegUsuarioSeleccionado(int idSegUsuarioSeleccionado) {
		this.idSegUsuarioSeleccionado = idSegUsuarioSeleccionado;
	}

	public List<SegAsignacion> getListaAsignaciones() {
		return listaAsignaciones;
	}

	public void setListaAsignaciones(List<SegAsignacion> listaAsignaciones) {
		this.listaAsignaciones = listaAsignaciones;
	}
}

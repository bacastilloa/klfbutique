package butiqueklf.model.usuario.manager;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import butiqueklf.model.auditoria.managers.ManagerAuditoria;
import butiqueklf.model.core.entities.SegAsignacion;
import butiqueklf.model.core.entities.SegModulo;
import butiqueklf.model.core.entities.SegUsuario;
import butiqueklf.model.core.managers.ManagerDAO;
import butiqueklf.model.core.util.ModelUtil;
import butiqueklf.model.usuario.dtos.LoginDTO;


@Stateless
@LocalBean
public class ManagerUsuario {
	@EJB
	private ManagerDAO mDAO;
	@EJB
	private ManagerAuditoria mAuditoria;
    /**
     * Default constructor. 
     */
    public ManagerUsuario() {
        
    }
    /**
     * Funcion de inicializacion de datos de usuarios.
     */
    public void inicializarDemo() throws Exception {
    	mAuditoria.mostrarLog(getClass(), "inicializarDemo", "Inicializacion de bdd demo.");
    	List<SegUsuario> listaUsuarios=mDAO.findAll(SegUsuario.class);
    	int idSegUsuarioAdmin=0;
    	
    	boolean existeAdministrador=false;
    	for(SegUsuario u:listaUsuarios) {
    		mAuditoria.mostrarLog(getClass(), "inicializarDemo", "Info de usuario "+u.getCorreo()+" "+u.getIdSegUsuario());
    		//Se considera al usuario 1 como administrador: 
    		if(u.getIdSegUsuario()==1) {
    			existeAdministrador=true;
    			idSegUsuarioAdmin=1;
    			System.out.println("Ya existe un usuario administrador (con id usuario 1)");
    		}
    	}
    	
    	
    	//creacion del usuario administrador:
    	if(existeAdministrador==false) {
			SegUsuario administrador=new SegUsuario();
			administrador.setActivo(true);
			administrador.setApellidos("Castañeda");
			administrador.setClave("12345");
			administrador.setCorreo("dcastañeda@butiqueklf.com");
			administrador.setNombres("Dimas");
			administrador.setCodigo("admin");
			mDAO.insertar(administrador);
			idSegUsuarioAdmin=administrador.getIdSegUsuario();
			mAuditoria.mostrarLog(getClass(), "inicializarDemo", "Usuario administrador creado (id : "+idSegUsuarioAdmin);
    	}
		//inicializacion de modulos:
		SegModulo modulo=new SegModulo();
		int idSegModuloUsuarios=0;
		int idSegModuloAuditoria=0;
		modulo.setNombreModulo("Usuarios");
		modulo.setRutaAcceso("usuarios/menu");
		mDAO.insertar(modulo);
		idSegModuloUsuarios=modulo.getIdSegModulo();
		modulo=new SegModulo();
		modulo.setNombreModulo("Auditoría");
		modulo.setRutaAcceso("auditoria/menu");
		mDAO.insertar(modulo);
		idSegModuloAuditoria=modulo.getIdSegModulo();
		mAuditoria.mostrarLog(getClass(), "inicializarDemo", "Módulos creados.");
		//asignacion de accesos:
		asignarModulo(idSegUsuarioAdmin, idSegModuloUsuarios);
		asignarModulo(idSegUsuarioAdmin, idSegModuloAuditoria);
		mAuditoria.mostrarLog(getClass(), "inicializarDemo", "Inicializacion de bdd demo terminada.");
    }
    

    public LoginDTO login(int idSegUsuario,String clave,String direccionIP) throws Exception{
    	//crear DTO:
		LoginDTO loginDTO=new LoginDTO();
		loginDTO.setIdSegUsuario(idSegUsuario);
		loginDTO.setDireccionIP(direccionIP);
		
    	if(ModelUtil.isEmpty(clave)) {
    		mAuditoria.mostrarLog(getClass(), "login", "Debe indicar una clave "+idSegUsuario);
    		throw new Exception("Debe indicar una clave");
    	}
    	SegUsuario usuario=(SegUsuario) mDAO.findById(SegUsuario.class, idSegUsuario);
    	if(usuario==null) {
    		mAuditoria.mostrarLog(getClass(), "login", "No existe usuario "+idSegUsuario);
    		throw new Exception("Error en credenciales.");
    	}
    		
    	if(usuario.getClave().equals(clave)) {
    		if(usuario.getActivo()==false) {
        		mAuditoria.mostrarLog(getClass(), "login", "Intento de login usuario desactivado "+idSegUsuario);
        		throw new Exception("El usuario esta desactivado.");
        	}
    		mAuditoria.mostrarLog(loginDTO, getClass(), "login", "Login exitoso "+idSegUsuario);
    		
    		loginDTO.setCorreo(usuario.getCorreo());
    		//aqui puede realizarse el envio de correo de notificacion.
    		
    		//obtener la lista de modulos a los que tiene acceso:
    		List<SegAsignacion> listaAsignaciones=findAsignacionByUsuario(usuario.getIdSegUsuario());
    		for(SegAsignacion asig:listaAsignaciones) {
    			SegModulo modulo=asig.getSegModulo();
    			loginDTO.getListaModulos().add(modulo);
    		}
    		return loginDTO;
    	}
    	mAuditoria.mostrarLog(getClass(), "login", "No coincide la clave "+idSegUsuario);
    	throw new Exception("Error en credenciales");
    }
    
    public void cerrarSesion(int idSegUsuario) {
    	mAuditoria.mostrarLog(getClass(), "cerrarSesion", "Cerrar sesión usuario: "+idSegUsuario);
    }
    
    public void accesoNoPermitido(int idSegUsuario,String ruta) {
    	mAuditoria.mostrarLog(getClass(), "accesoNoPermitido", "Usuario "+idSegUsuario+" intento no autorizado a "+ruta);
    }
    
    public List<SegUsuario> findAllUsuarios(){
    	return mDAO.findAll(SegUsuario.class, "apellidos");
    }
    
    public SegUsuario findByIdSegUsuario(int idSegUsuario) throws Exception {
    	return (SegUsuario) mDAO.findById(SegUsuario.class, idSegUsuario);
    }
    
    public void insertarUsuario(SegUsuario nuevoUsuario) throws Exception {
    	nuevoUsuario.setCodigo("n/a");
    	mDAO.insertar(nuevoUsuario);
    }
    
    public void actualizarUsuario(LoginDTO loginDTO,SegUsuario edicionUsuario) throws Exception {
    	SegUsuario usuario=(SegUsuario) mDAO.findById(SegUsuario.class, edicionUsuario.getIdSegUsuario());
    	usuario.setApellidos(edicionUsuario.getApellidos());
    	usuario.setClave(edicionUsuario.getClave());
    	usuario.setCorreo(edicionUsuario.getCorreo());
    	usuario.setCodigo(edicionUsuario.getCodigo());
    	usuario.setNombres(edicionUsuario.getNombres());
    	mDAO.actualizar(usuario);
    	mAuditoria.mostrarLog(loginDTO, getClass(), "actualizarUsuario", "se actualizó al usuario "+usuario.getApellidos());
    }
    
    public void activarDesactivarUsuario(int idSegUsuario) throws Exception {
    	SegUsuario usuario=(SegUsuario) mDAO.findById(SegUsuario.class, idSegUsuario);
    	if(idSegUsuario==1)
    		throw new Exception("No se puede desactivar al usuario administrador.");
    	usuario.setActivo(!usuario.getActivo());
    	System.out.println("activar/desactivar "+usuario.getActivo());
    	mDAO.actualizar(usuario);
    }
    
    public void eliminarUsuario(int idSegUsuario) throws Exception {
    	SegUsuario usuario=(SegUsuario) mDAO.findById(SegUsuario.class, idSegUsuario);
    	if(usuario.getIdSegUsuario()==1)
    		throw new Exception("No se puede eliminar el usuario administrador.");
    	if(usuario.getSegAsignacions().size()>0)
    		throw new Exception("No se puede elimininar el usuario porque tiene asignaciones de módulos.");
    	mDAO.eliminar(SegUsuario.class, usuario.getIdSegUsuario());
    }
    
    public List<SegModulo> findAllModulos(){
    	return mDAO.findAll(SegModulo.class, "nombreModulo");
    }
    
    public SegModulo insertarModulo(SegModulo nuevoModulo) throws Exception {
    	SegModulo modulo=new SegModulo();
    	modulo.setNombreModulo(nuevoModulo.getNombreModulo());
    	modulo.setRutaAcceso(nuevoModulo.getRutaAcceso());
    	mDAO.insertar(modulo);
    	return modulo;
    }
    
    public void eliminarModulo(int idSegModulo) throws Exception {
    	mDAO.eliminar(SegModulo.class, idSegModulo);
    }
    
    public void actualizarModulo(SegModulo edicionModulo) throws Exception {
    	SegModulo modulo=(SegModulo) mDAO.findById(SegModulo.class, edicionModulo.getIdSegModulo());
    	modulo.setNombreModulo(edicionModulo.getNombreModulo());
    	modulo.setRutaAcceso(edicionModulo.getRutaAcceso());
    	mDAO.actualizar(modulo);
    }
    
    public List<SegAsignacion> findAsignacionByUsuario(int idSegUsuario){
    	String consulta="o.segUsuario.idSegUsuario="+idSegUsuario;
		List<SegAsignacion> listaAsignaciones=mDAO.findWhere(SegAsignacion.class, consulta, null);
		return listaAsignaciones;
    }

    public void asignarModulo(int idSegUsuario,int idSegModulo) throws Exception{
    	//Buscar los objetos primarios:
    	SegUsuario usuario=(SegUsuario)mDAO.findById(SegUsuario.class, idSegUsuario);
    	SegModulo modulo=(SegModulo)mDAO.findById(SegModulo.class, idSegModulo);
    	//crear la relacion:
    	SegAsignacion asignacion=new SegAsignacion();
    	asignacion.setSegModulo(modulo);
    	asignacion.setSegUsuario(usuario);
    	//guardar la asignacion:
    	mDAO.insertar(asignacion);
    	mAuditoria.mostrarLog(getClass(), "asignarModulo", "Modulo "+idSegModulo+" asigando a usuario "+idSegUsuario);
    }
    
    public void eliminarAsignacion(int idSegAsignacion) throws Exception {
    	mDAO.eliminar(SegAsignacion.class, idSegAsignacion);
    }

}

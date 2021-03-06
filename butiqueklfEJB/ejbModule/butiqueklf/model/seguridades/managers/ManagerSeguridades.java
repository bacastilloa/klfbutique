package butiqueklf.model.seguridades.managers;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import butiqueklf.model.core.entities.SegAsignacion;
import butiqueklf.model.core.entities.SegModulo;
import butiqueklf.model.core.entities.SegUsuario;
import butiqueklf.model.core.managers.ManagerDAO;
import butiqueklf.model.core.utils.ModelUtil;
import butiqueklf.model.seguridades.dtos.LoginDTO;

/**
 * Implementa la logica de manejo de usuarios y autenticacion.
 * Funcionalidades principales:
 * <ul>
 * 	<li>Verificacion de credenciales de usuario.</li>
 *  <li>Asignacion de modulos a un usuario.</li>
 * </ul>
 * @author mrea
 */
@Stateless
@LocalBean
public class ManagerSeguridades {
	@EJB
	private ManagerDAO mDAO;
    /**
     * Default constructor. 
     */
    public ManagerSeguridades() {
        
    }
    /**
     * Funcion de inicializacion de datos de usuarios.
     */
    public void inicializarDemo() throws Exception {
    	List<SegUsuario> listaUsuarios=mDAO.findAll(SegUsuario.class);
    	int idSegUsuarioAdmin=0;
    	
    	boolean existeAdministrador=false;
    	for(SegUsuario u:listaUsuarios) {
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
			administrador.setClave("dimas123");
			administrador.setCodigo("admin");
			administrador.setCorreo("dcastañeda@butiqueklf.com");
			administrador.setNombres("Dimas");
			mDAO.insertar(administrador);
			idSegUsuarioAdmin=administrador.getIdSegUsuario();
    	}
		//inicializacion de modulos:
		SegModulo modulo=new SegModulo();
		int idSegModuloSeguridades=0;
		modulo.setNombreModulo("Seguridades");
		modulo.setRutaAcceso("seguridades/menu");
		mDAO.insertar(modulo);
		idSegModuloSeguridades=modulo.getIdSegModulo();
		//asignacion de accesos:
		asignarModulo(idSegUsuarioAdmin, idSegModuloSeguridades);
    }
    
    public LoginDTO login(int idSegUsuario,String clave,String direccionIP) throws Exception{
    	//crear DTO:
		LoginDTO loginDTO=new LoginDTO();
		loginDTO.setIdSegUsuario(idSegUsuario);
		loginDTO.setDireccionIP(direccionIP);
		
    	if(ModelUtil.isEmpty(clave)) {
    		throw new Exception("Debe indicar una clave");
    	}
    	SegUsuario usuario=(SegUsuario) mDAO.findById(SegUsuario.class, idSegUsuario);
    	if(usuario==null) {
    		throw new Exception("Error en credenciales.");
    	}
    		
    	if(usuario.getClave().equals(clave)) {
    		if(usuario.getActivo()==false) {
        		throw new Exception("El usuario esta desactivado.");
        	}
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
    	throw new Exception("Error en credenciales");
    }
    
    public void cerrarSesion(int idSegUsuario) {
    }
    
    public void accesoNoPermitido(int idSegUsuario,String ruta) {

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
    	modulo.setIdSegModulo(nuevoModulo.getIdSegModulo());
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
    
    /**
     * Permite asignar el acceso a un modulo del inventario de sistemas.
     * @param idSegUsuario El codigo del usuario.
     * @param idSegModulo El codigo del modulo que se va a asignar.
     * @throws Exception
     */
    public void asignarModulo(int idSegUsuario,int idSegModulo) throws Exception{
    	//Buscar los objetos primarios:
    	SegUsuario usuario=(SegUsuario)mDAO.findById(SegUsuario.class, idSegUsuario);
    	SegModulo modulo=(SegModulo)mDAO.findById(SegModulo.class, idSegModulo);
    	//crear la relacion:
    	SegAsignacion asignacion=new SegAsignacion();
    	asignacion.getIdSegAsignacion();
    	asignacion.setSegModulo(modulo);
    	asignacion.setSegUsuario(usuario);
    	//guardar la asignacion:
    	mDAO.insertar(asignacion);
    }
    
    public void eliminarAsignacion(int idSegAsignacion) throws Exception {
    	mDAO.eliminar(SegAsignacion.class, idSegAsignacion);
    }

}

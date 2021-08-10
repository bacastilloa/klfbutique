package butiqueklf.model.auditoria.managers;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.Query;

import butiqueklf.model.core.managers.ManagerDAO;
import butiqueklf.model.core.entities.AudBitacora;
import butiqueklf.model.usuario.dtos.LoginDTO;

import javax.ejb.Stateless;

/**
 * Session Bean implementation class ManagerAuditoria
 */
@SuppressWarnings("unused")
@Stateless
@LocalBean
public class ManagerAuditoria {

	@EJB
	private ManagerDAO mDAO;
    /**
     * Default constructor. 
     */
    public ManagerAuditoria() {
        // TODO Auto-generated constructor stub
    }
    
    public void mostrarLog(Class clase,String nombreMetodo,String mensaje) {
    	SimpleDateFormat format=new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
    	System.out.println(format.format(new Date())+"["+clase.getSimpleName()+"/"+nombreMetodo+"]: "+mensaje);
    	AudBitacora pista=new AudBitacora();
    	pista.setDescripcionEvento(mensaje);
    	pista.setDireccionIp("localhost");
    	Timestamp tiempo=new Timestamp(System.currentTimeMillis());
    	pista.setFechaEvento(tiempo);
    	pista.setIdUsuario("anonimo");
    	pista.setNombreClase(clase.getSimpleName());
    	pista.setNombreMetodo(nombreMetodo);
    	try {
			mDAO.insertar(pista);
		} catch (Exception e) {
			e.printStackTrace();
		}
    }
    
    public void mostrarLog(final LoginDTO loginDTO,Class clase,String nombreMetodo,String mensaje) {
    	SimpleDateFormat format=new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
    	System.out.println(format.format(new Date())+" ["+
				loginDTO.getIdSegUsuario()+"@"+
				loginDTO.getDireccionIP()+":"+clase.getSimpleName()+"/"+nombreMetodo+"]: "+mensaje);
    	AudBitacora pista=new AudBitacora();
    	pista.setDescripcionEvento(mensaje);
    	pista.setDireccionIp(loginDTO.getDireccionIP());
    	Timestamp tiempo=new Timestamp(System.currentTimeMillis());
    	pista.setFechaEvento(tiempo);
    	pista.setIdUsuario(""+loginDTO.getIdSegUsuario());
    	pista.setNombreClase(clase.getSimpleName());
    	pista.setNombreMetodo(nombreMetodo);
    	try {
			mDAO.insertar(pista);
		} catch (Exception e) {
			e.printStackTrace();
		}
    }
    
    public List<AudBitacora> findBitacoraByFecha(Date fechaInicio,Date fechaFin){
    	SimpleDateFormat format=new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
    	System.out.println("fecha inicio: "+format.format(fechaInicio));
    	System.out.println("fecha fin: "+format.format(fechaFin));
    	String consulta="select b from AudBitacora b where b.fechaEvento between :fechaInicio and :fechaFin order by b.fechaEvento";
    	Query q=mDAO.getEntityManager().createQuery(consulta, AudBitacora.class);
    	q.setParameter("fechaInicio", new Timestamp(fechaInicio.getTime()));
    	q.setParameter("fechaFin", new Timestamp(fechaFin.getTime()));
    	return q.getResultList();
    	
    }
    
    public void eliminarBitacora() {
    	System.out.println("La bitacora se ha eliminado.");
    }

}

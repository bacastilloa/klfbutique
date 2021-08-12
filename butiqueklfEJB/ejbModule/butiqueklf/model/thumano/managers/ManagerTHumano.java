package butiqueklf.model.thumano.managers;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import butiqueklf.model.core.entities.ThmCargo;
import butiqueklf.model.core.entities.ThmEmpleado;
import butiqueklf.model.core.managers.ManagerDAO;
import butiqueklf.model.seguridades.managers.ManagerSeguridades;
import butiqueklf.model.thumano.dtos.DTOThmCargo;

/**
 * Session Bean implementation class ManagerTHumano
 */
@Stateless
@LocalBean
public class ManagerTHumano {
	public final static double PORCENTAJE_IESS=0.0935;
	public final static double PORCENTAJE_FONDOS_RESERVA=0.0833;
	public final static int INCREMENTO_HEXTRA=2;
	
	@EJB
	private ManagerDAO mDAO;
	@EJB
	private ManagerSeguridades mSeguridades;
    /**
     * Default constructor. 
     */
    public ManagerTHumano() {
        
    }
    
    //EMPLEADOS:
    public List<ThmEmpleado> findAllThmEmpleado(){
    	return mDAO.findAll(ThmEmpleado.class);
    }
    
    public ThmEmpleado insertarThmEmpleado(ThmEmpleado nuevoEmpleado,int idThmCargo,int idSegUsuario) throws Exception {
    	ThmEmpleado nuevo=new ThmEmpleado();
    	Random rnd=new Random();
    	nuevo.setCoutaPrestamo(new BigDecimal(100*rnd.nextDouble()));//prestamo entre 0 y 100
    	nuevo.setHorasExtra(rnd.nextInt(20));//maximo 20 horas extra
    	nuevo.setHorasTrabajadas(160);//160 horas mensuales
    	nuevo.setSegUsuario(mSeguridades.findByIdSegUsuario(idSegUsuario));
    	nuevo.setThmCargo(findByIdThmCargo(idThmCargo));
    	mDAO.insertar(nuevo);
    	return nuevo;
    }
    
    //CARGOS:
    public ThmCargo findByIdThmCargo(int idThmCargo) throws Exception {
    	return (ThmCargo) mDAO.findById(ThmCargo.class, idThmCargo);
    }
    
    public List<ThmCargo> findAllThmCargo(){
    	return mDAO.findAll(ThmCargo.class, "nombreCargo");
    }
    
    public List<DTOThmCargo> findAllDTOThmCargo(){
    	List<DTOThmCargo> listaDTO=new ArrayList<DTOThmCargo>();
    	for(ThmCargo cargo:findAllThmCargo()) {
    		DTOThmCargo nuevoDTO=new DTOThmCargo(cargo.getIdThmCargo(), cargo.getNombreCargo(), 
    				cargo.getSueldo().doubleValue());
    		listaDTO.add(nuevoDTO);
    	}
    	return listaDTO;
    }
    
    public void insertarCargo() throws Exception {
    	ThmCargo cargo=new ThmCargo();
    	cargo.setNombreCargo("Nuevo cargo");
    	cargo.setSueldo(new BigDecimal(1300));
    	mDAO.insertar(cargo);
    }
    
}

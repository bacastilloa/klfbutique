package butiqueklf.model.core.managers;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 * Session Bean implementation class ManagerDAO
 */
@Stateless
@LocalBean
public class ManagerDAO {
	
	@PersistenceContext
	private EntityManager em;

    /**
     * Default constructor. 
     */
    public ManagerDAO() {
        // TODO Auto-generated constructor stub
    }
    
    public List findAll(Class clase,String propiedadOrderBy,boolean ascendente) {
    	Query q;
    	List listado;
    	String sentenciaJPQL;
    	if(propiedadOrderBy==null || propiedadOrderBy.length()==0)
    		sentenciaJPQL="SELECT o FROM "+clase.getSimpleName()+" o";
    	else {
    		if(ascendente)
    			sentenciaJPQL="SELECT o FROM "+clase.getSimpleName()+" o ORDER BY o."+propiedadOrderBy+" asc";
    		else
    			sentenciaJPQL="SELECT o FROM "+clase.getSimpleName()+" o ORDER BY o."+propiedadOrderBy+" desc";
    	}
    	q=em.createQuery(sentenciaJPQL);
    	listado=q.getResultList();
    	System.out.println("Consultados "+clase.getSimpleName()+": "+listado.size() +" objetos.");
    	return listado;
    		
    }

    
    public List findAll(Class clase) {
    	return findAll(clase,null,false);
    }
    
    @SuppressWarnings("rawtypes")
	public List findAll(Class clase,String propiedadOrderBy) {
		return findAll(clase,propiedadOrderBy,true);
	}
    
    @SuppressWarnings({ "rawtypes", "unchecked" })
	public Object findById(Class clase, Object pID) throws Exception {
		if (pID == null)
			throw new Exception("Debe especificar el codigo para buscar el dato.");
		Object o;
		try {
			o = em.find(clase, pID);
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("Error al buscar la informacion especificada (" + pID + ") : " + e.getMessage());
		}
		return o;
	}
    
    @SuppressWarnings("rawtypes")
	public List findWhere(Class clase, String pClausulaWhere, String pOrderBy) {
		Query q;
		List listado;
		String sentenciaJPQL;
		if (pOrderBy == null || pOrderBy.length() == 0)
			sentenciaJPQL = "SELECT o FROM " + clase.getSimpleName() + " o WHERE " + pClausulaWhere;
		else
			sentenciaJPQL = "SELECT o FROM " + clase.getSimpleName() + " o WHERE " + pClausulaWhere + " ORDER BY " + pOrderBy;
		q = em.createQuery(sentenciaJPQL);
		listado = q.getResultList();
		return listado;
	}
    
    public void insertar(Object pObjeto) throws Exception {		
		if (pObjeto == null)
			throw new Exception("No se puede insertar un objeto null.");
		try {
			em.persist(pObjeto);
		} catch (Exception e) {
			throw new Exception("No se pudo insertar el objeto especificado: " + e.getMessage());
		}
	}

    @SuppressWarnings("rawtypes")
	public void eliminar(Class clase, Object pID) throws Exception {
		if (pID == null) {
			throw new Exception("Debe especificar un identificador para eliminar el dato solicitado.");
		}
		Object o = findById(clase, pID);
		try {
			em.remove(o);
		} catch (Exception e) {
			throw new Exception("No se pudo eliminar el dato: " + e.getMessage());
		}
	}
    
    public void actualizar(Object pObjeto) throws Exception {
		if (pObjeto == null)
			throw new Exception("No se puede actualizar un dato null");
		try {
			em.merge(pObjeto);
		} catch (Exception e) {
			throw new Exception("No se pudo actualizar el dato: "
					+ e.getMessage());
		}
	}

	public EntityManager getEntityManager() {
		return em;
	}
	
	@SuppressWarnings("rawtypes")
	public List execJPQL(String pClausulaJPQL) {
		Query q;
		List listado;
		q = em.createQuery(pClausulaJPQL);
		listado = q.getResultList();

		return listado;
	}
	
	public Long obtenerSecuenciaPostgresql(String nombreSecuencia)
			throws Exception {
		String sentenciaSQL;
		Query q;
		BigInteger valSeq;
		Long valorSeq = null;
		try {
			sentenciaSQL = "SELECT nextval('" + nombreSecuencia + "')";
			q = em.createNativeQuery(sentenciaSQL);
			valSeq = (BigInteger) q.getSingleResult();
			valorSeq = valSeq.longValue();
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("Error al obtener valor de secuencia ("+nombreSecuencia+") : "+e.getMessage());
		}
		return valorSeq;
	}
	
	@SuppressWarnings("rawtypes")
	public long obtenerValorMax(Class clase,String nombrePropiedad) throws Exception{
		Query q;
		String sentenciaSQL;
		BigDecimal valorMax;
		try {
			sentenciaSQL="SELECT MAX(o."+nombrePropiedad+") FROM "+clase.getSimpleName()+" o";
			q = em.createQuery(sentenciaSQL);
			valorMax=(BigDecimal)q.getSingleResult();
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("Error al obtener valor max: "+clase.getCanonicalName()+"["+nombrePropiedad+"]. "+e.getMessage());
		}
		if(valorMax==null)
			return 0;
		return valorMax.longValue();
	}
	
	@SuppressWarnings("rawtypes")
	public long obtenerValorMaxWhere(Class clase,String nombrePropiedad, String pClausulaWhere ) throws Exception{
		Query q;
		String sentenciaSQL;
		BigDecimal valorMax;
		try {
			sentenciaSQL="SELECT MAX(o."+nombrePropiedad+") FROM "+clase.getSimpleName()+" o" + " WHERE " + pClausulaWhere;
			q = em.createQuery(sentenciaSQL);
			valorMax=(BigDecimal)q.getSingleResult();
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("Error al obtener valor max: "+clase.getCanonicalName()+"["+nombrePropiedad+"]. "+ pClausulaWhere+e.getMessage());
		}
		if(valorMax==null)
			return 0;
		return valorMax.longValue();
	}

}

package ni.org.fabretto.me.service;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ni.org.fabretto.me.domain.catalogs.Comunidad;


/**
 * Servicio para el objeto Comunidad
 * 
 * @author William Aviles
 * 
 **/

@Service("comunidadesService")
@Transactional
public class ComunidadesService {
	
	@Resource(name="sessionFactory")
	private SessionFactory sessionFactory;
	
	/**
	 * Regresa todos los comunidades
	 * 
	 * @return una lista de <code>Comunidad</code>(s)
	 */
	@SuppressWarnings("unchecked")
	public List<Comunidad> getComunidades() {
		// Retrieve session from Hibernate
		Session session = sessionFactory.getCurrentSession();
		// Create a Hibernate query (HQL)
		Query query = session.createQuery("FROM Comunidad com order by com.nombreComunidad");
		// Retrieve all
		return  query.list();
	}
	
	/**
	 * Regresa todos los comunidades activos
	 * 
	 * @return una lista de <code>Comunidad</code>(s)
	 */
	@SuppressWarnings("unchecked")
	public List<Comunidad> getComunidadesActivas() {
		// Retrieve session from Hibernate
		Session session = sessionFactory.getCurrentSession();
		// Create a Hibernate query (HQL)
		Query query = session.createQuery("FROM Comunidad com where com.pasivo ='0' order by com.nombreComunidad");
		// Retrieve all
		return  query.list();
	}
	
	/**
	 * Regresa un Comunidad
	 * @param comunidad El identificador de la comunidad a buscar
	 * @return <code>Comunidad</code>
	 */
	public Comunidad getComunidad(String comunidad) {
		// Retrieve session from Hibernate
		Session session = sessionFactory.getCurrentSession();
		// Create a Hibernate query (HQL)
		Query query = session.createQuery("FROM Comunidad com where com.idUnico =:comunidad");
		query.setParameter("comunidad",comunidad);
		return  (Comunidad) query.uniqueResult();
	}

	/**
	 * Guarda un Comunidad
	 * @param comunidad El Comunidad a guardar
	 * 
	 */
	public void saveComunidad(Comunidad comunidad) {
		Session session = sessionFactory.getCurrentSession();
		session.saveOrUpdate(comunidad);
	}
	
	
}

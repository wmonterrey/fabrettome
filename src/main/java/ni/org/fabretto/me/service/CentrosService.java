package ni.org.fabretto.me.service;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import ni.org.fabretto.me.domain.catalogs.Centro;
import ni.org.fabretto.me.users.model.UsuarioCentro;


/**
 * Servicio para el objeto Centro
 * 
 * @author William Aviles
 * 
 **/

@Service("centrosService")
@Transactional
public class CentrosService {
	
	@Resource(name="sessionFactory")
	private SessionFactory sessionFactory;
	
	/**
	 * Regresa todos los centros
	 * 
	 * @return una lista de <code>Centro</code>(s)
	 */
	@SuppressWarnings("unchecked")
	public List<Centro> getCentros() {
		// Retrieve session from Hibernate
		Session session = sessionFactory.getCurrentSession();
		// Create a Hibernate query (HQL)
		Query query = session.createQuery("FROM Centro cen order by cen.nombreCentro");
		// Retrieve all
		return  query.list();
	}
	
	/**
	 * Regresa todos los centros activos
	 * 
	 * @return una lista de <code>Centro</code>(s)
	 */
	@SuppressWarnings("unchecked")
	public List<Centro> getCentrosActivos() {
		// Retrieve session from Hibernate
		Session session = sessionFactory.getCurrentSession();
		// Create a Hibernate query (HQL)
		Query query = session.createQuery("FROM Centro cen where cen.pasivo ='0' order by cen.nombreCentro");
		// Retrieve all
		return  query.list();
	}
	
	/**
	 * Regresa un Centro
	 * @param centro El identificador de la centro a buscar
	 * @return <code>Centro</code>
	 */
	public Centro getCentro(String centro) {
		// Retrieve session from Hibernate
		Session session = sessionFactory.getCurrentSession();
		// Create a Hibernate query (HQL)
		Query query = session.createQuery("FROM Centro cen where cen.idUnico =:centro");
		query.setParameter("centro",centro);
		return  (Centro) query.uniqueResult();
	}
	
	/**
	 * Regresa un centro de usuario
	 * @param username El usuario
	 * @param center El centro
	 * @return un <code>UsuarioCentro</code>
	 */
	public UsuarioCentro getUsuarioCentro(String username, String center) {
		// Retrieve session from Hibernate
		Session session = sessionFactory.getCurrentSession();
		// Create a Hibernate query (HQL)
		Query query = session.createQuery("FROM UsuarioCentro ucen where ucen.usuarioCentroId.usuario =:username and ucen.usuarioCentroId.centro =:center");
		query.setParameter("username",username);
		query.setParameter("center",center);
		// Retrieve all
		return  (UsuarioCentro) query.uniqueResult();
	}
	
	/**
	 * Regresa todos los centros  de un usuario
	 * @param username El usuario de los centros
	 * @return una lista de <code>UsuarioCentro</code>(s)
	 */
	@SuppressWarnings("unchecked")
	public List<UsuarioCentro> getUsuarioCentrosTodos(String username) {
		// Retrieve session from Hibernate
		Session session = sessionFactory.getCurrentSession();
		// Create a Hibernate query (HQL)
		Query query = session.createQuery("FROM UsuarioCentro ucen where ucen.usuarioCentroId.usuario =:username");
		query.setParameter("username",username);
		// Retrieve all
		return  query.list();
	}
	
	
	/**
	 * Regresa todos los centros que no tiene el usuario
	 * @param username Nombre del usuario. 
	 * @return una lista de <code>Centro</code>(s)
	 */

	@SuppressWarnings("unchecked")
	public List<Centro> getCentrosNoTieneUsuario(String username) {
		// Retrieve session from Hibernate
		Session session = sessionFactory.getCurrentSession();
		// Create a Hibernate query (HQL)
		Query query = session.createQuery("FROM Centro cen  where cen.pasivo ='0' and cen.idUnico not in (Select ucen.usuarioCentroId.centro from UsuarioCentro ucen where ucen.usuarioCentroId.usuario =:username)");
		query.setParameter("username",username);
		// Retrieve all
		return  query.list();
	}


	/**
	 * Guarda un Centro
	 * @param centro El Centro a guardar
	 * 
	 */
	public void saveCentro(Centro centro) {
		Session session = sessionFactory.getCurrentSession();
		session.saveOrUpdate(centro);
	}
	
	
	/**
	 * Guarda un UsuarioCentro
	 * @param uc El UsuarioCentro a guardar
	 * 
	 */
	public void saveUsuarioCentro(UsuarioCentro uc) {
		Session session = sessionFactory.getCurrentSession();
		session.saveOrUpdate(uc);
	}
	
	
}

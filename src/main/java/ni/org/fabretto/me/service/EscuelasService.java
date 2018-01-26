package ni.org.fabretto.me.service;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ni.org.fabretto.me.domain.catalogs.Escuela;


/**
 * Servicio para el objeto Escuela
 * 
 * @author William Aviles
 * 
 **/

@Service("escuelasService")
@Transactional
public class EscuelasService {
	
	@Resource(name="sessionFactory")
	private SessionFactory sessionFactory;
	
	/**
	 * Regresa todos los escuelas
	 * 
	 * @return una lista de <code>Escuela</code>(s)
	 */
	@SuppressWarnings("unchecked")
	public List<Escuela> getEscuelas() {
		// Retrieve session from Hibernate
		Session session = sessionFactory.getCurrentSession();
		// Create a Hibernate query (HQL)
		Query query = session.createQuery("FROM Escuela esc order by esc.nombreEscuela");
		// Retrieve all
		return  query.list();
	}
	
	/**
	 * Regresa todos los escuelas activos
	 * 
	 * @return una lista de <code>Escuela</code>(s)
	 */
	@SuppressWarnings("unchecked")
	public List<Escuela> getEscuelaesActivas() {
		// Retrieve session from Hibernate
		Session session = sessionFactory.getCurrentSession();
		// Create a Hibernate query (HQL)
		Query query = session.createQuery("FROM Escuela esc where esc.pasivo ='0' order by esc.nombreEscuela");
		// Retrieve all
		return  query.list();
	}
	
	/**
	 * Regresa un Escuela
	 * @param escuela El identificador de la escuela a buscar
	 * @return <code>Escuela</code>
	 */
	public Escuela getEscuela(String escuela) {
		// Retrieve session from Hibernate
		Session session = sessionFactory.getCurrentSession();
		// Create a Hibernate query (HQL)
		Query query = session.createQuery("FROM Escuela esc where esc.idUnico =:escuela");
		query.setParameter("escuela",escuela);
		return  (Escuela) query.uniqueResult();
	}

	/**
	 * Guarda un Escuela
	 * @param escuela El Escuela a guardar
	 * 
	 */
	public void saveEscuela(Escuela escuela) {
		Session session = sessionFactory.getCurrentSession();
		session.saveOrUpdate(escuela);
	}
	
	
}

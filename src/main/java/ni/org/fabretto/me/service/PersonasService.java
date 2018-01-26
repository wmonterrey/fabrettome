package ni.org.fabretto.me.service;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ni.org.fabretto.me.domain.Persona;


/**
 * Servicio para el objeto Persona
 * 
 * @author William Aviles
 * 
 **/

@Service("personasService")
@Transactional
public class PersonasService {
	
	@Resource(name="sessionFactory")
	private SessionFactory sessionFactory;
	
	/**
	 * Regresa todos los personas
	 * 
	 * @return una lista de <code>Persona</code>(s)
	 */
	@SuppressWarnings("unchecked")
	public List<Persona> getPersonas() {
		// Retrieve session from Hibernate
		Session session = sessionFactory.getCurrentSession();
		// Create a Hibernate query (HQL)
		Query query = session.createQuery("FROM Persona");
		// Retrieve all
		return  query.list();
	}
	
	/**
	 * Regresa todos los personas activas
	 * 
	 * @return una lista de <code>Persona</code>(s)
	 */
	@SuppressWarnings("unchecked")
	public List<Persona> getPersonasActivas() {
		// Retrieve session from Hibernate
		Session session = sessionFactory.getCurrentSession();
		// Create a Hibernate query (HQL)
		Query query = session.createQuery("FROM Persona per where per.pasivo ='0' order by per.ident");
		// Retrieve all
		return  query.list();
	}
	
	/**
	 * Regresa un Persona
	 * @param persona El identificador de la persona a buscar
	 * @return <code>Persona</code>
	 */
	public Persona getPersona(String persona) {
		// Retrieve session from Hibernate
		Session session = sessionFactory.getCurrentSession();
		// Create a Hibernate query (HQL)
		Query query = session.createQuery("FROM Persona per where per.idUnico =:persona");
		query.setParameter("persona",persona);
		return  (Persona) query.uniqueResult();
	}

	/**
	 * Guarda un Persona
	 * @param persona El Persona a guardar
	 * 
	 */
	public void savePersona(Persona persona) {
		Session session = sessionFactory.getCurrentSession();
		session.saveOrUpdate(persona);
	}
	
	
}

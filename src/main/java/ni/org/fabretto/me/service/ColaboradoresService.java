package ni.org.fabretto.me.service;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ni.org.fabretto.me.domain.Colaborador;


/**
 * Servicio para el objeto Colaborador
 * 
 * @author William Aviles
 * 
 **/

@Service("colaboradoresService")
@Transactional
public class ColaboradoresService {
	
	@Resource(name="sessionFactory")
	private SessionFactory sessionFactory;
	
	/**
	 * Regresa todos los colaboradores
	 * 
	 * @return una lista de <code>Colaborador</code>(s)
	 */
	@SuppressWarnings("unchecked")
	public List<Colaborador> getColaboradores() {
		// Retrieve session from Hibernate
		Session session = sessionFactory.getCurrentSession();
		// Create a Hibernate query (HQL)
		Query query = session.createQuery("FROM Colaborador");
		// Retrieve all
		return  query.list();
	}
	
	/**
	 * Regresa todos los colaboradores activas
	 * 
	 * @return una lista de <code>Colaborador</code>(s)
	 */
	@SuppressWarnings("unchecked")
	public List<Colaborador> getColaboradoresActivos() {
		// Retrieve session from Hibernate
		Session session = sessionFactory.getCurrentSession();
		// Create a Hibernate query (HQL)
		Query query = session.createQuery("FROM Colaborador per where per.pasivo ='0' order by per.idUnico");
		// Retrieve all
		return  query.list();
	}
	
	/**
	 * Regresa un Colaborador
	 * @param colaborador El identificador de la colaborador a buscar
	 * @return <code>Colaborador</code>
	 */
	public Colaborador getColaborador(String colaborador) {
		// Retrieve session from Hibernate
		Session session = sessionFactory.getCurrentSession();
		// Create a Hibernate query (HQL)
		Query query = session.createQuery("FROM Colaborador per where per.idUnico =:colaborador");
		query.setParameter("colaborador",colaborador);
		return  (Colaborador) query.uniqueResult();
	}

	/**
	 * Guarda un Colaborador
	 * @param colaborador El Colaborador a guardar
	 * 
	 */
	public void saveColaborador(Colaborador colaborador) {
		Session session = sessionFactory.getCurrentSession();
		session.saveOrUpdate(colaborador);
	}
	
	
}

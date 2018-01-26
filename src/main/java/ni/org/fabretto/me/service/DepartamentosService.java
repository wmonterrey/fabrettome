package ni.org.fabretto.me.service;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ni.org.fabretto.me.domain.catalogs.Departamento;


/**
 * Servicio para el objeto Departamento
 * 
 * @author William Aviles
 * 
 **/

@Service("departamentosService")
@Transactional
public class DepartamentosService {
	
	@Resource(name="sessionFactory")
	private SessionFactory sessionFactory;
	
	/**
	 * Regresa todos los departamentos
	 * 
	 * @return una lista de <code>Departamento</code>(s)
	 */
	@SuppressWarnings("unchecked")
	public List<Departamento> getDepartamentos() {
		// Retrieve session from Hibernate
		Session session = sessionFactory.getCurrentSession();
		// Create a Hibernate query (HQL)
		Query query = session.createQuery("FROM Departamento dep order by dep.nombreDepartamento");
		// Retrieve all
		return  query.list();
	}
	
	/**
	 * Regresa todos los departamentos activos
	 * 
	 * @return una lista de <code>Departamento</code>(s)
	 */
	@SuppressWarnings("unchecked")
	public List<Departamento> getDepartamentosActivos() {
		// Retrieve session from Hibernate
		Session session = sessionFactory.getCurrentSession();
		// Create a Hibernate query (HQL)
		Query query = session.createQuery("FROM Departamento dep where dep.pasivo ='0' order by dep.nombreDepartamento");
		// Retrieve all
		return  query.list();
	}
	
	/**
	 * Regresa un Departamento
	 * @param departamento El identificador del departamento a buscar
	 * @return <code>Departamento</code>
	 */
	public Departamento getDepartamento(String departamento) {
		// Retrieve session from Hibernate
		Session session = sessionFactory.getCurrentSession();
		// Create a Hibernate query (HQL)
		Query query = session.createQuery("FROM Departamento dep where dep.idUnico =:departamento");
		query.setParameter("departamento",departamento);
		return  (Departamento) query.uniqueResult();
	}

	/**
	 * Guarda un Departamento
	 * @param departamento El Departamento a guardar
	 * 
	 */
	public void saveDepartamento(Departamento departamento) {
		Session session = sessionFactory.getCurrentSession();
		session.saveOrUpdate(departamento);
	}
	
	
}

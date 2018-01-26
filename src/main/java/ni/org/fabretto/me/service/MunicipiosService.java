package ni.org.fabretto.me.service;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ni.org.fabretto.me.domain.catalogs.Municipio;


/**
 * Servicio para el objeto Municipio
 * 
 * @author William Aviles
 * 
 **/

@Service("municipiosService")
@Transactional
public class MunicipiosService {
	
	@Resource(name="sessionFactory")
	private SessionFactory sessionFactory;
	
	/**
	 * Regresa todos los municipios
	 * 
	 * @return una lista de <code>Municipio</code>(s)
	 */
	@SuppressWarnings("unchecked")
	public List<Municipio> getMunicipios() {
		// Retrieve session from Hibernate
		Session session = sessionFactory.getCurrentSession();
		// Create a Hibernate query (HQL)
		Query query = session.createQuery("FROM Municipio mun order by mun.nombreMunicipio");
		// Retrieve all
		return  query.list();
	}
	
	/**
	 * Regresa todos los municipios activos
	 * 
	 * @return una lista de <code>Municipio</code>(s)
	 */
	@SuppressWarnings("unchecked")
	public List<Municipio> getMunicipiosActivos() {
		// Retrieve session from Hibernate
		Session session = sessionFactory.getCurrentSession();
		// Create a Hibernate query (HQL)
		Query query = session.createQuery("FROM Municipio mun where mun.pasivo ='0' order by mun.nombreMunicipio");
		// Retrieve all
		return  query.list();
	}
	
	/**
	 * Regresa un Municipio
	 * @param municipio El identificador de la municipio a buscar
	 * @return <code>Municipio</code>
	 */
	public Municipio getMunicipio(String municipio) {
		// Retrieve session from Hibernate
		Session session = sessionFactory.getCurrentSession();
		// Create a Hibernate query (HQL)
		Query query = session.createQuery("FROM Municipio mun where mun.idUnico =:municipio");
		query.setParameter("municipio",municipio);
		return  (Municipio) query.uniqueResult();
	}

	/**
	 * Guarda un Municipio
	 * @param municipio El Municipio a guardar
	 * 
	 */
	public void saveMunicipio(Municipio municipio) {
		Session session = sessionFactory.getCurrentSession();
		session.saveOrUpdate(municipio);
	}
	
	
}

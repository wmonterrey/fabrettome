package ni.org.fabretto.me.service.personas;

import java.sql.Timestamp;
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
	 * Regresa las personas en base a los parametros
	 * 
	 * @return una lista de <code>Persona</code>(s)
	 */
	@SuppressWarnings("unchecked")
	public List<Persona> getPersonas(String ident, String primerNombre, 
				String segundoNombre , String primerApellido, String segundoApellido, boolean incluirPasivos, 
				Long desde, Long hasta, String sexo, String comunidad) {
		//Set the SQL Query initially
		String hqlQuery = "FROM Persona per where 1=1";
		//Busqueda por identificador
		if (!ident.matches("")) {
			hqlQuery = hqlQuery + " and per.ident =:ident";
		}
		//Busqueda por primer nombre
		if (!primerNombre.matches("")) {
			hqlQuery = hqlQuery + " and per.primerNombre like:primerNombre";
		}
		//Busqueda por segundo nombre
		if (!segundoNombre.matches("")) {
			hqlQuery = hqlQuery + " and per.segundoNombre like:segundoNombre";
		}
		//Busqueda por primer apellido
		if (!primerApellido.matches("")) {
			hqlQuery = hqlQuery + " and per.primerApellido like:primerApellido";
		}
		//Busqueda por segundo apellido
		if (!segundoApellido.matches("")) {
			hqlQuery = hqlQuery + " and per.segundoApellido like:segundoApellido";
		}
		//Busqueda por fecha de nacimiento
		if(!(desde==null)) {
			hqlQuery = hqlQuery + " and per.fechaNacimiento between :fechaInicio and :fechaFinal";
		}
		//Busqueda por sexo
		if(!sexo.matches("")) {
			hqlQuery = hqlQuery + " and per.sexo =:sexo";
		}
		//Busqueda por comunidad
		if(!comunidad.matches("")) {
			hqlQuery = hqlQuery + " and per.comunidad.idUnico =:comunidad";
		}
		// Retrieve session from Hibernate
		Session session = sessionFactory.getCurrentSession();
		// Create a Hibernate query (HQL)
		Query query = session.createQuery(hqlQuery +" order by per.primerApellido");
		//Parametro de identificador
		if (!ident.matches("")) {
			query.setParameter("ident", ident);
		}
		//Parametro Busqueda por primer nombre
		if (!primerNombre.matches("")) {
			query.setParameter("primerNombre", '%'+primerNombre+'%');
		}
		//Parametro Busqueda por segundo nombre
		if (!segundoNombre.matches("")) {
			query.setParameter("segundoNombre", '%'+segundoNombre+'%');
		}
		//Parametro Busqueda por primer apellido
		if (!primerApellido.matches("")) {
			query.setParameter("primerApellido", '%'+primerApellido+'%');
		}
		//Parametro Busqueda por segundo apellido
		if (!segundoApellido.matches("")) {
			query.setParameter("segundoApellido", '%'+segundoApellido+'%');
		}
		//Parametro Busqueda por fecha de nacimiento
		if(!(desde==null)) {
			Timestamp timeStampInicio = new Timestamp(desde);
			Timestamp timeStampFinal = new Timestamp(hasta);
			query.setTimestamp("fechaInicio", timeStampInicio);
			query.setTimestamp("fechaFinal", timeStampFinal);
		}
		//Parametro Busqueda por sexo
		if (!sexo.matches("")) {
			query.setParameter("sexo", sexo);
		}
		//Parametro Busqueda por comunidad
		if (!comunidad.matches("")) {
			query.setParameter("comunidad", comunidad);
		}
		// Retrieve all
		return  query.list();
	}
	
	
	/**
	 * Regresa una Persona
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
	 * Guarda una Persona
	 * @param persona Persona a guardar
	 * 
	 */
	public void savePersona(Persona persona) {
		Session session = sessionFactory.getCurrentSession();
		session.saveOrUpdate(persona);
	}
	
	
}

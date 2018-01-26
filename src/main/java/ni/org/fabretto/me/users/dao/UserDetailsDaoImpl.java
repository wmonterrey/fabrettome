package ni.org.fabretto.me.users.dao;

import java.util.Date;

import javax.annotation.Resource;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.joda.time.DateTime;
import org.joda.time.Days;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.transaction.annotation.Transactional;

import ni.org.fabretto.me.users.model.AccesoUsuario;
import ni.org.fabretto.me.users.model.IntentoUsuario;
import ni.org.fabretto.me.users.model.Usuario;
/**
 * Servicio que provee guardar las transacciones de usuarios en la base de datos
 * 
 * @author William Aviles
 **/
@Transactional
public class UserDetailsDaoImpl implements UserDetailsDao {
	
	private static final int MAX_ATTEMPTS = 10;
	@Resource(name="sessionFactory")
	private SessionFactory sessionFactory;

	@Override
	public void updateFailAttempts(String username) {
		IntentoUsuario user = getUserAttempts(username);
		if (user == null) {
			if (isUserExists(username)) {
				// if no record, insert a new
				Session session = sessionFactory.getCurrentSession();
				session.save(new IntentoUsuario(username, 1, new Date()));
			}
		} else {
			if (isUserExists(username)) {
				Session session = sessionFactory.getCurrentSession();
				user.setNumIntentos(user.getNumIntentos()+1);
				user.setFechaUltimoIntento(new Date());
				session.update(user);
			}
			if(user.getNumIntentos()>= MAX_ATTEMPTS){
				Session session = sessionFactory.getCurrentSession();
				Query query = session.createQuery("update Usuario set cuentaSinBloquear = :nobloqueado" +
						" where nombreUsuario = :username");
				query.setParameter("nobloqueado", false);
				query.setParameter("username", username);
				query.executeUpdate();
			}
		}
	}

	@Override
	public void resetFailAttempts(String username) {
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("update IntentoUsuario set numIntentos = :intentos" +
				" where nombreUsuario = :username");
		query.setParameter("intentos", 0);
		query.setParameter("username", username);
		query.executeUpdate();
	}

	@Override
	public IntentoUsuario getUserAttempts(String username) {
		// Retrieve session from Hibernate
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("FROM IntentoUsuario u where " +
				"u.nombreUsuario = '" + username + "'");
		IntentoUsuario userAttemps = (IntentoUsuario) query.uniqueResult();
		return userAttemps;
	}
	
	private boolean isUserExists(String username) {
		// Retrieve session from Hibernate
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("FROM Usuario u where " +
				"u.nombreUsuario = '" + username + "'");
		Usuario user = (Usuario) query.uniqueResult();
		if (user!=null){
			return true;
		}
		else{
			return false;
		}
	}
	
	@Override
	public void insertNewAccess(Authentication auth) {
		Session session = sessionFactory.getCurrentSession();
		WebAuthenticationDetails wad  = (WebAuthenticationDetails) auth.getDetails();
		Date fechaAcceso = new Date();
		Query query = session.createQuery("From Usuario where nombreUsuario = :username");
		query.setParameter("username", auth.getName());
		Usuario usuario = (Usuario) query.uniqueResult();
		query = session.createQuery("update Usuario set fechaUltimoAcceso = :fechaIngreso" +
				" where nombreUsuario = :username");
		query.setParameter("fechaIngreso", fechaAcceso);
		query.setParameter("username", auth.getName());
		query.executeUpdate();
		session.save(new AccesoUsuario(usuario,fechaAcceso, wad.getSessionId(), wad.getRemoteAddress()));
	}
	
	@Override
	public void updateAccessDateLogout(String username, String idSesion, String direccionIp) {
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("update AccesoUsuario set fechaSalida = :fechaSalida" +
				" where usuario.nombreUsuario = :username and idSesion = :idSesion and direccionRemota = :direccionIp");
		query.setParameter("fechaSalida", new Date());
		query.setParameter("username", username);
		query.setParameter("idSesion", idSesion);
		query.setParameter("direccionIp", direccionIp);
		query.executeUpdate();
	}
	
	@Override
	public void updateAccessUrl(String username, String idSesion, String direccionIp, String url) {
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("update AccesoUsuario set urlSalida = :urlSalida" +
				" where usuario.nombreUsuario = :username and idSesion = :idSesion and direccionRemota = :direccionIp");
		query.setParameter("urlSalida", url);
		query.setParameter("username", username);
		query.setParameter("idSesion", idSesion);
		query.setParameter("direccionIp", direccionIp);
		query.executeUpdate();
		
	}

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Override
	public void checkCredentialsDate(String username) {
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("FROM Usuario u where " +
				"u.nombreUsuario = '" + username + "'");
		Usuario user = (Usuario) query.uniqueResult();
		DateTime fecUltCambio = new DateTime(user.getUltimoCambioCredencial());
    	DateTime fecHoy = new DateTime();
    	Days diff = Days.daysBetween(fecUltCambio, fecHoy);
		if (diff.getDays() >= 150){
			query = session.createQuery("update Usuario set credencialSinExpirar = :nobloqueado" +
					" where nombreUsuario = :username");
			query.setParameter("nobloqueado", false);
			query.setParameter("username", username);
			query.executeUpdate();
		}
	}
}
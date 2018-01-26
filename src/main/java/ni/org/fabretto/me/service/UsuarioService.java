package ni.org.fabretto.me.service;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ni.org.fabretto.me.domain.catalogs.Centro;
import ni.org.fabretto.me.users.model.AccesoUsuario;
import ni.org.fabretto.me.users.model.Rol;
import ni.org.fabretto.me.users.model.RolUsuario;
import ni.org.fabretto.me.users.model.Usuario;

/**
 * Servicio para el objeto Usuario
 * 
 * @author William Aviles
 * 
 **/

@Service("usuarioService")
@Transactional
public class UsuarioService {
	
	@Resource(name="sessionFactory")
	private SessionFactory sessionFactory;
	
	
	/**
	 * Regresa todos los usuarios
	 * 
	 * @return una lista de <code>UserSistema</code>(s)
	 */

	@SuppressWarnings("unchecked")
	public List<Usuario> getUsers() {
		// Retrieve session from Hibernate
		Session session = sessionFactory.getCurrentSession();
		// Create a Hibernate query (HQL)
		Query query = session.createQuery("FROM Usuario");
		// Retrieve all
		return  query.list();
	}
	
	/**
	 * Regresa todos los usuarios activos
	 * 
	 * @return una lista de <code>Usuario</code>(s)
	 */

	@SuppressWarnings("unchecked")
	public List<Usuario> getActiveUsers() {
		// Retrieve session from Hibernate
		Session session = sessionFactory.getCurrentSession();
		// Create a Hibernate query (HQL)
		Query query = session.createQuery("FROM Usuario us where us.habilitado is true");
		// Retrieve all
		return  query.list();
	}
	
	
	/**
	 * Regresa un Usuario
	 * @param username Nombre del usuario. 
	 * @return un <code>Usuario</code>
	 */

	public Usuario getUser(String username) {
		// Retrieve session from Hibernate
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("FROM Usuario u where " +
				"u.nombreUsuario =:username");
		query.setParameter("username",username);
		Usuario user = (Usuario) query.uniqueResult();
		return user;
	}
	
	/**
	 * Verifica Credenciales
	 * @param username Nombre del usuario. 
	 * @return boolean
	 */

	public Boolean checkCredential(String username) {
		// Retrieve session from Hibernate
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("FROM UserSistema u where " +
				"u.nombreUsuario =:username");
		query.setParameter("username",username);
		Usuario user = (Usuario) query.uniqueResult();
		return user.getCredencialSinExpirar();
	}
	
	/**
	 * Guarda un usuario
	 * @param user El usuario. 
	 * 
	 */
	public void saveUser(Usuario user) {
		Session session = sessionFactory.getCurrentSession();
		session.saveOrUpdate(user);
	}

	/**
	 * Regresa los UserAccess
	 * @param username Nombre del usuario. 
	 * @return una lista de <code>UserAccess</code>
	 */

	@SuppressWarnings("unchecked")
	public List<AccesoUsuario> getUserAccess(String username) {
		// Retrieve session from Hibernate
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("FROM AccesoUsuario u where " +
				"u.usuario.nombreUsuario = '" + username + "' order by u.fechaIngreso DESC");
		return query.list();
	}
	
	/**
	 * Regresa todos los roles de usuarios
	 * 
	 * @return una lista de <code>Rol</code>(es)
	 */

	@SuppressWarnings("unchecked")
	public List<Rol> getRoles() {
		// Retrieve session from Hibernate
		Session session = sessionFactory.getCurrentSession();
		// Create a Hibernate query (HQL)
		Query query = session.createQuery("FROM Rol");
		// Retrieve all
		return  query.list();
	}
	
	/**
	 * Guarda un rol del usuario
	 * @param rol El rol a guardar 
	 * 
	 */
	public void saveRoleUser(RolUsuario rol) {
		Session session = sessionFactory.getCurrentSession();
		session.saveOrUpdate(rol);
	}
	
	/**
	 * Regresa todos los roles de usuarios
	 * @param username Nombre del usuario. 
	 * @return una lista de <code>Rol</code>(es)
	 */

	@SuppressWarnings("unchecked")
	public List<RolUsuario> getRolesUsuario(String username) {
		// Retrieve session from Hibernate
		Session session = sessionFactory.getCurrentSession();
		// Create a Hibernate query (HQL)
		Query query = session.createQuery("FROM RolUsuario auth " +
				"where auth.rolUsuarioId.nombreUsuario =:username and auth.pasivo ='0'");
		query.setParameter("username",username);
		// Retrieve all
		return  query.list();
	}
	
	/**
	 * Regresa todos los roles que no tenga el usuario
	 * @param username Nombre del usuario. 
	 * @return una lista de <code>Rol</code>(es)
	 */

	@SuppressWarnings("unchecked")
	public List<Rol> getRolesNoTieneUsuario(String username) {
		// Retrieve session from Hibernate
		Session session = sessionFactory.getCurrentSession();
		// Create a Hibernate query (HQL)
		Query query = session.createQuery("FROM Rol roles " +
				"where roles.nombreRol not in (select auth.rolUsuarioId.nombreRol from RolUsuario auth where auth.rolUsuarioId.nombreUsuario =:username)");
		query.setParameter("username",username);
		// Retrieve all
		return  query.list();
	}
	
	
	/**
	 * Regresa todos los centros del usuario
	 * @param username Nombre del usuario. 
	 * @return una lista de <code>Centro</code>(s)
	 */

	@SuppressWarnings("unchecked")
	public List<Centro> getCentrosUsuario(String username) {
		// Retrieve session from Hibernate
		Session session = sessionFactory.getCurrentSession();
		// Create a Hibernate query (HQL)
		Query query = session.createQuery("FROM Centro cen " +
				"where (cen.pasivo ='0' and cen.idUnico in (Select ucen.usuarioCentroId.centro from UsuarioCentro ucen where ucen.usuarioCentroId.usuario =:username and ucen.pasivo ='0'))");
		query.setParameter("username",username);
		// Retrieve all
		return  query.list();
	}
	
	/**
	 * Regresa un rol del usuario
	 * @param username Nombre del usuario.
	 * @param rol Nombre del usuario.  
	 * @return un <code>Authority</code>
	 */

	public RolUsuario getRolUsuario(String username, String rol) {
		// Retrieve session from Hibernate
		Session session = sessionFactory.getCurrentSession();
		// Create a Hibernate query (HQL)
		Query query = session.createQuery("FROM RolUsuario auth " +
				"where auth.rolUsuarioId.nombreUsuario =:username and auth.rolUsuarioId.nombreRol =:rol");
		query.setParameter("username",username);
		query.setParameter("rol",rol);
		RolUsuario rolUsuario = (RolUsuario) query.uniqueResult();
		// Retrieve 
		return  rolUsuario;
	}
	
	/**
	 * Regresa todos los roles del usuario
	 * @param username Nombre del usuario. 
	 * @return una lista de <code>Rol</code>(es)
	 */

	@SuppressWarnings("unchecked")
	public List<RolUsuario> getRolesUsuarioTodos(String username) {
		// Retrieve session from Hibernate
		Session session = sessionFactory.getCurrentSession();
		// Create a Hibernate query (HQL)
		Query query = session.createQuery("FROM RolUsuario auth " +
				"where auth.rolUsuarioId.nombreUsuario =:username");
		query.setParameter("username",username);
		// Retrieve all
		return  query.list();
	}

}

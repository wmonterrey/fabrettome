package ni.org.fabretto.me.users.service;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ni.org.fabretto.me.users.model.RolUsuario;
import ni.org.fabretto.me.users.model.Usuario;
/**
 * Servicio que provee un usuario a Spring desde la base de datos
 * 
 * @author William Aviles
 **/
@Service("customUserDetailsService")
@Transactional
public class CustomUserDetailsService implements UserDetailsService {
	
	
	@Resource(name="sessionFactory")
	private SessionFactory sessionFactory;

	@Override
	public UserDetails loadUserByUsername(String nombreUsuario)
			throws UsernameNotFoundException {
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("FROM Usuario u where " +
				"u.nombreUsuario = '" + nombreUsuario + "'");
		Usuario usuario = (Usuario) query.uniqueResult();
		if (usuario!=null){
			List<GrantedAuthority> authList = new ArrayList<GrantedAuthority>();
			query = session.createQuery("FROM RolUsuario auth " +
					"where auth.rolUsuarioId.nombreUsuario =:nombreUsuario and auth.pasivo ='0'");
			query.setParameter("nombreUsuario",nombreUsuario);
			@SuppressWarnings("unchecked")
			List<RolUsuario> rolesUsuario = query.list();
			for (RolUsuario auth : rolesUsuario) {
				authList.add(new SimpleGrantedAuthority(auth.toString()));
			}
			UserDetails user = new User(usuario.getNombreUsuario(), usuario.getContrasena(),usuario.getHabilitado(), 
					usuario.getCuentaSinExpirar(), usuario.getCredencialSinExpirar(), usuario.getCuentaSinBloquear(), 
					authList);
			return user;
		}
		else{
			throw new UsernameNotFoundException(nombreUsuario);
		}
	}
	
	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
}
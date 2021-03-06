package ni.org.fabretto.me.users.dao;

import org.springframework.security.core.Authentication;

import ni.org.fabretto.me.users.model.IntentoUsuario;

/**
 * Auditable es la interface para implementar los datos de usuario de spring.
 *  
 * @author      William Avil�s
 * @version     1.0
 * @since       1.0
 */
public interface UserDetailsDao {

	void updateFailAttempts(String username);

	void resetFailAttempts(String username);
	
	void insertNewAccess(Authentication auth);
	
	void updateAccessDateLogout(String username, String idSesion, String direccionIp);
	
	void updateAccessUrl(String username, String idSesion, String direccionIp, String url);
	
	void checkCredentialsDate(String username);
	
	IntentoUsuario getUserAttempts(String username);

}
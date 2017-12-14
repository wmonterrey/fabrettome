package ni.org.fabretto.me.users.dao;

import org.springframework.security.core.Authentication;

import ni.org.fabretto.me.users.model.IntentoUsuario;


public interface UserDetailsDao {

	void updateFailAttempts(String username);

	void resetFailAttempts(String username);
	
	void insertNewAccess(Authentication auth);
	
	void updateAccessDateLogout(String username, String idSesion, String direccionIp);
	
	void updateAccessUrl(String username, String idSesion, String direccionIp, String url);
	
	void checkCredentialsDate(String username);
	
	IntentoUsuario getUserAttempts(String username);

}
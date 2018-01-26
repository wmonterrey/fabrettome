package ni.org.fabretto.me.web.controller;

import java.text.ParseException;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.StandardPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.google.gson.Gson;

import ni.org.fabretto.me.domain.catalogs.Centro;
import ni.org.fabretto.me.service.AuditTrailService;
import ni.org.fabretto.me.service.CentrosService;
import ni.org.fabretto.me.service.UsuarioService;
import ni.org.fabretto.me.users.model.Rol;
import ni.org.fabretto.me.users.model.RolUsuario;
import ni.org.fabretto.me.users.model.RolUsuarioId;
import ni.org.fabretto.me.users.model.Usuario;
import ni.org.fabretto.me.users.model.UsuarioCentro;
import ni.org.fabretto.me.users.model.UsuarioCentroId;


/**
 * Controlador web de peticiones relacionadas a usuarios
 * 
 * @author William Aviles
 */
@Controller
@RequestMapping("/admin/usuarios/*")
public class AdminUsuariosController {
	private static final Logger logger = LoggerFactory.getLogger(AdminUsuariosController.class);
	@Resource(name="usuarioService")
	private UsuarioService usuarioService;
	@Resource(name="auditTrailService")
	private AuditTrailService auditTrailService;
	@Resource(name="centrosService")
	private CentrosService centrosService;
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
    public String obtenerUsuarios(Model model) throws ParseException { 	
    	logger.debug("Mostrando Usuarios en JSP");
    	List<Usuario> usuarios = usuarioService.getUsers();
    	model.addAttribute("usuarios", usuarios);
    	return "admin/users/list";
	}
	
	
	/**
     * Controlador para agregar un usuario.
     * @param model Modelo enlazado a la vista
     * @return ModelMap con los atributos para la vista
     */
    @RequestMapping(value = "/nuevoUsuario/", method = RequestMethod.GET)
	public String initAddUserForm(Model model) {
    	List<Rol> roles = usuarioService.getRoles();
    	List<Centro> centros = centrosService.getCentrosActivos();
	    model.addAttribute("roles", roles);
	    model.addAttribute("centros", centros);
		return "admin/users/newForm";
	}
    
    
    /**
     * Custom handler for saving an user.
     * 
     * @param nombreUsuario nombre de usuario
     * @param nombreCompleto nombre completo de usuario
     * @param confirmPassword Contraseña confirmar
     * @param contrasena Contraseña
     * @param correoElectronico Correo
     * @param roles Roles
     * @param centros Segmentos
     * @return a ModelMap with the model attributes for the view
     */
    @RequestMapping( value="/saveUser/", method=RequestMethod.POST)
	public ResponseEntity<String> processUpdateUserForm( @RequestParam(value="nombreUsuario", required=true ) String nombreUsuario
	        , @RequestParam( value="nombreCompleto", required=true ) String nombreCompleto
	        , @RequestParam( value="confirm_password", required=false ) String confirmPassword
	        , @RequestParam( value="contrasena", required=false, defaultValue="" ) String contrasena
	        , @RequestParam( value="correoElectronico", required=true, defaultValue="" ) String correoElectronico
	        , @RequestParam( value="roles", required=false, defaultValue="") List<String> roles
	        , @RequestParam( value="centros", required=false, defaultValue="") List<String> centros
	        )
	{
    	Gson gson = new Gson();
    	try{
	    	Usuario usuarioActual = this.usuarioService.getUser(SecurityContextHolder.getContext().getAuthentication().getName());
	    	Usuario user = this.usuarioService.getUser(nombreUsuario);
	    	if (user==null){
	    		user = new Usuario();
	    		user.setnombreUsuario(nombreUsuario);
	    		user.setNombreCompleto(nombreCompleto);
	    		user.setCorreoElectronico(correoElectronico);
	    		user.setFechaCreacion(new Date());
	    		user.setUsuarioRegistro(usuarioActual.getNombreUsuario());
	    		user.setModified(new Date());
	    		user.setUsuarioModifica(usuarioActual.getNombreUsuario());
	    		user.setUltimoCambioCredencial(new Date());
	    		StandardPasswordEncoder encoder = new StandardPasswordEncoder();
	    		String encodedPass = encoder.encode(contrasena);
	    		user.setContrasena(encodedPass);
	    		this.usuarioService.saveUser(user);
	    		for(String r:roles){
	    			RolUsuario rol = new RolUsuario();
	    			rol.setRolUsuarioId(new RolUsuarioId(nombreUsuario,r));
					rol.setUsuarioRegistro(usuarioActual.getNombreUsuario());
					rol.setFechaRegistro(new Date());
					this.usuarioService.saveRoleUser(rol);
				}
	    		for(String c:centros){
	    			UsuarioCentro ucen = new UsuarioCentro();
	    			ucen.setUsuarioCentroId(new UsuarioCentroId(nombreUsuario,c));
					ucen.setUsuarioRegistro(usuarioActual.getNombreUsuario());
					ucen.setFechaRegistro(new Date());
					this.centrosService.saveUsuarioCentro(ucen);
				}
	    	}
	    	else{
	    		return new ResponseEntity<String>( gson.toJson("Duplicado"), HttpStatus.CREATED);
	    	}
			
			return createJsonResponse(user);
    	}
    	catch(Exception e){
    	    String json = gson.toJson(e.toString());
    		return new ResponseEntity<String>( json, HttpStatus.CREATED);
    	}
	}
    
    
    private ResponseEntity<String> createJsonResponse( Object o )
	{
	    HttpHeaders headers = new HttpHeaders();
	    headers.set("Content-Type", "application/json");
	    Gson gson = new Gson();
	    String json = gson.toJson(o);
	    return new ResponseEntity<String>( json, headers, HttpStatus.CREATED );
	}

}

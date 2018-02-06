package ni.org.fabretto.me.web.controller;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.StandardPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.Gson;

import ni.org.fabretto.me.domain.audit.AuditTrail;
import ni.org.fabretto.me.domain.catalogs.Centro;
import ni.org.fabretto.me.service.AuditTrailService;
import ni.org.fabretto.me.service.CentrosService;
import ni.org.fabretto.me.service.UsuarioService;
import ni.org.fabretto.me.users.model.AccesoUsuario;
import ni.org.fabretto.me.users.model.Rol;
import ni.org.fabretto.me.users.model.RolUsuario;
import ni.org.fabretto.me.users.model.Usuario;
import ni.org.fabretto.me.users.model.UsuarioCentro;

/**
 * Controlador web de peticiones relacionadas a usuarios
 * 
 * @author William Aviles
 */
@Controller
@RequestMapping("/usuarios/*")
public class UsuariosController {
	private static final Logger logger = LoggerFactory.getLogger(UsuariosController.class);
	@Resource(name="usuarioService")
	private UsuarioService usuarioService;
	@Resource(name="auditTrailService")
	private AuditTrailService auditTrailService;
	@Resource(name="centrosService")
	private CentrosService centrosService;
	
	/**
     * Controlador para validar si la credencial de usuario no esta vencida
     * @param userName El nombre del usuario al que se le verifican las credenciales
     * @return verdadero o falso el estado de las credenciales
     */
	@RequestMapping(value="checkcredential", method=RequestMethod.GET)
	public @ResponseBody boolean getCredential(@RequestParam String userName) {
	    return this.usuarioService.checkCredential(userName);
	}
	
	/**
     * Controlador que inicia el formulario de cambiar contraseña
     *
     * @return String con el nombre de la vista
     */
	@RequestMapping(value = "forcechgpass", method = RequestMethod.GET)
    public String initForceChangePassForm() {
	    return "forceChgPass";
    }
	
	
	/**
     * Controlador para mostrar el perfil de un usuario
     *
     * 
     * @return a ModelAndView con los atributos del modelo y la vista
     */
    @RequestMapping("perfil")
    public ModelAndView showUser() {
    	logger.debug("Mostrando Perfil en JSP");
    	ModelAndView mav;
    	Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    	Usuario user = this.usuarioService.getUser(authentication.getName());
        if(user==null){
        	mav = new ModelAndView("403");
        }
        else{
        	mav = new ModelAndView("usuarios/viewForm");
            List<AccesoUsuario> accesosUsuario = usuarioService.getUserAccess(authentication.getName());
            List<AuditTrail> bitacoraUsuario = auditTrailService.getBitacora(authentication.getName());
            mav.addObject("user",user);
            mav.addObject("accesses",accesosUsuario);
            mav.addObject("bitacora",bitacoraUsuario);
            List<RolUsuario> rolesusuario = this.usuarioService.getRolesUsuarioTodos(authentication.getName());
            mav.addObject("rolesusuario", rolesusuario);
            List<UsuarioCentro> centrosusuario = this.centrosService.getUsuarioCentrosTodos(authentication.getName());
            mav.addObject("centrosusuario", centrosusuario);
            List<Rol> roles = usuarioService.getRolesNoTieneUsuario(authentication.getName());
        	List<Centro> centros = this.centrosService.getCentrosNoTieneUsuario(authentication.getName());
        	mav.addObject("roles", roles);
        	mav.addObject("centros", centros);
        }
        return mav;
    }
    
    
    /**
     * Controlador para editar un usuario
     * @param model Modelo enlazado a la vista
     * @return una cadena con la vista a presentar
     */
    @RequestMapping(value = "editUsuario", method = RequestMethod.GET)
	public String initUpdateUserForm(Model model) {
    	Usuario usuarioEditar = this.usuarioService.getUser(SecurityContextHolder.getContext().getAuthentication().getName());
		if(usuarioEditar!=null){
			model.addAttribute("user",usuarioEditar);
			return "usuarios/editForm";
		}
		else{
			return "403";
		}
	}
    
    
    /**
     * Controlador para guardar un usuario que ha sido editado.
     * 
     * @param nombreCompleto nombre completo de usuario
     * @param correoElectronico Correo
     * @return a ResponseEntity con la entidad guardada
     */
    @RequestMapping( value="guardarUsuarioEditado", method=RequestMethod.POST)
	public ResponseEntity<String> processEditUserForm( @RequestParam( value="nombreCompleto", required=true ) String nombreCompleto
	        , @RequestParam( value="correoElectronico", required=true, defaultValue="" ) String correoElectronico
	        )
	{
    	Gson gson = new Gson();
    	try{
	    	Usuario user = this.usuarioService.getUser(SecurityContextHolder.getContext().getAuthentication().getName());
	    	if (user!=null){
	    		if(!(user.getNombreCompleto().equals(nombreCompleto) && user.getCorreoElectronico().equals(correoElectronico))) {
	    			user.setFechaUltimaModificacion(new Date());
	    			user.setUsuarioModifica(user.getNombreUsuario());
	    			user.setNombreCompleto(nombreCompleto);
		    		user.setCorreoElectronico(correoElectronico);
		    		this.usuarioService.saveUser(user);
	    		}
	    	}
	    	else{
	    		return new ResponseEntity<String>( gson.toJson("No existe"), HttpStatus.CREATED);
	    	}
			return createJsonResponse(user);
    	}
    	catch(Exception e){
    	    String json = gson.toJson(e.toString());
    		return new ResponseEntity<String>( json, HttpStatus.CREATED);
    	}
	}
    
    /**
     * Controlador para cambiar contraseña de usuario
     *
     * @param model Modelo
     * @return a String con la vista a dirigirse
     */
    @RequestMapping(value = "chgpass", method = RequestMethod.GET)
	public String initChangePassForm(Model model) {
    	Usuario usertoChange = this.usuarioService.getUser(SecurityContextHolder.getContext().getAuthentication().getName());
		if(usertoChange!=null){
			model.addAttribute("user",usertoChange);
			return "usuarios/passForm";
		}
		else{
			return "403";
		}
	}
    
    /**
     * Controlador para guardar la contraseña de usuario
     *
     * @param contrasena El nuevo password
     * @return a ResponseEntity con la entidad guardada
     */
    @RequestMapping( value="chgPass", method=RequestMethod.POST)
	public ResponseEntity<String> processChangePassForm(@RequestParam( value="contrasena", required=true ) String contrasena
	        )
	{
    	Usuario user = usuarioService.getUser(SecurityContextHolder.getContext().getAuthentication().getName());
    	try{
			user.setUsuarioModifica(user.getNombreUsuario());
			user.setFechaUltimaModificacion(new Date());
			StandardPasswordEncoder encoder = new StandardPasswordEncoder();
			String encodedPass = encoder.encode(contrasena);
			user.setContrasena(encodedPass);
			user.setUltimoCambioCredencial(new Date());
			this.usuarioService.saveUser(user);
			return createJsonResponse(user);
    	}
    	catch(Exception e){
    		Gson gson = new Gson();
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

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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.google.gson.Gson;

import ni.org.fabretto.me.domain.audit.AuditTrail;
import ni.org.fabretto.me.domain.catalogs.Centro;
import ni.org.fabretto.me.service.AuditTrailService;
import ni.org.fabretto.me.service.CentrosService;
import ni.org.fabretto.me.service.UsuarioService;
import ni.org.fabretto.me.users.model.AccesoUsuario;
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
	
	/**
     * Controlador para listar los usuarios.
     * @param model Modelo enlazado a la vista
     * @return una cadena con la vista a presentar
     */
	@RequestMapping(value = "/", method = RequestMethod.GET)
    public String obtenerUsuarios(Model model) throws ParseException { 	
    	logger.debug("Mostrando Usuarios en JSP");
    	List<Usuario> usuarios = usuarioService.getUsers();
    	model.addAttribute("usuarios", usuarios);
    	return "admin/usuarios/list";
	}
	
	
	/**
     * Controlador para agregar un usuario.
     * @param model Modelo enlazado a la vista
     * @return una cadena con la vista a presentar
     */
    @RequestMapping(value = "/nuevoUsuario/", method = RequestMethod.GET)
	public String initAddUserForm(Model model) {
    	List<Rol> roles = usuarioService.getRoles();
    	List<Centro> centros = centrosService.getCentrosActivos();
	    model.addAttribute("roles", roles);
	    model.addAttribute("centros", centros);
		return "admin/usuarios/newForm";
	}
    
    /**
     * Controlador para editar un usuario
     * @param model Modelo enlazado a la vista
     * @param username el usuario a editar
     * @return una cadena con la vista a presentar
     */
    @RequestMapping(value = "/editUsuario/{username}/", method = RequestMethod.GET)
	public String initUpdateUserForm(@PathVariable("username") String username, Model model) {
    	Usuario usuarioEditar = this.usuarioService.getUser(username);
		if(usuarioEditar!=null){
			model.addAttribute("user",usuarioEditar);
			return "admin/usuarios/editForm";
		}
		else{
			return "403";
		}
	}
    
    /**
     * Controlador para mostrar un usuario
     *
     * @param username el usuario a mostrar
     * @return a ModelAndView con los atributos del modelo y la vista
     */
    @RequestMapping("/{username}/")
    public ModelAndView showUser(@PathVariable("username") String username) {
    	ModelAndView mav;
    	Usuario user = this.usuarioService.getUser(username);
        if(user==null){
        	mav = new ModelAndView("403");
        }
        else{
        	mav = new ModelAndView("admin/usuarios/viewForm");
            List<AccesoUsuario> accesosUsuario = usuarioService.getUserAccess(username);
            List<AuditTrail> bitacoraUsuario = auditTrailService.getBitacora(username);
            mav.addObject("user",user);
            mav.addObject("accesses",accesosUsuario);
            mav.addObject("bitacora",bitacoraUsuario);
            List<RolUsuario> rolesusuario = this.usuarioService.getRolesUsuarioTodos(username);
            mav.addObject("rolesusuario", rolesusuario);
            List<UsuarioCentro> centrosusuario = this.centrosService.getUsuarioCentrosTodos(username);
            mav.addObject("centrosusuario", centrosusuario);
            List<Rol> roles = usuarioService.getRolesNoTieneUsuario(username);
        	List<Centro> centros = this.centrosService.getCentrosNoTieneUsuario(username);
        	mav.addObject("roles", roles);
        	mav.addObject("centros", centros);
        }
        return mav;
    }
    
    
    /**
     * Controlador para guardar un usuario nuevo.
     * 
     * @param nombreUsuario nombre de usuario
     * @param nombreCompleto nombre completo de usuario
     * @param confirmPassword Contraseña confirmar
     * @param contrasena Contraseña
     * @param correoElectronico Correo
     * @param roles Roles
     * @param centros Segmentos
     * @return a ResponseEntity<String> con la entidad guardada
     */
    @RequestMapping( value="/guardarUsuario/", method=RequestMethod.POST)
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
	    		user.setNombreUsuario(nombreUsuario);
	    		user.setNombreCompleto(nombreCompleto);
	    		user.setCorreoElectronico(correoElectronico);
	    		user.setFechaCreacion(new Date());
	    		user.setUsuarioRegistro(usuarioActual.getNombreUsuario());
	    		user.setFechaUltimaModificacion(new Date());
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
    
    
    /**
     * Controlador para guardar un usuario que ha sido editado.
     * 
     * @param nombreUsuario nombre de usuario
     * @param nombreCompleto nombre completo de usuario
     * @param correoElectronico Correo
     * @return a ResponseEntity<String> con la entidad guardada
     */
    @RequestMapping( value="/guardarUsuarioEditado/", method=RequestMethod.POST)
	public ResponseEntity<String> processEditUserForm( @RequestParam(value="nombreUsuario", required=true ) String nombreUsuario
	        , @RequestParam( value="nombreCompleto", required=true ) String nombreCompleto
	        , @RequestParam( value="correoElectronico", required=true, defaultValue="" ) String correoElectronico
	        )
	{
    	Gson gson = new Gson();
    	try{
	    	Usuario usuarioActual = this.usuarioService.getUser(SecurityContextHolder.getContext().getAuthentication().getName());
	    	Usuario user = this.usuarioService.getUser(nombreUsuario);
	    	if (user!=null){
	    		if(!(user.getNombreCompleto().equals(nombreCompleto) && user.getCorreoElectronico().equals(correoElectronico))) {
	    			user.setFechaUltimaModificacion(new Date());
	    			user.setUsuarioModifica(usuarioActual.getNombreUsuario());
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
     * Controlador para habilitar o deshabilitar un usuario.
     *
     * @param username Id del usuario
     * @param accion Habilitar o deshabilitar
     * @param redirectAttributes Regresa nombre de usuario
     * @return a String con la vista donde se va a redireccionar
     */
    @RequestMapping("/habdes/{accion}/{username}/")
    public String enableUser(@PathVariable("username") String username, 
    		@PathVariable("accion") String accion, RedirectAttributes redirectAttributes) {
    	String redirecTo="404";
    	boolean hab;
    	if (accion.matches("enable1")){
    		redirecTo = "redirect:/admin/usuarios/";
    		hab = true;
    		redirectAttributes.addFlashAttribute("usuarioHabilitado", true);
        }
        else if (accion.matches("enable2")){
        	redirecTo = "redirect:/admin/usuarios/{username}/";
    		hab = true;
    		redirectAttributes.addFlashAttribute("usuarioHabilitado", true);
        }
        else if(accion.matches("disable1")){
        	redirecTo = "redirect:/admin/usuarios/";
    		hab = false;
    		redirectAttributes.addFlashAttribute("usuarioDeshabilitado", true);
        }
        else if(accion.matches("disable2")){
        	redirecTo = "redirect:/admin/usuarios/{username}/";
    		hab = false;
    		redirectAttributes.addFlashAttribute("usuarioDeshabilitado", true);
        }
        else{
        	return redirecTo;
        }
    	Usuario usuarioActual = this.usuarioService.getUser(SecurityContextHolder.getContext().getAuthentication().getName());
    	Usuario user = this.usuarioService.getUser(username);
    	if(user!=null){
    		user.setFechaUltimaModificacion(new Date());
    		user.setUsuarioModifica(usuarioActual.getNombreUsuario());
    		user.setHabilitado(hab);
    		this.usuarioService.saveUser(user);
    		redirectAttributes.addFlashAttribute("nombreUsuario", username);
    	}
    	else{
    		redirecTo = "403";
    	}
    	return redirecTo;	
    }
    
    
    /**
     * Controlador para bloquear o desbloquear un usuario.
     *
     * @param username Id del usuario
     * @param accion bloquear o desbloquear
     * @param redirectAttributes Regresa nombre de usuario
     * @return a String con la vista donde se va a redireccionar
     */
    @RequestMapping("/lockunl/{accion}/{username}/")
    public String lockUnlockUser(@PathVariable("username") String username, 
    		@PathVariable("accion") String accion, RedirectAttributes redirectAttributes) {
    	String redirecTo="404";
    	boolean lock;
    	if (accion.matches("lock1")){
    		redirecTo = "redirect:/admin/usuarios/";
    		lock = true;
    		redirectAttributes.addFlashAttribute("usuarioBloqueado", true);
        }
        else if (accion.matches("lock2")){
        	redirecTo = "redirect:/admin/usuarios/{username}/";
        	lock = true;
    		redirectAttributes.addFlashAttribute("usuarioBloqueado", true);
        }
        else if(accion.matches("unlock1")){
        	redirecTo = "redirect:/admin/usuarios/";
        	lock = false;
    		redirectAttributes.addFlashAttribute("usuarioNoBloqueado", true);
        }
        else if(accion.matches("unlock2")){
        	redirecTo = "redirect:/admin/usuarios/{username}/";
        	lock = false;
    		redirectAttributes.addFlashAttribute("usuarioNoBloqueado", true);
        }
        else{
        	return redirecTo;
        }
    	Usuario usuarioActual = this.usuarioService.getUser(SecurityContextHolder.getContext().getAuthentication().getName());
    	Usuario user = this.usuarioService.getUser(username);
    	if(user!=null){
    		user.setFechaUltimaModificacion(new Date());
    		user.setUsuarioModifica(usuarioActual.getNombreUsuario());
    		user.setCuentaSinBloquear(!lock);
    		user.setCuentaSinExpirar(!lock);
    		this.usuarioService.saveUser(user);
    		redirectAttributes.addFlashAttribute("nombreUsuario", username);
    	}
    	else{
    		redirecTo = "403";
    	}
    	return redirecTo;	
    }
    
    
    /**
     * Controlador para cambiar contraseña de usuario
     *
     * @param username Id del usuario
     * @param model Modelo
     * @return a String con la vista a dirigirse
     */
    @RequestMapping(value = "/chgpass/{username}/", method = RequestMethod.GET)
	public String initChangePassForm(@PathVariable("username") String username, Model model) {
    	Usuario usertoChange = this.usuarioService.getUser(username);
		if(usertoChange!=null){
			model.addAttribute("user",usertoChange);
			return "admin/usuarios/passForm";
		}
		else{
			return "403";
		}
	}
    
    /**
     * Controlador para guardar la contraseña de usuario
     *
     * @param nombreUsuario Id del usuario
     * @param contrasena El nuevo password
     * @return a ResponseEntity<String> con la entidad guardada
     */
    @RequestMapping( value="/chgPass/", method=RequestMethod.POST)
	public ResponseEntity<String> processChangePassForm( @RequestParam(value="nombreUsuario", required=true ) String nombreUsuario
			, @RequestParam( value="contrasena", required=true ) String contrasena
	        )
	{
    	Usuario usuario = usuarioService.getUser(SecurityContextHolder.getContext().getAuthentication().getName());
    	Usuario user = usuarioService.getUser(nombreUsuario);
    	try{
			user.setUsuarioModifica(usuario.getNombreUsuario());
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
    
    
    /**
     * Controlador para deshabilitar un rol
     *
     * @param username id del usuario
     * @param rol Rol a deshabilitar
     * @param redirectAttributes Regresa nombre de rol
     * @return a String con la vista a mostrar
     */
    @RequestMapping("/disableRol/{username}/{rol}/")
    public String disableRol(@PathVariable("username") String username, 
    		@PathVariable("rol") String rol, RedirectAttributes redirectAttributes) {
    	String redirecTo="404";
		RolUsuario rolUser = this.usuarioService.getRolUsuario(username, rol);
    	if(rolUser!=null){
    		rolUser.setPasivo('1');
    		this.usuarioService.saveRoleUser(rolUser);
    		redirecTo = "redirect:/admin/usuarios/{username}/";
    		redirectAttributes.addFlashAttribute("rolDeshabilitado", true);
    		redirectAttributes.addFlashAttribute("nombreRol", rolUser.getRol().getNombreRol());
    	}
    	else{
    		redirecTo = "403";
    	}
    	return redirecTo;	
    }
    
    /**
     * Controlador para habilitar un rol
     *
     * @param username id del usuario
     * @param rol Rol a deshabilitar
     * @param redirectAttributes Regresa nombre de rol
     * @return a String con la vista a mostrar
     */
    @RequestMapping("/enableRol/{username}/{rol}/")
    public String enableRol(@PathVariable("username") String username, 
    		@PathVariable("rol") String rol, RedirectAttributes redirectAttributes) {
    	String redirecTo="404";
    	RolUsuario rolUser = this.usuarioService.getRolUsuario(username, rol);
    	if(rolUser!=null){
    		rolUser.setPasivo('0');
    		this.usuarioService.saveRoleUser(rolUser);
    		redirecTo = "redirect:/admin/usuarios/{username}/";
    		redirectAttributes.addFlashAttribute("rolHabilitado", true);
    		redirectAttributes.addFlashAttribute("nombreRol", rolUser.getRolUsuarioId().getNombreRol());
    	}
    	else{
    		redirecTo = "403";
    	}
    	return redirecTo;	
    }
    
    /**
     * Controlador para agregar un rol
     *
     * @param username id del usuario
     * @param rol Rol a agregar
     * @param redirectAttributes Regresa nombre de rol
     * @return a String con la vista a mostrar
     */
    @RequestMapping("/addRol/{username}/{rol}/")
    public String addRol(@PathVariable("username") String username, 
    		@PathVariable("rol") String rol, RedirectAttributes redirectAttributes) {
    	String redirecTo="404";
    	Usuario usuarioActual = this.usuarioService.getUser(SecurityContextHolder.getContext().getAuthentication().getName());
    	RolUsuario rolUser = this.usuarioService.getRolUsuario(username, rol);
    	if(rolUser==null){
    		rolUser = new RolUsuario();
    		rolUser.setRolUsuarioId(new RolUsuarioId(username,rol));
    		rolUser.setUsuarioRegistro(usuarioActual.getNombreUsuario());
    		rolUser.setFechaRegistro(new Date());
    		this.usuarioService.saveRoleUser(rolUser);
    		redirecTo = "redirect:/admin/usuarios/{username}/";
    		redirectAttributes.addFlashAttribute("rolAgregado", true);
    		redirectAttributes.addFlashAttribute("nombreRol", rolUser.getRolUsuarioId().getNombreRol());
    	}
    	else{
    		redirecTo = "403";
    	}
    	return redirecTo;	
    }
    
    
    /**
     * Controlador para deshabilitar un centro
     *
     * @param username id del usuario
     * @param centro Centro a deshabilitar
     * @param redirectAttributes Regresa nombre de centro
     * @return a String con la vista a mostrar
     */
    @RequestMapping("/disableCentro/{username}/{centro}/")
    public String disableCentro(@PathVariable("username") String username, 
    		@PathVariable("centro") String centro, RedirectAttributes redirectAttributes) {
    	String redirecTo="404";
		UsuarioCentro centerUser = this.centrosService.getUsuarioCentro(username, centro);
    	if(centerUser!=null){
    		centerUser.setPasivo('1');
    		this.centrosService.saveUsuarioCentro(centerUser);
    		redirecTo = "redirect:/admin/usuarios/{username}/";
    		redirectAttributes.addFlashAttribute("centroDeshabilitado", true);
    		redirectAttributes.addFlashAttribute("nombreCentro", centerUser.getCentro().getNombreCentro());
    	}
    	else{
    		redirecTo = "403";
    	}
    	return redirecTo;	
    }
    
    
    /**
     * Controlador para habilitar un centro
     *
     * @param username id del usuario
     * @param centro Centro a habilitar
     * @param redirectAttributes Regresa nombre de centro
     * @return a String con la vista a mostrar
     */
    @RequestMapping("/enableCentro/{username}/{centro}/")
    public String enableCentro(@PathVariable("username") String username, 
    		@PathVariable("centro") String centro, RedirectAttributes redirectAttributes) {
    	String redirecTo="404";
		UsuarioCentro centerUser = this.centrosService.getUsuarioCentro(username, centro);
    	if(centerUser!=null){
    		centerUser.setPasivo('0');
    		this.centrosService.saveUsuarioCentro(centerUser);
    		redirecTo = "redirect:/admin/usuarios/{username}/";
    		redirectAttributes.addFlashAttribute("centroHabilitado", true);
    		redirectAttributes.addFlashAttribute("nombreCentro", centerUser.getCentro().getNombreCentro());
    	}
    	else{
    		redirecTo = "403";
    	}
    	return redirecTo;	
    }
    
    /**
     * Controlador para agregar un centro
     *
     * @param username id del usuario
     * @param center Centro para agregar
     * @param redirectAttributes Regresa nombre de centro
     * @return a String con la vista a mostrar
     */
    @RequestMapping("/addCentro/{username}/{center}/")
    public String addCentro(@PathVariable("username") String username, 
    		@PathVariable("center") String center, RedirectAttributes redirectAttributes) {
    	String redirecTo="404";
    	Usuario usuarioActual = this.usuarioService.getUser(SecurityContextHolder.getContext().getAuthentication().getName());
    	UsuarioCentro centerUser = this.centrosService.getUsuarioCentro(username, center);
    	if(centerUser==null){
    		centerUser = new UsuarioCentro();
    		centerUser.setUsuarioCentroId(new UsuarioCentroId(username, center));
    		centerUser.setUsuarioRegistro(usuarioActual.getNombreUsuario());
    		centerUser.setFechaRegistro(new Date());
    		this.centrosService.saveUsuarioCentro(centerUser);
    		Centro centro = this.centrosService.getCentro(center);
    		redirecTo = "redirect:/admin/usuarios/{username}/";
    		redirectAttributes.addFlashAttribute("centroAgregado", true);
    		redirectAttributes.addFlashAttribute("nombreCentro", centro.getNombreCentro());
    	}
    	else{
    		redirecTo = "403";
    	}
    	return redirecTo;	
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

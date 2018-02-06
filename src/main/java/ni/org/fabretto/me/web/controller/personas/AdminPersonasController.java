package ni.org.fabretto.me.web.controller.personas;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.google.gson.Gson;

import ni.org.fabretto.me.domain.Persona;
import ni.org.fabretto.me.domain.audit.AuditTrail;
import ni.org.fabretto.me.language.MessageResource;
import ni.org.fabretto.me.domain.catalogs.Comunidad;
import ni.org.fabretto.me.service.AuditTrailService;
import ni.org.fabretto.me.service.MessageResourceService;
import ni.org.fabretto.me.service.personas.PersonasService;
import ni.org.fabretto.me.service.ComunidadesService;


/**
 * Controlador web de peticiones relacionadas a las personas
 * 
 * @author William Aviles
 */
@Controller
@RequestMapping("/perfil/personas/*")
public class AdminPersonasController {
	private static final Logger logger = LoggerFactory.getLogger(AdminPersonasController.class);
	@Resource(name="auditTrailService")
	private AuditTrailService auditTrailService;
	@Resource(name="personasService")
	private PersonasService personasService;
	@Resource(name="comunidadesService")
	private ComunidadesService comunidadesService;
	@Resource(name="messageResourceService")
	private MessageResourceService messageResourceService;
	
	/**
     * Controlador para presentar pagina de busqueda de personas.
     * @param model Modelo enlazado a la vista
     * @return String con la vista
     */
	@RequestMapping(value = "/", method = RequestMethod.GET)
    public String obtenerPersonas(Model model){ 	
    	logger.debug("Busqueda de personas");
    	return "perfiles/personas/list";
	}
	
	
	@RequestMapping(value = "/resultados/", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody List<Persona> fetchPersonas(@RequestParam(value = "ident", required = false, defaultValue = "") String ident,
    		@RequestParam(value = "primerNombre", required = false, defaultValue = "") String primerNombre,
    		@RequestParam(value = "segundoNombre", required = false, defaultValue = "") String segundoNombre,
    		@RequestParam(value = "primerApellido", required = false, defaultValue = "") String primerApellido,
    		@RequestParam(value = "segundoApellido", required = false, defaultValue = "") String segundoApellido,
    		@RequestParam(value = "daterange", required = false, defaultValue = "") String dateRange,
    		@RequestParam(value = "sexo", required = false, defaultValue = "") String sexo,
    		@RequestParam(value = "comunidad", required = false, defaultValue = "") String comunidad) throws ParseException {
        logger.info("Obteniendo encuestas");
        Long desde = null;
        Long hasta = null;
        if (!dateRange.matches("")) {
        	SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        	desde = formatter.parse(dateRange.substring(0, 10)).getTime();
        	hasta = formatter.parse(dateRange.substring(dateRange.length()-10, dateRange.length())).getTime();
        }
        List<Persona> datos = this.personasService.getPersonas(ident, primerNombre, segundoNombre, primerApellido, segundoApellido, true, desde, hasta, sexo, comunidad);
        if (datos == null){
        	logger.debug("Nulo");
        }
        return datos;
    }
	
	
	/**
     * Controlador para agregar un persona.
     * @param model Modelo enlazado a la vista
     * @return ModelMap con los atributos para la vista
     */
    @RequestMapping(value = "/nuevaPersona/", method = RequestMethod.GET)
	public String initAddPersonaForm(Model model) {
    	List<Comunidad> comunidades = this.comunidadesService.getComunidadesActivas();
    	model.addAttribute("comunidades", comunidades);
    	List<MessageResource> tipos = messageResourceService.getCatalogo("CAT_TIPESC");
    	model.addAttribute("tipos",tipos);
    	List<MessageResource> categorias = messageResourceService.getCatalogo("CAT_CATESC");
    	model.addAttribute("categorias",categorias);
		return "perfiles/personas/enterForm";
	}
    
    /**
     * Controlador para mostrar un persona.
     *
     * @param idUnico el identificador del persona a mostrar
     * @return ModelMap con el modelo y atributos para la vista
     */
    @RequestMapping("/verPersona/{idUnico}/")
    public ModelAndView showPersona(@PathVariable("idUnico") String idUnico) {
    	ModelAndView mav;
    	Persona persona = this.personasService.getPersona(idUnico);
        if(persona==null){
        	mav = new ModelAndView("403");
        }
        else{
        	mav = new ModelAndView("perfiles/personas/viewForm");
        	mav.addObject("persona",persona);
            List<AuditTrail> bitacora = auditTrailService.getBitacora(idUnico);
            mav.addObject("bitacora",bitacora);
        }
        return mav;
    }
    
    
    /**
     * Controlador para editar un persona.
     * @param model Modelo enlazado a la vista
     * @param idUnico el identificador del persona a editar
     * @return String  con la vista
     */
    @RequestMapping(value = "/editPersona/{idUnico}/", method = RequestMethod.GET)
	public String initUpdatePersonaForm(@PathVariable("idUnico") String idUnico, Model model) {
    	Persona personaEditar = this.personasService.getPersona(idUnico);
		if(personaEditar!=null){
			model.addAttribute("persona",personaEditar);
			List<Comunidad> comunidades = this.comunidadesService.getComunidadesActivas();
	    	model.addAttribute("comunidades", comunidades);
	    	List<MessageResource> tipos = messageResourceService.getCatalogo("CAT_TIPESC");
	    	model.addAttribute("tipos",tipos);
	    	List<MessageResource> categorias = messageResourceService.getCatalogo("CAT_CATESC");
	    	model.addAttribute("categorias",categorias);
			return "perfiles/personas/enterForm";
		}
		else{
			return "403";
		}
	}
    
    
    /**
     * Controlador para guardar un persona.
     * 
     * @param idUnico el identificador del persona a guardar
     * @param codigo codigo de la persona
     * @param nombrePersona nombre de la persona
     * @param telefono direccion de la persona
     * @param tipoPersona tipo de persona
     * @param catPersona categoria de persona
     * @param comunidad comunidad de la persona
     * @return ResponseEntity con el persona guardado
     */
    @RequestMapping( value="/guardarPersona/", method=RequestMethod.POST)
	public ResponseEntity<String> processUpdatePersonaForm( @RequestParam(value="idUnico", required=false, defaultValue="" ) String idUnico
			, @RequestParam( value="codigo", required=true ) String codigo
	        , @RequestParam( value="nombrePersona", required=true ) String nombrePersona
	        , @RequestParam( value="tipoPersona", required=true ) String tipoPersona
	        , @RequestParam( value="catPersona", required=true ) String catPersona
	        , @RequestParam( value="telefono", required=false, defaultValue="" ) String telefono
	        , @RequestParam( value="comunidad", required=true ) String comunidad
	        )
	{
    	try{
			Persona persona = new Persona();
			//Si el idUnico viene en blanco es un nuevo persona
			if (idUnico.equals("")){
				//Crear un nuevo persona
				idUnico = new UUID(SecurityContextHolder.getContext().getAuthentication().getName().hashCode(),new Date().hashCode()).toString();
				persona.setIdUnico(idUnico);
				persona.setUsuarioRegistro(SecurityContextHolder.getContext().getAuthentication().getName());
				persona.setFechaRegistro(new Date());
				WebAuthenticationDetails wad  = (WebAuthenticationDetails) SecurityContextHolder.getContext().getAuthentication().getDetails();
				persona.setIdEquipo(wad.getRemoteAddress());
			}
			//Si el idUnico no viene en blanco hay que editar el persona
			else{
				//Recupera el persona de la base de datos
				persona = this.personasService.getPersona(idUnico);
			}
			//persona.setCodigo(codigo);
			//persona.setNombrePersona(nombrePersona);
			//persona.setTipoPersona(tipoPersona);
			//persona.setCatPersona(catPersona);
			//persona.setTelefono(telefono);
			//persona.setComunidad(this.comunidadesService.getComunidad(comunidad));
			//Actualiza el persona
			this.personasService.savePersona(persona);
			persona.setComunidad(null);
			return createJsonResponse(persona);
    	}
		catch (DataIntegrityViolationException e){
			String message = e.getMostSpecificCause().getMessage();
			Gson gson = new Gson();
		    String json = gson.toJson(message);
			return new ResponseEntity<String>( json, HttpStatus.CREATED);
		}
		catch(Exception e){
			Gson gson = new Gson();
		    String json = gson.toJson(e.toString());
			return new ResponseEntity<String>( json, HttpStatus.CREATED);
		}
    	
	}
   
    
    /**
     * Controlador para deshabilitar un persona.
     *
     * @param idUnico el identificador del persona a deshabilitar
     * @param redirectAttributes Regresa nombre de persona
     * @return a String
     */
    @RequestMapping("/desPersona/{idUnico}/")
    public String disablePersona(@PathVariable("idUnico") String idUnico, 
    		RedirectAttributes redirectAttributes) {
    	String redirecTo="404";
		Persona persona = this.personasService.getPersona(idUnico);
    	if(persona!=null){
    		persona.setPasivo('1');
    		this.personasService.savePersona(persona);
    		redirectAttributes.addFlashAttribute("personaDeshabilitado", true);
    		redirectAttributes.addFlashAttribute("nombrePersona", persona.getNombreCompleto());
    		redirecTo = "redirect:/perfil/personas/";
    	}
    	else{
    		redirecTo = "403";
    	}
    	return redirecTo;	
    }
    
    
    /**
     * Controlador para habilitar un persona.
     *
     * @param idUnico el identificador del persona a habilitar
     * @param redirectAttributes Regresa nombre de persona
     * @return a String
     */
    @RequestMapping("/habPersona/{idUnico}/")
    public String enableUser(@PathVariable("idUnico") String idUnico, 
    		RedirectAttributes redirectAttributes) {
    	String redirecTo="404";
    	Persona persona = this.personasService.getPersona(idUnico);
    	if(persona!=null){
    		persona.setPasivo('0');
    		this.personasService.savePersona(persona);
    		redirectAttributes.addFlashAttribute("personaHabilitado", true);
    		redirectAttributes.addFlashAttribute("nombrePersona", persona.getNombreCompleto());
    		redirecTo = "redirect:/perfil/personas/";
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

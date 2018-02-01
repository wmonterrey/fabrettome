package ni.org.fabretto.me.web.controller;

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
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.google.gson.Gson;

import ni.org.fabretto.me.domain.audit.AuditTrail;
import ni.org.fabretto.me.domain.catalogs.Escuela;
import ni.org.fabretto.me.language.MessageResource;
import ni.org.fabretto.me.domain.catalogs.Centro;
import ni.org.fabretto.me.domain.catalogs.Comunidad;
import ni.org.fabretto.me.service.AuditTrailService;
import ni.org.fabretto.me.service.CentrosService;
import ni.org.fabretto.me.service.EscuelasService;
import ni.org.fabretto.me.service.MessageResourceService;
import ni.org.fabretto.me.service.ComunidadesService;


/**
 * Controlador web de peticiones relacionadas a los escuelas
 * 
 * @author William Aviles
 */
@Controller
@RequestMapping("/super/escuelas/*")
public class AdminEscuelasController {
	private static final Logger logger = LoggerFactory.getLogger(AdminEscuelasController.class);
	@Resource(name="auditTrailService")
	private AuditTrailService auditTrailService;
	@Resource(name="escuelasService")
	private EscuelasService escuelasService;
	@Resource(name="comunidadesService")
	private ComunidadesService comunidadesService;
	@Resource(name="centrosService")
	private CentrosService centrosService;
	@Resource(name="messageResourceService")
	private MessageResourceService messageResourceService;
	
	/**
     * Controlador para presentar lista de escuelas.
     * @param model Modelo enlazado a la vista
     * @return String con la vista
     */
	@RequestMapping(value = "/", method = RequestMethod.GET)
    public String obtenerEscuelas(Model model){ 	
    	logger.debug("Catalogo de escuelas");
    	List<Escuela> escuelas = escuelasService.getEscuelas();
    	model.addAttribute("escuelas", escuelas);
    	return "admin/escuelas/list";
	}
	
	
	/**
     * Controlador para agregar un escuela.
     * @param model Modelo enlazado a la vista
     * @return ModelMap con los atributos para la vista
     */
    @RequestMapping(value = "/nuevoEscuela/", method = RequestMethod.GET)
	public String initAddEscuelaForm(Model model) {
    	List<Comunidad> comunidades = this.comunidadesService.getComunidadesActivas();
    	model.addAttribute("comunidades", comunidades);
    	List<Centro> centros = this.centrosService.getCentrosActivos();
    	model.addAttribute("centros", centros);
    	List<MessageResource> tipos = messageResourceService.getCatalogo("CAT_TIPESC");
    	model.addAttribute("tipos",tipos);
    	List<MessageResource> categorias = messageResourceService.getCatalogo("CAT_CATESC");
    	model.addAttribute("categorias",categorias);
		return "admin/escuelas/enterForm";
	}
    
    /**
     * Controlador para mostrar un escuela.
     *
     * @param idUnico el identificador del escuela a mostrar
     * @return ModelMap con el modelo y atributos para la vista
     */
    @RequestMapping("/verEscuela/{idUnico}/")
    public ModelAndView showEscuela(@PathVariable("idUnico") String idUnico) {
    	ModelAndView mav;
    	Escuela escuela = this.escuelasService.getEscuela(idUnico);
        if(escuela==null){
        	mav = new ModelAndView("403");
        }
        else{
        	mav = new ModelAndView("admin/escuelas/viewForm");
        	mav.addObject("escuela",escuela);
            List<AuditTrail> bitacora = auditTrailService.getBitacora(idUnico);
            mav.addObject("bitacora",bitacora);
        }
        return mav;
    }
    
    
    /**
     * Controlador para editar un escuela.
     * @param model Modelo enlazado a la vista
     * @param idUnico el identificador del escuela a editar
     * @return String  con la vista
     */
    @RequestMapping(value = "/editEscuela/{idUnico}/", method = RequestMethod.GET)
	public String initUpdateEscuelaForm(@PathVariable("idUnico") String idUnico, Model model) {
    	Escuela escuelaEditar = this.escuelasService.getEscuela(idUnico);
		if(escuelaEditar!=null){
			model.addAttribute("escuela",escuelaEditar);
			List<Comunidad> comunidades = this.comunidadesService.getComunidadesActivas();
	    	model.addAttribute("comunidades", comunidades);
	    	List<Centro> centros = this.centrosService.getCentrosActivos();
	    	model.addAttribute("centros", centros);
	    	List<MessageResource> tipos = messageResourceService.getCatalogo("CAT_TIPESC");
	    	model.addAttribute("tipos",tipos);
	    	List<MessageResource> categorias = messageResourceService.getCatalogo("CAT_CATESC");
	    	model.addAttribute("categorias",categorias);
			return "admin/escuelas/enterForm";
		}
		else{
			return "403";
		}
	}
    
    
    /**
     * Controlador para guardar un escuela.
     * 
     * @param idUnico el identificador del escuela a guardar
     * @param codigo codigo de la escuela
     * @param nombreEscuela nombre de la escuela
     * @param telefono direccion de la escuela
     * @param tipoEscuela tipo de escuela
     * @param catEscuela categoria de escuela
     * @param comunidad comunidad de la escuela
     * @param centro centro de la escuela
     * @return ResponseEntity con el escuela guardado
     */
    @RequestMapping( value="/guardarEscuela/", method=RequestMethod.POST)
	public ResponseEntity<String> processUpdateEscuelaForm( @RequestParam(value="idUnico", required=false, defaultValue="" ) String idUnico
			, @RequestParam( value="codigo", required=true ) String codigo
	        , @RequestParam( value="nombreEscuela", required=true ) String nombreEscuela
	        , @RequestParam( value="tipoEscuela", required=true ) String tipoEscuela
	        , @RequestParam( value="catEscuela", required=true ) String catEscuela
	        , @RequestParam( value="telefono", required=false, defaultValue="" ) String telefono
	        , @RequestParam( value="comunidad", required=true ) String comunidad
	        , @RequestParam( value="centro", required=true ) String centro
	        )
	{
    	try{
			Escuela escuela = new Escuela();
			//Si el idUnico viene en blanco es un nuevo escuela
			if (idUnico.equals("")){
				//Crear un nuevo escuela
				idUnico = new UUID(SecurityContextHolder.getContext().getAuthentication().getName().hashCode(),new Date().hashCode()).toString();
				escuela.setIdUnico(idUnico);
				escuela.setUsuarioRegistro(SecurityContextHolder.getContext().getAuthentication().getName());
				escuela.setFechaRegistro(new Date());
				WebAuthenticationDetails wad  = (WebAuthenticationDetails) SecurityContextHolder.getContext().getAuthentication().getDetails();
				escuela.setIdEquipo(wad.getRemoteAddress());
			}
			//Si el idUnico no viene en blanco hay que editar el escuela
			else{
				//Recupera el escuela de la base de datos
				escuela = this.escuelasService.getEscuela(idUnico);
			}
			escuela.setCodigo(codigo);
			escuela.setNombreEscuela(nombreEscuela);
			escuela.setTipoEscuela(tipoEscuela);
			escuela.setCatEscuela(catEscuela);
			escuela.setCentro(this.centrosService.getCentro(centro));
			escuela.setTelefono(telefono);
			escuela.setComunidad(this.comunidadesService.getComunidad(comunidad));
			//Actualiza el escuela
			this.escuelasService.saveEscuela(escuela);
			return createJsonResponse(escuela);
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
     * Controlador para deshabilitar un escuela.
     *
     * @param idUnico el identificador del escuela a deshabilitar
     * @param redirectAttributes Regresa nombre de escuela
     * @return a String
     */
    @RequestMapping("/desEscuela/{idUnico}/")
    public String disableEscuela(@PathVariable("idUnico") String idUnico, 
    		RedirectAttributes redirectAttributes) {
    	String redirecTo="404";
		Escuela escuela = this.escuelasService.getEscuela(idUnico);
    	if(escuela!=null){
    		escuela.setPasivo('1');
    		this.escuelasService.saveEscuela(escuela);
    		redirectAttributes.addFlashAttribute("escuelaDeshabilitado", true);
    		redirectAttributes.addFlashAttribute("nombreEscuela", escuela.getNombreEscuela());
    		redirecTo = "redirect:/super/escuelas/";
    	}
    	else{
    		redirecTo = "403";
    	}
    	return redirecTo;	
    }
    
    
    /**
     * Controlador para habilitar un escuela.
     *
     * @param idUnico el identificador del escuela a habilitar
     * @param redirectAttributes Regresa nombre de escuela
     * @return a String
     */
    @RequestMapping("/habEscuela/{idUnico}/")
    public String enableUser(@PathVariable("idUnico") String idUnico, 
    		RedirectAttributes redirectAttributes) {
    	String redirecTo="404";
    	Escuela escuela = this.escuelasService.getEscuela(idUnico);
    	if(escuela!=null){
    		escuela.setPasivo('0');
    		this.escuelasService.saveEscuela(escuela);
    		redirectAttributes.addFlashAttribute("escuelaHabilitado", true);
    		redirectAttributes.addFlashAttribute("nombreEscuela", escuela.getNombreEscuela());
    		redirecTo = "redirect:/super/escuelas/";
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

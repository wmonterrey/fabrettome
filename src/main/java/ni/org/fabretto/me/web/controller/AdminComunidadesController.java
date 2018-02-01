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
import ni.org.fabretto.me.domain.catalogs.Comunidad;
import ni.org.fabretto.me.domain.catalogs.Municipio;
import ni.org.fabretto.me.service.AuditTrailService;
import ni.org.fabretto.me.service.ComunidadesService;
import ni.org.fabretto.me.service.MunicipiosService;


/**
 * Controlador web de peticiones relacionadas a los comunidades
 * 
 * @author William Aviles
 */
@Controller
@RequestMapping("/super/comunidades/*")
public class AdminComunidadesController {
	private static final Logger logger = LoggerFactory.getLogger(AdminComunidadesController.class);
	@Resource(name="auditTrailService")
	private AuditTrailService auditTrailService;
	@Resource(name="comunidadesService")
	private ComunidadesService comunidadesService;
	@Resource(name="municipiosService")
	private MunicipiosService municipiosService;
	
	/**
     * Controlador para presentar lista de comunidades.
     * @param model Modelo enlazado a la vista
     * @return String con la vista
     */
	@RequestMapping(value = "/", method = RequestMethod.GET)
    public String obtenerComunidades(Model model){ 	
    	logger.debug("Catalogo de comunidades");
    	List<Comunidad> comunidades = comunidadesService.getComunidades();
    	model.addAttribute("comunidades", comunidades);
    	return "admin/comunidades/list";
	}
	
	
	/**
     * Controlador para agregar un comunidad.
     * @param model Modelo enlazado a la vista
     * @return ModelMap con los atributos para la vista
     */
    @RequestMapping(value = "/nuevoComunidad/", method = RequestMethod.GET)
	public String initAddComunidadForm(Model model) {
    	List<Municipio> municipios = this.municipiosService.getMunicipiosActivos();
    	model.addAttribute("municipios", municipios);
		return "admin/comunidades/enterForm";
	}
    
    /**
     * Controlador para mostrar un comunidad.
     *
     * @param idUnico el identificador del comunidad a mostrar
     * @return ModelMap con el modelo y atributos para la vista
     */
    @RequestMapping("/verComunidad/{idUnico}/")
    public ModelAndView showComunidad(@PathVariable("idUnico") String idUnico) {
    	ModelAndView mav;
    	Comunidad comunidad = this.comunidadesService.getComunidad(idUnico);
        if(comunidad==null){
        	mav = new ModelAndView("403");
        }
        else{
        	mav = new ModelAndView("admin/comunidades/viewForm");
        	mav.addObject("comunidad",comunidad);
            List<AuditTrail> bitacora = auditTrailService.getBitacora(idUnico);
            mav.addObject("bitacora",bitacora);
        }
        return mav;
    }
    
    
    /**
     * Controlador para editar un comunidad.
     * @param model Modelo enlazado a la vista
     * @param idUnico el identificador del comunidad a editar
     * @return String  con la vista
     */
    @RequestMapping(value = "/editComunidad/{idUnico}/", method = RequestMethod.GET)
	public String initUpdateComunidadForm(@PathVariable("idUnico") String idUnico, Model model) {
    	Comunidad comunidadEditar = this.comunidadesService.getComunidad(idUnico);
		if(comunidadEditar!=null){
			model.addAttribute("comunidad",comunidadEditar);
			List<Municipio> municipios = this.municipiosService.getMunicipiosActivos();
	    	model.addAttribute("municipios", municipios);
			return "admin/comunidades/enterForm";
		}
		else{
			return "403";
		}
	}
    
    
    /**
     * Controlador para guardar un comunidad.
     * 
     * @param idUnico el identificador del comunidad a guardar
     * @param nombreComunidad nombre de la comunidad
     * @param descComunidad descripcion de la comunidad
     * @param municipio municipio de la comunidad
     * @return ResponseEntity con el comunidad guardado
     */
    @RequestMapping( value="/guardarComunidad/", method=RequestMethod.POST)
	public ResponseEntity<String> processUpdateComunidadForm( @RequestParam(value="idUnico", required=false, defaultValue="" ) String idUnico
	        , @RequestParam( value="nombreComunidad", required=true ) String nombreComunidad
	        , @RequestParam( value="descComunidad", required=true ) String descComunidad
	        , @RequestParam( value="municipio", required=true ) String municipio
	        )
	{
    	try{
			Comunidad comunidad = new Comunidad();
			//Si el idUnico viene en blanco es un nuevo comunidad
			if (idUnico.equals("")){
				//Crear un nuevo comunidad
				idUnico = new UUID(SecurityContextHolder.getContext().getAuthentication().getName().hashCode(),new Date().hashCode()).toString();
				comunidad.setIdUnico(idUnico);
				comunidad.setUsuarioRegistro(SecurityContextHolder.getContext().getAuthentication().getName());
				comunidad.setFechaRegistro(new Date());
				WebAuthenticationDetails wad  = (WebAuthenticationDetails) SecurityContextHolder.getContext().getAuthentication().getDetails();
				comunidad.setIdEquipo(wad.getRemoteAddress());
			}
			//Si el idUnico no viene en blanco hay que editar el comunidad
			else{
				//Recupera el comunidad de la base de datos
				comunidad = this.comunidadesService.getComunidad(idUnico);
			}
			comunidad.setNombreComunidad(nombreComunidad);
			comunidad.setDescComunidad(descComunidad);
			comunidad.setMunicipio(this.municipiosService.getMunicipio(municipio));
			//Actualiza el comunidad
			this.comunidadesService.saveComunidad(comunidad);
			return createJsonResponse(comunidad);
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
     * Controlador para deshabilitar un comunidad.
     *
     * @param idUnico el identificador del comunidad a deshabilitar
     * @param redirectAttributes Regresa nombre de comunidad
     * @return a String
     */
    @RequestMapping("/desComunidad/{idUnico}/")
    public String disableComunidad(@PathVariable("idUnico") String idUnico, 
    		RedirectAttributes redirectAttributes) {
    	String redirecTo="404";
		Comunidad comunidad = this.comunidadesService.getComunidad(idUnico);
    	if(comunidad!=null){
    		comunidad.setPasivo('1');
    		this.comunidadesService.saveComunidad(comunidad);
    		redirectAttributes.addFlashAttribute("comunidadDeshabilitado", true);
    		redirectAttributes.addFlashAttribute("nombreComunidad", comunidad.getNombreComunidad());
    		redirecTo = "redirect:/super/comunidades/";
    	}
    	else{
    		redirecTo = "403";
    	}
    	return redirecTo;	
    }
    
    
    /**
     * Controlador para habilitar un comunidad.
     *
     * @param idUnico el identificador del comunidad a habilitar
     * @param redirectAttributes Regresa nombre de comunidad
     * @return a String
     */
    @RequestMapping("/habComunidad/{idUnico}/")
    public String enableUser(@PathVariable("idUnico") String idUnico, 
    		RedirectAttributes redirectAttributes) {
    	String redirecTo="404";
    	Comunidad comunidad = this.comunidadesService.getComunidad(idUnico);
    	if(comunidad!=null){
    		comunidad.setPasivo('0');
    		this.comunidadesService.saveComunidad(comunidad);
    		redirectAttributes.addFlashAttribute("comunidadHabilitado", true);
    		redirectAttributes.addFlashAttribute("nombreComunidad", comunidad.getNombreComunidad());
    		redirecTo = "redirect:/super/comunidades/";
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

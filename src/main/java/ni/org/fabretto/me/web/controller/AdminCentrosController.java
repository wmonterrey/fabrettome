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

import ni.org.fabretto.me.domain.Colaborador;
import ni.org.fabretto.me.domain.audit.AuditTrail;
import ni.org.fabretto.me.domain.catalogs.Centro;
import ni.org.fabretto.me.domain.catalogs.Comunidad;
import ni.org.fabretto.me.service.AuditTrailService;
import ni.org.fabretto.me.service.CentrosService;
import ni.org.fabretto.me.service.ComunidadesService;
import ni.org.fabretto.me.service.ColaboradoresService;


/**
 * Controlador web de peticiones relacionadas a los centros
 * 
 * @author William Aviles
 */
@Controller
@RequestMapping("/super/centros/*")
public class AdminCentrosController {
	private static final Logger logger = LoggerFactory.getLogger(AdminCentrosController.class);
	@Resource(name="auditTrailService")
	private AuditTrailService auditTrailService;
	@Resource(name="centrosService")
	private CentrosService centrosService;
	@Resource(name="comunidadesService")
	private ComunidadesService comunidadesService;
	@Resource(name="colaboradoresService")
	private ColaboradoresService colaboradoresService;
	
	/**
     * Controlador para presentar lista de centros.
     * @param model Modelo enlazado a la vista
     * @return String con la vista
     */
	@RequestMapping(value = "/", method = RequestMethod.GET)
    public String obtenerCentros(Model model){ 	
    	logger.debug("Catalogo de centros");
    	List<Centro> centros = centrosService.getCentros();
    	model.addAttribute("centros", centros);
    	return "admin/centros/list";
	}
	
	
	/**
     * Controlador para agregar un centro.
     * @param model Modelo enlazado a la vista
     * @return ModelMap con los atributos para la vista
     */
    @RequestMapping(value = "/nuevoCentro/", method = RequestMethod.GET)
	public String initAddCentroForm(Model model) {
    	List<Comunidad> comunidades = this.comunidadesService.getComunidadesActivas();
    	model.addAttribute("comunidades", comunidades);
    	List<Colaborador> personas = colaboradoresService.getColaboradoresActivos();
    	model.addAttribute("personas", personas);
		return "admin/centros/enterForm";
	}
    
    /**
     * Controlador para mostrar un centro.
     *
     * @param idUnico el identificador del centro a mostrar
     * @return ModelMap con el modelo y atributos para la vista
     */
    @RequestMapping("/verCentro/{idUnico}/")
    public ModelAndView showCentro(@PathVariable("idUnico") String idUnico) {
    	ModelAndView mav;
    	Centro centro = this.centrosService.getCentro(idUnico);
        if(centro==null){
        	mav = new ModelAndView("403");
        }
        else{
        	mav = new ModelAndView("admin/centros/viewForm");
        	mav.addObject("centro",centro);
            List<AuditTrail> bitacora = auditTrailService.getBitacora(idUnico);
            mav.addObject("bitacora",bitacora);
        }
        return mav;
    }
    
    
    /**
     * Controlador para editar un centro.
     * @param model Modelo enlazado a la vista
     * @param idUnico el identificador del centro a editar
     * @return String  con la vista
     */
    @RequestMapping(value = "/editCentro/{idUnico}/", method = RequestMethod.GET)
	public String initUpdateCentroForm(@PathVariable("idUnico") String idUnico, Model model) {
    	Centro centroEditar = this.centrosService.getCentro(idUnico);
		if(centroEditar!=null){
			model.addAttribute("centro",centroEditar);
			List<Comunidad> comunidades = this.comunidadesService.getComunidadesActivas();
	    	model.addAttribute("comunidades", comunidades);
	    	List<Colaborador> personas = colaboradoresService.getColaboradoresActivos();
	    	model.addAttribute("personas", personas);
			return "admin/centros/enterForm";
		}
		else{
			return "403";
		}
	}
    
    
    /**
     * Controlador para guardar un centro.
     * 
     * @param idUnico el identificador del centro a guardar
     * @param codigo codigo del centro
     * @param nombreCentro nombre del centro
     * @param direccion direccion del centro
     * @param telefono direccion del centro
     * @param comunidad comunidad del centro
     * @param director Director del centro del centro
     * @return ResponseEntity con el centro guardado
     */
    @RequestMapping( value="/guardarCentro/", method=RequestMethod.POST)
	public ResponseEntity<String> processUpdateCentroForm( @RequestParam(value="idUnico", required=false, defaultValue="" ) String idUnico
			, @RequestParam( value="codigo", required=true ) String codigo
	        , @RequestParam( value="nombreCentro", required=true ) String nombreCentro
	        , @RequestParam( value="direccion", required=true ) String direccion
	        , @RequestParam( value="telefono", required=false, defaultValue="" ) String telefono
	        , @RequestParam( value="comunidad", required=false ) String comunidad
	        , @RequestParam( value="director", required=false ) String director
	        )
	{
    	try{
			Centro centro = new Centro();
			//Si el idUnico viene en blanco es un nuevo centro
			if (idUnico.equals("")){
				//Crear un nuevo centro
				idUnico = new UUID(SecurityContextHolder.getContext().getAuthentication().getName().hashCode(),new Date().hashCode()).toString();
				centro.setIdUnico(idUnico);
				centro.setUsuarioRegistro(SecurityContextHolder.getContext().getAuthentication().getName());
				centro.setFechaRegistro(new Date());
				WebAuthenticationDetails wad  = (WebAuthenticationDetails) SecurityContextHolder.getContext().getAuthentication().getDetails();
				centro.setIdEquipo(wad.getRemoteAddress());
			}
			//Si el idUnico no viene en blanco hay que editar el centro
			else{
				//Recupera el centro de la base de datos
				centro = this.centrosService.getCentro(idUnico);
			}
			centro.setCodigo(codigo);
			centro.setNombreCentro(nombreCentro);
			centro.setDireccion(direccion);
			centro.setTelefono(telefono);
			centro.setComunidad(this.comunidadesService.getComunidad(comunidad));
			centro.setDirector(this.colaboradoresService.getColaborador(director));
			//Actualiza el centro
			this.centrosService.saveCentro(centro);
			return createJsonResponse(centro);
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
     * Controlador para deshabilitar un centro.
     *
     * @param idUnico el identificador del centro a deshabilitar
     * @param redirectAttributes Regresa nombre de centro
     * @return a String
     */
    @RequestMapping("/desCentro/{idUnico}/")
    public String disableCentro(@PathVariable("idUnico") String idUnico, 
    		RedirectAttributes redirectAttributes) {
    	String redirecTo="404";
		Centro centro = this.centrosService.getCentro(idUnico);
    	if(centro!=null){
    		centro.setPasivo('1');
    		this.centrosService.saveCentro(centro);
    		redirectAttributes.addFlashAttribute("centroDeshabilitado", true);
    		redirectAttributes.addFlashAttribute("nombreCentro", centro.getNombreCentro());
    		redirecTo = "redirect:/super/centros/";
    	}
    	else{
    		redirecTo = "403";
    	}
    	return redirecTo;	
    }
    
    
    /**
     * Controlador para habilitar un centro.
     *
     * @param idUnico el identificador del centro a habilitar
     * @param redirectAttributes Regresa nombre de centro
     * @return a String
     */
    @RequestMapping("/habCentro/{idUnico}/")
    public String enableUser(@PathVariable("idUnico") String idUnico, 
    		RedirectAttributes redirectAttributes) {
    	String redirecTo="404";
    	Centro centro = this.centrosService.getCentro(idUnico);
    	if(centro!=null){
    		centro.setPasivo('0');
    		this.centrosService.saveCentro(centro);
    		redirectAttributes.addFlashAttribute("centroHabilitado", true);
    		redirectAttributes.addFlashAttribute("nombreCentro", centro.getNombreCentro());
    		redirecTo = "redirect:/super/centros/";
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

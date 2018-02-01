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
import ni.org.fabretto.me.domain.catalogs.Municipio;
import ni.org.fabretto.me.domain.catalogs.Departamento;
import ni.org.fabretto.me.service.AuditTrailService;
import ni.org.fabretto.me.service.MunicipiosService;
import ni.org.fabretto.me.service.DepartamentosService;


/**
 * Controlador web de peticiones relacionadas a los municipios
 * 
 * @author William Aviles
 */
@Controller
@RequestMapping("/super/municipios/*")
public class AdminMunicipiosController {
	private static final Logger logger = LoggerFactory.getLogger(AdminMunicipiosController.class);
	@Resource(name="auditTrailService")
	private AuditTrailService auditTrailService;
	@Resource(name="municipiosService")
	private MunicipiosService municipiosService;
	@Resource(name="departamentosService")
	private DepartamentosService departamentosService;
	
	/**
     * Controlador para presentar lista de municipios.
     * @param model Modelo enlazado a la vista
     * @return String con la vista
     */
	@RequestMapping(value = "/", method = RequestMethod.GET)
    public String obtenerMunicipios(Model model){ 	
    	logger.debug("Catalogo de municipios");
    	List<Municipio> municipios = municipiosService.getMunicipios();
    	model.addAttribute("municipios", municipios);
    	return "admin/municipios/list";
	}
	
	
	/**
     * Controlador para agregar un municipio.
     * @param model Modelo enlazado a la vista
     * @return ModelMap con los atributos para la vista
     */
    @RequestMapping(value = "/nuevoMunicipio/", method = RequestMethod.GET)
	public String initAddMunicipioForm(Model model) {
    	List<Departamento> departamentos = this.departamentosService.getDepartamentosActivos();
    	model.addAttribute("departamentos", departamentos);
		return "admin/municipios/enterForm";
	}
    
    /**
     * Controlador para mostrar un municipio.
     *
     * @param idUnico el identificador del municipio a mostrar
     * @return ModelMap con el modelo y atributos para la vista
     */
    @RequestMapping("/verMunicipio/{idUnico}/")
    public ModelAndView showMunicipio(@PathVariable("idUnico") String idUnico) {
    	ModelAndView mav;
    	Municipio municipio = this.municipiosService.getMunicipio(idUnico);
        if(municipio==null){
        	mav = new ModelAndView("403");
        }
        else{
        	mav = new ModelAndView("admin/municipios/viewForm");
        	mav.addObject("municipio",municipio);
            List<AuditTrail> bitacora = auditTrailService.getBitacora(idUnico);
            mav.addObject("bitacora",bitacora);
        }
        return mav;
    }
    
    
    /**
     * Controlador para editar un municipio.
     * @param model Modelo enlazado a la vista
     * @param idUnico el identificador del municipio a editar
     * @return String  con la vista
     */
    @RequestMapping(value = "/editMunicipio/{idUnico}/", method = RequestMethod.GET)
	public String initUpdateMunicipioForm(@PathVariable("idUnico") String idUnico, Model model) {
    	Municipio municipioEditar = this.municipiosService.getMunicipio(idUnico);
		if(municipioEditar!=null){
			model.addAttribute("municipio",municipioEditar);
			List<Departamento> departamentos = this.departamentosService.getDepartamentosActivos();
	    	model.addAttribute("departamentos", departamentos);
			return "admin/municipios/enterForm";
		}
		else{
			return "403";
		}
	}
    
    
    /**
     * Controlador para guardar un municipio.
     * 
     * @param idUnico el identificador del municipio a guardar
     * @param nombreMunicipio nombre del municipio
     * @param departamento departamento del municipio
     * @return ResponseEntity con el municipio guardado
     */
    @RequestMapping( value="/guardarMunicipio/", method=RequestMethod.POST)
	public ResponseEntity<String> processUpdateMunicipioForm( @RequestParam(value="idUnico", required=false, defaultValue="" ) String idUnico
	        , @RequestParam( value="nombreMunicipio", required=true ) String nombreMunicipio
	        , @RequestParam( value="departamento", required=true ) String departamento
	        )
	{
    	try{
			Municipio municipio = new Municipio();
			//Si el idUnico viene en blanco es un nuevo municipio
			if (idUnico.equals("")){
				//Crear un nuevo municipio
				idUnico = new UUID(SecurityContextHolder.getContext().getAuthentication().getName().hashCode(),new Date().hashCode()).toString();
				municipio.setIdUnico(idUnico);
				municipio.setUsuarioRegistro(SecurityContextHolder.getContext().getAuthentication().getName());
				municipio.setFechaRegistro(new Date());
				WebAuthenticationDetails wad  = (WebAuthenticationDetails) SecurityContextHolder.getContext().getAuthentication().getDetails();
				municipio.setIdEquipo(wad.getRemoteAddress());
			}
			//Si el idUnico no viene en blanco hay que editar el municipio
			else{
				//Recupera el municipio de la base de datos
				municipio = this.municipiosService.getMunicipio(idUnico);
			}
			municipio.setNombreMunicipio(nombreMunicipio);
			municipio.setDepartamento(this.departamentosService.getDepartamento(departamento));
			//Actualiza el municipio
			this.municipiosService.saveMunicipio(municipio);
			return createJsonResponse(municipio);
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
     * Controlador para deshabilitar un municipio.
     *
     * @param idUnico el identificador del municipio a deshabilitar
     * @param redirectAttributes Regresa nombre de municipio
     * @return a String
     */
    @RequestMapping("/desMunicipio/{idUnico}/")
    public String disableMunicipio(@PathVariable("idUnico") String idUnico, 
    		RedirectAttributes redirectAttributes) {
    	String redirecTo="404";
		Municipio municipio = this.municipiosService.getMunicipio(idUnico);
    	if(municipio!=null){
    		municipio.setPasivo('1');
    		this.municipiosService.saveMunicipio(municipio);
    		redirectAttributes.addFlashAttribute("municipioDeshabilitado", true);
    		redirectAttributes.addFlashAttribute("nombreMunicipio", municipio.getNombreMunicipio());
    		redirecTo = "redirect:/super/municipios/";
    	}
    	else{
    		redirecTo = "403";
    	}
    	return redirecTo;	
    }
    
    
    /**
     * Controlador para habilitar un municipio.
     *
     * @param idUnico el identificador del municipio a habilitar
     * @param redirectAttributes Regresa nombre de municipio
     * @return a String
     */
    @RequestMapping("/habMunicipio/{idUnico}/")
    public String enableUser(@PathVariable("idUnico") String idUnico, 
    		RedirectAttributes redirectAttributes) {
    	String redirecTo="404";
    	Municipio municipio = this.municipiosService.getMunicipio(idUnico);
    	if(municipio!=null){
    		municipio.setPasivo('0');
    		this.municipiosService.saveMunicipio(municipio);
    		redirectAttributes.addFlashAttribute("municipioHabilitado", true);
    		redirectAttributes.addFlashAttribute("nombreMunicipio", municipio.getNombreMunicipio());
    		redirecTo = "redirect:/super/municipios/";
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

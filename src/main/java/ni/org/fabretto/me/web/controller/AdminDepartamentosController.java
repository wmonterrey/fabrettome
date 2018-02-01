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
import ni.org.fabretto.me.domain.catalogs.Departamento;
import ni.org.fabretto.me.service.AuditTrailService;
import ni.org.fabretto.me.service.DepartamentosService;


/**
 * Controlador web de peticiones relacionadas a los departamentos
 * 
 * @author William Aviles
 */
@Controller
@RequestMapping("/super/departamentos/*")
public class AdminDepartamentosController {
	private static final Logger logger = LoggerFactory.getLogger(AdminDepartamentosController.class);
	@Resource(name="auditTrailService")
	private AuditTrailService auditTrailService;
	@Resource(name="departamentosService")
	private DepartamentosService departamentosService;
	
	/**
     * Controlador para presentar lista de departamentos.
     * @param model Modelo enlazado a la vista
     * @return String con la vista
     */
	@RequestMapping(value = "/", method = RequestMethod.GET)
    public String obtenerDepartamentos(Model model){ 	
    	logger.debug("Catalogo de departamentos");
    	List<Departamento> departamentos = departamentosService.getDepartamentos();
    	model.addAttribute("departamentos", departamentos);
    	return "admin/departamentos/list";
	}
	
	
	/**
     * Controlador para agregar un departamento.
     * @param model Modelo enlazado a la vista
     * @return ModelMap con los atributos para la vista
     */
    @RequestMapping(value = "/nuevoDepartamento/", method = RequestMethod.GET)
	public String initAddDepartamentoForm(Model model) {
		return "admin/departamentos/enterForm";
	}
    
    /**
     * Controlador para mostrar un departamento.
     *
     * @param idUnico el identificador del departamento a mostrar
     * @return ModelMap con el modelo y atributos para la vista
     */
    @RequestMapping("/verDepartamento/{idUnico}/")
    public ModelAndView showDepartamento(@PathVariable("idUnico") String idUnico) {
    	ModelAndView mav;
    	Departamento departamento = this.departamentosService.getDepartamento(idUnico);
        if(departamento==null){
        	mav = new ModelAndView("403");
        }
        else{
        	mav = new ModelAndView("admin/departamentos/viewForm");
        	mav.addObject("departamento",departamento);
            List<AuditTrail> bitacora = auditTrailService.getBitacora(idUnico);
            mav.addObject("bitacora",bitacora);
        }
        return mav;
    }
    
    
    /**
     * Controlador para editar un departamento.
     * @param model Modelo enlazado a la vista
     * @param idUnico el identificador del departamento a editar
     * @return String  con la vista
     */
    @RequestMapping(value = "/editDepartamento/{idUnico}/", method = RequestMethod.GET)
	public String initUpdateDepartamentoForm(@PathVariable("idUnico") String idUnico, Model model) {
    	Departamento departamentoEditar = this.departamentosService.getDepartamento(idUnico);
		if(departamentoEditar!=null){
			model.addAttribute("departamento",departamentoEditar);
			return "admin/departamentos/enterForm";
		}
		else{
			return "403";
		}
	}
    
    
    /**
     * Controlador para guardar un departamento.
     * 
     * @param idUnico el identificador del departamento a guardar
     * @param nombreDepartamento nombre del departamento
     * @return ResponseEntity con el departamento guardado
     */
    @RequestMapping( value="/guardarDepartamento/", method=RequestMethod.POST)
	public ResponseEntity<String> processUpdateDepartamentoForm( @RequestParam(value="idUnico", required=false, defaultValue="" ) String idUnico
	        , @RequestParam( value="nombreDepartamento", required=true ) String nombreDepartamento
	        )
	{
    	try{
			Departamento departamento = new Departamento();
			//Si el idUnico viene en blanco es un nuevo departamento
			if (idUnico.equals("")){
				//Crear un nuevo departamento
				idUnico = new UUID(SecurityContextHolder.getContext().getAuthentication().getName().hashCode(),new Date().hashCode()).toString();
				departamento.setIdUnico(idUnico);
				departamento.setUsuarioRegistro(SecurityContextHolder.getContext().getAuthentication().getName());
				departamento.setFechaRegistro(new Date());
				WebAuthenticationDetails wad  = (WebAuthenticationDetails) SecurityContextHolder.getContext().getAuthentication().getDetails();
				departamento.setIdEquipo(wad.getRemoteAddress());
			}
			//Si el idUnico no viene en blanco hay que editar el departamento
			else{
				//Recupera el departamento de la base de datos
				departamento = this.departamentosService.getDepartamento(idUnico);
			}
			departamento.setNombreDepartamento(nombreDepartamento);
			//Actualiza el departamento
			this.departamentosService.saveDepartamento(departamento);
			return createJsonResponse(departamento);
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
     * Controlador para deshabilitar un departamento.
     *
     * @param idUnico el identificador del departamento a deshabilitar
     * @param redirectAttributes Regresa nombre de departamento
     * @return a String
     */
    @RequestMapping("/desDepartamento/{idUnico}/")
    public String disableDepartamento(@PathVariable("idUnico") String idUnico, 
    		RedirectAttributes redirectAttributes) {
    	String redirecTo="404";
		Departamento departamento = this.departamentosService.getDepartamento(idUnico);
    	if(departamento!=null){
    		departamento.setPasivo('1');
    		this.departamentosService.saveDepartamento(departamento);
    		redirectAttributes.addFlashAttribute("departamentoDeshabilitado", true);
    		redirectAttributes.addFlashAttribute("nombreDepartamento", departamento.getNombreDepartamento());
    		redirecTo = "redirect:/super/departamentos/";
    	}
    	else{
    		redirecTo = "403";
    	}
    	return redirecTo;	
    }
    
    
    /**
     * Controlador para habilitar un departamento.
     *
     * @param idUnico el identificador del departamento a habilitar
     * @param redirectAttributes Regresa nombre de departamento
     * @return a String
     */
    @RequestMapping("/habDepartamento/{idUnico}/")
    public String enableUser(@PathVariable("idUnico") String idUnico, 
    		RedirectAttributes redirectAttributes) {
    	String redirecTo="404";
    	Departamento departamento = this.departamentosService.getDepartamento(idUnico);
    	if(departamento!=null){
    		departamento.setPasivo('0');
    		this.departamentosService.saveDepartamento(departamento);
    		redirectAttributes.addFlashAttribute("departamentoHabilitado", true);
    		redirectAttributes.addFlashAttribute("nombreDepartamento", departamento.getNombreDepartamento());
    		redirecTo = "redirect:/super/departamentos/";
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

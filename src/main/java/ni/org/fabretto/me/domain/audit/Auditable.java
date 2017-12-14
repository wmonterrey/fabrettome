package ni.org.fabretto.me.domain.audit;

/**
 * Auditable es la interface que representa si un objeto es parte de la auditor�a en el sistema
 * 
 *  
 * @author      William Avil�s
 * @version     1.0
 * @since       1.0
 */
public interface Auditable {
	/** @param fieldname Nombre del campo a evaluar si es parte de la auditor�a.
	 *  @return true si es parte de la auditor�a.*/
	public boolean isFieldAuditable(String fieldname);

}

package ni.org.fabretto.me.domain.audit;

/**
 * Auditable es la interface que representa si un objeto es parte de la auditoría en el sistema
 * 
 *  
 * @author      William Avilés
 * @version     1.0
 * @since       1.0
 */
public interface Auditable {
	/** @param fieldname Nombre del campo a evaluar si es parte de la auditoría.
	 *  @return true si es parte de la auditoría.*/
	public boolean isFieldAuditable(String fieldname);

}

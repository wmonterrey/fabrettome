package ni.org.fabretto.me.domain.audit;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "tblAuditoria", catalog = "fabrettome")
public class AuditTrail {
	
	private int id;
	private String entityId;
	private String entityClass;
	private String entityName;
	private String entityProperty;
	private String entityPropertyOldValue;
	private String entityPropertyNewValue;
	private String operationType;
	private String username;
	private Date operationDate;
	
	
	
	public AuditTrail() {
		super();
	}

	public AuditTrail(String entityId, String entityClass, String entityName,
			String entityProperty, String entityPropertyOldValue,
			String entityPropertyNewValue, String operationType,
			String username, Date operationDate) {
		super();
		this.entityId = entityId;
		this.entityClass = entityClass;
		this.entityName = entityName;
		this.entityProperty = entityProperty;
		this.entityPropertyOldValue = entityPropertyOldValue;
		this.entityPropertyNewValue = entityPropertyNewValue;
		this.operationType = operationType;
		this.username = username;
		this.operationDate = operationDate;
	}
	
	@Id
	@GenericGenerator(name="idautoinc3" , strategy="increment")
	@GeneratedValue(generator="idautoinc3")
	@Column(name="id")
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	@Column(name = "idEntidad", nullable = true, length =50)
	public String getEntityId() {
		return entityId;
	}
	public void setEntityId(String entityId) {
		this.entityId = entityId;
	}
	@Column(name = "claseEntidad", nullable = true, length =200)
	public String getEntityClass() {
		return entityClass;
	}

	public void setEntityClass(String entityClass) {
		this.entityClass = entityClass;
	}
	@Column(name = "nombreEntidad", nullable = true, length =200)
	public String getEntityName() {
		return entityName;
	}
	public void setEntityName(String entityName) {
		this.entityName = entityName;
	}
	@Column(name = "propiedad", nullable = true, length =150)
	public String getEntityProperty() {
		return entityProperty;
	}
	public void setEntityProperty(String entityProperty) {
		this.entityProperty = entityProperty;
	}
	@Column(name = "valorAnterior", nullable = true, length =1000)
	public String getEntityPropertyOldValue() {
		return entityPropertyOldValue;
	}
	public void setEntityPropertyOldValue(String entityPropertyOldValue) {
		this.entityPropertyOldValue = entityPropertyOldValue;
	}
	@Column(name = "valorActual", nullable = true, length =1000)
	public String getEntityPropertyNewValue() {
		return entityPropertyNewValue;
	}
	public void setEntityPropertyNewValue(String entityPropertyNewValue) {
		this.entityPropertyNewValue = entityPropertyNewValue;
	}
	@Column(name = "tipoOperacion", nullable = true, length =50)
	public String getOperationType() {
		return operationType;
	}
	public void setOperationType(String operationType) {
		this.operationType = operationType;
	}
	@Column(name = "nombreUsuario", nullable = true, length =50)
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	@Column(name = "fechaRegistro", nullable = true)
	public Date getOperationDate() {
		return operationDate;
	}
	public void setOperationDate(Date operationDate) {
		this.operationDate = operationDate;
	}

}

package libWeb.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;

/**
 * Clase persistente de audit de la tabla de la base de datos
 * @author Daniel Beltr�n Penagos
 * @author Santiago Correa Vera 
 * <br><br>
 * <center> <b> Universidad El Bosque<br>
 * Ingenier�a de Sistemas - Programaci�n 2<br>
 * Profesor Wilson Rojas Reales <br>
 * Proyecto libWeb</b> </center>
 */
@Entity
@Table(name = "audit")
public class Audit implements Serializable 
{
	/**
	 * Serial de la versi�n
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Id de las auditorias agregadas
	 */
	private int id;

	/**
	 * Fecha de la creaci�n de la auditor�a
	 */
	@Temporal(TemporalType.TIMESTAMP)
	private Date createDate;

	/**
	 * Operaci�n que se realiz� en la auditor�a 
	 */
	private String operation;

	/**
	 * Id de la tabla 
	 */
	private int tableId;

	/**
	 * Nombre de la tabla 
	 */
	private String tableName;

	/**
	 * Id del usuario 
	 */
	private int userId;

	/**
	 * Ip de la m�quina de donde se realiza la acci�n
	 */
	private String ip;

	/**
	 * Nombre del usuario que hizo alguna acci�n
	 */
	private String nameUser;

	/**
	 * Constructor de la Entidad
	 */
	public Audit() {
	}

	/**
	 * Retorna el Id del Usuario
	 * @return id del Usuario
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	public int getId() 
	{
		return this.id;
	}

	/**
	 * Cambia la Id del usuario
	 * <b> post: </b> se cambi� la id en la base de datos 
	 * @param id a cambiar
	 */
	public void setId(int id) 
	{
		this.id = id;
	}

	/**
	 * Da la fecha de la uditoria realizada
	 * @return fecha de la auditoria 
	 */
	@Column(name = "createDate")
	public Date getCreateDate() 
	{
		return this.createDate;
	}

	/**
	 * Cambia la fecha de la auditoria 
	 * <b> post: </b> se cambia la fecha en la base de datos
	 * @param createDate fecha a cambiar 
	 */
	public void setCreateDate(Date createDate) 
	{
		this.createDate = createDate;
	}

	/**
	 * Da la operaci�n que se realiz� en la auditor�a
	 * @return operaci�n realizada en la auditor�a 
	 */
	@Column(name = "operation")
	public String getOperation()
	{
		return this.operation;
	}

	/**
	 * Cambia la opreaci�n que se realiz� en la auditor�a 
	 * <b> post: </b> se cambi� la operaci�n en la base de datos 
	 * @param operation opreaci�n a cambiar
	 */
	public void setOperation(String operation)
	{
		this.operation = operation;
	}

	/**
	 * Obteniene el id de la auditor�a
	 * @return �ltimo registro de la tabla 
	 */
	@Column(name = "tableId")
	public int getTableId()
	{
		return this.tableId;
	}

	/**
	 * Cambia el id de la auditor�a
	 * <b> post: </b> se cambi� el id de la tabla en labase de datos 
	 * @param tableId id de la tabla a cambiar
	 */
	public void setTableId(int tableId)
	{
		this.tableId = tableId;
	}

	/**
	 * Nombre de la tabla que se realiz� la modificaci�n 
	 * @return nombre de la tabla modificada
	 */
	@Column(name = "tableName")
	public String getTableName()
	{
		return this.tableName;
	}

	/**
	 * Cambia el nombre de la tabla modificada
	 * <b> post: </b> se cambi� el nombre de la tabla en la base de datos 
	 * @param tableName nombre de la tabla a cambiar
	 */
	public void setTableName(String tableName) 
	{
		this.tableName = tableName;
	}

	/**
	 * Id del Usuario que realiza un cambio
	 * @return id del usuario 
	 */
	@Column(name = "userId")
	public int getUserId()
	{
		return this.userId;
	}

	/**
	 * Cambia el id del usuario que realiza un cambio en el programa
	 * <b> post: </b> se cambi� el id del usuario en la base de datos 
	 * @param userId id del usuario que se quiere cambiar
	 */
	public void setUserId(int userId)
	{
		this.userId = userId;
	}

	/**
	 * Obtiene la ip del Usuario
	 * @return ip del usuario 
	 */
	@Column(name ="ip")
	public String getIp()
	{
		return ip;
	}

	/**
	 * Cambia la ip del Usuario 
	 * <b> post: </b> se cambi� la ip del usuario en la base de datos 
	 * @param ip del usuario que se quire cambiar
	 */
	public void setIp(String ip)
	{
		this.ip = ip;
	}

	@Column(name = "nameUser")
	/**
	 * Obtiene el nombre de usuario 
	 * @return nombre del usuario 
	 */
	public String getNameUser()
	{
		return nameUser;
	}

	/**
	 * Cambia el nombre del Usuario 
	 * <b> post: </b> se cambi� el nombre de usuario en la base de datos
	 * @param nameUser que se quiere cambiar 
	 */
	public void setNameUser(String nameUser)
	{
		this.nameUser = nameUser;
	}
}
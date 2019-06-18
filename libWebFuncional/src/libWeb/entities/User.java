package libWeb.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * Clase persistente de user de la base de la base de datos
 * @author Daniel Beltr�n Penagos
 * @author Santiago Correa Vera 
 * <br><br>
 * <center> <b> Universidad El Bosque<br>
 * Ingenier�a de Sistemas - Programaci�n 2<br>
 * Profesor Wilson Rojas Reales <br>
 * Proyecto libWeb</b> </center>
 */
@Entity
@Table(name = "user")
public class User implements Serializable 
{
	/**
	 * Serial de la versi�n
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Id de la tabla 
	 */
	private int id;

	/**
	 * Activaci�n del usuario en la aplicaci�n 
	 */
	private String active;

	/**
	 * �ltima fecha de entrada al sistema
	 */
	@Temporal(TemporalType.TIMESTAMP)
	private Date dateLastPassword;


	/**
	 * Direcci�n del correo electr�nico
	 */
	private String emailAddress;

	/**
	 * Nombre completo del usuario
	 */
	private String fullName;

	/**
	 * Contrase�a del Usuario
	 */
	private String password;

	/**
	 * N�mero de tel�fono del usuario
	 */
	private String phoneNumber;

	/**
	 * Nombre de usuario
	 */
	private String userName;

	/**
	 * Tipo de usuario
	 */
	private String userType;

	/**
	 * Intentos del Usuario
	 */
	private int attempts;

	/**
	 * Direcci�n del Usuario
	 */
	private String address;

	/**
	 * Constructor de la entidad
	 */
	public User() 
	{
	}

	/**
	 * Id de la tabla 
	 * @return id de la tabla
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	public int getId() 
	{
		return this.id;
	}

	/**
	 * Cambia el Id de la tabla
	 * @param id de la tabla
	 */
	public void setId(int id) 
	{
		this.id = id;
	}

	/**
	 * Obtiene que en que estado est� el usuario (Activo/InActivo)
	 * @return
	 */
	@Column(name = "active")
	public String getActive()
	{
		return this.active;
	}

	/**
	 * Cambia el estado del usurio en la aplicaci�n 
	 * @param active estado a cambiar en la base de datos 
	 */
	public void setActive(String active) 
	{
		this.active = active;
	}

	/**
	 * Obtiene la �ltima fecha en la qu� el usuario ingres� a la aplicaci�n 
	 * @return �ltima fecha del ingreso a la aplicaci�n 
	 */
	@Column(name = "dateLastPassword")
	public Date getDateLastPassword() 
	{
		return this.dateLastPassword;
	}

	/**
	 * Cambia la �ltima fecha de entrada a la aplicaci�n del usuario
	 * @param dateLastPassword fecha a cambiar en la base de datos 
	 */
	public void setDateLastPassword(Date dateLastPassword)
	{
		this.dateLastPassword = dateLastPassword;
	}

	/**
	 * Obtiene la direcci�n del correo electr�nico 
	 * @return direcci�n del correo electr�nico 
	 */
	@Column(name = "emailAddress")
	public String getEmailAddress() 
	{
		return this.emailAddress;
	}

	/**
	 * Cambia la direcci�n del correo electr�nico
	 * @param emailAddress email a cambiar en la base de datos 
	 */
	public void setEmailAddress(String emailAddress) 
	{
		this.emailAddress = emailAddress;
	}

	/**
	 * Obtiene el nombre completo del usuario
	 * @return nombre completo del usuario
	 */
	@Column(name = "fullName")
	public String getFullName()
	{
		return this.fullName;
	}

	/**
	 * Cambia el nombre completo del Usuario
	 * @param fullName nombre a cambiar en la base de datos 
	 */
	public void setFullName(String fullName) 
	{
		this.fullName = fullName;
	}

	/**
	 * Obtiene la contrase�a del ususario 
	 * @return contrase�a del usuario 
	 */
	@Column(name = "password")
	public String getPassword() 
	{
		return this.password;
	}

	/**
	 * Cambia la contrase�a del usuario
	 *  <b> pre: </b> la contrase�a tiene que estar en MD5<br>
	 * @param password contrase�a a cambiar 
	 */
	public void setPassword(String password) 
	{
		this.password = password;
	}

	/**
	 * Obtiene el n�mero de tel�fono del usuario
	 * @return n�mero de tel�fono a cambiar en la base de datos 
	 */
	@Column(name = "phoneNumber")
	public String getPhoneNumber() 
	{
		return this.phoneNumber;
	}

	/**
	 * Cambia el tel�fono del usuario
	 * @param phoneNumber tel�fono a cambiar en la base de datos 
	 */
	public void setPhoneNumber(String phoneNumber)
	{
		this.phoneNumber = phoneNumber;
	}

	/**
	 * Obtiene el nombre de usuario
	 * @return nombre de usuario
	 */
	@Column(name = "userName")
	public String getUserName()
	{
		return this.userName;
	}

	/**
	 * Cambia el nombre de Usuario 
	 * @param userName nombre de usuario a cambiar en la base de datos 
	 */
	public void setUserName(String userName) 
	{
		this.userName = userName;
	}

	/**
	 * Obtiene el tipo de Usuario (NUEVO/ADMIN/NORMAL)
	 * @return tipo de usuario
	 */
	@Column(name = "userType")
	public String getUserType()
	{
		return this.userType;
	}

	/**
	 * <b> pre: </b> El tipo de usaurio s�lo puede tomar tres valores (NUEVO/ADMIN/NORMAL)
	 * Cambia el tipo de Usuario 
	 * @param userType tipo a cambiar en la base de datos 
	 */
	public void setUserType(String userType)
	{
		this.userType = userType;
	}

	/**
	 * Obtiene los intentos del usuario al momento de eqivocarse en la contrase�a
	 * @return intentos del usuario
	 */
	@Column(name = "attempts")
	public int getAttempts()
	{
		return this.attempts;
	}

	/**
	 * Cambia los intentos del usuario 
	 * @param attempts intentos a cambiar en la base de datos 
	 */
	public void setAttempts(int attempts)
	{
		this.attempts = attempts;
	}

	/**
	 * Obtiene la direcci�n del usuario
	 * @return direcci�n del usuario
	 */
	@Column(name = "address")
	public String getAddress()
	{
		return this.address;
	}


	/**
	 * Cambia la direcci�n del Usuario
	 * @param address direcci�n a cambiar en la base de datos 
	 */
	public void setAddress(String address)
	{
		this.address = address;
	}
}
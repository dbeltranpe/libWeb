package libWeb.beans;

import java.util.Date;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.context.FacesContext;

import org.apache.log4j.Logger;

import libWeb.DAO.implementation.AuditDAOImpl;
import libWeb.DAO.interfaces.AuditDAO;
import libWeb.entities.Audit;
import libWeb.util.Correo;

/**
 * Clase que representa al CorreoBean que permitir� enviar Correos
 * @author Daniel Beltr�n Penagos
 * @author Santiago Correa Vera 
 * <br><br>
 * <center> <b> Universidad El Bosque<br>
 * Ingenier�a de Sistemas - Programaci�n 2<br>
 * Profesor Wilson Rojas Reales <br>
 * Proyecto libWeb</b> </center>
 */
@ManagedBean
public class CorreoBean 
{

	/**
	 * Asunto del mensaje 
	 */
	private String subject;

	/**
	 * Cuerpo de texto del correo 
	 */
	private String bodyText;

	/**
	 * Logger para la muestra de los errores 
	 */
	private final static Logger logger = Logger.getLogger(CorreoBean.class);

	/**
	 * Conexi�n con el userBean 
	 */
	@ManagedProperty("#{userBean}")
	private UserBean userBean;

	/**
	 * Constructor del Bean
	 */
	public CorreoBean()
	{
	}

	/**
	 * Obtiene el userBean
	 * @return userBean Userban bean representativo del usuario
	 */
	public UserBean getUserBean()
	{
		return userBean;
	}

	/**
	 * Cambia el userBean
	 * @param bean userBean a cambiar 
	 */
	public void setUserBean(UserBean bean)
	{
		userBean = bean;
	}

	/**
	 * Obtiene el asunto del correo
	 * @return asunto del correo
	 */
	public String getSubject()
	{
		return subject;
	}

	/**
	 * Obtiene el cuerpo del correo 
	 * @return cuerpo del correo 
	 */
	public String getBodyText()
	{
		return bodyText;
	}

	/**
	 * Cambia el asunto del correo
	 * @param sub asunto a cambiar
	 */
	public void setSubject(String sub)
	{
		subject = sub;
	}

	/**
	 * Cambia el cuerpo del correo 
	 * @param body cuerpo del 
	 */
	public void setBodyText(String body)
	{
		bodyText = body;
	}

	/**
	 * M�todo que envia un correo al correo electr�nico del usuario escog�do
	 *  desde el administrador  
	 */
	public void enviarCorreo()
	{
		try
		{
			AuditDAO daoAudit = new AuditDAOImpl();

			Audit audit = new Audit();
			audit.setCreateDate(new Date());
			audit.setTableName("user");
			audit.setUserId(userBean.darUsuario().getId());
			audit.setTableId(userBean.darUsuario().getId());
			audit.setOperation("EnvCorreo");
			audit.setNameUser(userBean.darUsuario().getUserName());
			audit.setIp(userBean.darDireccionIp());
			daoAudit.save(audit);

			Correo.enviarCorreo(userBean.getEmailUser(), subject, bodyText);

			FacesContext.getCurrentInstance().getExternalContext().redirect("./gestionAdmon.xhtml");
		}
		catch (Exception e) 
		{
			logger.error("El getEmailUser es Nulo");
		}
	}

	/**
	 * M�todo que envia un correo al correo electr�nico del usuario escog�do
	 *  desde el usuario  
	 */
	public void enviarCorreo2()
	{
		try
		{
			AuditDAO daoAudit = new AuditDAOImpl();

			Audit audit = new Audit();
			audit.setCreateDate(new Date());
			audit.setTableName("user");
			audit.setUserId(userBean.darUsuario().getId());
			audit.setTableId(userBean.darUsuario().getId());
			audit.setOperation("EnvCorreo");
			audit.setIp(userBean.darDireccionIp());
			daoAudit.save(audit);

			Correo.enviarCorreo(userBean.getEmailUser(), subject, bodyText);

			FacesContext.getCurrentInstance().getExternalContext().redirect("./librosMundiales.xhtml");
		}
		catch (Exception e) 
		{
			logger.error("El getEmailUser es Nulo");
		}
	}


}
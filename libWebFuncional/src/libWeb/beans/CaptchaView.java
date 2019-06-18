package libWeb.beans;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

/**
 * Clase que representa el Bean del Captcha
 * @author Daniel Beltr�n Penagos
 * @author Santiago Correa Vera 
 * <br><br>
 * <center> <b> Universidad El Bosque<br>
 * Ingenier�a de Sistemas - Programaci�n 2<br>
 * Profesor Wilson Rojas Reales <br>
 * Proyecto libWeb</b> </center>
 */
@ManagedBean
public class CaptchaView 
{
	/**
	 *Env�o y validaci�n del captcha
	 */
	public void submit() 
	{
		FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Correct", "Correct");
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}
}
package libWeb.DAO.interfaces;
import java.util.List;
import libWeb.entities.Parameter;

/**
 * Interface que representa el Data Access Object (DAO) del par�metro
 * @author Daniel Beltr�n Penagos
 * @author Santiago Correa Vera 
 * <br><br>
 * <center> <b> Universidad El Bosque<br>
 * Ingenier�a de Sistemas - Programaci�n 2<br>
 * Profesor Wilson Rojas Reales <br>
 * Proyecto libWeb</b> </center>
 */
public interface ParameterDAO 
{
	/**
	 * Guarda un par�metro en la base de datos<br>
	 * <b> post:</b> Se guard� el par�metro en la base de datos<br>
	 * @param parameter Parameter a guardar
	 */
	public void save(Parameter parameter);

	/**
	 * Obtiene un par�metro en referencia al pasado por par�metro
	 * @param parameter Parameter referencia a buscar
	 * @return Parameter encontrado con las caracter�sticas del buscado
	 */
	public Parameter getParameter(Parameter parameter);

	/**
	 * Elimina un par�metro de la base de datos en referencia al pasado 
	 *  por parametro<br>
	 * <b> post:</b> Se elimin� el Parameter de la base de datos<br>
	 * @param parameter Parameter referencia a eliminar
	 */
	public void remove(Parameter parameter);

	/**
	 * Modifica un par�metro de la base de datos en referencia al pasado 
	 *  por parametro<br>
	 * <b> post:</b> Se modific� el Parameter de la base de datos<br>
	 * @param parameter Parameter referencia a actualizar
	 */
	public void update(Parameter parameter);

	/**
	 * Lista los par�metros de la base de datos<br>
	 * @return  List<Parameter> con la lista de los par�metros
	 */
	public List<Parameter> list();
}

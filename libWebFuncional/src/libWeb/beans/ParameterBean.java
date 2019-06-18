package libWeb.beans;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;

import libWeb.DAO.implementation.ParameterDAOimpl;
import libWeb.DAO.interfaces.ParameterDAO;
import libWeb.entities.Parameter;

/**
 * Clase que representa al parameterBean
 * @author Daniel Beltr�n Penagos
 * @author Santiago Correa Vera 
 * <br><br>
 * <center> <b> Universidad El Bosque<br>
 * Ingenier�a de Sistemas - Programaci�n 2<br>
 * Profesor Wilson Rojas Reales <br>
 * Proyecto libWeb</b> </center>
 */
@ManagedBean
@SessionScoped
public class ParameterBean
{
	/**
	 * Parametro para adicionar 
	 */
	private Parameter parametro;

	/**
	 * Lista de los par�metros
	 */
	private DataModel<Parameter> listaParametros;

	/**
	 * Constructor del Bean
	 */
	public ParameterBean()
	{
		parametro = new Parameter();
	}

	/**
	 * M�todo que inicializa la el atributo par�metro para adicionar uno
	 * @return p�gina para adicionar
	 */
	public String prepararAdicionarParametro() 
	{
		parametro = new Parameter();
		return "";
	}

	/**
	 * M�todo que prepara el atributo para la modificaci�n
	 * @return p�gina para la modificaci�n 
	 */
	public String prepararModificarParametro() 
	{
		parametro = (Parameter) (listaParametros.getRowData());
		return "";
	}

	/**
	 * M�todo que prepara el atributo para la eliminaci�n
	 * @return p�gina para eliminar
	 */
	public String eliminarParametro()
	{
		Parameter ParametroTemp = (Parameter) (listaParametros.getRowData());
		ParameterDAO dao = new ParameterDAOimpl();
		dao.update(ParametroTemp);
		return "";
	}

	/**
	 * M�todo que adiciona un nuevo par�metro
	 * @return p�gina inicial
	 */
	public String adicionarParametro() 
	{
		ParameterDAO dao = new ParameterDAOimpl();
		dao.save(parametro);
		return "";
	}

	/**
	 * M�todo que modifica el atributo de par�metro 
	 * @return p�gina inicial
	 */
	public String modificarParametro() 
	{
		ParameterDAO dao = new ParameterDAOimpl();
		dao.update(parametro);
		return "";
	}

	/**
	 * Obtiene el par�metro 
	 * @return par�metro
	 */
	public Parameter getParametro() 
	{
		return parametro;
	}

	/**
	 * Cambia los valores de los par�metros
	 * @param pParametro par�metro a cambiar 
	 */
	public void setParametro(Parameter pParametro) 
	{
		parametro = pParametro;
	}

	/**
	 * Genera la lista de los par�metros creados 
	 * @return p�gina para la visualizaci�n de los par�metros
	 */
	public DataModel<Parameter> getListarParametros() 
	{
		List<Parameter> lista = new ParameterDAOimpl().list();
		listaParametros = new ListDataModel<>(lista);
		return listaParametros;
	}

}

package libWeb.entities;

import java.io.Serializable;
import javax.persistence.*;

/**
 * Clase presistente de la tabla parameter de la base de datos
 * @author Daniel Beltr�n Penagos
 * @author Santiago Correa Vera 
 * <br><br>
 * <center> <b> Universidad El Bosque<br>
 * Ingenier�a de Sistemas - Programaci�n 2<br>
 * Profesor Wilson Rojas Reales <br>
 * Proyecto libWeb</b> </center>
 */
@Entity
@Table(name = "parameter")
public class Parameter implements Serializable
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
	 * 	Tipo de par�metro
	 */
	private String parameterType;

	/**
	 * Codigo del par�metro
	 */
	private String parameterCode;

	/**
	 * Descripci�n del par�metro
	 */
	private String descriptionParameter;

	/**
	 * Valor del texto
	 */
	private String textValue;

	/**
	 * N�mero de la variable
	 */
	private int numberValue;

	/**
	 * Constructor de la entidad
	 */
	public Parameter() {}

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
	 * Cambia el id de la tabla 
	 * @param id a cambiar en la tabla
	 */
	public void setId(int id)
	{
		this.id = id;
	}

	/**
	 * Tipo de par�metro hecho
	 * @return tipo de par�metro
	 */
	@Column(name = "parameterType")
	public String getParameterType()
	{
		return parameterType;
	}

	/**
	 * Cambia el tipo de par�metro de la tabla
	 * @param parameterType tipo de parametro a cambiar en la base de datos 
	 */
	public void setParameterType(String parameterType)
	{
		this.parameterType = parameterType;
	}

	/**
	 * C�digo del par�metro de la tabla
	 * @return c�digo del par�metro 
	 */
	@Column(name = "parameterCode")
	public String getParameterCode()
	{
		return parameterCode;
	}

	/**
	 * Cambia el c�digo del par�metro en la base de datos 
	 * @param parameterCode par�metro a cambiar 
	 */
	public void setParameterCode(String parameterCode)
	{
		this.parameterCode = parameterCode;
	}

	/**
	 * Retorna la Descripci�n del par�metro 
	 * @return descripci�n del par�metro
	 */
	@Column(name = "descriptionParameter")
	public String getDescriptionParameter()
	{
		return descriptionParameter;
	}

	/**
	 * Cambia la descripci�n del par�metro 
	 * @param descriptionParameter descripci�n a cambiar en la base de datos 
	 */
	public void setDescriptionParameter(String descriptionParameter)
	{
		this.descriptionParameter = descriptionParameter;
	}

	/**
	 * Valor de texto del par�metro
	 * @return valor del texto
	 */
	@Column(name = "textValue")
	public String getTextValue()
	{
		return textValue;
	}

	/**
	 * Cambia el valor del texto 
	 * @param textValue valor a cambiar en la base de datos 
	 */
	public void setTextValue(String textValue) 
	{
		this.textValue = textValue;
	}

	/**
	 * Valor del par�metro 
	 * @return valor del par�metro
	 */
	@Column(name = "numberValue")
	public int getNumberValue()
	{
		return numberValue;
	}

	/**
	 * Cambia el valor del par�metro 
	 * @param numberValue valor a cambiar en la base de datos 
	 */
	public void setNumberValue(int numberValue)
	{
		this.numberValue = numberValue;
	}
}
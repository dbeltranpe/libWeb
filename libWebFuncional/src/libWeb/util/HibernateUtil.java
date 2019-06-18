package libWeb.util;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

/**
 * Clase que permite la conexi�n con la base de datos
 * @author Daniel Beltr�n Penagos
 * @author Santiago Correa Vera 
 * <br><br>
 * <center> <b> Universidad El Bosque<br>
 * Ingenier�a de Sistemas - Programaci�n 2<br>
 * Profesor Wilson Rojas Reales <br>
 * Proyecto libWeb</b> </center>
 */
public class HibernateUtil 
{

	/**
	 * Sesi�n para la conexi�n con la base de datos 
	 */
	private static SessionFactory sessionFactory;

	/**
	 * Realiza la configuraci�n para la conexi�n con la base de datos 
	 */
	static 
	{
		Configuration configuration = new Configuration().configure();
		StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties());
		sessionFactory = configuration.buildSessionFactory(builder.build());
	}

	/**
	 * Retorna la sesi�n creada y configurada 
	 * @return la sesi�n 
	 */
	public static SessionFactory getSessionFactory()
	{
		return sessionFactory;
	}

}
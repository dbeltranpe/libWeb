package libWeb.DAO.implementation;
import libWeb.entities.Audit;
import libWeb.DAO.interfaces.AuditDAO;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.Transaction;
import libWeb.util.HibernateUtil;

/**
 * Clase que representa el Data Access Object (DAO) de las auditorias
 * @author Daniel Beltr�n Penagos
 * @author Santiago Correa Vera 
 * <br><br>
 * <center> <b> Universidad El Bosque<br>
 * Ingenier�a de Sistemas - Programaci�n 2<br>
 * Profesor Wilson Rojas Reales <br>
 * Proyecto libWeb</b> </center>
 */
public class AuditDAOImpl implements AuditDAO 
{
	@Override
	public void save(Audit auditoria) 
	{
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction t = session.beginTransaction();
		session.save(auditoria);
		t.commit();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Audit> list() 
	{
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction t = session.beginTransaction();
		@SuppressWarnings("rawtypes")
		List lista = session.createQuery("from Audit").list();
		t.commit();
		return lista;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Audit> listUser(int pUserId)
	{
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction t = session.beginTransaction();
		@SuppressWarnings("rawtypes")
		List lista = session.createQuery("from Audit where userId =" + pUserId).list();
		t.commit();
		return lista;
	}
	
	

}
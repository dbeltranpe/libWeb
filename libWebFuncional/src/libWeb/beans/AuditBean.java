package libWeb.beans;

import java.util.Date;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;

import org.apache.log4j.Logger;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.awt.Desktop;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

import libWeb.DAO.implementation.AuditDAOImpl;
import libWeb.DAO.implementation.UserDAOimpl;
import libWeb.DAO.interfaces.AuditDAO;
import libWeb.entities.Audit;
import libWeb.entities.User;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

/**
 * Clase que representa el Bean de Auditor�a (AuditBean)
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
public class AuditBean 
{

	/**
	 * Auditor�a
	 */
	private Audit audit;

	/**
	 * Logger del bean (log4j)
	 */
	private final static Logger logger = Logger.getLogger(AuditBean.class);

	/**
	 * Datamodel de auditorias con todas las auditorias de los usuarios
	 */
	private DataModel<Audit> listaAuditoria;

	/**
	 * Datamodel de auditorias con todas las auditorias de un usuario espec�fico
	 */
	private DataModel<Audit> listaTrazoUsuario;

	/**
	 * Adiciona una auditor�a a la base de datos<br>
	 * <b> post:</b> Se agreg� una auditor�a a la base de datos<br>
	 * @param auditoria Audit auditor�a a agregar
	 * @return String "listAudit"
	 */
	public String adicionarAuditoria(Audit auditoria) 
	{
		AuditDAO dao = new AuditDAOImpl();
		dao.save(auditoria);
		return "listAudit";
	}

	/**
	 * Genera un Datamodel con las auditor�as de los usuarios
	 * @return DataModel<Audit> Audit con las auditor�as de los usuarios
	 */
	public DataModel<Audit> getListarAuditoria() 
	{
		List<Audit> lista = new AuditDAOImpl().list();
		listaAuditoria = new ListDataModel<>(lista);
		return listaAuditoria;
	}

	/**
	 * Prepara la auditor�a para dirigirse �nicamente a la trazabilidad 
	 *  de un usuario<br>
	 * <b> pre:</b> listaAuditoria != null
	 */
	public void prepararTrazabilidadUsuario()
	{
		try
		{
			audit = (Audit) (listaAuditoria.getRowData());
			FacesContext.getCurrentInstance().getExternalContext().redirect("./trazabilidadUsuario.xhtml");
		}
		catch (Exception e) 
		{
			logger.warn("error de direccionamiento " + e.getMessage());
		}
	}

	/**
	 * Retorna la lista de auditor�as de un usuario<br>
	 * @return DataModel<Audit> con la lista de auditor�as de un usuario
	 */
	public DataModel<Audit> getListaTrazoUsuario() 
	{
		List<Audit> lista = new AuditDAOImpl().listUser(audit.getUserId());
		listaTrazoUsuario = new ListDataModel<>(lista);
		return listaTrazoUsuario;
	}

	/**
	 * Genera un excel con todas las auditor�as de los usuarios<br>
	 * <b> post:</b> En la carpeta descargas se encuentra el excel generado
	 * @return Cadena de texto vac�a
	 */
	public String excelAuditorias()
	{
		XSSFWorkbook workbook = new XSSFWorkbook();
		List<Audit> list = new AuditDAOImpl().list();

		Sheet hoja = workbook.createSheet("auditoria");

		int noFila = 0;

		for (int i = 0; i < list.size(); i++)
		{
			Audit actual = list.get(i);
			Row fila = hoja.createRow(noFila++);
			int noCelda = 0;

			if(i==0)
			{
				fila.createCell(noCelda++).setCellValue("ID");
				fila.createCell(noCelda++).setCellValue("USER ID");
				fila.createCell(noCelda++).setCellValue("OPERATION");
				fila.createCell(noCelda++).setCellValue("TABLE NAME");
				fila.createCell(noCelda++).setCellValue("TABLE ID");
				fila.createCell(noCelda++).setCellValue("IP");
				fila.createCell(noCelda++).setCellValue("CREATE DATE");

				fila = hoja.createRow(noFila++);
				noCelda = 0;
				fila.createCell(noCelda++).setCellValue(actual.getId());
				fila.createCell(noCelda++).setCellValue(actual.getUserId());
				fila.createCell(noCelda++).setCellValue(actual.getOperation());
				fila.createCell(noCelda++).setCellValue(actual.getTableName());
				fila.createCell(noCelda++).setCellValue(actual.getTableId());
				fila.createCell(noCelda++).setCellValue(actual.getIp());

				Date date = actual.getCreateDate();
				DateFormat df = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");  
				String reportDate = df.format(date);

				fila.createCell(noCelda++).setCellValue(reportDate);
			}

			else
			{
				fila.createCell(noCelda++).setCellValue(actual.getId());
				fila.createCell(noCelda++).setCellValue(actual.getUserId());
				fila.createCell(noCelda++).setCellValue(actual.getOperation());
				fila.createCell(noCelda++).setCellValue(actual.getTableName());
				fila.createCell(noCelda++).setCellValue(actual.getTableId());
				fila.createCell(noCelda++).setCellValue(actual.getIp());

				Date date = actual.getCreateDate();
				DateFormat df = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");  
				String reportDate = df.format(date);

				fila.createCell(noCelda++).setCellValue(reportDate);
			}
		}

		try
		{
			String home = System.getProperty("user.home");
			File file = new File(home + "/Downloads/auditoria.xlsx"); 
			FileOutputStream fos = new FileOutputStream(file);
			workbook.write(fos);
			
			try {
				Desktop.getDesktop().open(file);
			} catch (IOException e) {
				logger.error(e.getMessage());
			}
			
			fos.close();
		}
		catch(FileNotFoundException e)
		{
			logger.error("No se encontr� el directorio destino", e);	
		}
		catch(IOException e)
		{
			logger.error(e.getMessage());
		}

		return "";
	}

	/**
	 * Genera un pdf con todas las auditor�as de los usuarios<br>
	 * <b> post:</b> En la carpeta descargas se encuentra el pdf generado
	 * @return Cadena de texto vac�a
	 */
	public String pdfAuditorias()
	{
		List<Audit> list = new AuditDAOImpl().list();
		Document document = new Document();
		PdfPTable table = new PdfPTable(new float[] { 2, 2, 2, 2, 2 , 2, 2 });


		table.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);

		table.addCell("ID");
		table.addCell("ID USUARIO");
		table.addCell("OPERACI�N");
		table.addCell("NOMBRE TABLA");
		table.addCell("ID TABLE");
		table.addCell("IP");
		table.addCell("FECHA");

		table.setHeaderRows(1);

		PdfPCell[] cells = table.getRow(0).getCells();

		for (int j=0; j < cells.length; j++)
		{
			cells[j].setBackgroundColor(BaseColor.GRAY);
		}

		for (int i=0; i < list.size(); i++)
		{
			Audit actual = list.get(i);

			table.addCell(actual.getId()+"");
			table.addCell(actual.getUserId()+"");
			table.addCell(actual.getOperation()+"");
			table.addCell(actual.getTableName());
			table.addCell(actual.getTableId()+"");
			table.addCell(actual.getIp()+"");

			Date date = actual.getCreateDate();
			DateFormat df = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");  
			String reportDate = df.format(date);

			table.addCell(reportDate);
		}

		try
		{
			String home = System.getProperty("user.home");
			File file = new File(home + "/Downloads/auditoria.pdf"); 
			PdfWriter.getInstance(document, new FileOutputStream(file));

			try {
				Desktop.getDesktop().open(file);
			} catch (IOException e) {
				logger.error(e.getMessage());
			}

		} 
		catch (FileNotFoundException e)
		{
			logger.error(e.getMessage());
		} 
		catch (DocumentException e)
		{
			logger.error(e.getMessage());
		}

		document.open();

		try
		{
			document.add(table);
		} 
		catch (DocumentException e)
		{
			logger.error(e.getMessage());
		}
		document.close();

		return "";
	}

	/**
	 * Genera un excel con las auditor�as del usuario seleccionado<br>
	 * <b> post:</b> En la carpeta descargas se encuentra el excel generado
	 * @return Cadena de texto vac�a
	 */
	public String excelAuditoriasUsuarioUnico()
	{
		XSSFWorkbook workbook = new XSSFWorkbook();
		List<Audit> list = new AuditDAOImpl().listUser(audit.getUserId());

		Sheet hoja = workbook.createSheet("auditoria_usuario_" + audit.getUserId());

		int noFila = 0;

		for (int i = 0; i < list.size(); i++)
		{
			Audit actual = list.get(i);
			Row fila = hoja.createRow(noFila++);
			int noCelda = 0;

			if(i==0)
			{
				fila.createCell(noCelda++).setCellValue("ID");
				fila.createCell(noCelda++).setCellValue("USER ID");
				fila.createCell(noCelda++).setCellValue("OPERATION");
				fila.createCell(noCelda++).setCellValue("TABLE NAME");
				fila.createCell(noCelda++).setCellValue("TABLE ID");
				fila.createCell(noCelda++).setCellValue("IP");
				fila.createCell(noCelda++).setCellValue("CREATE DATE");

				fila = hoja.createRow(noFila++);
				noCelda = 0;
				fila.createCell(noCelda++).setCellValue(actual.getId());
				fila.createCell(noCelda++).setCellValue(actual.getUserId());
				fila.createCell(noCelda++).setCellValue(actual.getOperation());
				fila.createCell(noCelda++).setCellValue(actual.getTableName());
				fila.createCell(noCelda++).setCellValue(actual.getTableId());
				fila.createCell(noCelda++).setCellValue(actual.getIp());

				Date date = actual.getCreateDate();
				DateFormat df = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");  
				String reportDate = df.format(date);

				fila.createCell(noCelda++).setCellValue(reportDate);
			}

			else
			{
				fila.createCell(noCelda++).setCellValue(actual.getId());
				fila.createCell(noCelda++).setCellValue(actual.getUserId());
				fila.createCell(noCelda++).setCellValue(actual.getOperation());
				fila.createCell(noCelda++).setCellValue(actual.getTableName());
				fila.createCell(noCelda++).setCellValue(actual.getTableId());
				fila.createCell(noCelda++).setCellValue(actual.getIp());

				Date date = actual.getCreateDate();
				DateFormat df = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");  
				String reportDate = df.format(date);

				fila.createCell(noCelda++).setCellValue(reportDate);
			}
		}

		try
		{
			String home = System.getProperty("user.home");
			File file = new File(home + "/Downloads/auditoria_usuario_"+ audit.getUserId() +".xlsx"); 
			FileOutputStream fos = new FileOutputStream(file);
			workbook.write(fos);
			
			try {
				Desktop.getDesktop().open(file);
			} catch (IOException e) {
				logger.error(e.getMessage());
			}
			
			fos.close();
		}
		catch(FileNotFoundException e)
		{
			logger.error("No se encontr� el directorio destino", e);	
		}
		catch(IOException e)
		{
			logger.error(e.getMessage());
		}

		return "";
	}

	/**
	 * Genera un pdf con las auditor�as del usuario seleccionado<br>
	 * <b> post:</b> En la carpeta descargas se encuentra el pdf generado
	 * @return Cadena de texto vac�a
	 */
	public String pdfAuditoriasUsuarioUnico()
	{
		List<Audit> list = new AuditDAOImpl().listUser(audit.getUserId());
		Document document = new Document();
		PdfPTable table = new PdfPTable(new float[] { 2, 2, 2, 2, 2 ,2, 2});


		table.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);

		table.addCell("ID");
		table.addCell("USER ID");
		table.addCell("OPERATION");
		table.addCell("TABLE NAME");
		table.addCell("TABLE ID");
		table.addCell("IP");
		table.addCell("CREATE DATE");

		table.setHeaderRows(1);

		PdfPCell[] cells = table.getRow(0).getCells();

		for (int j=0; j < cells.length; j++)
		{
			cells[j].setBackgroundColor(BaseColor.GRAY);
		}

		for (int i=0; i < list.size(); i++)
		{
			Audit actual = list.get(i);

			table.addCell(actual.getId()+"");
			table.addCell(actual.getUserId()+"");
			table.addCell(actual.getOperation()+"");
			table.addCell(actual.getTableName());
			table.addCell(actual.getTableId()+"");
			table.addCell(actual.getIp()+"");

			Date date = actual.getCreateDate();
			DateFormat df = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");  
			String reportDate = df.format(date);

			table.addCell(reportDate);
		}

		try
		{
			String home = System.getProperty("user.home");
			File file = new File(home + "/Downloads/auditoria_usuario_"+ audit.getUserId()+".pdf"); 
			PdfWriter.getInstance(document, new FileOutputStream(file));
			
			try {
				Desktop.getDesktop().open(file);
			} catch (IOException e) {
				logger.error(e.getMessage());
			}
		} 
		catch (FileNotFoundException e)
		{
			logger.error(e.getMessage());
		} 
		catch (DocumentException e)
		{
			logger.error(e.getMessage());
		}

		document.open();

		try
		{
			document.add(table);
		} 
		catch (DocumentException e)
		{
			logger.error(e.getMessage());
		}
		document.close();

		return "";
	}

	/**
	 * Genera un excel con la informaci�n de todos los usuarios del sistema<br>
	 * <b> post:</b> En la carpeta descargas se encuentra el excel generado
	 * @return Cadena de texto vac�a
	 */
	public String excelGestionUsuarios()
	{
		XSSFWorkbook workbook = new XSSFWorkbook();
		List<User> list = new UserDAOimpl().list();

		Sheet hoja = workbook.createSheet("gestion");

		int noFila = 0;

		for (int i = 0; i < list.size(); i++)
		{
			User actual = list.get(i);
			Row fila = hoja.createRow(noFila++);
			int noCelda = 0;

			if(i==0)
			{
				fila.createCell(noCelda++).setCellValue("ID");
				fila.createCell(noCelda++).setCellValue("NICKNAME");
				fila.createCell(noCelda++).setCellValue("NOMBRE COMPLETO");
				fila.createCell(noCelda++).setCellValue("EMAIL");
				fila.createCell(noCelda++).setCellValue("N�MERO CELULAR");
				fila.createCell(noCelda++).setCellValue("�LTIMA FECHA CONTRASE�A");
				fila.createCell(noCelda++).setCellValue("ESTADO");
				fila.createCell(noCelda++).setCellValue("TIPO DE USUARIO");
				fila.createCell(noCelda++).setCellValue("INTENTOS");
				fila.createCell(noCelda++).setCellValue("DIRECCI�N");

				fila = hoja.createRow(noFila++);
				noCelda = 0;
				fila.createCell(noCelda++).setCellValue(actual.getId());
				fila.createCell(noCelda++).setCellValue(actual.getUserName());
				fila.createCell(noCelda++).setCellValue(actual.getFullName());
				fila.createCell(noCelda++).setCellValue(actual.getEmailAddress());
				fila.createCell(noCelda++).setCellValue(actual.getPhoneNumber());

				Date date = actual.getDateLastPassword();
				DateFormat df = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");  
				String reportDate = df.format(date);

				fila.createCell(noCelda++).setCellValue(reportDate);
				fila.createCell(noCelda++).setCellValue(actual.getActive());
				fila.createCell(noCelda++).setCellValue(actual.getUserType());
				fila.createCell(noCelda++).setCellValue(actual.getAttempts());
				fila.createCell(noCelda++).setCellValue(actual.getAddress());
			}

			else
			{
				fila.createCell(noCelda++).setCellValue(actual.getId());
				fila.createCell(noCelda++).setCellValue(actual.getUserName());
				fila.createCell(noCelda++).setCellValue(actual.getFullName());
				fila.createCell(noCelda++).setCellValue(actual.getEmailAddress());
				fila.createCell(noCelda++).setCellValue(actual.getPhoneNumber());

				Date date = actual.getDateLastPassword();
				DateFormat df = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");  
				String reportDate = df.format(date);

				fila.createCell(noCelda++).setCellValue(reportDate);
				fila.createCell(noCelda++).setCellValue(actual.getActive());
				fila.createCell(noCelda++).setCellValue(actual.getUserType());
				fila.createCell(noCelda++).setCellValue(actual.getAttempts());
				fila.createCell(noCelda++).setCellValue(actual.getAddress());
			}
		}

		try
		{
			String home = System.getProperty("user.home");
			File file = new File(home + "/Downloads/gestion.xlsx"); 
			FileOutputStream fos = new FileOutputStream(file);
			workbook.write(fos);
			
			try {
				Desktop.getDesktop().open(file);
			} catch (IOException e) {
				logger.error(e.getMessage());
			}
			fos.close();
		}
		catch(FileNotFoundException e)
		{
			logger.error("No se encontr� el directorio destino", e);	
		}
		catch(IOException e)
		{
			logger.error(e.getMessage());
		}

		return "";
	}

	/**
	 * Genera un pdf con la informaci�n de todos los usuarios del sistema<br>
	 * <b> post:</b> En la carpeta descargas se encuentra el excel generado
	 * @return Cadena de texto vac�a
	 */
	public String pdfGestionUsuarios()
	{
		List<User> list = new UserDAOimpl().list();
		Document document = new Document();
		PdfPTable table = new PdfPTable(new float[] { 2, 2, 2, 2, 2 ,2 ,2, 2, 2, 2});


		table.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);

		table.addCell("ID");
		table.addCell("NICKNAME");
		table.addCell("NOMBRE COMPLETO");
		table.addCell("EMAIL");
		table.addCell("N�MERO CELULAR");
		table.addCell("�LTIMA FECHA CONTRASE�A");
		table.addCell("ESTADO");
		table.addCell("TIPO DE USUARIO");
		table.addCell("INTENTOS");
		table.addCell("DIRECCI�N");

		table.setHeaderRows(1);

		PdfPCell[] cells = table.getRow(0).getCells();

		for (int j=0; j < cells.length; j++)
		{
			cells[j].setBackgroundColor(BaseColor.GRAY);
		}

		for (int i=0; i < list.size(); i++)
		{
			User actual = list.get(i);

			table.addCell(actual.getId()+"");
			table.addCell(actual.getUserName() +"");
			table.addCell(actual.getFullName() +"");
			table.addCell(actual.getEmailAddress()+"");
			table.addCell(actual.getPhoneNumber());

			Date date = actual.getDateLastPassword();
			DateFormat df = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");  
			String reportDate = df.format(date);

			table.addCell(reportDate);

			table.addCell(actual.getActive()+"");
			table.addCell(actual.getUserType()+"");
			table.addCell(actual.getAttempts()+"");
			table.addCell(actual.getAddress()+"");
		}

		try
		{
			String home = System.getProperty("user.home");
			File file = new File(home + "/Downloads/gestion.pdf"); 
			PdfWriter.getInstance(document, new FileOutputStream(file));
			
			try {
				Desktop.getDesktop().open(file);
			} catch (IOException e) {
				logger.error(e.getMessage());
			}
		} 
		catch (FileNotFoundException e)
		{
			logger.error(e.getMessage());
		} 
		catch (DocumentException e)
		{
			logger.error(e.getMessage());
		}

		document.open();

		try
		{
			document.add(table);
		} 
		catch (DocumentException e)
		{
			logger.error(e.getMessage());
		}
		document.close();

		return "";
	}




}
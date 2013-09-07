package pe.gob.inei.admin.aplicacion;

import java.io.File;
import java.util.Properties;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServlet;

import org.apache.log4j.PropertyConfigurator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CargaConfiguracion extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private static final String PRODUCCION = "produccion";

	public void init() {
		ServletContext context = getServletContext();
		String carpetaActual = getServletContext().getRealPath("/");
		final String estado = context.getInitParameter("estado");
		String nivelLog = context.getInitParameter("nivelLog");
		File carpetaLog = new File(carpetaActual, "log-series");
		File archivoLog = new File(carpetaLog, "log.log");
		File carpetaMapa = new File(carpetaActual, "js/json");
		Properties properties = new Properties ();
		if (PRODUCCION.equals(estado)) {
			properties.put("log4j.rootLogger", nivelLog + ", archivo");
		} else {
			properties.put("log4j.rootLogger", nivelLog + ", consola, archivo");
			properties.put("log4j.appender.consola", "org.apache.log4j.ConsoleAppender");
			properties.put("log4j.appender.consola.layout", "org.apache.log4j.PatternLayout");
			properties.put("log4j.appender.consola.layout.ConversionPattern", "[%t] (%F:%L) %-5p %c - %m%n");
			properties.put("log4j.logger.org.hibernate", "ERROR");
		}
		properties.put("log4j.appender.archivo", "org.apache.log4j.RollingFileAppender");
		properties.put("log4j.appender.archivo.file", archivoLog.getAbsolutePath());
		properties.put("log4j.appender.archivo.MaxFileSize", "5000KB");
		properties.put("log4j.appender.archivo.MaxBackupIndex", "5");
		properties.put("log4j.appender.archivo.layout", "org.apache.log4j.PatternLayout");
		properties.put("log4j.appender.archivo.layout.ConversionPattern", "%d [%t] %-5p %c - %m%n");
		PropertyConfigurator.configure(properties);
		
		final Logger log = LoggerFactory.getLogger(getClass());
		
		log.info("Archivo log: {}", archivoLog.getAbsolutePath());
	}

}

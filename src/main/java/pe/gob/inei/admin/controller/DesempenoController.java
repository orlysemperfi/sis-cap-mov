package pe.gob.inei.admin.controller;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;

import org.joda.time.DateTime;
import org.primefaces.model.chart.CartesianChartModel;
import org.primefaces.model.chart.LineChartSeries;

import pe.gob.inei.admin.dao.CedulaDAO;
import pe.gob.inei.admin.dao.DAOFactory;
import pe.gob.inei.admin.dao.PersonalDAO;
import pe.gob.inei.admin.dao.RutaDAO;
import pe.gob.inei.admin.dao.RutaPersonalDAO;
import pe.gob.inei.admin.dao.UbigeoDAO;
import pe.gob.inei.admin.util.Constantes;
import pe.gob.inei.sistencuesta.Personal;
import pe.gob.inei.sistencuesta.Ruta;
import pe.gob.inei.sistencuesta.RutaPersonal;
import pe.gob.inei.sistencuesta.Ubigeo;
import pe.gob.inei.sistencuesta.vista.DatoGrafico;

@ManagedBean(name="desempeno")
@ViewScoped
public class DesempenoController implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -3524289064510509689L;
	private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("dd/MM/yyyy");
	
	private String dni;
	private List<RutaPersonal> rutaPersonal;
	private String ruta;
	private String codDep;
	private String codProv;
	private String codDist;
	private List<Ubigeo> departamentos;
	private List<Ubigeo> provincias;
	private List<Ubigeo> distritos;

	private CartesianChartModel categoryModel;  
  
    private CartesianChartModel linearModel;  
    
	public DesempenoController() {
		UbigeoDAO ubigeoDAO=DAOFactory.getInstance().getUbigeoDAO();
		departamentos= ubigeoDAO.BuscarPorDepartamento();
		createLinearModel();
	}

	public void buscar(ActionEvent e){
		List<DatoGrafico> lista = null;
		if (!Constantes.VALOR_VACIO.equals(codDep)) {
			ArrayList<String> estados = new ArrayList<String>();
			estados.add("C");
			estados.add("T");
			CedulaDAO cedulaDAO = DAOFactory.getInstance().getCedulaDAO();
			lista = cedulaDAO.buscarPorUbigeo(codDep  + codProv + codDist, estados);
		}
		linearModel = new CartesianChartModel();
		if (lista != null) {
			Collections.sort(lista, new Comparator<DatoGrafico>() {
				public int compare(DatoGrafico o1, DatoGrafico o2) {
					try {
						Date date1 = DATE_FORMAT.parse(o1.getFecha());
						Date date2 = DATE_FORMAT.parse(o2.getFecha());
						if (date1.before(date2)) {
							return -1;
						} else if (date1.after(date2)) {
							return 1;
						}
					} catch (Exception ex) {
						ex.printStackTrace();
					}
					return 0;
				}
			});
			System.out.println("-------");
			for (DatoGrafico d : lista) {
				System.out.println(d.getFecha());
			}
			lista = modificarLista(lista);
			System.out.println("-------");
			for (DatoGrafico d : lista) {
				System.out.println(d.getFecha());
			}
	        LineChartSeries series1 = new LineChartSeries();  
	        series1.setLabel("Proyectado");  
	        LineChartSeries series2 = new LineChartSeries();  
	        series2.setLabel("Ejecutado");  
	        series2.setMarkerStyle("diamond");
	        
	        for (DatoGrafico dg : lista) {
	        	series1.set(dg.getFecha(), dg.getDiario());
	        	series2.set(dg.getFecha(), dg.getRealizado());
	        }
	  
	        linearModel.addSeries(series1);  
	        linearModel.addSeries(series2); 
		}
	
/*		createLinearModel();
		PersonalDAO personalDAO=DAOFactory.getInstance().getPersonalDAO();
		Personal personal=personalDAO.buscarPorDni(dni);
		if (personal!=null){
		RutaPersonalDAO rutaPersonalDAO=DAOFactory.getInstance().getRutaPersonalDAO();
		rutaPersonal =rutaPersonalDAO.buscarPersona(personal.getCodigoPersonal());
		}*/
	}
	
	public List<DatoGrafico> modificarLista (List<DatoGrafico> lista) {
		List<DatoGrafico> fin = new ArrayList<DatoGrafico>();
		try {
			DatoGrafico dg1 = lista.get(0);
			fin.add(dg1);
			double trabajados = dg1.getDiario();
			double avanzados = dg1.getRealizado();
			for (int i=1; i<lista.size(); i++) {
				DateTime dt1 = new DateTime(DATE_FORMAT.parse(dg1.getFecha()));
				DatoGrafico dg2 = lista.get(i);
				DateTime dt2 = new DateTime(DATE_FORMAT.parse(dg2.getFecha()));
				
				DateTime dtTemp = dt1.plusDays(1);
				while (dtTemp.isBefore(dt2)) {
					DatoGrafico dgTemp = new DatoGrafico();
					trabajados += dg1.getDiario();
					dgTemp.setDiario(trabajados);
					dgTemp.setFecha(DATE_FORMAT.format(dtTemp.toDate()));
					dgTemp.setRealizado(avanzados);
					fin.add(dgTemp);
					dtTemp = dtTemp.plusDays(1);
				}
				
				DatoGrafico dgTemp = new DatoGrafico();
				trabajados += dg1.getDiario();
				dgTemp.setDiario(trabajados);
				dgTemp.setFecha(dg2.getFecha());
				avanzados += dg1.getRealizado();
				dgTemp.setRealizado(avanzados);
				fin.add(dgTemp);
				dg1 = dg2;
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return fin;
	}
	
	public void buscarProvincia() {
		UbigeoDAO ubigeoDAO = DAOFactory.getInstance().getUbigeoDAO();
		provincias=ubigeoDAO.BuscarPorProvincia(codDep);
		codProv = null;
		codDist = null;
		distritos = new ArrayList<Ubigeo>();
	}

	public void buscarDistrito() {
		UbigeoDAO ubigeoDAO = DAOFactory.getInstance().getUbigeoDAO();
		distritos=ubigeoDAO.BuscarPorDistrito(codDep, codProv);

	}
    private void createLinearModel() {
		linearModel = new CartesianChartModel();/*
		ArrayList<String> estados = new ArrayList<String>();
		estados.add("C");
		estados.add("T");
		CedulaDAO cedulaDAO = DAOFactory.getInstance().getCedulaDAO();
		List<DatoGrafico> lista = cedulaDAO.buscarPorUbigeo("120101", estados);
		System.out.println(lista);
		if (lista != null) {  
			  
	        LineChartSeries series1 = new LineChartSeries();  
	        series1.setLabel("Proyectado");  
	        LineChartSeries series2 = new LineChartSeries();  
	        series2.setLabel("Ejecutado");  
	        series2.setMarkerStyle("diamond");
	        
	        for (DatoGrafico dg : lista) {
	        	series1.set(dg.getFecha(), dg.getDiario());
	        	series2.set(dg.getFecha(), dg.getRealizado());
	        }
	  
	        linearModel.addSeries(series1);  
	        linearModel.addSeries(series2); 
		}*/
    }  
	public String getDni() {
		return dni;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}

	public List<RutaPersonal> getRutaPersonal() {
		return rutaPersonal;
	}

	public void setRutaPersonal(List<RutaPersonal> rutaPersonal) {
		this.rutaPersonal = rutaPersonal;
	}

	public String getRuta() {
		return ruta;
	}

	public void setRuta(String ruta) {
		this.ruta = ruta;
	}
	
	public String getCodDep() {
		return codDep;
	}

	public void setCodDep(String codDep) {
		this.codDep = codDep;
	}
	public String getCodProv() {
		return codProv;
	}

	public void setCodProv(String codProv) {
		this.codProv = codProv;
	}

	public String getCodDist() {
		return codDist;
	}

	public void setCodDist(String codDist) {
		this.codDist = codDist;
	}

	public List<Ubigeo> getDepartamentos() {
		return departamentos;
	}

	public void setDepartamentos(List<Ubigeo> departamentos) {
		this.departamentos = departamentos;
	}

	public List<Ubigeo> getProvincias() {
		return provincias;
	}

	public void setProvincias(List<Ubigeo> provincias) {
		this.provincias = provincias;
	}

	public List<Ubigeo> getDistritos() {
		return distritos;
	}

	public void setDistritos(List<Ubigeo> distritos) {
		this.distritos = distritos;
	}

	public CartesianChartModel getCategoryModel() {
		return categoryModel;
	}

	public void setCategoryModel(CartesianChartModel categoryModel) {
		this.categoryModel = categoryModel;
	}

	public CartesianChartModel getLinearModel() {  
        return linearModel;  
    }  
}

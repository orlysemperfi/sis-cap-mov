package pe.gob.inei.admin.controller;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;

import org.primefaces.model.chart.CartesianChartModel;
import org.primefaces.model.chart.LineChartSeries;

import pe.gob.inei.admin.dao.DAOFactory;
import pe.gob.inei.admin.dao.PersonalDAO;
import pe.gob.inei.admin.dao.RutaPersonalDAO;
import pe.gob.inei.admin.dao.UbigeoDAO;
import pe.gob.inei.sistencuesta.Personal;
import pe.gob.inei.sistencuesta.RutaPersonal;
import pe.gob.inei.sistencuesta.Ubigeo;

@ManagedBean(name="desempeno")
@ViewScoped
public class DesempenoController implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -3524289064510509689L;
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
		//linearModel=null;
		createLinearModel();
		}

	public void buscar(ActionEvent e){
		System.out.println("prueba");
	
		createLinearModel();
		PersonalDAO personalDAO=DAOFactory.getInstance().getPersonalDAO();
		Personal personal=personalDAO.buscarPorDni(dni);
		if (personal!=null){
		RutaPersonalDAO rutaPersonalDAO=DAOFactory.getInstance().getRutaPersonalDAO();
		rutaPersonal =rutaPersonalDAO.buscarPersona(personal.getCodigoPersonal());
		}
	}
	public void buscarProvincia() {
		UbigeoDAO ubigeoDAO = DAOFactory.getInstance().getUbigeoDAO();
		provincias=ubigeoDAO.BuscarPorProvincia(codDep);
	}

	public void buscarDistrito() {
		UbigeoDAO ubigeoDAO = DAOFactory.getInstance().getUbigeoDAO();
		distritos=ubigeoDAO.BuscarPorDistrito(codDep, codProv);

	}
    private void createLinearModel() {  
        linearModel = new CartesianChartModel();  
  
        LineChartSeries series1 = new LineChartSeries();  
        series1.setLabel("Proyectado");  
  
        series1.set("Dia 1", 5);  
        series1.set("Dia 2", 10);  
        series1.set("Dia 3", 15);  
        series1.set("Dia 4", 20);  
        series1.set("Dia 5", 25);  
  
        LineChartSeries series2 = new LineChartSeries();  
        series2.setLabel("Ejecutado");  
        series2.setMarkerStyle("diamond");  
  
        series2.set("Dia 1", 7);  
        series2.set("Dia 2", 9);  
        series2.set("Dia 3", 17);  
        series2.set("Dia 4", 21);  
        series2.set("Dia 5", 23);  
  
        linearModel.addSeries(series1);  
        linearModel.addSeries(series2);  
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

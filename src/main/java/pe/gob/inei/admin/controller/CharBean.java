package pe.gob.inei.admin.controller;

import java.io.Serializable;  

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.primefaces.model.chart.CartesianChartModel;  
import org.primefaces.model.chart.ChartSeries;  
import org.primefaces.model.chart.LineChartSeries;  

@ManagedBean(name="charBean")
@ViewScoped
public class CharBean implements Serializable {  
  
    /**
	 * 
	 */
	private static final long serialVersionUID = -3888926105687191087L;

	private CartesianChartModel categoryModel;  
  
    private CartesianChartModel linearModel;  
  
    public CharBean() {  
        createCategoryModel();  
        createLinearModel();  
    }  
  
    public CartesianChartModel getCategoryModel() {  
        return categoryModel;  
    }  
  
    public CartesianChartModel getLinearModel() {  
        return linearModel;  
    }  
  
    private void createCategoryModel() {  
        categoryModel = new CartesianChartModel();  
  
        ChartSeries boys = new ChartSeries();  
        boys.setLabel("Boys");  
  
        boys.set("2004", 120);  
        boys.set("2005", 100);  
        boys.set("2006", 44);  
        boys.set("2007", 150);  
        boys.set("2008", 25);  
  
        ChartSeries girls = new ChartSeries();  
        girls.setLabel("Girls");  
  
        girls.set("2004", 52);  
        girls.set("2005", 60);  
        girls.set("2006", 110);  
        girls.set("2007", 135);  
        girls.set("2008", 120);  
  
        categoryModel.addSeries(boys);  
        categoryModel.addSeries(girls);  
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
} 
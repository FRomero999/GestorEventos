/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.paco.gestoreventos;

import java.net.URL;
import java.sql.SQLException;
import java.util.HashMap;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;
import net.sf.jasperreports.export.SimplePdfExporterConfiguration;
import net.sf.jasperreports.swing.JRViewer;

public class Report {

    private static final long serialVersionUID = 1L;
 
    public void showReport() throws JRException, ClassNotFoundException, SQLException {
 
        System.out.println("Working Directory = " + System.getProperty("user.dir"));
        System.out.println(App.class.getResource("eventos.jasper"));
        String fileName = App.class.getResource("eventos.jasper").getFile();
        HashMap hm = new HashMap();
        
        hm.put("TITLE", "Informe "+new java.util.Date().toString());
       
        
        //JasperReport report = JasperCompileManager.compileReport(fileName);     
        //JasperPrint jasperPrint = JasperFillManager.fillReport(report, hm, Conexion.conectar());

        
        JasperPrint jasperPrint = JasperFillManager.fillReport(fileName, hm, Conexion.conectar());
        
        JRViewer viewer = new JRViewer(jasperPrint);
 
        javax.swing.JFrame frame = new javax.swing.JFrame("Report");
        frame.getContentPane().add(viewer);
        frame.setExtendedState(javax.swing.JFrame.MAXIMIZED_BOTH);
        frame.pack();
        frame.setVisible(true);        

        System.out.print("Done!");       
    }    
    
    public void pdfReport() throws JRException, ClassNotFoundException, SQLException {
 
        String fileName = "/home/paco/JaspersoftWorkspace/MyReports/listado.jrxml";
        HashMap hm = new HashMap();
        
        // descarga dentro del mismo proyecto

        hm.put("TITLE", "Informe "+new java.util.Date().toString());
       
        JasperReport report = JasperCompileManager.compileReport(fileName);

        JasperPrint jasperPrint = JasperFillManager.fillReport(report, hm, Conexion.conectar());
        
        JRPdfExporter exp = new JRPdfExporter();
        exp.setExporterInput(new SimpleExporterInput(jasperPrint));
        exp.setExporterOutput(new SimpleOutputStreamExporterOutput("ReporteAlumnos.pdf"));
        SimplePdfExporterConfiguration conf = new SimplePdfExporterConfiguration();
        exp.setConfiguration(conf);
        exp.exportReport();      

        System.out.print("Done!");       
    }  
    

     
    
}

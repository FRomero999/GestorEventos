/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.paco.gestoreventos;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author paco
 */
public class AyudaController implements Initializable {

    @FXML
    private Button btnSalir;
    @FXML
    private WebView visor;
    
    private WebEngine webengine;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        webengine = visor.getEngine();
        var htmlayuda = AyudaController.class.getResource("ayuda/ayuda.html").toExternalForm();
        webengine.load(htmlayuda);
    }    

    @FXML
    private void salir(ActionEvent event) {
        Stage ventana = (Stage) btnSalir.getScene().getWindow();
        ventana.close();
    }
    
}

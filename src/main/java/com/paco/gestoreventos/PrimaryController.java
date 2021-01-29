package com.paco.gestoreventos;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Arrays;
import java.util.Date;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import static javafx.application.Application.launch;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.stage.Stage;
import models.*;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.resource.transaction.spi.TransactionStatus;

public class PrimaryController implements Initializable{

    @FXML
    private Button btnGuardar;
    @FXML
    private Button btnCancelar;
    @FXML
    private ChoiceBox<String> evento;
    @FXML
    private DatePicker fecha;
    @FXML
    private Spinner<Integer> asistentes;
    @FXML
    private TextField txtNombre;
    @FXML
    private TextField txtApellido1;
    @FXML
    private TextField txtApellido2;
    @FXML
    private TextField txtCorreo;
    @FXML
    private TextArea txtObservaciones;
    @FXML
    private Button btnListado;

    ObservableList<String> combo;

    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        combo = FXCollections.observableArrayList("Evento1","Evento2","Evento3");
        evento.setItems(combo);
        evento.getSelectionModel().select(0);

        /* Iniciamos el spinner para que muestre 1-5 */
        asistentes.setValueFactory( new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 5, 1) );

    }

    @FXML
    private void guardar(ActionEvent event) {
        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle("Revisión");
        alert.setHeaderText("Revise por favor el contenido antes de guardar");

        /* El DatePicker devuelve una instancia de clase LocalDate, pero en la base de datos el tipo de la columna es Date */
	ZoneId defaultZoneId = ZoneId.systemDefault();
        LocalDate local = fecha.getValue();
        Date f = Date.from(local.atStartOfDay(defaultZoneId).toInstant());

        /* Creamos la reserva */
        models.Reserva r = new Reserva(txtNombre.getText(),txtApellido1.getText(),txtApellido2.getText(),txtCorreo.getText(),
              evento.getValue(),  txtObservaciones.getText(), asistentes.getValue(), f );
        
        alert.setContentText(r.toString());
       
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK){
            // ... solo ahora operamos con la base de datos
            try (Session s = HibernateUtil.getSessionFactory().openSession()) {
              Transaction t = s.beginTransaction();
              s.save(r);
              t.commit();
              
              if(t.getStatus()==TransactionStatus.COMMITTED){
                  /* Commit realizado con éxito */
                Alert alert2 = new Alert(AlertType.INFORMATION);
                alert2.setTitle("Inserción");
                alert2.setHeaderText("Reserva insertada con éxito");
                alert2.showAndWait();
              }
              
            } catch( Exception e) {
                /* si hay una excepción, la muestro en un alert */
                Alert alert2 = new Alert(AlertType.ERROR);
                alert2.setTitle("Error");
                alert2.setHeaderText("Hubo un error");
                alert2.setContentText(e.toString());
                alert2.showAndWait();                
            }
        } else {
            /* Nada que hacer */
            r=null;
        }
        
    }

    @FXML
    private void cancelar(ActionEvent event) {
    }

    @FXML
    private void verlistado(ActionEvent event) throws IOException {
        App.setRoot("secondary");        
    }

    @FXML
    private void ayuda(ActionEvent event) {
        
        Scene escenaAyuda;
        try {
            FXMLLoader fxmlAyuda = App.loadFXML("ayuda");
            escenaAyuda = new Scene(fxmlAyuda.load());
            AyudaController ayudaController = fxmlAyuda.getController();
            Stage ventanaAyuda = new Stage();
            ventanaAyuda.setScene(escenaAyuda);
            ventanaAyuda.show();
        } catch (IOException ex) {
            System.out.println("Error accediendo a la ayuda");
            System.out.println(ex);
            ex.printStackTrace();
        }
        
    }
}

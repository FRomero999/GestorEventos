package com.paco.gestoreventos;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseEvent;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;


public class SecondaryController implements Initializable {

    @FXML
    private ChoiceBox<String> evento;
    @FXML
    private TableColumn<models.Reserva, String> cNombre;
    @FXML
    private TableColumn<models.Reserva, String> cApellidos;
    @FXML
    private TableColumn<models.Reserva, String> cAsistentes;
    @FXML
    private TableColumn<models.Reserva, String> cEmail;
    @FXML
    private TableColumn<models.Reserva, String> cFecha;
    @FXML
    private Button btnVolver;
    @FXML
    private Button btnInforme;
    @FXML
    private Button btnActualizar;
    @FXML
    private TableView<models.Reserva> tabla;

    ObservableList<String> combo;
    ObservableList<models.Reserva> reservas;    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        /* Inicio el ChoiceBox */
        combo = FXCollections.observableArrayList("Evento1","Evento2","Evento3");
        evento.setItems(combo);
        evento.getSelectionModel().select(0);
        
        /* Iniciamos la tabla... siempre es así */
        cNombre.setCellValueFactory((CellDataFeatures<models.Reserva, String> p) -> new ReadOnlyObjectWrapper(p.getValue().getNombre()));
        cApellidos.setCellValueFactory((CellDataFeatures<models.Reserva, String> p) -> new ReadOnlyObjectWrapper(p.getValue().getApellido1() +" "+ p.getValue().getApellido2()));
        cAsistentes.setCellValueFactory((CellDataFeatures<models.Reserva, String> p) -> new ReadOnlyObjectWrapper(p.getValue().getAsistentes()+""));
        cEmail.setCellValueFactory((CellDataFeatures<models.Reserva, String> p) -> new ReadOnlyObjectWrapper(p.getValue().getCorreo() ));
        cFecha.setCellValueFactory((CellDataFeatures<models.Reserva, String> p) -> new ReadOnlyObjectWrapper(p.getValue().getFecha().toString() ));

        reservas = FXCollections.observableArrayList();
        tabla.setItems(reservas);

        actualizar();

    }
    
    
    @FXML
    private void Volver(ActionEvent event) throws IOException {
        App.setRoot("primary");        
    }

    @FXML
    private void generarInforme(ActionEvent event) {
    }

    @FXML
    private void actualizarTabla(ActionEvent event) {
        actualizar();
    }
    
    private void actualizar(){
        
        // Obtengo la sesión para obtener el contenido de la tabla.
        // Como es una consulta simple, no es necesaria la transacción
        try (Session s = HibernateUtil.getSessionFactory().openSession()) {
                        
            Query q = s.createQuery("FROM Reserva WHERE evento=:evento");
            q.setParameter("evento",evento.getValue());
            
            List<models.Reserva> resultados = q.getResultList();
            System.out.println(resultados);
            reservas.clear();
            reservas.addAll(resultados);                        
            
        }          
        
    };


    @FXML
    private void mostrarRerserva(MouseEvent event) {
        
        models.Reserva reservaSeleccionada = tabla.getSelectionModel().getSelectedItem();
        
        // Si no hay selección, no hacer nada       
        if(reservaSeleccionada==null){
            return;
        }
        
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Detalles");
        alert.setHeaderText("Detalles de la reserva");
        alert.setContentText( reservaSeleccionada.toString() );
        
        /* añadimos un botón más para borrar */
        ButtonType btnBorrarAlert = new ButtonType("Borrar");
        alert.getButtonTypes().add(btnBorrarAlert);

        Optional<ButtonType> result = alert.showAndWait();
        
        if (result.get() == btnBorrarAlert){
            /* listo para borrar */
            try (Session s = HibernateUtil.getSessionFactory().openSession()) {
                Transaction t = s.beginTransaction();
                s.delete(reservaSeleccionada);
                t.commit();
                actualizar();
            }                
        }

    }
    
}
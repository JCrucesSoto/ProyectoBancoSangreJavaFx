/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Modelo.Donaciones;
import Modelo.Donante;
import Modelo.tablaDonaciones;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;



/**
 *
 * @author J
 */


public class FXMLBancoSangreController implements Initializable {
    
    
    
    @FXML
    private TextField txtDNI;
    @FXML
    private TextField txtNombre;
    @FXML
    private TextField txtDireccion;
    @FXML
    private TextField txtCodPostal;
    @FXML
    private TextField txtLocalidad;
    @FXML
    private TextField txtTelefono;
    @FXML
    private ChoiceBox<String> cbxGrupoSanguineo;
    @FXML
    private ChoiceBox<String> cbxFactorRH;
    @FXML
    private TableView<Donante> tblDonantes;
    @FXML
    private TableColumn<Donante, String> tbcDNI;
    @FXML
    private TableColumn<Donante, String> tbcNombre;
    @FXML
    private TableColumn<Donante, String> tbcDireccion;
    @FXML
    private TableColumn<Donante, String> tbcCodPostal;
    @FXML
    private TableColumn<Donante, String> tbcLocalidad;
    @FXML
    private TableColumn<Donante, String> tbcFechaNac;
    @FXML
    private TableColumn<Donante, String> tbcMail;
    @FXML
    private TableColumn<Donante, String> tbcTelefono;
    @FXML
    private TableColumn<Donante, String> tbcGrupoSanguineo;
    @FXML
    private TableColumn<Donante, String> tbcFactorRH;
    @FXML
    private Button btnConsultarDni;
    @FXML
    private Button btnConsultarTodos;
    @FXML
    private Button btnInsertar;
    @FXML
    private Button btnModificar;
    @FXML
    private Button btnEliminar;


    @FXML
    private TextField txtMail;
    @FXML
    private TextField txtDniDonante;
    @FXML
    private Button btnConsultarDonaciones;
    @FXML
    private TableView<tablaDonaciones> tblDonaciones;
    @FXML
    private TableColumn<tablaDonaciones, String> tbcCodSanitario;
    @FXML
    private TableColumn<tablaDonaciones, String> tbcNomSanitario;
    @FXML
    private TableColumn<tablaDonaciones, String> tbcFecha;
    @FXML
    private TableColumn<tablaDonaciones, String> tbcCantidad;
    @FXML
    private TableColumn<tablaDonaciones, String> tbcIncidencia;
    @FXML
    private Button btnAveriguar;
    @FXML
    private DatePicker txtFechaNac;
    
    ObservableList<Donante> obListDon = FXCollections.observableArrayList();
    
    
    
    ClaseManejadorFichero claseManejadorFicheros;
    Donaciones datosDonaciones;
    
    @FXML
    private Label lblSetNombreDonante;
    @FXML
    private Label lblSetTipoSangre;
    @FXML
    private ChoiceBox<String> ChoiceGruSan;
    @FXML
    private ChoiceBox<String> ChoicefactorRH;
    
    private ObservableList<String> obListDonarA = FXCollections.observableArrayList();
    private ObservableList<String> obListRecibirDe = FXCollections.observableArrayList();
    @FXML
    private TableView<String> tbCompapda;
    @FXML
    private TableColumn<String , String> tcpda;
    @FXML
    private TableView<String> tbCompaprd;
    @FXML
    private TableColumn<String, String> tcprd;
    

    
    

    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        cbxGrupoSanguineo.setItems(FXCollections.observableArrayList("0","A","B", "AB"));
        cbxFactorRH.setItems(FXCollections.observableArrayList("+","-"));
        
        ChoiceGruSan.setItems(FXCollections.observableArrayList("0","A","B", "AB"));
        ChoicefactorRH.setItems(FXCollections.observableArrayList("+","-"));
        
               
    }    

    @FXML
    private void listenerBotonConsultarDNI(ActionEvent event) throws SQLException {
        
        tblDonantes.getItems().clear();
        
        String dni = txtDNI.getText();
        
        InsertarYConsultar objeto = new InsertarYConsultar();
       
        ResultSet sql = objeto.consulta("SELECT * FROM DONANTES WHERE DNI = '" + dni + "'");
        
        
        while (sql.next()) {
            obListDon.add(new Donante(sql.getString("DNI"), sql.getString("Nombre"), sql.getString("Direccion"), sql.getString("CodPostal"), sql.getString("Localidad"), sql.getString("FechaNac"), sql.getString("Correo"), sql.getString("Telefono"), sql.getString("GrupoSang"), sql.getString("FactorRH")));
        
        }
        
        tbcDNI.setCellValueFactory(new PropertyValueFactory<>("DNI"));
        tbcNombre.setCellValueFactory(new PropertyValueFactory<>("NomDonante"));
        tbcDireccion.setCellValueFactory(new PropertyValueFactory<>("DireccionDonante"));
        tbcCodPostal.setCellValueFactory(new PropertyValueFactory<>("CodPostal"));
        tbcLocalidad.setCellValueFactory(new PropertyValueFactory<>("Localidad"));
        tbcFechaNac.setCellValueFactory(new PropertyValueFactory<>("FechaNac"));
        tbcMail.setCellValueFactory(new PropertyValueFactory<>("mail"));
        tbcTelefono.setCellValueFactory(new PropertyValueFactory<>("Telefono"));
        tbcGrupoSanguineo.setCellValueFactory(new PropertyValueFactory<>("GrupoSang"));
        tbcFactorRH.setCellValueFactory(new PropertyValueFactory<>("FactorRH"));
        
        tblDonantes.setItems(obListDon);   
        }

    @FXML
    private void listenerBotonConsultarTodos(ActionEvent event) throws SQLException {
        
        tblDonantes.getItems().clear();
        
        InsertarYConsultar objeto = new InsertarYConsultar();
       
        ResultSet sql = objeto.consulta("SELECT * FROM DONANTES");
        
        
        while (sql.next()) {
            obListDon.add(new Donante(sql.getString("DNI"), sql.getString("Nombre"), sql.getString("Direccion"), sql.getString("CodPostal"), sql.getString("Localidad"), sql.getString("FechaNac"), sql.getString("Correo"), sql.getString("Telefono"), sql.getString("GrupoSang"), sql.getString("FactorRH")));
        
        }
        
        tbcDNI.setCellValueFactory(new PropertyValueFactory<>("DNI"));
        tbcNombre.setCellValueFactory(new PropertyValueFactory<>("NomDonante"));
        tbcDireccion.setCellValueFactory(new PropertyValueFactory<>("DireccionDonante"));
        tbcCodPostal.setCellValueFactory(new PropertyValueFactory<>("CodPostal"));
        tbcLocalidad.setCellValueFactory(new PropertyValueFactory<>("Localidad"));
        tbcFechaNac.setCellValueFactory(new PropertyValueFactory<>("FechaNac"));
        tbcMail.setCellValueFactory(new PropertyValueFactory<>("mail"));
        tbcTelefono.setCellValueFactory(new PropertyValueFactory<>("Telefono"));
        tbcGrupoSanguineo.setCellValueFactory(new PropertyValueFactory<>("GrupoSang"));
        tbcFactorRH.setCellValueFactory(new PropertyValueFactory<>("FactorRH"));
        
        tblDonantes.setItems(obListDon);
            
      
    }

    @FXML
    private void listenerBotonInsertar(ActionEvent event) {
        InsertarYConsultar objeto = new InsertarYConsultar();
        String dni = txtDNI.getText();
        String nomDonante = txtNombre.getText();
        String direccionDonante = txtDireccion.getText();
        String codPostal = txtCodPostal.getText();
        String localidad = txtLocalidad.getText();
        String telefono = txtTelefono.getText();
        LocalDate fechaNac =txtFechaNac.getValue();
        String mail = txtMail.getText();
        String grupoSang = cbxGrupoSanguineo.getValue();
        String factorRH = cbxFactorRH.getValue();
        
        boolean a = objeto.insertar("insert into donantes(DNI, Nombre, Direccion, CodPostal, Localidad, FechaNac, Correo, Telefono, GrupoSang, FactorRH) VALUES ('" + dni + "', '" + nomDonante + "', '" + direccionDonante + "', '" + codPostal + "', '" + localidad + "', '" + fechaNac + "', '" + mail + "', '" + telefono + "', '" + grupoSang + "', '" + factorRH + "')");
    }

    @FXML
    private void listenerBotonModificar(ActionEvent event) {
        InsertarYConsultar objeto = new InsertarYConsultar();
        String dni = txtDNI.getText();
        String nomDonante = txtNombre.getText();
        String direccionDonante = txtDireccion.getText();
        String codPostal = txtCodPostal.getText();
        String localidad = txtLocalidad.getText();
        String telefono = txtTelefono.getText();
        LocalDate fechaNac =txtFechaNac.getValue();
        String mail = txtMail.getText();
        String grupoSang = cbxGrupoSanguineo.getValue();
        String factorRH = cbxFactorRH.getValue();
        
        boolean a = objeto.insertar("UPDATE DONANTES SET Nombre ='" + nomDonante + "', Direccion ='" + direccionDonante + "', CodPostal ='" + codPostal + "', Localidad = '" + localidad + "', FechaNac = '" + fechaNac + "', Correo ='" + mail + "', Telefono ='" + telefono + "', GrupoSang ='" + grupoSang + "', FactorRH ='" + factorRH + "' WHERE DNI = '" + dni + "'");
    }

    @FXML
    private void listenerBotonEliminar(ActionEvent event) {
        InsertarYConsultar objeto = new InsertarYConsultar();
        String dni = txtDNI.getText();
        
        boolean a = objeto.insertar("DELETE FROM DONANTES WHERE DNI = '" + dni + "'");
    }

    @FXML
    private void listenerBotonConsultarDonaciones(ActionEvent event) {
        
        tblDonantes.getItems().clear();
        
        String dni = txtDniDonante.getText();
        
        claseManejadorFicheros.leerDatosFicheroDAT(dni, tblDonaciones, lblSetNombreDonante, lblSetTipoSangre);
        datosDonaciones = claseManejadorFicheros.getDonaciones();
    }

    @FXML
    private void listenerBotonAveriguar(ActionEvent event) {
        tbCompapda.getItems().clear();
        tbCompaprd.getItems().clear();
        ArrayList<String> puedeDonarA = new ArrayList();
        ArrayList<String> puedeRecibirDe = new ArrayList();
        

        puedeDonarA.clear();
        puedeRecibirDe.clear();


        if (ChoicefactorRH.getValue().equals("+")) {
            switch ((String)ChoiceGruSan.getValue()) {
                case "0":
                    puedeDonarA.add("0+");
                    puedeDonarA.add("A+");
                    puedeDonarA.add("B+");
                    puedeDonarA.add("AB+");
                    puedeRecibirDe.add("0-");
                    puedeRecibirDe.add("0+");
                    break;
                case "A":
                    puedeDonarA.add("A+");
                    puedeDonarA.add("AB+");
                    puedeRecibirDe.add("0-");
                    puedeRecibirDe.add("0+");
                    puedeRecibirDe.add("A-");
                    puedeRecibirDe.add("A+");
                    break;
                case "B":
                    puedeDonarA.add("B+");
                    puedeDonarA.add("AB+");
                    puedeRecibirDe.add("0-");
                    puedeRecibirDe.add("0+");
                    puedeRecibirDe.add("B-");
                    puedeRecibirDe.add("B+");
                    break;
                case "AB":
                    puedeDonarA.add("AB+");
                    puedeRecibirDe.add("0-");
                    puedeRecibirDe.add("0+");
                    puedeRecibirDe.add("A-");
                    puedeRecibirDe.add("A+");
                    puedeRecibirDe.add("B-");
                    puedeRecibirDe.add("B+");
                    puedeRecibirDe.add("AB-");
                    puedeRecibirDe.add("AB+");
                    break;
            }
        } else {
            switch ((String) ChoiceGruSan.getValue()) {
                case "0":
                    puedeRecibirDe.add("0-");
                    puedeDonarA.add("0-");
                    puedeDonarA.add("0+");
                    puedeDonarA.add("A-");
                    puedeDonarA.add("A+");
                    puedeDonarA.add("B-");
                    puedeDonarA.add("B+");
                    puedeDonarA.add("AB-");
                    puedeDonarA.add("AB+");
                    break;
                case "A":
                    puedeDonarA.add("A-");
                    puedeDonarA.add("A+");
                    puedeDonarA.add("AB-");
                    puedeDonarA.add("AB+");
                    puedeRecibirDe.add("0-");
                    puedeRecibirDe.add("A-");
                    break;
                case "B":
                    puedeDonarA.add("B-");
                    puedeDonarA.add("B+");
                    puedeDonarA.add("AB-");
                    puedeDonarA.add("AB+");
                    puedeRecibirDe.add("0-");
                    puedeRecibirDe.add("B-");
                    break;
                case "AB":
                    puedeDonarA.add("AB-");
                    puedeDonarA.add("AB+");
                    puedeRecibirDe.add("0-");
                    puedeRecibirDe.add("A-");
                    puedeRecibirDe.add("B-");
                    puedeRecibirDe.add("AB-");
                    break;
            }

            
        }
        
        for (int i = 0; i < puedeDonarA.size(); i++) {

            obListDonarA.add(puedeDonarA.get(i));
        }

        for (int i = 0; i < puedeRecibirDe.size(); i++) {

            obListRecibirDe.add(puedeRecibirDe.get(i));
        }
        
        tcpda.setCellValueFactory(data -> new SimpleStringProperty(data.getValue()));
        tcprd.setCellValueFactory(data -> new SimpleStringProperty(data.getValue()));

        tbCompaprd.setItems(obListDonarA);
        tbCompapda.setItems(obListRecibirDe);

        
    }
    
    

    
}

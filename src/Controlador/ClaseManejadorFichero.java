/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Modelo.Donaciones;
import Modelo.tablaDonaciones;
import conexiondb.ConexionMySQL;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.swing.*;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.*;
import java.sql.*;
import java.time.LocalDate;
import java.util.Scanner;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;



// Documentacion fichero > ClaseManejadorFichero

public class ClaseManejadorFichero {

    //

    private Scanner teclado = new Scanner(System.in);

    // Documentacion ficheroLectura > ficheroEntrada

    private FileOutputStream ficheroSalida = null;
    private FileInputStream ficheroEntrada = null;
    private DataOutputStream datosSalida = null;
    private DataInputStream datosEntrada = null;
    private RandomAccessFile fichero = null;

    /**
     * Ruta de los archivos DAT y XML para poderlos localizar e interactuar con ellos
     */

    private String rutaArchivoDat = "src/donaciones.dat";
    private String rutaArchivoXml = "src/sanitarios.xml";

    Donaciones donaciones = new Donaciones();

    protected void introducirDatosFicheroDAT(String dniDonante, int codigoSanitario, String fechaDonacion, float cantidadMl, boolean incidencia) throws IOException {

        try {

            ficheroSalida = new FileOutputStream(rutaArchivoDat, true);
            datosSalida = new DataOutputStream(ficheroSalida);

            datosSalida.writeUTF(dniDonante);
            datosSalida.writeInt(codigoSanitario);
            datosSalida.writeUTF(fechaDonacion);
            datosSalida.writeFloat(cantidadMl);
            datosSalida.writeBoolean(incidencia);

            System.out.println("\t\nDatos incorporados al fichero\n");

        } catch (IOException fnfe) {
            System.out.println(fnfe.getMessage());
        } finally {
            try {
                if (ficheroSalida != null) {
                    ficheroSalida.close();
                }
                if (datosSalida != null) {
                    datosSalida.close();
                }
            } catch (IOException ioe) {
                System.out.println(ioe.getMessage());
            }
        }
    }

    protected void leerDatosFicheroDAT(String dniDeseado, TableView<tablaDonaciones> tablaParaRellenar, Label labelNombre, Label labelOtro) {
        
        String inci;

        // Documentacion encontrado > seEncontro

        boolean seEncontro = false;

        try {

            /**
             * Se abre el fichero en modo lectura y ponemos el cursor en la fila 0 para empezar a contar hasta que
             * encontremos los datos del sanitario cuyo DNI estamos buscando y se construyen los campos
             * y la tabla
             */

            fichero = new RandomAccessFile(rutaArchivoDat, "r");
            fichero.seek(0);
            int contador = 0;

            while (true) {

                /**
                 * Leemos los datos para irlos inicializando
                 */

                donaciones.dni = fichero.readUTF();
                donaciones.codigoSanitarioDat = fichero.readInt();
                donaciones.fechaDonacion = fichero.readUTF();
                donaciones.cantidadMl = fichero.readFloat();
                donaciones.incidencia = fichero.readBoolean();

                /**
                 * Si el DNI del sanitario es el que queremos, pasamos al metodo leerDatosDB para obtener el nombre
                 * del donante, el grupo sanguineo y el factor RH correspondiente, y luego vamos rellenando
                 * la tabla correspondiente, mientras que a la vez leemos los datos del XML
                 */

                if (donaciones.dni.equals(dniDeseado)) {

                    seEncontro = true;

                    leerDatosDB(dniDeseado, labelNombre, labelOtro);

                    /*tablaParaRellenar.setValueAt(donaciones.codigoSanitarioDat, contador, 0);
                    tablaParaRellenar.setValueAt(donaciones.fechaDonacion, contador, 2);
                    tablaParaRellenar.setValueAt(donaciones.cantidadMl, contador, 3);*/

                    leerDatosXML(dniDeseado, tablaParaRellenar, contador);

                    if (donaciones.incidencia) {
                        
                        inci = "Incidencia encontrada";


                    } else {
                        inci = "Ninguna incidencia";
                    }


                    String inc = donaciones.incidencia ? "S??" : "No";
                    
                    InsertarYConsultar objeto = new InsertarYConsultar();


                    boolean a = objeto.insertar("insert into sanitariosbanco(codSanitario, NombreSanitario, FechaDonacion, Cantidad, Incidencia, DniDonante) VALUES ('" + donaciones.codigoSanitarioDat + "', '" + donaciones.nombreSanitario + "', '" + donaciones.fechaDonacion + "', '" + donaciones.cantidadMl + "', '" + inci + "')");
                    contador++;
                }
            }

        } catch (EOFException eofe) {
            if(!seEncontro) JOptionPane.showMessageDialog(null, "DNI introducido err??neo", "Consultar DNI", JOptionPane.INFORMATION_MESSAGE);
        } catch (IOException fnfe) {
            System.out.println(fnfe.getMessage());
        } finally {
            try {
                if (fichero != null) {
                    fichero.close();
                }
            } catch (IOException eio) {
                System.out.println(eio.getMessage());
            }
        }
    }

    protected void leerDatosXML(String dni, TableView<tablaDonaciones> tablaParaRellenar, int contador) {
        File fichXML = new File(rutaArchivoXml);

        try {
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(fichXML);
            doc.getDocumentElement().normalize();

            doc.getDocumentElement();

            NodeList sanitarios = doc.getElementsByTagName("sanitario");

            for (int cont = 0; cont < sanitarios.getLength(); cont++) {
                Node nodo = sanitarios.item(cont);

                if (nodo.getNodeType() == Node.ELEMENT_NODE) {
                    Element element = (Element) nodo;

                    if (donaciones.codigoSanitarioDat == Integer.parseInt(element.getElementsByTagName("codsan").item(0).getTextContent())) {
                        donaciones.codigoSanitarioXML = donaciones.codigoSanitarioDat;
                        donaciones.nombreSanitario = element.getElementsByTagName("nomap").item(0).getTextContent();
                        donaciones.correo = element.getElementsByTagName("correo").item(0).getTextContent();
                        donaciones.telefono = element.getElementsByTagName("telefono").item(0).getTextContent();
                        if (donaciones.dni.equals(dni)) {
                            //tablaParaRellenar.setValueAt(donaciones.nombreSanitario, contador, 1);
                            contador++;
                        }
                    }
                }
            }
        } catch (Exception e) {
            System.out.println(e);
            e.printStackTrace();
        }
    }

    protected void leerDatosDB(String dniDonante, Label nombre, Label grupoSang) {
        try {
            /**
             * Preparamos los objetos de la base de datos para que pueda aceptar conexiones y realizamos
             * una consulta de la tabla donantes. Si el DNI es el que se busca, inicializa los campos
             * correspondientes.
             *
             * Este metodo sirve de auxiliar para leerDatosFicheroDAT
             */
            
            ConexionMySQL conexion = new ConexionMySQL("localhost", "proy3TE6", "root", "root");
            
            conexion.ejecutarConsulta("SELECT * FROM DONANTES WHERE DNI = '" + dniDonante + "'");
        
            ResultSet resultadoConsultas = conexion.getResultSet();

  

            while (resultadoConsultas.next()) {
                if (resultadoConsultas.getString(1).equals(donaciones.dni)) {

                    donaciones.nombreDonante = resultadoConsultas.getString(2);
                    donaciones.grupoSang = resultadoConsultas.getString(9);
                    donaciones.factorRH = resultadoConsultas.getString(10);


                    nombre.setText(donaciones.nombreDonante);
                    grupoSang.setText(donaciones.grupoSang + donaciones.factorRH);
                }
            }
        } catch (SQLException cnfe) {
            System.out.println(cnfe.getMessage());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    protected boolean introDatosDB(String codSanitario, String nombreSanitario, String fechaDonacion, String cantidad, String incidencia, String dniDonante){
        boolean control = false;
        try {
            Class.forName("org.mariadb.jdbc.Driver");
            String urlCon = "jdbc:mariadb://localhost:3306/Proy3TE6";
            Connection conexBd = DriverManager.getConnection(urlCon, "root", "root");
            Statement encapsulaCons = conexBd.createStatement();

            int filActualizadas = encapsulaCons.executeUpdate("INSERT INTO SANITARIOSBANCO(CodSanitario, NombreSanitario, FechaDonacion, Cantidad, Incidencia, DniDonante) VALUES('" + codSanitario + "', '" + nombreSanitario + "', '" + fechaDonacion + "', '" + cantidad + "', '" + incidencia + "', '" + dniDonante + "')"); // Ejecutamos la actualizaci??n anterior
            control = filActualizadas > 0;

            encapsulaCons.close();
            conexBd.close();
        } catch (ClassNotFoundException | SQLException cnfe) {
            System.out.println(cnfe.getMessage());
        }
        return control;
    }

    Donaciones getDonaciones() {
        return donaciones;
    }

}

    


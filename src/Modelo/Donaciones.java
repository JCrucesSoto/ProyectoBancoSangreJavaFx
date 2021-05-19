/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

/**
 *
 * @author J
 */
public class Donaciones {
        // Campos relacionados con el archivo dat

    public String dni;
    public int codigoSanitarioDat;
    public String fechaDonacion;
    public float cantidadMl;
    public boolean incidencia;

    // Campos relacionados con el archivo xml

    public int codigoSanitarioXML;
    public String nombreSanitario;
    public String correo;
    public String telefono;

    // Campos relacionados con el sql

    public String nombreDonante;
    public String factorRH;
    public String grupoSang;
    
}

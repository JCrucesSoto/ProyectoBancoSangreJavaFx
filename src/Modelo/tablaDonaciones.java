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
public class tablaDonaciones {
    
    String codSanitario;
    String nomSanitario;
    String fecha;
    String Cantidad;
    String incidencia;

    public String getCodSanitario() {
        return codSanitario;
    }

    public void setCodSanitario(String codSanitario) {
        this.codSanitario = codSanitario;
    }

    public String getNomSanitario() {
        return nomSanitario;
    }

    public void setNomSanitario(String nomSanitario) {
        this.nomSanitario = nomSanitario;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getCantidad() {
        return Cantidad;
    }

    public void setCantidad(String Cantidad) {
        this.Cantidad = Cantidad;
    }

    public String getIncidencia() {
        return incidencia;
    }

    public void setIncidencia(String incidencia) {
        this.incidencia = incidencia;
    }

    public tablaDonaciones(String codSanitario, String nomSanitario, String fecha, String Cantidad, String incidencia) {
        this.codSanitario = codSanitario;
        this.nomSanitario = nomSanitario;
        this.fecha = fecha;
        this.Cantidad = Cantidad;
        this.incidencia = incidencia;
    }
    
}

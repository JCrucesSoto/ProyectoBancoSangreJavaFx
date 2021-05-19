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
public class Donante {

    public String getNomDonante() {
        return nomDonante;
    }

    public void setNomDonante(String nomDonante) {
        this.nomDonante = nomDonante;
    }

    public String getDireccionDonante() {
        return direccionDonante;
    }

    public void setDireccionDonante(String direccionDonante) {
        this.direccionDonante = direccionDonante;
    }

    public String getCodPostal() {
        return codPostal;
    }

    public void setCodPostal(String codPostal) {
        this.codPostal = codPostal;
    }

    public String getLocalidad() {
        return localidad;
    }

    public void setLocalidad(String localidad) {
        this.localidad = localidad;
    }

    public String getFechaNac() {
        return fechaNac;
    }

    public void setFechaNac(String fechaNac) {
        this.fechaNac = fechaNac;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getGrupoSang() {
        return grupoSang;
    }

    public void setGrupoSang(String grupoSang) {
        this.grupoSang = grupoSang;
    }

    public String getFactorRH() {
        return factorRH;
    }

    public void setFactorRH(String factorRH) {
        this.factorRH = factorRH;
    }

    public Donante(String DNI, String nomDonante, String direccionDonante, String codPostal, String localidad, String fechaNac, String mail, String telefono, String grupoSang, String factorRH) {
        this.DNI = DNI;
        this.nomDonante = nomDonante;
        this.direccionDonante = direccionDonante;
        this.codPostal = codPostal;
        this.localidad = localidad;
        this.fechaNac = fechaNac;
        this.mail = mail;
        this.telefono = telefono;
        this.grupoSang = grupoSang;
        this.factorRH = factorRH;
    }

    public String getDNI() {
        return DNI;
    }

    public void setDNI(String DNI) {
        this.DNI = DNI;
    }
        
        String DNI;
        String nomDonante;
        String direccionDonante;
        String codPostal;
        String localidad;
        String fechaNac;
        String mail;
        String telefono;
        String grupoSang;
        String factorRH;
    
}

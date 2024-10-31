package com.edu.uniquindio.co.marketplace.clases.personas;

import java.io.File;
import java.io.Serializable;

public class Persona implements Serializable {
    protected String nombre;
    protected String apellido;
    protected String cedula;
    protected String correo;
    protected String contrasenia;
    protected File imagen;
    private static final long serialVersionUID = 1L;

    public Persona(String nombre, String apellido, String cedula, String correo, String contrasenia, File imagen) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.cedula = cedula;
        this.correo = correo;
        this.contrasenia = contrasenia;
        this.imagen = imagen;
    }

    public Persona(){

    }

    //----------------------------Gets y Sets-------------------------------------

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getContrasenia() {
        return contrasenia;
    }

    public void setContrasenia(String contrasenia) {
        this.contrasenia = contrasenia;
    }

    public File getImagen() {
        return imagen;
    }

    public void setImagen(File imagen) {
        this.imagen = imagen;
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author paco
 */
public class Reserva implements Serializable{
    
    private Integer id;
    private String nombre;
    private String apellido1;
    private String apellido2;
    private String correo;
    private String evento;
    private String observaciones;
    private Integer asistentes;
    private Date fecha;

    public Reserva() {
    }

    public Reserva(String nombre, String apellido1, String apellido2, String email, String evento, String observaciones, Integer asistentes, Date fecha) {
        this.id = 0;
        this.nombre = nombre;
        this.apellido1 = apellido1;
        this.apellido2 = apellido2;
        this.correo = email;
        this.evento = evento;
        this.observaciones = observaciones;
        this.asistentes = asistentes;
        this.fecha = fecha;
    }


    @Override
    public String toString() {
        return "Reserva{" + "id=" + id + ", nombre=" + nombre + ", apellido1=" + apellido1 + ", apellido2=" + apellido2 + ", email=" + correo + ", evento=" + evento + ", observaciones=" + observaciones + ", asistentes=" + asistentes + ", fecha=" + fecha.toString() + '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido1() {
        return apellido1;
    }

    public void setApellido1(String apellido1) {
        this.apellido1 = apellido1;
    }

    public String getApellido2() {
        return apellido2;
    }

    public void setApellido2(String apellido2) {
        this.apellido2 = apellido2;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getEvento() {
        return evento;
    }

    public void setEvento(String evento) {
        this.evento = evento;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public Integer getAsistentes() {
        return asistentes;
    }

    public void setAsistentes(Integer asistentes) {
        this.asistentes = asistentes;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }
    
    
    
}

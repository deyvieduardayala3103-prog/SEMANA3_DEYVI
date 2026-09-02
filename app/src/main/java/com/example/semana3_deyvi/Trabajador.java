package com.example.semana3_deyvi;

public class Trabajador {

    private int idTrabajador;
    private String nombresApellidos;
    private String dni;
    private String telefono;
    private String cargo;
    private String area;

    // Constructor vacío
    public Trabajador() {
    }

    // Constructor completo
    public Trabajador(int idTrabajador,
                      String nombresApellidos,
                      String dni,
                      String telefono,
                      String cargo,
                      String area) {

        this.idTrabajador = idTrabajador;
        this.nombresApellidos = nombresApellidos;
        this.dni = dni;
        this.telefono = telefono;
        this.cargo = cargo;
        this.area = area;
    }

    // Constructor sin ID
    public Trabajador(String nombresApellidos,
                      String dni,
                      String telefono,
                      String cargo,
                      String area) {

        this.nombresApellidos = nombresApellidos;
        this.dni = dni;
        this.telefono = telefono;
        this.cargo = cargo;
        this.area = area;
    }

    public int getIdTrabajador() {
        return idTrabajador;
    }

    public void setIdTrabajador(int idTrabajador) {
        this.idTrabajador = idTrabajador;
    }

    public String getNombresApellidos() {
        return nombresApellidos;
    }

    public void setNombresApellidos(String nombresApellidos) {
        this.nombresApellidos = nombresApellidos;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }
}
package com.example.semana3_deyvi;

public class Usuario {

    private int idUsuario;
    private String nombreApellido;
    private String contrasena;
    private String rol;

    // Constructor vacío
    public Usuario() {
    }

    // Constructor completo
    public Usuario(int idUsuario,
                   String nombreApellido,
                   String contrasena,
                   String rol) {

        this.idUsuario = idUsuario;
        this.nombreApellido = nombreApellido;
        this.contrasena = contrasena;
        this.rol = rol;
    }

    // Constructor sin ID
    public Usuario(String nombreApellido,
                   String contrasena,
                   String rol) {

        this.nombreApellido = nombreApellido;
        this.contrasena = contrasena;
        this.rol = rol;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getNombreApellido() {
        return nombreApellido;
    }

    public void setNombreApellido(String nombreApellido) {
        this.nombreApellido = nombreApellido;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }
}
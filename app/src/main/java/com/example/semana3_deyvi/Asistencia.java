package com.example.semana3_deyvi;

public class Asistencia {

    private int idAsistencia;
    private int idTrabajador;
    private String fecha;
    private String horaEntrada;
    private String horaSalida;
    private String observacion;

    // Constructor vacío
    public Asistencia() {
    }

    // Constructor completo
    public Asistencia(int idAsistencia,
                      int idTrabajador,
                      String fecha,
                      String horaEntrada,
                      String horaSalida,
                      String observacion) {

        this.idAsistencia = idAsistencia;
        this.idTrabajador = idTrabajador;
        this.fecha = fecha;
        this.horaEntrada = horaEntrada;
        this.horaSalida = horaSalida;
        this.observacion = observacion;
    }

    // Constructor sin ID asistencia
    public Asistencia(int idTrabajador,
                      String fecha,
                      String horaEntrada,
                      String horaSalida,
                      String observacion) {

        this.idTrabajador = idTrabajador;
        this.fecha = fecha;
        this.horaEntrada = horaEntrada;
        this.horaSalida = horaSalida;
        this.observacion = observacion;
    }

    public int getIdAsistencia() {
        return idAsistencia;
    }

    public void setIdAsistencia(int idAsistencia) {
        this.idAsistencia = idAsistencia;
    }

    public int getIdTrabajador() {
        return idTrabajador;
    }

    public void setIdTrabajador(int idTrabajador) {
        this.idTrabajador = idTrabajador;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getHoraEntrada() {
        return horaEntrada;
    }

    public void setHoraEntrada(String horaEntrada) {
        this.horaEntrada = horaEntrada;
    }

    public String getHoraSalida() {
        return horaSalida;
    }

    public void setHoraSalida(String horaSalida) {
        this.horaSalida = horaSalida;
    }

    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }
}
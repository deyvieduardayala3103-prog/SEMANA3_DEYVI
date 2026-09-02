package com.example.semana3_deyvi;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class AdminSQLiteData extends SQLiteOpenHelper {

    // Nombre de la base de datos
    private static final String NOMBRE_BD = "empresa_segovia.db";

    // Versión de la base de datos
    private static final int VERSION_BD = 3;

    // Nombre de las tablas
    private static final String TABLA_TRABAJADOR = "trabajador";
    private static final String TABLA_ASISTENCIA = "asistencia";
    private static final String TABLA_USUARIO = "usuario";


    // =========================================================
    // CONSTRUCTOR
    // =========================================================

    public AdminSQLiteData(Context context) {
        super(context, NOMBRE_BD, null, VERSION_BD);
    }


    // =========================================================
    // CREACIÓN DE LAS TABLAS
    // =========================================================

    @Override
    public void onCreate(SQLiteDatabase db) {

        // TABLA TRABAJADOR
        String crearTrabajador =
                "CREATE TABLE " + TABLA_TRABAJADOR + " (" +
                        "id_trabajador INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        "nombres_apellidos TEXT NOT NULL, " +
                        "dni TEXT NOT NULL UNIQUE, " +
                        "telefono TEXT, " +
                        "cargo TEXT, " +
                        "area TEXT)";

        db.execSQL(crearTrabajador);


        // TABLA ASISTENCIA
        String crearAsistencia =
                "CREATE TABLE " + TABLA_ASISTENCIA + " (" +
                        "id_asistencia INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        "id_trabajador INTEGER NOT NULL, " +
                        "fecha TEXT NOT NULL, " +
                        "hora_entrada TEXT, " +
                        "hora_salida TEXT, " +
                        "observacion TEXT, " +
                        "FOREIGN KEY(id_trabajador) " +
                        "REFERENCES trabajador(id_trabajador))";

        db.execSQL(crearAsistencia);


        // TABLA USUARIO
        String crearUsuario =
                "CREATE TABLE " + TABLA_USUARIO + " (" +
                        "id_usuario INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        "nombre_apellido TEXT NOT NULL, " +
                        "contrasena TEXT NOT NULL, " +
                        "rol TEXT NOT NULL)";

        db.execSQL(crearUsuario);
    }


    // =========================================================
    // ACTUALIZACIÓN DE LA BASE DE DATOS
    // =========================================================

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS " + TABLA_ASISTENCIA);
        db.execSQL("DROP TABLE IF EXISTS " + TABLA_TRABAJADOR);
        db.execSQL("DROP TABLE IF EXISTS " + TABLA_USUARIO);

        onCreate(db);
    }


    // =========================================================
    // CRUD TRABAJADOR
    // =========================================================


    // CREATE - REGISTRAR TRABAJADOR
    public boolean registrarTrabajador(
            String nombresApellidos,
            String dni,
            String telefono,
            String cargo,
            String area) {

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues valores = new ContentValues();

        valores.put("nombres_apellidos", nombresApellidos);
        valores.put("dni", dni);
        valores.put("telefono", telefono);
        valores.put("cargo", cargo);
        valores.put("area", area);

        long resultado = db.insert(
                TABLA_TRABAJADOR,
                null,
                valores
        );

        return resultado != -1;
    }


    // READ - LISTAR TRABAJADORES
    public Cursor listarTrabajadores() {

        SQLiteDatabase db = this.getReadableDatabase();

        return db.rawQuery(
                "SELECT * FROM " + TABLA_TRABAJADOR +
                        " ORDER BY nombres_apellidos ASC",
                null
        );
    }


    // READ - BUSCAR TRABAJADOR POR DNI
    public Cursor buscarTrabajador(String dni) {

        SQLiteDatabase db = this.getReadableDatabase();

        return db.rawQuery(
                "SELECT * FROM " + TABLA_TRABAJADOR +
                        " WHERE dni=?",
                new String[]{dni}
        );
    }


    // READ - BUSCAR TRABAJADOR POR ID
    public Cursor buscarTrabajadorPorId(int idTrabajador) {

        SQLiteDatabase db = this.getReadableDatabase();

        return db.rawQuery(
                "SELECT * FROM " + TABLA_TRABAJADOR +
                        " WHERE id_trabajador=?",
                new String[]{String.valueOf(idTrabajador)}
        );
    }


    // UPDATE - ACTUALIZAR TRABAJADOR
    public boolean actualizarTrabajador(
            int idTrabajador,
            String nombresApellidos,
            String dni,
            String telefono,
            String cargo,
            String area) {

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues valores = new ContentValues();

        valores.put("nombres_apellidos", nombresApellidos);
        valores.put("dni", dni);
        valores.put("telefono", telefono);
        valores.put("cargo", cargo);
        valores.put("area", area);

        int resultado = db.update(
                TABLA_TRABAJADOR,
                valores,
                "id_trabajador=?",
                new String[]{String.valueOf(idTrabajador)}
        );

        return resultado > 0;
    }


    // DELETE - ELIMINAR TRABAJADOR
    public boolean eliminarTrabajador(int idTrabajador) {

        SQLiteDatabase db = this.getWritableDatabase();

        int resultado = db.delete(
                TABLA_TRABAJADOR,
                "id_trabajador=?",
                new String[]{String.valueOf(idTrabajador)}
        );

        return resultado > 0;
    }


    // =========================================================
    // CRUD ASISTENCIA
    // =========================================================


    // CREATE - REGISTRAR ASISTENCIA
    public boolean registrarAsistencia(
            int idTrabajador,
            String fecha,
            String horaEntrada,
            String horaSalida,
            String observacion) {

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues valores = new ContentValues();

        valores.put("id_trabajador", idTrabajador);
        valores.put("fecha", fecha);
        valores.put("hora_entrada", horaEntrada);
        valores.put("hora_salida", horaSalida);
        valores.put("observacion", observacion);

        long resultado = db.insert(
                TABLA_ASISTENCIA,
                null,
                valores
        );

        return resultado != -1;
    }


    // READ - LISTAR ASISTENCIAS
    public Cursor listarAsistencias() {

        SQLiteDatabase db = this.getReadableDatabase();

        String consulta =
                "SELECT " +
                        "a.id_asistencia, " +
                        "a.id_trabajador, " +
                        "t.nombres_apellidos, " +
                        "t.dni, " +
                        "a.fecha, " +
                        "a.hora_entrada, " +
                        "a.hora_salida, " +
                        "a.observacion " +
                        "FROM asistencia a " +
                        "INNER JOIN trabajador t " +
                        "ON a.id_trabajador = t.id_trabajador " +
                        "ORDER BY a.id_asistencia DESC";

        return db.rawQuery(consulta, null);
    }


    // READ - BUSCAR ASISTENCIA POR TRABAJADOR
    public Cursor buscarAsistenciaTrabajador(int idTrabajador) {

        SQLiteDatabase db = this.getReadableDatabase();

        return db.rawQuery(
                "SELECT * FROM " + TABLA_ASISTENCIA +
                        " WHERE id_trabajador=?",
                new String[]{String.valueOf(idTrabajador)}
        );
    }


    // UPDATE - ACTUALIZAR ASISTENCIA
    public boolean actualizarAsistencia(
            int idAsistencia,
            int idTrabajador,
            String fecha,
            String horaEntrada,
            String horaSalida,
            String observacion) {

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues valores = new ContentValues();

        valores.put("id_trabajador", idTrabajador);
        valores.put("fecha", fecha);
        valores.put("hora_entrada", horaEntrada);
        valores.put("hora_salida", horaSalida);
        valores.put("observacion", observacion);

        int resultado = db.update(
                TABLA_ASISTENCIA,
                valores,
                "id_asistencia=?",
                new String[]{String.valueOf(idAsistencia)}
        );

        return resultado > 0;
    }


    // DELETE - ELIMINAR ASISTENCIA
    public boolean eliminarAsistencia(int idAsistencia) {

        SQLiteDatabase db = this.getWritableDatabase();

        int resultado = db.delete(
                TABLA_ASISTENCIA,
                "id_asistencia=?",
                new String[]{String.valueOf(idAsistencia)}
        );

        return resultado > 0;
    }


    // =========================================================
    // CRUD USUARIO
    // =========================================================


    // CREATE - REGISTRAR USUARIO
    public boolean registrarUsuario(
            String nombreApellido,
            String contrasena,
            String rol) {

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues valores = new ContentValues();

        valores.put("nombre_apellido", nombreApellido);
        valores.put("contrasena", contrasena);
        valores.put("rol", rol);

        long resultado = db.insert(
                TABLA_USUARIO,
                null,
                valores
        );

        return resultado != -1;
    }


    // READ - LISTAR USUARIOS
    public Cursor listarUsuarios() {

        SQLiteDatabase db = this.getReadableDatabase();

        return db.rawQuery(
                "SELECT * FROM " + TABLA_USUARIO,
                null
        );
    }


    // READ - BUSCAR USUARIO POR ID
    public Cursor buscarUsuarioPorId(int idUsuario) {

        SQLiteDatabase db = this.getReadableDatabase();

        return db.rawQuery(
                "SELECT * FROM " + TABLA_USUARIO +
                        " WHERE id_usuario=?",
                new String[]{String.valueOf(idUsuario)}
        );
    }


    // READ - INICIAR SESIÓN
    public Cursor iniciarSesion(
            String nombreApellido,
            String contrasena) {

        SQLiteDatabase db = this.getReadableDatabase();

        return db.rawQuery(
                "SELECT * FROM " + TABLA_USUARIO +
                        " WHERE nombre_apellido=? AND contrasena=?",
                new String[]{nombreApellido, contrasena}
        );
    }


    // UPDATE - ACTUALIZAR USUARIO
    public boolean actualizarUsuario(
            int idUsuario,
            String nombreApellido,
            String contrasena,
            String rol) {

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues valores = new ContentValues();

        valores.put("nombre_apellido", nombreApellido);
        valores.put("contrasena", contrasena);
        valores.put("rol", rol);

        int resultado = db.update(
                TABLA_USUARIO,
                valores,
                "id_usuario=?",
                new String[]{String.valueOf(idUsuario)}
        );

        return resultado > 0;
    }


    // DELETE - ELIMINAR USUARIO
    public boolean eliminarUsuario(int idUsuario) {

        SQLiteDatabase db = this.getWritableDatabase();

        int resultado = db.delete(
                TABLA_USUARIO,
                "id_usuario=?",
                new String[]{String.valueOf(idUsuario)}
        );

        return resultado > 0;
    }
}
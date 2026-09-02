package com.example.semana3_deyvi;

import android.app.AlertDialog;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    // =========================================================
    // CAMPOS DEL TRABAJADOR
    // =========================================================

    EditText edtIdTrabajador;
    EditText edtNombreApellido;
    EditText edtDni;
    EditText edtTelefono;
    EditText edtCargo;
    EditText edtArea;


    // =========================================================
    // BOTONES
    // =========================================================

    Button btnRegistrar;
    Button btnBuscar;
    Button btnActualizar;
    Button btnEliminar;
    Button btnLimpiar;


    // =========================================================
    // BASE DE DATOS
    // =========================================================

    AdminSQLiteData admin;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);


        // =====================================================
        // ENLAZAR CAMPOS
        // =====================================================

        edtIdTrabajador =
                findViewById(R.id.edtIdTrabajador);

        edtNombreApellido =
                findViewById(R.id.edtNombreApellido);

        edtDni =
                findViewById(R.id.edtDni);

        edtTelefono =
                findViewById(R.id.edtTelefono);

        edtCargo =
                findViewById(R.id.edtCargo);

        edtArea =
                findViewById(R.id.edtArea);


        // =====================================================
        // ENLAZAR BOTONES
        // =====================================================

        btnRegistrar =
                findViewById(R.id.btnRegistrar);

        btnBuscar =
                findViewById(R.id.btnBuscar);

        btnActualizar =
                findViewById(R.id.btnActualizar);

        btnEliminar =
                findViewById(R.id.btnEliminar);

        btnLimpiar =
                findViewById(R.id.btnLimpiar);


        // =====================================================
        // INICIAR BASE DE DATOS
        // =====================================================

        admin = new AdminSQLiteData(this);


        // =====================================================
        // BOTÓN REGISTRAR
        // =====================================================

        btnRegistrar.setOnClickListener(v -> {

            registrarTrabajador();

        });


        // =====================================================
        // BOTÓN BUSCAR
        // =====================================================

        btnBuscar.setOnClickListener(v -> {

            buscarTrabajador();

        });


        // =====================================================
        // BOTÓN ACTUALIZAR
        // =====================================================

        btnActualizar.setOnClickListener(v -> {

            actualizarTrabajador();

        });


        // =====================================================
        // BOTÓN ELIMINAR
        // =====================================================

        btnEliminar.setOnClickListener(v -> {

            eliminarTrabajador();

        });


        // =====================================================
        // BOTÓN LIMPIAR
        // =====================================================

        btnLimpiar.setOnClickListener(v -> {

            limpiarCampos();

        });

    }


    // =========================================================
    // REGISTRAR TRABAJADOR
    // =========================================================

    private void registrarTrabajador() {

        String nombre =
                edtNombreApellido
                        .getText()
                        .toString()
                        .trim();

        String dni =
                edtDni
                        .getText()
                        .toString()
                        .trim();

        String telefono =
                edtTelefono
                        .getText()
                        .toString()
                        .trim();

        String cargo =
                edtCargo
                        .getText()
                        .toString()
                        .trim();

        String area =
                edtArea
                        .getText()
                        .toString()
                        .trim();


        // VALIDAR NOMBRE
        if (nombre.isEmpty()) {

            edtNombreApellido.setError(
                    "Ingrese nombres y apellidos"
            );

            edtNombreApellido.requestFocus();

            return;
        }


        // VALIDAR DNI
        if (dni.isEmpty()) {

            edtDni.setError(
                    "Ingrese DNI"
            );

            edtDni.requestFocus();

            return;
        }


        // VALIDAR TELÉFONO
        if (telefono.isEmpty()) {

            edtTelefono.setError(
                    "Ingrese teléfono"
            );

            edtTelefono.requestFocus();

            return;
        }


        // VALIDAR CARGO
        if (cargo.isEmpty()) {

            edtCargo.setError(
                    "Ingrese cargo"
            );

            edtCargo.requestFocus();

            return;
        }


        // VALIDAR ÁREA
        if (area.isEmpty()) {

            edtArea.setError(
                    "Ingrese área"
            );

            edtArea.requestFocus();

            return;
        }


        // =====================================================
        // REGISTRAR EN SQLITE
        // =====================================================

        boolean resultado =
                admin.registrarTrabajador(
                        nombre,
                        dni,
                        telefono,
                        cargo,
                        area
                );


        if (resultado) {

            Toast.makeText(
                    MainActivity.this,
                    "Trabajador registrado correctamente",
                    Toast.LENGTH_LONG
            ).show();


            limpiarCampos();

        } else {

            Toast.makeText(
                    MainActivity.this,
                    "Error al registrar. El DNI puede estar repetido.",
                    Toast.LENGTH_LONG
            ).show();

        }

    }


    // =========================================================
    // BUSCAR TRABAJADOR
    // =========================================================

    private void buscarTrabajador() {

        String dni =
                edtDni
                        .getText()
                        .toString()
                        .trim();


        if (dni.isEmpty()) {

            edtDni.setError(
                    "Ingrese el DNI para buscar"
            );

            edtDni.requestFocus();

            return;
        }


        Cursor cursor =
                admin.buscarTrabajador(dni);


        if (cursor.moveToFirst()) {


            int id =
                    cursor.getInt(
                            cursor.getColumnIndexOrThrow(
                                    "id_trabajador"
                            )
                    );


            String nombre =
                    cursor.getString(
                            cursor.getColumnIndexOrThrow(
                                    "nombres_apellidos"
                            )
                    );


            String telefono =
                    cursor.getString(
                            cursor.getColumnIndexOrThrow(
                                    "telefono"
                            )
                    );


            String cargo =
                    cursor.getString(
                            cursor.getColumnIndexOrThrow(
                                    "cargo"
                            )
                    );


            String area =
                    cursor.getString(
                            cursor.getColumnIndexOrThrow(
                                    "area"
                            )
                    );


            // MOSTRAR DATOS
            edtIdTrabajador.setText(
                    String.valueOf(id)
            );

            edtNombreApellido.setText(nombre);

            edtTelefono.setText(telefono);

            edtCargo.setText(cargo);

            edtArea.setText(area);


            Toast.makeText(
                    MainActivity.this,
                    "Trabajador encontrado",
                    Toast.LENGTH_SHORT
            ).show();


        } else {

            Toast.makeText(
                    MainActivity.this,
                    "Trabajador no encontrado",
                    Toast.LENGTH_SHORT
            ).show();

        }


        cursor.close();

    }


    // =========================================================
    // ACTUALIZAR TRABAJADOR
    // =========================================================

    private void actualizarTrabajador() {

        String idTexto =
                edtIdTrabajador
                        .getText()
                        .toString()
                        .trim();


        String nombre =
                edtNombreApellido
                        .getText()
                        .toString()
                        .trim();


        String dni =
                edtDni
                        .getText()
                        .toString()
                        .trim();


        String telefono =
                edtTelefono
                        .getText()
                        .toString()
                        .trim();


        String cargo =
                edtCargo
                        .getText()
                        .toString()
                        .trim();


        String area =
                edtArea
                        .getText()
                        .toString()
                        .trim();


        if (idTexto.isEmpty()) {

            Toast.makeText(
                    MainActivity.this,
                    "Primero busque un trabajador",
                    Toast.LENGTH_SHORT
            ).show();

            return;

        }


        if (nombre.isEmpty()
                || dni.isEmpty()
                || telefono.isEmpty()
                || cargo.isEmpty()
                || area.isEmpty()) {

            Toast.makeText(
                    MainActivity.this,
                    "Complete todos los campos",
                    Toast.LENGTH_SHORT
            ).show();

            return;

        }


        int id =
                Integer.parseInt(idTexto);


        boolean resultado =
                admin.actualizarTrabajador(
                        id,
                        nombre,
                        dni,
                        telefono,
                        cargo,
                        area
                );


        if (resultado) {

            Toast.makeText(
                    MainActivity.this,
                    "Trabajador actualizado correctamente",
                    Toast.LENGTH_LONG
            ).show();


            limpiarCampos();


        } else {

            Toast.makeText(
                    MainActivity.this,
                    "No se pudo actualizar",
                    Toast.LENGTH_LONG
            ).show();

        }

    }


    // =========================================================
    // ELIMINAR TRABAJADOR
    // =========================================================

    private void eliminarTrabajador() {

        String idTexto =
                edtIdTrabajador
                        .getText()
                        .toString()
                        .trim();


        if (idTexto.isEmpty()) {

            Toast.makeText(
                    MainActivity.this,
                    "Primero busque un trabajador",
                    Toast.LENGTH_SHORT
            ).show();

            return;

        }


        int id =
                Integer.parseInt(idTexto);


        // MENSAJE DE CONFIRMACIÓN
        new AlertDialog.Builder(this)

                .setTitle("Eliminar trabajador")

                .setMessage(
                        "¿Está seguro de eliminar este trabajador?"
                )

                .setPositiveButton(
                        "Sí",
                        (dialog, which) -> {


                            boolean resultado =
                                    admin.eliminarTrabajador(id);


                            if (resultado) {

                                Toast.makeText(
                                        MainActivity.this,
                                        "Trabajador eliminado correctamente",
                                        Toast.LENGTH_LONG
                                ).show();


                                limpiarCampos();


                            } else {

                                Toast.makeText(
                                        MainActivity.this,
                                        "No se pudo eliminar",
                                        Toast.LENGTH_LONG
                                ).show();

                            }

                        }
                )


                .setNegativeButton(
                        "Cancelar",
                        null
                )


                .show();

    }


    // =========================================================
    // LIMPIAR CAMPOS
    // =========================================================

    private void limpiarCampos() {

        edtIdTrabajador.setText("");

        edtNombreApellido.setText("");

        edtDni.setText("");

        edtTelefono.setText("");

        edtCargo.setText("");

        edtArea.setText("");


        edtNombreApellido.requestFocus();

    }

}
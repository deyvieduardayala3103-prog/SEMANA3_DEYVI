package com.example.semana3_deyvi;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MenuPrincipalActivity extends AppCompatActivity {

    Button btnTrabajador, btnAsistencia, btnUsuario, btnSalir;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_principal);

        btnTrabajador = findViewById(R.id.btnTrabajador);
        btnAsistencia = findViewById(R.id.btnAsistencia);
        btnUsuario = findViewById(R.id.btnUsuario);
        btnSalir = findViewById(R.id.btnSalir);

        btnTrabajador.setOnClickListener(v -> {
            Intent intent = new Intent(MenuPrincipalActivity.this, MainActivity.class);
            startActivity(intent);
        });

        btnAsistencia.setOnClickListener(v -> {
            Intent intent = new Intent(MenuPrincipalActivity.this, AsistenciaActivity.class);
            startActivity(intent);
        });

        btnUsuario.setOnClickListener(v -> {
            Intent intent = new Intent(MenuPrincipalActivity.this, UsuarioActivity.class);
            startActivity(intent);
        });

        btnSalir.setOnClickListener(v -> {
            Toast.makeText(MenuPrincipalActivity.this, "Saliendo del sistema", Toast.LENGTH_SHORT).show();
            finish();
        });
    }
}
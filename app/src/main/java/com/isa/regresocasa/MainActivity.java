package com.isa.regresocasa;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    Button btnConfigurar;
    Button btnMapa;
    Button btnGuardarDestino;
    ConstraintLayout lytConfigurar;

    private String address;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);

        btnConfigurar = findViewById(R.id.btnConfigurar);
        btnMapa=findViewById(R.id.btnTrazar);
        lytConfigurar = findViewById(R.id.lytConfigurar);
        btnGuardarDestino = findViewById(R.id.btnGuardarDestino);

        btnConfigurar.setOnClickListener(btnConfigurar_click);
        btnMapa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent screenMapa = new Intent(getBaseContext(), mapa.class);
                startActivity(screenMapa);
            }
        });
        btnGuardarDestino.setOnClickListener(btnGurdarD_click);
        lytConfigurar.setVisibility(View.GONE);
    }

    View.OnClickListener btnConfigurar_click = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            lytConfigurar.setVisibility(View.VISIBLE);
            btnConfigurar.setVisibility(View.GONE);
            btnMapa.setVisibility(View.GONE);
        }
    };

    View.OnClickListener btnGurdarD_click = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            lytConfigurar.setVisibility(View.GONE);
            btnConfigurar.setVisibility(View.VISIBLE);
            btnMapa.setVisibility(View.VISIBLE);

            TextView txtCalle, txtNo, txtCol, txtCiudad, txtEstado, txtPais;
            txtCalle = findViewById(R.id.txtCalle);
            txtNo = findViewById(R.id.txtNo);
            txtCol = findViewById(R.id.txtCol);
            txtCiudad = findViewById(R.id.txtCiudad);
            txtEstado = findViewById(R.id.txtEstado);
            txtPais  = findViewById(R.id.txtPais);

            address = txtCalle.getText().toString() + " " + txtNo.getText().toString()
                    + " " + txtCol.getText().toString() + " " + txtCiudad.getText().toString()
                    + " " + txtEstado.getText().toString() + " " + txtPais.getText().toString();

            SharedPreferences sharedPref = getSharedPreferences
                    (getPackageName()+"_preferences", getBaseContext().MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPref.edit();
            editor.putString("addressOrigen", address);
            editor.commit();

            Log.d("cosa", "La direcciòn es: " + sharedPref.getString("addressOrigen", "No hay :c"));
        }
    };

}
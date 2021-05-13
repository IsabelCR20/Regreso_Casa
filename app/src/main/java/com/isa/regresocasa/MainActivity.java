package com.isa.regresocasa;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    Button btnConfigurar;
    Button btnMapa;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);
        btnConfigurar = findViewById(R.id.btnConfigurar);
        btnMapa=findViewById(R.id.btnTrazar);


        btnMapa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent screenMapa = new Intent(getBaseContext(), mapa.class);
                startActivity(screenMapa);
            }
        });
    }

}
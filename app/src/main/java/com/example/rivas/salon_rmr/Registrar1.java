package com.example.rivas.salon_rmr;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.AutoCompleteTextView;
import android.widget.Button;

public class Registrar1 extends AppCompatActivity {

    private Button btnGuardar ;


    private AutoCompleteTextView userNombre;
    private AutoCompleteTextView userApellido;
    private AutoCompleteTextView userCorreo;
    private AutoCompleteTextView userPassword;
    private AutoCompleteTextView userTelephone;
    private Button btnRegistrar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrar1);

        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        btnGuardar= (Button) findViewById(R.id.btnGuardar);


        btnGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent btnGuardar = new Intent(Registrar1.this, Principal.class);
                startActivity(btnGuardar);
            }
        });

    }


}


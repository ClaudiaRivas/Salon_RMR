package com.example.rivas.salon_rmr.Activities;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.Button;

import com.example.rivas.salon_rmr.R;

public class RegistrationActivity extends AppCompatActivity {

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
        setContentView(R.layout.activity_registration);

        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        btnGuardar= (Button) findViewById(R.id.btnGuardar);


        btnGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent btnGuardar = new Intent(RegistrationActivity.this, PrincipalActivity.class);
                startActivity(btnGuardar);
            }
        });

    }


}


package com.example.rivas.salon_rmr.Activities;

import android.content.Intent;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.rivas.salon_rmr.R;

import java.security.Principal;
import java.util.regex.Pattern;

public class LoginActivity extends AppCompatActivity {

    private EditText userEmail;
    private EditText userPassword;
    private Button btnIngresar ;
    private TextInputEditText impEmail;
    TextInputEditText impPassword;

    boolean Cor=false, Pas=false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        userEmail=(EditText)findViewById(R.id.userEmail);
        userPassword=(EditText)findViewById(R.id.userPassword);

        impEmail=(TextInputEditText)findViewById(R.id.impEmail);
        impPassword=(TextInputEditText)findViewById(R.id.impPassword);
        btnIngresar = (Button) findViewById(R.id.btnIngresar);

        btnIngresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (Patterns.EMAIL_ADDRESS.matcher(userEmail.getText().toString()).matches()==false){
                    impEmail.setError("Correo Invalido");
                    Cor=false;
                }else{
                    Cor=true;
                    impEmail.setError(null);
                }
                Pattern p=Pattern.compile("[0-9, a-z] [0-9, a-z] [0-9, a-z] [0-9, a-z] [0-9, a-z] [0-9, a-z] [0-9, a-z] [0-9, a-z]");
                if (p.matcher(userPassword.getText().toString()).matches()==false){
                    impPassword.setError("Contrasena Invalido");
                    Pas=false;
                }else{
                    Pas=true;
                    impPassword.setError(null);
                }
                if (Cor==true && Pas==true){
                    String usu=userEmail.getText().toString();
                    String clave=userPassword.getText().toString();
                    if (usu.equals("admin@gmail.com") &&clave.equals("root0508")){
                        Intent i=new Intent(getApplicationContext(), Principal.class);
                        startActivity(i);
                    }else {
                        Toast.makeText(getApplicationContext(), "Usuario o Contrasena Incorrectos", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }
}

package com.example.rivas.salon_rmr.Activities;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.Button;

import com.example.rivas.salon_rmr.R;

public class LoginActivity extends AppCompatActivity {

    private AutoCompleteTextView userEmail;
    private AutoCompleteTextView userPassword;
    private Button btnIngresar ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        btnIngresar = (Button) findViewById(R.id.btnIngresar);

        btnIngresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent btnIngresar = new Intent(LoginActivity.this, PrincipalActivity.class);
                startActivity(btnIngresar);
            }
        });
    }
}

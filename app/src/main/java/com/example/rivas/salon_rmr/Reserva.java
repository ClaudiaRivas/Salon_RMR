package com.example.rivas.salon_rmr;

import android.support.v7.app.AppCompatActivity;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TimePicker;

import java.text.Format;
import java.util.Calendar;

public class Reserva extends AppCompatActivity implements View.OnClickListener {

        Spinner servicios;
        Button btnFecha;
        Button btnHora;
        EditText editFecha;
        EditText editHora;

        private int dia, mes, anio, hora, minutos;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_reserva);


            ActionBar actionBar = getSupportActionBar();
            actionBar.hide();

            servicios = (Spinner) findViewById(R.id.sp01);

            ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.servicios, android.R.layout.simple_spinner_item);
            servicios.setAdapter(adapter);

            btnFecha=(Button) findViewById(R.id.btnFecha);
            btnHora=(Button) findViewById(R.id.btnHora);
            editFecha=(EditText) findViewById(R.id.editFecha);
            editHora=(EditText) findViewById(R.id.editHora);
            btnFecha.setOnClickListener(this);
            btnHora.setOnClickListener(this);

        }

        @Override
        public void onClick(View v) {
            if(v == btnFecha){
                final Calendar calendar= Calendar.getInstance();
                dia=calendar.get(Calendar.DAY_OF_MONTH);
                mes=calendar.get(Calendar.MONTH);
                anio=calendar.get(Calendar.YEAR);

                DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener(){
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        editFecha.setText(dayOfMonth+"/"+(monthOfYear+1)+"/"+year);
                    }
                } ,dia, mes, anio);
                datePickerDialog.show();

            }

            if(v==btnHora){
                final Calendar calendar= Calendar.getInstance();
                hora=calendar.get(Calendar.HOUR_OF_DAY);
                minutos=calendar.get(Calendar.MINUTE);

                TimePickerDialog timePickerDialog = new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {

                        String AM_PM ;

                        if(hourOfDay < 12) {
                            AM_PM = "AM";
                        } else {
                            AM_PM = "PM";
                        }

                        editHora.setText(hourOfDay + " : " + minute + " " + AM_PM );

                    }

                }, hora, minutos,false);
                timePickerDialog.show();

            }

        }
}


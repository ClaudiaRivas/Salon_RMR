package com.example.rivas.salon_rmr.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridLayout;
import android.widget.Toast;

import com.example.rivas.salon_rmr.R;

public class ProductFragment extends Fragment {

    GridLayout maingrid;

    CardView cardViewcabello, cardViewjoyeria , cardViewunas , cardViewmaquillaje;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_product,container,false);

        maingrid = (GridLayout)view.findViewById(R.id.maingrid);

        //setToggleEvent(maingrid);

        cardViewcabello = (CardView)view.findViewById(R.id.cardviewcabello);
        cardViewjoyeria = (CardView)view.findViewById(R.id.cardviewjoyeria);
        cardViewmaquillaje = (CardView)view.findViewById(R.id.cardviewmaquillaje);
        cardViewunas = (CardView)view.findViewById(R.id.cardviewunas);


        cardViewcabello.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Es el fragmento nuevo que quiero mostrar
                CabelloFragment cabelloFragment = new CabelloFragment();
                //creo una transaccion de fragmentos
                FragmentManager transaction = getFragmentManager();
                //iniciar la transaccion
                FragmentTransaction fragmentTransaction = transaction.beginTransaction();
                //reemplazar el fragmento actual con el nuevo
                fragmentTransaction.replace(R.id.fragmentProducto,cabelloFragment);
                fragmentTransaction.addToBackStack(null);
                //guardar cambios
                fragmentTransaction.commit();



            }
        });

        cardViewjoyeria.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Es el fragmento nuevo que quiero mostrar
                JoyeriaFragment joyeriaFragment = new JoyeriaFragment();
                //creo una transaccion de fragmentos
                FragmentManager transaction = getFragmentManager();
                //iniciar la transaccion
                FragmentTransaction fragmentTransaction = transaction.beginTransaction();
                //reemplazar el fragmento actual con el nuevo
                fragmentTransaction.replace(R.id.fragmentProducto,joyeriaFragment);
                fragmentTransaction.addToBackStack(null);
                //guardar cambios
                fragmentTransaction.commit();

            }
        });

        cardViewmaquillaje.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Es el fragmento nuevo que quiero mostrar
                MaquillajeFragment maquillajeFragment = new MaquillajeFragment();
                //creo una transaccion de fragmentos
                FragmentManager transaction = getFragmentManager();
                //iniciar la transaccion
                FragmentTransaction fragmentTransaction = transaction.beginTransaction();
                //reemplazar el fragmento actual con el nuevo
                fragmentTransaction.replace(R.id.fragmentProducto,maquillajeFragment);
                fragmentTransaction.addToBackStack(null);
                //guardar cambios
                fragmentTransaction.commit();
            }
        });

        cardViewunas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                //Es el fragmento nuevo que quiero mostrar
                UnasFragment unasFragment = new UnasFragment();
                //creo una transaccion de fragmentos
                FragmentManager transaction = getFragmentManager();
                //iniciar la transaccion
                FragmentTransaction fragmentTransaction = transaction.beginTransaction();
                //reemplazar el fragmento actual con el nuevo
                fragmentTransaction.replace(R.id.fragmentProducto,unasFragment);
                fragmentTransaction.addToBackStack(null);
                //guardar cambios
                fragmentTransaction.commit();

            }
        });
        return view;

    }
    private void setToggleEvent(GridLayout maingrid){

        for (int i = 0;i<maingrid.getChildCount();i++){
            final CardView cardView = (CardView)maingrid.getChildAt(i);
            cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                }
            });
        }

    }
}

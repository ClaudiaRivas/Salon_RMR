package com.example.rivas.salon_rmr.Fragment;

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

import com.example.rivas.salon_rmr.R;

public class ProductFragment extends Fragment {


    CardView cardViewcabello, cardViewjoyeria , cardViewunas , cardViewmaquillaje;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_product,container,false);

        cardViewcabello = (CardView)view.findViewById(R.id.cardviewcabello);
        cardViewjoyeria = (CardView)view.findViewById(R.id.cardviewjoyeria);
        cardViewmaquillaje = (CardView)view.findViewById(R.id.cardviewmaquillaje);
        cardViewunas = (CardView)view.findViewById(R.id.cardviewunas);


        cardViewcabello.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO pendiente referencia
                cargarFragmento("Productos de cabello","producto_cabello");
            }
        });

        cardViewjoyeria.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO pendiente referencia
                cargarFragmento("Productos de joyeria","producto_joyeria");
            }
        });

        cardViewmaquillaje.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO pendiente referencia
                cargarFragmento("Productos de maquillaje","producto_maquillaje");
            }
        });

        cardViewunas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO pendiente referencia
                cargarFragmento("Productos de uñas","producto_uñas");
            }
        });
        return view;

    }


    private void cargarFragmento(String titulo,String referencia){
        //Es el fragmento nuevo que quiero mostrar
        FragmentProductoGenerico fragmentProductoGenerico = new FragmentProductoGenerico();
        //establecer titulo y referencia de la BD
        fragmentProductoGenerico.setTitulo(titulo);
        fragmentProductoGenerico.setReferencia(referencia);
        //creo una transaccion de fragmentos
        FragmentManager transaction = getFragmentManager();
        //iniciar la transaccion
        FragmentTransaction fragmentTransaction = transaction.beginTransaction();
        //reemplazar el fragmento actual con el nuevo
        fragmentTransaction.replace(R.id.fragmentProduct, fragmentProductoGenerico);
        fragmentTransaction.addToBackStack(null);
        //guardar cambios
        fragmentTransaction.commit();
    }
}

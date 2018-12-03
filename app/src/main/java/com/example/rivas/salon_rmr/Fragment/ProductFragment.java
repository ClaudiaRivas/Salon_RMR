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
import android.widget.FrameLayout;
import android.widget.RelativeLayout;

import com.example.rivas.salon_rmr.Apputilities.BaseFragment;
import com.example.rivas.salon_rmr.R;

public class ProductFragment extends BaseFragment {

    CardView cardViewcabello, cardViewjoyeria , cardViewunas , cardViewmaquillaje;

    private static FragmentProductoGenerico mFragmentCabello    = new FragmentProductoGenerico();
    private static FragmentProductoGenerico mFragmentJoyeria    = new FragmentProductoGenerico();
    private static FragmentProductoGenerico mFragmentMaquillaje = new FragmentProductoGenerico();
    private static FragmentProductoGenerico mFragmentUnas       = new FragmentProductoGenerico();


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_product,container,false);

        cardViewcabello = (CardView)view.findViewById(R.id.cardviewcabello);
        cardViewjoyeria = (CardView)view.findViewById(R.id.cardviewjoyeria);
        cardViewmaquillaje = (CardView)view.findViewById(R.id.cardviewmaquillaje);
        cardViewunas = (CardView)view.findViewById(R.id.cardviewunas);

        initProductFragment();

        cardViewcabello.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO pendiente referencia
                cargarFragmento(mFragmentCabello);
            }
        });

        cardViewjoyeria.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO pendiente referencia
                cargarFragmento(mFragmentJoyeria);
            }
        });

        cardViewmaquillaje.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO pendiente referencia
                cargarFragmento(mFragmentMaquillaje);
            }
        });

        cardViewunas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO pendiente referencia
                cargarFragmento(mFragmentUnas);
            }
        });
        return view;
    }


    private void initProductFragment(){
        //inicializar el cabello
        mFragmentCabello.setTitulo("Productos de cabello");
        mFragmentCabello.setReferencia("producto_cabello");
        mFragmentCabello.setRutaImg("img_producto/cabello");

        //inicializar la joyeria
        mFragmentJoyeria.setTitulo("Productos de joyeria");
        mFragmentJoyeria.setReferencia("producto_joyeria");
        mFragmentJoyeria.setRutaImg("img_producto/joyeria");

        //inicializar el maquillaje
        mFragmentMaquillaje.setTitulo("Productos de maquillaje");
        mFragmentMaquillaje.setReferencia("producto_maquillaje");
        mFragmentMaquillaje.setRutaImg("img_producto/maquillaje");

        //inicializar las unias
        mFragmentUnas.setTitulo("Productos de uñas");
        mFragmentUnas.setReferencia("producto_uñas");
        mFragmentUnas.setRutaImg("img_producto/uñas");
    }

    private void cargarFragmento(FragmentProductoGenerico frmProducto){
        if (mFragmentNavigation != null) {
            mFragmentNavigation.pushFragment(frmProducto);
        }
    }
}

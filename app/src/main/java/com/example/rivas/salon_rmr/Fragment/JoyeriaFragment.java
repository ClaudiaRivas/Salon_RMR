package com.example.rivas.salon_rmr.Fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.rivas.salon_rmr.Apputilities.AdaptadorProductos;
import com.example.rivas.salon_rmr.Apputilities.GridDecoracion;
import com.example.rivas.salon_rmr.Model.Producto;
import com.example.rivas.salon_rmr.R;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class JoyeriaFragment extends Fragment {



    private RecyclerView recycler;
    private RecyclerView.LayoutManager administrador;
    private AdaptadorProductos adaptadorProductos;
    private GridDecoracion decoracion;
    private ArrayList<Producto> listaProductos=new ArrayList<>();

    public JoyeriaFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_joyeria, container, false);


        recycler = view.findViewById(R.id.reciclerJoyeria);
        recycler.setHasFixedSize(true);


        decoracion = new GridDecoracion(2,50,true);



        administrador = new GridLayoutManager(getContext(), 2);
        recycler.setLayoutManager(administrador);
        llenarLista();
        adaptadorProductos = new AdaptadorProductos(listaProductos,getContext());

        recycler.addItemDecoration(decoracion);
        recycler.setAdapter(adaptadorProductos);

        return view;
    }

    private void llenarLista() {
        listaProductos.add(new Producto("1","Producto 1","Descripcion1 ","$10"));
        listaProductos.add(new Producto("2","Producto 2","Descripcion1 ","$12"));
        listaProductos.add(new Producto("3","Producto 3","Descripcion1 ","$100"));
        listaProductos.add(new Producto("4","Producto 4","Descripcion1","$50"));
        listaProductos.add(new Producto("5","Producto 5","Descripcion1 ","$130"));
        listaProductos.add(new Producto("6","Producto 6","Descripcion1 ","$50"));
        listaProductos.add(new Producto("7","Producto 7","Descripcion1 ","$90"));
        listaProductos.add(new Producto("8","Producto 8","Descripcion1 ","$30"));
        listaProductos.add(new Producto("9","Producto 9","Descripcion1 ","$130"));
        listaProductos.add(new Producto("10","Producto 10","Descripcion1 ","$130"));


    }

}


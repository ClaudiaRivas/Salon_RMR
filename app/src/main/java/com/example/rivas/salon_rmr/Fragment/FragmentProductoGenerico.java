package com.example.rivas.salon_rmr.Fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.TextView;

import com.example.rivas.salon_rmr.Apputilities.AdaptadorProductos;
import com.example.rivas.salon_rmr.Apputilities.FragmentConsultaFirebase;
import com.example.rivas.salon_rmr.Apputilities.GridDecoracion;
import com.example.rivas.salon_rmr.R;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;

public class FragmentProductoGenerico extends FragmentConsultaFirebase {

    private RecyclerView recycler;
    private RecyclerView.LayoutManager administrador;

    private GridDecoracion decoracion;
    private CollectionReference dbReferencia;

    private String referencia;
    private String titulo;

    public String getReferencia() {
        return referencia;
    }

    public void setReferencia(String referencia) {
        this.referencia = referencia;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //inflar el layout
        View view = inflater.inflate(R.layout.fragment_producto_generico, container, false);

        TextView txtTitulo = (TextView) view.findViewById(R.id.txtTituloProductoGenerico);

        if (titulo != null) {
            txtTitulo.setText(titulo);
        } else {
            txtTitulo.setText("Productos");
        }

        recycler = view.findViewById(R.id.reciclerProductoGenerico);
        recycler.setHasFixedSize(true);

        decoracion = new GridDecoracion(2, 50, true);

        administrador = new GridLayoutManager(getContext(), 2);
        recycler.setLayoutManager(administrador);
        adaptadorItems = new AdaptadorProductos(listaItems, getContext());
        ((AdaptadorProductos) adaptadorItems).setFragmentManager(getFragmentManager());

        recycler.addItemDecoration(decoracion);
        recycler.setAdapter(adaptadorItems);
        //si tiene referencia de la BD
        if (referencia != null) {
            dbReferencia = FirebaseFirestore.getInstance().collection(referencia);
            dbReferencia.addSnapshotListener(new EventListener<QuerySnapshot>() {
                @Override
                public void onEvent(@javax.annotation.Nullable QuerySnapshot queryDocumentSnapshots, @javax.annotation.Nullable FirebaseFirestoreException e) {
                    actualizarDatos(queryDocumentSnapshots.getDocumentChanges());
                }
            });
        }
        return view;
    }
}

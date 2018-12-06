package com.example.rivas.salon_rmr.Fragment;


import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.rivas.salon_rmr.Activities.PrincipalActivity;
import com.example.rivas.salon_rmr.Apputilities.BaseFragment;
import com.example.rivas.salon_rmr.Apputilities.Contact;
import com.example.rivas.salon_rmr.Apputilities.DialogoContacto;
import com.example.rivas.salon_rmr.Model.Item;
import com.example.rivas.salon_rmr.R;

public class DetailsFragment extends BaseFragment {

    TextView txtDetalleTitulo,txtDetalleNombre,txtDetalleDescripcion,txtDetallePrecio;
    ImageView imagDetalleItem;
    Button btnReservar;
    private Item item;
    View view;
    CoordinatorLayout coordinatorLayout;

    public void setItem(Item item) {
        this.item = item;
    }

    public DetailsFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_details, container, false);
        coordinatorLayout       = view.findViewById(R.id.layoutDetails);

        txtDetalleTitulo        = view.findViewById(R. id.textDetalleTitulo);
        txtDetalleNombre        = view.findViewById(R. id.textDetalleNombre);
        txtDetalleDescripcion   = view.findViewById(R. id.textDetalleDescripcion);
        txtDetallePrecio        = view.findViewById(R. id.textDetallePrecio);
        imagDetalleItem         = view.findViewById(R. id.imgDetalleItem);
        btnReservar             = view.findViewById(R. id.btnReservar);

        if(item!=null){
            //txtDetalleTitulo.setText(item.ge);
            txtDetalleNombre.setText(item.getNombre());
            txtDetalleDescripcion.setText(item.getDescripcion());
            txtDetallePrecio.setText(item.getPrecio());

            if(item.getImgItem()!=null){
                imagDetalleItem.setImageBitmap(item.getImgItem());
            }
        }

        btnReservar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                contactarSalon();
            }
        });
        return view;
    }

    private void contactarSalon() {
        String num = PrincipalActivity.txtWhatsapp;
        if(Contact.verificarContacto(getContext(),coordinatorLayout,num)){
            DialogoContacto dialogo = new DialogoContacto();
            dialogo.showDialog(getActivity(),num,"Me interesa el : "+txtDetalleNombre.getText().toString()+" que tiene un valor de "+txtDetallePrecio.getText().toString());
        }
    }


}

package com.example.rivas.salon_rmr.Fragment;


import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
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

import java.util.ArrayList;
import java.util.List;

public class DetailsFragment extends BaseFragment {

    TextView txtDetalleTitulo,txtDetalleNombre,txtDetalleDescripcion,txtDetallePrecio;
    ImageView imagDetalleItem;
    Button btnReservar;
    private Item item;
    View view;
    CoordinatorLayout coordinatorLayout;

    String num = "";
    String msg = "";

    private static final int REQUEST_ID_MULTIPLE_PERMISSIONS = 1;

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
        num = PrincipalActivity.txtWhatsapp;
        msg =  "Me interesa el : " + txtDetalleNombre.getText().toString() + " que tiene un valor de " + txtDetallePrecio.getText().toString();
        if(checkAndRequestPermissions()) {
            if (Contact.verificarContacto(getContext(), coordinatorLayout, num)) {
                DialogoContacto dialogo = new DialogoContacto();
                dialogo.showDialog(getActivity(), num,msg);
            }
        }
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        //super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == REQUEST_ID_MULTIPLE_PERMISSIONS) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // Permission is granted
                if (Contact.verificarContacto(getContext(), coordinatorLayout, num)) {
                    DialogoContacto dialogo = new DialogoContacto();
                    dialogo.showDialog(getActivity(), num, msg);
                }
            } else {
                Snackbar snackbar = Snackbar
                        .make(coordinatorLayout, "Error al buscar en contactos", Snackbar.LENGTH_LONG);
                snackbar.show();
            }
        }
    }

    private  boolean checkAndRequestPermissions() {
        int permissionREAD_CONTACT = ContextCompat.checkSelfPermission(getContext(), Manifest.permission.READ_CONTACTS);
        int permissionWRITE_CONTACT = ContextCompat.checkSelfPermission(getContext(), Manifest.permission.WRITE_CONTACTS);
        List<String> listPermissionsNeeded = new ArrayList<>();
        if (permissionWRITE_CONTACT != PackageManager.PERMISSION_GRANTED) {
            listPermissionsNeeded.add(Manifest.permission.WRITE_CONTACTS);
        }
        if (permissionREAD_CONTACT != PackageManager.PERMISSION_GRANTED) {
            listPermissionsNeeded.add(Manifest.permission.READ_CONTACTS);
        }
        if (!listPermissionsNeeded.isEmpty()) {
            requestPermissions(listPermissionsNeeded.toArray(new String[listPermissionsNeeded.size()]),REQUEST_ID_MULTIPLE_PERMISSIONS);
            return false;
        }
        return true;
    }

}

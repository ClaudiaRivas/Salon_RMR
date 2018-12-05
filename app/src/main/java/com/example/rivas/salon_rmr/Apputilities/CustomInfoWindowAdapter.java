package com.example.rivas.salon_rmr.Apputilities;

import android.app.Activity;
import android.content.Context;
import android.content.ContextWrapper;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.rivas.salon_rmr.Activities.PrincipalActivity;
import com.example.rivas.salon_rmr.R;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.Marker;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

/**
 * Created by toni on 07/07/2017.
 */
public class CustomInfoWindowAdapter implements GoogleMap.InfoWindowAdapter {

    private LayoutInflater inflater;

    public CustomInfoWindowAdapter(LayoutInflater inflater) {
        this.inflater = inflater;
    }
    @Override
    public View getInfoContents(final Marker m) {
        //Carga layout personalizado
        View v = inflater.inflate(R.layout.info_windows_layout, null);
        ((TextView) v.findViewById(R.id.info_window_nombre)).setText(m.getTitle());
        String dir = (PrincipalActivity.txtDirection!=null)?PrincipalActivity.txtDirection:"Direccion no disponible";
        ((TextView) v.findViewById(R.id.info_window_direccion)).setText(dir);
        return v;
    }
    @Override
    public View getInfoWindow(Marker m) {
        return null;
    }

}
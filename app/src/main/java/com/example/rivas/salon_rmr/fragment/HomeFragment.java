package com.example.rivas.salon_rmr.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.view.animation.Animation;
import android.widget.ArrayAdapter;
import android.widget.ImageSwitcher;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.ViewSwitcher;

import com.example.rivas.salon_rmr.Principal;
import com.example.rivas.salon_rmr.Promociones;
import com.example.rivas.salon_rmr.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class HomeFragment extends Fragment {


    View view;
    private List<Promociones> MyPromocion = new ArrayList<Promociones>();
    private ImageSwitcher imageSwitcher;
    private int[] galeria = {R.drawable.p, R.drawable.p1, R.drawable.p2, R.drawable.p4};
    private int posicion;
    private static final int DURACION = 9000;
    private Timer timer = null;
    private List<Principal> myPrincipal = new ArrayList<Principal>();
    private TextView TxtTema;
    private TextView TxtSubTema;
    private ImageView ImagenPromociones;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_home,container,false);

        imageSwitcher = (ImageSwitcher) view.findViewById(R.id.imageSwitcher);
        imageSwitcher.setFactory(new ViewSwitcher.ViewFactory() {
            public View makeView() {
                ImageView imageView = new ImageView(getContext());
                imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);

                return imageView;
            }
        });

        Animation fadeIn = AnimationUtils.loadAnimation(getContext(), R.anim.fade_in);
        Animation fadeOut = AnimationUtils.loadAnimation(getContext(), R.anim.fade_out);
        imageSwitcher.setInAnimation(fadeIn);
        imageSwitcher.setOutAnimation(fadeOut);



        //Creamos los metodos para poder Crear las diferentes promociones
        PromocionesMes();
        PromocionesView();

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();

        timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            public void run() {
                getActivity().runOnUiThread(new Runnable() {
                    public void run() {
                        imageSwitcher.setImageResource(galeria[posicion]);
                        posicion++;
                        if (posicion == galeria.length)
                            posicion = 0;
                    }
                });
            }
        }, 0, DURACION);

    }


    @Override
    public void onPause() {
        super.onPause();
        timer.cancel();

    }

    private void PromocionesMes() {
        MyPromocion.add(new Promociones("Pistoleado", "La definición de pistolear en el diccionario castellano es sablear.", R.drawable.portada));
        MyPromocion.add(new Promociones("Pistoleado", "La definición de pistolear en el diccionario castellano es sablear.", R.drawable.portada1));
        MyPromocion.add(new Promociones("Pistoleado", "La definición de pistolear en el diccionario castellano es sablear.", R.drawable.portada2));
        MyPromocion.add(new Promociones("Pistoleado", "La definición de pistolear en el diccionario castellano es sablear.", R.drawable.portada3));
        MyPromocion.add(new Promociones("Pistoleado", "La definición de pistolear en el diccionario castellano es sablear.", R.drawable.portada));
    }

    //Creacion de Nuestro adapter con el metodo llamado PROMOCIONESVIEW
    private void PromocionesView() {
        ArrayAdapter<Promociones> adapter = new MyListAdapter(getContext(),MyPromocion);
        ListView list = (ListView) view.findViewById(R.id.listview);
        list.setAdapter(adapter);
    }

    // Hacemos el metodo MyListAdapter
    private class MyListAdapter extends ArrayAdapter<Promociones> {

        public MyListAdapter(@NonNull Context context,  @NonNull List<Promociones> objects) {
            super(context, 0, objects);
        }

        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            View itemView = convertView;
            if (itemView == null)
                itemView = getLayoutInflater().inflate(R.layout.item_promociones, parent, false);

            //CurrentPromociones es la posicion en la que vamos a estar
            Promociones CurrentPromociones = MyPromocion.get(position);
            ImageView imageView = (ImageView) itemView.findViewById(R.id.ImagenPromociones);
            imageView.setImageResource(CurrentPromociones.getImagenesPro());

            TextView TemaTxt = (TextView) itemView.findViewById(R.id.TxtTema);
            TemaTxt.setText(CurrentPromociones.getTema());

            TextView SubTemaTxt = (TextView) itemView.findViewById(R.id.TxtSubtema);
            SubTemaTxt.setText(CurrentPromociones.getSubtema());
            return itemView;
        }

    }
}

package com.example.rivas.salon_rmr.Fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
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
import android.widget.Toast;
import android.widget.ViewSwitcher;

import com.example.rivas.salon_rmr.Activities.MainActivity;
import com.example.rivas.salon_rmr.Model.Promocion;
import com.example.rivas.salon_rmr.R;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;
import com.smarteist.autoimageslider.SliderLayout;
import com.smarteist.autoimageslider.SliderView;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class HomeFragment extends Fragment {


    View view;

    SliderLayout sliderLayout;

    private ImageSwitcher imageSwitcher;
    private int[] galeria = {R.drawable.p, R.drawable.p1, R.drawable.p2, R.drawable.p4};
    private int posicion;
    private static final int DURACION = 9000;
    private Timer timer = null;

    //firebase
    CollectionReference dbPromociones;
    //adaptador
    AdaptadorPromocion adaptadorPromocion;
    //lista
    private List<Promocion> listaPromociones = new ArrayList<Promocion>();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_home,container,false);

       //instancia al listview
        ListView list = (ListView) view.findViewById(R.id.listview);
        //hacer instancia a la bd en firebase
        dbPromociones = FirebaseFirestore.getInstance().collection("promociones");
        //crear adaptador
        adaptadorPromocion = new AdaptadorPromocion(getContext(),listaPromociones);
        //establecer el adaptador a la listview
        list.setAdapter(adaptadorPromocion);
        /**
         * Registra cambios en la bd mediante el evento Document Changed
         */
        dbPromociones.addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@javax.annotation.Nullable QuerySnapshot queryDocumentSnapshots, @javax.annotation.Nullable FirebaseFirestoreException e) {
                actualizarDatos(queryDocumentSnapshots.getDocumentChanges());
            }
        });
        //animaciones();

        sliderLayout =  view.findViewById(R.id.imageSwitcher);

        setSliderViews();

        return view;
    }


    private void setSliderViews() {

        for (int i = 0; i <= 3; i++) {

            SliderView sliderView = new SliderView(getContext());

            switch (i) {
                case 0:
                    sliderView.setImageDrawable(R.drawable.p);
                    break;
                case 1:
                    sliderView.setImageDrawable(R.drawable.p1);
                    break;
                case 2:
                    sliderView.setImageDrawable(R.drawable.p2);
                    break;
                case 3:
                    sliderView.setImageDrawable(R.drawable.p4);
                    break;
            }

            sliderView.setImageScaleType(ImageView.ScaleType.FIT_XY);
            //sliderView.setDescription("setDescription " + (i + 1));
            final int finalI = i;

            //at last add this view in your layout :
            sliderLayout.addSliderView(sliderView);
        }
    }


    private void animaciones(){
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
    }

    private void actualizarDatos(List<DocumentChange> cambios) {
        //para cada documento cambiado
        for(DocumentChange document_changed: cambios){
            //obtengo el documento
            DocumentSnapshot document = document_changed.getDocument();
            //obtengo la posicion del producto basado en el Id
            int posicion = posicionPromocion(document.getId());
            //si el documento fue eliminado
            if(document_changed.getType()==DocumentChange.Type.REMOVED){
                //se elimina de la lista tambien
                listaPromociones.remove(posicion);
            }else {
                //obtengo un objeto promocion del documento
                Promocion promocion = getPromocion(document);
                //si la posicion es mayor a cero es por que existe en la lista y se actualiza
                if (posicion >= 0) {
                    listaPromociones.set(posicion, promocion);
                } else {
                    //si no , es por que es un elemento nuevo
                    listaPromociones.add(promocion);
                }
            }
            Log.d("LISTA FIREBASE","Actualizada "+document.getId()+ " "+document.getData().values());
        }
        //notifico al adaptador de los cambios
        adaptadorPromocion.notifyDataSetChanged();
        Toast.makeText(getContext(), "Datos actualizados", Toast.LENGTH_SHORT).show();
    }

    private Promocion getPromocion(DocumentSnapshot document) {
        Promocion promocion = new Promocion();
        //establecer parametros
        promocion.setId( document.getId()  );
        promocion.setNombre( document.getString("nombre")  );
        promocion.setDescripcion(  document.getString("descripcion")  );
        promocion.setPrecio( document.getString("precio") );
        return promocion;
    }

    private int posicionPromocion(String id) {
        for(Promocion promocion : listaPromociones) {
            if(promocion.getId().equals(id)) {
                return listaPromociones.indexOf(promocion);
            }
        }
        return -1;
    }

    @Override
    public void onResume() {
        super.onResume();
    }


    @Override
    public void onPause() {
        super.onPause();

    }

    // Hacemos el metodo AdaptadorServicio
    private class AdaptadorPromocion extends ArrayAdapter<Promocion> {

        public AdaptadorPromocion(@NonNull Context context, @NonNull List<Promocion> objects) {
            super(context, 0, objects);
        }

        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            View itemView = convertView;
            if (itemView == null)
                itemView = getLayoutInflater().inflate(R.layout.item_promociones, parent, false);

            //CurrentPromociones es la posicion en la que vamos a estar
            Promocion promocion = listaPromociones.get(position);
            ImageView imageView = (ImageView) itemView.findViewById(R.id.ImagenPromociones);
            imageView.setImageResource(R.drawable.service2);

            TextView TemaTxt = (TextView) itemView.findViewById(R.id.TxtTema);
            TemaTxt.setText(promocion.getNombre());

            TextView SubTemaTxt = (TextView) itemView.findViewById(R.id.TxtSubtema);
            SubTemaTxt.setText(promocion.getDescripcion());

            TextView PrecioTxt = (TextView) itemView.findViewById(R.id.TextPrecio);
            PrecioTxt.setText(promocion.getPrecio());
            return itemView;
        }

    }
}

package com.example.rivas.salon_rmr.Apputilities;

import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;

import com.example.rivas.salon_rmr.Model.Item;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.DocumentSnapshot;

import java.util.ArrayList;
import java.util.List;

public class FragmentConsultaFirebase extends BaseFragment {

    //adaptador
    protected RecyclerView.Adapter adaptadorItems;
    //lista
    protected ArrayList<Item> listaItems = new ArrayList<>();

    protected void actualizarDatos(List<DocumentChange> cambios) {
        //para cada documento cambiado
        for(DocumentChange document_changed: cambios){
            //obtengo el documento
            DocumentSnapshot document = document_changed.getDocument();
            //obtengo la posicion del producto basado en el Id
            int posicion = posicionItem(document.getId());
            //si el documento fue eliminado
            if(document_changed.getType()==DocumentChange.Type.REMOVED){
                //se elimina de la lista tambien
                listaItems.remove(posicion);
            }else {
                //obtengo un objeto promocion del documento
                Item item = getItem(document);
                //si la posicion es mayor a cero es por que existe en la lista y se actualiza
                if (posicion >= 0) {
                    listaItems.set(posicion, item);
                } else {
                    //si no , es por que es un elemento nuevo
                    listaItems.add(item);
                }
            }
        }
        //notifico al adaptador de los cambios
        if(adaptadorItems!=null){
            adaptadorItems.notifyDataSetChanged();
        }
    }

    protected int posicionItem(String id) {
        for(Item item : listaItems) {
            if(item.getId().equals(id)) {
                return listaItems.indexOf(item);
            }
        }
        return -1;
    }


    public Item getItem(DocumentSnapshot document) {
        Item item = new Item();
        //establecer parametros
        item.setId(document.getId());
        item.setNombre(document.getString("nombre"));
        item.setDescripcion(document.getString("descripcion"));
        item.setPrecio(document.getString("precio"));
        return item;
    }
}

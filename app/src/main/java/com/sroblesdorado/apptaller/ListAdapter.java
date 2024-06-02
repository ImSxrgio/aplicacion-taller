package com.sroblesdorado.apptaller;

import android.content.Context;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ListAdapter extends RecyclerView.Adapter<ListAdapter.ViewHolder>{

    private List<ListaElementos> mData;
    private LayoutInflater mInflater;
    private Context context;


    public ListAdapter(List<ListaElementos>listaElementos,Context context) {
        this.mInflater = LayoutInflater.from(context);
        this.context = context;
        this.mData = listaElementos;


    }


    @Override
    public int getItemCount() { return mData.size(); }

    @Override
    public ListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.elementos_lista, null);
        return new ListAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ListAdapter.ViewHolder holder, final int position) {
        holder.bindData(mData.get(position));
    }

    public void setItems(List<ListaElementos> items) { mData=items; }

    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView nombre;
        TextView precio;
        ImageView imagenProducto;
        TextView status;

        ViewHolder(View itemView) {
            super(itemView);
            nombre = itemView.findViewById(R.id.respuesto_txt);
            precio = itemView.findViewById(R.id.precio_txt);
            imagenProducto = itemView.findViewById(R.id.producto_faro);
            status = itemView.findViewById(R.id.status_txt);

        }

        void bindData(final ListaElementos item) {
            int resourceId = context.getResources().getIdentifier(String.valueOf(item.getImagenId()),"drawable", context.getPackageName());
            imagenProducto.setImageResource(resourceId);
            nombre.setText(item.getNombre());
            precio.setText(item.getPrecio());
            status.setText(item.getStatus());

        }
    }
}

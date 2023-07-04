package com.acenkzproject.myfuitlist;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class FruitGridAdapter extends RecyclerView.Adapter<FruitGridAdapter.ListViewHolder>{
    private ArrayList<Fruit> listFruit;
    private boolean ViewList;

    public FruitGridAdapter(ArrayList<Fruit> list, boolean ViewList){

        this.listFruit = list;
        this.ViewList = ViewList;
    }

    @NonNull
    @Override
    public ListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        int layoutId = ViewList ? R.layout.item_list_view : R.layout.item_grid_view;
        View view = LayoutInflater.from(parent.getContext()).inflate(layoutId, parent, false);
        return new ListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FruitGridAdapter.ListViewHolder holder, int position) {
        Fruit fruit = listFruit.get(position);
        Glide.with(holder.itemView.getContext())
                .load(fruit.getGambar())
                .circleCrop()
                .into(holder.imgFruit);
        holder.tvNama.setText(fruit.getNama());
        holder.TvDeskripsi.setText(fruit.getDeskripsi());
    }

    @Override
    public int getItemCount() {
        return listFruit.size();
    }


    public class ListViewHolder extends RecyclerView.ViewHolder{
        ImageView imgFruit;
        TextView tvNama, TvDeskripsi;
        public ListViewHolder(View itemView) {
            super(itemView);
            imgFruit = itemView.findViewById(R.id.img_item_photo);
            tvNama = itemView.findViewById(R.id.nama_buah);
            TvDeskripsi = itemView.findViewById(R.id.deskripsi);

        }
    }
}

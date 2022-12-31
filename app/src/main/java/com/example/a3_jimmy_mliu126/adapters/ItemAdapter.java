package com.example.a3_jimmy_mliu126.adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.a3_jimmy_mliu126.databinding.CustomRowLayoutBinding;
import com.example.a3_jimmy_mliu126.models.Equipment;

import java.util.ArrayList;

public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.ItemViewHolder> {

    private final Context context;
    private final ArrayList<Equipment> equipmentArrayList;
    CustomRowLayoutBinding binding;

    public ItemAdapter(Context context, ArrayList<Equipment> items){
        this.equipmentArrayList = items;
        this.context = context;
    }

    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ItemViewHolder(CustomRowLayoutBinding.inflate(LayoutInflater.from(context), parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ItemViewHolder holder, int position) {
        Equipment currentItem = equipmentArrayList.get(position);
        holder.bind(context, currentItem);
    }

    @Override
    public int getItemCount() {
        Log.d("ItemAdapter", "getItemCount: Number of items " +this.equipmentArrayList.size() );
        return this.equipmentArrayList.size();
    }

    public static class ItemViewHolder extends RecyclerView.ViewHolder{
        CustomRowLayoutBinding itemBinding;

        public ItemViewHolder(CustomRowLayoutBinding binding){
            super(binding.getRoot());
            this.itemBinding = binding;
        }

        public void bind(Context context, Equipment currentItem){
            itemBinding.tvName.setText(currentItem.getName().substring(0, 1).toUpperCase() + currentItem.getName().substring(1));
            itemBinding.tvAttack.setText("Attack: " + currentItem.getAttack());
            itemBinding.tvDefense.setText("Defense: " + currentItem.getDefense());
            itemBinding.tvDescription.setText(currentItem.getDescription());

            // image
            Glide.with(context).load(currentItem.getImage()).into(itemBinding.ivImage);
        }
    }
}
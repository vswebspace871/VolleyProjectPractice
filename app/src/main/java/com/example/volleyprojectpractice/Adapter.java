package com.example.volleyprojectpractice;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.volleyprojectpractice.databinding.RowItemBinding;

import java.util.ArrayList;

public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> {

    ArrayList<Model> list;

    public Adapter(ArrayList<Model> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public Adapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.row_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull Adapter.ViewHolder holder, int position) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("name: "+list.get(position).getName());
        holder.binding.tvName.setText(stringBuilder);
        holder.binding.tvEmail.setText("email: "+list.get(position).getEmail());
        holder.binding.tvCity.setText("city: "+list.get(position).getCity());
        holder.binding.tvPhone.setText("phone: "+list.get(position).getPhone());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        RowItemBinding binding;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            binding = RowItemBinding.bind(itemView);
        }
    }
}

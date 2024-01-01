package com.example.tabulator;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.ObservableArrayList;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tabulator.models.Tab;

public class MenuAdapter extends RecyclerView.Adapter<MenuAdapter.ViewHolder> {
    ObservableArrayList<Tab> entries;
    public interface OnTabClicked {
        public void onClick(Tab entry);
    }

    OnTabClicked listener;

    public MenuAdapter(ObservableArrayList<Tab> entries, OnTabClicked listener) {
        this.entries = entries;
        this.listener = listener;
    }

    @NonNull
    @Override
    public MenuAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.tab_list_entry, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        TextView textView = holder.itemView.findViewById(R.id.name);
        textView.setText(entries.get(position).name);
        holder.itemView.setOnClickListener(view -> {
            listener.onClick(entries.get(position));
        });
    }

    @Override
    public int getItemCount() {
        return entries.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public ViewHolder(@NonNull View itemView){
            super(itemView);
        }
    }
}

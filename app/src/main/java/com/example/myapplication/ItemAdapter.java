package com.example.myapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.RecyclerViewHolder>{
    Context context;
    LayoutInflater inflater;
    List<Item> items, selected;

    public class RecyclerViewHolder extends RecyclerView.ViewHolder {
        TextView tv;

        public RecyclerViewHolder(View itemView) {
            super(itemView);
            tv = (TextView) itemView.findViewById(R.id.tv);
        }
    }

    public ItemAdapter(Context context) {
        this.context = context;
        this.inflater = LayoutInflater.from(context);
        this.selected = new ArrayList<>();
        this.items = new ArrayList<>();
    }

    @Override
    public RecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = inflater.inflate(R.layout.recycler_view, parent, false);

        RecyclerViewHolder viewHolder = new RecyclerViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final RecyclerViewHolder holder, int position) {
        final Item item = items.get(position);
        holder.tv.setText(item.getTitle());
        holder.tv.setTag(holder);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (selected.contains(item)) {
                    selected.remove(item);
                    unhighlightView(holder);
                } else {
                    selected.add(item);
                    highlightView(holder);
                }
            }
        });

        if (selected.contains(item))
            highlightView(holder);
        else
            unhighlightView(holder);
    }

    private void highlightView(RecyclerViewHolder holder) {
        holder.itemView.setBackgroundColor(ContextCompat.getColor(context, R.color.colorPrimary));
    }

    private void unhighlightView(RecyclerViewHolder holder) {
        holder.itemView.setBackgroundColor(ContextCompat.getColor(context, android.R.color.transparent));
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public void addAll(List<Item> items) {
        clearAll(false);
        this.items = items;
        notifyDataSetChanged();
    }

    public void clearAll(boolean isNotify) {
        items.clear();
        selected.clear();
        if (isNotify) notifyDataSetChanged();
    }

    public void clearSelected() {
        selected.clear();
        notifyDataSetChanged();
    }

    public void selectAll() {
        selected.clear();
        selected.addAll(items);
        notifyDataSetChanged();
    }

    public List<Item> getSelected() {
        return selected;
    }
}
package com.shajt3ch.room_04;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.List;

public class RecViewAdapter extends RecyclerView.Adapter<RecViewAdapter.RecViewHolder> {

    // Create a list of Item type
    private List<Item> items = new ArrayList<>();


    // Create the view Holder
    @NonNull
    @Override
    public RecViewAdapter.RecViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.recview_card, viewGroup, false);
        return new RecViewHolder(itemView);
    }


    // Bind the data
    @Override
    public void onBindViewHolder(@NonNull RecViewAdapter.RecViewHolder recViewHolder, int position) {
        Item currentItem = items.get(position);
        recViewHolder.textViewId.setText(String.valueOf(currentItem.getItemId()));
        recViewHolder.textViewValue.setText(currentItem.getItemValue());

    }


    // Get the list count
    @Override
    public int getItemCount() {
        return items.size();
    }


    // Setter
    public void setItems(List<Item> items) {
        this.items = items;
        notifyDataSetChanged();
    }

    // Constructor?
    class RecViewHolder extends RecyclerView.ViewHolder {
        private TextView textViewId, textViewValue;

        public RecViewHolder(View itemView) {
            super(itemView);
            textViewId = itemView.findViewById(R.id.textViewId);
            textViewValue = itemView.findViewById(R.id.textViewValue);
        }
    }
}

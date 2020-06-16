package com.example.todoapplication;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class DoAdapter extends RecyclerView.Adapter<DoAdapter.MyViewHolder> {
    private ArrayList<Doing> doings;

    public DoAdapter(ArrayList<Doing> doings) {
        this.doings = doings;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView header;
        TextView doingText;
        MyViewHolder(View view){
            super(view);
            header = (TextView) view.findViewById(R.id.header);
            doingText = (TextView) view.findViewById(R.id.doing_text);
        }
    }

    @Override
    public DoAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.doing_item, parent, false);
        MyViewHolder vh = new MyViewHolder(v);
        return vh;

    }

    @Override
    public void onBindViewHolder(@NonNull DoAdapter.MyViewHolder holder, int position) {
        Doing doing = doings.get(position);
        holder.header.setText(doing.getHeader());
        holder.doingText.setText(doing.getText());
    }

    @Override
    public int getItemCount() {
        return doings.size();
    }
}

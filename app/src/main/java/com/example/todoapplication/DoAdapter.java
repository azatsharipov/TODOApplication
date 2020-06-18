package com.example.todoapplication;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class DoAdapter extends RecyclerView.Adapter<DoAdapter.MyViewHolder> {
    private ArrayList<Doing> doings;
    private boolean isSelectedMode;

    public DoAdapter(ArrayList<Doing> doings) {
        this.doings = doings;
    }

    public ArrayList<Integer> getSelected() {
        ArrayList<Integer> selected = new ArrayList<>();
        for (int i = 0; i < doings.size(); i++) {
            if (doings.get(i).getSelected())
                selected.add(i);
        }
        return selected;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView header;
        TextView doingText;
        CheckBox cb;
        MyViewHolder(View view) {
            super(view);
            header = (TextView) view.findViewById(R.id.header);
            doingText = (TextView) view.findViewById(R.id.doing_text);
            cb = (CheckBox) view.findViewById(R.id.doing_cb);
        }
    }

    @Override
    public DoAdapter.MyViewHolder onCreateViewHolder(final ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.doing_item, parent, false);
        v.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), EditActivity.class);
                TextView tv = (TextView)view.findViewById(R.id.header);
                String header = tv.getText().toString();
                intent.putExtra("HEADER", header);
                tv = (TextView)view.findViewById(R.id.doing_text);
                String doingText = tv.getText().toString();
                intent.putExtra("DOING", doingText);
                CheckBox cb = (CheckBox) view.findViewById(R.id.doing_cb);
                intent.putExtra("POSITION", (int)cb.getTag());
//                view.getContext().startActivity(intent);
                MainActivity ma = (MainActivity) view.getContext();
                ma.startActivityForResult(intent, 1);
            }
        });
        /*
        v.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                return true;
            }
        });
         */
        MyViewHolder vh = new MyViewHolder(v);
        return vh;

    }

    @Override
    public void onBindViewHolder(@NonNull DoAdapter.MyViewHolder holder, final int position) {
        Doing doing = doings.get(position);
        holder.header.setText(doing.getHeader());
        holder.doingText.setText(doing.getText());
        holder.cb.setChecked(doing.getSelected());
        holder.cb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                doings.get(position).setSelected(b);
            }
        });
        holder.cb.setTag(position);
    }

    @Override
    public int getItemCount() {
        return doings.size();
    }
}

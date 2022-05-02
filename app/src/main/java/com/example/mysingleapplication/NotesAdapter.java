package com.example.mysingleapplication;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class NotesAdapter extends RecyclerView.Adapter<NotesAdapter.MyHolder> {

    Context context;
    List<Notes> arrayList;

    public NotesAdapter(Context context, List<Notes> arrayList) {
        this.context = context;
        this.arrayList = arrayList;
    }



    public class MyHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView task,time;
        public MyHolder(@NonNull View itemView) {
            super(itemView);

            task = itemView.findViewById(R.id.custom_task);
            time = itemView.findViewById(R.id.custom_time);

            itemView.setOnClickListener((View.OnClickListener) this);


        }

        @Override
        public void onClick(View view) {
            Notes notes = arrayList.get(getAdapterPosition());
            Intent intent = new Intent(context,UpdateDeleteActivity.class);
            intent.putExtra("notes",notes);
            context.startActivity(intent);
        }
    }
    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.custom_note_layout,parent,false);
        return  new MyHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyHolder holder, int position) {

        Notes list = arrayList.get(position);
        holder.task.setText(list.getTask_name());
        holder.time.setText(list.getTask_time());



    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }









}

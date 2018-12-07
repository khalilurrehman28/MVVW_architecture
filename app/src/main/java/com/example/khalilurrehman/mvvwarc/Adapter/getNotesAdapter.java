package com.example.khalilurrehman.mvvwarc.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.khalilurrehman.mvvwarc.R;
import com.example.khalilurrehman.mvvwarc.tables.notes;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mandeep on 4/9/17.
 */

public class getNotesAdapter extends RecyclerView.Adapter<getNotesAdapter.MyViewHolder> {
    private Context mContext;
    private List<notes> notelist=new ArrayList<>();

    public notes getItemAtPosition(int adapterPosition) {
        return notelist.get(adapterPosition);
    }

    public class MyViewHolder extends RecyclerView.ViewHolder  {

        public TextView note_title,
                note_text;
        CardView note_cardview;

        public MyViewHolder(View vm) {
            super(vm);
            note_title = vm.findViewById(R.id.note_title);
            note_text = vm.findViewById(R.id.note_text);
            note_cardview = vm.findViewById(R.id.note_card);

        }
    }


    public getNotesAdapter(Context mContext) {
        this.mContext = mContext;
    }

    public void setList(List<notes> notes){
        this.notelist = notes;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_notice_item, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder holder, int position) {
        notes notesData = notelist.get(position);
        holder.note_title.setText(notesData.getTitle());
        holder.note_text.setText(notesData.getText());

    }

    @Override
    public int getItemCount() {
        return notelist.size();
    }


}

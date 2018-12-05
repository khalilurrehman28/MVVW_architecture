package com.example.khalilurrehman.mvvwarc.Adapter;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

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
    public class MyViewHolder extends RecyclerView.ViewHolder  {

        public TextView note_title,
                note_text,
                note_date;
        CardView note_cardview;

        public MyViewHolder(View view) {
            super(view);
            note_title = view.findViewById(R.id.note_title);
            note_text = view.findViewById(R.id.note_text);
            note_date = view.findViewById(R.id.note_date);
            note_cardview = view.findViewById(R.id.note_card);

        }
    }


    public getNotesAdapter(Context mContext) {
        this.mContext = mContext;
    }

    public void setList(List<notes> notes){
        this.notelist = notes;
        notifyDataSetChanged();
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_notice_item, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {
        notes notesData = notelist.get(position);
        holder.note_title.setText(notesData.getTitle());
        holder.note_text.setText(notesData.getText());

    }

    @Override
    public int getItemCount() {
        return notelist.size();
    }


}

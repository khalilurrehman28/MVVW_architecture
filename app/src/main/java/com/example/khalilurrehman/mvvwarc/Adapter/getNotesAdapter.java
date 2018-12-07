package com.example.khalilurrehman.mvvwarc.Adapter;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.DiffUtil;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.khalilurrehman.mvvwarc.R;
import com.example.khalilurrehman.mvvwarc.tables.notes;

/**
 * Created by mandeep on 4/9/17.
 */

public class getNotesAdapter extends ListAdapter<notes,getNotesAdapter.MyViewHolder> {

    public getNotesAdapter() {
        super(diffCallback);
    }

    public static final DiffUtil.ItemCallback<notes> diffCallback = new DiffUtil.ItemCallback<notes>() {
        @Override
        public boolean areItemsTheSame(@NonNull notes old, @NonNull notes newItem) {
            return old.getId()==newItem.getId();
        }

        @Override
        public boolean areContentsTheSame(@NonNull notes old, @NonNull notes newItem) {
            return old.getId()==newItem.getId() &&  old.getTitle().equals(newItem.getTitle()) && old.getText().equals(newItem.getText());
        }
    };

    public notes getItemAtPosition(int adapterPosition) {
        return getItem(adapterPosition);
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


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_notice_item, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder holder, int position) {
        notes notesData = getItem(position);
        holder.note_title.setText(notesData.getTitle());
        holder.note_text.setText(notesData.getText());

    }

}

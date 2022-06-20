package com.example.quizzapp.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupMenu;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.quizzapp.ActivityEditSoal;
import com.example.quizzapp.ActivitySoal;
import com.example.quizzapp.R;
import com.example.quizzapp.database.DBController;
import com.example.quizzapp.database.Quiz;

import java.util.ArrayList;
import java.util.HashMap;

public class SoalAdapter extends RecyclerView.Adapter<SoalAdapter.SoalViewHolder> {
    private ArrayList<Quiz> listData;
    private Context control;

    public SoalAdapter(ArrayList<Quiz> listData) {
        this.listData = listData;
    }

    @Override
    public SoalAdapter.SoalViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInf = LayoutInflater.from(parent.getContext());
        View view = layoutInf.inflate(R.layout.row_soal,parent,false);
        control = parent.getContext();
        return new SoalViewHolder(view);
    }

    @Override
    public void onBindViewHolder(SoalAdapter.SoalViewHolder holder, int position) {

        String sl, jwb, id;
        id = listData.get(position).getId();
        sl = listData.get(position).getSoal();
        jwb = listData.get(position).getJawaban();
        DBController db = new DBController(control);

        holder.soalTxt.setText(sl);
        holder.jawabanTxt.setText(jwb);
        holder.cardku.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                PopupMenu popupMenu = new PopupMenu(control, holder.cardku);
                popupMenu.inflate(R.menu.menu);
                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem menuItem) {
                        switch ((menuItem.getItemId())){
                            case R.id.mnEdit:
                                Intent i = new Intent(control, ActivityEditSoal.class);
                                i.putExtra("id",id);
                                i.putExtra("soal",sl);
                                i.putExtra("jawaban",jwb);
                                control.startActivity(i);
                                break;
                            case R.id.mnHapus:
                                HashMap<String,String> values = new HashMap<>();
                                values.put("id",id);
                                db.DeleteData(values);
                                Intent j = new Intent(control, ActivitySoal.class);
                                control.startActivity(j);
                                break;
                        }
                        return true;
                    }
                });
                popupMenu.show();
                return false;
            }
        });
    }

    @Override
    public int getItemCount() {
        return (listData != null)?listData.size() : 0;
    }

    public class SoalViewHolder extends RecyclerView.ViewHolder {
        private CardView cardku;
        private TextView soalTxt, jawabanTxt;
        public SoalViewHolder(View view) {
            super(view);
            cardku = (CardView) view.findViewById(R.id.kartuku);
            soalTxt = (TextView) view.findViewById(R.id.textSoal);
            jawabanTxt = (TextView) view.findViewById(R.id.textJwb);
        }
    }
}

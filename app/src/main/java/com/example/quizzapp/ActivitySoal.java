package com.example.quizzapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.quizzapp.adapter.SoalAdapter;
import com.example.quizzapp.database.DBController;
import com.example.quizzapp.database.Quiz;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Locale;

public class ActivitySoal extends AppCompatActivity {

    private RecyclerView rv;
    private SoalAdapter adapter;
    private FloatingActionButton fab;
    private ArrayList<Quiz> soalArrayList;
    DBController controller = new DBController(this);
    String id, soal, jwb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_soal);

        rv = (RecyclerView) findViewById(R.id.recyclerV);
        fab = (FloatingActionButton) findViewById(R.id.floatingBtn);
        BacaData();
        adapter = new SoalAdapter(soalArrayList);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(ActivitySoal.this);
        rv.setAdapter(adapter);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ActivitySoal.this,ActivitySoalBaru.class);
                startActivity(intent);
            }
        });
    }

    public void BacaData(){
        ArrayList<HashMap<String,String>> daftarSoal = controller.getAllSoal();
        soalArrayList = new ArrayList<>();

        for(int i=0;i<daftarSoal.size();i++){
            Quiz quiz = new Quiz();
            quiz.setId(daftarSoal.get(i).get("id").toString());
            quiz.setSoal(daftarSoal.get(i).get("soal").toString());
            quiz.setJawaban(daftarSoal.get(i).get("jawaban").toString());

            soalArrayList.add(quiz);
        }
    }
}
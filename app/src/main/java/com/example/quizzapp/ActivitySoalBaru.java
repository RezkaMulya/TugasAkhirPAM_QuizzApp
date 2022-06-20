package com.example.quizzapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.quizzapp.database.DBController;
import com.google.android.material.textfield.TextInputEditText;

import java.util.HashMap;

public class ActivitySoalBaru extends AppCompatActivity {
    private TextInputEditText tsoal, tjwb;
    private Button SaveBtn;
    String sl,jwb;
    DBController controller = new DBController(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_soal_baru);

        tsoal = (TextInputEditText) findViewById(R.id.tietSoal);
        tjwb = (TextInputEditText) findViewById(R.id.tietJawaban);
        SaveBtn = (Button) findViewById(R.id.buttonSave);

        SaveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(tsoal.getText().toString().equals("")||tjwb.getText().toString().equals("")){
                    Toast.makeText(getApplicationContext(),"Isi Semua Datanya!!!",Toast.LENGTH_LONG).show();
                } else {
                    sl = tsoal.getText().toString();
                    jwb = tjwb.getText().toString();

                    HashMap<String,String> qvalues = new HashMap<>();
                    qvalues.put("soal",sl);
                    qvalues.put("jawaban",jwb);

                    controller.insertData(qvalues);
                    callHome();
                }
            }
        });
    }

    public void callHome(){
        Intent intent = new Intent(ActivitySoalBaru.this, ActivitySoal.class);
        startActivity(intent);
        finish();
    }
}
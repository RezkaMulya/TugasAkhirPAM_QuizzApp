package com.example.quizzapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.quizzapp.database.DBController;

import java.util.HashMap;

public class ActivityEditSoal extends AppCompatActivity {
    EditText edsoal,edjwb;
    Button sv;
    String sl, jwb, id;
    DBController controller = new DBController(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_soal);

        edsoal = findViewById(R.id.edSoal);
        edjwb = findViewById(R.id.edJwb);
        sv = findViewById(R.id.simpanBtn);

        id = getIntent().getStringExtra("id");
        sl = getIntent().getStringExtra("soal");
        jwb = getIntent().getStringExtra("jawaban");

        edsoal.setText(sl);
        edjwb.setText(jwb);

        sv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(edsoal.getText().toString().equals("") || edjwb.getText().toString().equals("")){
                    Toast.makeText(getApplicationContext(), "Data Harus Diisi Semua!!", Toast.LENGTH_LONG).show();
                }else{
                    sl=edsoal.getText().toString();
                    jwb = edjwb.getText().toString();
                    HashMap<String,String> values = new HashMap<>();
                    values.put("id",id);
                    values.put("soal", sl);
                    values.put("jawaban", jwb);
                    controller.UpdateData(values);
                    callHome();
                }
            }
        });
    }

    public void callHome(){
        Intent i = new Intent(ActivityEditSoal.this, ActivitySoal.class);
        startActivity(i);
        finish();
    }
}
package com.example.quizzapp.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.HashMap;

public class DBController extends SQLiteOpenHelper {
    public DBController(Context context) {

        super(context, "myquizz", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("create table quiz (id integer primary key, soal text, jawaban text)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int neVersion) {
        sqLiteDatabase.execSQL("drop table if exists quiz");
        onCreate(sqLiteDatabase);

    }

    public void insertData(HashMap<String,String> queryValues){
        SQLiteDatabase basisdata = this.getWritableDatabase();
        ContentValues nilai = new ContentValues();
        nilai.put("soal",queryValues.get("soal"));
        nilai.put("jawaban",queryValues.get("jawaban"));
        basisdata.insert("quiz",null,nilai);
        basisdata.close();
    }

    public void UpdateData(HashMap<String,String>queryValues){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues nilai = new ContentValues();
        nilai.put("soal",queryValues.get("soal"));
        nilai.put("jawaban",queryValues.get("jawaban"));

        db.update("quiz",nilai,"id=?", new String[]{queryValues.get("id")});
        db.close();
    }

    public void DeleteData(HashMap<String,String>queryValues){
        SQLiteDatabase db = getWritableDatabase();
        db.delete("quiz","id=?",new String[]{queryValues.get("id")});
        db.close();

    }

    public ArrayList<HashMap<String,String>> getAllSoal(){
        ArrayList<HashMap<String,String>> daftarSoal;
        daftarSoal = new ArrayList<HashMap<String, String>>();
        String selectQuery = "Select * from quiz";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery,null);
        if(cursor.moveToFirst()){
            do{
                HashMap<String,String> map = new HashMap<>();
                map.put("id",cursor.getString(0));
                map.put("soal",cursor.getString(1));
                map.put("jawaban", cursor.getString(2));
                daftarSoal.add(map);
            }while(cursor.moveToNext());
        }
        db.close();
        return daftarSoal;
    }
}

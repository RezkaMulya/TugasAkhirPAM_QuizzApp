package com.example.quizzapp.database;

public class Quiz {
    String id;
    String soal;
    String jawaban;

    public Quiz() {
    }

    public Quiz(String id, String soal, String jawaban) {
        this.id = id;
        this.soal = soal;
        this.jawaban = jawaban;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSoal() {
        return soal;
    }

    public void setSoal(String soal) {
        this.soal = soal;
    }

    public String getJawaban() {
        return jawaban;
    }

    public void setJawaban(String jawaban) {
        this.jawaban = jawaban;
    }
}

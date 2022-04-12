package com.stevanuschristian.crudsqlitefootballplayer;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.concurrent.Callable;

public class MainActivity extends AppCompatActivity {
    private FloatingActionButton fabTambah;
    private RecyclerView rvPlayer;
    private MyDatabaseHelper myDB;
    private AdapterFootballPlayer adapterPlayer;
    private ArrayList<String> arrID, arrNama, arrNomor, arrKlub;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myDB = new MyDatabaseHelper(MainActivity.this);

        fabTambah = findViewById(R.id.fab_tambah);
        fabTambah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, TambahActivity.class));
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        tampilPlayer();
    }

    private void SQLiteToArrayList(){
        Cursor cursor = myDB.bacaDataPlayer();
        if (cursor.getCount() == 0){
            Toast.makeText(this, "Tidak ada data", Toast.LENGTH_SHORT).show();
        }
        else {
            while (cursor.moveToNext()){
                arrID.add(cursor.getString(0));
                arrNama.add(cursor.getString(1));
                arrNomor.add(cursor.getString(2));
                arrKlub.add(cursor.getString(3));
            }
        }
    }

    private void tampilPlayer(){
        arrID = new ArrayList<>();
        arrNama = new ArrayList<>();
        arrNomor = new ArrayList<>();
        arrKlub = new ArrayList<>();

        SQLiteToArrayList();

        rvPlayer = findViewById(R.id.rv_player);
        adapterPlayer = new AdapterFootballPlayer(MainActivity.this, arrID, arrNama, arrNomor, arrKlub);
        rvPlayer.setAdapter(adapterPlayer);
        rvPlayer.setLayoutManager(new LinearLayoutManager(MainActivity.this));
    }
}
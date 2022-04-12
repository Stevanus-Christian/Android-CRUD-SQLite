package com.stevanuschristian.crudsqlitefootballplayer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class UbahActivity extends AppCompatActivity {
    private EditText etNama, etNomor, etKlub;
    private Button btnUbah;
    private String id, nama, nomor, klub;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ubah);

        etNama = findViewById(R.id.et_nama);
        etNomor = findViewById(R.id.et_nomor);
        etKlub = findViewById(R.id.et_klub);
        btnUbah = findViewById(R.id.btn_ubah);

        Intent intent = getIntent();
        id = intent.getStringExtra("vID");
        nama = intent.getStringExtra("vNama");
        nomor = intent.getStringExtra("vNomor");
        klub = intent.getStringExtra("vKlub");

        etNama.setText(nama);
        etNomor.setText(nomor);
        etKlub.setText(klub);

        btnUbah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String newNama = etNama.getText().toString();
                String newNomor = etNomor.getText().toString();
                String newKlub = etKlub.getText().toString();

                if(newNama.trim().equals("")){
                    etNama.setError("Nama harus diisi!");
                }else if(newNomor.trim().equals("")){
                    etNomor.setError("Nomor harus diisi!");
                }else if(newKlub.trim().equals("")){
                    etKlub.setError("Klub harus diisi!");
                }else{
                    MyDatabaseHelper myDB = new MyDatabaseHelper(UbahActivity.this);
                    long eksekusi = myDB.ubahPlayer(id, newNama, newNomor, newKlub);

                    if(eksekusi == -1){
                        Toast.makeText(UbahActivity.this, "Gagal merubah data player", Toast.LENGTH_SHORT).show();
                        etNama.requestFocus();
                    }
                    else{
                        Toast.makeText(UbahActivity.this, "Sukses merubah data player", Toast.LENGTH_SHORT).show();
                        finish();
                    }
                }
            }
        });
    }
}
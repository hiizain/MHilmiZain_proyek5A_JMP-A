package com.example.myapplicationpertemuan12a;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplicationpertemuan12a.helper.DBHelper;

public class DetailActivity extends AppCompatActivity {

    private DBHelper helper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        helper = new DBHelper(this);

        EditText etNama = findViewById(R.id.et_nama);
        EditText etAlamat = findViewById(R.id.et_alamat);
        Button bSimpan = findViewById(R.id.b_simpan);

        bSimpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nama = etNama.getText().toString();
                String alamat = etAlamat.getText().toString();
                if (nama.length()==0 && alamat.length()==0){
                    Toast.makeText(DetailActivity.this, "Nama/alamat tidak boleh kosong", Toast.LENGTH_SHORT).show();
                } else {
                    insert(nama, alamat);
                }
            }
        });
    }

    private void insert(String nama, String alamat){
        helper.insert(nama,alamat);
        Toast.makeText(this, "Data diproses", Toast.LENGTH_SHORT).show();
        finish();
    }
}
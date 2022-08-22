package com.example.myapplicationpertemuan12a;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.myapplicationpertemuan12a.adapter.PersonAdapter;
import com.example.myapplicationpertemuan12a.helper.DBHelper;
import com.example.myapplicationpertemuan12a.model.Person;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DBHelper helper = new DBHelper(this);

        FloatingActionButton fab = findViewById(R.id.floatingActionButton);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, DetailActivity.class);
                startActivity(intent);
            }
        });

        ArrayList<Person> persons = helper.getAllData();
//        for (Person p:persons){
//            Log.d("MainActivity", p.getId()+" "+p.getNama()+" "+p.getAlamat());
//        }

        ListView lvPerson = findViewById(R.id.lv_person);
        PersonAdapter adapter = new PersonAdapter(this, persons);
        lvPerson.setAdapter(adapter);

        lvPerson.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                Person p = persons.get(i);
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setMessage("Hapus data "+p.getNama()+"?");
                builder.setPositiveButton("Cancel", null);
                builder.setNegativeButton("Hapus", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        helper.delete(p.getId());
                        persons.remove(p);
                        adapter.notifyDataSetChanged();
                    }
                });
                builder.create().show();
                return false;
            }
        });
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        finish();
        startActivity(getIntent());
    }
}
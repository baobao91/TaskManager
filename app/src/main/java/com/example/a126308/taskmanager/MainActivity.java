package com.example.a126308.taskmanager;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    ListView lvTask;
    Button btnAdd;
    ArrayList<String> al;
    ArrayAdapter aa;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lvTask = (ListView)findViewById(R.id.ListViewTask);

        btnAdd = (Button)findViewById(R.id.buttonAdd);

        al = new ArrayList<String>();

        aa = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, al);
        lvTask.setAdapter(aa);

        DBHelper dbh = new DBHelper(MainActivity.this);
        al.clear();
        al.addAll(dbh.getAllTasks());
        dbh.close();

        aa.notifyDataSetChanged();

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, AddActivity.class);
                startActivityForResult(i,9);

            }
        });
    }

    protected void onActivityResult(int requestCode, int resultCode,
                                    Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK && requestCode == 9){
            DBHelper dbh = new DBHelper(MainActivity.this);
            al.clear();
            al.addAll(dbh.getAllTasks());
            dbh.close();

            aa.notifyDataSetChanged();
        }
    }
}

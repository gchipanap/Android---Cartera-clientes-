package com.example.clientwallet;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.clientwallet.DataBase.DataOpenHelper;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity
{
    private ListView dataList;
    private ArrayAdapter<String> adapter;
    private ArrayList<String> clients;

    private SQLiteDatabase connection;
    private DataOpenHelper dataOpenHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Intent intent = new Intent(MainActivity.this, NewClientActivity.class);
                startActivityForResult(intent, 0);
            }
        });

        update();
    }

    private void update()
    {
        dataList = (ListView) findViewById(R.id.dataList);
        clients = new ArrayList<String>();

        try
        {
            dataOpenHelper = new DataOpenHelper(this);
            connection = dataOpenHelper.getWritableDatabase();
            StringBuilder sql = new StringBuilder();
            sql.append("SELECT * FROM CLIENT");
            String sName;
            String sPhone;

            Cursor result = connection.rawQuery(sql.toString(), null);

            if(result.getCount() > 0)
            {
                result.moveToFirst();
                do
                {
                    sName = result.getString(result.getColumnIndex("NAME"));
                    sPhone = result.getString(result.getColumnIndex("PHONE"));
                    clients.add(sName + ": " + sPhone);
                }
                while(result.moveToNext());
            }

            adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, clients);
            dataList.setAdapter(adapter);
        }
        catch(Exception e)
        {
            AlertDialog.Builder dialog = new AlertDialog.Builder(this);
            dialog.setTitle("Aviso!");
            dialog.setMessage(e.getMessage());
            dialog.setNeutralButton("OK", null);
            dialog.show();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data)
    {
        update();
    }
}

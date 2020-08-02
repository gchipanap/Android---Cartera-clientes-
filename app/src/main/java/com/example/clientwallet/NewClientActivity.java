package com.example.clientwallet;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;

import com.example.clientwallet.DataBase.DataOpenHelper;

public class NewClientActivity extends AppCompatActivity
{
    private EditText editName;
    private EditText editAddress;
    private EditText editEmail;
    private EditText editPhone;

    private SQLiteDatabase connection;
    private DataOpenHelper dataOpenHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_client);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        editName = (EditText) findViewById(R.id.editName);
        editAddress = (EditText) findViewById(R.id.editAddress);
        editEmail = (EditText) findViewById(R.id.editEmail);
        editPhone = (EditText) findViewById(R.id.editPhone);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_activity_new_client, menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        int id = item.getItemId();
        switch(id)
        {
            case R.id.action_ok:
                if(checkFields())
                {
                    try
                    {
                        dataOpenHelper = new DataOpenHelper(this);
                        connection = dataOpenHelper.getWritableDatabase();
                        StringBuilder sql = new StringBuilder();
                        sql.append("INSERT INTO CLIENT (NAME, ADDRESS, EMAIL, PHONE) VALUES ('");
                        sql.append(editName.getText().toString().trim() + "', '");
                        sql.append(editAddress.getText().toString().trim() + "', '");
                        sql.append(editEmail.getText().toString().trim() + "', '");
                        sql.append(editPhone.getText().toString().trim() + "')");

                        connection.execSQL(sql.toString());
                        connection.close();

                        finish();
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
                else
                {
                    AlertDialog.Builder dialog = new AlertDialog.Builder(this);
                    dialog.setTitle("Aviso!");
                    dialog.setMessage("Existen Campos Vacios");
                    dialog.setNeutralButton("OK", null);
                    dialog.show();
                }
                break;

            case R.id.action_cancel:
                finish();
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    private boolean checkFields()
    {
        boolean state = true;
        if(editName.getText().toString().trim().isEmpty())
        {
            editName.requestFocus();
            state = false;
        }
        if(editAddress.getText().toString().trim().isEmpty())
        {
            editAddress.requestFocus();
            state = false;
        }
        if(editEmail.getText().toString().trim().isEmpty())
        {
            editEmail.requestFocus();
            state = false;
        }
        if(editName.getText().toString().trim().isEmpty())
        {
            editEmail.requestFocus();
            state = false;
        }
        return state;
    }
}

package com.example.cliff.androidlabs;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class TestToolbar extends AppCompatActivity {

    private EditText message;
    private Dialog customDialog;
    private String passedMessage = "Default message";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_toolbar);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, R.string.message, Snackbar.LENGTH_SHORT)
                        .setAction("Action", null).show();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_test_toolbar, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_weather) {
            Intent intent = new Intent(TestToolbar.this,WeatherForecast.class);
            startActivity(intent);
            return true;
        }
        if (id == R.id.action_add) {
            AlertDialog.Builder commentBuilder = new AlertDialog.Builder(this);
            View commentView = getLayoutInflater().inflate(R.layout.custom_dialog, null);
            message = commentView.findViewById(R.id.message);

            commentBuilder.setView(commentView);
            customDialog = commentBuilder.create();
            customDialog.show();
            return true;
        }
        if (id == R.id.action_message) {
            Snackbar.make(this.findViewById( R.id.action_message),passedMessage, Snackbar.LENGTH_SHORT)
                    .setAction("Action", null).show();
            return true;
        }
        if (id == R.id.action_about) {
            Toast.makeText(this,R.string.message,Toast.LENGTH_SHORT).show();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void Save(View view) {
        passedMessage = message.getText().toString();
        customDialog.dismiss();
    }
}

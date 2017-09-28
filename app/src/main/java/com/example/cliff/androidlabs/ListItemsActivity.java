package com.example.cliff.androidlabs;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.RadioGroup;
import android.widget.Switch;
import android.widget.Toast;

public class ListItemsActivity extends Activity {

    private static final String TAG = ListItemsActivity.class.getSimpleName();
    private static final int REQUEST_IMAGE_CAPTURE = 1;
    private ImageButton button_camera;
    private Switch switchs;
    private CheckBox checkBox;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_items);
        Log.i(TAG, "In onCreate()");


        button_camera = findViewById(R.id.button_camera);
        switchs = findViewById(R.id.switchs);
        checkBox = findViewById(R.id.checkBox);

        button_camera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dispatchTakePictureIntent();
            }
        });

        switchs.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                Toast.makeText(getApplicationContext(), isChecked ? R.string.switch_on : R.string.switch_off, Toast.LENGTH_LONG).show();
            }
        });

        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if (isChecked) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(ListItemsActivity.this);
                    builder.setMessage(R.string.dialog_message);
                    builder.setTitle(R.string.dialog_title)
                            .setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    Intent resultIntent = new Intent();
                                    resultIntent.putExtra("Response", "Here is my response");
                                    setResult(Activity.RESULT_OK, resultIntent);
                                    finish();
                                }
                            })
                            .setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {

                                }
                            })
                            .show();

                }
            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i(TAG, "In onResume()");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.i(TAG, "In onStart()");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.i(TAG, "In onPause()");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i(TAG, "In onStop()");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i(TAG, "In onDestroy()");
    }

    private void dispatchTakePictureIntent() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
        }
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == Activity.RESULT_OK) {
            Bitmap photo = (Bitmap) data.getExtras().get("data");
            button_camera.setImageBitmap(photo);
        }
    }
}

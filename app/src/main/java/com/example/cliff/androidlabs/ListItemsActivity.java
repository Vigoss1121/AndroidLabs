package com.example.cliff.androidlabs;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

public class ListItemsActivity extends Activity {

    private static final String TAG = ListItemsActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_items);
        Log.i(TAG,"In onCreate()");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i(TAG,"In onResume()");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.i(TAG,"In onStart()");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.i(TAG,"In onPause()");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i(TAG,"In onStop()");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i(TAG,"In onDestroy()");
    }
}

package com.cs442.mvarhola.l2assignment;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    int inc = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    protected void onStart() {
        super.onStart();
        inc++;
        Log.d("mvarhola: ",Integer.toString(inc)+": onStart() called.");
    }

    protected void onStop() {
        super.onStop();
        inc++;
        Log.d("mvarhola: ",Integer.toString(inc)+": onStop() called.");
    }

    protected void onResume() {
        super.onResume();
        inc++;
        Log.d("mvarhola: ",Integer.toString(inc)+": onResume() called.");
    }

    protected void onPause() {
        super.onPause();
        inc++;
        Log.d("mvarhola: ",Integer.toString(inc)+": onPause() called.");
    }

    protected void onDestroy() {
        super.onDestroy();
        inc++;
        Log.d("mvarhola: ",Integer.toString(inc)+": onDestroy() called.");
    }

    protected void onRestart() {
        super.onRestart();
        inc++;
        Log.d("mvarhola: ",Integer.toString(inc)+": onRestart() called.");
    }

    public void showSequence(View view){
        Toast.makeText(MainActivity.this,
                "Sequence #: "+Integer.toString(inc), Toast.LENGTH_LONG).show();
    }

}

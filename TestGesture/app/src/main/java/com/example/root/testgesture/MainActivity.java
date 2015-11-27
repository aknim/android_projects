package com.example.root.testgesture;

import android.support.v4.view.MotionEventCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event){

        int action = MotionEventCompat.getActionMasked(event);

        switch(action){
            case(MotionEvent.ACTION_DOWN):
                Toast.makeText(MainActivity.this, "Moved hand Down", Toast.LENGTH_SHORT).show();
                break;
            case(MotionEvent.ACTION_UP):
                Toast.makeText(MainActivity.this, "moved hand Up", Toast.LENGTH_SHORT).show();
                break;
            default:
                break;
        }
        return true;
    }
}

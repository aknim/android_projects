package com.example.root.testbutton;

import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    private EditText text;
    private FloatingActionButton edit;
    private boolean wasEditable = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setupViews();
    }

    private void setupViews() {
        text = (EditText)(findViewById(R.id.text));
        edit = (FloatingActionButton)(findViewById(R.id.edit));
        edit.setEnabled(false);
        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!wasEditable){
                    Log.d("debug", "now Editable");
                    text.setEnabled(true);
                    wasEditable = !wasEditable;
                }
                else{
                    Log.d("debug", "now not Editable");
                    text.setEnabled(false);
                    wasEditable = !wasEditable;
                }
            }
        });
    }
}

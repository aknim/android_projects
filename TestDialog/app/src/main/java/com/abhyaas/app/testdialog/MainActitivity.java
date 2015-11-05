package com.abhyaas.app.testdialog;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.NumberPicker;
import android.widget.Toast;


public class MainActitivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_actitivity);
        setupView();
    }

    private void setupView() {
        Button openDialog = (Button)(findViewById(R.id.openDialog));
        openDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Dialog dialog = new Dialog(MainActitivity.this);
                dialog.setContentView(R.layout.dialog);
                dialog.setTitle("The dialog!");

                dialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
                    @Override
                    public void onDismiss(DialogInterface dialog) {
                        Toast toast = Toast.makeText(getBaseContext(), "Dialog box dismissed", Toast.LENGTH_SHORT);
                        toast.show();
                    }
                });


                //This won't work, need to access by dialog.findViewById
                //Button dialogButton = (Button)(findViewById(R.id.dialogOK));
                Button dialogButton = (Button)(dialog.findViewById(R.id.dialogOK));
                dialogButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast toast = Toast.makeText(getBaseContext(), "Dialog's ok button clicked, via dialog.findViewById", Toast.LENGTH_SHORT);
                        toast.show();
                    }
                });
                dialog.show();
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main_actitivity, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}

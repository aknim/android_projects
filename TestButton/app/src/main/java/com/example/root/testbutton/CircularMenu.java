package com.example.root.testbutton;

import com.oguzdev.circularfloatingactionmenu.library.*;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

public class CircularMenu extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_circular_menu);
        setupViews();
    }

    private void setupViews() {
        //creating icon for fab
        ImageView icon = new ImageView(this);
        icon.setImageDrawable(getResources().getDrawable(R.mipmap.ic_launcher));

        //creating button, note the import. Its not the google library one
        FloatingActionButton fab= new FloatingActionButton.Builder(this)
                                    .setContentView(icon)
                                    .build();

        //Create menu items
        ///Creating icons
        ///////
        /////// ITS IMPORTANT TO SET NEW ICONS, IN buttonBuilder.setContentView(icon1).build(); FOR EACH BUTTON,
        /////// OTHERWISE IT GIVES ERROR
        ///////
        ImageView icon1 = new ImageView(this);
        icon1.setImageDrawable(getResources().getDrawable(R.mipmap.ic_launcher));


        ImageView icon2 = new ImageView(this);
        icon2.setImageDrawable(getResources().getDrawable(R.mipmap.ic_launcher));

        SubActionButton.Builder buttonBuilder = new SubActionButton.Builder(this);

        SubActionButton button1 = buttonBuilder.setContentView(icon1).build();
        SubActionButton button2 = buttonBuilder.setContentView(icon2).build();

        //Create menu with the menu items
        FloatingActionMenu menu = new FloatingActionMenu.Builder(this)
                                    .addSubActionView(button1)
                                    .addSubActionView(button2)
                                    .attachTo(fab)
                                    .build();

    }
}

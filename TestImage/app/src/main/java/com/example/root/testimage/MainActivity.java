package com.example.root.testimage;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    private String selectedImagePath;
    private ImageView image;
    public enum cases {FROMURI, FROMSTRINGURI, FROMPATH};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setViews();
        startImage();
    }

    public void setViews() {
        image = (ImageView) findViewById(R.id.imageDisplay);
    }

    public void startImage(){

        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), 1);
    }
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        cases differentCase = cases.FROMURI;
        if (resultCode == RESULT_OK) {
            if (requestCode == 1) {
                Uri selectedImageUri = data.getData();
                switch (differentCase){
                    case FROMPATH ://working on API16, 15(small image), 10(small image)
                        //won't work for lollipop as in it getPath returns null
                        selectedImagePath = getPath(selectedImageUri);
                        Log.d("image path:", selectedImagePath);
                        Bitmap bmImg = BitmapFactory.decodeFile(selectedImagePath);
                        image.setImageBitmap(bmImg);
                        break;
                    case FROMSTRINGURI://tested on Nexus(img/print), API16, 15(small image), 10(small image)
                        String stringUri = selectedImageUri.toString();
                        Log.d("image stringuri:", stringUri);
                        image.setImageURI(Uri.parse(stringUri));
                        break;
                    case FROMURI://tested on lollypop(img/print), API16, 15(small image), 10(small image)
                        Log.d("image imageuri:",""+ selectedImageUri);
                        image.setImageURI(selectedImageUri);
                        break;
                }

            }
        }
    }

    public String getPath(Uri uri) {
        String[] projection = { MediaStore.Images.Media.DATA };
        Cursor cursor = getContentResolver().query(uri, projection, null, null, null);
        int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
        cursor.moveToFirst();
        return cursor.getString(column_index);
    }
}

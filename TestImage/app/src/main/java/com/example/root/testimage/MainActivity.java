package com.example.root.testimage;

import android.annotation.TargetApi;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
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
    private int api;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        api = Build.VERSION.SDK_INT;
        Log.d("debug", ""+ api);
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
        Bitmap bmImg;
        if (resultCode == RESULT_OK) {
            if (requestCode == 1) {
                Uri selectedImageUri = data.getData();
                selectedImagePath = (api<19)?getPath(selectedImageUri):getPathLollipop(selectedImageUri);
                Log.d("image path:", selectedImagePath);
                bmImg = BitmapFactory.decodeFile(selectedImagePath);
                image.setImageBitmap(bmImg);
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

    @TargetApi(19)
    public String getPathLollipop(Uri uri){
        String wholeID = DocumentsContract.getDocumentId(uri);
        String id = wholeID.split(":")[1];
        String[] column = { MediaStore.Images.Media.DATA };
        String sel = MediaStore.Images.Media._ID + "=?";
        Cursor cursor = getContentResolver().query(
                MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                column,
                sel,
                new String[]{ id },
                null);
        int columnIndex = cursor.getColumnIndex(column[0]);
        cursor.moveToFirst();
        selectedImagePath = cursor.getString(columnIndex);
        cursor.close();
        return selectedImagePath;
    }
}

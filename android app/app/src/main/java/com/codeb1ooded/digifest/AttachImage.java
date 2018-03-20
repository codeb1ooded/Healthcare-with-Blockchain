package com.codeb1ooded.digifest;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by megha on 02/12/17.
 */

public class AttachImage extends AppCompatActivity{

    int TAKE_PHOTO_CODE = 0;
    Button attachImage;
    String file_name;

    /** Called when the activity is first created. */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.upload_screen);

        // Here, we are making a folder named picFolder to store
        // pics taken by the camera using this application.
        final String dir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES) + "/digifest/";
        File newdir = new File(dir);
        newdir.mkdirs();

        attachImage = (Button) findViewById(R.id.attach);
        attachImage.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                String timeStamp = new SimpleDateFormat("ddMMyyyy_HHmmss").format(new Date());
                file_name = dir + "IMG_"+ timeStamp +".jpg";
                File newfile = new File(file_name);
                try {
                    newfile.createNewFile();
                }
                catch (IOException e) {}

                Log.e("AbsolutePath", newfile.getAbsolutePath());

                Uri outputFileUri = Uri.fromFile(newfile);

                Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, outputFileUri);

                startActivityForResult(cameraIntent, TAKE_PHOTO_CODE);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == TAKE_PHOTO_CODE && resultCode == RESULT_OK) {
            Log.d("CameraDemo", "Pic saved");
            Intent i = new Intent();
            i.putExtra("PICTURE_PATH", file_name);
            setResult(RESULT_OK , i);
            finish();
        }
    }
}

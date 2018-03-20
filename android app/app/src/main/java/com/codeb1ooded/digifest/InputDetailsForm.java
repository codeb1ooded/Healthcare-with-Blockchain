package com.codeb1ooded.digifest;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.util.Base64;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.codeb1ooded.digifest.networking.ApiService;
import com.google.gson.JsonObject;

import java.io.ByteArrayOutputStream;
import java.io.File;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by megha on 02/12/17.
 */

public class InputDetailsForm extends AppCompatActivity {

    EditText titleEditText, patientNameEditText, patientAgeEditText, patientSexEditText, othersEditText;
    ImageView attachedImage;
    Button submit, upload;
    int TAKE_PHOTO_CODE = 0;
    Call<EmptyClass> call;
    String imageInString;
    ProgressDialog progressDialog;
    int age;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.input_data);

        titleEditText = (EditText) findViewById(R.id.title);
        patientNameEditText = (EditText) findViewById(R.id.name);
        patientAgeEditText = (EditText) findViewById(R.id.age);
        patientSexEditText = (EditText) findViewById(R.id.sex);
        othersEditText = (EditText) findViewById(R.id.others);

        submit = (Button) findViewById(R.id.submit);
        upload = (Button) findViewById(R.id.upload);

        attachedImage = (ImageView) findViewById(R.id.image_report);

        upload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(InputDetailsForm.this, AttachImage.class);
                startActivityForResult(intent, TAKE_PHOTO_CODE);
            }
        });

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                progressDialog = new ProgressDialog(InputDetailsForm.this);
                progressDialog.setMessage("Loading...");

                if(!patientAgeEditText.getText().toString().equals("") && patientAgeEditText.getText().toString() != null)
                    age = Integer.parseInt(patientAgeEditText.getText().toString());

                JsonObject postParam = new JsonObject();
                postParam.addProperty("user", getSharedPreferences("Digifest", MODE_PRIVATE).getString("USERNAME", null)) ;
                postParam.addProperty("title", titleEditText.getText().toString()) ;
                postParam.addProperty("name", patientNameEditText.getText().toString()) ;
                postParam.addProperty("age", age) ;
                postParam.addProperty("sex", patientSexEditText.getText().toString()) ;
                postParam.addProperty("others", othersEditText.getText().toString()) ;
                postParam.addProperty("image", imageInString) ;

                call = ApiService.getInterface().addBlock(postParam);
                call.enqueue(new Callback<EmptyClass>() {
                    @Override
                    public void onResponse(Call<EmptyClass> call, Response<EmptyClass> response) {
                        progressDialog.cancel();
                        finish();
                    }

                    @Override
                    public void onFailure(Call<EmptyClass> call, Throwable t) {
                        progressDialog.cancel();
                        Toast.makeText(InputDetailsForm.this, "You are not connected to internet", Toast.LENGTH_SHORT).show();
                        finish();
                    }
                });

            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == TAKE_PHOTO_CODE && resultCode == RESULT_OK) {
            Log.d("Input_Details", data.getStringExtra("PICTURE_PATH"));
            imageInString = getStringFromBitmap(createBitmap(data.getStringExtra("PICTURE_PATH")));
            Log.e("ImageString", imageInString);
            Bitmap bitmap = getBitmapFromString(imageInString);
            attachedImage.setVisibility(View.VISIBLE);
            attachedImage.setImageBitmap(bitmap);
        }
    }

    private Bitmap createBitmap(String pathname){
        BitmapFactory.Options bmOptions = new BitmapFactory.Options();
        Bitmap bitmap = BitmapFactory.decodeFile(pathname);
        return bitmap;
    }

    private String getStringFromBitmap(Bitmap bitmapPicture) {
        final int COMPRESSION_QUALITY = 100;
        String encodedImage;
        ByteArrayOutputStream byteArrayBitmapStream = new ByteArrayOutputStream();
        bitmapPicture.compress(Bitmap.CompressFormat.PNG, COMPRESSION_QUALITY,
                byteArrayBitmapStream);
        byte[] b = byteArrayBitmapStream.toByteArray();
        encodedImage = Base64.encodeToString(b, Base64.DEFAULT);
        return encodedImage;
    }

    private Bitmap getBitmapFromString(String stringPicture) {
        byte[] decodedString = Base64.decode(stringPicture, Base64.DEFAULT);
        Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
        return decodedByte;
    }
}

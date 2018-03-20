package com.codeb1ooded.digifest;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by megha on 03/12/17.
 */

public class RecordDetailsActivity extends AppCompatActivity {
    
    TextView titleTextView, patientNameTextView, patientAgeTextView, patientSexTextView, othersTextView;
    ImageView attachedImage;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_record_detail);

        titleTextView = (TextView) findViewById(R.id.title);
        patientNameTextView = (TextView) findViewById(R.id.name);
        patientAgeTextView = (TextView) findViewById(R.id.age);
        patientSexTextView = (TextView) findViewById(R.id.sex);
        othersTextView = (TextView) findViewById(R.id.others);
        attachedImage = (ImageView) findViewById(R.id.image_report);

        BlockData data = (BlockData) getIntent().getSerializableExtra("BlockData");
        titleTextView.setText(data.getTitle());
        patientNameTextView.setText(data.getName());
        patientAgeTextView.setText(data.getAge());
        patientSexTextView.setText(data.getSex());
        othersTextView.setText(data.getOthers());
        if(data.getImage() != null)
            attachedImage.setImageBitmap(createBitmap(data.getImage()));
    }

    private Bitmap createBitmap(String pathname){
        BitmapFactory.Options bmOptions = new BitmapFactory.Options();
        Bitmap bitmap = BitmapFactory.decodeFile(pathname);
        return bitmap;
    }
}

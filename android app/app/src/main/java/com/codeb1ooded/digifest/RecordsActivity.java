package com.codeb1ooded.digifest;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.codeb1ooded.digifest.networking.ApiService;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by megha on 03/12/17.
 */

public class RecordsActivity extends AppCompatActivity {

    FloatingActionButton addNewRecord;
    ListView recordsListView;
    RecordsAdapter adapter;
    ArrayList<BlockData> datalist;
    Call<RecordModel> call;

    ProgressDialog progressDialog;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_records);
        recordsListView = (ListView) findViewById(R.id.records_list_view);
        datalist = new ArrayList<>();
        BlockData data = new BlockData();
        data.setTitle("Cancer Reports");
        datalist.add(data);
        data = new BlockData();
        data.setTitle("Diabetese Reports");
        datalist.add(data);
        data = new BlockData();
        data.setTitle("Parkinson Reports");
        datalist.add(data);
        data = new BlockData();
        data.setTitle("Cancer Reports Recent");
        datalist.add(data);
        adapter = new RecordsAdapter(RecordsActivity.this, datalist);
        recordsListView.setAdapter(adapter);

        addNewRecord = (FloatingActionButton) findViewById(R.id.add_record);
        addNewRecord.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(RecordsActivity.this, InputDetailsForm.class);
                startActivity(intent);
            }
        });

        progressDialog = new ProgressDialog(RecordsActivity.this);
        progressDialog.setMessage("Loading...");
        call = ApiService.getInterface().getDetails(getSharedPreferences("Digifest", MODE_PRIVATE).getString("USERNAME", null));
        call.enqueue(new Callback<RecordModel>() {
            @Override
            public void onResponse(Call<RecordModel> call, Response<RecordModel> response) {
                progressDialog.cancel();
                if (response.isSuccessful()) {
                    datalist.addAll(response.body().data);
                } else {
                    Toast.makeText(RecordsActivity.this, response.code() + response.message(), Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<RecordModel> call, Throwable t) {
                progressDialog.cancel();
                Toast.makeText(RecordsActivity.this, "You are not connected to internet", Toast.LENGTH_SHORT).show();
            }
        });

    }

}

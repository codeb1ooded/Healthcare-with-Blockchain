package com.codeb1ooded.digifest;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Button loginButton;
    EditText username;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        loginButton = (Button) findViewById(R.id.login);
        username = (EditText) findViewById(R.id.username);
        sharedPreferences = getSharedPreferences("Digifest", MODE_PRIVATE);
        editor = sharedPreferences.edit();
        if(sharedPreferences.getBoolean("LOGGED_IN", false)){
            Intent intent = new Intent(MainActivity.this, RecordsActivity.class);
            startActivity(intent);
        }
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(username.getText().toString().equals("username")){
                    editor.putBoolean("LOGGED_IN", true);
                    editor.putString("USERNAME", username.getText().toString());
                    editor.commit();
                    Intent intent = new Intent(MainActivity.this, RecordsActivity.class);
                    startActivity(intent);
                }
                else{
                    Toast.makeText(MainActivity.this, "Invalid Login", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}

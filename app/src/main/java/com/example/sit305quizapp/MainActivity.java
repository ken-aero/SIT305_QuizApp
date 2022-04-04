package com.example.sit305quizapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button startButton = findViewById(R.id.btnStart);
        EditText inputName = findViewById(R.id.editTextName);

        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = inputName.getText().toString();
                if (!name.isEmpty()) {
                    Intent intent = new Intent(getApplicationContext(), Questions.class);
                    intent.putExtra("name", name);
                    startActivityForResult(intent, 1);
                    finish();
                } else {
                    Toast.makeText(MainActivity.this, "Please enter a name to start the quiz", Toast.LENGTH_SHORT ).show();
                }
            }
        });
    }
}

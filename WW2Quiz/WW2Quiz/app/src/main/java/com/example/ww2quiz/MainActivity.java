package com.example.ww2quiz;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.content.Intent;
import android.view.View;

public class MainActivity extends AppCompatActivity {
    private Button buttonOpenQZ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button buttonOpenQZ = (Button) findViewById(R.id.strtQZ);
        //Constructor to open the quiz activity
        buttonOpenQZ.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentQuiz = new Intent(MainActivity.this, quizStart.class);
                startActivity(intentQuiz);
            }
        });
    }
}
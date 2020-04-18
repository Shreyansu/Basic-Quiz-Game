package com.example.quiz20;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.strictmode.IntentReceiverLeakedViolation;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

public class ResultActivity extends AppCompatActivity {
    private Button back;
    private TextView scratch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        back = (Button)findViewById(R.id.Back);
        scratch = (TextView)findViewById(R.id.scratch);

        TextView resultLabel = (TextView)findViewById(R.id.resultLabel);
        TextView scorelabel  = (TextView)findViewById(R.id.totalScoreLabel);

        int Score = getIntent().getIntExtra("RIGHT_ANSWER_COUNT",0);

        SharedPreferences settings = getSharedPreferences("quizApp", Context.MODE_PRIVATE);
        int totalScore = settings.getInt("totalScore",0);
        totalScore += Score*10;



        resultLabel.setText(Score +"/10");
        scorelabel.setText("TotalScore:" + totalScore);
        totalScore=0;

        SharedPreferences.Editor editor = settings.edit();
        editor.putInt("totalScore", totalScore);
        editor.commit();
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                startActivity(intent);


            }
        });
        scratch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ResultActivity.this,ScratchActivity.class);
                startActivity(intent);
            }
        });


    }
}

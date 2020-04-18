package com.example.quiz20;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Toast;

import com.anupkumarpanwar.scratchview.ScratchView;

public class ScratchActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scratch);
        ScratchView scratchView = findViewById(R.id.scratchView);
        scratchView.setRevealListener(new ScratchView.IRevealListener() {
            @Override
            public void onRevealed(ScratchView scratchView)
            {
                Toast.makeText(ScratchActivity.this,"Revealed",Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onRevealPercentChangedListener(ScratchView scratchView, float percent) {

            }
        });
    }
}

package com.example.quiz20;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private TextView countlabel,questionLabel;
    private Button btn1,btn2,btn3,btn4;

    private String rightAnswer;
    private int rightAnswerCount = 0;
    private int quizCount = 1;
    static final private int QUIZ_COUNT = 10;

    ArrayList<ArrayList<String>> quizArray = new ArrayList<>();
    String quizData[][] = {
            //"question","right answer","choice1","choice2","choice3"
            {"Capital of India?","delhi","Mumbai","Patna","Kolkata"},
            {"Capital Of China","Beijing","Wuhan","jakarta","Stockholm"},
            {"Capital of Japan","tokyo","Jakarta","taipei","bangkok"},
            {"Choose the right option","Mississippi","Misissippi","Missisippi","Missisipi"},
            {"The World Largest desert is ?","Sahara","Thar","Kalahari","Sohoran"},
            {"Country that has the highest in Barley Production ?","Russia","China","India","France"},
            {"Mount Everest is located in ?","Nepal","China","India","Tibet"},
            {"Which soil is suitable for agriculture ?","peaty Soil","Sand","Red soil","Black Soil"},
            {"The Gate way of India is ?","Mumbai","Delhi","Kolkata","Hyderabad"},
            {"Which is considered as the biggest port of India ?","Mumbai","Cochin","Kolkata","Chennai"},
            {"The state which has desert in India is ?","Rajasthan","Madhya Pradesh","Uttar Pradesh","Punjab"},
            {"The largest river in India is ?","Ganga","Brahmaputra","Kaveri","Yamuna"},
            {"Which city is famous for Cotton Industry in TamilNadu ?","Coimbatore","Madurai","Trichy","Chennai"},
            {"The term“Googly” is associated with ?","Cricket","Badminton","Tennis","Football"},
            {" Who are Kangaroos ?","Australia","NewZealand","kenya","Zimbabwe"},
            {"Viswanath Anand is associated with ?","Chess","Hockey","Carrom","Cricket"},
            {"Eden Gardens in Kolkata is ----- stadium.?","Cricket","Football","tennis","Polo"},
            {" When is the Earth Day celebrated?","22nd April","21st May","8th October","24th November"}
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        countlabel = (TextView)findViewById(R.id.countLabel);
        questionLabel= (TextView)findViewById(R.id.questionLabel);
        btn1=(Button)findViewById(R.id.answerBtn1);
        btn2=(Button)findViewById(R.id.answerBtn2);
        btn3=(Button)findViewById(R.id.answerBtn3);
        btn4=(Button)findViewById(R.id.answerBtn4);

        for(int i=0; i < quizData.length;i++)
        {
            ArrayList<String> tmpArray = new ArrayList<>();
            tmpArray.add(quizData[i][0]);
            tmpArray.add(quizData[i][1]);
            tmpArray.add(quizData[i][2]);
            tmpArray.add(quizData[i][3]);
            tmpArray.add(quizData[i][4]);

            quizArray.add(tmpArray);

        }
        showNextQuiz();

    }

    private void showNextQuiz()
    {
        countlabel.setText("Q" +quizCount);

        Random random = new Random();
        int randumNum = random.nextInt(quizArray.size());

        ArrayList<String> quiz = quizArray.get(randumNum);

        questionLabel.setText(quiz.get(0));
        rightAnswer = quiz.get(1);

        quiz.remove(0);
        Collections.shuffle(quiz);

        btn1.setText(quiz.get(0));
        btn2.setText(quiz.get(1));
        btn3.setText(quiz.get(2));
        btn4.setText(quiz.get(3));

        quizArray.remove(randumNum);
    }


    public void checkAnswer(View view)
    {
        Button answerBtn = (Button)findViewById(view.getId());
        String btnText = answerBtn.getText().toString();
        String AlertTitle;

        if(btnText.equals(rightAnswer))
        {
            AlertTitle  = "Correct";
            rightAnswerCount++;
        }
        else
        {
            AlertTitle = "Wrong";
        }
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(AlertTitle);
        builder.setMessage("Answer :" + rightAnswer);

        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which)
            {
                if(quizCount ==QUIZ_COUNT)
                {

                    Intent intent = new Intent(getApplicationContext(),ResultActivity.class);
                    intent.putExtra("RIGHT_ANSWER_COUNT",rightAnswerCount);
                    startActivity(intent);


                }
                else
                {
                    quizCount++;
                    showNextQuiz();
                }

            }
        });
        builder.setCancelable(false);
        builder.show();
    }

}

package com.example.ww2quiz;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class quizStart extends AppCompatActivity {

     //This class outputs the question on screen, checks if they're correct, shows next question, updates score, timer and also colour codes answer when result submitted.

    private List<QuestionList> questionLst;
    private TextView tQuestion, tScore, tQuestionNo, tTimer;
    private RadioGroup rdGroup;
    private RadioButton r1, r2, r3, r4;
    private Button btnNext;

    int totalQuestion;
    int questCounter = 0;
    int score;

    ColorStateList dfRBColor;
    boolean answered;

    CountDownTimer countDwnTimer;

    private QuestionList crntQuestion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz_start);

        //Creates new list via the arraylist class.
        questionLst = new ArrayList<>();
        tQuestion = findViewById(R.id.question);
        tScore = findViewById(R.id.score);
        tQuestionNo = findViewById(R.id.questionNo);
        tTimer = findViewById(R.id.timer);

        rdGroup = findViewById(R.id.rdgp);
        r1 = findViewById(R.id.rbq1);
        r2 = findViewById(R.id.rbq2);
        r3 = findViewById(R.id.rbq3);
        r4 = findViewById(R.id.rbq4);
        btnNext = findViewById(R.id.nxtQuestion);

        dfRBColor = r1.getTextColors();
        addQuestion();
        totalQuestion = questionLst.size();
        showNxtQuestion();

        // This checks whether or not you have picked an option before clicking submit, an alert box appears telling you to do so.
        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(answered == false){
                    if(r1.isChecked() || r2.isChecked() || r3.isChecked() || r4.isChecked()){
                        checkAnsw();
                        countDwnTimer.cancel();
                    }else{
                        Toast.makeText(quizStart.this, "Select an option!", Toast.LENGTH_SHORT);
                    }
                }else{
                    showNxtQuestion();
                }
            }
        });
    }

    //This method checks the answer and increments the score if you're correct. Also colour codes the wrong and correct answer once you click submit.
    private void checkAnsw() {
        answered = true;
        RadioButton rbSelected = findViewById(rdGroup.getCheckedRadioButtonId());
        int answNo = rdGroup.indexOfChild(rbSelected) + 1;
        if(answNo == crntQuestion.getCorrectAns()){
            score++;
            tScore.setText("Score: "+score);
        }
        r1.setTextColor(Color.RED);
        r2.setTextColor(Color.RED);
        r3.setTextColor(Color.RED);
        r4.setTextColor(Color.RED);
        switch(crntQuestion.getCorrectAns()){
            case 1:
                r1.setTextColor(Color.GREEN);
                break;
            case 2:
                r2.setTextColor(Color.GREEN);
                break;
            case 3:
                r3.setTextColor(Color.GREEN);
                break;
            case 4:
                r4.setTextColor(Color.GREEN);
                break;
        }
        if(questCounter < totalQuestion){
            btnNext.setText("Next");
        }else{
            btnNext.setText("Finish");
        }
    }

    // This method changes the textview of the question and option to the next on in the list while also updating the display on what question you're on.
    private void showNxtQuestion() {
        rdGroup.clearCheck();
        r1.setTextColor(dfRBColor);
        r2.setTextColor(dfRBColor);
        r3.setTextColor(dfRBColor);
        r4.setTextColor(dfRBColor);

        if(questCounter < totalQuestion){
            timer();
            crntQuestion = questionLst.get(questCounter);
            tQuestion.setText(crntQuestion.getQuestion());
            r1.setText(crntQuestion.getOption1());
            r2.setText(crntQuestion.getOption2());
            r3.setText(crntQuestion.getOption3());
            r4.setText(crntQuestion.getOption4());
            questCounter++;
            btnNext.setText("Submit");
            tQuestionNo.setText("Question: "+questCounter+"/"+totalQuestion);
            answered = false;
        } else{
            finish();
        }
    }

    //This method initialise the timer, setting it to 20 seconds and going down 1 second per tick. If the timer finishes, next question is displayed. 
    private void timer() {
        countDwnTimer = new CountDownTimer(20000, 1000){
            @Override
            public void onTick(long l) {
                tTimer.setText("00:" + 1/1000);
            }

            @Override
            public void onFinish() {
                showNxtQuestion();
            }
        }.start();
    }

    //This method add's a new list onto the variable with the paramters for the question, it's options and the correct answer.
    private void addQuestion(){
        questionLst.add(new QuestionList("What was the name of the German operation of the invasion of the Soviet Union?", "Herkules", "Barbarossa", "Blau", "Zitadelle ", 2));
        questionLst.add(new QuestionList("When did WW2 Begin?", "1915", "1936", "1939", "1946 ", 3));
        questionLst.add(new QuestionList("When did WW2 End?", "1917", "1938", "1942", "1945 ", 4));
        questionLst.add(new QuestionList("What was the name of the major allied landing on the coast of France during 1944?", "Normandy landings", "Red Landing", "Dusk landing", "Camarague", 1));
        questionLst.add(new QuestionList("Who was the leader of the Soviet Union during WW2?", "Stalin", "Lenin", "Khrushchev", "Trotsky", 1));
        questionLst.add(new QuestionList("Who was the leader of Nazi Germany?", "Otto Moritz", "Hermann Goering", "Adolf Hitler", "Erich Raeder", 3));
        questionLst.add(new QuestionList("What was the name of the major factions during WW2?", "Allies & Axis", "Entente & Central Powers", "North Legion & Confederates", "NCR & PCR", 1));
    }
}
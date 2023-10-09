package com.example.quiz;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;



public class QuizActivity extends AppCompatActivity {


    private TextView questionTextView;
    private RadioButton option1RadioButton;
    private RadioButton option2RadioButton;
    private RadioButton option3RadioButton;
    private Button nextButton;
    private RadioGroup optionsRadioGroup;

    private String[] questions;
    private String[] options;
    private int[] answers;
    private int currentQuestionIndex;
    private int score;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.quiz_activity);



        questionTextView = findViewById(R.id.question_text);
        option1RadioButton = findViewById(R.id.option1_radio);
        option2RadioButton = findViewById(R.id.option2_radio);
        option3RadioButton = findViewById(R.id.option3_radio);
        nextButton = findViewById(R.id.next_button);
        optionsRadioGroup = findViewById(R.id.options_radio_group);

        questions = getResources().getStringArray(R.array.questions);
        options = getResources().getStringArray(R.array.options);
        answers = getResources().getIntArray(R.array.answers);

        currentQuestionIndex = 0;
        score = 0;

        showQuestion();

        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAnswer();
                currentQuestionIndex++;
                if (currentQuestionIndex < questions.length) {
                    showQuestion();
                } else {
                    showResult();
                }
            }
        });
    }

    private void showQuestion() {
        questionTextView.setText(questions[currentQuestionIndex]);
        option1RadioButton.setText(options[currentQuestionIndex * 3]);
        option2RadioButton.setText(options[currentQuestionIndex * 3 + 1]);
        option3RadioButton.setText(options[currentQuestionIndex * 3 + 2]);
        optionsRadioGroup.clearCheck();
    }

    private void checkAnswer() {
        int selectedAnswer = optionsRadioGroup.getCheckedRadioButtonId();
        if (selectedAnswer != -1) {
            RadioButton selectedRadioButton = findViewById(selectedAnswer);
            int selectedOption = optionsRadioGroup.indexOfChild(selectedRadioButton);
            if (selectedOption == answers[currentQuestionIndex]) {
                score++;
            }
        }
    }

    private void showResult() {
        int maxScore = questions.length;
        int percentage = (score * 100) / maxScore;

        Intent intent = new Intent(QuizActivity.this, ResultActivity.class);
        intent.putExtra("score", score);
        intent.putExtra("maxScore", maxScore);
        startActivity(intent);

        finish();
    }
}


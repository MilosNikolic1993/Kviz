package com.example.quiz;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class ResultActivity extends AppCompatActivity {
    private TextView scoreTextView;
    private TextView gradeTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        scoreTextView = findViewById(R.id.score_text);
        gradeTextView = findViewById(R.id.grade_text);

        int score = getIntent().getIntExtra("score", 0);
        int maxScore = getIntent().getIntExtra("maxScore", 0);
        int percentage = (score * 100) / maxScore;
        String grade = calculateGrade(percentage);

        scoreTextView.setText("Rezultat: " + score + "/" + maxScore);
        gradeTextView.setText("Ocena: " + grade);
    }

    private String calculateGrade(int percentage) {
        if (percentage >= 90) {
            return "5";
        } else if (percentage >= 75) {
            return "4";
        } else if (percentage >= 60) {
            return "3";
        } else if (percentage >= 50) {
            return "2";
        } else {
            return "1";
        }
    }
}


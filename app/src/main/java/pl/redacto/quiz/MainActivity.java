package pl.redacto.quiz;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class MainActivity extends AppCompatActivity {
    int question = 1;
    int score = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    // Displays the result of the quiz
    public void displayResult(View view) {
        // Checks if it is a first submission
        if (question == 1) {
            score = 0;
            score = calculateResult();
            question = question + 1;
            // Presents the obtained score in a toast
            String finalResult = ("Your result: " + score);
            Toast.makeText(this, finalResult, Toast.LENGTH_LONG).show();
            // Prints correct answers
            TextView answers = (TextView) findViewById(R.id.answers);
            String answersMessage = "Correct answers:"+"\n"+"Question 1: B."+"\n"+"Question 2: D."+"\n"+"Question 3: transcription."+"\n"+"Question 4: D."+"\n"+"Question 5: A, D. The first drug produced using recombinant DNA technology was insulin. You're encouraged to read more on knockout mice."+"\n"+"Question 6: A." ;
            answers.setText(answersMessage);
        }else { // If the submit button is clicked more than once
            String toastMessage = "You can only submit once! Your scored: "+score;
            Toast.makeText(this, toastMessage, Toast.LENGTH_SHORT).show();
        }
    }
    // Calculates result (score)
    private int calculateResult() {
        // Finds the views with correct answers and checks if they are marked
        int score = 0;
        boolean checked1 = ((RadioButton) findViewById(R.id.b1)).isChecked();
        boolean checked2 = ((RadioButton) findViewById(R.id.d2)).isChecked();
        boolean checked4 = ((RadioButton) findViewById(R.id.d4)).isChecked();
        boolean checked6 = ((RadioButton) findViewById(R.id.a6)).isChecked();
        boolean checked5a = ((CheckBox) findViewById(R.id.a5)).isChecked();
        boolean checked5b = ((CheckBox) findViewById(R.id.b5)).isChecked();
        boolean checked5c = ((CheckBox) findViewById(R.id.c5)).isChecked();
        boolean checked5d = ((CheckBox) findViewById(R.id.d5)).isChecked();
        if (checked1) {
            // The correct answer: B.
            score = score + 1;
        }
        if (checked2) {
            // The correct answer: D.
            score = score + 1;
        }
        if (checked4) {
            // The correct answer: D.
            score = score + 1;
        }
        if (checked6) {
            // The correct answer: A.
            score = score + 1;
        }
        if (checked5a && checked5d && !checked5b && !checked5c) { // The correct answers: A, D.
            score = score + 1;
        }
        // Open question - allowed answers: transcription/Transcription --> TODO: periods? other characters in response?
        EditText transcription = findViewById(R.id.transcription_q);
        String answerTranscription = transcription.getText().toString().toLowerCase();
        if (answerTranscription.equals("transcription")) {
            score = score + 1;
        }
        return score;
    }
}
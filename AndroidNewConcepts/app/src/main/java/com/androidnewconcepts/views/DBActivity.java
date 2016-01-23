package com.androidnewconcepts.views;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.androidnewconcepts.R;
import com.androidnewconcepts.db.DatabaseHelper;
import com.androidnewconcepts.model.MCQModel;

import java.util.ArrayList;


/**
 * Created by indianic on 05/01/16.
 */
public class DBActivity extends Activity implements View.OnClickListener {
    private ArrayList<MCQModel> mcqModelArrayList;
    private TextView tvQuestion;
    private TextView tvOptionA;
    private TextView tvOptionB;
    private TextView tvOptionC;
    private TextView tvOptionD;
    private String answer = "";
    private int qno = 0;
    private int totalRecords = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.row_mcq);
        Log.e("DBActivity", "DBActivity");
        initComponents();
        addComponents();
        initDatabase();


    }

    private void addComponents() {
        tvOptionA.setOnClickListener(this);
        tvOptionB.setOnClickListener(this);
        tvOptionC.setOnClickListener(this);
        tvOptionD.setOnClickListener(this);

    }

    private void initComponents() {
        tvQuestion = (TextView) findViewById(R.id.row_mcq_tv_question);
        tvOptionA = (TextView) findViewById(R.id.row_mcq_tv_optionA);
        tvOptionB = (TextView) findViewById(R.id.row_mcq_tv_optionB);
        tvOptionC = (TextView) findViewById(R.id.row_mcq_tv_optionC);
        tvOptionD = (TextView) findViewById(R.id.row_mcq_tv_optionD);


    }

    private void initDatabase() {
        DatabaseHelper dbHelper = new DatabaseHelper(this);
        dbHelper.openDataBase();
        mcqModelArrayList = dbHelper.getAllMCQ();
        totalRecords = mcqModelArrayList.size();
        Log.e("mcqModelArrayListSize", "" + mcqModelArrayList.size());
        moveToNextQuestion(qno);

    }


    @Override
    public void onClick(View view) {
        if (view == tvOptionA) {
            checkCorrectAns("A", tvOptionA);
        } else if (view == tvOptionB) {
            checkCorrectAns("B", tvOptionB);
        } else if (view == tvOptionC) {
            checkCorrectAns("C", tvOptionC);
        } else if (view == tvOptionD) {
            checkCorrectAns("D", tvOptionD);
        }
    }

    private void checkCorrectAns(String optionName, TextView tv) {
        if (optionName.equalsIgnoreCase(answer)) {
            tv.setBackgroundColor(Color.GREEN);

            qno = qno + 1;
            if (qno == totalRecords) {
                Toast.makeText(DBActivity.this, "FINISH", Toast.LENGTH_LONG).show();
            } else {
                moveToNextQuestion(qno);
            }


        } else {
            tv.setBackgroundColor(Color.RED);
        }
    }

    private void moveToNextQuestion(int qno) {
        clearQA();
        loadNextQA(qno);
    }

    private void loadNextQA(int qno) {

        tvQuestion.setText("(Q." + (qno + 1) + ")" + mcqModelArrayList.get(qno).getQuestion());
        tvOptionA.setText("(A) " + mcqModelArrayList.get(qno).getOptionA());
        tvOptionB.setText("(B) " + mcqModelArrayList.get(qno).getOptionB());
        tvOptionC.setText("(C) " + mcqModelArrayList.get(qno).getOptionC());
        tvOptionD.setText("(D) " + mcqModelArrayList.get(qno).getOptionD());
        answer = mcqModelArrayList.get(qno).getAnswer();
    }

    private void clearQA() {
        tvQuestion.setText("");
        tvOptionA.setText("");
        tvOptionB.setText("");
        tvOptionC.setText("");
        tvOptionD.setText("");
        answer = "";
        clearAllBackgrounds();

    }

    private void clearAllBackgrounds() {
        tvOptionA.setBackgroundColor(Color.WHITE);
        tvOptionB.setBackgroundColor(Color.WHITE);
        tvOptionC.setBackgroundColor(Color.WHITE);
        tvOptionD.setBackgroundColor(Color.WHITE);
    }
}

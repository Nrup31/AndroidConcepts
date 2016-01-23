package com.androidnewconcepts.model;

import java.util.ArrayList;

/**
 * Created by indianic on 05/01/16.
 */
public class MCQModel {
    private int qNo = 0;
    private String question = "";
    private String optionA = "";
    private String optionB = "";
    private String optionC = "";
    private String optionD = "";
    private String answer = "";


    private ArrayList<MCQModel> mcqModelArrayList = new ArrayList<>();

    public  int getqNo() {
        return qNo;
    }

    public void setqNo(int qNo) {
        this.qNo = qNo;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getOptionA() {
        return optionA;
    }

    public void setOptionA(String optionA) {
        this.optionA = optionA;
    }

    public String getOptionB() {
        return optionB;
    }

    public void setOptionB(String optionB) {
        this.optionB = optionB;
    }

    public String getOptionC() {
        return optionC;
    }

    public void setOptionC(String optionC) {
        this.optionC = optionC;
    }

    public String getOptionD() {
        return optionD;
    }

    public void setOptionD(String optionD) {
        this.optionD = optionD;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public ArrayList<MCQModel> getMcqModelArrayList() {
        return mcqModelArrayList;
    }

    public void setMcqModelArrayList(ArrayList<MCQModel> mcqModelArrayList) {
        this.mcqModelArrayList = mcqModelArrayList;
    }

}
